package com.hengsu.uliketu.mall.service.impl;

import static com.hengsu.uliketu.core.ErrorCode.*;

import com.hengsu.uliketu.core.Consts;
import com.hengsu.uliketu.core.model.RecommendRelationModel;
import com.hengsu.uliketu.core.model.StatementModel;
import com.hengsu.uliketu.core.model.UserModel;
import com.hengsu.uliketu.core.service.MessageService;
import com.hengsu.uliketu.core.service.RecommendRelationService;
import com.hengsu.uliketu.core.service.UserService;
import com.hengsu.uliketu.mall.model.ShoppingLogModel;
import com.hengsu.uliketu.mall.service.GoodsService;
import com.hengsu.uliketu.mall.service.ShoppingLogService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.mall.entity.Shopping;
import com.hengsu.uliketu.mall.repository.ShoppingRepository;
import com.hengsu.uliketu.mall.model.ShoppingModel;
import com.hengsu.uliketu.mall.service.ShoppingService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.Date;
import java.util.List;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    private final Logger logger = LoggerFactory.getLogger(ShoppingServiceImpl.class);
    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingRepository shoppingRepo;

    @Autowired
    private ShoppingLogService shoppingLogService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RecommendRelationService recommendRelationService;

    @Autowired
    private MessageService messageService;


    @Transactional
    @Override
    public int create(ShoppingModel shoppingModel) {
        return shoppingRepo.insert(beanMapper.map(shoppingModel, Shopping.class));
    }

    @Transactional
    @Override
    public int createSelective(ShoppingModel shoppingModel) {
        return shoppingRepo.insertSelective(beanMapper.map(shoppingModel, Shopping.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return shoppingRepo.deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void buyGoods(Long id, Long userId) {

        //实名认证
        UserModel userModel = userService.findByPrimaryKey(userId);
        if(UserModel.CERTIFIED!=userModel.getCertifie()){
            throwBusinessException(SHOPPING_NOT_CERTIFIED);
        }

        //减少库存
        int result = reduceRepertory(id);
        if (result == 1) {

            //检查用户余额
            userService.addBalance(userId, -100L, StatementModel.GOODS,"购买shangp"+id+"");

            //购买成功
            ShoppingLogModel shoppingLogModel = new ShoppingLogModel();
            shoppingLogModel.setShoppingId(id);
            shoppingLogModel.setUserId(userId);
            shoppingLogModel.setShoppingTime(new Date());
            shoppingLogService.createSelective(shoppingLogModel);
        } else {
            //已经卖完
            ShoppingModel shoppingModel = findByPrimaryKey(id);
            if (ShoppingModel.STATUS_UNFINISH != shoppingModel.getStatus()) {
                throwBusinessException(SHOPPING_HAVE_FINISHED);
            }
            ShoppingModel param = new ShoppingModel();
            param.setId(id);
            param.setStatus(ShoppingModel.STATUS_WATTING_LOTTERY);
            updateByPrimaryKeySelective(param);

            shoppingLogService.lottery(shoppingModel);
        }

        //推荐奖
        RecommendRelationModel rrm = new RecommendRelationModel();
        rrm.setRecommendId(userId);
        rrm.setStatus(RecommendRelationModel.RECOMMEDN_STATUS_USED);
        List<RecommendRelationModel> recommendRelationModels =
                recommendRelationService.selectPage(rrm,new PageRequest(0,Integer.MAX_VALUE));
        if(CollectionUtils.isNotEmpty(recommendRelationModels)){
            RecommendRelationModel recommendRelationModel = recommendRelationModels.get(0);
            userService.addBalance(userId,recommendRelationModel.getNum(),StatementModel.RECOMMEND,"推荐奖");
            messageService.addMessage(Consts.RECOMMEDN,userId);
        }



    }

    @Transactional(readOnly = true)
    @Override
    public ShoppingModel findByPrimaryKey(Long id) {
        Shopping shopping = shoppingRepo.selectByPrimaryKey(id);
        return beanMapper.map(shopping, ShoppingModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public long selectCount(ShoppingModel shoppingModel) {
        return shoppingRepo.selectCount(beanMapper.map(shoppingModel, Shopping.class));
    }

    @Override
    public List<ShoppingModel> selectPage(ShoppingModel shoppingModel, Pageable pageable) {
        return null;
    }

    @Transactional
    @Override
    public int reduceRepertory(Long id) {
        return shoppingRepo.reduceRepertory(id);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(ShoppingModel shoppingModel) {
        return shoppingRepo.updateByPrimaryKey(beanMapper.map(shoppingModel, Shopping.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(ShoppingModel shoppingModel) {
        return shoppingRepo.updateByPrimaryKeySelective(beanMapper.map(shoppingModel, Shopping.class));
    }

}

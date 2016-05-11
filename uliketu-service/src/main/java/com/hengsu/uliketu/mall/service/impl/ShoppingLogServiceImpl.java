package com.hengsu.uliketu.mall.service.impl;

import com.hengsu.uliketu.core.Consts;
import com.hengsu.uliketu.core.service.MessageService;
import com.hengsu.uliketu.core.service.RecommendRelationService;
import com.hengsu.uliketu.mall.model.ShoppingModel;
import com.hengsu.uliketu.mall.model.WinnerModel;
import com.hengsu.uliketu.mall.service.GoodsService;
import com.hengsu.uliketu.mall.service.ShoppingService;
import com.hengsu.uliketu.mall.service.WinnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.mall.entity.ShoppingLog;
import com.hengsu.uliketu.mall.repository.ShoppingLogRepository;
import com.hengsu.uliketu.mall.model.ShoppingLogModel;
import com.hengsu.uliketu.mall.service.ShoppingLogService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ShoppingLogServiceImpl implements ShoppingLogService {

    private final Logger logger = LoggerFactory.getLogger(ShoppingLogServiceImpl.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private ShoppingLogRepository shoppingLogRepo;

    @Autowired
    private ShoppingService shoppingService;

    @Autowired
    private WinnerService winnerService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MessageService messageService;

    @Transactional
    @Override
    public int create(ShoppingLogModel shoppingLogModel) {
        return shoppingLogRepo.insert(beanMapper.map(shoppingLogModel, ShoppingLog.class));
    }

    @Transactional
    @Override
    public int createSelective(ShoppingLogModel shoppingLogModel) {
        return shoppingLogRepo.insertSelective(beanMapper.map(shoppingLogModel, ShoppingLog.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return shoppingLogRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public ShoppingLogModel findByPrimaryKey(Long id) {
        ShoppingLog shoppingLog = shoppingLogRepo.selectByPrimaryKey(id);
        return beanMapper.map(shoppingLog, ShoppingLogModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public long selectCount(ShoppingLogModel shoppingLogModel) {
        return shoppingLogRepo.selectCount(beanMapper.map(shoppingLogModel, ShoppingLog.class));
    }

    @Async
    @Override
    @Transactional
    public void lottery(ShoppingModel shoppingModel) {

        synchronized (this) {

            logger.info("start lottery,shopping id = {}", shoppingModel.getId());

            ShoppingModel param = new ShoppingModel();
            param.setGoodsId(shoppingModel.getGoodsId());
            param.setStatus(ShoppingModel.STATUS_UNFINISH);
            if (shoppingService.selectCount(param) > 0) {
                return;
            }

            //开始新一期抢购
            goodsService.startNewShopping(shoppingModel.getGoodsId());

            //抽奖
            ShoppingLogModel logparam = new ShoppingLogModel();
            long count = selectCount(logparam);
            int index = new Random().nextInt((int) count);
            Pageable pageable = new PageRequest(index, 1);
            List<ShoppingLogModel> shoppingLogModels = selectPage(logparam, pageable);
            ShoppingLogModel shoppingLogModel = shoppingLogModels.get(0);

            logger.info("lottery user ,user id = {}", shoppingLogModel.getId());
            WinnerModel winnerModel = new WinnerModel();
            winnerModel.setCreateTime(new Date());
            winnerModel.setUserId(shoppingLogModel.getUserId());
            winnerModel.setShoppingId(shoppingModel.getId());
            winnerService.createSelective(winnerModel);

            //更新状态
            ShoppingModel updateParam = new ShoppingModel();
            updateParam.setId(shoppingModel.getId());
            updateParam.setStatus(ShoppingModel.STATUS_FINISHED);
            updateParam.setFinishTime(new Date());
            shoppingService.updateByPrimaryKeySelective(updateParam);

            messageService.addMessage(Consts.LOTTERY,shoppingLogModel.getUserId());
        }


    }

    @Override
    public List<ShoppingLogModel> selectPage(ShoppingLogModel shoppingLogModel, Pageable pageable) {
        List<ShoppingLog> shoppingLogs = shoppingLogRepo.selectPage(beanMapper.map(shoppingLogModel, ShoppingLog.class), pageable);
        return beanMapper.mapAsList(shoppingLogs, ShoppingLogModel.class);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(ShoppingLogModel shoppingLogModel) {
        return shoppingLogRepo.updateByPrimaryKey(beanMapper.map(shoppingLogModel, ShoppingLog.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(ShoppingLogModel shoppingLogModel) {
        return shoppingLogRepo.updateByPrimaryKeySelective(beanMapper.map(shoppingLogModel, ShoppingLog.class));
    }

}

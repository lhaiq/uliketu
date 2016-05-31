package com.hengsu.uliketu.mall.service.impl;

import static com.hengsu.uliketu.core.ErrorCode.*;

import com.hengsu.uliketu.mall.model.ShoppingModel;
import com.hengsu.uliketu.mall.service.ShoppingService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.mall.entity.Goods;
import com.hengsu.uliketu.mall.repository.GoodsRepository;
import com.hengsu.uliketu.mall.model.GoodsModel;
import com.hengsu.uliketu.mall.service.GoodsService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private GoodsRepository goodsRepo;

    @Autowired
    private ShoppingService shoppingService;

    @Transactional
    @Override
    public int create(GoodsModel goodsModel) {
        return goodsRepo.insert(beanMapper.map(goodsModel, Goods.class));
    }

    @Transactional
    @Override
    public int createSelective(GoodsModel goodsModel) {
        Goods goods = beanMapper.map(goodsModel, Goods.class);
        int ret = goodsRepo.insertSelective(goods);
        goodsModel.setId(goods.getId());
        return ret;
    }

    @Transactional
    @Override
    public void saveAndShelve(GoodsModel goodsModel) {
        //保存
        createSelective(goodsModel);

        //上架
        shelve(goodsModel.getId());
    }

    @Transactional
    @Override
    public void shelve(Long id) {

        GoodsModel goodsModel = findByPrimaryKey(id);

        //判断状态  未上架才能上架
        if (goodsModel == null || GoodsModel.GOODS_STATUS_UNSHELVE != goodsModel.getStatus()) {
            throwBusinessException(CANNOT_SHELVE);
        }

        GoodsModel param = new GoodsModel();
        param.setId(id);
        param.setStatus(GoodsModel.GOODS_STATUS_SHELVE);
        updateByPrimaryKeySelective(param);

        startNewShopping(id);

    }

    @Transactional
    @Override
    public void unShelve(Long id) {

        //正在抢购的商品不能下架
        ShoppingModel param = new ShoppingModel();
        param.setGoodsId(id);
        List<ShoppingModel> shoppingModels = shoppingService.selectPage(param, new PageRequest(0, Integer.MAX_VALUE));
        if (CollectionUtils.isEmpty(shoppingModels)) return;
        for (ShoppingModel shoppingModel : shoppingModels) {
            if (shoppingModel.getRemainNum() != 0) {
                throwBusinessException(CANNOT_UNSHELVE);
            }
        }

        GoodsModel updateParam = new GoodsModel();
        updateParam.setId(id);
        updateParam.setStatus(GoodsModel.GOODS_STATUS_UNSHELVE);
        updateByPrimaryKeySelective(updateParam);

    }

    @Override
    public void preUnShelve(Long id) {
        GoodsModel param = new GoodsModel();
        param.setId(id);
        param.setStatus(GoodsModel.GOODS_STATUS_PRESHELVE);
        updateByPrimaryKeySelective(param);
    }

    @Transactional
    @Override
    public void startNewShopping(Long id) {

        GoodsModel goodsModel = findByPrimaryKey(id);

        //如果处于预下架,则下架
        if (GoodsModel.GOODS_STATUS_PRESHELVE == goodsModel.getStatus()) {
            unShelve(id);
            return;
        }

        //判断是否超出期数
        if (goodsModel.getExcutePeriods() >= goodsModel.getPeriods()) {
            return;
        }

        //新的shopping
        ShoppingModel shoppingModel = new ShoppingModel();
        shoppingModel.setGoodsId(goodsModel.getId());
        shoppingModel.setPeriods(goodsModel.getExcutePeriods() + 1);
        shoppingModel.setRemainNum(goodsModel.getNum());
        shoppingService.createSelective(shoppingModel);

        //更新商品
        GoodsModel param = new GoodsModel();
        param.setId(goodsModel.getId());
        param.setExcutePeriods(goodsModel.getExcutePeriods() + 1);
        updateByPrimaryKeySelective(param);

    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return goodsRepo.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteGoods(Long id) {
        //正在抢购的商品不能下架
        ShoppingModel param = new ShoppingModel();
        param.setGoodsId(id);
        List<ShoppingModel> shoppingModels = shoppingService.selectPage(param, new PageRequest(0, Integer.MAX_VALUE));
        for (ShoppingModel shoppingModel : shoppingModels) {
            if (shoppingModel.getRemainNum() != 0) {
                throwBusinessException(CANNOT_UNSHELVE);
            }
        }

        return deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public GoodsModel findByPrimaryKey(Long id) {
        Goods goods = goodsRepo.selectByPrimaryKey(id);
        return beanMapper.map(goods, GoodsModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public long selectCount(GoodsModel goodsModel) {
        return goodsRepo.selectCount(beanMapper.map(goodsModel, Goods.class));
    }

    @Override
    public List<GoodsModel> selectPage(GoodsModel goodsModel, Pageable pageable) {
        List<Goods> goodses = goodsRepo.selectPage(beanMapper.map(goodsModel, Goods.class), pageable);
        return beanMapper.mapAsList(goodses, GoodsModel.class);
    }

    @Override
    public List<GoodsModel> selectShopping(Long cateGoryId) {
        return beanMapper.mapAsList(goodsRepo.selectShopping(cateGoryId), GoodsModel.class);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(GoodsModel goodsModel) {
        return goodsRepo.updateByPrimaryKey(beanMapper.map(goodsModel, Goods.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(GoodsModel goodsModel) {
        return goodsRepo.updateByPrimaryKeySelective(beanMapper.map(goodsModel, Goods.class));
    }

    @Override
    public int updateGoods(GoodsModel goodsModel) {
        GoodsModel param = findByPrimaryKey(goodsModel.getId());
        if (GoodsModel.GOODS_STATUS_UNSHELVE != param.getStatus()) {
            throwBusinessException(CANNOT_UPDATE);
        }
        return updateByPrimaryKeySelective(goodsModel);
    }

}

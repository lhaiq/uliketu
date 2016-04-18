package com.hengsu.uliketu.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Transactional
	@Override
	public int create(GoodsModel goodsModel) {
		return goodsRepo.insert(beanMapper.map(goodsModel, Goods.class));
	}

	@Transactional
	@Override
	public int createSelective(GoodsModel goodsModel) {
		return goodsRepo.insertSelective(beanMapper.map(goodsModel, Goods.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return goodsRepo.deleteByPrimaryKey(id);
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
		List<Goods> goodses = goodsRepo.selectPage(beanMapper.map(goodsModel,Goods.class),pageable);
		return beanMapper.mapAsList(goodses,GoodsModel.class);
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

}

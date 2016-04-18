package com.hengsu.uliketu.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.mall.entity.Shopping;
import com.hengsu.uliketu.mall.repository.ShoppingRepository;
import com.hengsu.uliketu.mall.model.ShoppingModel;
import com.hengsu.uliketu.mall.service.ShoppingService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class ShoppingServiceImpl implements ShoppingService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private ShoppingRepository shoppingRepo;

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

	@Transactional(readOnly = true)
	@Override
	public ShoppingModel findByPrimaryKey(Long id) {
		Shopping shopping = shoppingRepo.selectByPrimaryKey(id);
		return beanMapper.map(shopping, ShoppingModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(ShoppingModel shoppingModel) {
		return shoppingRepo.selectCount(beanMapper.map(shoppingModel, Shopping.class));
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

package com.hengsu.uliketu.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.mall.entity.ShoppingLog;
import com.hengsu.uliketu.mall.repository.ShoppingLogRepository;
import com.hengsu.uliketu.mall.model.ShoppingLogModel;
import com.hengsu.uliketu.mall.service.ShoppingLogService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class ShoppingLogServiceImpl implements ShoppingLogService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private ShoppingLogRepository shoppingLogRepo;

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
	public int selectCount(ShoppingLogModel shoppingLogModel) {
		return shoppingLogRepo.selectCount(beanMapper.map(shoppingLogModel, ShoppingLog.class));
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

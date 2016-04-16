package com.hengsu.uliketu.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.core.entity.Conf;
import com.hengsu.uliketu.core.repository.ConfRepository;
import com.hengsu.uliketu.core.model.ConfModel;
import com.hengsu.uliketu.core.service.ConfService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class ConfServiceImpl implements ConfService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private ConfRepository confRepo;

	@Transactional(readOnly = true)
	@Override
	public ConfModel findByPrimaryKey(String id) {
		Conf conf = confRepo.selectByPrimaryKey(id);
		return beanMapper.map(conf, ConfModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(ConfModel confModel) {
		return confRepo.updateByPrimaryKey(beanMapper.map(confModel, Conf.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(ConfModel confModel) {
		return confRepo.updateByPrimaryKeySelective(beanMapper.map(confModel, Conf.class));
	}

	@Override
	public double findDouble(String key) {
		return Double.parseDouble(findByPrimaryKey(key).getConfValue());
	}

	@Override
	public String findString(String key) {
		return findByPrimaryKey(key).getConfValue();
	}

	@Override
	public int findInt(String key) {
		return Integer.parseInt(findByPrimaryKey(key).getConfValue());
	}

	@Override
	public long findLong(String key) {
		return Long.parseLong(findByPrimaryKey(key).getConfValue());
	}

}

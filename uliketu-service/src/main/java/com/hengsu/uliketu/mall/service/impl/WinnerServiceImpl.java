package com.hengsu.uliketu.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.mall.entity.Winner;
import com.hengsu.uliketu.mall.repository.WinnerRepository;
import com.hengsu.uliketu.mall.model.WinnerModel;
import com.hengsu.uliketu.mall.service.WinnerService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class WinnerServiceImpl implements WinnerService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private WinnerRepository winnerRepo;

	@Transactional
	@Override
	public int create(WinnerModel winnerModel) {
		return winnerRepo.insert(beanMapper.map(winnerModel, Winner.class));
	}

	@Transactional
	@Override
	public int createSelective(WinnerModel winnerModel) {
		return winnerRepo.insertSelective(beanMapper.map(winnerModel, Winner.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return winnerRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public WinnerModel findByPrimaryKey(Long id) {
		Winner winner = winnerRepo.selectByPrimaryKey(id);
		return beanMapper.map(winner, WinnerModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(WinnerModel winnerModel) {
		return winnerRepo.selectCount(beanMapper.map(winnerModel, Winner.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(WinnerModel winnerModel) {
		return winnerRepo.updateByPrimaryKey(beanMapper.map(winnerModel, Winner.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(WinnerModel winnerModel) {
		return winnerRepo.updateByPrimaryKeySelective(beanMapper.map(winnerModel, Winner.class));
	}

}

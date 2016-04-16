package com.hengsu.uliketu.nav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.nav.entity.LinkClick;
import com.hengsu.uliketu.nav.repository.LinkClickRepository;
import com.hengsu.uliketu.nav.model.LinkClickModel;
import com.hengsu.uliketu.nav.service.LinkClickService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class LinkClickServiceImpl implements LinkClickService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private LinkClickRepository linkClickRepo;

	@Transactional
	@Override
	public int create(LinkClickModel linkClickModel) {
		return linkClickRepo.insert(beanMapper.map(linkClickModel, LinkClick.class));
	}

	@Transactional
	@Override
	public int createSelective(LinkClickModel linkClickModel) {
		return linkClickRepo.insertSelective(beanMapper.map(linkClickModel, LinkClick.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return linkClickRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public LinkClickModel findByPrimaryKey(Long id) {
		LinkClick linkClick = linkClickRepo.selectByPrimaryKey(id);
		return beanMapper.map(linkClick, LinkClickModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(LinkClickModel linkClickModel) {
		return linkClickRepo.selectCount(beanMapper.map(linkClickModel, LinkClick.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(LinkClickModel linkClickModel) {
		return linkClickRepo.updateByPrimaryKey(beanMapper.map(linkClickModel, LinkClick.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(LinkClickModel linkClickModel) {
		return linkClickRepo.updateByPrimaryKeySelective(beanMapper.map(linkClickModel, LinkClick.class));
	}

}

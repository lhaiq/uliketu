package com.hengsu.uliketu.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.core.entity.Website;
import com.hengsu.uliketu.core.repository.WebsiteRepository;
import com.hengsu.uliketu.core.model.WebsiteModel;
import com.hengsu.uliketu.core.service.WebsiteService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class WebsiteServiceImpl implements WebsiteService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private WebsiteRepository websiteRepo;

	@Transactional
	@Override
	public int create(WebsiteModel websiteModel) {
		return websiteRepo.insert(beanMapper.map(websiteModel, Website.class));
	}

	@Transactional
	@Override
	public int createSelective(WebsiteModel websiteModel) {
		return websiteRepo.insertSelective(beanMapper.map(websiteModel, Website.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return websiteRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public WebsiteModel findByPrimaryKey(Long id) {
		Website website = websiteRepo.selectByPrimaryKey(id);
		return beanMapper.map(website, WebsiteModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(WebsiteModel websiteModel) {
		return websiteRepo.selectCount(beanMapper.map(websiteModel, Website.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(WebsiteModel websiteModel) {
		return websiteRepo.updateByPrimaryKey(beanMapper.map(websiteModel, Website.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(WebsiteModel websiteModel) {
		return websiteRepo.updateByPrimaryKeySelective(beanMapper.map(websiteModel, Website.class));
	}

}

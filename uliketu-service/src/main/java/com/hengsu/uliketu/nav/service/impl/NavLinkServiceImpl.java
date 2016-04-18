package com.hengsu.uliketu.nav.service.impl;

import com.hengsu.uliketu.nav.model.LinkClickModel;
import com.hengsu.uliketu.nav.service.LinkClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.nav.entity.NavLink;
import com.hengsu.uliketu.nav.repository.NavLinkRepository;
import com.hengsu.uliketu.nav.model.NavLinkModel;
import com.hengsu.uliketu.nav.service.NavLinkService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.Date;
import java.util.List;

@Service
public class NavLinkServiceImpl implements NavLinkService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private NavLinkRepository navLinkRepo;

	@Autowired
	private LinkClickService linkClickService;

	@Transactional
	@Override
	public int create(NavLinkModel navLinkModel) {
		return navLinkRepo.insert(beanMapper.map(navLinkModel, NavLink.class));
	}

	@Transactional
	@Override
	public int createSelective(NavLinkModel navLinkModel) {
		return navLinkRepo.insertSelective(beanMapper.map(navLinkModel, NavLink.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return navLinkRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public NavLinkModel findByPrimaryKey(Long id) {
		NavLink navLink = navLinkRepo.selectByPrimaryKey(id);
		return beanMapper.map(navLink, NavLinkModel.class);
	}

	@Transactional
	@Override
	public void clickLink(Long id, Long userId) {
		LinkClickModel linkClickModel = new LinkClickModel();
		linkClickModel.setClickTime(new Date());
		linkClickModel.setUserId(userId);
		linkClickModel.setLinkId(id);
		linkClickService.createSelective(linkClickModel);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(NavLinkModel navLinkModel) {
		return navLinkRepo.selectCount(beanMapper.map(navLinkModel, NavLink.class));
	}

	@Override
	public List<NavLinkModel> selectPage(NavLinkModel navLinkModel, Pageable pageable) {
		List<NavLink> navLinks = navLinkRepo.selectPage(beanMapper.map(navLinkModel,NavLink.class),pageable);
		return beanMapper.mapAsList(navLinks,NavLinkModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(NavLinkModel navLinkModel) {
		return navLinkRepo.updateByPrimaryKey(beanMapper.map(navLinkModel, NavLink.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(NavLinkModel navLinkModel) {
		return navLinkRepo.updateByPrimaryKeySelective(beanMapper.map(navLinkModel, NavLink.class));
	}

}

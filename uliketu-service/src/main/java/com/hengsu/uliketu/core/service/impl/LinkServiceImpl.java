package com.hengsu.uliketu.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.core.entity.Link;
import com.hengsu.uliketu.core.repository.LinkRepository;
import com.hengsu.uliketu.core.model.LinkModel;
import com.hengsu.uliketu.core.service.LinkService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private LinkRepository linkRepo;

	@Transactional
	@Override
	public int create(LinkModel linkModel) {
		return linkRepo.insert(beanMapper.map(linkModel, Link.class));
	}

	@Transactional
	@Override
	public int createSelective(LinkModel linkModel) {
		return linkRepo.insertSelective(beanMapper.map(linkModel, Link.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return linkRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public LinkModel findByPrimaryKey(Integer id) {
		Link link = linkRepo.selectByPrimaryKey(id);
		return beanMapper.map(link, LinkModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(LinkModel linkModel) {
		return linkRepo.selectCount(beanMapper.map(linkModel, Link.class));
	}

	@Override
	public List<LinkModel> selectPage(LinkModel linkModel, Pageable pageable) {
		List<Link> links = linkRepo.selectPage(beanMapper.map(linkModel,Link.class),pageable);
		return beanMapper.mapAsList(links,LinkModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(LinkModel linkModel) {
		return linkRepo.updateByPrimaryKey(beanMapper.map(linkModel, Link.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(LinkModel linkModel) {
		return linkRepo.updateByPrimaryKeySelective(beanMapper.map(linkModel, Link.class));
	}

}

package com.hengsu.uliketu.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.core.entity.RecommendRelation;
import com.hengsu.uliketu.core.repository.RecommendRelationRepository;
import com.hengsu.uliketu.core.model.RecommendRelationModel;
import com.hengsu.uliketu.core.service.RecommendRelationService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class RecommendRelationServiceImpl implements RecommendRelationService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private RecommendRelationRepository recommendRelationRepo;

	@Transactional
	@Override
	public int create(RecommendRelationModel recommendRelationModel) {
		return recommendRelationRepo.insert(beanMapper.map(recommendRelationModel, RecommendRelation.class));
	}

	@Transactional
	@Override
	public int createSelective(RecommendRelationModel recommendRelationModel) {
		return recommendRelationRepo.insertSelective(beanMapper.map(recommendRelationModel, RecommendRelation.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return recommendRelationRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public RecommendRelationModel findByPrimaryKey(Long id) {
		RecommendRelation recommendRelation = recommendRelationRepo.selectByPrimaryKey(id);
		return beanMapper.map(recommendRelation, RecommendRelationModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(RecommendRelationModel recommendRelationModel) {
		return recommendRelationRepo.selectCount(beanMapper.map(recommendRelationModel, RecommendRelation.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(RecommendRelationModel recommendRelationModel) {
		return recommendRelationRepo.updateByPrimaryKey(beanMapper.map(recommendRelationModel, RecommendRelation.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(RecommendRelationModel recommendRelationModel) {
		return recommendRelationRepo.updateByPrimaryKeySelective(beanMapper.map(recommendRelationModel, RecommendRelation.class));
	}

}

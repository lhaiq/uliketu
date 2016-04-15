package com.hengsu.uliketu.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.core.entity.Advertisement;
import com.hengsu.uliketu.core.repository.AdvertisementRepository;
import com.hengsu.uliketu.core.model.AdvertisementModel;
import com.hengsu.uliketu.core.service.AdvertisementService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private AdvertisementRepository advertisementRepo;

	@Transactional
	@Override
	public int create(AdvertisementModel advertisementModel) {
		return advertisementRepo.insert(beanMapper.map(advertisementModel, Advertisement.class));
	}

	@Transactional
	@Override
	public int createSelective(AdvertisementModel advertisementModel) {
		return advertisementRepo.insertSelective(beanMapper.map(advertisementModel, Advertisement.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return advertisementRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public AdvertisementModel findByPrimaryKey(Integer id) {
		Advertisement advertisement = advertisementRepo.selectByPrimaryKey(id);
		return beanMapper.map(advertisement, AdvertisementModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(AdvertisementModel advertisementModel) {
		return advertisementRepo.selectCount(beanMapper.map(advertisementModel, Advertisement.class));
	}

	@Override
	public List<AdvertisementModel> selectPage(AdvertisementModel advertisementModel, Pageable pageable) {
		List<Advertisement> advertisements = advertisementRepo.selectPage
				(beanMapper.map(advertisementModel,Advertisement.class),pageable);
		return beanMapper.mapAsList(advertisements,AdvertisementModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(AdvertisementModel advertisementModel) {
		return advertisementRepo.updateByPrimaryKey(beanMapper.map(advertisementModel, Advertisement.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(AdvertisementModel advertisementModel) {
		return advertisementRepo.updateByPrimaryKeySelective(beanMapper.map(advertisementModel, Advertisement.class));
	}

}

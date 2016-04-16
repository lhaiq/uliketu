package com.hengsu.uliketu.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.core.entity.Image;
import com.hengsu.uliketu.core.repository.ImageRepository;
import com.hengsu.uliketu.core.model.ImageModel;
import com.hengsu.uliketu.core.service.ImageService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private ImageRepository imageRepo;

	@Transactional
	@Override
	public int create(ImageModel imageModel) {
		return imageRepo.insert(beanMapper.map(imageModel, Image.class));
	}

	@Transactional
	@Override
	public int createSelective(ImageModel imageModel) {
		return imageRepo.insertSelective(beanMapper.map(imageModel, Image.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return imageRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public ImageModel findByPrimaryKey(Long id) {
		Image image = imageRepo.selectByPrimaryKey(id);
		return beanMapper.map(image, ImageModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(ImageModel imageModel) {
		return imageRepo.selectCount(beanMapper.map(imageModel, Image.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(ImageModel imageModel) {
		return imageRepo.updateByPrimaryKey(beanMapper.map(imageModel, Image.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(ImageModel imageModel) {
		return imageRepo.updateByPrimaryKeySelective(beanMapper.map(imageModel, Image.class));
	}

}

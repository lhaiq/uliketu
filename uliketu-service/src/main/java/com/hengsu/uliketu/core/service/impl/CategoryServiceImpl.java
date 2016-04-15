package com.hengsu.uliketu.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.core.entity.Category;
import com.hengsu.uliketu.core.repository.CategoryRepository;
import com.hengsu.uliketu.core.model.CategoryModel;
import com.hengsu.uliketu.core.service.CategoryService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private CategoryRepository categoryRepo;

	@Transactional
	@Override
	public int create(CategoryModel categoryModel) {
		return categoryRepo.insert(beanMapper.map(categoryModel, Category.class));
	}

	@Transactional
	@Override
	public int createSelective(CategoryModel categoryModel) {
		return categoryRepo.insertSelective(beanMapper.map(categoryModel, Category.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return categoryRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public CategoryModel findByPrimaryKey(Long id) {
		Category category = categoryRepo.selectByPrimaryKey(id);
		return beanMapper.map(category, CategoryModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(CategoryModel categoryModel) {
		return categoryRepo.selectCount(beanMapper.map(categoryModel, Category.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(CategoryModel categoryModel) {
		return categoryRepo.updateByPrimaryKey(beanMapper.map(categoryModel, Category.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(CategoryModel categoryModel) {
		return categoryRepo.updateByPrimaryKeySelective(beanMapper.map(categoryModel, Category.class));
	}

}

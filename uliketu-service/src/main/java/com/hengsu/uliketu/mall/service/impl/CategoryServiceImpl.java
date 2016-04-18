package com.hengsu.uliketu.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.mall.entity.Category;
import com.hengsu.uliketu.mall.repository.CategoryRepository;
import com.hengsu.uliketu.mall.model.CategoryModel;
import com.hengsu.uliketu.mall.service.CategoryService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

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
    public long selectCount(CategoryModel categoryModel) {
        return categoryRepo.selectCount(beanMapper.map(categoryModel, Category.class));
    }

    @Override
    public List<CategoryModel> selectPage(CategoryModel categoryModel, Pageable pageable) {
        List<Category> categories = categoryRepo.selectPage(beanMapper.map(categoryModel, Category.class), pageable);
        return beanMapper.mapAsList(categories,CategoryModel.class);
    }

    @Override
    public List<CategoryModel> selectParent(Pageable pageable) {
        List<Category> categories = categoryRepo.selectParent(pageable);
        return beanMapper.mapAsList(categories,CategoryModel.class);
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


package com.hengsu.uliketu.core.service;

import com.hengsu.uliketu.core.model.CategoryModel;

public interface CategoryService{
	
	public int create(CategoryModel categoryModel);
	
	public int createSelective(CategoryModel categoryModel);
	
	public CategoryModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(CategoryModel categoryModel);
	
	public int updateByPrimaryKeySelective(CategoryModel categoryModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(CategoryModel categoryModel);
	
}

package com.hengsu.uliketu.mall.service;

import com.hengsu.uliketu.mall.model.CategoryModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService{
	
	public int create(CategoryModel categoryModel);
	
	public int createSelective(CategoryModel categoryModel);
	
	public CategoryModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(CategoryModel categoryModel);
	
	public int updateByPrimaryKeySelective(CategoryModel categoryModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public long selectCount(CategoryModel categoryModel);

	public List<CategoryModel> selectPage(CategoryModel categoryModel,Pageable pageable);

	public List<CategoryModel> selectParent(Pageable pageable);


	
}
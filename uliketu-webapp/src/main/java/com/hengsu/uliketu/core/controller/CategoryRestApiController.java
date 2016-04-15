package com.hengsu.uliketu.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.uliketu.core.service.CategoryService;
import com.hengsu.uliketu.core.model.CategoryModel;
import com.hengsu.uliketu.core.vo.CategoryVO;

@RestApiController
@RequestMapping("/uliketu")
public class CategoryRestApiController {

	private final Logger logger = LoggerFactory.getLogger(CategoryRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/core/category/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<CategoryVO>> getCategoryById(@PathVariable Long id){
		CategoryModel categoryModel = categoryService.findByPrimaryKey(id);
		CategoryVO categoryVO =beanMapper.map(categoryModel, CategoryVO.class);
		ResponseEnvelope<CategoryVO> responseEnv = new ResponseEnvelope<CategoryVO>(categoryVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/core/category", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createCategory(@RequestBody CategoryVO categoryVO){
		CategoryModel categoryModel = beanMapper.map(categoryVO, CategoryModel.class);
		Integer  result = categoryService.create(categoryModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/core/category/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteCategoryByPrimaryKey(@PathVariable Long id){
		Integer  result = categoryService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/core/category/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateCategoryByPrimaryKeySelective(@PathVariable Long id, @RequestBody CategoryVO categoryVO){
		CategoryModel categoryModel = beanMapper.map(categoryVO, CategoryModel.class);
		categoryModel.setId(id);
		Integer  result = categoryService.updateByPrimaryKeySelective(categoryModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}

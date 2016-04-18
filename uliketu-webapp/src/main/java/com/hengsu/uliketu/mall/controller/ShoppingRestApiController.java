package com.hengsu.uliketu.mall.controller;

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

import com.hengsu.uliketu.mall.service.ShoppingService;
import com.hengsu.uliketu.mall.model.ShoppingModel;
import com.hengsu.uliketu.mall.vo.ShoppingVO;

@RestApiController
@RequestMapping("/uliketu")
public class ShoppingRestApiController {

	private final Logger logger = LoggerFactory.getLogger(ShoppingRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private ShoppingService shoppingService;
	
	@RequestMapping(value = "/mall/shopping/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<ShoppingVO>> getShoppingById(@PathVariable Long id){
		ShoppingModel shoppingModel = shoppingService.findByPrimaryKey(id);
		ShoppingVO shoppingVO =beanMapper.map(shoppingModel, ShoppingVO.class);
		ResponseEnvelope<ShoppingVO> responseEnv = new ResponseEnvelope<ShoppingVO>(shoppingVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mall/shopping", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createShopping(@RequestBody ShoppingVO shoppingVO){
		ShoppingModel shoppingModel = beanMapper.map(shoppingVO, ShoppingModel.class);
		Integer  result = shoppingService.create(shoppingModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mall/shopping/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteShoppingByPrimaryKey(@PathVariable Long id){
		Integer  result = shoppingService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mall/shopping/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateShoppingByPrimaryKeySelective(@PathVariable Long id, @RequestBody ShoppingVO shoppingVO){
		ShoppingModel shoppingModel = beanMapper.map(shoppingVO, ShoppingModel.class);
		shoppingModel.setId(id);
		Integer  result = shoppingService.updateByPrimaryKeySelective(shoppingModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}

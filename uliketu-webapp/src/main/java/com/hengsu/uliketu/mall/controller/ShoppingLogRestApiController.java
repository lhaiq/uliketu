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

import com.hengsu.uliketu.mall.service.ShoppingLogService;
import com.hengsu.uliketu.mall.model.ShoppingLogModel;
import com.hengsu.uliketu.mall.vo.ShoppingLogVO;

@RestApiController
@RequestMapping("/uliketu")
public class ShoppingLogRestApiController {

	private final Logger logger = LoggerFactory.getLogger(ShoppingLogRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private ShoppingLogService shoppingLogService;
	
	@RequestMapping(value = "/mall/shoppingLog/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<ShoppingLogVO>> getShoppingLogById(@PathVariable Long id){
		ShoppingLogModel shoppingLogModel = shoppingLogService.findByPrimaryKey(id);
		ShoppingLogVO shoppingLogVO =beanMapper.map(shoppingLogModel, ShoppingLogVO.class);
		ResponseEnvelope<ShoppingLogVO> responseEnv = new ResponseEnvelope<ShoppingLogVO>(shoppingLogVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mall/shoppingLog", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createShoppingLog(@RequestBody ShoppingLogVO shoppingLogVO){
		ShoppingLogModel shoppingLogModel = beanMapper.map(shoppingLogVO, ShoppingLogModel.class);
		Integer  result = shoppingLogService.create(shoppingLogModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mall/shoppingLog/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteShoppingLogByPrimaryKey(@PathVariable Long id){
		Integer  result = shoppingLogService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mall/shoppingLog/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateShoppingLogByPrimaryKeySelective(@PathVariable Long id, @RequestBody ShoppingLogVO shoppingLogVO){
		ShoppingLogModel shoppingLogModel = beanMapper.map(shoppingLogVO, ShoppingLogModel.class);
		shoppingLogModel.setId(id);
		Integer  result = shoppingLogService.updateByPrimaryKeySelective(shoppingLogModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}

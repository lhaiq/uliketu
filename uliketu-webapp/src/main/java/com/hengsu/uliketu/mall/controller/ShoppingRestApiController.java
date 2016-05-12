package com.hengsu.uliketu.mall.controller;

import com.hengsu.uliketu.core.vo.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

	/**
	 * 购买商品
	 * @param id
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/mall/buy/shopping/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<String>> buyGoods(@PathVariable Long id,
																 @Value("#{request.getAttribute('userId')}") Long userId){
		shoppingService.buyGoods(id,userId);
		ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}

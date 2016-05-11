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

import com.hengsu.uliketu.mall.service.WinnerService;
import com.hengsu.uliketu.mall.model.WinnerModel;
import com.hengsu.uliketu.mall.vo.WinnerVO;

@RestApiController
@RequestMapping("/uliketu")
public class WinnerRestApiController {

	private final Logger logger = LoggerFactory.getLogger(WinnerRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private WinnerService winnerService;
	
	@RequestMapping(value = "/mall/winner/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<WinnerVO>> getWinnerById(@PathVariable Long id){
		WinnerModel winnerModel = winnerService.findByPrimaryKey(id);
		WinnerVO winnerVO =beanMapper.map(winnerModel, WinnerVO.class);
		ResponseEnvelope<WinnerVO> responseEnv = new ResponseEnvelope<WinnerVO>(winnerVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mall/winner", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createWinner(@RequestBody WinnerVO winnerVO){
		WinnerModel winnerModel = beanMapper.map(winnerVO, WinnerModel.class);
		Integer  result = winnerService.create(winnerModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

}

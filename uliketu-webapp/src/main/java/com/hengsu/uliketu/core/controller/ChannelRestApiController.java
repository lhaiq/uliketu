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

import com.hengsu.uliketu.core.service.ChannelService;
import com.hengsu.uliketu.core.model.ChannelModel;
import com.hengsu.uliketu.core.vo.ChannelVO;

@RestApiController
@RequestMapping("/uliketu")
public class ChannelRestApiController {

	private final Logger logger = LoggerFactory.getLogger(ChannelRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private ChannelService channelService;
	
	@RequestMapping(value = "/core/channel/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<ChannelVO>> getChannelById(@PathVariable Long id){
		ChannelModel channelModel = channelService.findByPrimaryKey(id);
		ChannelVO channelVO =beanMapper.map(channelModel, ChannelVO.class);
		ResponseEnvelope<ChannelVO> responseEnv = new ResponseEnvelope<ChannelVO>(channelVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/core/channel", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createChannel(@RequestBody ChannelVO channelVO){
		ChannelModel channelModel = beanMapper.map(channelVO, ChannelModel.class);
		Integer  result = channelService.create(channelModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/core/channel/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteChannelByPrimaryKey(@PathVariable Long id){
		Integer  result = channelService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/core/channel/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateChannelByPrimaryKeySelective(@PathVariable Long id, @RequestBody ChannelVO channelVO){
		ChannelModel channelModel = beanMapper.map(channelVO, ChannelModel.class);
		channelModel.setId(id);
		Integer  result = channelService.updateByPrimaryKeySelective(channelModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}

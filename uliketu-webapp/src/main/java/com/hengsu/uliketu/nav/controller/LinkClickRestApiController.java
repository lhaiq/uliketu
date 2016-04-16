package com.hengsu.uliketu.nav.controller;

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

import com.hengsu.uliketu.nav.service.LinkClickService;
import com.hengsu.uliketu.nav.model.LinkClickModel;
import com.hengsu.uliketu.nav.vo.LinkClickVO;

@RestApiController
@RequestMapping("/uliketu")
public class LinkClickRestApiController {

	private final Logger logger = LoggerFactory.getLogger(LinkClickRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private LinkClickService linkClickService;
	
	@RequestMapping(value = "/nav/linkClick/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<LinkClickVO>> getLinkClickById(@PathVariable Long id){
		LinkClickModel linkClickModel = linkClickService.findByPrimaryKey(id);
		LinkClickVO linkClickVO =beanMapper.map(linkClickModel, LinkClickVO.class);
		ResponseEnvelope<LinkClickVO> responseEnv = new ResponseEnvelope<LinkClickVO>(linkClickVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/nav/linkClick", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createLinkClick(@RequestBody LinkClickVO linkClickVO){
		LinkClickModel linkClickModel = beanMapper.map(linkClickVO, LinkClickModel.class);
		Integer  result = linkClickService.create(linkClickModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/nav/linkClick/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteLinkClickByPrimaryKey(@PathVariable Long id){
		Integer  result = linkClickService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/nav/linkClick/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateLinkClickByPrimaryKeySelective(@PathVariable Long id, @RequestBody LinkClickVO linkClickVO){
		LinkClickModel linkClickModel = beanMapper.map(linkClickVO, LinkClickModel.class);
		linkClickModel.setId(id);
		Integer  result = linkClickService.updateByPrimaryKeySelective(linkClickModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}

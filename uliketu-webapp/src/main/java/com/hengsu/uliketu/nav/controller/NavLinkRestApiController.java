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

import com.hengsu.uliketu.nav.service.NavLinkService;
import com.hengsu.uliketu.nav.model.NavLinkModel;
import com.hengsu.uliketu.nav.vo.NavLinkVO;

@RestApiController
@RequestMapping("/uliketu")
public class NavLinkRestApiController {

	private final Logger logger = LoggerFactory.getLogger(NavLinkRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private NavLinkService navLinkService;
	
	@RequestMapping(value = "/nav/navLink/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<NavLinkVO>> getNavLinkById(@PathVariable Long id){
		NavLinkModel navLinkModel = navLinkService.findByPrimaryKey(id);
		NavLinkVO navLinkVO =beanMapper.map(navLinkModel, NavLinkVO.class);
		ResponseEnvelope<NavLinkVO> responseEnv = new ResponseEnvelope<NavLinkVO>(navLinkVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/nav/navLink", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createNavLink(@RequestBody NavLinkVO navLinkVO){
		NavLinkModel navLinkModel = beanMapper.map(navLinkVO, NavLinkModel.class);
		Integer  result = navLinkService.create(navLinkModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/nav/navLink/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteNavLinkByPrimaryKey(@PathVariable Long id){
		Integer  result = navLinkService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/nav/navLink/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateNavLinkByPrimaryKeySelective(@PathVariable Long id, @RequestBody NavLinkVO navLinkVO){
		NavLinkModel navLinkModel = beanMapper.map(navLinkVO, NavLinkModel.class);
		navLinkModel.setId(id);
		Integer  result = navLinkService.updateByPrimaryKeySelective(navLinkModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}

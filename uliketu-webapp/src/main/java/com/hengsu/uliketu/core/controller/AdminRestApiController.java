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

import com.hengsu.uliketu.core.service.AdminService;
import com.hengsu.uliketu.core.model.AdminModel;
import com.hengsu.uliketu.core.vo.AdminVO;

@RestApiController
@RequestMapping("/uliketu")
public class AdminRestApiController {

	private final Logger logger = LoggerFactory.getLogger(AdminRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/core/admin/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<AdminVO>> getAdminById(@PathVariable Long id){
		AdminModel adminModel = adminService.findByPrimaryKey(id);
		AdminVO adminVO =beanMapper.map(adminModel, AdminVO.class);
		ResponseEnvelope<AdminVO> responseEnv = new ResponseEnvelope<AdminVO>(adminVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/core/admin", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createAdmin(@RequestBody AdminVO adminVO){
		AdminModel adminModel = beanMapper.map(adminVO, AdminModel.class);
		Integer  result = adminService.create(adminModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/core/admin/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteAdminByPrimaryKey(@PathVariable Long id){
		Integer  result = adminService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/core/admin/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateAdminByPrimaryKeySelective(@PathVariable Long id, @RequestBody AdminVO adminVO){
		AdminModel adminModel = beanMapper.map(adminVO, AdminModel.class);
		adminModel.setId(id);
		Integer  result = adminService.updateByPrimaryKeySelective(adminModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}

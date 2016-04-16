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

import com.hengsu.uliketu.nav.service.ColumnService;
import com.hengsu.uliketu.nav.model.ColumnModel;
import com.hengsu.uliketu.nav.vo.ColumnVO;

@RestApiController
@RequestMapping("/uliketu")
public class ColumnRestApiController {

	private final Logger logger = LoggerFactory.getLogger(ColumnRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private ColumnService columnService;
	
	@RequestMapping(value = "/nav/column/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<ColumnVO>> getColumnById(@PathVariable Long id){
		ColumnModel columnModel = columnService.findByPrimaryKey(id);
		ColumnVO columnVO =beanMapper.map(columnModel, ColumnVO.class);
		ResponseEnvelope<ColumnVO> responseEnv = new ResponseEnvelope<ColumnVO>(columnVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/nav/column", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createColumn(@RequestBody ColumnVO columnVO){
		ColumnModel columnModel = beanMapper.map(columnVO, ColumnModel.class);
		Integer  result = columnService.create(columnModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/nav/column/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteColumnByPrimaryKey(@PathVariable Long id){
		Integer  result = columnService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/nav/column/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateColumnByPrimaryKeySelective(@PathVariable Long id, @RequestBody ColumnVO columnVO){
		ColumnModel columnModel = beanMapper.map(columnVO, ColumnModel.class);
		columnModel.setId(id);
		Integer  result = columnService.updateByPrimaryKeySelective(columnModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}

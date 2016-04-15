package com.hengsu.uliketu.core.controller;

import com.hengsu.uliketu.core.vo.ReturnCode;
import org.apache.commons.lang3.StringUtils;
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

import com.hengsu.uliketu.core.service.ConfService;
import com.hengsu.uliketu.core.model.ConfModel;
import com.hengsu.uliketu.core.vo.ConfVO;

@RestApiController
@RequestMapping("/uliketu")
public class ConfRestApiController {

	private final Logger logger = LoggerFactory.getLogger(ConfRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private ConfService confService;

	/**
	 * 获取配置项
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/conf/{key}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<ConfVO>> getConfById(@PathVariable String key){
		ConfModel confModel = confService.findByPrimaryKey(key);
		ConfVO confVO =beanMapper.map(confModel, ConfVO.class);
		ResponseEnvelope<ConfVO> responseEnv = new ResponseEnvelope<>(confVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 修改配置文件
 	 * @param confVO
	 * @return
	 */
	@RequestMapping(value = "/conf", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<String>> updateConfByPrimaryKeySelective(@RequestBody ConfVO confVO){
		ConfModel confModel = beanMapper.map(confVO, ConfModel.class);
		confService.updateByPrimaryKeySelective(confModel);
		ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}

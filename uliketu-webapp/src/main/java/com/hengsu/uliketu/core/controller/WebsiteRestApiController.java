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

import com.hengsu.uliketu.core.service.WebsiteService;
import com.hengsu.uliketu.core.model.WebsiteModel;
import com.hengsu.uliketu.core.vo.WebsiteVO;

@RestApiController
@RequestMapping("/uliketu")
public class WebsiteRestApiController {

	private final Logger logger = LoggerFactory.getLogger(WebsiteRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private WebsiteService websiteService;

	/**
	 * 配置详细
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/website/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<WebsiteVO>> getWebsiteById(@PathVariable Long id){
		WebsiteModel websiteModel = websiteService.findByPrimaryKey(id);
		WebsiteVO websiteVO =beanMapper.map(websiteModel, WebsiteVO.class);
		ResponseEnvelope<WebsiteVO> responseEnv = new ResponseEnvelope<>(websiteVO,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 修改设置
	 * @param id
	 * @param websiteVO
	 * @return
	 */
	@RequestMapping(value = "/admin/website/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateWebsiteByPrimaryKeySelective(@PathVariable Long id,
																						@RequestBody WebsiteVO websiteVO){
		WebsiteModel websiteModel = beanMapper.map(websiteVO, WebsiteModel.class);
		websiteModel.setId(id);
		Integer  result = websiteService.updateByPrimaryKeySelective(websiteModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}

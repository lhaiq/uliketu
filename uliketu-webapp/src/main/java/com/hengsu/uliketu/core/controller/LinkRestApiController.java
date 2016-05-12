package com.hengsu.uliketu.core.controller;

import com.hengsu.uliketu.core.annotation.IgnoreAuth;
import com.hengsu.uliketu.core.annotation.Permission;
import com.hengsu.uliketu.core.model.AuthModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

import com.hengsu.uliketu.core.service.LinkService;
import com.hengsu.uliketu.core.model.LinkModel;
import com.hengsu.uliketu.core.vo.LinkVO;

import java.util.List;

@RestApiController
@RequestMapping("/uliketu")
public class LinkRestApiController {

	private final Logger logger = LoggerFactory.getLogger(LinkRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private LinkService linkService;

	/**
	 * 链接详细
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/link/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<LinkVO>> getLinkById(@PathVariable Integer id){
		LinkModel linkModel = linkService.findByPrimaryKey(id);
		LinkVO linkVO =beanMapper.map(linkModel, LinkVO.class);
		ResponseEnvelope<LinkVO> responseEnv = new ResponseEnvelope<>(linkVO,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 链接列表
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping(value = "/links", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<Page<LinkModel>>> listLink(Pageable pageable){
		LinkModel param = new LinkModel();
		List<LinkModel> linkModels = linkService.selectPage(param,pageable);
		long count = linkService.selectCount(param);
		Page<LinkModel> page = new PageImpl<>(linkModels,pageable,count);
		ResponseEnvelope<Page<LinkModel>> responseEnv = new ResponseEnvelope<>(page,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 添加链接
	 * @param linkVO
	 * @return
	 */
	@Permission(roles = {AuthModel.ROLE_ADMIN,AuthModel.ROLE_SUPER_ADMIN})
	@RequestMapping(value = "/admin/link", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createLink(@RequestBody LinkVO linkVO){
		LinkModel linkModel = beanMapper.map(linkVO, LinkModel.class);
		Integer  result = linkService.create(linkModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 删除链接
	 * @param id
	 * @return
	 */
	@Permission(roles = {AuthModel.ROLE_ADMIN,AuthModel.ROLE_SUPER_ADMIN})
	@RequestMapping(value = "/admin/link/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteLinkByPrimaryKey(@PathVariable Integer id){
		Integer  result = linkService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 更新链接
	 * @param id
	 * @param linkVO
	 * @return
	 */
	@Permission(roles = {AuthModel.ROLE_ADMIN,AuthModel.ROLE_SUPER_ADMIN})
	@RequestMapping(value = "/admin/link/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateLinkByPrimaryKeySelective(@PathVariable Integer id,
																					 @RequestBody LinkVO linkVO){
		LinkModel linkModel = beanMapper.map(linkVO, LinkModel.class);
		linkModel.setId(id);
		Integer  result = linkService.updateByPrimaryKeySelective(linkModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}

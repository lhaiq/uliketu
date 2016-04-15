package com.hengsu.uliketu.core.controller;

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

import com.hengsu.uliketu.core.service.AdvertisementService;
import com.hengsu.uliketu.core.model.AdvertisementModel;
import com.hengsu.uliketu.core.vo.AdvertisementVO;

import java.util.List;

@RestApiController
@RequestMapping("/uliketu")
public class AdvertisementRestApiController {

	private final Logger logger = LoggerFactory.getLogger(AdvertisementRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private AdvertisementService advertisementService;

	/**
	 * 广告详细
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/advertisement/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<AdvertisementVO>> getAdvertisementById(@PathVariable Integer id){
		AdvertisementModel advertisementModel = advertisementService.findByPrimaryKey(id);
		AdvertisementVO advertisementVO =beanMapper.map(advertisementModel, AdvertisementVO.class);
		ResponseEnvelope<AdvertisementVO> responseEnv = new ResponseEnvelope<AdvertisementVO>(advertisementVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 广告列表
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/admin/advertisements", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<Page<AdvertisementModel>>> listAdvertisement(Pageable pageable){
		AdvertisementModel param = new AdvertisementModel();
		List<AdvertisementModel> advertisementModels = advertisementService.selectPage(param,pageable);
		long count=advertisementService.selectCount(param);
		Page<AdvertisementModel> page = new PageImpl<>(advertisementModels,pageable,count);
		ResponseEnvelope<Page<AdvertisementModel>> responseEnv = new ResponseEnvelope<>(page);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 添加广告
	 * @param advertisementVO
	 * @return
	 */
	@RequestMapping(value = "/admin/advertisement", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createAdvertisement(@RequestBody AdvertisementVO advertisementVO){
		AdvertisementModel advertisementModel = beanMapper.map(advertisementVO, AdvertisementModel.class);
		Integer  result = advertisementService.create(advertisementModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 删除广告
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/advertisement/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteAdvertisementByPrimaryKey(@PathVariable Integer id){
		Integer  result = advertisementService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 修改广告
	 * @param id
	 * @param advertisementVO
	 * @return
	 */
	@RequestMapping(value = "/admin/advertisement/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateAdvertisementByPrimaryKeySelective(@PathVariable Integer id,
																							  @RequestBody AdvertisementVO advertisementVO){
		AdvertisementModel advertisementModel = beanMapper.map(advertisementVO, AdvertisementModel.class);
		advertisementModel.setId(id);
		Integer  result = advertisementService.updateByPrimaryKeySelective(advertisementModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}

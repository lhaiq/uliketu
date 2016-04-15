package com.hengsu.uliketu.core.controller;

import com.hengsu.uliketu.core.vo.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

import com.hengsu.uliketu.core.service.MessageService;
import com.hengsu.uliketu.core.model.MessageModel;
import com.hengsu.uliketu.core.vo.MessageVO;

import java.util.List;

@RestApiController
@RequestMapping("/uliketu")
public class MessageRestApiController {

	private final Logger logger = LoggerFactory.getLogger(MessageRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private MessageService messageService;

	/**
	 * 获取单条消息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<MessageVO>> getMessageById(@PathVariable Long id){
		MessageModel messageModel = messageService.findByPrimaryKey(id);
		MessageVO messageVO =beanMapper.map(messageModel, MessageVO.class);
		ResponseEnvelope<MessageVO> responseEnv = new ResponseEnvelope<MessageVO>(messageVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 获取某个用户的消息
	 * @param userId
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<Page<MessageModel>>> getMessages(@Value("#{request.getAttribute('userId')}") Long userId,
																   Pageable pageable){
		MessageModel param = new MessageModel();
		param.setUserId(userId);
		List<MessageModel> messageModes = messageService.selectPage(param,pageable);
		long count = messageService.selectCount(param);
		Page<MessageModel> page = new PageImpl<>(messageModes,pageable,count);
		ResponseEnvelope<Page<MessageModel>> responseEnv = new ResponseEnvelope<>(page);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 该消息已读
	 * @param userId
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/message/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<String>> readMessage(@Value("#{request.getAttribute('userId')}") Long userId,
																			@PathVariable Long id){messageService.readMessage(id, userId);
		messageService.readMessage(id,userId);
		ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
}

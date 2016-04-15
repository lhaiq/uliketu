package com.hengsu.uliketu.core.service.impl;

import static com.hengsu.uliketu.core.ErrorCode.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.core.entity.Message;
import com.hengsu.uliketu.core.repository.MessageRepository;
import com.hengsu.uliketu.core.model.MessageModel;
import com.hengsu.uliketu.core.service.MessageService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private MessageRepository messageRepo;

	@Transactional
	@Override
	public int create(MessageModel messageModel) {
		return messageRepo.insert(beanMapper.map(messageModel, Message.class));
	}

	@Transactional
	@Override
	public int createSelective(MessageModel messageModel) {
		return messageRepo.insertSelective(beanMapper.map(messageModel, Message.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return messageRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public MessageModel findByPrimaryKey(Long id) {
		Message message = messageRepo.selectByPrimaryKey(id);
		return beanMapper.map(message, MessageModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(MessageModel messageModel) {
		return messageRepo.selectCount(beanMapper.map(messageModel, Message.class));
	}

	@Override
	public List<MessageModel> selectPage(MessageModel messageModel, Pageable pageable) {
		Message message = beanMapper.map(messageModel,Message.class);
		List<Message> messages = messageRepo.selectPage(message,pageable);
		return beanMapper.mapAsList(messages,MessageModel.class);
	}

	@Transactional
	@Override
	public void addMessage(String content, Long userId) {
		MessageModel messageModel  = new MessageModel();
		messageModel.setCreateTime(new Date());
		messageModel.setContent(content);
		messageModel.setUserId(userId);
		createSelective(messageModel);
	}

	@Transactional
	@Override
	public void readMessage(Long id, Long userId) {

		//判断是否是自己的消息
		MessageModel messageModel = findByPrimaryKey(id);
		if(null==messageModel) throwBusinessException(MESSAGE_NOT_EXISTED);

		if(messageModel.getUserId()!=userId) throwBusinessException(IS_NOT_YOUR_MESSAGE/**/);

		//更新状态
		MessageModel param = new MessageModel();
		param.setId(id);
		param.setIsread(MessageModel.readed);
		updateByPrimaryKeySelective(param);

	}

	@Transactional
	@Override
	public int updateByPrimaryKey(MessageModel messageModel) {
		return messageRepo.updateByPrimaryKey(beanMapper.map(messageModel, Message.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(MessageModel messageModel) {
		return messageRepo.updateByPrimaryKeySelective(beanMapper.map(messageModel, Message.class));
	}

}

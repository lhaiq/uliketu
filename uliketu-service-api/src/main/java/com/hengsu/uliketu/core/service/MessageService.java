
package com.hengsu.uliketu.core.service;

import com.hengsu.uliketu.core.model.MessageModel;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface MessageService{
	
	public int create(MessageModel messageModel);
	
	public int createSelective(MessageModel messageModel);
	
	public MessageModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(MessageModel messageModel);
	
	public int updateByPrimaryKeySelective(MessageModel messageModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public long selectCount(MessageModel messageModel);

	public List<MessageModel> selectPage(MessageModel messageModel,Pageable pageable);

	public void addMessage(String content,Long userId);

	public void readMessage(Long id,Long userId);
	
}
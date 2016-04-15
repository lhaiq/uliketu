		package com.hengsu.uliketu.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hkntv.pylon.unit.BaseDbTest;
import com.hengsu.uliketu.core.service.MessageService;
import com.hengsu.uliketu.core.model.MessageModel;

import java.util.Date;

public class MessageServiceTest extends BaseDbTest{

	@Autowired
	private MessageService messageService;

	@Test
	public void testCreate() throws Exception {
		MessageModel messageModel = new MessageModel();
		messageModel.setId(531786743L);
		messageModel.setContent("a19edfe6-d272-473e-b3f3-a33b48135b0c");
		messageModel.setUserId(871685817L);
		messageModel.setCreateTime(new Date());
		messageModel.setIsread(36732324);
		Long pkValue = messageModel.getId();
		saveModel(messageModel);

		MessageModel findModel = messageService.findByPrimaryKey(pkValue);
		assertEquals(pkValue, findModel.getId());
		assertEquals(messageModel.getContent(), findModel.getContent());

		cleanModel(pkValue);
	}

	@Test
	public void testFindByPrimaryKey() throws Exception {
		MessageModel messageModel = new MessageModel();
		messageModel.setId(350521869L);
		messageModel.setContent("c224b00c-97b7-40db-b103-7c09df2dac61");
		messageModel.setUserId(494972455L);
		messageModel.setCreateTime(new Date());
		messageModel.setIsread(13444211);
		Long pkValue = messageModel.getId();
		saveModel(messageModel);

		MessageModel findModel = messageService.findByPrimaryKey(pkValue);
		assertEquals(pkValue, findModel.getId());
		assertEquals(messageModel.getContent(), findModel.getContent());

		cleanModel(pkValue);
	}

	@Test
	public void testUpdateByPrimaryKey() throws Exception {
		MessageModel messageModel = new MessageModel();
		messageModel.setId(976944161L);
		messageModel.setContent("b345faf0-b241-462f-9a9f-573c659568bd");
		messageModel.setUserId(439294856L);
		messageModel.setCreateTime(new Date());
		messageModel.setIsread(42428898);
		Long pkValue = messageModel.getId();
		saveModel(messageModel);

		//MessageModel updateModel = createModel();
		MessageModel updateModel = new MessageModel();
		updateModel.setId(653034821L);
		updateModel.setContent("44c823f4-7e48-416e-9b82-fef8b329c5a9");
		updateModel.setUserId(636379379L);
		updateModel.setCreateTime(new Date());
		updateModel.setIsread(41161093);
		
		updateModel.setId(pkValue);
		Integer updateResult = messageService.updateByPrimaryKey(updateModel);
		assertEquals(new Integer(1), updateResult);
		MessageModel findModel = messageService.findByPrimaryKey(pkValue);
		assertEquals(pkValue, findModel.getId());
		assertEquals(updateModel.getContent(), findModel.getContent());

		cleanModel(pkValue);
	}

	@Test
	public void testDeleteByPrimaryKey() throws Exception{
		MessageModel messageModel = new MessageModel();
		messageModel.setId(585713503L);
		messageModel.setContent("9976541b-5e98-4a37-9057-158c0701f359");
		messageModel.setUserId(176404676L);
		messageModel.setCreateTime(new Date());
		messageModel.setIsread(44715904);
		Long pkValue = messageModel.getId();
		saveModel(messageModel);
	
		Integer deleteResult = messageService.deleteByPrimaryKey(pkValue);
		assertEquals(new Integer(1), deleteResult);
		MessageModel findModel = messageService.findByPrimaryKey(pkValue);
		assertNull(findModel);
	}
	
	private void saveModel(MessageModel messageModel) throws Exception {
		Integer createResult = messageService.create(messageModel);
		assertEquals(createResult, new Integer(1));
	}

	private void cleanModel(Long pk) throws Exception {
		Integer deleteResult = messageService.deleteByPrimaryKey(pk);
		assertEquals(deleteResult, new Integer(1));
	}

	@SuppressWarnings("unused")
	private MessageModel createModel() {
		MessageModel messageModel = new MessageModel();
		messageModel.setId(133990575L);
		messageModel.setContent("1839dd15-b27b-411b-9d8b-a3f9447c4106");
		messageModel.setUserId(936269867L);
		messageModel.setCreateTime(new Date());
		messageModel.setIsread(42367389);
		return messageModel;
	}


}

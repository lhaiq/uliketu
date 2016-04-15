		package com.hengsu.uliketu.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hkntv.pylon.unit.BaseDbTest;
import com.hengsu.uliketu.core.service.ChannelService;
import com.hengsu.uliketu.core.model.ChannelModel;


public class ChannelServiceTest extends BaseDbTest{

	@Autowired
	private ChannelService channelService;

	@Test
	public void testCreate() throws Exception {
		ChannelModel channelModel = new ChannelModel();
		channelModel.setId(285917028L);
		channelModel.setName("a61e2cce-3778-4e3c-8a52-1d01dea9");
		channelModel.setOrder(41134230);
		channelModel.setParent(721454189L);
		Long pkValue = channelModel.getId();
		saveModel(channelModel);

		ChannelModel findModel = channelService.findByPrimaryKey(pkValue);
		assertEquals(pkValue, findModel.getId());
		assertEquals(channelModel.getName(), findModel.getName());

		cleanModel(pkValue);
	}

	@Test
	public void testFindByPrimaryKey() throws Exception {
		ChannelModel channelModel = new ChannelModel();
		channelModel.setId(224420394L);
		channelModel.setName("f6c5d7b7-fed0-47b0-9be7-aaf702d0");
		channelModel.setOrder(48302467);
		channelModel.setParent(332314794L);
		Long pkValue = channelModel.getId();
		saveModel(channelModel);

		ChannelModel findModel = channelService.findByPrimaryKey(pkValue);
		assertEquals(pkValue, findModel.getId());
		assertEquals(channelModel.getName(), findModel.getName());

		cleanModel(pkValue);
	}

	@Test
	public void testUpdateByPrimaryKey() throws Exception {
		ChannelModel channelModel = new ChannelModel();
		channelModel.setId(555252231L);
		channelModel.setName("da1a2d4b-b322-471a-8d8c-a01366b7");
		channelModel.setOrder(37593191);
		channelModel.setParent(903556973L);
		Long pkValue = channelModel.getId();
		saveModel(channelModel);

		//ChannelModel updateModel = createModel();
		ChannelModel updateModel = new ChannelModel();
		updateModel.setId(373137137L);
		updateModel.setName("24a886c5-3508-45bc-8ce3-02a539b6");
		updateModel.setOrder(61315883);
		updateModel.setParent(843866119L);
		
		updateModel.setId(pkValue);
		Integer updateResult = channelService.updateByPrimaryKey(updateModel);
		assertEquals(new Integer(1), updateResult);
		ChannelModel findModel = channelService.findByPrimaryKey(pkValue);
		assertEquals(pkValue, findModel.getId());
		assertEquals(updateModel.getName(), findModel.getName());

		cleanModel(pkValue);
	}

	@Test
	public void testDeleteByPrimaryKey() throws Exception{
		ChannelModel channelModel = new ChannelModel();
		channelModel.setId(276064081L);
		channelModel.setName("7318a45c-a6dc-43b5-8cba-950d6dd9");
		channelModel.setOrder(19780434);
		channelModel.setParent(532428602L);
		Long pkValue = channelModel.getId();
		saveModel(channelModel);
	
		Integer deleteResult = channelService.deleteByPrimaryKey(pkValue);
		assertEquals(new Integer(1), deleteResult);
		ChannelModel findModel = channelService.findByPrimaryKey(pkValue);
		assertNull(findModel);
	}
	
	private void saveModel(ChannelModel channelModel) throws Exception {
		Integer createResult = channelService.create(channelModel);
		assertEquals(createResult, new Integer(1));
	}

	private void cleanModel(Long pk) throws Exception {
		Integer deleteResult = channelService.deleteByPrimaryKey(pk);
		assertEquals(deleteResult, new Integer(1));
	}

	@SuppressWarnings("unused")
	private ChannelModel createModel() {
		ChannelModel channelModel = new ChannelModel();
		channelModel.setId(447999923L);
		channelModel.setName("7bc62346-7abd-49f4-a519-d395c519");
		channelModel.setOrder(95999987);
		channelModel.setParent(528022689L);
		return channelModel;
	}


}

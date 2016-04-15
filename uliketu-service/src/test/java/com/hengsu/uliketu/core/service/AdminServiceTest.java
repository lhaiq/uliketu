		package com.hengsu.uliketu.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hkntv.pylon.unit.BaseDbTest;
import com.hengsu.uliketu.core.service.AdminService;
import com.hengsu.uliketu.core.model.AdminModel;


public class AdminServiceTest extends BaseDbTest{

	@Autowired
	private AdminService adminService;

	@Test
	public void testCreate() throws Exception {
		AdminModel adminModel = new AdminModel();
		adminModel.setId(910573710L);
		adminModel.setName("56166f04-f901-4f35-828f-44ee64ff");
		adminModel.setPassword("9a511b79-6efc-4892-a216-a0867defcb68");
		adminModel.setRole(66573959);
		Long pkValue = adminModel.getId();
		saveModel(adminModel);

		AdminModel findModel = adminService.findByPrimaryKey(pkValue);
		assertEquals(pkValue, findModel.getId());
		assertEquals(adminModel.getName(), findModel.getName());

		cleanModel(pkValue);
	}

	@Test
	public void testFindByPrimaryKey() throws Exception {
		AdminModel adminModel = new AdminModel();
		adminModel.setId(534690725L);
		adminModel.setName("ffdf5a41-4ad7-4888-91f0-040c2c05");
		adminModel.setPassword("14bc1c44-f7d8-48eb-b1ff-8792c78af2ee");
		adminModel.setRole(62020015);
		Long pkValue = adminModel.getId();
		saveModel(adminModel);

		AdminModel findModel = adminService.findByPrimaryKey(pkValue);
		assertEquals(pkValue, findModel.getId());
		assertEquals(adminModel.getName(), findModel.getName());

		cleanModel(pkValue);
	}

	@Test
	public void testUpdateByPrimaryKey() throws Exception {
		AdminModel adminModel = new AdminModel();
		adminModel.setId(169255860L);
		adminModel.setName("2f25bc0d-694f-4d5e-baad-883bd05d");
		adminModel.setPassword("03c4dd8a-7c0a-4ba2-a242-9b3c87992c20");
		adminModel.setRole(34356605);
		Long pkValue = adminModel.getId();
		saveModel(adminModel);

		//AdminModel updateModel = createModel();
		AdminModel updateModel = new AdminModel();
		updateModel.setId(875477670L);
		updateModel.setName("a33a92da-6e78-4c62-9556-5c5b1202");
		updateModel.setPassword("40639744-a0a6-49e6-997b-15405a1dd089");
		updateModel.setRole(69637647);
		
		updateModel.setId(pkValue);
		Integer updateResult = adminService.updateByPrimaryKey(updateModel);
		assertEquals(new Integer(1), updateResult);
		AdminModel findModel = adminService.findByPrimaryKey(pkValue);
		assertEquals(pkValue, findModel.getId());
		assertEquals(updateModel.getName(), findModel.getName());

		cleanModel(pkValue);
	}

	@Test
	public void testDeleteByPrimaryKey() throws Exception{
		AdminModel adminModel = new AdminModel();
		adminModel.setId(759054450L);
		adminModel.setName("3cedfc65-c1a0-4d03-9bd1-d176d0f4");
		adminModel.setPassword("298241fa-00d9-4c70-bdb5-f53b60799677");
		adminModel.setRole(15968649);
		Long pkValue = adminModel.getId();
		saveModel(adminModel);
	
		Integer deleteResult = adminService.deleteByPrimaryKey(pkValue);
		assertEquals(new Integer(1), deleteResult);
		AdminModel findModel = adminService.findByPrimaryKey(pkValue);
		assertNull(findModel);
	}
	
	private void saveModel(AdminModel adminModel) throws Exception {
		Integer createResult = adminService.create(adminModel);
		assertEquals(createResult, new Integer(1));
	}

	private void cleanModel(Long pk) throws Exception {
		Integer deleteResult = adminService.deleteByPrimaryKey(pk);
		assertEquals(deleteResult, new Integer(1));
	}

	@SuppressWarnings("unused")
	private AdminModel createModel() {
		AdminModel adminModel = new AdminModel();
		adminModel.setId(476432045L);
		adminModel.setName("7bf7a067-8c25-4907-a241-023a9636");
		adminModel.setPassword("2fa9280b-9f2b-4684-ba87-7eb7a6ebf4e6");
		adminModel.setRole(87908143);
		return adminModel;
	}


}

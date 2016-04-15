		package com.hengsu.uliketu.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hkntv.pylon.unit.BaseDbTest;
import com.hengsu.uliketu.core.service.UserService;
import com.hengsu.uliketu.core.model.UserModel;

import java.util.Date;

public class UserServiceTest extends BaseDbTest{

	@Autowired
	private UserService userService;

	@Test
	public void testCreate() throws Exception {
		UserModel userModel = new UserModel();
		userModel.setId(370397333L);
		userModel.setUsername("3b0028d9-a0ee-4e13-87bb-c7ebc344");
		userModel.setPassword("78c5397a-9f73-4d2a-938b-5f2c71c800d8");
		userModel.setMail("d5a673ae-7fd3-4c3c-ba47-a7b6087969cb");
		userModel.setPhone("4889bbb7-fb6b-4cbc-9c20-6948147b14c3");
		userModel.setAvatar("1f7c7395-9794-43d9-8e0c-7287fca0a726");
		userModel.setBalance(0.9605338159173108);
		userModel.setIdnumber("cdb80e19-9cf2-441a-ad8a-9899a3d5");
		userModel.setIdphoto("252345cf-1470-4f19-9ccd-6229fe9fcdd4");
		userModel.setBlackStatus(32599989);
		userModel.setCertifie(56824738);
		userModel.setBaifubaoAccount("6336e3f2-e9e3-4d15-b97a-f4fd9e8e2a77");
		userModel.setRegisterTime(new Date());
		Long pkValue = userModel.getId();
		saveModel(userModel);

		UserModel findModel = userService.findByPrimaryKey(pkValue);
		assertEquals(pkValue, findModel.getId());
		assertEquals(userModel.getUsername(), findModel.getUsername());

		cleanModel(pkValue);
	}

	@Test
	public void testFindByPrimaryKey() throws Exception {
		UserModel userModel = new UserModel();
		userModel.setId(940211097L);
		userModel.setUsername("de1fec3e-2bd2-4e43-9d62-e1f80f5c");
		userModel.setPassword("3fa484d0-6957-4af9-ae02-7274289b5fc4");
		userModel.setMail("16f5a40d-5385-4b88-b7c9-56398a3d1897");
		userModel.setPhone("0abb018a-e24f-4521-bbe6-894698c2c6fd");
		userModel.setAvatar("8b2a39d7-bafa-4b2b-adc8-a536e0bd0c30");
		userModel.setBalance(0.3400302552460237);
		userModel.setIdnumber("38235e1d-c32d-4839-99e2-509e29e5");
		userModel.setIdphoto("40fcfa9d-8b8a-4ca6-8205-86d6595ac209");
		userModel.setBlackStatus(20157107);
		userModel.setCertifie(45038164);
		userModel.setBaifubaoAccount("55e2783f-0fc5-424a-9374-93cd42d51699");
		userModel.setRegisterTime(new Date());
		Long pkValue = userModel.getId();
		saveModel(userModel);

		UserModel findModel = userService.findByPrimaryKey(pkValue);
		assertEquals(pkValue, findModel.getId());
		assertEquals(userModel.getUsername(), findModel.getUsername());

		cleanModel(pkValue);
	}

	@Test
	public void testUpdateByPrimaryKey() throws Exception {
		UserModel userModel = new UserModel();
		userModel.setId(493679107L);
		userModel.setUsername("cc782516-50bf-4327-9c04-97aabc6d");
		userModel.setPassword("46e0f63c-df9a-49ed-9de1-25511b45aede");
		userModel.setMail("ca4adc60-da66-41b6-9033-98a0996f5bd1");
		userModel.setPhone("78f67031-3989-4299-bb94-e44ba32a127d");
		userModel.setAvatar("fd95a928-ba13-4f03-8edd-cf1025d818c6");
		userModel.setBalance(0.49643098504845673);
		userModel.setIdnumber("1b58a137-4af9-42d6-97e4-4cbe9c66");
		userModel.setIdphoto("42cf635d-83ad-44a6-a1c7-436db36d6a70");
		userModel.setBlackStatus(78146904);
		userModel.setCertifie(41267390);
		userModel.setBaifubaoAccount("4ee4f349-866c-4ed8-8fd4-4ccb2fb3ac38");
		userModel.setRegisterTime(new Date());
		Long pkValue = userModel.getId();
		saveModel(userModel);

		//UserModel updateModel = createModel();
		UserModel updateModel = new UserModel();
		updateModel.setId(278650254L);
		updateModel.setUsername("43b79d44-4199-4c52-8483-ba1814fa");
		updateModel.setPassword("696c9515-681d-4f90-8425-878f3d2f3deb");
		updateModel.setMail("99a2ee2e-6f4c-4a44-8a91-358c310b1d33");
		updateModel.setPhone("e52841cb-3abe-453d-9097-05ce48ace358");
		updateModel.setAvatar("7e2ef07d-e69a-4210-87e1-547da7b41713");
		updateModel.setBalance(0.6696780695807214);
		updateModel.setIdnumber("9a16c859-02a6-47d1-a4f7-8f698c9e");
		updateModel.setIdphoto("e97be622-da2b-49bf-93cb-e32bcc91b48f");
		updateModel.setBlackStatus(64805555);
		updateModel.setCertifie(57746930);
		updateModel.setBaifubaoAccount("f4cc4afd-b313-4557-b8fb-5db88b41a948");
		updateModel.setRegisterTime(new Date());
		
		updateModel.setId(pkValue);
		Integer updateResult = userService.updateByPrimaryKey(updateModel);
		assertEquals(new Integer(1), updateResult);
		UserModel findModel = userService.findByPrimaryKey(pkValue);
		assertEquals(pkValue, findModel.getId());
		assertEquals(updateModel.getUsername(), findModel.getUsername());

		cleanModel(pkValue);
	}

	@Test
	public void testDeleteByPrimaryKey() throws Exception{
		UserModel userModel = new UserModel();
		userModel.setId(617794567L);
		userModel.setUsername("403d4224-3bf5-42fd-a511-c7ff2aa5");
		userModel.setPassword("abd3bbc6-58a6-4a2e-805c-fa2418e2e9fa");
		userModel.setMail("312c4b1d-6cd5-46df-af99-01cbbb9cedb3");
		userModel.setPhone("3adec41d-a620-4439-a3c8-efdafdf67574");
		userModel.setAvatar("00a58d90-b0a8-4fc1-93f4-cf7858cc95c3");
		userModel.setBalance(0.28025908182814374);
		userModel.setIdnumber("cf3aaedd-524c-4a6c-a1a3-f5d8763d");
		userModel.setIdphoto("628e77d3-883b-4fb9-840f-039051582717");
		userModel.setBlackStatus(24950077);
		userModel.setCertifie(25446619);
		userModel.setBaifubaoAccount("ca7b3778-aa19-4e1f-8e44-052764ebcf20");
		userModel.setRegisterTime(new Date());
		Long pkValue = userModel.getId();
		saveModel(userModel);
	
		Integer deleteResult = userService.deleteByPrimaryKey(pkValue);
		assertEquals(new Integer(1), deleteResult);
		UserModel findModel = userService.findByPrimaryKey(pkValue);
		assertNull(findModel);
	}
	
	private void saveModel(UserModel userModel) throws Exception {
		Integer createResult = userService.create(userModel);
		assertEquals(createResult, new Integer(1));
	}

	private void cleanModel(Long pk) throws Exception {
		Integer deleteResult = userService.deleteByPrimaryKey(pk);
		assertEquals(deleteResult, new Integer(1));
	}

	@SuppressWarnings("unused")
	private UserModel createModel() {
		UserModel userModel = new UserModel();
		userModel.setId(857410155L);
		userModel.setUsername("294bb32a-ba8b-46bf-862a-d30b87ce");
		userModel.setPassword("145429bf-d1ef-4217-95d0-752623592f4b");
		userModel.setMail("30ceb09a-9173-46df-a298-3c9dcc4dc13d");
		userModel.setPhone("2a3207c2-431d-4e88-ac4b-ce452064679b");
		userModel.setAvatar("10a56de2-1ae3-411b-8f16-e8f3a0abecd8");
		userModel.setBalance(0.06894217090708754);
		userModel.setIdnumber("8d3819bf-b54c-4652-8dc1-ba337df4");
		userModel.setIdphoto("5bf18985-90dd-456c-be26-8b50b54b1d90");
		userModel.setBlackStatus(85552409);
		userModel.setCertifie(55065600);
		userModel.setBaifubaoAccount("9f5ec433-2d34-443f-9381-76ed14fcd89e");
		userModel.setRegisterTime(new Date());
		return userModel;
	}


}

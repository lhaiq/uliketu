		package com.hengsu.uliketu.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hkntv.pylon.unit.BaseDbTest;
import com.hengsu.uliketu.core.service.CategoryService;
import com.hengsu.uliketu.core.model.CategoryModel;


public class CategoryServiceTest extends BaseDbTest{

	@Autowired
	private CategoryService categoryService;

	@Test
	public void testCreate() throws Exception {
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setId(374650678L);
		categoryModel.setName("5ac6e978-8110-4a09-a5e7-3ec06ee94e9d");
		categoryModel.setDescription("be9a0a5a-6c68-4a3f-a30d-4527c9efb9cb");
		categoryModel.setParentId(822971092L);
		Long pkValue = categoryModel.getId();
		saveModel(categoryModel);

		CategoryModel findModel = categoryService.findByPrimaryKey(pkValue);
		assertEquals(pkValue, findModel.getId());
		assertEquals(categoryModel.getName(), findModel.getName());

		cleanModel(pkValue);
	}

	@Test
	public void testFindByPrimaryKey() throws Exception {
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setId(772867050L);
		categoryModel.setName("540230ff-5af5-4ebf-933a-60be6180b603");
		categoryModel.setDescription("45fbbf87-f326-46b0-b728-99cb64c7657e");
		categoryModel.setParentId(212532351L);
		Long pkValue = categoryModel.getId();
		saveModel(categoryModel);

		CategoryModel findModel = categoryService.findByPrimaryKey(pkValue);
		assertEquals(pkValue, findModel.getId());
		assertEquals(categoryModel.getName(), findModel.getName());

		cleanModel(pkValue);
	}

	@Test
	public void testUpdateByPrimaryKey() throws Exception {
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setId(482607414L);
		categoryModel.setName("e8ad75b9-d4c1-4042-a23e-e2ae78965161");
		categoryModel.setDescription("8c545edd-923b-4aa6-8226-f81c646e487b");
		categoryModel.setParentId(921580960L);
		Long pkValue = categoryModel.getId();
		saveModel(categoryModel);

		//CategoryModel updateModel = createModel();
		CategoryModel updateModel = new CategoryModel();
		updateModel.setId(971877039L);
		updateModel.setName("a0c637eb-fd45-4d36-85d5-b2440efe8872");
		updateModel.setDescription("c4dfc331-cc46-4db8-874f-914a699f90d5");
		updateModel.setParentId(535237879L);
		
		updateModel.setId(pkValue);
		Integer updateResult = categoryService.updateByPrimaryKey(updateModel);
		assertEquals(new Integer(1), updateResult);
		CategoryModel findModel = categoryService.findByPrimaryKey(pkValue);
		assertEquals(pkValue, findModel.getId());
		assertEquals(updateModel.getName(), findModel.getName());

		cleanModel(pkValue);
	}

	@Test
	public void testDeleteByPrimaryKey() throws Exception{
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setId(294576274L);
		categoryModel.setName("225d5e27-f61f-4943-b16f-c495b57bd534");
		categoryModel.setDescription("f3082702-ed56-4fa9-af65-fe2c090a825c");
		categoryModel.setParentId(592273628L);
		Long pkValue = categoryModel.getId();
		saveModel(categoryModel);
	
		Integer deleteResult = categoryService.deleteByPrimaryKey(pkValue);
		assertEquals(new Integer(1), deleteResult);
		CategoryModel findModel = categoryService.findByPrimaryKey(pkValue);
		assertNull(findModel);
	}
	
	private void saveModel(CategoryModel categoryModel) throws Exception {
		Integer createResult = categoryService.create(categoryModel);
		assertEquals(createResult, new Integer(1));
	}

	private void cleanModel(Long pk) throws Exception {
		Integer deleteResult = categoryService.deleteByPrimaryKey(pk);
		assertEquals(deleteResult, new Integer(1));
	}

	@SuppressWarnings("unused")
	private CategoryModel createModel() {
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setId(524780285L);
		categoryModel.setName("77c80d0b-a7dd-4c23-b367-36e611c46c07");
		categoryModel.setDescription("5421d0b1-e921-4dee-ab24-6803d8b76bef");
		categoryModel.setParentId(249706470L);
		return categoryModel;
	}


}

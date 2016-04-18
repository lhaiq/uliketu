package com.hengsu.uliketu.mall.controller;

import com.alibaba.fastjson.JSON;
import com.hengsu.uliketu.mall.vo.CategoryTreeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
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

import com.hengsu.uliketu.mall.service.CategoryService;
import com.hengsu.uliketu.mall.model.CategoryModel;
import com.hengsu.uliketu.mall.vo.CategoryVO;

import java.util.List;

@RestApiController
@RequestMapping("/uliketu")
public class CategoryRestApiController {

    private final Logger logger = LoggerFactory.getLogger(CategoryRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CategoryService categoryService;

    /**
     * 商品类型详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/mall/category/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<CategoryVO>> getCategoryById(@PathVariable Long id) {
        CategoryModel categoryModel = categoryService.findByPrimaryKey(id);
        CategoryVO categoryVO = beanMapper.map(categoryModel, CategoryVO.class);
        ResponseEnvelope<CategoryVO> responseEnv = new ResponseEnvelope<>(categoryVO, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 商品类型树
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/mall/category/tree", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<List<CategoryTreeVO>>> tree(Pageable pageable) {
        List<CategoryModel> categoryModels = categoryService.selectParent(pageable);
        List<CategoryTreeVO> categoryTreeVOs = beanMapper.mapAsList(categoryModels, CategoryTreeVO.class);
        for (CategoryTreeVO categoryTreeVO : categoryTreeVOs) {
            CategoryModel param = new CategoryModel();
            param.setParentId(categoryTreeVO.getId());
            List<CategoryModel> children = categoryService.selectPage(param, pageable);
            categoryTreeVO.setChildren(beanMapper.mapAsList(children, CategoryTreeVO.class));
        }
        ResponseEnvelope<List<CategoryTreeVO>> responseEnv = new ResponseEnvelope<>(categoryTreeVOs, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 添加商品类型
     *
     * @param categoryVO
     * @return
     */
    @RequestMapping(value = "/mall/category", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> createCategory(@RequestBody CategoryVO categoryVO) {
        CategoryModel categoryModel = beanMapper.map(categoryVO, CategoryModel.class);
        Integer result = categoryService.createSelective(categoryModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 删除商品类型
     * @param id
     * @return
     */
    @RequestMapping(value = "/mall/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Integer>> deleteCategoryByPrimaryKey(@PathVariable Long id) {
        Integer result = categoryService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 更新商品类型
     * @param id
     * @param categoryVO
     * @return
     */
    @RequestMapping(value = "/mall/category/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<Integer>> updateCategoryByPrimaryKeySelective(@PathVariable Long id, @RequestBody CategoryVO categoryVO) {
        CategoryModel categoryModel = beanMapper.map(categoryVO, CategoryModel.class);
        categoryModel.setId(id);
        Integer result = categoryService.updateByPrimaryKeySelective(categoryModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}

package com.hengsu.uliketu.mall.controller;

import com.hengsu.uliketu.core.annotation.IgnoreAuth;
import com.hengsu.uliketu.core.annotation.Permission;
import com.hengsu.uliketu.core.model.AuthModel;
import com.hengsu.uliketu.core.vo.ReturnCode;
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

import com.hengsu.uliketu.mall.service.GoodsService;
import com.hengsu.uliketu.mall.model.GoodsModel;
import com.hengsu.uliketu.mall.vo.GoodsVO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestApiController
@RequestMapping("/uliketu")
public class GoodsRestApiController {

    private final Logger logger = LoggerFactory.getLogger(GoodsRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private GoodsService goodsService;

    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/mall/goods/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<GoodsVO>> getGoodsById(@PathVariable Long id) {
        GoodsModel goodsModel = goodsService.findByPrimaryKey(id);
        GoodsVO goodsVO = beanMapper.map(goodsModel, GoodsVO.class);
        ResponseEnvelope<GoodsVO> responseEnv = new ResponseEnvelope<>(goodsVO, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 商品列表
     *
     * @param categoryId
     * @param pageable
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/mall/{categoryId}/goods", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<GoodsModel>>> listGoods(@PathVariable Long categoryId,
                                                                        Pageable pageable) {
        GoodsModel param = new GoodsModel();
        param.setCategoryId(categoryId);
        List<GoodsModel> goodsModels = goodsService.selectPage(param, pageable);
        long count = goodsService.selectCount(param);
        Page<GoodsModel> page = new PageImpl<>(goodsModels, pageable, count);
        ResponseEnvelope<Page<GoodsModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 可抢购的商品
     *
     * @param categoryIds
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/mall/shoppings", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Map>> listShopping(@RequestBody List<Long> categoryIds) {
        Map map = new HashMap<>();
        for (Long categoryId : categoryIds) {
            map.put(categoryId, goodsService.selectShopping(categoryId));
        }
        ResponseEnvelope<Map> responseEnv = new ResponseEnvelope<>(map, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 添加商品
     *
     * @param goodsVO
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/mall/goods", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> createGoods(@RequestBody GoodsVO goodsVO) {
        GoodsModel goodsModel = beanMapper.map(goodsVO, GoodsModel.class);
        goodsModel.setAddTime(new Date());
        Integer result = goodsService.createSelective(goodsModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 保存并上架
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/mall/saveAndShelve/goods", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<String>> saveAndShelve(@RequestBody GoodsVO goodsVO) {
        GoodsModel goodsModel = beanMapper.map(goodsVO, GoodsModel.class);
        goodsModel.setAddTime(new Date());
        goodsService.saveAndShelve(goodsModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 上架
     *
     * @param id
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/mall/shelve/goods/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> shelve(@PathVariable Long id) {
        goodsService.shelve(id);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 下架
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/mall/unShelve/goods/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> unShelve(@PathVariable Long id) {
        goodsService.unShelve(id);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 预下架
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/mall/preUnShelve/goods/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> preUnShelve(@PathVariable Long id) {
        goodsService.preUnShelve(id);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/mall/goods/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Integer>> deleteGoodsByPrimaryKey(@PathVariable Long id) {
        Integer result = goodsService.deleteGoods(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 修改商品
     *
     * @param id
     * @param goodsVO
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/mall/goods/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<String>> updateGoodsByPrimaryKeySelective(@PathVariable Long id,
                                                                                     @RequestBody GoodsVO goodsVO) {
        GoodsModel goodsModel = beanMapper.map(goodsVO, GoodsModel.class);
        goodsModel.setId(id);
        goodsService.updateGoods(goodsModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


}

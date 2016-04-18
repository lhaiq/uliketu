package com.hengsu.uliketu.mall.controller;

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
import java.util.List;

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
    @RequestMapping(value = "/mall/goods/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<GoodsVO>> getGoodsById(@PathVariable Long id) {
        GoodsModel goodsModel = goodsService.findByPrimaryKey(id);
        GoodsVO goodsVO = beanMapper.map(goodsModel, GoodsVO.class);
        ResponseEnvelope<GoodsVO> responseEnv = new ResponseEnvelope<>(goodsVO, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 商品列表
     * @param categoryId
     * @param pageable
     * @return
     */
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
     * 添加商品
     *
     * @param goodsVO
     * @return
     */
    @RequestMapping(value = "/mall/goods", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> createGoods(@RequestBody GoodsVO goodsVO) {
        GoodsModel goodsModel = beanMapper.map(goodsVO, GoodsModel.class);
        goodsModel.setAddTime(new Date());
        Integer result = goodsService.createSelective(goodsModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/mall/goods/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Integer>> deleteGoodsByPrimaryKey(@PathVariable Long id) {
        Integer result = goodsService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 修改商品
     *
     * @param id
     * @param goodsVO
     * @return
     */
    @RequestMapping(value = "/mall/goods/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<Integer>> updateGoodsByPrimaryKeySelective(@PathVariable Long id,
                                                                                      @RequestBody GoodsVO goodsVO) {
        GoodsModel goodsModel = beanMapper.map(goodsVO, GoodsModel.class);
        goodsModel.setId(id);
        Integer result = goodsService.updateByPrimaryKeySelective(goodsModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}

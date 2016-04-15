package com.hengsu.uliketu.core.controller;

import com.hengsu.uliketu.core.vo.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.uliketu.core.service.CashService;
import com.hengsu.uliketu.core.model.CashModel;
import com.hengsu.uliketu.core.vo.CashVO;

import java.util.List;

@RestApiController
@RequestMapping("/uliketu")
public class CashRestApiController {

    private final Logger logger = LoggerFactory.getLogger(CashRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CashService cashService;

    /**
     * 查询单个提现
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/cash/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<CashVO>> getCashById(@PathVariable Long id) {
        CashModel cashModel = cashService.findByPrimaryKey(id);
        CashVO cashVO = beanMapper.map(cashModel, CashVO.class);
        ResponseEnvelope<CashVO> responseEnv = new ResponseEnvelope<CashVO>(cashVO);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 我的提现记录
     *
     * @param userId
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/cashs", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<CashModel>>> getCashs(@Value("#{request.getAttribute('userId')}") Long userId,
                                                                      Pageable pageable) {
        CashModel param = new CashModel();
        param.setUserid(userId);
        List<CashModel> cashModels = cashService.selectPage(param, pageable);
        long count = cashService.selectCount(param);
        Page<CashModel> page = new PageImpl<>(cashModels, pageable, count);
        ResponseEnvelope<Page<CashModel>> responseEnv = new ResponseEnvelope<>(page);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 提交提现申请
     *
     * @param cashVO
     * @return
     */
    @RequestMapping(value = "/cash", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<String>> addCash(@RequestBody CashVO cashVO) {
        CashModel cashModel = beanMapper.map(cashVO, CashModel.class);
        cashService.addCash(cashModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 根据状态查提现列表
     *
     * @param status
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/admin/cashs", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<CashModel>>> getCashsByStatus(@RequestParam int status,
                                                                              Pageable pageable) {
        CashModel param = new CashModel();
        param.setStatus(status);
        List<CashModel> cashModels = cashService.selectPage(param, pageable);
        long count = cashService.selectCount(param);
        Page<CashModel> page = new PageImpl<>(cashModels, pageable, count);
        ResponseEnvelope<Page<CashModel>> responseEnv = new ResponseEnvelope<>(page);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 同意提现申请
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/agreecash/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<String>> agreeCash(@PathVariable Long id) {
        cashService.agreeCash(id);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 拒绝提现申请
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/refusecash/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<String>> refuseCash(@PathVariable Long id) {
        cashService.refuseCash(id);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}

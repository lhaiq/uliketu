package com.hengsu.uliketu.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

import com.hengsu.uliketu.core.service.StatementService;
import com.hengsu.uliketu.core.model.StatementModel;
import com.hengsu.uliketu.core.vo.StatementVO;

import java.util.List;

@RestApiController
@RequestMapping("/uliketu")
public class StatementRestApiController {

    private final Logger logger = LoggerFactory.getLogger(StatementRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private StatementService statementService;


    /**
     * 个人流水详情
     *
     * @param userId
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/statements", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<StatementModel>>> listStatements(@Value("#{request.getAttribute('userId')}") Long userId,
                                                                                 Pageable pageable) {
        StatementModel param = new StatementModel();
        List<StatementModel> statementModels = statementService.selectPage(param, pageable);
        long count = statementService.selectCount(param);
        Page<StatementModel> page = new PageImpl<>(statementModels, pageable, count);
        ResponseEnvelope<Page<StatementModel>> responseEnv = new ResponseEnvelope<>(page);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


}

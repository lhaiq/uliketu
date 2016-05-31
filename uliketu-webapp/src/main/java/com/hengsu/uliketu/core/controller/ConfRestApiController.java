package com.hengsu.uliketu.core.controller;

import com.hengsu.uliketu.core.annotation.Permission;
import com.hengsu.uliketu.core.model.AuthModel;
import com.hengsu.uliketu.core.vo.ReturnCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.hengsu.uliketu.core.service.ConfService;
import com.hengsu.uliketu.core.model.ConfModel;
import com.hengsu.uliketu.core.vo.ConfVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestApiController
@RequestMapping("/uliketu")
public class ConfRestApiController {

    private final Logger logger = LoggerFactory.getLogger(ConfRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private ConfService confService;

    /**
     * 获取配置项
     *
     * @param keys
     * @return
     */
    @RequestMapping(value = "/conf", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Map<String, String>>> getConfs(@RequestBody List<String> keys) {
        Map<String, String> map = new HashMap<>();
        for (String key : keys) {
            map.put(key, confService.findByPrimaryKey(key).getConfValue());
        }
        ResponseEnvelope<Map<String, String>> responseEnv = new ResponseEnvelope<>(map, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 修改配置文件
     *
     * @param maps
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/conf", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<String>> updateConfByPrimaryKey(@RequestBody Map<String,String> maps) {
        for(Map.Entry<String,String> entry:maps.entrySet()){
            ConfModel confModel = new ConfModel();
            confModel.setConfKey(entry.getKey());
            confModel.setConfValue(entry.getValue());
            confService.updateByPrimaryKeySelective(confModel);
        }
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}

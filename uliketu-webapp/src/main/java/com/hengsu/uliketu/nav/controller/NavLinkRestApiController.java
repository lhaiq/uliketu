package com.hengsu.uliketu.nav.controller;

import com.hengsu.uliketu.core.annotation.IgnoreAuth;
import com.hengsu.uliketu.core.annotation.Permission;
import com.hengsu.uliketu.core.model.AuthModel;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.uliketu.nav.service.NavLinkService;
import com.hengsu.uliketu.nav.model.NavLinkModel;
import com.hengsu.uliketu.nav.vo.NavLinkVO;

import java.util.Date;
import java.util.List;

@RestApiController
@RequestMapping("/uliketu")
public class NavLinkRestApiController {

    private final Logger logger = LoggerFactory.getLogger(NavLinkRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private NavLinkService navLinkService;


    /**
     * 点击链接
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/nav/clickink/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> clickNavLink(@PathVariable Long id,
                                                                 @Value("#{request.getAttribute('userId')}") Long userId) {
        navLinkService.clickLink(id, userId);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 导航链接详情
     *
     * @param id
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/nav/navLink/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<NavLinkModel>> getNavLinkById(@PathVariable Long id) {
        NavLinkModel navLinkModel = navLinkService.findByPrimaryKey(id);
        ResponseEnvelope<NavLinkModel> responseEnv = new ResponseEnvelope<>(navLinkModel, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 导航链接列表
     *
     * @param columnId
     * @param pageable
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/nav/{columnId}/navLinks", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<NavLinkModel>>> listNavLinks(@PathVariable Long columnId,
                                                                             Pageable pageable) {
        NavLinkModel param = new NavLinkModel();
        param.setColumnId(columnId);
        List<NavLinkModel> navLinkModes = navLinkService.selectPage(param, pageable);
        long count = navLinkService.selectCount(param);
        Page<NavLinkModel> page = new PageImpl<>(navLinkModes, pageable, count);
        ResponseEnvelope<Page<NavLinkModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 添加导航链接
     *
     * @param navLinkVO
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/nav/navLink", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> createNavLink(@RequestBody NavLinkVO navLinkVO) {
        NavLinkModel navLinkModel = beanMapper.map(navLinkVO, NavLinkModel.class);
        navLinkModel.setAddTime(new Date());
        Integer result = navLinkService.createSelective(navLinkModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 删除导航链接
     *
     * @param id
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/nav/navLink/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Integer>> deleteNavLinkByPrimaryKey(@PathVariable Long id) {
        Integer result = navLinkService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 更新导航链接
     *
     * @param id
     * @param navLinkVO
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/nav/navLink/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<Integer>> updateNavLinkByPrimaryKeySelective(@PathVariable Long id,
                                                                                        @RequestBody NavLinkVO navLinkVO) {
        NavLinkModel navLinkModel = beanMapper.map(navLinkVO, NavLinkModel.class);
        navLinkModel.setId(id);
        Integer result = navLinkService.updateByPrimaryKeySelective(navLinkModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}

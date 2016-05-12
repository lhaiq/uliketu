package com.hengsu.uliketu.core.controller;

import com.google.common.cache.Cache;
import com.hengsu.uliketu.core.ErrorCode;
import com.hengsu.uliketu.core.annotation.IgnoreAuth;
import com.hengsu.uliketu.core.annotation.Permission;
import com.hengsu.uliketu.core.model.AuthModel;
import com.hengsu.uliketu.core.model.UserModel;
import com.hengsu.uliketu.core.vo.ReturnCode;
import com.hengsu.uliketu.core.vo.UpdateAdminPassVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

import com.hengsu.uliketu.core.service.AdminService;
import com.hengsu.uliketu.core.model.AdminModel;
import com.hengsu.uliketu.core.vo.AdminVO;

import java.util.List;

@RestApiController
@RequestMapping("/uliketu")
public class AdminRestApiController {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    @Qualifier("sessionCache")
    private Cache<String, AuthModel> sessionCache;

    /**
     * 管理员详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<AdminVO>> getAdminById(@PathVariable Long id) {
        AdminModel adminModel = adminService.findByPrimaryKey(id);
        AdminVO adminVO = beanMapper.map(adminModel, AdminVO.class);
        ResponseEnvelope<AdminVO> responseEnv = new ResponseEnvelope<>(adminVO, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 管理员列表
     *
     * @return
     */
    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<AdminModel>>> adminlist(Pageable pageable) {
        AdminModel param = new AdminModel();
        List<AdminModel> adminModels = adminService.selectPage(param, pageable);
        long count = adminService.selectCount(param);
        Page<AdminModel> page = new PageImpl<>(adminModels, pageable, count);
        ResponseEnvelope<Page<AdminModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 添加管理员
     *
     * @param adminVO
     * @return
     */
    @Permission(roles = AuthModel.ROLE_SUPER_ADMIN)
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<String>> createAdmin(@RequestBody AdminVO adminVO) {
        AdminModel adminModel = beanMapper.map(adminVO, AdminModel.class);
        adminService.addAdmin(adminModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 更新自己密码
     *
     * @param userId
     * @param adminVO
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_SUPER_ADMIN,AuthModel.ROLE_ADMIN})
    @RequestMapping(value = "/admin/mypass", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<String>> updateMyPass(@Value("#{request.getAttribute('userId')}") Long userId,
                                                                 @RequestBody UpdateAdminPassVO adminVO) {

        //验证就密码是否正确
        AdminModel param = new AdminModel();
        param.setId(userId);
        param.setPassword(DigestUtils.md5Hex(adminVO.getOldPassword()));
        if (CollectionUtils.isEmpty(adminService.selectPage(param, new PageRequest(0, 1)))) {
            ErrorCode.throwBusinessException(ErrorCode.ADMIN_PASS_ERROR);
        }

        param.setPassword(DigestUtils.md5Hex(adminVO.getNewPassword()));
        adminService.updateByPrimaryKeySelective(param);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 重置密码
     *
     * @param adminVO
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/admin/pass/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<String>> updatePass(@PathVariable Long id,
                                                               @RequestBody AdminVO adminVO) {
        AdminModel adminModel = beanMapper.map(adminVO, AdminModel.class);
        adminModel.setId(id);
        adminModel.setPassword(DigestUtils.md5Hex(adminVO.getPassword()));
        adminService.updateByPrimaryKeySelective(adminModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 管理员登录
     *
     * @param adminVO
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<AdminModel>> adminLogin(
            @RequestBody AdminVO adminVO) {
        AdminModel adminModel = beanMapper.map(adminVO, AdminModel.class);
        adminModel = adminService.adminLogin(adminModel);
        AuthModel authModel = new AuthModel(adminModel.getId(),adminModel.getRole(), UserModel.BLACK_NO);
        sessionCache.put(adminModel.getAuthCode(), authModel);
        ResponseEnvelope<AdminModel> responseEnv = new ResponseEnvelope<>(adminModel, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 删除管理员
     *
     * @param id
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Integer>> deleteAdminByPrimaryKey(@PathVariable Long id) {
        Integer result = adminService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }



}

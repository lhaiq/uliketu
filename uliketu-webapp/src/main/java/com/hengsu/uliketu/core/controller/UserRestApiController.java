package com.hengsu.uliketu.core.controller;

import com.google.common.cache.Cache;
import com.hengsu.uliketu.core.annotation.IgnoreAuth;
import com.hengsu.uliketu.core.model.AuthModel;
import com.hengsu.uliketu.core.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.uliketu.core.service.UserService;
import com.hengsu.uliketu.core.model.UserModel;

import javax.validation.Valid;
import java.util.List;

@RestApiController
@RequestMapping("/uliketu")
public class UserRestApiController {
    //@Value("#{request.getAttribute('userId')}") Long userId
    private final Logger logger = LoggerFactory.getLogger(UserRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("sessionCache")
    private Cache<String, AuthModel> sessionCache;

    /**
     * 我的详情
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<UserVO>> getUserById(@Value("#{request.getAttribute('userId')}") Long userId) {
        UserModel userModel = userService.findByPrimaryKey(userId);
        UserVO userVO = beanMapper.map(userModel, UserVO.class);
        ResponseEnvelope<UserVO> responseEnv = new ResponseEnvelope<>(userVO,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 用户注册
     *
     * @param userVO
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<String>> registerUser(@Valid@RequestBody RegisterUserVO userVO) {
        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        userService.registerUser(userModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 校验账号是否已经存在
     *
     * @param account
     * @param type
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/user/validateaccount", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> validateAccount(@RequestParam String account,
                                                                    @RequestParam String type) {
        userService.validateAccount(account, type);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 用户登录
     *
     * @param loginUser
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<UserVO>> userLogin(@RequestBody LoginUserVO loginUser) {
        //判断账号
        UserModel userModel = userService.login(loginUser.getAccount(), loginUser.getPassword());

        //保存authcode
        AuthModel authModel = new AuthModel(userModel.getId(), AuthModel.ROLE_USER);
        sessionCache.put(userModel.getAuthCode(), authModel);

        ResponseEnvelope<UserVO> responseEnv = new ResponseEnvelope<>(beanMapper.map(userModel,UserVO.class),true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 申请密码修改
     *
     * @param mail
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/user/applyidentify", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> applyIdentifyCode(@RequestParam String mail) {
        userService.applyIdentifyCode(mail);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 校验验证码
     *
     * @param code
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/user/authcode", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> authCodeValidate(@RequestParam String mail,
                                                                     @RequestParam String code) {
        userService.validateIdentifyCode(mail, code);

        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 密码更新
     *
     * @param passwdModifyVO
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/user/passwd", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<String>> modifyPasswd(@RequestBody PasswdModifyVO passwdModifyVO) {
        //判断账号
        userService.modifyPasswd(passwdModifyVO.getMail(),
                passwdModifyVO.getPassword(),
                passwdModifyVO.getIdentifyCode());

        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 用户删除:只有管理员才可操作
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Integer>> deleteUserByPrimaryKey(@PathVariable Long id) {
        Integer result = userService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 用户资料更新
     *
     * @param id
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<Integer>> updateUserByPrimaryKeySelective(@PathVariable Long id,
                                                                                     @RequestBody UserVO userVO) {
        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        userModel.setId(id);
        Integer result = userService.updateByPrimaryKeySelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 提交实名认证 TODO 真实名字 gender age
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/submitcertifie/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Integer>> certifie(@RequestParam Long id) {
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setCertifie(UserModel.CERTIFIED);
        Integer result = userService.updateByPrimaryKeySelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**TODO 管理员再做
     * 添加用户
     *
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/admin/user/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> addUser(@Validated @RequestBody UserVO userVO) {
        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        Integer result = userService.createSelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 添加黑名单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/addblacklist/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Integer>> addBlacklist(@PathVariable Long id) {
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setBalance(0L);
        userModel.setBlockBalance(0L);
        userModel.setBlackStatus(UserModel.BLACK_YES);
        Integer result = userService.updateByPrimaryKeySelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 黑名单用户列表
     *
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/admin/blackusers", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<UserVO>>> listBlackUser(Pageable pageable) {

        UserModel param = new UserModel();
        param.setBlackStatus(UserModel.BLACK_YES);
        List<UserModel> userModels = userService.selectPage(param, pageable);
        Long count = userService.selectCount(param);
        Page<UserVO> pages = new PageImpl<>(beanMapper.mapAsList(userModels,UserVO.class), pageable, count);
        ResponseEnvelope<Page<UserVO>> responseEnv = new ResponseEnvelope<>(pages,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 实名认证状态用户列表
     *
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/admin/certifieusers", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<UserVO>>> listCertifieUsers(@RequestParam int certifieStatus,
                                                                               Pageable pageable) {
        UserModel param = new UserModel();
        param.setCertifie(certifieStatus);
        List<UserModel> userModels = userService.selectPage(param, pageable);
        Long count = userService.selectCount(param);
        Page<UserVO> pages = new PageImpl<>(beanMapper.mapAsList(userModels,UserVO.class), pageable, count);
        ResponseEnvelope<Page<UserVO>> responseEnv = new ResponseEnvelope<>(pages,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 用户列表
     *
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<UserModel>>> listUser(Pageable pageable) {

        UserModel param = new UserModel();
        List<UserModel> userModels = userService.selectPage(param, pageable);
        Long count = userService.selectCount(param);
        Page<UserModel> pages = new PageImpl<>(userModels, pageable, count);
        ResponseEnvelope<Page<UserModel>> responseEnv = new ResponseEnvelope<>(pages,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 通过实名认证
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/passcertifie/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Integer>> passCertifie(@PathVariable Long id) {
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setCertifie(UserModel.CERTIFIED);
        Integer result = userService.updateByPrimaryKeySelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 用户详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<UserVO>> detailUser(@PathVariable Long id) {
        UserModel userModel = userService.findByPrimaryKey(id);
        UserVO userVO = beanMapper.map(userModel, UserVO.class);
        ResponseEnvelope<UserVO> responseEnv = new ResponseEnvelope<>(userVO,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}

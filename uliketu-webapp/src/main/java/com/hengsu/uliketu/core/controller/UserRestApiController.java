package com.hengsu.uliketu.core.controller;

import com.google.common.cache.Cache;
import com.hengsu.uliketu.core.model.AuthModel;
import com.hengsu.uliketu.core.vo.LoginUserVO;
import com.hengsu.uliketu.core.vo.PasswdModifyVO;
import com.hengsu.uliketu.core.vo.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.hengsu.uliketu.core.vo.UserVO;

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
     * 获取单个用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<UserVO>> getUserById(@PathVariable Long id) {
        UserModel userModel = userService.findByPrimaryKey(id);
        UserVO userVO = beanMapper.map(userModel, UserVO.class);
        ResponseEnvelope<UserVO> responseEnv = new ResponseEnvelope<>(userVO);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 用户注册
     *
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<String>> registerUser(@Validated @RequestBody UserVO userVO) {
        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        userService.registerUser(userModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 校验账号是否已经存在
     *
     * @param account
     * @param type
     * @return
     */
    @RequestMapping(value = "/user/validateaccount", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> validateAccount(@RequestParam String account,
                                                                    @RequestParam String type) {
        userService.validateAccount(account, type);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 用户登录
     *
     * @param loginUser
     * @return
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<UserModel>> userLogin(@RequestBody LoginUserVO loginUser) {
        //判断账号
        UserModel userModel = userService.login(loginUser.getAccount(), loginUser.getPassword());

        //保存authcode
        AuthModel authModel = new AuthModel(userModel.getId(), AuthModel.ROLE_USER);
        sessionCache.put(userModel.getAuthCode(), authModel);

        ResponseEnvelope<UserModel> responseEnv = new ResponseEnvelope<>(userModel);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 申请密码修改
     *
     * @param mail
     * @return
     */
    @RequestMapping(value = "/user/applyidentify", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> applyIdentifyCode(@RequestParam String mail) {
        userService.applyIdentifyCode(mail);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 校验验证码
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/user/authcode", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> authCodeValidate(@RequestParam String mail,
                                                                     @RequestParam String code) {
        userService.validateIdentifyCode(mail, code);

        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 密码更新
     *
     * @param passwdModifyVO
     * @return
     */
    @RequestMapping(value = "/user/passwd", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<String>> modifyPasswd(@RequestBody PasswdModifyVO passwdModifyVO) {
        //判断账号
        userService.modifyPasswd(passwdModifyVO.getMail(),
                passwdModifyVO.getPassword(),
                passwdModifyVO.getIdentifyCode());

        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK);
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
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
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
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 提交实名认证 TODO
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/certifie/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Integer>> certifie(@RequestParam Long id) {
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setCertifie(UserModel.CERTIFIED);
        Integer result = userService.updateByPrimaryKeySelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 添加用户
     *
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/admin/user/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> addUser(@Validated @RequestBody UserVO userVO) {
        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        Integer result = userService.createSelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    //TODO
    @RequestMapping(value = "/admin/users", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> listUsers(@RequestBody UserVO userVO) {
        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        Integer result = userService.createSelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 添加黑名单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/addblacklist/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Integer>> addBlacklist(@RequestParam Long id) {
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setBalance(0.0);
        userModel.setBlackStatus(UserModel.BLACK_YES);
        Integer result = userService.updateByPrimaryKeySelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 黑名单用户列表
     *
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/admin/blackusers", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<UserModel>>> listBlackUser(Pageable pageable) {

        UserModel param = new UserModel();
        param.setBlackStatus(UserModel.BLACK_YES);
        List<UserModel> userModels = userService.selectPage(param, pageable);
        Long count = userService.selectCount(param);
        Page<UserModel> pages = new PageImpl<>(userModels, pageable, count);
        ResponseEnvelope<Page<UserModel>> responseEnv = new ResponseEnvelope<>(pages);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 实名认证状态用户列表
     *
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/admin/certifieusers", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<UserModel>>> listCertifieUsers(@RequestParam int certifieStatus,
                                                                               Pageable pageable) {
        UserModel param = new UserModel();
        param.setCertifie(certifieStatus);
        List<UserModel> userModels = userService.selectPage(param, pageable);
        Long count = userService.selectCount(param);
        Page<UserModel> pages = new PageImpl<>(userModels, pageable, count);
        ResponseEnvelope<Page<UserModel>> responseEnv = new ResponseEnvelope<>(pages);
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
        Page<UserModel> pages = new PageImpl<UserModel>(userModels, pageable, count);
        ResponseEnvelope<Page<UserModel>> responseEnv = new ResponseEnvelope<>(pages);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 通过实名认证
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/certifie/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Integer>> passCertifie(@RequestParam Long id) {
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setCertifie(UserModel.CERTIFIED);
        Integer result = userService.updateByPrimaryKeySelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


}

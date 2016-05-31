package com.hengsu.uliketu.core.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.hengsu.uliketu.core.Consts;
import com.hengsu.uliketu.core.ErrorCode;
import com.hengsu.uliketu.core.annotation.IgnoreAuth;
import com.hengsu.uliketu.core.annotation.Permission;
import com.hengsu.uliketu.core.model.AuthModel;
import com.hengsu.uliketu.core.service.MessageService;
import com.hengsu.uliketu.core.vo.*;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private MessageService messageService;

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
        ResponseEnvelope<UserVO> responseEnv = new ResponseEnvelope<>(userVO, true);
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
    public ResponseEntity<ResponseEnvelope<String>> registerUser(@Valid @RequestBody RegisterUserVO userVO) {
        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        userModel.setAnswer(JSON.toJSONString(userVO.getAnswers()));
        userService.registerUser(userModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
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
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
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
        AuthModel authModel = new AuthModel(userModel.getId(), AuthModel.ROLE_USER, userModel.getBlackStatus());
        sessionCache.put(userModel.getAuthCode(), authModel);

        ResponseEnvelope<UserVO> responseEnv = new ResponseEnvelope<>(beanMapper.map(userModel, UserVO.class), true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    @IgnoreAuth
    @RequestMapping(value = "/user/qqlogin", method = RequestMethod.GET)
    public void qqLogin(HttpServletRequest request) throws IOException {

        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);

            if (accessTokenObj.getAccessToken().equals("")) {
//                我们的网站被CSRF攻击了或者用户取消了授权
//                做一些数据统计工作
                logger.error("没有获取到响应参数");
            } else {
                String accessToken = accessTokenObj.getAccessToken();
                long tokenExpireIn = accessTokenObj.getExpireIn();

                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj = new OpenID(accessToken);
                String openID = openIDObj.getUserOpenID();

            }
        } catch (QQConnectException e) {
            logger.error("QQConnectException,", e);
        }
    }

    /**
     * 查询自己问题
     *
     * @param phone
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/user/answers", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Map<String, Object>>> getAnswers(@RequestParam String phone) {
        UserModel userModel = userService.findByPhone(phone);
        if (null == userModel) {
            ErrorCode.throwBusinessException(ErrorCode.LOGIN_USER_NOT_EXISTED);
        }
        Set<String> questions = JSON.parseObject(userModel.getAnswer()).keySet();
        Map<String, Object> result = new HashMap<>();
        result.put("id", userModel.getId());
        result.put("questions", questions);

        ResponseEnvelope<Map<String, Object>> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 更新密码通过问题
     *
     * @param id
     * @param passWdQuestionVO
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/user/passedByquestion/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<String>> updatePassWdByRequestion(
            @PathVariable Long id,
            @RequestBody UpdatePassWdQuestionVO passWdQuestionVO) {

        UserModel userModel = userService.findByPrimaryKey(id);
        if (null == userModel) {
            ErrorCode.throwBusinessException(ErrorCode.LOGIN_USER_NOT_EXISTED);
        }
        JSONObject jsonObject = JSON.parseObject(userModel.getAnswer());

        if (jsonObject.size() != passWdQuestionVO.getAnswers().size()) {
            ErrorCode.throwBusinessException(ErrorCode.ANSWER_ERROR);
        }
        for (Map.Entry<String, String> entry : passWdQuestionVO.getAnswers().entrySet()) {
            String key = entry.getKey();
            if (!entry.getValue().equals(jsonObject.getString(key))) {
                ErrorCode.throwBusinessException(ErrorCode.ANSWER_ERROR);
            }
        }

        UserModel param = new UserModel();
        param.setId(id);
        param.setPassword(DigestUtils.md5Hex(passWdQuestionVO.getPassword()));
        userService.updateByPrimaryKeySelective(param);

        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
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
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
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

        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
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

        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 用户删除:只有管理员才可操作
     *
     * @param id
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Integer>> deleteUserByPrimaryKey(@PathVariable Long id) {
        Integer result = userService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 用户资料更新
     *
     * @param userId
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<Integer>> updateUserByPrimaryKey(@Value("#{request.getAttribute('userId')}") Long userId,
                                                                                     @RequestBody UserVO userVO) {
        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        userModel.setId(userId);
        Integer result = userService.updateByPrimaryKeySelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 提交实名认证
     *
     * @param certifieUserVO
     * @return
     */
    @RequestMapping(value = "/user/submitcertifie", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> certifie(@Value("#{request.getAttribute('userId')}") Long userId,
                                                              @Valid @RequestBody CertifieUserVO certifieUserVO) {
        UserModel userModel = beanMapper.map(certifieUserVO, UserModel.class);
        userModel.setId(userId);
        userModel.setCertifie(UserModel.CERTIFIED);
        Integer result = userService.updateByPrimaryKeySelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 添加用户
     *
     * @param userVO
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/admin/user/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> addUser(@Validated @RequestBody UserVO userVO) {
        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        Integer result = userService.createSelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 添加黑名单
     *
     * @param id
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/admin/addblacklist/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Integer>> addBlacklist(@PathVariable Long id) {
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setBalance(0L);
        userModel.setBlockBalance(0L);
        userModel.setBlackStatus(UserModel.BLACK_YES);
        Integer result = userService.updateByPrimaryKeySelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 黑名单用户列表
     *
     * @param pageable
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/admin/blackusers", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<UserVO>>> listBlackUser(Pageable pageable) {

        UserModel param = new UserModel();
        param.setBlackStatus(UserModel.BLACK_YES);
        List<UserModel> userModels = userService.selectPage(param, pageable);
        Long count = userService.selectCount(param);
        Page<UserVO> pages = new PageImpl<>(beanMapper.mapAsList(userModels, UserVO.class), pageable, count);
        ResponseEnvelope<Page<UserVO>> responseEnv = new ResponseEnvelope<>(pages, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 实名认证状态用户列表
     *
     * @param pageable
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/admin/certifieusers", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<UserVO>>> listCertifieUsers(@RequestParam int certifieStatus,
                                                                            Pageable pageable) {
        UserModel param = new UserModel();
        param.setCertifie(certifieStatus);
        List<UserModel> userModels = userService.selectPage(param, pageable);
        Long count = userService.selectCount(param);
        Page<UserVO> pages = new PageImpl<>(beanMapper.mapAsList(userModels, UserVO.class), pageable, count);
        ResponseEnvelope<Page<UserVO>> responseEnv = new ResponseEnvelope<>(pages, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 用户列表
     *
     * @param pageable
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<UserModel>>> listUser(Pageable pageable) {

        UserModel param = new UserModel();
        List<UserModel> userModels = userService.selectPage(param, pageable);
        Long count = userService.selectCount(param);
        Page<UserModel> pages = new PageImpl<>(userModels, pageable, count);
        ResponseEnvelope<Page<UserModel>> responseEnv = new ResponseEnvelope<>(pages, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 通过实名认证
     *
     * @param id
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/admin/passcertifie/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Integer>> passCertifie(@PathVariable Long id) {
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setCertifie(UserModel.CERTIFIED);
        Integer result = userService.updateByPrimaryKeySelective(userModel);
        messageService.addMessage(Consts.REAL_NAME, id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 用户详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<UserVO>> detailUser(@PathVariable Long id) {
        UserModel userModel = userService.findByPrimaryKey(id);
        UserVO userVO = beanMapper.map(userModel, UserVO.class);
        ResponseEnvelope<UserVO> responseEnv = new ResponseEnvelope<>(userVO, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 最新用户
     *
     * @param pageable
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/newestusers", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<UserModel>>> newestUsers(Pageable pageable) {

        UserModel param = new UserModel();
        List<UserModel> userModels = userService.selectPage(param, pageable);
        for (UserModel userModel : userModels) {
            userModel.setPassword(null);
            String phone = userModel.getPhone();
            userModel.setPhone(phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4, phone.length()));
            userModel.setAnswer(null);
            userModel.setMail(null);
        }
        Long count = userService.selectCount(param);
        Page<UserModel> pages = new PageImpl<>(userModels, pageable, count);
        ResponseEnvelope<Page<UserModel>> responseEnv = new ResponseEnvelope<>(pages, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}

package com.hengsu.uliketu.core.service.impl;

import static com.hengsu.uliketu.core.ErrorCode.*;

import com.google.common.cache.Cache;
import com.google.common.collect.ImmutableMap;
import com.hengsu.uliketu.core.Consts;
import com.hengsu.uliketu.core.RandomUtil;
import com.hengsu.uliketu.core.model.RecommendRelationModel;
import com.hengsu.uliketu.core.model.StatementModel;
import com.hengsu.uliketu.core.service.ConfService;
import com.hengsu.uliketu.core.service.RecommendRelationService;
import com.hengsu.uliketu.core.service.StatementService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.core.entity.User;
import com.hengsu.uliketu.core.repository.UserRepository;
import com.hengsu.uliketu.core.model.UserModel;
import com.hengsu.uliketu.core.service.UserService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RecommendRelationService recommendRelationService;

    @Autowired
    private ConfService confService;

    @Autowired
    private StatementService statementService;

    @Autowired
    @Qualifier("identifyCodeCache")
    private Cache<String, String> identifyCodeCache;

    @Transactional
    @Override
    public int create(UserModel userModel) {
        return userRepo.insert(beanMapper.map(userModel, User.class));
    }

    @Transactional
    @Override
    public int createSelective(UserModel userModel) {
        User user = beanMapper.map(userModel, User.class);
        int ret = userRepo.insertSelective(user);
        userModel.setId(user.getId());
        return ret;
    }

    @Transactional
    @Override
    public void registerUser(UserModel userModel) {

        validateAccount(userModel.getUsername(), UserModel.ACCOUNT_TYPE_USERNAME);
        validateAccount(userModel.getMail(), UserModel.ACCOUNT_TYPE_MAIL);
        validateAccount(userModel.getUsername(), UserModel.ACCOUNT_TYPE_PHONE);

        UserModel recommendUser = null;
        //检测推荐人是否存在
        if (!StringUtils.isEmpty(userModel.getRandomId())) {
            recommendUser = findByRandomId(userModel.getRandomId());
            if (null == recommendUser) {
                throwBusinessException(RECOMMEND_USER_NOT_EXISTED);
            }
        }

        //生成自己的randomId
        userModel.setRandomId(generateRandomId());

        //添加User
        userModel.setRegisterTime(new Date());
        userModel.setPassword(DigestUtils.md5Hex(userModel.getPassword()));
        createSelective(userModel);

        if (null != recommendUser) {
            //添加推荐关系
            RecommendRelationModel rrm = new RecommendRelationModel();
            rrm.setCreateTime(new Date());
            rrm.setRecommendId(recommendUser.getId());
            rrm.setUserId(userModel.getId());
            rrm.setNum(Long.parseLong(confService.findByPrimaryKey(Consts.RECOMMEDN_VIRTUAL_NUM).getConfValue()));
            recommendRelationService.createSelective(rrm);
        }
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return userRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserModel findByPrimaryKey(Long id) {
        User user = userRepo.selectByPrimaryKey(id);
        return beanMapper.map(user, UserModel.class);
    }

    @Override
    public UserModel findByUserName(String username) {
        return beanMapper.map(userRepo.selectByUserName(username), UserModel.class);
    }

    @Override
    public UserModel findByPhone(String phone) {
        return beanMapper.map(userRepo.selectByPhone(phone), UserModel.class);
    }

    @Override
    public UserModel findByMail(String mail) {
        return beanMapper.map(userRepo.selectByMail(mail), UserModel.class);
    }

    @Override
    public UserModel findByRandomId(String randomId) {
        return beanMapper.map(userRepo.selectByRandomId(randomId), UserModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public long selectCount(UserModel userModel) {
        return userRepo.selectCount(beanMapper.map(userModel, User.class));
    }

    @Override
    public List<UserModel> selectPage(UserModel userModel, Pageable pageable) {
        List<User> users = userRepo.selectPage(beanMapper.map(userModel, User.class), pageable);
        return beanMapper.mapAsList(users, UserModel.class);
    }

    @Override
    public UserModel login(String account, String password) {

        UserModel userModel = null;

        //用户名
        userModel = findByUserName(account);
        if (null == userModel) {
            //邮箱
            userModel = findByMail(account);
            if (null == userModel) {
                //手机
                userModel = findByPhone(account);
            }
        }

        if (null == userModel) {
            throwBusinessException(LOGIN_USER_NOT_EXISTED);
        }

        //判断密码
        if (!userModel.getPassword().equals(DigestUtils.md5Hex(password))) {
            throwBusinessException(LOGIN_PASSWORD_ERROR);
        }

        //生成authCode
        String authCode = RandomUtil.generateAuthToken();
        userModel.setAuthCode(authCode);
        userModel.setPassword(null);
        return userModel;
    }

    @Transactional
    @Override
    public void modifyPasswd(String mail, String password, String identifyCode) {
        if (!identifyCode.equals(identifyCodeCache.getIfPresent(mail))) {
            throwBusinessException(IDENTIFY_CODE_ERROR);
        }

        identifyCodeCache.invalidate(mail);

        //判断user是否存在
        UserModel userModel = findByMail(mail);
        if (null == userModel) {
            throwBusinessException(MAIL_NOT_EXISTED);
        }

        //更新密码
        UserModel param = new UserModel();
        param.setId(userModel.getId());
        param.setPassword(DigestUtils.md5Hex(password));
        updateByPrimaryKeySelective(param);
    }

    @Override
    public void validateAccount(String account, String type) {

        if (UserModel.ACCOUNT_TYPE_USERNAME.equals(type)) {
            if (null != findByUserName(account)) throwBusinessException(USERNAME_EXISTED);
        } else if (UserModel.ACCOUNT_TYPE_PHONE.equals(type)) {
            if (null != findByPhone(account)) throwBusinessException(PHONE_EXISTED);
        } else if (UserModel.ACCOUNT_TYPE_MAIL.equals(type)) {
            if (null != findByMail(account)) throwBusinessException(MAIL_EXISTED);
        } else {
            throwBusinessException(ACCOUNT_TYPE_NOT_SUPPORTED);
        }

    }

    @Override
    public void validateIdentifyCode(String mail, String identifyCode) {
        if (!identifyCode.equals(identifyCodeCache.getIfPresent(mail.toString()))) {
            throwBusinessException(IDENTIFY_CODE_ERROR);
        }
    }

    @Override
    public void applyIdentifyCode(String mail) {

        UserModel userModel = findByMail(mail);
        if (null == userModel) {
            throwBusinessException(MAIL_NOT_EXISTED);
        }

        String identifyCode = RandomUtil.createRandom(true, 6);
        identifyCodeCache.put(mail, identifyCode);
        //TODO send mail
        System.out.println(identifyCode);
    }

    @Transactional
    @Override
    public int addBalance(Long id, Long balance, Integer type, String desc) {
        Map param = ImmutableMap.of("id", id, "balance", balance);
        int ret = userRepo.addBalance(param);
        if (ret == 0) {
            throwBusinessException(BALANCE_NOT_ENOUGH);
        }


        //创建流水
        StatementModel statementModel = new StatementModel();
        statementModel.setCreateTime(new Date());
        statementModel.setDescription(desc);
        statementModel.setNum(balance);
        statementModel.setUserid(id);
        statementModel.setType(type);

        statementService.createSelective(statementModel);


        return ret;
    }

    @Transactional
    @Override
    public int addBlockBalance(Long id, Long balance) {
        Map param = ImmutableMap.of("id", id, "balance", balance);
        return userRepo.addBlockBalance(param);
    }


    @Transactional
    @Override
    public int updateByPrimaryKey(UserModel userModel) {
        return userRepo.updateByPrimaryKey(beanMapper.map(userModel, User.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(UserModel userModel) {
        return userRepo.updateByPrimaryKeySelective(beanMapper.map(userModel, User.class));
    }

    private String generateRandomId() {
        String randomId = "";
        while (true) {
            if (StringUtils.isNotEmpty(randomId)) return randomId;
            randomId = RandomUtil.createRandom(false, 8);
            if (null != findByRandomId(randomId)) {
                randomId = "";
            }
        }
    }

}

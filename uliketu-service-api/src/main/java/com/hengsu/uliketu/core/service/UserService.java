
package com.hengsu.uliketu.core.service;

import com.hengsu.uliketu.core.model.UserModel;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface UserService {

    public int create(UserModel userModel);

    public int createSelective(UserModel userModel);

    public void registerUser(UserModel userModel);

    public UserModel findByPrimaryKey(Long id);

    public UserModel findByUserName(String username);

    public UserModel findByPhone(String phone);

    public UserModel findByMail(String mail);

    public UserModel findByRandomId(String randomId);

    public int updateByPrimaryKey(UserModel userModel);

    public int updateByPrimaryKeySelective(UserModel userModel);

    public int deleteByPrimaryKey(Long id);

    public long selectCount(UserModel userModel);

    public List<UserModel> selectPage(UserModel userModel,Pageable pageable);

    public UserModel login(String account, String password);

    public void modifyPasswd(String mail, String password,String identifyCode);

    public void validateAccount(String account,String type);

    public void validateIdentifyCode(String mail,String identifyCode);

    public void applyIdentifyCode(String mail);

    public void addBalance(double balance);

    public void addBlockBalance(double balance);


}
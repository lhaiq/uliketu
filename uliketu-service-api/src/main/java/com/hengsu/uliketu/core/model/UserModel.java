package com.hengsu.uliketu.core.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

import java.util.Date;

@MapClass("com.hengsu.uliketu.core.entity.User")
public class UserModel {
    public final static int BLACK_YES = 1;
    public final static int BLACK_NO = 0;

    public final static String ACCOUNT_TYPE_USERNAME = "username";
    public final static String ACCOUNT_TYPE_MAIL = "mail";
    public final static String ACCOUNT_TYPE_PHONE = "phone";

    public final static int UNCERTIFIE = 0;
    public final static int CERTIFIEDING = 1;

    public final static int CERTIFIED = 2;



    private Long id;
    private String username;
    private String password;
    private String mail;
    private String phone;
    private String avatar;
    private Long balance;

    public Long getBlockBalance() {
        return blockBalance;
    }

    public void setBlockBalance(Long blockBalance) {
        this.blockBalance = blockBalance;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    private Long blockBalance;
    private String idnumber;
    private String idphoto;
    private Integer blackStatus;
    private Integer certifie;
    private Date registerTime;
    private String nickname;
    private String authCode;
    private String randomId;
    private String answer;

    private String realName;

    private Integer gender;

    private Integer age;

    private String address;

    private Integer accountType;

    private String accountName;

    private String accountId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return this.mail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getIdnumber() {
        return this.idnumber;
    }

    public void setIdphoto(String idphoto) {
        this.idphoto = idphoto;
    }

    public String getIdphoto() {
        return this.idphoto;
    }

    public void setBlackStatus(Integer blackStatus) {
        this.blackStatus = blackStatus;
    }

    public Integer getBlackStatus() {
        return this.blackStatus;
    }

    public void setCertifie(Integer certifie) {
        this.certifie = certifie;
    }

    public Integer getCertifie() {
        return this.certifie;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getRegisterTime() {
        return this.registerTime;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setRandomId(String randomId) {
        this.randomId = randomId;
    }

    public String getRandomId() {
        return randomId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
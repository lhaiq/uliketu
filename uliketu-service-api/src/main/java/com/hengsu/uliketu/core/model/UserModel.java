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
    private Double balance;
    private Double blockBalance;
    private String idnumber;
    private String idphoto;
    private Integer blackStatus;
    private Integer certifie;
    private String baifubaoAccount;
    private Date registerTime;
    private String nickname;
    private String authCode;
    private String randomId;

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

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void setBlockBalance(Double blockBalance) {
        this.blockBalance = blockBalance;
    }

    public Double getBlockBalance() {
        return this.blockBalance;
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

    public void setBaifubaoAccount(String baifubaoAccount) {
        this.baifubaoAccount = baifubaoAccount;
    }

    public String getBaifubaoAccount() {
        return this.baifubaoAccount;
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
}
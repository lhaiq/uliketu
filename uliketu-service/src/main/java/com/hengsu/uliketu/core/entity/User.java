package com.hengsu.uliketu.core.entity;

import java.util.Date;

public class User {
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

    private String randomId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBlockBalance() {
        return blockBalance;
    }

    public void setBlockBalance(Double blockBalance) {
        this.blockBalance = blockBalance;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getIdphoto() {
        return idphoto;
    }

    public void setIdphoto(String idphoto) {
        this.idphoto = idphoto;
    }

    public Integer getBlackStatus() {
        return blackStatus;
    }

    public void setBlackStatus(Integer blackStatus) {
        this.blackStatus = blackStatus;
    }

    public Integer getCertifie() {
        return certifie;
    }

    public void setCertifie(Integer certifie) {
        this.certifie = certifie;
    }

    public String getBaifubaoAccount() {
        return baifubaoAccount;
    }

    public void setBaifubaoAccount(String baifubaoAccount) {
        this.baifubaoAccount = baifubaoAccount;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setRandomId(String randomId) {
        this.randomId = randomId;
    }

    public String getRandomId() {
        return randomId;
    }
}
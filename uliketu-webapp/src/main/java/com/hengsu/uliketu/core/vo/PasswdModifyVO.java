package com.hengsu.uliketu.core.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by haiquanli on 16/4/13.
 */
public class PasswdModifyVO {

    @NotEmpty
    private String mail;

    public String getPassword() {
        return password;
    }

    @NotEmpty
    private String identifyCode;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    @NotEmpty
    private String password;

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public String getIdentifyCode() {
        return identifyCode;
    }
}

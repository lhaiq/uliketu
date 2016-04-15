package com.hengsu.uliketu.core.vo;

/**
 * Created by haiquanli on 16/4/13.
 */
public class LoginUserVO {

    private String account;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    private String password;

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }
}

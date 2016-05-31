package com.hengsu.uliketu.core.vo;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Map;

/**
 * Created by haiquanli on 16/4/13.
 */
public class RegisterUserVO {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String mail;
    @NotEmpty
    private String phone;
    private String randomId;
    @NotEmpty
    private Map<String,String> answers;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRandomId(String randomId) {
        this.randomId = randomId;
    }

    public String getRandomId() {
        return randomId;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }
}

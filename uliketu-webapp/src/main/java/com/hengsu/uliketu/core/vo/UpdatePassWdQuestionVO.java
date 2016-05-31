package com.hengsu.uliketu.core.vo;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Map;

/**
 * Created by haiquanli on 16/5/12.
 */
public class UpdatePassWdQuestionVO {

    @NotEmpty
    private String password;

    @NotEmpty
    private Map<String,String> answers;

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.hengsu.uliketu.core;

import com.hkntv.pylon.core.exception.BusinessException;

/**
 * Created by haiquanli on 15/11/19.
 */
public enum ErrorCode {

    //AUTH
    SYSTEM_INTERNAL_ERROR("1000", "内部错误"),
    AUTH_CODE_TIME_OUT("1001", "验证码超时"),
    AUTH_CODE_ERROR("1002", "验证码不正确"),
    AUTH_TOKEN_INVALID("1003", "AUTH TOKEN非法"),
    AUTH_TOKEN_MUST("1004", "AUTH TOKEN 必须填"),
    REGISTER_PHONE_EXISTED("1005", "该手机已经注册过"),
    LOGIN_USER_NOT_EXISTED("1006", "用户不存在"),
    LOGIN_PASSWORD_ERROR("1007", "密码错误"),
    ACCOUNT_TYPE_NOT_SUPPORTED("1007", "该类型账号不支持"),
    USERNAME_EXISTED("1007", "该用户名已经注册"),
    MAIL_EXISTED("1007", "该邮箱已经被注册"),
    PHONE_EXISTED("1007", "该手机已经被注册"),
    IDENTIFY_CODE_ERROR("1007", "验证码错误"),
    MAIL_NOT_EXISTED("1007", "该用户不存在"),
    RECOMMEND_USER_NOT_EXISTED("1007", "推荐用户不存在"),

    MESSAGE_NOT_EXISTED("1007", "该消息不存在"),
    IS_NOT_YOUR_MESSAGE("1007", "不是您的消息,无法更新"),
    BALANCE_NOT_ENOUGH("1007", "您的虚拟币不够"),
    DATE_CANNOT_CASH("1007", "每月25日后不能提取,请下月1日再来"),


    PHONE_NOT_REGISTER("1009", "该手机号尚未注册"),

    JSON_FORMATTED("1010", "JSON格式错误"),
    FIELD_MUST("1011", "字段必须填"),
    PARAMETER_MUST("1012", "字段必须填"),
    IMAGE_NOT_EXISTED("1013", "图片不存在"),

    DELETE_ONLY_SELF("1014", "只能删除自己"),
    AUTH_CODE_NOT_EXISTED("1015", "该手机号尚未获取验证码"),
    JSON_FORMAT_ERROR("1016", "json格式错误"),
    OLD_PASSWORD_ERROR("1017", "旧密码错误"),
    CREATE_PERSON_ERROR("1018", "提取人脸信息失败"),
    VERIFY_PERSON_ERROR("1019", "人脸登陆失败"),




    ;

    private String code;
    private String errorMsg;

    private ErrorCode(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public static void throwBusinessException(ErrorCode errorCode) {
        throw new BusinessException(errorCode.errorMsg, errorCode.code);
    }

    public String getCode() {
        return code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public static void main(String[] args) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            System.out.println(errorCode.getCode() + "\t" + errorCode.getErrorMsg());
        }
    }
}

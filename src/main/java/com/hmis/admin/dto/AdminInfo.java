package com.hmis.admin.dto;

public class AdminInfo {

    private String userName;

    private String userPasswd;

    //登录时的验证码
    private String code;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
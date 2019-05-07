package com.hmis.worker.dto;

public class WorkerInfo {

    private Integer workerNo;

   //private int worker;

    private String workerName;

    private String workerSex;

    private Byte workerAge;

   private Integer  departmentNo;

    private String workerTel;

    private String workerEmail;

    private String workerPwd;

    private String headPortrait;

    //登录时的验证码
    private String code;

    //登录时的表单数据用户名
    private String userName;

    //登录用的
    private String userPasswd;


    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerSex() {
        return workerSex;
    }

    public void setWorkerSex(String workerSex) {
        this.workerSex = workerSex;
    }

    public Byte getWorkerAge() {
        return workerAge;
    }

    public void setWorkerAge(Byte workerAge) {
        this.workerAge = workerAge;
    }

    public int getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(int departmentNo) {
        this.departmentNo = departmentNo;
    }

    public String getWorkerTel() {
        return workerTel;
    }

    public void setWorkerTel(String workerTel) {
        this.workerTel = workerTel;
    }

    public String getWorkerEmail() {
        return workerEmail;
    }

    public void setWorkerEmail(String workerEmail) {
        this.workerEmail = workerEmail;
    }

    public String getWorkerPwd() {
        return workerPwd;
    }

    public void setWorkerPwd(String workerPwd) {
        this.workerPwd = workerPwd;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public Integer getWorkerNo() {
        return workerNo;
    }

    public void setWorkerNo(Integer workerNo) {
        this.workerNo = workerNo;
    }
}
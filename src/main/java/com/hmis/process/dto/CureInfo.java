package com.hmis.process.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CureInfo {

    private Integer cureNo;

    private Integer orderNo;

    private Integer departmentNo;

    private String departmentName;

    private Integer workerNo;

    private String workerName;

    private Integer userNo;

    private String userName;

    private Integer animalNo;

    private String animalName;

//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String cureTime;

    private String cureContent;

    public Integer getCureNo() {
        return cureNo;
    }

    public void setCureNo(Integer cureNo) {
        this.cureNo = cureNo;
    }

    public Integer getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(Integer departmentNo) {
        this.departmentNo = departmentNo;
    }

    public Integer getWorkerNo() {
        return workerNo;
    }

    public void setWorkerNo(Integer workerNo) {
        this.workerNo = workerNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getAnimalNo() {
        return animalNo;
    }

    public void setAnimalNo(Integer animalNo) {
        this.animalNo = animalNo;
    }

    public String getCureTime() {
        return cureTime;
    }

    public void setCureTime(String cureTime) {
        this.cureTime = cureTime;
    }

    public String getCureContent() {
        return cureContent;
    }

    public void setCureContent(String cureContent) {
        this.cureContent = cureContent;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }
}
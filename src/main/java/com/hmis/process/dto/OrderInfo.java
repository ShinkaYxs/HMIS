package com.hmis.process.dto;

import java.util.Date;

public class OrderInfo {
    private Integer orderNo;

    private Integer orderNoOfDept;

    private Integer userNo;

    private Integer animalNo;

    private Integer departmentNo;

    private Integer workerNo;

    private Date orderTime;

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderNoOfDept() {
        return orderNoOfDept;
    }

    public void setOrderNoOfDept(Integer orderNoOfDept) {
        this.orderNoOfDept = orderNoOfDept;
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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}
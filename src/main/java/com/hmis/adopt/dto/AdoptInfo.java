package com.hmis.adopt.dto;

public class AdoptInfo {
    private Integer adoptNo;

    private Integer userNo;

    private Integer animalNo;

    private String operationType;

    private String operationTime;

    public Integer getAdoptNo() {
        return adoptNo;
    }

    public void setAdoptNo(Integer adoptNo) {
        this.adoptNo = adoptNo;
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

    public String getOperationType() { return operationType; }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationTime() { return operationTime; }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

}
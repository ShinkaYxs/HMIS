package com.hmis.worker.service;

import com.hmis.worker.dto.WorkerInfo;

import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/4/30 16:49
 */
public interface WorkerInfoService {

    /**
     * 工作人员登录
     * @param workerInfo
     * @return
     */
    List<WorkerInfo> workerLogin(WorkerInfo workerInfo);


    /**
     * 工作人员-个人资料修改
     * @param workerInfo
     * @return
     */
    int updateByNoSelective(WorkerInfo workerInfo);

    /**
     * 工作人员修改个人资料之后重新查询该人的个人资料
     * @param workerNo
     * @return
     */
    WorkerInfo selectByNo(int workerNo);

}





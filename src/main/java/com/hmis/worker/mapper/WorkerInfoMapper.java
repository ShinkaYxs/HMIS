package com.hmis.worker.mapper;

import com.hmis.worker.dto.WorkerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerInfoMapper {

    /**
     * 工作人员登录
     * @param workerInfo
     * @return
     */
    List<WorkerInfo> queryCountForLogin(@Param("dto") WorkerInfo workerInfo);

    /**
     * 工作人员个人资料修改
     * @param workerInfo
     * @return
     */
    int updateByNoSelective(@Param("dto") WorkerInfo workerInfo);

    /**
     * 工作人员修改个人资料之后重新查询该人的个人资料
     * @param workerNo
     * @return
     */
    WorkerInfo selectByNo(int workerNo);

    /**
     * 工作人员修改密码
     * @param workerInfo
     * @return
     */
    int updatePwdByNoAndOld(@Param("dto") WorkerInfo workerInfo);

    /**
     * 查询所有工作人员信息
     * @return
     */
//    List<WorkerInfo> workerInfoQueryAll(@Param("dto") WorkerInfo workerInfo);
    List<WorkerInfo> workerInfoQueryAll();

    /**
     * 管理员根据工号删除工作人员
     * @param workerId
     * @return
     */
    int deleteWorkerByNo(Integer workerId);



    int insertSelective(WorkerInfo workerInfo);

    int insert(WorkerInfo workerInfo);

    int updateByPrimaryKey(WorkerInfo workerInfo);
}
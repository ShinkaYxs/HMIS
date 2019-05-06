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

    int deleteByPrimaryKey(String workerId);

    int insert(WorkerInfo record);

    int insertSelective(WorkerInfo record);

    /**
     * 工作人员修改个人资料之后重新查询该人的个人资料
     * @param workerId
     * @return
     */
    WorkerInfo selectById(String workerId);

    /**
     * 工作人员-个人资料修改
     * @param record
     * @return
     */
    int updateByIdSelective(@Param("dto") WorkerInfo record);

    int updateByPrimaryKey(WorkerInfo record);
}
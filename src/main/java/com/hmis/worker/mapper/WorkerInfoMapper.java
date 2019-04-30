package com.hmis.worker.mapper;

import com.hmis.worker.dto.WorkerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerInfoMapper {

    List<WorkerInfo> queryCountForLogin(@Param("dto") WorkerInfo workerInfo);

    int deleteByPrimaryKey(String workerId);

    int insert(WorkerInfo record);

    int insertSelective(WorkerInfo record);

    WorkerInfo selectByPrimaryKey(String workerId);

    int updateByPrimaryKeySelective(WorkerInfo record);

    int updateByPrimaryKey(WorkerInfo record);
}
package com.hmis.worker.service;

import com.hmis.worker.dto.WorkerInfo;

import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/4/30 16:49
 */
public interface WorkerInfoService {

    List<WorkerInfo> workerLogin(WorkerInfo workerInfo);
}

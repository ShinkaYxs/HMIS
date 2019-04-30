/**
 * 文件名：WorkerInfoServiceImpl
 * 描述：
 */

package com.hmis.worker.service.impl;

import com.hmis.worker.dto.WorkerInfo;
import com.hmis.worker.mapper.WorkerInfoMapper;
import com.hmis.worker.service.WorkerInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/4/30 16:50
 */
@Service
public class WorkerInfoServiceImpl implements WorkerInfoService {

    @Resource
    private WorkerInfoMapper workerInfoMapper;

    @Override
    public List<WorkerInfo> workerLogin(WorkerInfo workerInfo){
        return workerInfoMapper.queryCountForLogin(workerInfo);
    }

}

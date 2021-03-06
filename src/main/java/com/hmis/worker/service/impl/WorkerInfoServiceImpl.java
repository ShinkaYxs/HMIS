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

    /**
     * 工作人员登录
     * @param workerInfo
     * @return
     */
    @Override
    public List<WorkerInfo> workerLogin(WorkerInfo workerInfo){
        return workerInfoMapper.queryCountForLogin(workerInfo);
    }

    /**
     * 工作人员个人资料修改
     * @param workerInfo
     * @return
     */
    @Override
    public int updateByNoSelective(WorkerInfo workerInfo) {
        return workerInfoMapper.updateByNoSelective(workerInfo);
    }

    /**
     * 工作人员修改个人资料之后重新查询该人的个人资料
     * @param workerNo
     * @return
     */
    @Override
    public WorkerInfo selectByNo(int workerNo) {
        return workerInfoMapper.selectByNo(workerNo);
    }

    /**
     * 工作人员修改密码
     * @param workerInfo
     * @return
     */
    @Override
    public int updatePwdByNoAndOld(WorkerInfo workerInfo) {
        return workerInfoMapper.updatePwdByNoAndOld(workerInfo);
    }

    /**
     * 查询所有工作人员信息
     * @return
     */
//    @Override
//    public List<WorkerInfo> workerInfoQueryAll(WorkerInfo workerInfo) {
//        return workerInfoMapper.workerInfoQueryAll(workerInfo);
//    }
    @Override
    public List<WorkerInfo> workerInfoQueryAll() {
        return workerInfoMapper.workerInfoQueryAll();
    }

    /**
     * 管理员根据工号删除工作人员
     * @param workerId
     * @return
     */
    @Override
    public int deleteWorkerByNo(Integer workerId) {
        return workerInfoMapper.deleteWorkerByNo(workerId);
    }

    /**
     * 管理员添加工作人员信息
     * @param workerInfo
     * @return
     */
    @Override
    public int workerAdd(WorkerInfo workerInfo) {
        return workerInfoMapper.workerAdd(workerInfo);
    }

    /**
     * 管理员根据科室No查询该科室下的工作人员
     * @param deptId
     * @return
     */
    @Override
    public List<WorkerInfo> workerQueryByDeptNo(Integer deptId) {
        return workerInfoMapper.workerQueryByDeptNo(deptId);
    }
}

package com.hmis.dept.service;

import com.hmis.dept.dto.DepartmentInfo;

import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/10 20:46
 */
public interface DepartmentInfoService {

    /**
     * 查询所有科室信息
     * @return
     */
    List<DepartmentInfo> deptInfoQueryAll();

    /**
     * 科室修改
     * @param departmentInfo
     * @return
     */
    int updateByNoSelective(DepartmentInfo departmentInfo);

    /**
     * 管理员根据No删除科室信息
     * @param departmentNo
     * @return
     */
    int deleteDeptByNo(Integer departmentNo);

    /**
     * 添加科室信息
     * @param departmentInfo
     * @return
     */
    int deptAdd(DepartmentInfo departmentInfo);

}

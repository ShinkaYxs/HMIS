package com.hmis.dept.mapper;

import com.hmis.dept.dto.DepartmentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentInfoMapper {

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
    int updateByNoSelective(@Param("dto") DepartmentInfo departmentInfo);

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
    int deptAdd(@Param("dto") DepartmentInfo departmentInfo);



    int insertSelective(DepartmentInfo departmentInfo);

    DepartmentInfo selectByPrimaryKey(Integer departmentNo);

    int updateByPrimaryKey(DepartmentInfo departmentInfo);
}
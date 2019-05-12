/**
 * 文件名：DepartmentInfoServiceImpl
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.dept.service.impl;

import com.hmis.dept.dto.DepartmentInfo;
import com.hmis.dept.mapper.DepartmentInfoMapper;
import com.hmis.dept.service.DepartmentInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/10 20:46
 */
@Service
public class DepartmentInfoServiceImpl implements DepartmentInfoService {

    @Resource
    private DepartmentInfoMapper departmentInfoMapper;

    /**
     * 查询所有科室信息
     * @return
     */
    @Override
    public List<DepartmentInfo> deptInfoQueryAll() {
        return departmentInfoMapper.deptInfoQueryAll();
    }

    /**
     * 科室修改
     * @param departmentInfo
     * @return
     */
    @Override
    public int updateByNoSelective(DepartmentInfo departmentInfo) {
        return departmentInfoMapper.updateByNoSelective(departmentInfo);
    }

    /**
     * 管理员根据No删除科室信息
     * @param departmentNo
     * @return
     */
    @Override
    public int deleteDeptByNo(Integer departmentNo) {
        return departmentInfoMapper.deleteDeptByNo(departmentNo);
    }

    /**
     * 添加科室信息
     * @param departmentInfo
     * @return
     */
    @Override
    public int deptAdd(DepartmentInfo departmentInfo) {
        return departmentInfoMapper.deptAdd(departmentInfo);
    }
}

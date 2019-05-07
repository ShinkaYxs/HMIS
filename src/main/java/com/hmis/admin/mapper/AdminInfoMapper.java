package com.hmis.admin.mapper;

import com.hmis.admin.dto.AdminInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminInfoMapper {

    /**
     * 管理员登录
     * @param adminInfo
     * @return
     */
    List<AdminInfo> queryCountForLogin(@Param("dto") AdminInfo adminInfo);

    int insert(AdminInfo adminInfo);

    int insertSelective(AdminInfo adminInfo);
}
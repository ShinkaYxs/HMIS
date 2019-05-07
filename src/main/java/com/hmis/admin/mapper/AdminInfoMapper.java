package com.hmis.admin.mapper;

import com.hmis.admin.dto.AdminInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminInfoMapper {

    List<AdminInfo> queryCountForLogin(@Param("dto") AdminInfo adminInfo);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);
}
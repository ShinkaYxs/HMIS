package com.hmis.login.mapper;

import com.hmis.login.dto.AdminInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminInfoMapper {

//    int queryCountForLogin(@Param("adminName") String adminName,
//                           @Param("adminPasswd") String adminPasswd);

    List<AdminInfo> queryCountForLogin(@Param("dto") AdminInfo adminInfo);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);
}
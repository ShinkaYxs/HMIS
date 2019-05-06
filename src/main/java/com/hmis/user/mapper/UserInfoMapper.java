package com.hmis.user.mapper;

import com.hmis.user.dto.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {

    List<UserInfo> queryCountForLogin(@Param("dto") UserInfo userInfo);

    int deleteByPrimaryKey(String userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> queryCountForRegister(@Param("dto") UserInfo userInfo);
}
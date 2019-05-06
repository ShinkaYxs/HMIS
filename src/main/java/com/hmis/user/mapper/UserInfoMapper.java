package com.hmis.user.mapper;

import com.hmis.user.dto.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {

    /**
     * 普通用户登录
     * @param userInfo
     * @return
     */
    List<UserInfo> queryCountForLogin(@Param("dto") UserInfo userInfo);

    int deleteByPrimaryKey(String userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    /**
     * 普通用户修改个人资料之后重新查询该人的个人资料
     * @param userId
     * @return
     */
    UserInfo selectById(String userId);

    /**
     * 普通用户-个人资料修改
     * @param record
     * @return
     */
    int updateByIdSelective(@Param("dto") UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    int insertCountForRegister(@Param("dto") UserInfo userInfo);
}
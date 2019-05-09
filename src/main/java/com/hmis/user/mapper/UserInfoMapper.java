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

    /**
     * 普通用户修改个人资料之后重新查询该人的个人资料
     * @param userNo
     * @return
     */
    UserInfo selectByNo(Integer userNo);

    /**
     * 普通用户个人资料修改
     * @param userInfo
     * @return
     */
    int updateByNoSelective(@Param("dto") UserInfo userInfo);

    /**
     * 普通用户注册
     * @param userInfo
     * @return
     */
    int insertCountForRegister(@Param("dto") UserInfo userInfo);

    /**
     * 普通用户修改密码
     * @param userInfo
     * @return
     */
    int updatePwdByNoAndOld(@Param("dto") UserInfo userInfo);

    /**
     * 查询所有普通用户信息
     * @return
     */
    List<UserInfo> userInfoQueryAll();

    /**
     * 管理员根据No删除普通用户
     * @param userNo
     * @return
     */
    int deleteUserByNo(Integer userNo);



    int insertSelective(UserInfo userInfo);

    int updateByPrimaryKey(UserInfo userInfo);
}
package com.hmis.user.service;

import com.hmis.user.dto.UserInfo;

import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/1 15:13
 */
public interface UserInfoService {

    /**
     * 普通用户登录
     * @param userInfo
     * @return
     */
    List<UserInfo> userLogin(UserInfo userInfo);
<<<<<<< HEAD
    int userRegister(UserInfo userInfo);
=======

    /**
     * 普通用户-个人资料修改
     * @param record
     * @return
     */
    int updateByIdSelective(UserInfo record);

    /**
     * 普通用户修改个人资料之后重新查询该人的个人资料
     * @param userId
     * @return
     */
    UserInfo selectById(String userId);
>>>>>>> dev
}

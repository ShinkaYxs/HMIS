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

    int userRegister(UserInfo userInfo);

    /**
     * 普通用户个人资料修改
     * @param userInfo
     * @return
     */
    int updateByNoSelective(UserInfo userInfo);

    /**
     * 普通用户修改个人资料之后重新查询该人的个人资料
     * @param userNo
     * @return
     */
    UserInfo selectByNo(Integer userNo);

    /**
     * 普通用户注册
     * @param userInfo
     * @return
     */
    //int userRegister(UserInfo userInfo);

    /**
     * 普通用户修改密码
     * @param userInfo
     * @return
     */
    int updatePwdByNoAndOld(UserInfo userInfo);

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
}

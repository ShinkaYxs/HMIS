package com.hmis.user.service;

import com.hmis.user.dto.UserInfo;

import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/1 15:13
 */
public interface UserInfoService {

    List<UserInfo> userLogin(UserInfo userInfo);
    List<UserInfo> userRegister(UserInfo userInfo);
}

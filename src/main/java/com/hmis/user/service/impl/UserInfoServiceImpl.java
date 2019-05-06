/**
 * 文件名：UserInfoServiceImpl
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.user.service.impl;

import com.hmis.user.dto.UserInfo;
import com.hmis.user.mapper.UserInfoMapper;
import com.hmis.user.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/1 15:13
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> userLogin(UserInfo userInfo) {
        return userInfoMapper.queryCountForLogin(userInfo);
    }
    @Override
    public List<UserInfo> userRegister(UserInfo userInfo) {
        return userInfoMapper.insertCountForRegister(userInfo);
    }
}

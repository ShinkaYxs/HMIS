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

    /**
     * 普通用户登录
     * @param userInfo
     * @return
     */
    @Override
    public List<UserInfo> userLogin(UserInfo userInfo) {
        return userInfoMapper.queryCountForLogin(userInfo);
    }

    /**
     * 普通用户修改个人资料之后重新查询该人的个人资料
     * @param userNo
     * @return
     */
    @Override
    public UserInfo selectByNo(Integer userNo) {
        return userInfoMapper.selectByNo(userNo);
    }

    /**
     * 普通用户个人资料修改
     * @param userInfo
     * @return
     */
    @Override
    public int updateByNoSelective(UserInfo userInfo) {
        return userInfoMapper.updateByNoSelective(userInfo);
    }

    /**
     * 普通用户注册
     * @param userInfo
     * @return
     */
    @Override
    public int userRegister(UserInfo userInfo) {
        return userInfoMapper.insertCountForRegister(userInfo);
    }

    /**
     * 普通用户修改密码
     * @param userInfo
     * @return
     */
    @Override
    public int updatePwdByNoAndOld(UserInfo userInfo) {
        return userInfoMapper.updatePwdByNoAndOld(userInfo);
    }

    /**
     * 查询所有普通用户信息
     * @return
     */
    @Override
    public List<UserInfo> userInfoQueryAll() {
        return userInfoMapper.userInfoQueryAll();
    }

    /**
     * 管理员根据No删除普通用户
     * @param userNo
     * @return
     */
    @Override
    public int deleteUserByNo(Integer userNo) {
        return userInfoMapper.deleteUserByNo(userNo);
    }
}

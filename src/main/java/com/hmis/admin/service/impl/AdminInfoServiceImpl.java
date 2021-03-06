/**
 * 文件名：AdminInfoServiceImpl
 * 描述：
 */

package com.hmis.admin.service.impl;

import com.hmis.admin.dto.AdminInfo;
import com.hmis.admin.mapper.AdminInfoMapper;
import com.hmis.admin.service.AdminInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/4/24 15:21
 */
@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    @Resource
    private AdminInfoMapper adminInfoMapper;

    /**
     * 管理员登录
     * @param adminInfo
     * @return
     */
    @Override
    public List<AdminInfo> adminLogin(AdminInfo adminInfo){
        List<AdminInfo> result = adminInfoMapper.queryCountForLogin(adminInfo);
        return result;
    }

    /**
     * 管理员修改密码
     * @param adminInfo
     * @return
     */
    @Override
    public int updatePwdByOld(AdminInfo adminInfo) {
        return adminInfoMapper.updatePwdByOld(adminInfo);
    }
}

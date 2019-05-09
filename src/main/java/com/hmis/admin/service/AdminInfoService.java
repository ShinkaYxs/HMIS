package com.hmis.admin.service;

import com.hmis.admin.dto.AdminInfo;

import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/4/24 15:19
 */
public interface AdminInfoService {

    /**
     * 管理员登录
     * @param adminInfo
     * @return
     */
    List<AdminInfo> adminLogin(AdminInfo adminInfo);

    /**
     * 管理员修改密码
     * @param adminInfo
     * @return
     */
    int updatePwdByOld(AdminInfo adminInfo);
}

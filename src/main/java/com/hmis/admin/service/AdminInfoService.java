package com.hmis.admin.service;

import com.hmis.admin.dto.AdminInfo;

import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/4/24 15:19
 */
public interface AdminInfoService {

    List<AdminInfo> adminLogin(AdminInfo adminInfo);
}

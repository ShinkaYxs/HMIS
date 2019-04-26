package com.hmis.login.service;

import com.hmis.login.dto.AdminInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xishen.yan@hand-china.com
 * @version 1.0
 * @date 2019/4/24 15:19
 */
public interface AdminInfoService {

//    int adminLogin(String adminName,String adminPasswd);
    List<AdminInfo> adminLogin(AdminInfo adminInfo);
}

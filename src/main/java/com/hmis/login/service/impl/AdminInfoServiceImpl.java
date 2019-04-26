/**
 * 文件名：AdminInfoServiceImpl
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述：
 */

package com.hmis.login.service.impl;

import com.hmis.login.dto.AdminInfo;
import com.hmis.login.mapper.AdminInfoMapper;
import com.hmis.login.service.AdminInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xishen.yan@hand-china.com
 * @version 1.0
 * @date 2019/4/24 15:21
 */
@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    @Resource
    private AdminInfoMapper adminInfoMapper;

    @Override
    public List<AdminInfo> adminLogin(AdminInfo adminInfo){
        List<AdminInfo> result = adminInfoMapper.queryCountForLogin(adminInfo);
        return result;
    }
}

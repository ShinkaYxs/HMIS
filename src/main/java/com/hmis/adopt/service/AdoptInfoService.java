package com.hmis.adopt.service;

import com.hmis.adopt.dto.AdoptInfo;

import java.util.List;


public interface AdoptInfoService {

    int adoptInsert(AdoptInfo adoptInfo);

    int updateByNoSelective(AdoptInfo record);

    AdoptInfo selectByNo(Integer adoptNo);

    List<AdoptInfo> adoptInfoQueryAll();

    int deleteAdoptByNo(Integer adoptNo);
}

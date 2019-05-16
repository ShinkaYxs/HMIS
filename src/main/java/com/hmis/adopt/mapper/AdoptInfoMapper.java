package com.hmis.adopt.mapper;

import com.hmis.adopt.dto.AdoptInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AdoptInfoMapper {



    int insert(AdoptInfo record);

    int insertSelective(AdoptInfo record);

    int updateByNoSelective(@Param("dto") AdoptInfo record);

    int insertAdopt(@Param("dto") AdoptInfo adoptInfo);

    List<AdoptInfo> adoptInfoQueryAll();

    int deleteAdoptByNo(Integer adoptNo);

    AdoptInfo selectByNo(Integer adoptNo);
}
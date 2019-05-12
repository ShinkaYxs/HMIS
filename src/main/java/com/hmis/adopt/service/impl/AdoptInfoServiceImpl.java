package com.hmis.adopt.service.impl;

import com.hmis.adopt.dto.AdoptInfo;
import com.hmis.adopt.mapper.AdoptInfoMapper;
import com.hmis.adopt.service.AdoptInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class AdoptInfoServiceImpl implements AdoptInfoService {

    @Resource
    private AdoptInfoMapper adoptInfoMapper;

    @Override
    public int adoptInsert(AdoptInfo adoptInfo) {
        return adoptInfoMapper.insertAdopt(adoptInfo);
    }

    @Override
    public AdoptInfo selectByNo(Integer adoptNo) {
        return adoptInfoMapper.selectByNo(adoptNo);
    }

    @Override
    public int updateByNoSelective(AdoptInfo adoptInfo) {
        return adoptInfoMapper.updateByNoSelective(adoptInfo);
    }

    @Override
    public List<AdoptInfo> adoptInfoQueryAll() {
        return adoptInfoMapper.adoptInfoQueryAll();
    }

    @Override
    public int deleteAdoptByNo(Integer adoptNo) {
        return adoptInfoMapper.deleteAdoptByNo(adoptNo);
    }
}

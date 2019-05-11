package com.hmis.notice.service.impl;

import com.hmis.notice.dto.NoticeInfo;
import com.hmis.notice.mapper.NoticeInfoMapper;
import com.hmis.notice.service.NoticeInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class NoticeInfoServiceImpl implements NoticeInfoService {

    @Resource
    private NoticeInfoMapper noticeInfoMapper;

    @Override
    public int noticeInsert(NoticeInfo noticeInfo) {
        return noticeInfoMapper.insertNotice(noticeInfo);
    }

    @Override
    public NoticeInfo selectByNo(Integer noticeNo) {
        return noticeInfoMapper.selectByNo(noticeNo);
    }

    @Override
    public int updateByNoSelective(NoticeInfo noticeInfo) {
        return noticeInfoMapper.updateByNoSelective(noticeInfo);
    }

//    @Override
//    public int updatePwdByNoAndOld(NoticeInfo noticeInfo) {
//        return noticeInfoMapper.updatePwdByNoAndOld(noticeInfo);
//    }

    @Override
    public List<NoticeInfo> noticeInfoQueryAll() {
        return noticeInfoMapper.noticeInfoQueryAll();
    }

    @Override
    public int deleteNoticeByNo(Integer noticeNo) {
        return noticeInfoMapper.deleteNoticeByNo(noticeNo);
    }
}

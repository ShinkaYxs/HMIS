package com.hmis.notice.service;

import com.hmis.notice.dto.NoticeInfo;

import java.util.List;


public interface NoticeInfoService {

    int noticeInsert(NoticeInfo noticeInfo);

    int updateByNoSelective(NoticeInfo record);

    NoticeInfo selectByNo(Integer noticeNo);

    //int updatePwdByNoAndOld(NoticeInfo noticeInfo);

    List<NoticeInfo> noticeInfoQueryAll();

    int deleteNoticeByNo(Integer noticeNo);
}

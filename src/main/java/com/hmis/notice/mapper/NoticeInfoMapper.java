package com.hmis.notice.mapper;

import com.hmis.notice.dto.NoticeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeInfoMapper {



    //List<NoticeInfo> queryCountForLogin(@Param("dto") NoticeInfo noticeInfo);

    int insert(NoticeInfo record);

    int insertSelective(NoticeInfo record);

    int updateByNoSelective(@Param("dto") NoticeInfo record);

    int updateByPrimaryKey(NoticeInfo record);

    int insertNotice(@Param("dto") NoticeInfo noticeInfo);

//    int updatePwdByNoAndOld(@Param("dto") NoticeInfo noticeInfo);

    List<NoticeInfo> noticeInfoQueryAll();

    int deleteNoticeByNo(Integer noticeNo);

    NoticeInfo selectByNo(Integer noticeNo);
}
package com.hmis.process.service;

import com.hmis.process.dto.CureInfo;

import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/11 17:04
 */
public interface CureInfoService {

    /**
     * 插入一条救助记录
     * @param cureInfo
     * @return
     */
    int insert(CureInfo cureInfo);

    /**
     * 查询所有救助记录
     * @return
     */
    List<CureInfo> selectCureAll();

    /**
     * 管理员删除救助记录
     * @param cureNo
     * @return
     */
    int deleteByNo(Integer cureNo);

}

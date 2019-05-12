package com.hmis.process.mapper;

import com.hmis.process.dto.CureInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CureInfoMapper {

    /**
     * 插入一条救助记录
     * @param cureInfo
     * @return
     */
    int insert(@Param("dto") CureInfo cureInfo);

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



    int insertSelective(CureInfo cureInfo);

    int updateByPrimaryKeySelective(CureInfo cureInfo);

    int updateByPrimaryKey(CureInfo cureInfo);
}
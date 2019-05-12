/**
 * 文件名：CureInfoServiceImpl
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.process.service.impl;

import com.hmis.process.dto.CureInfo;
import com.hmis.process.mapper.CureInfoMapper;
import com.hmis.process.service.CureInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/11 17:03
 */
@Service
public class CureInfoServiceImpl implements CureInfoService {

    @Resource
    private CureInfoMapper cureInfoMapper;

    /**
     * 插入一条救助记录
     * @param cureInfo
     * @return
     */
    @Override
    public int insert(CureInfo cureInfo) {
        return cureInfoMapper.insert(cureInfo);
    }

    /**
     * 查询所有救助记录
     * @return
     */
    @Override
    public List<CureInfo> selectCureAll() {
        return cureInfoMapper.selectCureAll();
    }

    /**
     * 管理员删除救助记录
     * @param cureNo
     * @return
     */
    @Override
    public int deleteByNo(Integer cureNo) {
        return cureInfoMapper.deleteByNo(cureNo);
    }
}

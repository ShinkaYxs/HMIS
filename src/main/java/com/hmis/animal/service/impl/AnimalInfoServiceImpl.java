/**
 * 文件名：AnimalInfoServiceImpl
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.animal.service.impl;

import com.hmis.admin.service.AdminInfoService;
import com.hmis.animal.dto.AnimalInfo;
import com.hmis.animal.mapper.AnimalInfoMapper;
import com.hmis.animal.service.AnimalInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/9 16:48
 */
@Service
public class AnimalInfoServiceImpl implements AnimalInfoService {

    @Resource
    private AnimalInfoMapper animalInfoMapper;

    /**
     * 查询所有动物信息
     * @return
     */
    @Override
    public List<AnimalInfo> animalInfoQueryAll() {
        return animalInfoMapper.animalInfoQueryAll();
    }

    /**
     * 动物资料修改
     * @param animalInfo
     * @return
     */
    @Override
    public int updateByNoSelective(AnimalInfo animalInfo) {
        return animalInfoMapper.updateByNoSelective(animalInfo);
    }

    /**
     * 管理员根据No删除动物信息
     * @param animalNo
     * @return
     */
    @Override
    public int deleteAnimalByNo(Integer animalNo) {
        return animalInfoMapper.deleteAnimalByNo(animalNo);
    }

    /**
     * 添加动物信息
     * @param animalInfo
     * @return
     */
    @Override
    public int animalAdd(AnimalInfo animalInfo) {
        return animalInfoMapper.animalAdd(animalInfo);
    }

    /**
     * 为下拉选择框查询某一用户的动物动物No和名称
     * @param userNo
     * @return
     */
    @Override
    public List<AnimalInfo> animalOfUserQuery(Integer userNo) {
        return animalInfoMapper.animalOfUserQuery(userNo);
    }
}

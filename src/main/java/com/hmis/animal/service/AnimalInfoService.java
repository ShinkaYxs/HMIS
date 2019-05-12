package com.hmis.animal.service;

import com.hmis.animal.dto.AnimalInfo;

import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/9 16:48
 */
public interface AnimalInfoService {

    /**
     * 查询所有动物信息
     * @return
     */
    List<AnimalInfo> animalInfoQueryAll();

    /**
     * 动物资料修改
     * @param animalInfo
     * @return
     */
    int updateByNoSelective(AnimalInfo animalInfo);

    /**
     * 管理员根据No删除动物信息
     * @param animalNo
     * @return
     */
    int deleteAnimalByNo(Integer animalNo);

    /**
     * 添加动物信息
     * @param animalInfo
     * @return
     */
    int animalAdd(AnimalInfo animalInfo);

    /**
     * 为下拉选择框查询某一用户的动物动物No和名称
     * @param animalNo
     * @return
     */
    List<AnimalInfo> animalOfUserQuery(Integer userNo);

}

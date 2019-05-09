package com.hmis.animal.mapper;

import com.hmis.animal.dto.AnimalInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnimalInfoMapper {

    /**
     * 查询所有动物信息
     * @return
     */
    List<AnimalInfo> AnimalInfoQueryAll();

    /**
     * 动物资料修改
     * @param animalInfo
     * @return
     */
    int updateByNoSelective(@Param("dto") AnimalInfo animalInfo);

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
    int animalAdd(@Param("dto") AnimalInfo animalInfo);



    int insertSelective(AnimalInfo animalInfo);

    AnimalInfo selectByPrimaryKey(Integer animalNo);

    int updateByPrimaryKey(AnimalInfo animalInfo);
}
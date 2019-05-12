package com.hmis.animal.mapper;

import com.hmis.animal.dto.AnimalInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnimalInfoMapper {

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

    /**
     * 为下拉选择框查询某一用户的动物动物No和名称
     * @param userNo
     * @return
     */
    List<AnimalInfo> animalOfUserQuery(Integer userNo);



    int insertSelective(AnimalInfo animalInfo);

    int updateByPrimaryKey(AnimalInfo animalInfo);
}
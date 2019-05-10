package com.hmis.process.mapper;

import com.hmis.process.dto.OrderInfo;

public interface OrderInfoMapper {

    /**
     * 新增挂号记录
     * @param orderInfo
     * @return
     */
    int insert(OrderInfo orderInfo);



    int deleteByPrimaryKey(Integer orderNo);

    int insertSelective(OrderInfo orderInfo);

    OrderInfo selectByPrimaryKey(Integer orderNo);

    int updateByPrimaryKeySelective(OrderInfo orderInfo);

    int updateByPrimaryKey(OrderInfo orderInfo);
}
package com.hmis.process.mapper;

import com.hmis.process.dto.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderInfoMapper {

    /**
     * 判断是否已挂号
     * @param orderInfo
     * @return
     */
    int selectCountOfExist(@Param("dto") OrderInfo orderInfo);

    /**
     * 新增挂号记录
     * @param orderInfo
     * @return
     */
    int insert(@Param("dto") OrderInfo orderInfo);

    /**
     * 获取最大的等候队列No
     * @param orderInfo
     * @return
     */
    Integer selectMaxQueueNo(@Param("dto") OrderInfo orderInfo);

    /**
     * 查看等候队列
     * @param workerNo
     * @return
     */
    List<OrderInfo> selectQueue(@Param(value="workerNo") Integer workerNo);

    /**
     * 救治完成或者过号 删除一条记录
     * @param orderNo
     * @return
     */
    int deleteByNo(Integer orderNo);




    int insertSelective(OrderInfo orderInfo);

    OrderInfo selectByPrimaryKey(Integer orderNo);

    int updateByPrimaryKeySelective(OrderInfo orderInfo);

    int updateByPrimaryKey(OrderInfo orderInfo);
}
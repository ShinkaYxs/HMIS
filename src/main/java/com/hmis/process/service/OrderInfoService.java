package com.hmis.process.service;

import com.hmis.process.dto.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/11 09:53
 */
public interface OrderInfoService {

    /**
     * 新增挂号记录
     * @param orderInfo
     * @return
     */
    int insert(OrderInfo orderInfo);

    /**
     * 获取最大的等候队列No
     * @param orderInfo
     * @return
     */
    int selectMaxQueueNo(@Param("dto") OrderInfo orderInfo);

    /**
     * 查看等候队列
     * @param workerNo
     * @return
     */
    List<OrderInfo> selectQueue(Integer workerNo);

    /**
     * 救治完成或者过号 删除一条记录
     * @param orderNo
     * @return
     */
    int deleteByNo(Integer orderNo);


}

/**
 * 文件名：OrderInfoServiceImpl
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.process.service.impl;

import com.hmis.process.dto.OrderInfo;
import com.hmis.process.mapper.OrderInfoMapper;
import com.hmis.process.service.OrderInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/11 09:54
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    /**
     * 新增挂号记录
     * @param orderInfo
     * @return
     */
    @Override
    public int insert(OrderInfo orderInfo) {
        //不等于0已存在该挂号信息，返回-1提示不能重复挂号
        return orderInfoMapper.selectCountOfExist(orderInfo)!=0 ? -1 : orderInfoMapper.insert(orderInfo);
    }

    /**
     * 获取最大的等候队列No
     * @param orderInfo
     * @return
     */
    @Override
    public int selectMaxQueueNo(OrderInfo orderInfo) {
        Integer maxQueueNo = orderInfoMapper.selectMaxQueueNo(orderInfo);
        return null==maxQueueNo ? 0 : ++maxQueueNo;
    }

    /**
     * 查看等候队列
     * @param workerNo
     * @return
     */
    @Override
    public List<OrderInfo> selectQueue(Integer workerNo) {
        return orderInfoMapper.selectQueue(workerNo);
    }

    /**
     * 救治完成或者过号 删除一条记录
     * @param orderNo
     * @return
     */
    @Override
    public int deleteByNo(Integer orderNo) {
        return orderInfoMapper.deleteByNo(orderNo);
    }
}

/**
 * 文件名：OrderInfoController
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.process.controller;

import com.hmis.process.dto.OrderInfo;
import com.hmis.process.service.OrderInfoService;
import com.hmis.tools.PojoMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/11 09:52
 */
@Controller
@RequestMapping(value = "/order")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 新增挂号记录
     * @param orderInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderAdd")
    @ResponseBody
    public PojoMsg orderAdd(@RequestBody OrderInfo orderInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        //设置在 该科室该医生下的 等候队列的号码
        orderInfo.setQueueNo(orderInfoService.selectMaxQueueNo(orderInfo));
        int orderAddResult = orderInfoService.insert(orderInfo);
        if (orderAddResult == -1){
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("不能重复挂号！");
            return pojoMsg;
        }else if (orderAddResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("挂号成功！");
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("挂号过程发生错误！");
            return pojoMsg;
        }
    }

    /**
     * 查看等候队列
     * @param workerNo
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderSelectQueue")
    @ResponseBody
    public PojoMsg selectQueue(Integer workerNo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        List<OrderInfo> orderInfoList = orderInfoService.selectQueue(workerNo);
        if (orderInfoList.size() >= 0){
            pojoMsg.setCode(0);
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("查询成功！");

            //执行成功后返回给登录页面的数据
            int count = 0;
            for(OrderInfo orderInfo_elem : orderInfoList){
                pojoMsg.add(String.valueOf(count++),orderInfo_elem);
            }
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("查询过程未知错误！");
            return pojoMsg;
        }
    }

    /**
     * 救治完成或者过号 删除一条记录
     * @param orderNo
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderDeleteByNo")
    @ResponseBody
    public PojoMsg orderDeleteByNo(Integer orderNo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int orderDeleteByNoResult = orderInfoService.deleteByNo(orderNo);
        if (orderDeleteByNoResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("删除成功！");
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("队列中不存在该信息或发生其他错误！");
            return pojoMsg;
        }
    }


}

/**
 * 文件名：WorkerInfoController
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.worker.controller;

import com.alibaba.fastjson.JSONObject;
import com.hmis.tools.GetRequestJsonUtils;
import com.hmis.tools.PojoMsg;
import com.hmis.worker.dto.WorkerInfo;
import com.hmis.worker.service.WorkerInfoService;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/1 00:13
 */
@Controller
@RequestMapping(value = "/worker")
public class WorkerInfoController {

    @Autowired
    private WorkerInfoService workerInfoService;

    /**
     * 工作人员登录
     * @param workerInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/workerLogin")
    @ResponseBody
    public PojoMsg workerLogin(@RequestBody WorkerInfo workerInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        //判断验证码是否正确
        if (!CaptchaUtil.ver(workerInfo.getCode(), request)) {
            CaptchaUtil.clear(request);
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("验证码错误！");
            return pojoMsg;
        }

        List<WorkerInfo> workerInfoList = workerInfoService.workerLogin(workerInfo);
        if (workerInfoList.size() == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("登录成功！");

            //将用户除密码外的所有信息放入session中
            HttpSession session = request.getSession();
            session.setAttribute("workerInfo",workerInfoList.get(0));

            //执行成功后返回给登录页面的数据，实际上拿到这些数据也不用，所以不放入这些信息也行
            int count = 0;
            for(WorkerInfo workerInfo_elem : workerInfoList){
                pojoMsg.add(String.valueOf(count++),workerInfo_elem);
            }
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("用户名或密码错误！");
            return pojoMsg;
        }
    }

    /**
     * 工作人员-个人资料修改
     * @param workerInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/workerChange")
    @ResponseBody
    public PojoMsg workerChange(@RequestBody WorkerInfo workerInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int workerChangeResult = workerInfoService.updateByNoSelective(workerInfo);
        if (workerChangeResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("登录成功！");

            //重新查询一遍工作人员的信息
            WorkerInfo workerInfoSelectByNo = workerInfoService.selectByNo(workerInfo.getWorkerNo());

            //将除密码外的所有信息放入session中
            HttpSession session = request.getSession();
            session.setAttribute("workerInfo",workerInfoSelectByNo);

            //执行成功后返回给页面的数据，实际上拿到这些数据也不用，所以不放入这些信息也行
            pojoMsg.add(String.valueOf(0),workerInfoSelectByNo);
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("更新信息时发生错误！");
            return pojoMsg;
        }
    }

    /**
     * 工作人员-修改密码
     * @param workerInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/workerChangePwd")
    @ResponseBody
    public PojoMsg workerChangePwd(@RequestBody WorkerInfo workerInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();

        int workerChangeResult = workerInfoService.updateByNoSelective(workerInfo);
        if (workerChangeResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("登录成功！");

            //重新查询一遍工作人员的信息
            WorkerInfo workerInfoSelectById = workerInfoService.selectByNo(workerInfo.getWorkerNo());

            //将除密码外的所有信息放入session中
            HttpSession session = request.getSession();
            session.setAttribute("workerInfo",workerInfoSelectById);

            //执行成功后返回给页面的数据，实际上拿到这些数据也不用，所以不放入这些信息也行
            pojoMsg.add(String.valueOf(0),workerInfoSelectById);
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("更新信息时发生错误！");
            return pojoMsg;
        }
    }


}

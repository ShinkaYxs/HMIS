/**
 * 文件名：WorkerInfoController
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.worker.controller;

import com.hmis.tools.PojoMsg;
import com.hmis.worker.dto.WorkerInfo;
import com.hmis.worker.service.WorkerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = "/workerLogin")
    public PojoMsg workerLogin(@RequestBody WorkerInfo workerInfo, HttpServletRequest request){
        List<WorkerInfo> workerInfoList = workerInfoService.workerLogin(workerInfo);
        PojoMsg pojoMsg = new PojoMsg();
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
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("用户名或密码错误！");
            return pojoMsg;
        }
    }
}

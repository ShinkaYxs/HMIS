/**
 * 文件名：WorkerInfoController
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.worker.controller;

import com.hmis.tools.PojoMsg;
import com.hmis.tools.UploadUtil;
import com.hmis.worker.dto.WorkerInfo;
import com.hmis.worker.service.WorkerInfoService;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 工作人员个人资料修改
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
            pojoMsg.setMsg("修改成功！");

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
     * 工作人员上传头像
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/workerUploadHeadPortrait")
    @ResponseBody
    public Map<String,Object> workerUploadHeadPortrait(@RequestParam MultipartFile file, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        WorkerInfo workerInfo = new WorkerInfo();
        String workerNoStr = request.getParameter("workerNo");
        if(workerNoStr!=null){
            workerInfo.setWorkerNo(Integer.valueOf(workerNoStr));
        }

        try {
            //获取的是target对应的头像图片资源存放路径
            String path = request.getSession().getServletContext().getRealPath("\\images\\headPortrait\\");
            String imageName = UploadUtil.uploadFile(file, path);
            //更新数据库中的用户头像路径
            String newHeadPortrait = "/images/headPortrait/"+imageName;
            workerInfo.setHeadPortrait(newHeadPortrait);
            int workerChangeResult = workerInfoService.updateByNoSelective(workerInfo);
            if (workerChangeResult == 1){
                //更新session中的用户头像路径
                HttpSession session = request.getSession();
                workerInfo = (WorkerInfo) session.getAttribute("workerInfo");
                workerInfo.setHeadPortrait(newHeadPortrait);
                session.setAttribute("workerInfo", workerInfo);
                //设置返回数据
                map.put("code",0);
                map.put("imageName",imageName);
            }else {
                map.put("code",1);
            }
        }catch (Exception e){
            map.put("code",2);
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 工作人员修改密码
     * @param workerInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/workerChangePwd")
    @ResponseBody
    public PojoMsg workerChangePwd(@RequestBody WorkerInfo workerInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int workerChangePwdResult = workerInfoService.updatePwdByNoAndOld(workerInfo);
        if (workerChangePwdResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("修改成功！");
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("旧密码输入错误！");
            return pojoMsg;
        }
    }

    /**
     * 查询所有工作人员信息
//     * @param workerInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/workerInfoQueryAll")
    @ResponseBody
    public PojoMsg workerInfoQueryAll(HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        List<WorkerInfo> workerInfoList = workerInfoService.workerInfoQueryAll();
        if (workerInfoList.size() >= 0){
            pojoMsg.setCode(0);
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("查询成功！");

            //执行成功后返回给登录页面的数据
            int count = 0;
            for(WorkerInfo workerInfo_elem : workerInfoList){
                pojoMsg.add(String.valueOf(count++),workerInfo_elem);
            }
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("查询过程未知错误！");
            return pojoMsg;
        }
    }

    /**
     * 管理员根据工号删除工作人员
     * @param workerNo
     * @param request
     * @return
     */
    @RequestMapping(value = "/workerDeleteByNo")
    @ResponseBody
    public PojoMsg workerDeleteByNo(Integer workerNo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int workerDeleteByNoResult = workerInfoService.deleteWorkerByNo(workerNo);
        if (workerDeleteByNoResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("删除成功！");
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("员工不存在或发生其他错误！");
            return pojoMsg;
        }
    }

    /**
     * 管理员添加工作人员信息
     * @param workerInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/workerAdd")
    @ResponseBody
    public PojoMsg workerAdd(@RequestBody WorkerInfo workerInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        workerInfo.setWorkerPwd("111111");     //初始密码设为111111
        int workerAddResult = workerInfoService.workerAdd(workerInfo);
        if (workerAddResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("添加成功！");
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("添加过程发生错误！");
            return pojoMsg;
        }
    }

    /**
     * 管理员根据科室No查询该科室下的工作人员
     * @param deptNo
     * @param request
     * @return
     */
    @RequestMapping(value = "/workerQueryByDeptNo")
    @ResponseBody
    public PojoMsg workerQueryByDeptNo(Integer deptNo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        List<WorkerInfo> workerInfoList = workerInfoService.workerQueryByDeptNo(deptNo);
        if (workerInfoList.size() >= 0){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("查询成功！");

            //执行成功后返回给登录页面的数据
            int count = 0;
            for(WorkerInfo workerInfo_elem : workerInfoList){
                pojoMsg.add(String.valueOf(count++),workerInfo_elem);
            }
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("查询过程错误！");
            return pojoMsg;
        }
    }

}

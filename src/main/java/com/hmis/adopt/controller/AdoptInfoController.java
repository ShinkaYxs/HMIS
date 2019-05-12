package com.hmis.adopt.controller;

import com.hmis.adopt.dto.AdoptInfo;
import com.hmis.adopt.service.AdoptInfoService;
import com.hmis.tools.PojoMsg;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping(value = "/adopt")
public class AdoptInfoController {

    @Autowired
    private AdoptInfoService adoptInfoService;

    @RequestMapping(value = "/adoptInsert")
    @ResponseBody
    public PojoMsg adoptInsert(@RequestBody AdoptInfo adoptInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();

        int adoptInfoList = adoptInfoService.adoptInsert(adoptInfo);
        if (adoptInfoList == 1){

            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("发布成功！");


            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("发布失败！");
            return pojoMsg;
        }
    }
    @RequestMapping(value = "/adoptChange")
    @ResponseBody
    public PojoMsg adoptChange(@RequestBody AdoptInfo adoptInfo, HttpServletRequest request) {
        PojoMsg pojoMsg = new PojoMsg();
        int adoptChangeResult = adoptInfoService.updateByNoSelective(adoptInfo);
        if (adoptChangeResult == 1) {
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("修改成功！");

            AdoptInfo adoptInfoselectByNo = adoptInfoService.selectByNo(adoptInfo.getAdoptNo());

            HttpSession session = request.getSession();
            session.setAttribute("adoptInfo", adoptInfoselectByNo);

            //执行成功后返回给登录页面的数据，实际上拿到这些数据也不用，所以不放入这些信息也行
            pojoMsg.add(String.valueOf(0), adoptInfoselectByNo);
            return pojoMsg;
        } else {
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("更新信息时发生错误！");
            return pojoMsg;
        }
    }

    @RequestMapping(value = "/adoptInfoQueryAll")
    @ResponseBody
    public PojoMsg adoptInfoQueryAll(HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        List<AdoptInfo> adoptInfoList = adoptInfoService.adoptInfoQueryAll();
        if (adoptInfoList.size() >= 0){
            pojoMsg.setCode(0);
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("查询成功！");

            int count = 0;
            for(AdoptInfo adoptInfo_elem : adoptInfoList){
                pojoMsg.add(String.valueOf(count++),adoptInfo_elem);
            }
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("查询过程未知错误！");
            return pojoMsg;
        }
    }
    @RequestMapping(value = "/adoptDeleteByNo")
    @ResponseBody
    public PojoMsg adoptDeleteByNo (Integer adoptNo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int adoptDeleteByNoResult = adoptInfoService.deleteAdoptByNo(adoptNo);
        if (adoptDeleteByNoResult == 1) {
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("删除成功！");
            return pojoMsg;
        } else {
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("员工不存在或发生其他错误！");
            return pojoMsg;
        }
    }

}

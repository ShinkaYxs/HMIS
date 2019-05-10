package com.hmis.notice.controller;

import com.hmis.notice.dto.NoticeInfo;
import com.hmis.notice.service.NoticeInfoService;
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
@RequestMapping(value = "/notice")
public class NoticeInfoController {

    @Autowired
    private NoticeInfoService noticeInfoService;

    @RequestMapping(value = "/noticeInsert")
    @ResponseBody
    public PojoMsg noticeInsert(@RequestBody NoticeInfo noticeInfo,HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();

        int noticeInfoList = noticeInfoService.noticeInsert(noticeInfo);
        if (noticeInfoList == 1){

            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("发布成功！");

            //将用户除密码外的所有信息放入session中

            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("发布失败！");
            return pojoMsg;
        }
    }

}

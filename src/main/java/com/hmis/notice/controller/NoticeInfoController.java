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
    @RequestMapping(value = "/noticeChange")
    @ResponseBody
    public PojoMsg noticeChange(@RequestBody NoticeInfo noticeInfo, HttpServletRequest request) {
        PojoMsg pojoMsg = new PojoMsg();
        int noticeChangeResult = noticeInfoService.updateByNoSelective(noticeInfo);
        if (noticeChangeResult == 1) {
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("修改成功！");

            //重新查询一遍普通用户的信息
            NoticeInfo noticeInfoselectByNo = noticeInfoService.selectByNo(noticeInfo.getNoticeNo());

            //将用户除密码外的所有信息放入session中
            HttpSession session = request.getSession();
            session.setAttribute("noticeInfo", noticeInfoselectByNo);

            //执行成功后返回给登录页面的数据，实际上拿到这些数据也不用，所以不放入这些信息也行
            pojoMsg.add(String.valueOf(0), noticeInfoselectByNo);
            return pojoMsg;
        } else {
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("更新信息时发生错误！");
            return pojoMsg;
        }
    }

    /**
     * 查询所有
     * //     * @param noticeInfo
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/noticeInfoQueryAll")
    @ResponseBody
    public PojoMsg noticeInfoQueryAll(HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        List<NoticeInfo> noticeInfoList = noticeInfoService.noticeInfoQueryAll();
        if (noticeInfoList.size() >= 0){
            pojoMsg.setCode(0);
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("查询成功！");

            //执行成功后返回给登录页面的数据
            int count = 0;
            for(NoticeInfo noticeInfo_elem : noticeInfoList){
                pojoMsg.add(String.valueOf(count++),noticeInfo_elem);
            }
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("查询过程未知错误！");
            return pojoMsg;
        }
    }
    /**
     * 管理员根据No删除
     * @param noticeNo
     * @param request
     * @return
     */
    @RequestMapping(value = "/noticeDeleteByNo")
    @ResponseBody
    public PojoMsg noticeDeleteByNo (Integer noticeNo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int noticeDeleteByNoResult = noticeInfoService.deleteNoticeByNo(noticeNo);
        if (noticeDeleteByNoResult == 1) {
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

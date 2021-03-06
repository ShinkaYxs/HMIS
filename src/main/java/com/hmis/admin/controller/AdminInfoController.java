/**
 * 文件名：AdminInfoController
 * 描述：管理员登录
 */

package com.hmis.admin.controller;

import com.hmis.admin.dto.AdminInfo;
import com.hmis.admin.service.AdminInfoService;
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

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/4/24 14:50
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminInfoController {

    @Autowired
    private AdminInfoService adminInfoService;

    /**
     * 管理员登录校验
     * @param adminInfo 传入的Json格式数据，用户名 密码 验证码
     * @return pojoMsg  返回Json格式数据
     */
    @RequestMapping(value = "/adminLogin")
    @ResponseBody
    public PojoMsg adminLogin(@RequestBody AdminInfo adminInfo,HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        //判断验证码是否正确
        if (!CaptchaUtil.ver(adminInfo.getCode(), request)) {
            CaptchaUtil.clear(request);
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("验证码错误！");
            return pojoMsg;
        }

        List<AdminInfo> adminInfoList = adminInfoService.adminLogin(adminInfo);
        if (adminInfoList.size() == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("登录成功！");

            //将用户名放入session中，在登录后的主页面可以从session中获取，显示在右上角
            HttpSession session = request.getSession();
            String userName = adminInfoList.get(0).getUserName();
            session.setAttribute("userName",userName);

            //执行成功后返回给登录页面的数据，实际上拿到这些数据也不用，所以不放入这些信息也行
            int count = 0;
            for(AdminInfo adminInfo_elem : adminInfoList){
                pojoMsg.add(String.valueOf(count++),adminInfo_elem);
            }
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("用户名或密码错误！");
            return pojoMsg;
        }
    }

    /**
     * 管理员修改密码
     * @param adminInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/adminChangePwd")
    @ResponseBody
    public PojoMsg adminChangePwd(@RequestBody AdminInfo adminInfo,HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int adminChangePwdResult = adminInfoService.updatePwdByOld(adminInfo);
        if (adminChangePwdResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("修改成功！");
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("旧密码输入错误！");
            return pojoMsg;
        }
    }

}

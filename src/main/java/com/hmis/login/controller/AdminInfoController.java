/**
 * 文件名：AdminInfoController
 * 描述：管理员登录
 */

package com.hmis.login.controller;

import com.hmis.login.dto.AdminInfo;
import com.hmis.login.service.AdminInfoService;
import com.hmis.tools.PojoMsg;
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
public class AdminInfoController {

    @Autowired
    private AdminInfoService adminInfoService;

    @RequestMapping(value = "/adminLogin")
    @ResponseBody
    public PojoMsg adminLogin(@RequestBody AdminInfo adminInfo){
        List<AdminInfo> resultAdminInfo = adminInfoService.adminLogin(adminInfo);
        PojoMsg pojoMsg = new PojoMsg();
        if (resultAdminInfo.size() == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("登录成功！");
            int count = 0;
            for(AdminInfo adminInfo1:resultAdminInfo){
                pojoMsg.add(String.valueOf(count++),adminInfo1);
            }
            return pojoMsg;

//            HttpSession session=request.getSession();
//            session.setAttribute("resultAdminInfo", resultAdminInfo);
//            return "views/admin/main.jsp";
        }else{
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("用户名或密码错误！");
            return pojoMsg;

//            request.setAttribute("adminInfo", adminInfo);
//            request.setAttribute("errorMsg", "用户名或密码错误！");
//            return "redirect:/login.jsp";
        }
    }

}

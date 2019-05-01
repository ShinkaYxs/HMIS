/**
 * 文件名：UserInfoController
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.user.controller;

import com.hmis.tools.PojoMsg;
import com.hmis.user.dto.UserInfo;
import com.hmis.user.service.UserInfoService;
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
 * @date 2019/5/1 15:19
 */
@Controller
@RequestMapping(value = "/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/userLogin")
    @ResponseBody
    public PojoMsg workerLogin(@RequestBody UserInfo userInfo, HttpServletRequest request){
        List<UserInfo> userInfoList = userInfoService.userLogin(userInfo);
        PojoMsg pojoMsg = new PojoMsg();
        if (userInfoList.size() == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("登录成功！");

            //将用户除密码外的所有信息放入session中
            HttpSession session = request.getSession();
            session.setAttribute("userInfo",userInfoList.get(0));

            //执行成功后返回给登录页面的数据，实际上拿到这些数据也不用，所以不放入这些信息也行
            int count = 0;
            for(UserInfo userInfo_elem : userInfoList){
                pojoMsg.add(String.valueOf(count++),userInfo_elem);
            }
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("用户名或密码错误！");
            return pojoMsg;
        }
    }

}




/**
 * 文件名：UserInfoController
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.user.controller;

import com.hmis.tools.PojoMsg;
import com.hmis.user.dto.UserInfo;
import com.hmis.user.service.UserInfoService;
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
 * @date 2019/5/1 15:19
 */
@Controller
@RequestMapping(value = "/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 普通用户登录
     * @param userInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/userLogin")
    @ResponseBody
    public PojoMsg userLogin(@RequestBody UserInfo userInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        //判断验证码是否正确
        if (!CaptchaUtil.ver(userInfo.getCode(), request)) {
            CaptchaUtil.clear(request);
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("验证码错误！");
            return pojoMsg;
        }

        List<UserInfo> userInfoList = userInfoService.userLogin(userInfo);
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
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("用户名或密码错误！");
            return pojoMsg;
        }
    }

    /**
     * 普通用户个人资料修改
     * @param userInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/userChange")
    @ResponseBody
    public PojoMsg userChange(@RequestBody UserInfo userInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int userChangeResult = userInfoService.updateByIdSelective(userInfo);
        if (userChangeResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("修改成功！");

            //重新查询一遍普通用户的信息
            UserInfo userInfoselectByNo = userInfoService.selectByNo(userInfo.getUserNo());

            //将用户除密码外的所有信息放入session中
            HttpSession session = request.getSession();
            session.setAttribute("userInfo",userInfoselectByNo);

            //执行成功后返回给登录页面的数据，实际上拿到这些数据也不用，所以不放入这些信息也行
            pojoMsg.add(String.valueOf(0),userInfoselectByNo);
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("更新信息时发生错误！");
            return pojoMsg;
        }
    }

    /**
     * 普通用户注册
     * @param userInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/userRegister")
    @ResponseBody
    public PojoMsg userRegister(@RequestBody UserInfo userInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        if(null == userInfo.getUserPwd()){
            userInfo.setUserPwd("111111");
        }else{
            if (!CaptchaUtil.ver(userInfo.getCode(), request)) {
                CaptchaUtil.clear(request);
                pojoMsg.setSuccess(false);
                pojoMsg.setMsg("验证码错误！");
                return pojoMsg;
            }
        }

        int userInfoList = userInfoService.userRegister(userInfo);
        if (userInfoList == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("注册成功！");
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("用户注册失败请重试！");
            return pojoMsg;
        }
    }

    /**
     * 普通用户修改密码
     * @param userInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/userChangePwd")
    @ResponseBody
    public PojoMsg userChangePwd(@RequestBody UserInfo userInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int userChangePwdResult = userInfoService.updatePwdByNoAndOld(userInfo);
        if (userChangePwdResult == 1){
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
     * 查询所有普通用户信息
     //     * @param userInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/userInfoQueryAll")
    @ResponseBody
    public PojoMsg userInfoQueryAll(HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        List<UserInfo> userInfoList = userInfoService.userInfoQueryAll();
        if (userInfoList.size() >= 0){
            pojoMsg.setCode(0);
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("查询成功！");

            //执行成功后返回给登录页面的数据
            int count = 0;
            for(UserInfo userInfo_elem : userInfoList){
                pojoMsg.add(String.valueOf(count++),userInfo_elem);
            }
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("查询过程未知错误！");
            return pojoMsg;
        }
    }

    /**
     * 管理员根据No删除普通用户
     * @param userNo
     * @param request
     * @return
     */
    @RequestMapping(value = "/userDeleteByNo")
    @ResponseBody
    public PojoMsg userDeleteByNo(Integer userNo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int userDeleteByNoResult = userInfoService.deleteUserByNo(userNo);
        if (userDeleteByNoResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("删除成功！");
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("员工不存在或发生其他错误！");
            return pojoMsg;
        }
    }

}




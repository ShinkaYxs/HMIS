/**
 * 文件名：PageController
 * 描述：控制页面跳转的Controller
 */

package com.hmis.tools;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/4/28 10:29
 */
@RequestMapping("/page")
@Controller
public class PageController {

    @RequestMapping("/views/admin/{pageName}")
    public String toAdminPage(@PathVariable("pageName") String pageName){
        return "views/admin/" + pageName;
    }

    @RequestMapping("/views/worker/{pageName}")
    public String toWorkerPage(@PathVariable("pageName") String pageName){
        return "views/worker/" + pageName;
    }

    @RequestMapping("/views/user/{pageName}")
    public String toUserPage(@PathVariable("pageName") String pageName){
        return "views/user/" + pageName;
    }
}


//@RequestMapping("/page")
//@controller
//public class PageController {
//    //    @RequestMapping("/views/worker/{pageName}")
//    @RequestMapping(value = "/views/worker/main")
//    public void toPage(@PathVariable("pageName") String pageName, HttpServletRequest request, HttpServletResponse response) throws Exception{
//        request.getRequestDispatcher(pageName).forward(request,response);
//    }
//}

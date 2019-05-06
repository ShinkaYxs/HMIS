/**
 * 文件名：CaptchaController
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.tools;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/1 17:57
 */
@Controller
@RequestMapping(value = "/captcha")
public class CaptchaController {

    /**
     * 输出验证码到前端
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/imagesOutToPage")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }

}

/**
 * 文件名：CureInfoController
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.process.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hmis.process.dto.CureInfo;
import com.hmis.process.service.CureInfoService;
import com.hmis.process.service.OrderInfoService;
import com.hmis.tools.GetRequestJsonUtils;
import com.hmis.tools.PojoMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/11 17:05
 */
@Controller
@RequestMapping(value = "/cure")
public class CureInfoController {

    @Autowired
    private CureInfoService cureInfoService;

    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 这是一个给三三解决bug时候的测试，跟本项目无关
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/jsonTest")
    public void test(HttpServletRequest request) throws Exception{
        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);
//        String s = GetRequestJsonUtils.getRequestJsonString(request);

        JSONObject jsonObject = json.getJSONObject("student_list");
        System.out.println(jsonObject);
        JSONArray studentListArray = jsonObject.getJSONArray("0");
        System.out.println(studentListArray);


        JSONArray testArray = json.getJSONArray("testArray");
        System.out.println(testArray);
        System.out.println(testArray.getString(1));
    }

    /**
     * 新增救治记录
     * @param cureInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/cureAdd")
    @ResponseBody
    public PojoMsg cureAdd(@RequestBody CureInfo cureInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int cureAddResult = cureInfoService.insert(cureInfo);
        if (cureAddResult == 1){
            int orderDeleteByNoResult = orderInfoService.deleteByNo(cureInfo.getOrderNo());
            if(orderDeleteByNoResult == 1){
                pojoMsg.setSuccess(true);
                pojoMsg.setMsg("新增救助记录、删除队列记录成功！");
                return pojoMsg;
            }else{
                pojoMsg.setSuccess(false);
                pojoMsg.setMsg("新增救助记录成功、删除队列记录失败！");
                return pojoMsg;
            }
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("新增救助记录过程发生错误！");
            return pojoMsg;
        }
    }

    /**
     * 查询所有救助记录
     * @param request
     * @return
     */
    @RequestMapping(value = "/cureSelectAll")
    @ResponseBody
    public PojoMsg cureSelectAll(HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        List<CureInfo> cureInfoList = cureInfoService.selectCureAll();
        if (cureInfoList.size() >= 0){
            pojoMsg.setCode(0);
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("查询成功！");

            //执行成功后返回给登录页面的数据
            int count = 0;
            for(CureInfo cureInfo_elem : cureInfoList){
                pojoMsg.add(String.valueOf(count++),cureInfo_elem);
            }
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("查询过程未知错误！");
            return pojoMsg;
        }
    }

    /**
     * 管理员删除救助记录
     * @param cureNo
     * @param request
     * @return
     */
    @RequestMapping(value = "/cureDeleteByNo")
    @ResponseBody
    public PojoMsg orderDeleteByNo(Integer cureNo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int curerDeleteByNoResult = cureInfoService.deleteByNo(cureNo);
        if (curerDeleteByNoResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("删除成功！");
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("队列中不存在该信息或发生其他错误！");
            return pojoMsg;
        }
    }



}

/**
 * 文件名：DepartmentInfoController
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.dept.controller;

import com.hmis.dept.dto.DepartmentInfo;
import com.hmis.dept.service.DepartmentInfoService;
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
 * @date 2019/5/10 20:44
 */
@Controller
@RequestMapping(value = "/department")
public class DepartmentInfoController {

    @Autowired
    private DepartmentInfoService departmentInfoService;

    /**
     * 查询所有科室信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/deptInfoQueryAll")
    @ResponseBody
    public PojoMsg departmentInfoQueryAll(HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        List<DepartmentInfo> deptInfoList = departmentInfoService.deptInfoQueryAll();
        if (deptInfoList.size() >= 0){
            pojoMsg.setCode(0);
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("查询成功！");

            //执行成功后返回给登录页面的数据
            int count = 0;
            for(DepartmentInfo deptInfo_elem : deptInfoList){
                pojoMsg.add(String.valueOf(count++),deptInfo_elem);
            }
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("查询过程未知错误！");
            return pojoMsg;
        }
    }

    /**
     * 动物资料修改
     * @param departmentInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/deptChange")
    @ResponseBody
    public PojoMsg deptChange(@RequestBody DepartmentInfo departmentInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int deptChangeResult = departmentInfoService.updateByNoSelective(departmentInfo);
        if (deptChangeResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("修改成功！");
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("更新信息时发生错误！");
            return pojoMsg;
        }
    }

    /**
     * 管理员根据No删除动物信息
     * @param deptNo
     * @param request
     * @return
     */
    @RequestMapping(value = "/deptDeleteByNo")
    @ResponseBody
    public PojoMsg deptDeleteByNo(Integer deptNo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int deptDeleteByNoResult = departmentInfoService.deleteDeptByNo(deptNo);
        if (deptDeleteByNoResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("删除成功！");
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("动物不存在或发生其他错误！");
            return pojoMsg;
        }
    }

    /**
     * 管理员添加动物信息
     * @param departmentInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/deptAdd")
    @ResponseBody
    public PojoMsg deptAdd(@RequestBody DepartmentInfo departmentInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int deptAddResult = departmentInfoService.deptAdd(departmentInfo);
        if (deptAddResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("添加成功！");
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("添加过程发生错误！");
            return pojoMsg;
        }
    }

}

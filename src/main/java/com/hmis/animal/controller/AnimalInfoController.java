/**
 * 文件名：AnimalInfoController
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.animal.controller;

import com.hmis.animal.dto.AnimalInfo;
import com.hmis.animal.service.AnimalInfoService;
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
 * @date 2019/5/9 16:47
 */
@Controller
@RequestMapping(value = "/animal")
public class AnimalInfoController {

    @Autowired
    private AnimalInfoService animalInfoService;

    /**
     * 查询所有动物信息
     //     * @param workerInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/animalInfoQueryAll")
    @ResponseBody
    public PojoMsg animalInfoQueryAll(HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        List<AnimalInfo> animalInfoList = animalInfoService.animalInfoQueryAll();
        if (animalInfoList.size() >= 0){
            pojoMsg.setCode(0);
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("查询成功！");

            //执行成功后返回给登录页面的数据
            int count = 0;
            for(AnimalInfo animalInfo_elem : animalInfoList){
                pojoMsg.add(String.valueOf(count++),animalInfo_elem);
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
     * @param animalInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/animalChange")
    @ResponseBody
    public PojoMsg animalChange(@RequestBody AnimalInfo animalInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int animalChangeResult = animalInfoService.updateByNoSelective(animalInfo);
        if (animalChangeResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("修改成功！");

//            //重新查询一遍动物的信息
//            AnimalInfo workerInfoSelectByNo = animalInfoService.selectByNo(animalInfo.getWorkerNo());

//            //将除密码外的所有信息放入session中
//            HttpSession session = request.getSession();
//            session.setAttribute("workerInfo",workerInfoSelectByNo);

//            //执行成功后返回给页面的数据，实际上拿到这些数据也不用，所以不放入这些信息也行
//            pojoMsg.add(String.valueOf(0),workerInfoSelectByNo);
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("更新信息时发生错误！");
            return pojoMsg;
        }
    }

    /**
     * 管理员根据No删除动物信息
     * @param animalNo
     * @param request
     * @return
     */
    @RequestMapping(value = "/animalDeleteByNo")
    @ResponseBody
    public PojoMsg animalDeleteByNo(Integer animalNo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int animalDeleteByNoResult = animalInfoService.deleteAnimalByNo(animalNo);
        if (animalDeleteByNoResult == 1){
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
     * @param animalInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/animalAdd")
    @ResponseBody
    public PojoMsg animalAdd(@RequestBody AnimalInfo animalInfo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        int animalAddResult = animalInfoService.animalAdd(animalInfo);
        if (animalAddResult == 1){
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("添加成功！");
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("添加过程发生错误！");
            return pojoMsg;
        }
    }

    /**
     * 为下拉选择框查询某一用户的动物动物No和名称
     * @param userNo
     * @param request
     * @return
     */
    @RequestMapping(value = "/animalOfUserQuery")
    @ResponseBody
    public PojoMsg animalOfUserQuery(Integer userNo, HttpServletRequest request){
        PojoMsg pojoMsg = new PojoMsg();
        List<AnimalInfo> animalInfoList = animalInfoService.animalOfUserQuery(userNo);
        if (animalInfoList.size() >= 0){
            pojoMsg.setCode(0);
            pojoMsg.setSuccess(true);
            pojoMsg.setMsg("查询成功！");

            //执行成功后返回给登录页面的数据
            int count = 0;
            for(AnimalInfo animalInfo_elem : animalInfoList){
                pojoMsg.add(String.valueOf(count++),animalInfo_elem);
            }
            return pojoMsg;
        }else{
            pojoMsg.setSuccess(false);
            pojoMsg.setMsg("查询过程未知错误！");
            return pojoMsg;
        }
    }
}

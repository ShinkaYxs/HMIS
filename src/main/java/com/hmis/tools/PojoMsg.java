/**
 * 文件名：PojoMsg
 * 描述：用于返回数据的Pojo
 */

package com.hmis.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/4/24 20:04
 */
public class PojoMsg {
    private boolean success;    //返回100表示成功,200表示失败
    private String msg;    //返回提示信息
//    private Map<String,Object> extend = new HashMap<String,Object>();    //用户返回给浏览器的数据
    private Map<String,Object> data = new HashMap<String,Object>();    //用户返回给浏览器的数据

    public PojoMsg success() {
        this.setSuccess(true);
        this.setMsg("成功!");
        return this;
    }
    public PojoMsg fail() {
        setSuccess(false);
        setMsg("失败!");
        return this;
    }

    public PojoMsg add(String key,Object value) {
//        this.getExtend().put(key, value);
        this.getData().put(key, value);
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

//    public Map<String, Object> getExtend() {
//        return extend;
//    }
//
//    public void setExtend(Map<String, Object> extend) {
//        this.extend = extend;
//    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }



    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}

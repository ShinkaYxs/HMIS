<%--
  Created by IntelliJ IDEA.
  User: kongxiaoxiao
  Date: 2019/5/12
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>发布收养寄养信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="/css/login/login.css" media="all" />
</head>
<body>
<video class="video-player" preload="auto" autoplay="autoplay" loop="loop">
    <source src="/images/video/adoptAdd.mp4" type="video/mp4">
</video>
<div class="video_mask"></div>
<!-- 		用户注册需要填写的信息 -->
<div class="login" style="height:520px;width:500px;padding: 20px;background-color:rgba(0,0,0,0.5);border-radius: 4px;position:absolute;left: 50%;top: 50%; margin:-220px 0 0 -240px;z-index:99;">
    <h1>发布收养寄养信息</h1>
    <form id="formId" class="layui-form">
        <div class="layui-form-item">
            <input name="userNo" class="layui-input" placeholder="用户编号" lay-verify="required|number" type="text" autocomplete="off" style="padding-left: 40px;">
        </div>
        <div class="layui-form-item">
            <input name="animalNo" class="layui-input" placeholder="动物编号" lay-verify="required|number" type="text" autocomplete="off" style="padding-left: 40px;">
        </div>
        <div class="layui-form-item layui-inline">
            <div class="layui-input-inline">
                <input type="text" name="operationTime" class="layui-input" id="operationTime" placeholder="时间">
            </div>
        </div>
        <div class="layui-form-item" style="color: #fff">
            <label class="layui-form-label" style="text-align:center;font-weight: bold;padding: 10px 0px">操作类型</label>
            <div class="layui-input-block" style="margin-left: 60px;margin-right: 0px;!important">
                <input type="radio" name="operationType" value="收养" title="收养" checked>
                <input type="radio" name="operationType" value="寄养" title="寄养" >
            </div>
        </div>
        <button id="InsertAdoptButton" class="layui-btn login_btn" type="submit" lay-submit="" lay-filter="InsertAdoptButton">发布</button>
    </form>
</div>
</body>

<script type="text/javascript" src="/layui/layui.js"></script>

<script>


    layui.use(['form', 'laydate'], function() {
        //var $ = layui.$;
        var laydate = layui.laydate;
        var nowTime = new Date().valueOf();
        var max = null;
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : parent.layer;
        $ = layui.jquery;
        var start = laydate.render({
            elem: '#operationTime',
            // type: 'datetime',
            max: nowTime,
            btns: ['clear', 'confirm'],
            done: function (value, date) {
                endMax = end.config.max;
                end.config.min = date;
                end.config.min.month = date.month - 1;
            }
        });
        var end = laydate.render({
            elem: '#end_time',
            type: 'datetime',
            max: nowTime,
            done: function (value, date) {
                if ($.trim(value) == '') {
                    var curDate = new Date();
                    date = {
                        'date': curDate.getDate(),
                        'month': curDate.getMonth() + 1,
                        'year': curDate.getFullYear()
                    };
                }
                start.config.max = date;
                start.config.max.month = date.month - 1;
            }
        });
        form.on('submit(InsertAdoptButton)', function(data){

            var param = data.field;                 //表单数
            var dataJson = JSON.stringify(param);   //转成Json


            $.ajax({
                url:'/adopt/adoptInsert',
                method:'post',
                contentType: "application/json;charset=utf-8",
                data:dataJson,
                dataType:"JSON",
                success:function(data){
                    if (data.success) {
                        layer.msg( "发布成功" ,{offset: '60px',icon: 6,anim: 6,time: 2000});
                        window.location.href = "#";
                    }else{
                        console.log("错误信息是: " + data.msg);
                        layer.msg(data.msg ,{offset: '60px',icon: 5,anim: 6,time: 3000});
                        changeCodeImg();
                    }
                },
                error:function(data){
                    layer.msg(data.msg ,{offset: '60px',icon: 5,anim: 6,time: 3000});
                    changeCodeImg();
                }
            });
            return false;   //使用Ajax提交，此语句阻止LayUI的form表单进行第二次提交
        });
    })
</script>

</html>


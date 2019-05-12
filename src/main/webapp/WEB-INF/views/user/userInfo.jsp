<%--
  Created by IntelliJ IDEA.
  User: 闫喜深
  Date: 2019/5/2
  Time: 19:28
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>个人资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all" />
    <style type="text/css">
        form input.layui-input[disabled]{ background:#f2f2f2; color:#595963!important; }
        .user_left{ width:45%; float: left; margin:20px 0 0 5%; }
        .user_right{ width:25%; float: left; margin:20px 0 0 5%; text-align: center; }
        .user_right p{ margin:10px 0 25px; font-size: 12px; text-align: center; color: #FF5722;}
        .user_right img#userFace{ width:200px; height:200px; }
        .layui-table,.layui-table th{ text-align:center; }

        /*用户列表*/
        .news_list .layui-btn,.news_list .layui-btn+.layui-btn{ margin:2px 5px; }
        #page{ text-align:right; }

        /*修改密码*/
        .changePwd{ width:30%; margin:3% 0 0 5%; }

        /*适配*/
        @media screen and (max-width:1050px){
            /*用户信息*/
            .user_left,.user_right,.changePwd{ width:100%; float:none; margin-left: 0; }
            .user_right{ margin-bottom: 20px; }
        }
        @media screen and (max-width: 750px){
            /*用户信息*/
            .user_left,.user_right,.changePwd{ width:100%; float:none; margin-left: 0; }
            .user_right{ margin-bottom: 20px; }
        }
        @media screen and (max-width:432px){
            /*用户信息*/
            .user_left,.user_right,.changePwd{ width:100%; float:none; margin-left: 0; }
            .user_right{ margin-bottom: 20px; }
        }
    </style>
</head>
<body class="childrenBody">
<form class="layui-form">
    <div class="user_left">
        <div class="layui-form-item">
            <label class="layui-form-label">用户No</label>
            <div class="layui-input-block">
                <input type="text" name="userNo" value=${sessionScope.userInfo.userNo} disabled class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="userName" value=${sessionScope.userInfo.userName} disabled class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="text" name="userSex" value=${sessionScope.userInfo.userSex} disabled class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">年龄</label>
            <div class="layui-input-block">
                <input type="tel" name="userAge" value=${sessionScope.userInfo.userAge} placeholder="请输入年龄" lay-verify="required|number" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-block">
                <input type="tel" name="userTel" value=${sessionScope.userInfo.userTel} placeholder="请输入手机号码" lay-verify="required|phone" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" name="userEmail" value=${sessionScope.userInfo.userEmail} placeholder="请输入邮箱" lay-verify="required|email" class="layui-input">
            </div>
        </div>
    </div>
    <div class="user_right">
        <h1 style="color: #1E9FFF">${sessionScope.userInfo.userName}的个人资料</h1>
        <p></p>
        <button type="button" class="layui-btn layui-btn-normal" id="testUpload">
            <i class="layui-icon" >&#xe67c;</i>换个头像
        </button>
        <p></p>
        <img src=${sessionScope.userInfo.headPortrait} class="layui-circle" id="userFace">
    </div>
    <div class="layui-form-item" style="margin-left: 5%;">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="changeUser">确认修改</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="/layui/layui.js"></script>
<script>
    layui.use(['upload','form','layer'], function(){
        var upload = layui.upload;
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : parent.layer,
            $ = layui.jquery;

        //文件上传 执行实例
        var uploadInst = upload.render({
            elem: '#testUpload' //绑定元素
            ,url: '/upload/' //上传接口
            ,done: function(res){
                //上传完毕回调
            }
            ,error: function(){
                //请求异常回调
            }
        });

        //删除Json中指定的元素
        function JsonDelItem(JSONArray, index){
            for(var key in JSONArray){
                if(key == index || JSONArray[key] == index){
                    delete JSONArray[key];
                    break;
                }
            }
        };

        //监听确认提交按钮
        form.on('submit(changeUser)', function(data){
            var param = data.field;                 //表单数据
            JsonDelItem(param,"file");
            var dataJson = JSON.stringify(param);   //转成Json

            $.ajax({
                url:'/user/userChange',
                method:'post',
                contentType: "application/json;charset=utf-8",
                data:dataJson,
                dataType:"JSON",
                success:function(data){
                    if (data.success) {
                        layer.msg( "修改成功" ,{offset: '60px',icon: 6,anim: 6,time: 2000, end: function (){
                                                location.href = ''}});
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
    });
</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
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
<video class="video-player" preload="auto" autoplay="autoplay" loop="loop" data-height="1080" data-width="1920" height="1080" width="1920">
    <source src="/images/loginBackGround.mp4" type="video/mp4">
    <!-- 此视频文件为支付宝所有,，在此仅供样式参考，如用到商业用途，请自行更换为其他视频或图片，否则造成的任何问题使用者本人承担，谢谢 -->
</video>
<div class="video_mask"></div>
<div class="login">
    <h1>登录</h1>
    <form id="formId" class="layui-form">
        <div class="layui-form-item">
            <label class="layadmin-user-login-icon layui-icon layui-icon-username" style="left: auto"></label>
            <input name="userName" class="layui-input" placeholder="账号" lay-verify="required" type="text" autocomplete="off" style="padding-left: 40px;">
        </div>
        <div class="layui-form-item">
            <label class="layadmin-user-login-icon layui-icon layui-icon-password" style="left: auto"></label>
            <input name="userPasswd" class="layui-input" placeholder="密码" lay-verify="required" type="password" autocomplete="off" style="padding-left: 40px;">
        </div>
        <div class="layui-form-item form_code">
            <label class="layadmin-user-login-icon layui-icon layui-icon-vercode"  style="left: auto"></label>
            <input name="code" class="layui-input" placeholder="验证码" lay-verify="required" type="text" autocomplete="off" style="padding-left: 40px;">
            <div class="code" ><img src="https://www.oschina.net/action/user/captcha" width="116" height="36"></div>
        </div>
        <div class="layui-form-item" style="margin-bottom: 20px;">
            <input type="checkbox" name="remember" lay-skin="primary" title="记住密码">
            <a href="register.html" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">注册帐号</a>
            <a href="forget.html" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">忘记密码？&nbsp;&nbsp;&nbsp;</a>
        </div>
        <button id="loginFormButton" class="layui-btn login_btn" lay-submit="" lay-filter="loginFormButton">登录</button>
    </form>
</div>
<script type="text/javascript" src="/layui/layui.js"></script>
<%--<script type="text/javascript" src="/js/login/login.js"></script>--%>
<script>
    // layui.config({
    //   base : "js/"
    // }).
    layui.use(['form','layer'],function(){
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : parent.layer,
            $ = layui.jquery;
        //video
        $(window).resize(function(){
            if($(".video-player").width() > $(window).width()){
                $(".video-player").css({"height":$(window).height(),"width":"auto","left":-($(".video-player").width()-$(window).width())/2});
            }else{
                $(".video-player").css({"width":$(window).width(),"height":"auto","left":-($(".video-player").width()-$(window).width())/2});
            }
        }).resize();

        //
        // form.on("submit(login)",function(data){
        //   window.location.href = "/main.jsp";
        //   return false;
        // })

        form.on('submit(loginFormButton)', function(data){
            var param = data.field;
            var dataJson = JSON.stringify(param);
            // var param = {userName:userName,userPasswd:userPasswd};
            // console.log(JSON.stringify(param));//是否获取到表单数据，调试模式下在页面控制台查看
            // alert(JSON.stringify(param))
            debugger
            $.ajax({
                url:"/adminLogin",
                method:'post',
                contentType: "application/json;charset=utf-8",
                data:dataJson,
                dataType:"JSON",
                success:function(data){
                    // debugger
                    if (data.success) {
                        layer.msg( "登录成功" ,{offset: '60px',icon: 6,anim: 6,time: 1000});
                        window.location.href='/views/admin/main.jsp';
                    }else{
                        refreshImg();
                        console.log("错误信息是: " + data.msg);
                        layer.msg(data.msg ,{offset: '60px',icon: 5,anim: 6,time: 3000});
                    }
                }
            });
            return false;
        });
    })

</script>
</body>
</html>
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
<video class="video-player" preload="auto" autoplay="autoplay" loop="loop">
    <source src="/images/loginBackGround.mp4" type="video/mp4">
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
            <%--<div class="code" ><img onclick="javascript:this.src='/captcha/imagesOutToPage?id='+Math.random();" src="/captcha/imagesOutToPage" width="116" height="36"></div>--%>
            <div class="code" ><img id="codeImg" onclick="javascript:changeCodeImg();" src="/captcha/imagesOutToPage" width="116" height="36"></div>
        </div>
        <div class="layui-form-item" style="color: #fff">
            <label class="layui-form-label" style="text-align:center;font-weight: bold;padding: 10px 0px">角色</label>
            <div class="layui-input-block" style="margin-left: 60px;margin-right: 0px;!important">
                <input type="radio" name="role" value="admin" title="管理员" checked>
                <input type="radio" name="role" value="worker" title="工作人员" >
                <input type="radio" name="role" value="user" title="普通用户" >
            </div>
        </div>
        <div class="layui-form-item" style="margin-bottom: 20px;">
            <input type="checkbox" name="remember" lay-skin="primary" title="记住密码">
            <a href="/user_register.html" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">注册帐号</a>
            <a href="forget.html" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">忘记密码？&nbsp;&nbsp;&nbsp;</a>
        </div>
        <button id="loginFormButton" class="layui-btn login_btn" lay-submit="" lay-filter="loginFormButton">登录</button>
    </form>
</div>
<script type="text/javascript" src="/layui/layui.js"></script>
<script>
    //刷新验证码图片
    function changeCodeImg(){
        document.getElementById("codeImg").src='/captcha/imagesOutToPage?id='+Math.random();
    }

    layui.use(['form','layer'],function(){
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : parent.layer,
            $ = layui.jquery;

        // form.render();

        //登录页面的视频背景
        $(window).resize(function(){
            if($(".video-player").width() > $(window).width()){
                $(".video-player").css({"height":$(window).height(),"width":"auto","left":-($(".video-player").width()-$(window).width())/2});
            }else{
                //本项目所使用的背景视频尺寸较小，执行else中的语句
                $(".video-player").css({"width":$(window).width(),"height":"auto","left":0});
            }
        }).resize();

        //删除Json中指定的元素
        function JsonDelItem(JSONArray, index){
            for(var key in JSONArray){
                if(key == index || JSONArray[key] == index){
                    delete JSONArray[key];
                    break;
                }
            }
        };

        //监听登录按钮
        form.on('submit(loginFormButton)', function(data){
            var param = data.field;                 //表单数据
            var roleType = param.role;              //role元素角色选择
            JsonDelItem(param,"role");              //把表单数据除去role元素
            var dataJson = JSON.stringify(param);   //转成Json
            // console.log(JSON.stringify(param));  //调试模式下在页面控制台查看

            //定义三种角色要访问的Controller和登录成功后的url
            var urlController = "";
            var urlJumpPage = "";
            if(roleType == "admin"){
                urlController = "/admin/adminLogin";
                urlJumpPage = '/page/views/admin/adminMain';
            }else if(roleType == "worker"){
                urlController = "/worker/workerLogin";
                urlJumpPage = "/page/views/worker/workerMain";
            }else if(roleType == "user"){
                urlController = "/user/userLogin";
                urlJumpPage = "/page/views/user/userMain";
            }else{
                layer.msg("错误" ,{offset: '60px',icon: 5,anim: 6,time: 3000});
            }

            $.ajax({
                url:urlController,
                method:'post',
                contentType: "application/json;charset=utf-8",
                data:dataJson,
                dataType:"JSON",
                success:function(data){
                    if (data.success) {
                        layer.msg( "登录成功" ,{offset: '60px',icon: 6,anim: 6,time: 2000});
                        window.location.href = urlJumpPage;
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
</body>
</html>
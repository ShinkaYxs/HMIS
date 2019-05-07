<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
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
<!-- 		用户注册需要填写的信息 -->
<div class="login" style="height:600px;width:500px;padding: 20px;background-color:rgba(0,0,0,0.5);border-radius: 4px;position:absolute;left: 50%;top: 50%; margin:-280px 0 0 -200px;z-index:99;">
    <h1>注册</h1>
    <form id="formId" class="layui-form">
        <div class="layui-form-item">
            <input lay-verify="required" name="userName" class="layui-input" placeholder="姓名" lay-verify="required" type="text" autocomplete="off" style="padding-left: 40px;">
        </div>
<%--        <div class="layui-form-item">--%>
<%--            <input name="userId" class="layui-input" placeholder="编号" lay-verify="required" type="text" autocomplete="off" style="padding-left: 40px;">--%>
<%--        </div>--%>
        <div class="layui-form-item">
            <input lay-verify="required|number"name="userAge" class="layui-input" placeholder="年龄" lay-verify="required" type="text" autocomplete="off" style="padding-left: 40px;">
        </div>
        <div class="layui-form-item">
        <input lay-verify="required|phone|number" name="userTel" class="layui-input" placeholder="手机号" lay-verify="required" type="text" autocomplete="off" style="padding-left: 40px;">
        </div>
        <div class="layui-form-item">
            <input lay-verify="email" name="userEmail" class="layui-input" placeholder="邮箱号" lay-verify="required" type="text" autocomplete="off" style="padding-left: 40px;">
        </div>
        <div class="layui-form-item">
            <input lay-verify="pass" name="userPwd" class="layui-input" placeholder="密码" lay-verify="required" type="password" autocomplete="off" style="padding-left: 40px;">
        </div>
        <div class="layui-form-item">
            <input lay-verify="pass|repass" name="userRePwd" class="layui-input" placeholder="确认密码" lay-verify="required" type="password" autocomplete="off" style="padding-left: 40px;">
        </div>
        <div class="layui-form-item" style="color: #fff">
            <label class="layui-form-label" style="text-align:center;font-weight: bold;padding: 10px 0px">性别</label>
            <div class="layui-input-block" style="margin-left: 60px;margin-right: 0px;!important">
<%--                <input type="radio" name="userSex" value="男" title="男" checked>--%>
<%--                <input type="radio" name="userSex" value="女" title="女" >--%>
    <input type="radio" name="userSex" value="男" title="男"checked>
    <input type="radio" name="userSex" value="女" title="女" >

            </div>
        </div>
        <div class="layui-form-item form_code">
            <label class="layadmin-user-login-icon layui-icon layui-icon-vercode"  style="left: auto"></label>
            <input name="code" class="layui-input" placeholder="验证码" lay-verify="required" type="text" autocomplete="off" style="padding-left: 40px;">
            <%--<div class="code" ><img onclick="javascript:this.src='/captcha/imagesOutToPage?id='+Math.random();" src="/captcha/imagesOutToPage" width="116" height="36"></div>--%>
            <div class="code" ><img id="codeImg" onclick="javascript:changeCodeImg();" src="/captcha/imagesOutToPage" width="116" height="36"></div>
        </div>
        <button id="RegisterFormButton" class="layui-btn login_btn" type="submit" lay-submit="" lay-filter="RegisterFormButton">注册</button>
    </form>
</div>
</body>

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
        //form.render();

        form.verify({
            pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且两次密码要相同'
            ]
            ,repass: function(value, item){ //value：表单的值、item：表单的DOM对象
                if(value !=layui.$('input[name="userPwd"]').val()){
                    return '密码不相同请重新输入！'}
            }
        });

        //监听登录按钮
        form.on('submit(RegisterFormButton)', function(data){
            
            var param = data.field;                 //表单数
            var dataJson = JSON.stringify(param);   //转成Json


            $.ajax({
                url:'/user/userRegister',
                method:'post',
                contentType: "application/json;charset=utf-8",
                data:dataJson,
                dataType:"JSON",
                success:function(data){
                    if (data.success) {
                        layer.msg( "注册成功" ,{offset: '60px',icon: 6,anim: 6,time: 2000});
                        window.location.href = "/login.jsp";
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

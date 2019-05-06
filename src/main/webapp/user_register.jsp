<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link rel="stylesheet" href="/css/index/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/index/drag.css" />
    <style type="text/css">
        *{
            padding: 0px;
            margin: 0px;
        }
        body{
            background-image: url(/images/index/ba_20.png);
            background-size:cover;
        }
        h1{
            font-style: initial;
            font-weight: bold;
        }
    </style>
</head>
<body>
<!-- 		患者注册需要填写的信息 -->
<div id="register">
<!--   <form action="" method="" class="form-horizontal" name="register"-->
<!--         onsubmit="return checkForm();">-->
    <form class="form-horizontal">
        <div>
            <h1 class="text-center">用户注册</h1>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3">真实姓名*</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" placeholder="请输入姓名"
                       name="userName" id="userName" onblur="check()"/>
            </div>
            <div id="div1" class="col-sm-3" style="display:inline;color: red;"></div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3">性别*</label>
            <div class="col-sm-6">
                <select class="form-control" name="userSex">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3">年龄*</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" name="userAge" id="userAge"/>
            </div>
            <div id="div3" style="display:inline;color: red;"></div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3">联系电话*</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" placeholder="请输入您正在使用的电话号码"
                       name="userTel" id="userTel"/>
            </div>
            <div id="div4" class="col-sm-3" style="display:inline;color: red;"></div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3">电子邮箱*</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" placeholder="请输入电子邮箱"
                       name="userEmail" id="userEmail"/>
            </div>
            <div id="div5" class="col-sm-3" style="display:inline;color: red;"></div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3">输入密码*</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" placeholder="输入密码"
                       name="userPwd" id="userPwd" onblur="check()"/>
            </div>
            <div id="div6" class="col-sm-3" style="display:inline;color: red;"></div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3">确认密码*</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" placeholder="再次输入密码"
                       name="userRepwd" id="userRepwd" onblur="check()"/>
            </div>
            <div id="div7" class="col-sm-3" style="display:inline;color: red;"></div>
        </div>
        <div class="form-group text-center">
            <div>标记为*为您必填的项目，注册后您的账号为手机号或者邮箱账号</div>
<%--            <button class="btn btn-lg btn-default" type="submit" id="submit" diabaled="">注册</button>--%>
            <button id="registerFormButton" class="layui-btn login_btn" lay-submit="" lay-filter="registerFormButton">注册</button>
            <button class="btn btn-lg btn-default" type="reset">重置</button>
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="/js/index/jquery-3.2.1.min.js" ></script>
<script type="text/javascript" src="/js/index/drag.js" ></script>
<script type="text/javascript" src="/js/index/bootstrap.min.js" ></script>
<script type="application/javascript">
</script>
<script type="application/javascript">
    function checkName()
    {
        var n=jQuery("#userName").val();
        if(!n)
        {
            jQuery("#div1").html("用户名不能为空!");
            return false;
        }
        jQuery("#div1").html("√");
        jQuery("#div1").css("color","green");
        return true; 的
    }

    function checkAge()
    {
        var age=jQuery("#userAge").val();
        if(!age)
        {
            jQuery("#div3").html("年龄不能为空!");
            return false;
        }
        jQuery("#div3").html("√");
        jQuery("#div3").css("color","green");
        return true;
    }
    function checkTel(){
        var tel=jQuery("#userTel").val();
        var p=/^1[34578]\d{9}$/;
        if(tel){
            if(p.test(tel)){
                jQuery("#div4").html("√");
                jQuery("#div4").css("color","green");
                return true;
            }
            jQuery("#div4").html("请输入有效的手机号码!");
        }
        else {
            jQuery("#div4").html("手机号不能为空!");
        }
        return false;
    }
    function checkEmail(){
        var email=jQuery("#userEmail").val();
        var p=/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if(email&&(p.test(email))){
            jQuery("#div5").html("√");
            jQuery("#div5").css("color","green");
            return true;
        }
        jQuery("#div5").html("请输入有效的邮箱地址!");
        return false;
    }
    function checkpassword(){
        var p1=jQuery("#userPwd").val();
        if(p1){
            jQuery("#div6").html("√");
            jQuery("#div6").css("color","green");
            return true;
        }
        jQuery("#div6").html("密码不能为空!");
        return false;
    }
    function checkRepassword(){
        var p1=jQuery("#userPwd").val();
        var p2=jQuery("#userRepwd").val();
        if(p1==p2){
            jQuery("#div7").html("√");
            jQuery("#div7").css("color","green");
            return true;
        }
        jQuery("#div7").html("密码输入不一致!");
        return false;
    }
    function checkForm()
    {
        if(checkName()&&checkAge()&&checkTel()&&checkEmail()&&checkpassword()&&checkRepassword()){
            return true;
        }
        else {
            return false;
        }
    }
</script>
<script type="text/javascript" src="/layui/layui.js"></script>
<script>


    layui.use(['form','layer'],function() {
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : parent.layer,
            $ = layui.jquery;

        // form.render();


        form.on('submit(registerFormButton)', function (data) {
            $.ajax({
                url: '/user/userRegister',
                method: 'post',
                contentType: "application/json;charset=utf-8",
                data: dataJson,
                dataType: "JSON",
                success: function (data) {
                    if (data.success) {
                        // layer.msg( "登录成功" ,{offset: '60px',icon: 6,anim: 6,time: 2000});
                        window.location.href = "/login.jsp";
                    } else {
                        console.log("错误信息是: " + data.msg);
                        layer.msg(data.msg, {offset: '60px', icon: 5, anim: 6, time: 3000});
                        changeCodeImg();
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {offset: '60px', icon: 5, anim: 6, time: 3000});
                    changeCodeImg();
                }
            });
            return false;   //使用Ajax提交，此语句阻止LayUI的form表单进行第二次提交
        })
    });
</script>
</html>

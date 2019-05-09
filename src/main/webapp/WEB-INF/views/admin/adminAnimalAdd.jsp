<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加动物信息</title>
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
<div class="login" style="height:400px;width:500px;padding: 20px;background-color:rgba(0,0,0,0.5);border-radius: 4px;position:absolute;left: 50%;top: 50%; margin:-220px 0 0 -260px;z-index:99;">
    <h1>添加动物信息</h1>
    <form id="formId" class="layui-form">
        <div class="layui-form-item">
            <input name="userNo" class="layui-input" placeholder="用户ID" lay-verify="required|number" type="text" autocomplete="off" style="padding-left: 40px;">
        </div>
        <div class="layui-form-item">
            <input name="animalType" class="layui-input" placeholder="动物类型" lay-verify="required" type="text" autocomplete="off" style="padding-left: 40px;">
        </div>
        <div class="layui-form-item">
            <input name="animalName" class="layui-input" placeholder="姓名" lay-verify="required" type="text" autocomplete="off" style="padding-left: 40px;">
        </div>
        <div class="layui-form-item">
            <input name="animalAge" class="layui-input" placeholder="年龄" lay-verify="required|number" type="text" autocomplete="off" style="padding-left: 40px;">
        </div>
        <div class="layui-form-item" style="color: #fff">
            <label class="layui-form-label" style="text-align:center;font-weight: bold;padding: 10px 0px">性别</label>
            <div class="layui-input-block" style="margin-left: 60px;margin-right: 0px;!important">
                <input type="radio" name="animalSex" value="男" title="男" checked>
                <input type="radio" name="animalSex" value="女" title="女" >
            </div>
        </div>
        <button id="RegisterFormButton" class="layui-btn login_btn" type="submit" lay-submit="" lay-filter="RegisterFormButton">添加</button>
    </form>
</div>
</body>

<script type="text/javascript" src="/layui/layui.js"></script>

<script>
    layui.use(['form','layer'],function(){
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : parent.layer,
            $ = layui.jquery;

        //视频背景
        $(window).resize(function(){
            if($(".video-player").width() > $(window).width()){
                // $(".video-player").css({"height":$(window).height(),"width":"auto","left":-($(".video-player").width()-$(window).width())/2});
                $(".video-player").css({"width":$(window).width(),"height":"auto","left":0});
            }else{
                //本项目所使用的背景视频尺寸较小，执行else中的语句
                $(".video-player").css({"width":$(window).width()+200,"height":"auto","left":0});
            }
        }).resize();

        //监听登录按钮
        form.on('submit(RegisterFormButton)', function(data){
            var param = data.field;                 //表单数据
            var dataJson = JSON.stringify(param);   //转成Json
            $.ajax({
                url:'/animal/animalAdd',
                method:'post',
                contentType: "application/json;charset=utf-8",
                data:dataJson,
                dataType:"JSON",
                success:function(data){
                    if (data.success) {
                        layer.msg( "添加成功" ,{offset: '60px',icon: 6,anim: 6,time: 2000, end: function (){
                                            location.reload();}});
                    }else{
                        console.log("错误信息是: " + data.msg);
                        layer.msg(data.msg ,{offset: '60px',icon: 5,anim: 6,time: 3000});
                    }
                },
                error:function(data){
                    layer.msg(data.message ,{offset: '60px',icon: 5,anim: 6,time: 3000});
                }
            });
            return false;   //使用Ajax提交，此语句阻止LayUI的form表单进行第二次提交
        });

    })

</script>

</html>

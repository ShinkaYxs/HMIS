<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>预约救治</title>
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
<div class="login" style="height:320px;width:500px;padding: 20px;background-color:rgba(0,0,0,0.5);border-radius: 4px;position:absolute;left: 50%;top: 50%; margin:-180px 0 0 -260px;z-index:99;">
    <h1>预约救治</h1>
    <form id="formId" class="layui-form">
        <div class="layui-form-item">
            <input name="userNo" value=${sessionScope.userInfo.userNo} disabled class="layui-input" placeholder="用户ID" lay-verify="required|number" type="text" autocomplete="off" style="padding-left: 40px;">
        </div>
        <div class="layui-form-item">
            <div class="layui-inline" style="width: 100%">
                <div class="layui-input-inline" style="width: 100%">
                        <select name="animalNo" id="animalNo" placeholder="动物No" lay-verify="required" lay-search=""></select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline" style="width: 100%">
                <div class="layui-input-inline" style="width: 100%">
                    <select name="departmentNo" id="departmentNo" placeholder="科室No" lay-verify="required" lay-search="" lay-filter="departmentNo"></select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline" style="width: 100%">
                <div class="layui-input-inline" style="width: 100%">
                    <select name="workerNo" id="workerNo" placeholder="工作人员No" lay-verify="required" lay-search="" lay-filter="workerNo"></select>
                </div>
            </div>
        </div>
        <button id="RegisterFormButton" class="layui-btn login_btn" type="submit" lay-submit="" lay-filter="RegisterFormButton">添加</button>
    </form>
</div>
</body>

<script type="text/javascript" src="/layui/layui.js"></script>

<script>

    //调方法更新比较好。
    function formrender(){
        layui.use(['form', 'layedit', 'laydate'], function(){
            //var form = layui.form(); //1.0.9版用法
            var form = layui.form; // 2.0.1以上用
            form.render();
        })
    }

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

        //下拉框从后台获取动物no和名称
        queryAnimalOfUser();

        //下拉框从后台获取动物no和名称
        function queryAnimalOfUser(){
            var userNoInForm = layui.$('input[name="userNo"]').val();
            $.ajax({
                url:'/animal/animalOfUserQuery?userNo=' + userNoInForm,
                // type: "post",
                dataType: 'json',
                success: function(result) {
                    if (result.success) {
                        var msg = result.data;
                        var length = Object.keys(msg).length;   //对象的长度不能用.length获取,用js原生的Object.keys可以获取到
                        var htm = '<option value="">请选择您的动物</option>';

                        for (var i = 0; i < length ; i++) {
                            htm += '"<option value="'+msg[i].animalNo+'">' + msg[i].animalName+"</option> ";
                        };
                        $("#animalNo").append(htm);
                        formrender();
                    }else{
                        console.log("错误信息是: " + data.msg);
                        layer.msg(data.msg ,{offset: '60px',icon: 5,anim: 6,time: 3000});
                    }
                },
                error:function(result){
                    layer.msg(result.message ,{offset: '60px',icon: 5,anim: 6,time: 3000});
                }

            });
        }

        //下拉框从后台获取科室no和名称
        queryDept();

        //下拉框从后台获取科室no和名称
        function queryDept(){
            // var userNoInForm = layui.$('input[name="userNo"]').val();
            $.ajax({
                url:'/department/deptInfoQueryAll',
                dataType: 'json',
                success: function(result) {
                    if (result.success) {
                        var msg = result.data;
                        var length = Object.keys(msg).length;   //对象的长度不能用.length获取,用js原生的Object.keys可以获取到
                        var htm = '<option value="">请选择科室</option>';

                        for (var i = 0; i < length ; i++) {
                            htm += '"<option value="'+msg[i].departmentNo+'">' + msg[i].departmentName+"</option> ";
                        };
                        $("#departmentNo").append(htm);
                        formrender();
                    }else{
                        console.log("错误信息是: " + data.msg);
                        layer.msg(data.msg ,{offset: '60px',icon: 5,anim: 6,time: 3000});
                    }
                },
                error:function(result){
                    layer.msg(result.message ,{offset: '60px',icon: 5,anim: 6,time: 3000});
                }

            });
        }

        //监听科室，下拉框从后台获取工作人员no和名称
        form.on('select(departmentNo)', function (data) {
            var departmentNo = data.value;
            $.ajax({
                url:'/worker/workerQueryByDeptNo?deptNo=' + departmentNo,
                dataType: 'json',
                success: function(result) {
                    if (result.success) {
                        var dt=document.getElementById("workerNo");
                        dt.options.length=0;

                        var msg = result.data;
                        var length = Object.keys(msg).length;   //对象的长度不能用.length获取,用js原生的Object.keys可以获取到
                        var htm = '<option value="">请选择工作人员</option>';

                        for (var i = 0; i < length ; i++) {
                            htm += '"<option value="'+msg[i].workerNo+'">' + msg[i].workerName+"</option> ";
                            // var op=new Option(msg[i].workerName, msg[i].workerNo);
                            // dt.add(op);
                        };
                        $("#workerNo").append(htm);
                        formrender();
                    }else{
                        console.log("错误信息是: " + data.msg);
                        layer.msg(data.msg ,{offset: '60px',icon: 5,anim: 6,time: 3000});
                    }
                },
                error:function(result){
                    layer.msg(result.message ,{offset: '60px',icon: 5,anim: 6,time: 3000});
                }

            });

        });

        //监听登录按钮
        form.on('submit(RegisterFormButton)', function(data){
            var param = data.field;                 //表单数据
            var dataJson = JSON.stringify(param);   //转成Json
            $.ajax({
                url:'/order/orderAdd',
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

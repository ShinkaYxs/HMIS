<%--
  Created by IntelliJ IDEA.
  User: 闫喜深
  Date: 2019/5/7
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>工作人员信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
</head>
<body>

    <%--<div class="layui-btn-group test-table-operate-btn" style="margin-bottom: 10px;">--%>
        <%--<button class="layui-btn" data-type="getCheckData">获取选中行数据</button>--%>
        <%--<button class="layui-btn" data-type="getCheckLength">获取选中数目</button>--%>
        <%--<button class="layui-btn" data-type="isAll">验证是否全选</button>--%>
    <%--</div>--%>

    <table class="layui-hide" id="test-table-operate" lay-filter="test-table-operate"></table>

    <%--<script type="text/html" id="test-table-operate-barDemo">--%>
        <%--<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>--%>
        <%--<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
    <%--</script>--%>

<script src="/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['table', 'form', 'layer'], function(){
        var table = layui.table;
        var form = layui.form
            ,layer = layui.layer;
        var $ = layui.$;

        table.render({
            elem: '#test-table-operate'
            ,url: '/worker/workerInfoQueryAll'
            ,cols: [[
                {type:'checkbox', fixed: 'left'}
                ,{field:'workerNo', width:80, title: '工号', sort: true, fixed: 'left'}
                ,{field:'workerName', width:80, title: '用户名'}
                ,{field:'workerSex', width:80, title: '性别', sort: true}
                ,{field:'workerAge', width:80, title: '年龄'}
                ,{field:'departmentNo', width: 120, title: '部门编号'}
                ,{field:'workerTel', width:120, title: '手机', sort: true}
                ,{field:'workerEmail', title: '邮箱', sort: true}
                // ,{field:'headPortrait',title: '头像路径'}
                // ,{width:120, align:'center', fixed: 'right', toolbar: '#test-table-operate-barDemo'}
            ]]
            ,page: true
        });

        //监听表格复选框选择
        table.on('checkbox(test-table-operate)', function(obj){
            console.log(obj)
        });

        //监听工具条
        table.on('tool(test-table-operate)', function(obj){
            var data = obj.data;
            // if(obj.event === 'detail'){
            //     layer.msg('ID：'+ data.workerNo + ' 的查看操作');
            // } else
            if(obj.event === 'del'){
                layer.confirm('真的删除该人员信息？', {icon: 3, title:'提示', offset: '50px'}, function(index){
                    $.ajax({
                        url:'/worker/workerDeleteByNo?workerNo=' + data.workerNo,
                        dataType:"JSON",
                        success:function (resultData) {
                            if (resultData.success) {
                                layer.msg("删除成功", {offset: '60px',icon: 6,anim: 6,time: 2000, end: function (){
                                        // layer.closeAll();//关闭所有的弹出层
                                        location.reload();}});
                            }else{
                                layer.msg("删除失败", {icon: 5});
                            }
                        },
                        error:function(resultData){
                            layer.msg(resultData.msg ,{offset: '60px',icon: 5,anim: 6,time: 3000});
                        }
                    });
                    // obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                $("#workerNo").val(data.workerNo);
                $("#workerName").val(data.workerName);
                $("#workerSex").val(data.workerSex);
                $("#workerAge").val(data.workerAge);
                $("#departmentNo").val(data.departmentNo);
                $("#workerTel").val(data.workerTel);
                $("#workerEmail").val(data.workerEmail);
                $("#headPortrait").val(data.headPortrait);
                layer.open({
                    //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                    type: 1,
                    title: "修改工作人员信息",
                    area: ['420px', '460px'],
                    offset: '10px',             //只定义top坐标，水平保持居中
                    content: $("#popUpdateTest")//引用的弹出层的页面层的方式加载修改界面表单
                });

                //动态向表传递赋值可以参看进行修改界面的更新前数据的显示，当然也是异步请求的要数据的修改数据的获取
                setFormValue();
            }
        });

        //监听弹出框表单提交，massage是修改界面的表单数据'submit(demo11),是修改按钮的绑定
        function setFormValue(){
            form.on('submit(yesButton)', function(dataNew) {
                var dataNewParam = dataNew.field;
                var dataNewJson = JSON.stringify(dataNewParam);
                $.ajax({
                    url:'/worker/workerChange',
                    method:'post',
                    contentType: "application/json;charset=utf-8",
                    data:dataNewJson,
                    dataType:"JSON",
                    success:function (resultData) {
                        if (resultData.success) {
                            layer.msg("修改成功", {offset: '60px',icon: 6,anim: 6,time: 2000, end: function (){
                                                    layer.closeAll();//关闭所有的弹出层
                                                    location.reload();}});
                        }else{
                            layer.msg("修改失败", {icon: 5});
                        }
                    },
                    error:function(resultData){
                        layer.msg(resultData.msg ,{offset: '60px',icon: 5,anim: 6,time: 3000});
                    }
                });
                return false;   //使用Ajax提交，此语句阻止LayUI的form表单进行第二次提交
            });

        }

        //表格上方三个按钮
        var active = {
            getCheckData: function(){ //获取选中数据
                var checkStatus = table.checkStatus('test-table-operate')
                    ,data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            ,getCheckLength: function(){ //获取选中数目
                var checkStatus = table.checkStatus('test-table-operate')
                    ,data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
            }
            ,isAll: function(){ //验证是否全选
                var checkStatus = table.checkStatus('test-table-operate');
                layer.msg(checkStatus.isAll ? '全选': '未全选')
            }
        };

        $('.test-table-operate-btn .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>

<%--//这里是弹出层的表单信息--%>
<%--//表单的id用于表单的选择，style是在本页隐藏，只有点击编辑才会弹出--%>
<div class="layui-row" id="popUpdateTest" style="display:none;">
    <div class="layui-col-md10">
        <form class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">工号</label>
                <div class="layui-input-block">
                    <input type="text" id="workerNo" name="workerNo" placeholder="请输入工号" disabled class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" id="workerName" name="workerName" placeholder="请输入用户名" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="text" id="workerSex" name="workerSex" value="" placeholder="请输入性别" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">年龄</label>
                <div class="layui-input-block">
                    <input type="tel" id="workerAge" name="workerAge" value="" placeholder="请输入年龄" lay-verify="required|number" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">部门</label>
                <div class="layui-input-block">
                    <input type="text" id="departmentNo" name="departmentNo" value="" placeholder="请输入部门" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机</label>
                <div class="layui-input-block">
                    <input type="tel" id="workerTel" name="workerTel" value="" placeholder="请输入手机号码" lay-verify="required|phone" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-block">
                    <input type="text" id="workerEmail" name="workerEmail" value="" placeholder="请输入邮箱" lay-verify="required|email" class="layui-input">
                </div>
            </div>
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="yesButton">确认修改</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
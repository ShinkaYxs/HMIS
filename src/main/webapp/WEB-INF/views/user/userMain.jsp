<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>救助中心后台管理</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">小型动物救助中心</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    ${sessionScope.userName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">所有商品</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/index.html" data-id="testId" data-title="这里是选项a" href="#"
                               class="site-demo-active" data-type="tabAdd">选项a</a></dd>
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="javascript:;">列表三</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="demo" lay-allowclose="true">
            <ul class="layui-tab-title">
                <li class="layui-this" lay-id="indexId">管理首页</li>
            </ul>
            <ul class="rightmenu" style="display: none;position: absolute;">
                <li data-type="closethis">关闭当前</li>
                <li data-type="closeall">关闭所有</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">这是管理首页里的内容</div>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="/layui/layui.js"></script>
<script>
    layui.use('element', function(){
        var $ = layui.jquery;
        var element = layui.element;        //Tab的切换功能，切换事件监听等，需要依赖element模块

        //给active绑定几项事件，后面可通过active调用这些事件
        var active = {
            tabAdd: function(url,id,name) { //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
                                            //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
                element.tabAdd('demo', {
                    title: name,
                    content: '<iframe data-frameid="'+id+'" scrolling="auto" frameborder="0" src="'+url+'" style="width:100%;height:99%;"></iframe>',
                    id: id
                })
                CustomRightClick(id);       //给tab绑定右击事件
                FrameWH();                  //计算ifram层的大小
            },
            tabChange: function(id) {           //切换到指定Tab项
                element.tabChange('demo', id);  //根据传入的id传入到指定的tab项
            },
            tabDelete: function (id) {          //删除
                element.tabDelete("demo", id);
            },
            tabDeleteAll: function (ids) {      //删除所有
                $.each(ids, function (i,item) {
                    element.tabDelete("demo", item); //ids是一个数组，里面存放了多个id，调用tabDelete方法分别删除
                })
            }
        };

        //当点击有site-demo-active属性的标签时，即左侧菜单栏中内容 ，触发点击事件
        $('.site-demo-active').on('click', function() {
            var dataid = $(this);

            if ($(".layui-tab-title li[lay-id]").length <= 0) {     //判断右侧已经打开的tab项数目  即.layui-tab-title属性下的有lay-id属性的li的数目，
                active.tabAdd(dataid.attr("data-url"),              //比零小则直接打开新的tab项
                              dataid.attr("data-id"),
                              dataid.attr("data-title"));
            } else {                                                //判断该tab项是否以及存在
                var isData = false;                                 //初始化一个标志，false是未打开该tab项 为true则说明已有
                $.each($(".layui-tab-title li[lay-id]"), function () {
                    if ($(this).attr("lay-id") == dataid.attr("data-id")) {//若点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                        isData = true;
                    }
                })
                if (isData == false) {                              //标志为false 新增一个tab项
                    active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-title"));
                }
            }
            //最后不管是否新增tab，最后都转到要打开的选项页面上
            active.tabChange(dataid.attr("data-id"));
        });

        //取消右键  rightmenu属性开始是隐藏的 ，当右击的时候显示，左击的时候隐藏
        function CustomRightClick(id) {
            $('.layui-tab-title li').on('contextmenu', function () { return false; })
            $('.layui-tab-title,.layui-tab-title li').click(function () {
                $('.rightmenu').hide();
            });
            //桌面点击右击
            $('.layui-tab-title li').on('contextmenu', function (e) {
                var popupmenu = $(".rightmenu");
                popupmenu.find("li").attr("data-id",id);    //在右键菜单中的标签绑定id属性

                //判断右侧菜单的位置
                l = ($(document).width()-200 - e.clientX) < popupmenu.width() ? (e.clientX - popupmenu.width()) : e.clientX-200;
                t = ($(document).height()-25 - e.clientY) < popupmenu.height() ? (e.clientY - popupmenu.height()) : e.clientY-25;
                popupmenu.css({ left: l, top: t }).show();  //进行绝对定位
                //alert("右键菜单")
                return false;
            });
        }

        //右键菜单中的选项被点击
        $(".rightmenu li").click(function () {
            if ($(this).attr("data-type") == "closethis") {         //关闭当前，即根据显示右键菜单时所绑定的id，执行tabDelete
                active.tabDelete($(this).attr("data-id"))
            } else if ($(this).attr("data-type") == "closeall") {   //关闭所有 ，即将所有的lay-id放进数组，执行tabDeleteAll
                var tabtitle = $(".layui-tab-title li");
                var ids = new Array();
                $.each(tabtitle, function (i) {
                    ids[i] = $(this).attr("lay-id");
                })
                active.tabDeleteAll(ids);
            }

            $('.rightmenu').hide(); //最后再隐藏右键菜单
        })

        function FrameWH() {
            var h = $(window).height() -41- 10 - 60 -10-44 -10;
            $("iframe").css("height",h+"px");
        }

        $(window).resize(function () {
            FrameWH();
        })

    });
</script>
</body>
</html>
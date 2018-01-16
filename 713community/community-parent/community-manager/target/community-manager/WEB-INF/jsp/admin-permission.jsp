<%--
  Created by IntelliJ IDEA.
  User: 先
  Date: 2017/10/19
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="http://static.community.com/lib/html5.js"></script>
    <script type="text/javascript" src="http://static.community.com/lib/respond.min.js"></script>
    <script type="text/javascript" src="http://static.community.com/lib/PIE_IE678.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="http://static.community.com/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="http://static.community.com/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="http://static.community.com/lib/Hui-iconfont/1.0.7/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="http://static.community.com/lib/icheck/icheck.css" />
    <link rel="stylesheet" type="text/css" href="http://static.community.com/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="http://static.community.com/static/h-ui.admin/css/style.css" />
    <link rel="stylesheet" type="text/css" href="http://static.community.com/lib/layui-v2.1.5/layui/css/layui.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>菜单管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 菜单管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
        <form class="Huiform " target="_self">
            <div class="col-xs-11" >
                <div class="col-xs-2" style="display: inline-block"></div>
                <div class="formControls col-xs-2" style="display: inline-block">
                    <span class="select-box">
                        <select class="select" size="1" id="search-param">
                            <option value="name" selected>菜单名</option>
                            <option value="type">菜单类别</option>
                        </select>
				    </span>
                </div>
            <div class="col-xs-5" style="display: inline-block">
                <input type="text" class="input-text"  placeholder="模糊搜索..." id="serach-input">
                </div>
            <div class="col-xs-2" style="display: inline-block">

                <button style="display: inline-block" type="button" class="btn btn-success" id="searchBtn"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
            </div>
            </div>

        </form>
    </div>
    <div style="margin-top: 50px;"></div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a href="javascript:;" onclick="menu_add('添加权限节点','admin-permission-add.html','800','450')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加权限节点</a></span> <span class="r">共有数据：<strong id="total-text"></strong> 条</span> </div>
    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="10">菜单栏目</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" id="test" name="" value="10"></th>
            <th width="40">ID</th>
            <th width="100">类型</th>
            <th width="180">上级菜单</th>
            <th width="180">菜单名称</th>
            <th width="220">URL</th>
            <th width="30">图标</th>
            <th width="220">权限集</th>
            <th width="40">序号</th>
            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody id="tb-body"></tbody>
    </table>

</div>

<button id="btn-refresh" style="display:none">刷新</button>

<div id="page-nav" style="float: right"></div>


<script type="text/javascript" src="http://static.community.com/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="http://static.community.com/lib/layer/2.1/layer.js"></script>
<script type="text/javascript" src="http://static.community.com/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="http://static.community.com/lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="http://static.community.com/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="http://static.community.com/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="http://static.community.com/lib/layui-v2.1.5/layui/layui.js"></script>
<script>

    $(function(){
        //加载菜单列表数据
        menuPageData('../../menu/page/', null, null);
    });

    /*当编辑成功后，该点击事件被触发，重新加载数据*/
    $("#btn-refresh").click(function() {
        window.location.reload();
    });

    /*搜索条件提示*/
    $("#search-param").change(function () {
        var type = $("#search-param option:selected").val();
        if (type == "name") {
            $("#serach-input").attr("placeholder", "模糊搜索...");
        } else if (type == "type") {
            $("#serach-input").attr("placeholder", "请输入数字：0-顶级菜单，1-栏目，2-按钮");
        }
    });

    /*点击搜索*/
    $("#searchBtn").click(function () {
        var searchParam = $("#search-param").val();
        var searchVal = $("#serach-input").val();
        menuPageData('../../menu/param/page/', searchParam, searchVal);

    });

    /*点击编辑按钮触发的事件*/
    $("#tb-body").on("click", ".edit", function() {

        var _this = $(this);
        var data = _this.parent().siblings();
        var arr = [];
        /*//id单独获取
         var menu_id = $(data[1]).text();
         alert(menu_id);
         arr.push(menu_id);*/
        for(var i = 1; i < data.length; i++){
            //console.log($(data[i]).text());
            arr.push($(data[i]).text());//拿到点击按钮的当前那条信息的内容 放到一个数组里
        }
        console.log(arr);
        layer.open({
            type: 2,
            skin: 'CLYM-style',
            area: ['800px','450px'],
            title: '编辑菜单栏目',
            content: 'admin-permission-edit.html',
            success: function(layero, index){
                var body = layer.getChildFrame('body',index);//建立父子联系
                var selectList = body.find('select');
                var inputList = body.find('input');
                //id单独处理
                $(inputList[0]).val(arr[0]);
                for (var i = 1; i <= selectList.length; i++) {
                    var optionList = $(selectList[i - 1]).find('option');

                    for (var j = 0; j <= optionList.length; j++) {

                        if (arr[i] == $(optionList[j]).text()) {
                            //console.log(arr[i])
                            $(optionList[j]).attr("selected", true);
                        }
                        /*如果optionList.length等于0，表示此次遍历读取的是父级菜单，
                         由于弹出编辑窗口时，父级菜单下拉表初始值为空，所以直接添加一个option*/
                        if (optionList.length == 0) {
                            $("<option selected></option>")
                                .append(arr[i])
                                .attr("value", arr[arr.length - 1])
                                .appendTo($(selectList[i -1 ]));
                        }
                    }
                }

                for(var i = selectList.length + 1, j = 1; i <= inputList.length; i++, j++) {
                    $(inputList[j]).val(arr[i]);
                }
            }
        });
    });

    /*分页加载菜单数据*/
    function menuPageData(url, param, paramVal) {
        //param、paramVal仅用于搜索时

        //以下将以jquery.ajax为例，演示一个异步分页
        $.getJSON(url + '1', {
                rows : 10, //每页显示的数据条数
                searchParam : param,
                searchVal : paramVal
            },
            function (res) { //从第1页开始请求。返回的json格式可以任意定义
                laypage({
                    cont: 'page-nav', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
                    pages: res.pageCount, //通过后台拿到的总页数
                    curr: 1, //初始化当前页
                    skin: '#5a98de',//皮肤颜色
                    groups: 5, //连续显示分页数
                    skip: true, //是否开启跳页
                    count:res.total, //数据总数
                    limit: 10, //每页显示的条数。laypage将会借助 count 和 limit 计算出分页数
                    limits:[10, 20, 30, 40, 50], //每页条数的选择项。如果 layout 参数开启了 limit，则会出现每页条数的select选择框
                    first: '首页', //若不显示，设置false即可
                    last: '尾页', //若不显示，设置false即可
                    prev: '上一页', //若不显示，设置false即可
                    next: '下一页', //若不显示，设置false即可
                    jump: function (e) { //触发分页后的回调
                        $.getJSON(url + e.curr, {
                            rows : 10, //每页显示的数据条数
                            searchParam : param,
                            searchVal : paramVal
                        }, function (res) {

                            var data = res.rows;
                            $("#total-text").html(res.total);
                            /*解析表格分页数据*/
                            analysisTableData(data);

                        });
                    }
                });
            });
    }

    /*解析表格分页数据*/
    function analysisTableData(data) {
        //每次执行解析前都先清空之前的数据
        $("#tb-body").empty();
        var n = data.length;
        for (var i = 0; i < n; i++) {
            var td1 = $("<td></td>").append($("<input  type='checkbox'></input>").attr("value", data[i].menuId));
            var td2 = $("<td></td>").append(data[i].menuId);
            var td3 = $("<td></td>").append(data[i].menuName);
            var td4 = $("<td></td>").append(data[i].parentName);
            var td5 = $("<td></td>").append(data[i].url);
            var td6 = $("<td></td>").append($("<i class='Hui-iconfont'></li>").append(data[i].icon));
            var td7 = $("<td></td>").append(data[i].perms);

            var typeName = null;
            if (data[i].type == 0) {
                typeName = "顶级菜单";
            } else if (data[i].type == 1) {
                typeName = "栏目";
            } else if (data[i].type == 2) {
                typeName = "按钮";
            }

            var td8 = $("<td></td>").append(typeName);
            var td9 = $("<td></td>").append(data[i].orderNum);
            var td11 = $("<td style='display:none'></td>").append(data[i].parentId);
            var td10 = $("<td></td>")
                .append($("<a ></a>")
                    .addClass("ml-5 edit")
                    .attr("style", "text-decoration:none;display:inline-block;")
                    .append($("<li class='Hui-iconfont'></li>").append("&#xe6df;")))
                .append($("<a ></a>")
                    .attr("title", "删除")
                    .attr("href", "javascript:;")
                    .attr("onclick", "menu_del(this,'" + data[i].menuId + "')")
                    .addClass("ml-5")
                    .attr("style", "text-decoration:none;display:inline-block;")
                    .append($("<li class='Hui-iconfont'></li>").append("&#xe6e2;")));
            $("#tb-body").append($("<tr></tr>").addClass("text-c menuDetail")
                .append(td1)
                .append(td2)
                .append(td8)
                .append(td4)
                .append(td3)
                .append(td5)
                .append(td6)
                .append(td7)
                .append(td9)
                .append(td11)
                .append(td10));
        }
    }

    /*
     参数解释：
     title	标题
     url		请求的url
     id		需要操作的数据id
     w		弹出层宽度（缺省调默认值）
     h		弹出层高度（缺省调默认值）
     */
    /*菜单-添加*/
    function menu_add(title,url,w,h){
        layer_show(title,url,w,h);
    }


    /*菜单-删除*/
    function menu_del(obj,id){
        layer.confirm('角色删除须谨慎，确认要删除吗？',function(index){
            $.ajax({
                type : "delete",
                url : "../../menu/" + id,
                dataType : "json",
                statusCode : {
                    200 : function () {
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!',{icon:1,time:2000});
                    },
                    500 : function () {
                        layer.msg('操作失败！服务器出错', {
                            time : 1000,
                            icon: 5
                        });
                    }
                }
            });

        });
    }
</script>

</body>
</html>

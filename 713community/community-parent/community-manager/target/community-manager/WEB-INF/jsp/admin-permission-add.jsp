<%--
  Created by IntelliJ IDEA.
  User: 先
  Date: 2017/10/19
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <LINK rel="Bookmark" href="http://static.community.com/favicon.ico" >
    <LINK rel="Shortcut Icon" href="http://static.community.com/favicon.ico" />
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
    <!--[if IE 6]>
    <script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <!--/meta 作为公共模版分离出去-->

    <title>添加用户 - H-ui.admin v2.3</title>
    <meta name="keywords" content="">
   </head>
<body>
<article class="page-container">
    <form action="" class="form form-horizontal" id="form-menu-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">类型：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" size="1" id="menu-type" name="type">
					<option value="" selected>请选择菜单类型</option>
					<option value="0">顶级菜单</option>
					<option value="1">栏目</option>
					<option value="2">按钮</option>
				</select>
				</span> </div>
        </div>
        <div class="row cl" id="parent_id" hidden="hidden">
            <label class="form-label col-xs-4 col-sm-3">父级菜单：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" id="parentId" size="1" name="parentId">

                </select>
				</span> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>菜单名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="menuName" name="menuName">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>URL：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value=""  id="url" name="url">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>图标icon：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value=""  name="icon" id="icon">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限perms：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value=""  name="perms" id="perms">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>顺序序号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value=""  name="orderNum" id="orderNum">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="button" id="submit-menu-btn" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="http://static.community.com/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="http://static.community.com/lib/layer/2.1/layer.js"></script>
<script type="text/javascript" src="http://static.community.com/lib/icheck/jquery.icheck.min.js"></script>
<script type="text/javascript" src="http://static.community.com/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
<script type="text/javascript" src="http://static.community.com/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="http://static.community.com/lib/jquery.validation/1.14.0/messages_zh.min.js"></script>
<script type="text/javascript" src="http://static.community.com/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="http://static.community.com/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    $(function() {
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });


        });

        /*提交表单数据*/
        $("#submit-menu-btn").click(function() {
            layer.msg('确定添加？', {
                time : 0,
                btn : ['确定', '取消'],
                yes : function(index) {
                    layer.close(index);
                    $.ajax({
                        type : "post",
                        data : $("#form-menu-add").serialize(),
                        url : "../../menu",
                        dataType : "json",
                        statusCode : {
                            201 : function() {
                                layer.msg('添加成功', {
                                    time : 0,
                                    icon: 6,
                                    btn: ['确定'],
                                    yes : function() {
                                        var current_window = parent.layer.getFrameIndex(window.name);
                                        parent.$('#btn-refresh').click();
                                        parent.layer.close(current_window);
                                    }
                                });
                            },
                            500 : function () {
                                layer.msg('操作失败！服务器出错', {
                                    time : 0,
                                    icon: 5,
                                    btn: ['返回']
                                });
                            }
                        }
                    });
                }
            })
        });

        /*显示父级菜单*/
        $("#menu-type").change(function() {

            var type_val = $("#menu-type option:selected").val();

            if (type_val == 0) {
                $("#parent_id").hide();
            } else {
                $.ajax({
                    type : "get",
                    url : "../menu/type/" + type_val,
                    dataType : "json",
                    statusCode : {
                        200 : function(data) {
                            //添加数据前，先清空之前的数据
                            $("#parentId").empty();

                            $("<option selected></option>")
                                .append("请选择父级菜单")
                                .appendTo("#parentId");
                            for (var i = 0; i < data.length; i++) {
                                var option = $("<option></option>")
                                    .append(data[i].menuName)
                                    .attr("value", data[i].menuId)
                                    .appendTo("#parentId");
                            }
                        },
                        404 : function () {
                            layer.msg('请求结果为空！', {
                                time : 1000,
                                icon: 5
                            });
                        },
                        500 : function () {
                            layer.msg('操作失败！服务器出错', {
                                time : 1000,
                                icon: 5
                            });
                        }
                    }

                });
                $("#parent_id").show();
            }


        });





</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>

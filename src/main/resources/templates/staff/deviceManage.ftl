<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8" />
    <title>数据列表页面</title>
    <!-- layui.css -->
    <link href="${ctx!}/js/plugins/layui/css/layui.css" rel="stylesheet" />
    <style>
        tr td:not(:nth-child(0)),
        tr th:not(:nth-child(0)) {
            text-align: center;
        }
        /*可选*/
        .layui-laypage > * {
            float: left;
        }
        .layui-field-title .layui-field-box{
            padding: 10px 20px 10px 30px;
        }
        .layui-table-cell{
            padding-top: 4px;
            height: 45px;
        }
        .star-so{
             text-align: center;
             margin-bottom: 10px;
             margin-top: 40px;
        }
        .star-so input.layui-input{
            width: 200px;
            display: inline-block;
        }
    </style>
</head>
<body>

<fieldset id="dataList" class="layui-elem-field layui-field-title sys-list-field">
    <legend style="text-align:center;">AchieveIT设备管理</legend>


    <#--<div class="layui-row">-->
        <#--<div class="layui-form layui-col-md12 star-so">-->
            <#--<input class="layui-input" placeholder="请输入关键字" name="keyword">-->

            <#--<button class="layui-btn" id="search" "><i class="layui-icon">&#xe615;</i></button>-->
        <#--</div>-->
    <#--</div>-->
    <div style="padding: 40px 0px 0px 80px;position: relative;right: 100px;" align="right">
            <div class="layui-inline">
                <div class="layui-input-inline" style="width:auto">
                    <a id="addDevice" class="layui-btn layui-btn-normal">添加设备</a>
                </div>
                <button class="layui-btn" onclick="javascript:location.replace(location.href)">
                    <i class="layui-icon">&#x1002;</i>
                </button>
            </div>
        </div>


    <div class="layui-field-box">
        <div id="dataContent" class="">
            <table class="layui-hide" id="device" lay-filter="table"></table>
            <script type="text/html" id="operator">
                <a class="layui-btn layui-btn-normal" lay-event="record">借用记录</a>
                <#--TODO 编辑按钮是否显示-->
                <#--<a class="layui-btn" lay-event="edit">编辑</a>-->
                <a class="layui-btn layui-btn-danger " lay-event="del">删除</a>
            </script>
            <script type="text/html" id="status">
                <form class="layui-form" action="">
                    <div class="layui-form-item" style="margin:0;">
                        {{#  if(d.pro_status == 0){ }}
                        项目待审批
                        {{#  } else if (d.pro_status == 1 ){ }}
                        项目上级审批通过
                        {{#  } else if (d.pro_status == -1 ){ }}
                        项目上级审批拒绝
                        {{#  } }}
                    </div>
                </form>
                <#--<button class="layui-btn layui-btn-small layui-btn-normal" onclick="layui.datalist.editData({{d.id}})"><i class="layui-icon">&#xe642;</i></button>-->
            </script>
        </div>
    </div>
</fieldset>



<!-- layui.js -->
<script src="${ctx!}/js/plugins/layui/layui.js"></script>
<!-- layui规范化用法 -->
<script type="text/javascript">
    layui.config({
        base: '${ctx}/js/'
    }).use('staff/deviceManage');
</script>
</body>
</html>




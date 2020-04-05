<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 员工</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <link href="${ctx!}/js/plugins/layui/css/layui.css" rel="stylesheet" media="all"/>
    <style type="text/css">
        p{
            margin: 0;
            padding: 0;
        }
        .layui-input{
            height:30px;
        }
        .layui-table-cell {
            overflow: initial;
        }
        .layui-layer-content{
            overflow: visible !important;
        }
        .layui-form-selectup dl {
            top: 42px;
            bottom: unset;
        }
    </style>
</head>

<body>

<fieldset id="dataList" class="layui-elem-field layui-field-title sys-list-field">
    <legend style="text-align:center;">调整当前员工角色</legend>
</fieldset>

<div >
    <button class="layui-btn" id="getCheckData">提交选中行数据</button>
</div>
<div class="container-fluid larry-wrapper">
    <div id="dataContent" class="">
        <table class="layui-hide" id="staff" lay-filter="table"></table>
        <script type="text/html" id="selectTpl">
            <select name="interest" lay-filter="classifyDemo">
                <option value=""></option>
                {{#  layui.each(options, function(index, option){ }}
                <option value="{{option.value}}" {{option.value == d.classify ? 'selected=""': ''}}>{{option.text}}</option>
                {{#  }); }}
            </select>
        </script>
    </div>

</div>

<script src="${ctx!}/js/plugins/layui/layui.js"></script>
<script src="${ctx!}/js/common.js"></script>
<!-- layui规范化用法 -->
<script type="text/javascript">
    layui.config({
        base: '${ctx}/js/'
    }).use('staff/modifyMemberRole');
</script>
</body>

</html>

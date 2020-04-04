<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 详情</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="${ctx!}/js/plugins/layui/css/layui.css" rel="stylesheet" />
    <style type="text/css">
        .layui-form-item{
            margin: 20px 0 0 200px
        }
        p{
            margin: 0;
            padding: 0;
        }
        .layui-input-block {
            margin-left: 150px;
        }
        .tag,
        .tag-defined {
            display: inline-block;
            position: relative;
            padding: 0 20px;
            border: 1px solid #DDD;
            border-radius: 2px;
            cursor: pointer;
            line-height: 36px;
            margin: 0 10px 10px 0;
        }

        .tag-selected {
            border: 1px solid #5FB878;
            color: #5FB878;
        }

        .tick-box {
            display: none;
        }

        .tag .tick-bg {
            position: absolute;
            right: 0;
            bottom: 0;
            border: 10px solid;
            border-color: transparent #5FB878 #5FB878 transparent;
        }

        .tag .tick {
            position: absolute;
            right: 0;
            bottom: -12px;
            font-size: 10px;
            color: #FFF;
        }
        .imgbox{
            margin-top: 20px;
            font-size: 16px;
            color: red;
        }
        .layui-input-block{
            margin-left: 110px;
        }
    </style>
</head>

<body>

<fieldset id="dataList" class="layui-elem-field layui-field-title sys-list-field">
    <legend style="text-align:center;">AchieveIT项目详情</legend>
</fieldset>
<div class="container-fluid larry-wrapper">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <section class="panel panel-padding">
                <form id="form1" class="layui-form "  lay-filter="form">

                <#--<div class="layui-form-item">-->
                <#--<input type="hidden" name="id"  value="${(project.id)!}" >-->
                <#--</div>-->

                    <div class="layui-form-item" style="margin-top: 10px">
                        <label class="layui-form-label">项目名称：</label>
                        <div class="layui-input-inline">

                            <input readonly="readonly" id="pro_name" type="text"  name="name" lay-verify="required"   placeholder="请输入项目采用技术(必填）" value="${project.pro_name}"
                                    class="layui-input ">

                        </div>
                        <#--<span style="color: red">必填</span>-->
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">采用技术：</label>
                        <div class="layui-input-block">
                            <input readonly="readonly" id="pro_tech" type="text"  name="tech"   placeholder="无"
                                    class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">业务领域：</label>
                        <div class="layui-input-block">
                            <input readonly="readonly" id="pro_area" type="text"  name="area"   placeholder="无"
                                    class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">核心功能：</label>
                        <div class="layui-input-block">
                            <input readonly="readonly" id="pro_func" type="text"  name="func"   placeholder="无"
                                    class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">开始时间：</label>
                        <div class="layui-input-block">
                            <input readonly="readonly" id="pro_startdate" type="text"  name="startdate"   placeholder="以“YYYY-MM-DD”为例(必填）"
                                   autocomplete="off" class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">结束时间：</label>
                        <div class="layui-input-block">
                            <input readonly="readonly" id="pro_enddate" type="text"  name="enddate"   placeholder="以“YYYY-MM-DD”为例(必填）"
                                   autocomplete="off" class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">项目描述：</label>
                        <div class="layui-input-block">
                            <textarea readonly="readonly" id="pro_desc" name="desc" placeholder="请输入内容描述(必填）"  class="layui-textarea" style="width: 60%">${(project.pro_desc)!}</textarea>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">项目成员：</label>
                        <div class="layui-input-block">
                            <input readonly="readonly" id="pro_member" type="text"  name="menber"   placeholder="无"
                                   class="layui-input ">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">项目仓库：</label>
                        <div class="layui-input-block">
                            <input readonly="readonly" id="giturl" type="text"  name="giturl"   placeholder="无"
                                   class="layui-input ">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">根目录：</label>
                        <div class="layui-input-block">
                            <input readonly="readonly" id="root" type="text"  name="root"   placeholder="无"
                                   class="layui-input ">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">分配大小：</label>
                        <div class="layui-input-block">
                            <input readonly="readonly" id="disk_size" type="text"  name="disk_size"   placeholder="无"
                                   class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item" style="text-align:center;">
                        <button class="layui-btn" lay-submit lay-filter="edit" id ="edit">编辑</button>
                    </div>



                </form>
            </section>
        </div>
    </div>
</div>

<script src="${ctx!}/js/plugins/layui/layui.js"></script>
<script src="${ctx!}/js/common.js"></script>
<!-- layui规范化用法 -->
<script type="text/javascript">
    layui.config({
        base: '${ctx}/js/'
    }).use('staff/detail');
</script>
</body>

</html>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 学生信息</title>
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
    <legend style="text-align:center;">AchieveIT项目申报</legend>
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
                        <label class="layui-form-label">项目名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="name" lay-verify="required"  placeholder="请输入项目名" value="${project.name}"
                                   autocomplete="off" class="layui-input ">

                        </div>
                        <span style="color: red">必填</span>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">采用技术：</label>
                        <div class="layui-input-block">
                            <input type="text" name="tech"   placeholder="请输入项目采用技术"
                                   autocomplete="off" class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">业务领域：</label>
                        <div class="layui-input-block">
                            <input type="text" name="area"   placeholder="请输入项目业务领域"
                                   autocomplete="off" class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">核心功能：</label>
                        <div class="layui-input-block">
                            <input type="text" name="func"   placeholder="请输入项目核心功能"
                                   autocomplete="off" class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item">
                    <label class="layui-form-label">项目开始时间:</label>
                    <div class="layui-input-block">
                        <input type="text" name="startdate"   placeholder="以“XXXX-YY-DD为例”"
                               autocomplete="off" class="layui-input ">
                    </div>
                </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">项目结束时间:</label>
                        <div class="layui-input-block">
                            <input type="text" name="enddate"   placeholder="以“XXXX-YY-DD为例”"
                                   autocomplete="off" class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">描述：</label>
                        <div class="layui-input-block">
                                <textarea id="description" name="desc" placeholder="请输入内容：必填"  class="layui-textarea" style="width: 60%">${(project.desc)!}</textarea>
                        </div>
                    </div>





                    <div class="layui-form-item">
                        <button class="layui-btn" lay-submit lay-filter="add">立即提交</button>
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
    }).use('staff/form');
</script>
</body>

</html>

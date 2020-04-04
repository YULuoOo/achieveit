<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 配置管理</title>
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
    <legend style="text-align:center;">配置</legend>
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
                        <label class="layui-form-label">代码仓库：</label>
                        <div class="layui-input-inline">
                            <input type="text" name="giturl" lay-verify="required" id="giturl" placeholder="请输入代码仓库"
                                  autocomplete="off" class="layui-input ">
                        </div>
                    </div>


                    <div class="layui-form-item">
                        <label class="layui-form-label">根目录：</label>
                        <div class="layui-input-inline">
                            <input type="text" id="root" name="root" lay-verify="required"  placeholder="请输入根目录"
                                  autocomplete="off" class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">分配大小：</label>
                        <div class="layui-input-block">
                            <input type="text" id="disk_size" name="disk_size" lay-verify="required"  placeholder="请输入分配大小"
                                  autocomplete="off" class="layui-input ">
                        </div>
                    </div>




                    <div class="layui-form-item" align="center">
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
    }).use('staff/config');
</script>
</body>

</html>

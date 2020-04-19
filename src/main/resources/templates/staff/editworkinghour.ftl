<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 工时信息</title>
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
    <legend style="text-align:center;">AchieveIT工时详情</legend>
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
                        <label class="layui-form-label">工作日期：</label>
                        <div class="layui-input-inline">
                            <input readonly="readonly" id="work_date" type="text"  name="work_date"   placeholder="请输入工作日期"
                                                               autocomplete="off" class="layui-input ">
                        </div>
                    </div>


                    <div class="layui-form-item">
                        <label class="layui-form-label">工作时长：</label>
                        <div class="layui-input-inline">
                            <input readonly="readonly" id="work_length" type="text"  name="work_length"   placeholder="请输入工作时长" class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">工作内容：</label>
                        <div class="layui-input-block">
                            <input readonly="readonly" id="work_content" type="text"  name="work_content"   placeholder="请输入工作内容" class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">工时状态：</label>
                        <div class="layui-input-inline">
                            <input readonly="readonly" id="state" type="text"  name="state" class="layui-input ">
                        </div>
                    </div>

                    <div class="layui-form-item" style="style="margin-left: 230px">
                       <button class="layui-btn" lay-submit lay-filter="edit" id ="edit">编辑</button>
                       <button class="layui-btn" lay-submit lay-filter="accept" id ="accept">通过</button>
                       <button class="layui-btn" lay-submit lay-filter="refuse" id ="refuse">拒绝</button>
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
    }).use('staff/editworkinghour');
</script>
</body>

</html>

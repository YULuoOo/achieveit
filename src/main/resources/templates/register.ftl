<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 注册</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="${ctx!}/js/plugins/layui/css/layui.css" rel="stylesheet">
    <link href="${ctx!}/css/login.css" rel="stylesheet">
    <script src="${ctx!}/js/plugins/layui/layui.js" charset="utf-8" type="text/javascript"></script>
    <script src="${ctx!}/js/common.js" charset="utf-8" type="text/javascript"></script>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body>
    <div class="layui-carousel video_mask" id="login_carousel" >
        <div carousel-item>
            <div class="carousel_div1"></div>
            <div class="carousel_div2"></div>
            <div class="carousel_div3"></div>
        </div>
        <div class="login layui-anim layui-anim-up">
            <h1>公司项目信息管理系统</h1>
            <form class="layui-form" action="${ctx!}/regi" method="post">
                <div class="layui-form-item">
                    <input type="text" name="num" lay-verify="number" placeholder="请输入学号或者帐号" autocomplete="off"  value="" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <input type="text" name="name" lay-verify="string" placeholder="请输入名字" autocomplete="off"  value="" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <input type="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" value="" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <input type="text" name="email" lay-verify="string" placeholder="请输入邮箱" autocomplete="off"  value="" class="layui-input">
                </div>

            
                 <div class="layui-input-block" style="margin-left: 0">
                    <select name="title" lay-verify="">
                        <option value="">请选择职位</option>
                        <option value="项目经理">项目经理</option>
                        <option value="项目成员">项目成员</option>
                        <option value="配置管理员">配置管理员</option>
                        <option value="EPG Leader">EPG Leader</option>
                        <option value="QA管理员">QA管理员</option>
                        <option value="项目上级">项目上级</option>
                    </select>
                </div>
                <div class="layui-input-block" style="margin-left: 0">
                    <select name="sex" lay-verify="">
                        <option value="">请选择性别</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </div>
                <button class="layui-btn login_btn" lay-submit="" lay-filter="regi">注册系统</button>
            </form>

        </div>

    </div>

</body>

</html>
<script>
    layui.config({
        base : "${ctx!}/js/"
    }).use(['form','layer','jquery','carousel', 'element'], function () {
        var $ = layui.jquery,
                form = layui.form,
                carousel = layui.carousel,
                layer = layui.layer;

        /**背景图片轮播*/
        carousel.render({
            elem: '#login_carousel',
            width: '100%',
            height: '100%',
            interval:3000,
            arrow: 'none',
            anim: 'fade',
            indicator:'none'
        });

        //监听登陆提交
        form.on('submit(regi)', function (data) {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/regi",
                data: data.field,
                success: function(ret){
                    console.log(ret);
                    if(ret.isOk){
                        layer.msg("操作成功", {time: 2000},function(){
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index);
                            window.location.href=ret.returnUrl;
                        });
                    }else{
                        layer.msg(ret.msg, {time: 2000});
                    }
                }
            });
            return false;
        });
    });
</script>
layui.define(['element', 'layer', 'form','laydate','upload','tags'], function (exports) {
    var form = layui.form,
        laydate = layui.laydate,
        upload = layui.upload,
        tags = layui.tags,
        $ = layui.jquery;
    tags.init();
    //自定义验证
    // form.verify({
    //     name: function (value) {
    //         if (value.length <= 0 || value.length > 20) {
    //             return "名称必须1到20位"
    //         }
    //     },
    //     // description:function (value) {
    //     //     if (value.length <= 1 || value.length > 100) {
    //     //         return "描述必须2到00位"
    //     //     }
    //     // }
    //
    // });
    //TODO 【查】 页面有参数 ?id=  调用getQueryVariable可获取id值
    //TODO 调用url:"/staff/project/" + id + "/get" 可获取ach_project对象(ret)
    //TODO 然后在界面初始化时将对象的数据显示在界面上
    $.ajax({
        type: "GET",
        dataType: "json",
        url:"staff/project/"+getQueryVariable("id")+"/get",
        success:function (ret) {
            if(ret != null){
                $("#pro_name").html(ret.pro_name);
                $("#pro_tech").html("123456");
                document.getElementsByName("area").item(0).innerHTML = getQueryVariable("id");
            }else{
                $("#pro_name").html("3333");
                $("#pro_tech").html("123");
                document.getElementsByName("area").item(0).innerHTML = getQueryVariable("id");
            }
        }
    });

    function getQueryVariable(variable)
    {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    }


    form.on('submit(edit)', function (data) {
        $.ajax({
            //TODO 【改】 将input变为readonly = false 将编辑按钮变为确定按钮 确定后update数据库
            type: "POST",
            dataType: "json",
            url: "/staff/project/create",
            data: data.field,
            success: function(ret){
                if(ret.isOk){
                    layer.msg("操作成功", {time: 2000},function(){
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index);
                        window.location.href = "/staff/form";
                    });
                }else{
                    layer.msg(ret.msg, {time: 2000});
                }
            }
        });
        console.log(data);
        return false;
    });

    exports('staff/detail', {});
});
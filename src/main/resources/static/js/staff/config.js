layui.define(['element', 'layer', 'form','laydate','upload','tags'], function (exports) {
    var form = layui.form,
        laydate = layui.laydate,
        upload = layui.upload,
        tags = layui.tags,
        $ = layui.jquery,
        state = 0;
    tags.init();


    $.ajax({
        type: "GET",
        dataType: "json",
        url:"/config/"+getQueryVariable("id")+"/get",
        success:function (ret) {
            if(ret.isOk){
                $("#giturl").val(ret.data.giturl);
                $("#root").val(ret.data.root);
                $("#disk_size").val(ret.data.disk_size);
            }else{
                layer.msg(ret.msg, {time: 2000});
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


    form.on('submit(add)', function (data) {
        $.ajax({
            type: "POST",
            dataType: "json",
            url:"/config/"+getQueryVariable("id")+"/update",
            data: data.field,
            success: function(ret){
                if(ret.isOk){
                    layer.msg("操作成功", {time: 2000},function(){
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index);
                        parent.location.reload()
                    });
                }else{
                    layer.msg(ret.msg, {time: 2000});
                }
            }
        });
        console.log(data);
        return false;
    });

    exports('staff/config', {});
});
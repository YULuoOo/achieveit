layui.define(['element', 'layer', 'form','laydate','upload','tags'], function (exports) {
    var form = layui.form,
        laydate = layui.laydate,
        upload = layui.upload,
        tags = layui.tags,
        $ = layui.jquery,
        state = 0;
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


    $.ajax({
        type: "GET",
        dataType: "json",
        url:"/staff/workinghour/"+getQueryVariable("id")+"/get",
        success:function (ret) {
            if(ret.isOk){
                $("#work_date").val(new Date(ret.data.work_date).format("yyyy-MM-dd"));
                $("#work_length").val(ret.data.work_length);
                $("#work_content").val(ret.data.work_content);
            }else{
                layer.msg(ret.msg, {time: 2000});
            }
        }
    });

    Date.prototype.format = function(fmt)
    { //author: meizz
        var o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "h+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }

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
        console.log("点击编辑按钮");
        if(state == 0){
            $("#work_date").removeAttr("readonly");
            $("#work_length").removeAttr("readonly");
            $("#work_content").removeAttr("readonly");
            $("#edit").html("提交");
            laydate.render({
             elem: '#work_date'
             ,format: 'yyyy-MM-dd'
            });
            state = 1;
         }else{
            $.ajax({
                        //TODO 【改】 将input变为readonly = false 将编辑按钮变为确定按钮 确定后update数据库
                        type: "PUT",
                        dataType: "json",
                        url:"/staff/workinghour/"+getQueryVariable("id")+"/update",
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
        }
        console.log(data);
        return false;
    });

    exports('staff/editworkinghour', {});
});
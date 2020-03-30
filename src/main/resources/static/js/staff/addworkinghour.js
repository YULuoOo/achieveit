layui.define(['element', 'layer', 'form','laydate','upload','tags'], function (exports) {
    var form = layui.form,
        laydate = layui.laydate,
        upload = layui.upload,
        tags = layui.tags,
        $ = layui.jquery;
    tags.init();
    //自定义验证
    form.verify({
        name: function (value) {
            if (value.length <= 0 || value.length > 20) {
                return "名称必须1到20位"
            }
        },
        // description:function (value) {
        //     if (value.length <= 1 || value.length > 100) {
        //         return "描述必须2到00位"
        //     }
        // }

    });
    laydate.render({
        elem: '#test'
        ,format: 'yyyy-MM-dd'
      });

    form.on('submit(add)', function (data) {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/staff/workinghour/create",
            data: data.field,
            success: function(ret){
                if(ret.isOk){
                    layer.msg("操作成功", {time: 2000},function(){
//                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
//                        parent.layer.close(index);
//                        parent.location.reload()
//                                top.layui.element.tabDelete("tab", top.jQuery(".layui-tab-title .layui-this").attr("lay-id"));
//
//                       var fileName = window.location.href.match(/.*\/(.*\.*)/i)[1];
//                       $.each(parent.layui.$('#LAY_app_tabsheader>li'), function(index, li) {
//                           var $li = $(li);
//                           if ($li.attr('lay-id').indexOf(fileName) > -1) {
//                               $li.find('.layui-tab-close').trigger('click');
//                               return
//                           }
//                       })
//                       window.location.href = "/staff/index";
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

    form.on('select(genre)', function(data){

        // console.log(data.elem); //得到select原始DOM对象
        console.log(data.value); //得到被选中的值
        // console.log(data.othis); //得到美化后的DOM对象
        if(data.value =="团体赛"){

            $("#group").show();
        }else{
            $("#group").find(".tag").remove();
            $("#group").find("[name='groups']").val("");
            $("#group").hide();
        }
    });
    exports('staff/addworkinghour', {});
});

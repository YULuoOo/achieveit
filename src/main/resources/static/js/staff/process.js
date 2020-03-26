layui.define([ 'layer',  'table','common'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        common = layui.common,
        table  = layui.table ;
    table.render({
        elem: '#student'
        ,height: 'full-200'
        ,method:'GET'
        ,url: '/staff/project/process' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'pro_name', align:'center', title: '项目名称',unresize:true}
            ,{field: 'pro_desc', align:'center', title: '项目描述',unresize:true}
            ,{field: 'pro_tech', align:'center', title: '采用技术',unresize:true}
            ,{field: 'pro_area', align:'center', title: '业务领域',unresize:true}
            ,{field: 'pro_func', align:'center', title: '主要功能',unresize:true}
            ,{title: '项目状态',templet: '#status',unresize:true}
            ,{fixed: 'right',  title:'操作',align:'center', toolbar: '#operator',templet: '#operator',unresize:true}
        ]]
    });

    //监听工具条
    table.on('tool(table)', function(obj){
        var data = obj.data;
        if(obj.event === 'accept'){
            console.log(111);
            accept(data.id);
        }else if(obj.event === 'refuse'){
            refuse(data.id);
        }
    });



    function accept(id) {
        layer.confirm('确定通过审批？', function (index) {
            $.ajax({
                type: "PUT",
                dataType: "json",
                url: "/staff/project/" + id + "/accept",
                success: function (ret) {
                    if (ret.isOk) {
                        layer.msg("操作成功", {time: 2000}, function () {
                            layer.close(index);
                            window.location.href = "/staff/process";
                        });
                    } else {
                        layer.msg(ret.msg, {time: 2000});
                    }
                }
            });
        });
    }
    function refuse(id) {
        layer.confirm('确定拒绝审批？', function (index) {
            $.ajax({
                type: "PUT",
                dataType: "json",
                url: "/staff/project/" + id + "/refuse",
                success: function (ret) {
                    if (ret.isOk) {
                        layer.msg("操作成功", {time: 2000}, function () {
                            layer.close(index);
                            window.location.href = "/staff/process";
                        });
                    } else {
                        layer.msg(ret.msg, {time: 2000});
                    }
                }
            });
        });
    }

    //搜索
    $('#search').click(function () {

        table.reload('app', {
            url: "/appinfo/app/search"
            ,where: {keyword:keyword} //设定异步数据接口的额外参数
            //,height: 300
        });
    });

    exports('staff/process');
});
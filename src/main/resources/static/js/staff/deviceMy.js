layui.define([ 'layer',  'table','common'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        common = layui.common,
        table  = layui.table ;
    table.render({
        elem: '#device'
        ,height: 'full-200'
        ,method:'GET'
        ,url: '/staff/device/listbyborrower' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'name', align:'center', title: '名称',unresize:true}
            ,{field: 'category', align:'center', title: '类别',unresize:true}
            ,{field: 'owner', align:'center', title: '资产管理者',unresize:true}
            ,{field: 'state', align:'center', title: '状态',unresize:true}
            ,{field: 'condition', align:'center', title: '损坏情况',unresize:true}
            ,{field: 'borrower', align:'center', title: '借用者',unresize:true}
            ,{field: 'borrow_date', align:'center', title: '借用日期',unresize:true,tmplet:'#createBorrowTime'}
            ,{fixed:'right',title:'操作',align:'center', toolbar: '#operator',width:200,unresize:true}
        ]]
    });

    //监听工具条
    table.on('tool(table)', function(obj){
        var data = obj.data;
        if(obj.event === 'return'){
            returndevice(data);
            // common.frame_show('借用记录','/staff/deviceBorrow?id='+data.id);

        }
    });

    //借用设备
    $('#borrowDevice').click(function () {
        var index = layer.load(1);
        setTimeout(function () {
            layer.close(index);
            common.frame_show('可借设备','/staff/deviceBorrowable');
            // layer.msg('打开添加窗口');
        }, 500);
    });


    //输出接口，主要是两个函数，一个删除一个编辑
    var datalist = {
        deleteData: function (id) {
            layer.confirm('确定删除？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                del(id);
            }, function () {

            });
        },
        editData: function (id) {
            common.frame_show('编辑','/staff/editworkinghour?id='+id);
        }
    };
    function returndevice(data) {
        layer.confirm('确认归还么？', function (index) {
            $.ajax({
                type: "PUT",
                dataType: "json",
                url: "/staff/device/" + data.id + "/return",
                data:data,
                success: function (ret) {
                    if (ret.isOk) {
                        layer.msg("操作成功", {time: 2000}, function () {
                            layer.close(index);
                            window.location.href = "/staff/deviceMy";
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

    exports('staff/deviceMy', datalist);
});




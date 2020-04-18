layui.define([ 'layer',  'table','common'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        common = layui.common,
        table  = layui.table ;
    table.render({
        elem: '#deviceBorrow'
        ,height: 'full-200'
        ,method:'GET'
        ,url: '/staff/device/'+getQueryVariable("id")+'/borrowlist' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'borrower', align:'center', title: '借用者',unresize:true}
            ,{field: 'state', align:'center', title: '状态',unresize:true}
            ,{field: 'borrow_date', align:'center', title: '申请日期',unresize:true,tmplet:'#createBorrowTime'}
            ,{field: 'return_date', align:'center', title: '归还日期',unresize:true,templet:'#createReturnTime'}
            ,{field: 'condition', align:'center', title: '损坏情况',edit:'text',unresize:true}
            ,{field: 'detail', align:'center', title: '备注',edit:'text',unresize:true}
            ,{fixed:'right',title:'操作',align:'center', toolbar: '#operator',width:200,unresize:true}
        ]]
    });

    //监听工具条
    table.on('tool(table)', function(obj){
        var data = obj.data;
        if(obj.event === 'accept'){
            console.log(data.state);
            console.log(data.id);
            console.log(data.condition);
            console.log(data.detail);
            accept(data)
            // common.frame_show('查看','/staff/editworkinghour?id='+data.id);

        }
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

    function accept(data) {
        layer.confirm('确认通过么？', function (index) {
            $.ajax({
                type: "Put",
                dataType: "json",
                url: "/staff/device/" + data.id + "/borrowaccept",
                data:data,
                success: function (ret) {
                    if (ret.isOk) {
                        layer.msg("操作成功", {time: 2000}, function () {
                            layer.close(index);
                            window.location.href = "/staff/deviceBorrow";
                            parent.location.reload();
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

    exports('staff/deviceBorrow', datalist);
});




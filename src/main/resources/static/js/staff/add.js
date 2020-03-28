/**
 项目JS主入口
 以依赖layui的layer和form模块为例
 **/
layui.define(['layer', 'form',  'table','common'], function(exports){
    var $ = layui.jquery,
        layer = layui.layer,
        common = layui.common,
        table  = layui.table ;
    table.render({
        elem: '#staff'
        ,method:'GET'
        ,url: '/staff/project/'+getQueryVariable("id")+'/others'
        ,page: true //开启分页
        ,cols: [[ //表头
            {type:'checkbox'}
            ,{field: 'id', align:'center', title: '员工id',unresize:true}
            ,{field: 'num', align:'center', title: '员工工号',unresize:true}
            ,{field: 'name', align:'center', title: '员工姓名',unresize:true}
        ]]
        ,id: 'idTest'
    });

    //提交新分配的员工
    $('#getCheckData').click(function () {
        var checkStatus = table.checkStatus('idTest')
            ,data = checkStatus.data;
        let arr = [];
        data.forEach((item) => {
            arr.push(
                item.id+""
            );
        });
        console.log(arr);
        if (arr === undefined || arr.length == 0) {
            // array empty or does not exist
            layer.msg("没有新增成员", {time: 2000});
        } else{
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/staff/project/"+getQueryVariable("id")+"/add",
                data: {'check' : arr},
                traditional:true,
                success: function(ret){
                    if(ret.isOk){
                        layer.msg("操作成功", {time: 2000},function(){
                            //var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            //parent.layer.close(index);
                            //window.location.href = "/staff/add";
                            table.reload('idTest', {
                                url: '/staff/project/'+getQueryVariable("id")+'/others'
                                ,where: {} //设定异步数据接口的额外参数
                                //,height: 300
                            });
                        });
                    }else{
                        layer.msg(ret.msg, {time: 2000});
                    }
                }
            });
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

    exports('staff/add', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
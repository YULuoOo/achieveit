layui.define([ 'layer',  'table','common'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        common = layui.common,
        table  = layui.table ;
    table.render({
        elem: '#student'
        ,height: 'full-200'
        ,method:'GET'
        ,url: '/staff/workinghour/list' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'staff_num', align:'center', title: '用户账号',unresize:true}
            ,{field: 'staff_name', align:'center', title: '用户名称',unresize:true}
            ,{field: 'work_content', align:'center', title: '工作内容',unresize:true}
            ,{field: 'work_date', align:'center', title: '工作日期',unresize:true,templet :'#createTime'}
            ,{field: 'work_length', align:'center', title: '工作时长',unresize:true}
            ,{field: 'state', align:'center', title: '状态',unresize:true}
            ,{fixed: 'right',  title:'操作',align:'center', toolbar: '#operator',unresize:true}
        ]]
    });

    //监听工具条
    table.on('tool(table)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            common.frame_show('查看','/staff/editworkinghour?id='+data.id);
            // if($("#detail-view-"+data.id).length > 0){
            //     $("#detail-view-"+data.id).hide();
            //     $("#detail-view-"+data.id).remove();
            // }else{
            //     createHtml(obj);
            //     $("#detail-view-"+data.id).show();
            // }

        }else if(obj.event === 'del'){
            del(data.id);
        }
    });

    //添加工时
    $('#addWorkingHour').click(function () {
        var index = layer.load(1);
        setTimeout(function () {
            layer.close(index);
            common.frame_show('添加','/staff/addworkinghour');
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
    function del(id) {
        layer.confirm('确定删除么？', function (index) {
            $.ajax({
                type: "DELETE",
                dataType: "json",
                url: "/staff/workinghour/" + id + "/del",
                success: function (ret) {
                    if (ret.isOk) {
                        layer.msg("操作成功", {time: 2000}, function () {
                            layer.close(index);
                            window.location.href = "/staff/workinghour";
                        });
                    } else {
                        layer.msg(ret.msg, {time: 2000});
                    }
                }
            });
        });
    }

    function createHtml(obj) {
        var data = obj.data;
        var expert = data.expert;
        var groups = data.groups;
        var ereason,treason,group;
        if(expert == null){
            ereason = '此项目还未分配专家审核';
        }else{
            ereason = data.ereason;
        }
        if(groups ==="个人赛无团队"){
            group = "此项目为个人赛";
        }else{
            group ="组员学号："+groups;
        }
        if(data.treason === null){
            treason = "教师还未审核";
        }else{
            treason = data.treason;
        }
        var detailHtml = '';
        detailHtml += '<tr class="detail-view" style="display: none" id="detail-view-'+data.id+'">';
        detailHtml += '<td colspan="10"><blockquote class="layui-elem-quote" style="line-height: 30px;text-align:left;padding-left: 30px;">';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">项目描述:</div>'+data.desc+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">团队性质：</div>'+group+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">项目任务书下载地址:</div><a href="'+data.book.downloadUrl+'">'+data.book.downloadUrl+'</a></br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">展示视频下载地址:</div><a href="'+data.video.downloadUrl+'">'+data.video.downloadUrl+'</a></br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">指导教师工号:</div>'+data.teacher.num+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">指导教师姓名:</div>'+data.teacher.name+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">指导教师意见:</div>'+treason+'</br>';
        detailHtml += '<div class="layui-inline layui-word-aux" style="width: 150px">专家意见:</div>'+ereason+'</br>';
        detailHtml += '</blockquote></td></tr>';
        obj.tr.after(detailHtml);
    }
    //搜索
    $('#search').click(function () {

        table.reload('app', {
            url: "/appinfo/app/search"
            ,where: {keyword:keyword} //设定异步数据接口的额外参数
            //,height: 300
        });
    });

    exports('staff/workinghour', datalist);
});




<html>

<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

<#include "../admin/nav.ftl">
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">

            <table class="layui-hide"  lay-filter="content" id="content"></table>

        </div>
    </div>

</div>

<script>
  var dataList = ${list};
  layui.use('layer', function(){
      var layer = layui.layer;

  });
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#content'
            ,data:dataList
            ,cols: [[
                {type:'numbers',}
                ,{field:'shopName', width:180,title: '商店名称'}
                ,{field:'shopTypeName', width:100, title: '经营类型', sort: true}
                ,{field:'personName', width:100, title: '申请人'}
                ,{field:'personId', title: '身份证号'}
                ,{field:'phone',width:150, title: '电话'}
                ,{field:'shopAddress', title: '商铺地址', sort: true}
                ,{field:'createTime',  title: '创建时间',sort: true}
                ,{templet:'#Tpl', width:100, field:'delFlag', title: '审核状态',sort: true}
                ,{toolbar: '#barDemo', title: '操作'}
            ]]
            ,page: true
        });

        //监听行工具事件
        table.on('tool(content)', function(obj){
            var id = obj.data.id;
              console.log(id);

            if(obj.event === 'detail'){
                top.layer.open({
                    type: 2,
                    area: ['80%', '80%'],
                    title: '详情',
                    maxmin: true, //开启最大化最小化按钮
                    content: '/sell/admin/detail?id='+id,
                });

            } else   if(obj.event === 'success'){

                $.ajax({
                    url:'/sell/admin/success?id='+id,
                    success:function (result) {
                        layer.msg(result, function(){
                            window.parent.location.reload();//刷新父页面
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index); //再执行关闭

                        });

                    }

                });

            }
            else   if(obj.event === 'fail'){
                $.ajax({
                    url:'/sell/admin/fail?id='+id,
                    success:function (result) {
                        layer.msg(result, function(){
                            window.parent.location.reload();//刷新父页面
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index); //再执行关闭

                        });

                    }

                });

            }

        })


    });


</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">图片详情</a>
    {{#  if(d.delFlag === 0){ }}
    <a class="layui-btn layui-btn-xs" lay-event="success">通过</a>
    <a class="layui-btn layui-btn-xs" lay-event="fail">不通过</a>
    {{#  } }}

</script>
<script type="text/html" id="Tpl">

    {{#  if(d.delFlag === 0){ }}
     未审核
    {{#  } else { }}
    {{#  if(d.delFlag === 1){ }}
    审核通过
    {{#  } else { }}
    审核不通过
    {{#  } }}
    {{#  } }}
</script>
</body>
</html>
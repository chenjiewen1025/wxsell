<html>

<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

<#include "../common/nav.ftl">
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">

            <table class="layui-hide"  lay-filter="content" id="content"></table>

        </div>
    </div>

</div>

<script>
  var dataList = ${orderMasterList};
  layui.use('layer', function(){
      var layer = layui.layer;

  });
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#content'
            ,data:dataList
            ,cols: [[
                {field:'orderId', width:200, title: '订单id'}
                ,{field:'buyerName', title: '姓名'}
                ,{field:'buyerPhone',  title: '手机号', sort: true}
                ,{field:'buyerAddress',  title: '地址'}
                ,{field:'orderAmount', title: '金额'}
                ,{field:'orderStatusMes', title: '订单状态', sort: true}
                ,{field:'payStatusMes',  title: '支付状态', sort: true}
                ,{field:'createTime',  title: '创建时间',sort: true}
                ,{toolbar: '#barDemo', width:100,title: '操作'}
            ]]
            ,page: true
        });

        //监听行工具事件
        table.on('tool(content)', function(obj){
            var id = obj.data.orderId;
             // console.log(id);

            if(obj.event === 'detail'){
                top.layer.open({
                    type: 2,
                    area: ['80%', '80%'],
                    title: '详情',
                    maxmin: true, //开启最大化最小化按钮
                    content: '/sell/seller/order/detail?orderId='+id,


                });

            }

        })


    });


</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">详情</a>

</script>
</body>
</html>
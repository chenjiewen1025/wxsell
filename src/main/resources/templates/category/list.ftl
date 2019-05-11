<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <button class="layui-btn" onclick="add()">新增</button>
            <table class="layui-hide"  lay-filter="content" id="content"></table>


        </div>
    </div>

</div>
<script>
    var dataList = ${categoryList};
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#content'
            ,data:dataList
            ,cols: [[
                {field:'categoryId', width:200, title: '类目id'}
                ,{field:'categoryName', title: '名字'}
                ,{field:'categoryType',  title: 'type', sort: true}
                ,{field:'createTime',  title: '创建时间',sort: true}
                ,{field:'updateTime',  title: '修改时间',sort: true}
                ,{toolbar: '#barDemo', width:100,title: '操作'}
            ]]
            ,page: true
        });

        //监听行工具事件
        table.on('tool(content)', function(obj){
            var id = obj.data.categoryId;
            // console.log(id);

            if(obj.event === 'detail'){
                top.layer.open({
                    type: 2,
                    area: ['60%', '60%'],
                    title: '详情',
                    maxmin: true, //开启最大化最小化按钮
                    content: '/sell/seller/category/index?categoryId='+id,


                });

            }

        })


    });

    function add() {
        layer.open({
            type: 2,
            area: ['60%', '60%'],
            title: '新增',
            maxmin: true, //开启最大化最小化按钮
            content: '/sell/seller/category/index',


        });

    }
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">修改</a>

</script>
</body>
</html>
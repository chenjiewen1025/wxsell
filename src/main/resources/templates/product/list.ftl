<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
<style>
.layui-table-cell{
    height:auto !important;
}
</style>
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <button class="layui-btn" onclick="add()">新增</button>
            <table class="layui-hide"  lay-filter="content" id="content"></table>
            <#--<div class="row clearfix">-->
                <#--<div class="col-md-12 column">-->
                    <#--<div class="container">-->
                        <#--<div class="row clearfix">-->
                            <#--<div class="col-md-4 column">-->
                                <#--<a type="button" class="btn btn-default btn-primary" href="/sell/seller/product/list?type=0">在架列表</a>-->
                            <#--</div>-->
                            <#--<div class="col-md-4 column">-->
                                <#--<a type="button" class="btn btn-default btn-primary" href="/sell/seller/product/list">全部列表</a>-->
                            <#--</div>-->
                            <#--<div class="col-md-4 column">-->
                                <#--<a type="button" class="btn btn-default btn-primary" href="/sell/seller/product/list?type=1">下架列表</a>-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<table class="table table-bordered table-condensed">-->
                        <#--<thead>-->
                        <#--<tr>-->
                            <#--<th>商品id</th>-->
                            <#--<th>名称</th>-->
                            <#--<th>图片</th>-->
                            <#--<th>单价</th>-->
                            <#--<th>库存</th>-->
                            <#--<th>描述</th>-->
                            <#--<th>类目</th>-->
                            <#--<th>创建时间</th>-->
                            <#--<th>修改时间</th>-->
                            <#--<th>状态</th>-->
                            <#--<th colspan="3">操作</th>-->
                        <#--</tr>-->
                        <#--</thead>-->
                        <#--<tbody>-->

                        <#--<#list productInfoPageInfo.list as productInfo>-->
                        <#--<tr>-->
                            <#--<td>${productInfo.productId}</td>-->
                            <#--<td>${productInfo.productName}</td>-->
                            <#--<td><img height="100" width="100" src="${productInfo.productIcon}" alt=""></td>-->
                            <#--<td>${productInfo.productPrice}</td>-->
                            <#--<td>${productInfo.productStock}</td>-->
                            <#--<td>${productInfo.productDescription}</td>-->
                            <#--<td>${productInfo.categoryType}</td>-->
                            <#--<td>${productInfo.createTime}</td>-->
                            <#--<td>${productInfo.updateTime}</td>-->
                            <#--<td>-->
                            <#--${productInfo.getProductStatusEnum().message}-->
                            <#--</td>-->
                            <#--<td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>-->

                            <#--<td>-->
                                <#--<#if productInfo.getProductStatusEnum().message == "在架">-->
                                    <#--<a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">下架</a>-->
                                <#--<#else>-->
                                    <#--<a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">上架</a>-->
                                <#--</#if>-->
                            <#--</td>-->
                            <#--<td><a href="/sell/seller/product/delete?productId=${productInfo.productId}">删除</a></td>-->
                        <#--</tr>-->
                        <#--</#list>-->
                        <#--</tbody>-->
                    <#--</table>-->
                <#--</div>-->

            <#--&lt;#&ndash;分页&ndash;&gt;-->
                <#--<div class="col-md-12 column">-->
                    <#--<ul class="pagination pull-right">-->
                    <#--<#if productInfoPageInfo.pageNum lte 1>-->
                        <#--<li class="disabled"><a href="#">上一页</a></li>-->
                    <#--<#else>-->
                        <#--<li><a href="/sell/seller/order/list?page=${productInfoPageInfo.pageNum - 1}&size=${productInfoPageInfo.pageSize}">上一页</a></li>-->
                    <#--</#if>-->

                    <#--<#list 1..productInfoPageInfo.pages as index>-->
                        <#--<#if productInfoPageInfo.pageNum == index>-->
                            <#--<li class="disabled"><a href="#">${index}</a></li>-->
                        <#--<#else>-->
                            <#--<li><a href="/sell/seller/order/list?page=${index}&size=${productInfoPageInfo.pageSize}">${index}</a></li>-->
                        <#--</#if>-->
                    <#--</#list>-->

                    <#--<#if productInfoPageInfo.pageNum gte productInfoPageInfo.pages>-->
                        <#--<li class="disabled"><a href="#">下一页</a></li>-->
                    <#--<#else>-->
                        <#--<li><a href="/sell/seller/order/list?page=${productInfoPageInfo.pageNum + 1}&size=${productInfoPageInfo.pageSize}">下一页</a></li>-->
                    <#--</#if>-->
                    <#--</ul>-->
                <#--</div>-->
            <#--</div>-->
        </div>
    </div>

</div>
<script>
    function add() {
        layer.open({
            type: 2,
            area: ['60%', '60%'],
            title: '新增',
            maxmin: true, //开启最大化最小化按钮
            content: '/sell/seller/product/index',


        });

    }
</script>
<script>
    var dataList = ${productInfoList};
    layui.use('layer', function(){
        var layer = layui.layer;

    });
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#content'
            ,data:dataList
            ,cols: [[
                {field:'productId', width:200, title: '商品id'}
                ,{field:'productName', title: '名称'}
                ,{field:'productIcon',  title: '图片',templet: '#Tpl'}
                ,{field:'productPrice', width:80, title: '单价', sort: true}
                ,{field:'productStock', width:80,title: '库存'}
                ,{field:'productDescription', title: '描述'}
                ,{field:'categoryType',  title: '类目', sort: true}
                ,{field:'productStatusEnumMsg',  title: '状态', sort: true}
                ,{field:'createTime',  title: '创建时间',sort: true}
                ,{field:'updateTime',  title: '创建时间',sort: true}
                ,{toolbar: '#barDemo', width:200,title: '操作'}
            ]]
            ,page: true
        });

        //监听行工具事件
        table.on('tool(content)', function(obj){
            var id = obj.data.productId;
            // console.log(id);

            switch (obj.event) {
                case 'edit':{
                    top.layer.open({
                        type: 2,
                        area: ['80%', '80%'],
                        title: '详情',
                        maxmin: true, //开启最大化最小化按钮
                        content: '/sell/seller/product/index?productId='+id,


                    });
                    break;
                }
                case 'del':{
                    $.ajax({
                        url:'/sell/seller/product/delete?productId='+id,
                        success:function (result) {
                            layer.msg(result, function(){
                                window.parent.location.reload();//刷新父页面
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭

                            });

                        }

                    });
                    break;
                }

                case 'up' :{

                    $.ajax({
                        url:'/sell/seller/product/on_sale?productId='+id,
                        success:function (result) {
                            layer.msg(result, function(){
                                window.parent.location.reload();//刷新父页面
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭

                            });

                        }

                    });
                    break;

                }

                case 'down' :{
                    $.ajax({
                        url:'/sell/seller/product/off_sale?productId='+id,
                        success:function (result) {
                            layer.msg(result, function(){
                                window.parent.location.reload();//刷新父页面
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭

                            });

                        }

                    });
                    break;
                }

                default:break;



            }

        })


    });

</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-xs" lay-event="del">删除</a>
    {{#  if(d.productStatusEnumMsg === '在架'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="down">下架</a>
    {{#  } else { }}
    <a class="layui-btn layui-btn-xs" lay-event="up">上架</a>
    {{#  } }}
</script>
<script type="text/html" id="Tpl">
    <img height="100" width="100" src="{{d.productIcon}}">
</script>

</body>
</html>
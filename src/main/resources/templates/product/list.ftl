<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="container">
                        <div class="row clearfix">
                            <div class="col-md-4 column">
                                <a type="button" class="btn btn-default btn-primary" href="/sell/seller/product/list?type=0">在架列表</a>
                            </div>
                            <div class="col-md-4 column">
                                <a type="button" class="btn btn-default btn-primary" href="/sell/seller/product/list">全部列表</a>
                            </div>
                            <div class="col-md-4 column">
                                <a type="button" class="btn btn-default btn-primary" href="/sell/seller/product/list?type=1">下架列表</a>
                            </div>
                        </div>
                    </div>
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>状态</th>
                            <th colspan="3">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list productInfoPageInfo.list as productInfo>
                        <tr>
                            <td>${productInfo.productId}</td>
                            <td>${productInfo.productName}</td>
                            <td><img height="100" width="100" src="${productInfo.productIcon}" alt=""></td>
                            <td>${productInfo.productPrice}</td>
                            <td>${productInfo.productStock}</td>
                            <td>${productInfo.productDescription}</td>
                            <td>${productInfo.categoryType}</td>
                            <td>${productInfo.createTime}</td>
                            <td>${productInfo.updateTime}</td>
                            <td>
                            ${productInfo.getProductStatusEnum().message}
                            </td>
                            <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>

                            <td>
                                <#if productInfo.getProductStatusEnum().message == "在架">
                                    <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
                                <#else>
                                    <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
                                </#if>
                            </td>
                            <td><a href="/sell/seller/product/delete?productId=${productInfo.productId}">删除</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if productInfoPageInfo.pageNum lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${productInfoPageInfo.pageNum - 1}&size=${productInfoPageInfo.pageSize}">上一页</a></li>
                    </#if>

                    <#list 1..productInfoPageInfo.pages as index>
                        <#if productInfoPageInfo.pageNum == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${index}&size=${productInfoPageInfo.pageSize}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if productInfoPageInfo.pageNum gte productInfoPageInfo.pages>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${productInfoPageInfo.pageNum + 1}&size=${productInfoPageInfo.pageSize}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
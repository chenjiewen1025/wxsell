<html>

<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

<#include "../common/nav.ftl">
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list orderMasterList.list as orderDTO>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.buyerName}</td>
                            <td>${orderDTO.buyerPhone}</td>
                            <td>${orderDTO.buyerAddress}</td>
                            <td>${orderDTO.orderAmount}</td>
                            <td>${orderDTO.getOrderStatusEnum().message}</td>
                            <td>${orderDTO.getPayStatusEnum().message}</td>
                            <td>${orderDTO.createTime}</td>
                            <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                            <td>
                            <#if orderDTO.getOrderStatusEnum().message == "新订单">
                                    <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>

                            </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <#--分页-->
                <div class="container">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <ul class="pagination pull-right">
                               <#if orderMasterList.pageNum lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                               <#else>
                        <li><a href="/sell/seller/order/list?page=${orderMasterList.pageNum - 1}&size=${orderMasterList.pageSize}">上一页</a></li>
                               </#if>

                    <#list 1..orderMasterList.pages as index>
                        <#if orderMasterList.pageNum == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${index}&size=${orderMasterList.pageSize}">${index}</a></li>
                        </#if>
                    </#list>

                               <#if orderMasterList.pageNum gte orderMasterList.pages>
                        <li class="disabled"><a href="#">下一页</a></li>
                               <#else>
                        <li><a href="/sell/seller/order/list?page=${orderMasterList.pageNum + 1}&size=${orderMasterList.pageSize}">下一页</a></li>
                               </#if>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

</div>

</body>
</html>
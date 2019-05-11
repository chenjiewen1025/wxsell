<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

   <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>订单总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.orderAmount}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            <#--订单详情表数据-->
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>商品名称</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTO.orderDetailList as orderDetail>
                        <tr>
                            <td>${orderDetail.productId}</td>
                            <td>${orderDetail.productName}</td>
                            <td>${orderDetail.productPrice}</td>
                            <td>${orderDetail.productQuantity}</td>
                            <td>${orderDetail.productQuantity * orderDetail.productPrice}</td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--操作-->
                <div class="col-md-12 column">
                <#if orderDTO.getOrderStatusEnum().message == "新订单">
                    <a onclick="finish('${orderDTO.orderId}')" type="button" class="btn btn-default btn-primary">完结订单</a>
                    <a onclick="cancel('${orderDTO.orderId}')"type="button" class="btn btn-default btn-danger">取消订单</a>
                </#if>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use('layer', function(){
        var layer = layui.layer;

    });

    function cancel(id) {
        $.ajax({
            url:'/sell/seller/order/cancel?orderId='+id,
            success:function (result) {
                layer.msg(result, function(){
                    window.parent.location.reload();//刷新父页面
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭

                });

            }

        });
    }

    function finish(id) {
        $.ajax({
            url:'/sell/seller/order/finish?orderId='+id,
            success:function (result) {
                layer.msg(result, function(){
                    window.parent.location.reload();//刷新父页面
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭

                });
            }

        });
    }

</script>
</body>
</html>
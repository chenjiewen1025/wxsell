<html>
<#include "../common/header.ftl">

<body>

        <div class="container" style="width: 400px">
            <div class="row clearfix"style="border:1px solid #000">
        <div class="layui-col-md12">
            <h2 style="text-align: center;">${seller.shopName!}</h2>
            <br><br>
            <h4 style="text-align: left;">订单号：${orderDTO.orderId!}</h4>
            <h4 style="text-align: left;margin-top: 15px">订单日期：${orderDTO.createTime!}</h4>
            <hr style="margin-top: 15px">
        </div>
                <div class="layui-col-md12">
                    <table class="table table-bordered" border=1 width="80%" frame=void >
                        <thead>
                        <tr>
                            <th>商品名称</th>
                            <th>价格/数量</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTO.orderDetailList as orderDetail>
                        <tr>
                            <td>${orderDetail.productName}</td>
                            <td>${orderDetail.productPrice}/${orderDetail.productQuantity}</td>
                            <td>${orderDetail.productQuantity * orderDetail.productPrice}</td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>

            </div>
                <h3 style="text-align: center;margin-top: 15px">总金额：${orderDTO.orderAmount!}</h3>
                <h3 style="text-align: center;margin-top: 15px">支付成功！</h3>


                <h3 style="text-align: center;margin-top: 15px">收货人信息</h3>
                <h4 style="text-align: center;margin-top: 15px">姓名：${orderDTO.buyerName!}</h4>
                <h4 style="text-align: center;margin-top: 15px">电话：${orderDTO.buyerPhone!}</h4>
                <h4 style="text-align: center;margin-top: 15px">地址：${orderDTO.buyerAddress!}</h4>
        </div>

<script>
    layui.use('layer', function(){
        var layer = layui.layer;

    });

    $(document).ready(function(){
        if (${able!}=='1')
        {
            window.print();
        }

    });
</script>
</body>
</html>
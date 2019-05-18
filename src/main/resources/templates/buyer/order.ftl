<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>SUI Mobile 使用</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
</head>

<style type="text/css">
    .infinite-scroll-preloader {
        margin-top:-20px;
    }
    .round_icon{
        height:46px;
        display: flex;
        border-radius: 50%;
        align-items: center;
        justify-content: center;
        overflow: hidden;
    }
    .shop{
        margin-top: 10px;
        margin-left: 40px;
        height:80px;

        display: flex;
        align-items: center;
        justify-content: center;
        overflow: hidden;

    }
    .card{
        margin-bottom: 0.5rem;
        margin-top: 10px;
        margin-left: 0rem;
        margin-right: 0rem;
    }





</style>
<body>
<!-- page集合的容器，里面放多个平行的.page，其他.page作为内联页面由路由控制展示 -->
<div class="page-group">
    <!-- 单个page ,第一个.page默认被展示-->
    <div class="page">
        <!-- 标题栏 -->
        <header class="bar bar-nav">
            <h1 class="title">订单</h1>
        </header>

        <nav class="bar bar-tab">
            <a class="tab-item external " href="/sell/buyer/index">
                <span class="icon icon-home"></span>
                <span class="tab-label">外卖</span>
            </a>
            <a class="tab-item external active" onclick="goOrder()">
                <span class="icon icon-me"></span>
                <span class="tab-label">订单</span>
            </a>
            <a class="tab-item external " href="/sell/buyer/myMess">
                <span class="icon icon-star"></span>
                <span class="tab-label">我的</span>
            </a>

        </nav>

        <!-- 这里是页面内容区 -->
        <div class="content  pull-to-refresh-content"  data-ptr-distance="55" >
            <!-- 默认的下拉刷新层 -->
            <div class="pull-to-refresh-layer">
                <div class="preloader"></div>
                <div class="pull-to-refresh-arrow"></div>
            </div>


                <div style="text-align: center;margin-top: 30px">

                    <#list orderVoList as item>
                    <a style="color: #0C0C0C;margin-top: 10px" onclick="godetail('${item.orderId}')">
                    <div class="card" style="height: 140px">
                        <div class="row" style="height: 100px">
                            <div class="col-30"><img class="shop" src="${item.img}" alt=""></div>
                            <div class="col-40"><h4>
                                ${item.name}</h4>
                                <h6 style="margin-top: -15px;">
                                   ${item.time}
                                </h6>
                                <h6 style="margin-top: -20px;">${item.num}件商品</h6>
                            </div>
                            <div class="col-20"><h4>
                                <#if item.status == 1>
                                    完结
                                    <#elseif item.status == 0>
                                    配送中
                                <#elseif item.status == 2>
                                   取消
                                </#if>
                            </h4>
                                <h4>
                                    ￥${item.amount}元</h4>

                            </div>
                        </div>

                        <div class="row" style="height: 40px;">
                            <#if item.status == 0>
                                <div class="col-50">
                                    <a style="display: inline;line-height: 2rem" href="#"
                                       class="button button-round" onclick="cancel('${item.orderId}')">取消</a>
                                </div>
                            </#if>
                                 <#if item.status == 0>
                                <div class="col-50">
                                    <a style="display: inline;line-height: 2rem" href="#"
                                       class="button button-round" onclick="success('${item.orderId}')">完结</a>
                                </div>
                                 </#if>
                             <#if item.orderComment == 0>
                            <div class="col-50">
                                <a style="display: inline;line-height: 2rem" href="#"
                                   class="button button-round" onclick="comment('${item.orderId}')">评价</a>
                            </div>
                             <#else >
                             已评价
                             </#if>

                        </div>
                    </div>


                    </a>
                </#list>
                </div>

            </div>

        </div>


</div>


<link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
<!-- 默认必须要执行$.init(),实际业务里一般不会在HTML文档里执行，通常是在业务页面代码的最后执行 -->
<!-- <script>$.init()</script> -->
<script type="text/javascript">
    $(document).ready(function () {
        $.init();


        if (getCookie("openId")==null)
        {
            window.location.href="http://chenjiewen.natapp1.cc/sell/wechat/authorize?returnUrl=http://chenjiewen.natapp1.cc/sell/buyer/index";

        }



    });

    function setCookie(name,value)
    {
        var exp = new Date();
        exp.setTime(exp.getTime() + 7200*1000);
        document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
    }
    function getCookie(name)
    {
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg))
            return unescape(arr[2]);
        else
            return null;
    }
    function goOrder() {
        window.location.href="/sell/buyer/order?openId="+getCookie("openId")
    }
    function cancel(id) {
        $.ajax({
            type:'get',
            url:'/sell/buyer/order/cancel?openId='+getCookie("openId")+'&orderId='+id,
            success:function (result) {
                $.toast(result.msg);
                history.go(0);
            },
        });

    }
    function success(id) {
        $.ajax({
            type:'get',
            url:'/sell/buyer/order/finish?orderId='+id,
            success:function (result) {
                $.toast(result);
                history.go(0);
            },
        });
    }
    function comment(id) {
        window.location.href="/sell/buyer/addcomment?orderId="+id;
    }
function godetail(id) {
    window.location.href="/sell/buyer/order/print?able=0&orderId="+id;
}
</script>

<script>
    // 添加'refresh'监听器
    $(document).on('refresh', '.pull-to-refresh-content',function(e) {
        history.go(0);
        console.log("刷新");
    });

</script>
</body>
</html>

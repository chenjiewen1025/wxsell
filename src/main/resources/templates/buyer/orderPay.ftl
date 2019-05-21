<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>订单</title>
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


        <!-- 这里是页面内容区 -->
        <div class="content  pull-to-refresh-content"  data-ptr-distance="55" >
            <form id="form" action="/sell/buyer/order/create">
            <div class="list-block">
                <ul>
            <li>
                <div class="item-content">
                    <div class="item-media"><i class="icon icon-form-name"></i></div>
                    <div class="item-inner">
                        <div class="item-title label">姓名</div>
                        <div class="item-input">
                            <input type="text" name="name" placeholder="Your name">
                        </div>
                    </div>
                </div>
            </li><li>
            <div class="item-content">
                <div class="item-media"><i class="icon icon-form-name"></i></div>
                <div class="item-inner">
                    <div class="item-title label">地址</div>
                    <div class="item-input">
                        <input type="text" name="address" placeholder="">
                    </div>
                </div>
            </div>
        </li><li>
            <div class="item-content">
                <div class="item-media"><i class="icon icon-form-name"></i></div>
                <div class="item-inner">
                    <div class="item-title label">手机</div>
                    <div class="item-input">
                        <input type="text" name="phone" placeholder="">
                    </div>
                </div>
            </div>
        </li>
                </ul>
            </div>
                <input type="hidden" name="sellerId" value="${sellerId}">
                <input type="hidden" id="openId" name="openid" value="${openId}">
                <textarea style="display: none" name="items" id="items">${items}</textarea>

            </form>

            <table class="table table-bordered" style="width: 100%">
                <thead>
                <tr>
                    <th>商品名称</th>
                    <th>价格/数量</th>
                    <th>总额</th>
                </tr>
                </thead>
                <tbody>
                        <#list orderMaster.orderDetailList as orderDetail>
                        <tr>
                            <td style="text-align: center">${orderDetail.productName}</td>
                            <td style="text-align: center">${orderDetail.productPrice}/${orderDetail.productQuantity}</td>
                            <td style="text-align: center">${orderDetail.productQuantity * orderDetail.productPrice}</td>
                        </tr>
                        </#list>
                </tbody>
            </table>

            <div class="col-50"><a href="#" onclick="onsub()" class="button button-big button-fill button-success">提交</a></div>

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

    function onsub() {
        $("#form").submit();
    }
</script>

<script>


</script>
</body>
</html>

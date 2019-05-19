<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>订单详情</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
    <link rel="stylesheet" href="/sell/layui/css/layui.css">
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

        height:80px;
        width: 80px;
        display: flex;
        align-items: center;
        justify-content: center;
        overflow: hidden;
        margin-right: 30px;
    }
    .card{
        margin-bottom: 0px;
        margin-top: 0.5rem;
        margin-left: 0rem;
        margin-right: 0rem;
    }
.menu-wrapper{
    flex: 0 0 80px;
    width: 25%;
   background: ghostwhite;
    margin-top: 15px;

}
    .food-wrapper{
        flex: 0 0 80px;
        width: 70%;
        background: ghostwhite;

    }

.menu-item{
    display: table;
    height: 40px;
    width: 100%;
    line-height: 40px;
    text-align: center;
    background-color: blanchedalmond;

}

    .wrap{
        width:100%;
        margin:0 auto;
        position: relative;
    }
    .left{
        width:30%;


        position: absolute;
        top:0;
        left:0;
    }
    .right{
        width:70%;

        position: absolute;
        top:0;
        right:0;
    }
    .pl{
        padding-right: 10px;
        overflow:hidden;
        text-overflow:ellipsis;
        background: goldenrod;
        white-space: nowrap;/*加宽度width属来兼容部分浏览*/
        text-align: center;
        padding-left: 10px;
    }
    　* {
        margin: 0;
        padding: 0;
        border: 0;
        outline: 0
    }

    ul,
    li {
        list-style: none;
    }

    a {
        text-decoration: none;
    }

    a:hover {
        cursor: pointer;
        text-decoration: none;
    }

    a:link {
        text-decoration: none;
    }

    img {
        vertical-align: middle;
    }

    .btn-numbox {
        overflow: hidden;
        margin-top: 20px;
    }

    .btn-numbox li {
        float: left;
    }

    .btn-numbox li .number,
    .kucun {
        display: inline-block;
        font-size: 12px;
        color: #808080;
        vertical-align: sub;
    }

    .btn-numbox .count {
        overflow: hidden;
        margin: 0;
    }

    .btn-numbox .count .num-jian,
    .input-num,
    .num-jia {
        display: inline-block;
        width: 28px;
        height: 28px;
        line-height: 28px;
        text-align: center;
        font-size: 18px;
        color: #999;
        cursor: pointer;
        border: 1px solid #e6e6e6;
    }
    .btn-numbox .count .input-num {
        width: 58px;
        height: 26px;
        color: #333;
        border-left: 0;
        border-right: 0;
    }
</style>
<body>
<!-- page集合的容器，里面放多个平行的.page，其他.page作为内联页面由路由控制展示 -->
<div class="page-group">
    <!-- 单个page ,第一个.page默认被展示-->
    <div class="page">
        <!-- 标题栏 -->
        <header class="bar bar-nav">

                 <h1 class="title">店铺名</h1>

        </header>


        <!-- 这里是页面内容区 -->
        <div class="content"  data-ptr-distance="44" >


            <div class="card" style="height: 150px">
                <div class="card-content">
                    <div class="card-content-inner"style="padding: 0">
                        <img class="shop" style="    margin-left: 167px;" src="
<#if seller.shopImg??>${seller.shopImg}</#if>" alt="">
                        <h2 style="text-align: center">${seller.shopName}</h2>

                        <a href="" class="open-about"><p class="pl"><#if seller.shopDes??>${seller.shopDes}</#if></p></a>
                    </div>
                </div>
            </div>

            <div class="buttons-tab fixed-tab" data-offset="44">
                <a href="#tab1" class="tab-link active button">点餐</a>
                <a href="#tab2" class="tab-link button">评价</a>
                <a href="#tab3" class="tab-link button">商家</a>
            </div>


            <div class="tabs">
                <div id="tab1" class="tab active">
                    <div class="content-block"style="padding: 0;margin: 0">

                        <#if productVOList??>

                        <div class="wrap"style="width: 100%">
                            <div class="left">
                                <#list productVOList as item>

                                    <li class="menu-item">
                                        <span class="text">${item.categoryName}</span>

                                    </li>
                                    <hr>
                                </#list>
                            </div>

                            <div class="right">
                                <#list productVOList as item>
                                    <#if item.productInfoVOList??>
                                    <ul id="${item.categoryType}">
                                     <#list item.productInfoVOList as item2>
                                    <div class="card" style="height: 120px">
                                        <div class="row" style="height: 100%;">
                                            <div class="col-30" style="    margin-top: 10px;
    margin-left: 20px;
    height: 80px;
    width: 80px;"><img class="shop" src=" ${item2.productIcon}" alt=""></div>
                                            <div class="col-70" style="margin-top: 10px;     position: absolute;display: inline"><h3>${item2.productName}</h3>
                                                <h6 style="margin-top: 5px;display: inline" >${item2.productPrice}<p></h6>
                                                <h6 style="margin-top: 5px;"> ${item2.productDescription}</h6>
                                                <#--<a style="left: 70px; display: inline;" href="#" class="button">加入</a>-->
                                                　　<ul class="btn-numbox" style="margin-top: -60px;  margin-left: 74px">
                                                    <li>
                                                        <ul class="count">
                                                            <span onclick="jian(this)" class="num-jian">-</span>
                                                            <input readonly type="text" class="input-num" value="0" />
                                                            <span onclick="jia(this)" class="num-jia">+</span>
                                                        </ul>
                                                    </li>

                                                    　　　  </ul>　　


                                            </div>
                                        </div>
                                    </div>
                                     </ul>
                                     </#list>

                                    </#if>
                            </#list>
                            </div>

                        </div>

                    </#if>

                    </div>

                </div>
                <div id="tab2" class="tab">
                    <div class="content-block">


                        <#list comments as item>
                            <div class="card" style="height: 100px">
                                <div class="row" style="height: 100%;">
                                    <div class="col-30"><img class="shop" src=" ${item.orderImg}" alt=""></div>
                                    <div class="col-70" style="margin-top: 10px;display: inline">
                                        <h3>${item.orderName}</h3>
                                        <h6 style="margin-top: 5px;display: inline" >${item.comment}<p></h6>
                                        <h6 style="margin-top: 5px;">
                                            <script>
                                                for (var i = 1;i<=${item.star!};i++)
                                                {
                                                    document.writeln("<span class=\"icon icon-star\"></span>");
                                                }
                                            </script>
                                           </h6>
                                    </div>
                                </div>
                            </div>


                    </#list>
                    </div>
                </div>
                <div id="tab3" class="tab">
                    <div class="content-block">

                        <img class="shop" style="    margin-left: 167px;" src="
<#if seller.shopImg??>${seller.shopImg}</#if>" alt="">
                        <h2 style="text-align: center">${seller.shopName}</h2>
                        <#if seller.shopDes??><h3>${seller.shopDes}</h3></#if>
                        <h3>电话：1888888888</h3>
                    </div>
                </div>
            </div>

    </div>
        <div id="car" style="    width: 100%;
    bottom: 46px;
    position: fixed;
    background-color:antiquewhite; display: none "> 211212 </div>

        <div style="width: 100%;
    bottom: 0;
    position: fixed;
    background-color: antiquewhite;
    height: 46px;">
            <a href=""class="open-shopcart" onclick="showcar()"><span style="font-size: 39px" class="icon icon-cart"></span></a>
            <span style="margin-left: 48px;
    font-size: 20px;">共XX件   xx元</span>
            <div style="position: fixed;
    /* margin-right: 0px; */
    width: 130px;margin-top: -43px;
    right: 0;">

                <#if seller.shopAble==1>
                <a href="#" class="button button-big button-fill button-success">结算</a>
                    <#else >
                  <a href="#" style="background-color: #5f646e;" class="button button-big button-fill button-success">暂停营业</a>
            </#if>

            </div>
        </div>
    </div>
    <div class="popup popup-about"style="background:rgba(0,0,0,.5);">
        <div class="content-block" style="color: aliceblue;
    font-size: 20px;" >
       ${seller.shopDes!0}
           <a href="" class="close-popup" style="text-align: center;
    position: fixed;
    bottom: 10px;
    color: coral;">关闭</a>
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
                // window.location.href="http://chenjiewen.natapp1.cc/sell/wechat/authorize?returnUrl=http://chenjiewen.natapp1.cc/sell/buyer/index";

            }



    });

    var isshow = 0;
    function showcar() {
        if (isshow==0)
        {
            document.all["car"].style.display="block"; //显示
            isshow = 1;
        }
        else {
            document.all["car"].style.display="none"; //显示
            isshow = 0;
        }


    }
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
    function goshopdetail(sellerId) {

        window.location.href="/sell/buyer/product/shopDetail?sellerId="+sellerId;
    }

function catego(re) {
    window.location.href="/sell/buyer/indexByCategory?category="+re;
}
    function goOrder() {
        window.location.href="/sell/buyer/order?openId="+getCookie("openId");
    }
</script>
<script>
    $(document).on('click','.open-about', function () {
        $.popup('.popup-about');
    });


</script>
<script>

    function jian(e) {
        var num_jia = $(e).next().next();
        var num_jian = $(e);
        var input_num = $(e).next();

        if(input_num.val() <= 0) {
            input_num.val(0);
        } else {

            input_num.val(parseInt(input_num.val()) - 1) ;
        }

    }
    function jia(e) {
        var num_jia = $(e);
        var num_jian = $(e).prev().prev();
        var input_num = $(e).prev();
        input_num.val(parseInt(input_num.val()) + 1);


    }





</script>
</body>
</html>

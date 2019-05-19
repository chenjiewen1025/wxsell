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
        width: 80px;
        display: flex;
        align-items: center;
        justify-content: center;
        overflow: hidden;
        margin-right: 30px;
    }
    .card{
        margin-bottom: 0.5rem;
        margin-top: 0.5rem;
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
            <#if type??>
                <h1 class="title">${type}</h1>
            <#elseif key??>
                  <h1 class="title">${key}</h1>
            <#else >

                 <h1 class="title">eee外卖平台</h1>
            </#if>
        </header>

        <nav class="bar bar-tab">
            <a class="tab-item external active" href="/sell/buyer/index">
                <span class="icon icon-home"></span>
                <span class="tab-label">外卖</span>
            </a>
            <a class="tab-item external" onclick="goOrder()">
                <span class="icon icon-me"></span>
                <span class="tab-label">订单</span>
            </a>
            <a class="tab-item external" href="/sell/buyer/myMess">
                <span class="icon icon-star"></span>
                <span class="tab-label">我的</span>
            </a>

        </nav>

        <div class="bar bar-header-secondary">

            <div class="searchbar">
                <a class="searchbar-cancel" onclick="search1()">确定</a>
                <div class="search-input">
                    <label class="icon icon-search" for="search"></label>
                    <input type="search"  id='search' placeholder='输入关键字...'/>
                </div>
            </div>
        </div>
        <!-- 这里是页面内容区 -->
        <div class="content  pull-to-refresh-content"  data-ptr-distance="55" >
            <!-- 默认的下拉刷新层 -->
            <div class="pull-to-refresh-layer">
                <div class="preloader"></div>
                <div class="pull-to-refresh-arrow"></div>
            </div>



                <div ><!-- Slider -->
                    <div class="swiper-container" data-space-between='10'>
                        <div class="swiper-wrapper" style="height: 40px;" align="center">
                            <div class="swiper-slide"><img src="//gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i1/TB1n3rZHFXXXXX9XFXXXXXXXXXX_!!0-item_pic.jpg_320x320q60.jpg" alt=""></div>
                            <div class="swiper-slide"><img src="//gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i4/TB10rkPGVXXXXXGapXXXXXXXXXX_!!0-item_pic.jpg_320x320q60.jpg" alt=""></div>
                            <div class="swiper-slide"><img src="//gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i1/TB1kQI3HpXXXXbSXFXXXXXXXXXX_!!0-item_pic.jpg_320x320q60.jpg" alt=""></div>
                        </div>

                    </div>
                    <#if categoryList??>
                <div class="swiper-container" style="margin-top: 12px;padding-bottom: 6px;" data-space-between='10'>
                    <div class="swiper-wrapper"  align="center">

                        <div class="swiper-slide">
                            <div class="row" style="height: 50%" >

                                <div class="col-20"style="height: 100%">
                                    <a onclick="catego('${categoryList[0].value!}')">
                                    <img src="${categoryList[0].img!}" class="round_icon"  alt="">
                                    <span style="font-size: 10px">${categoryList[0].name!}</span>
                                    </a>
                                </div>
                                <div class="col-20"style="height: 100%">
                                    <a onclick="catego('${categoryList[1].value!}')">
                                        <img src="${categoryList[1].img!}" class="round_icon"  alt="">
                                        <span style="font-size: 10px">${categoryList[1].name!}</span>
                                    </a>
                                </div>
                                <div class="col-20"style="height: 100%">
                                    <a onclick="catego('${categoryList[2].value!}')">
                                        <img src="${categoryList[2].img!}" class="round_icon"  alt="">
                                        <span style="font-size: 10px">${categoryList[2].name!}</span>
                                    </a>
                                </div>
                                <div class="col-20"style="height: 100%">
                                    <a onclick="catego('${categoryList[3].value!}')">
                                        <img src="${categoryList[3].img!}" class="round_icon"  alt="">
                                        <span style="font-size: 10px">${categoryList[3].name!}</span>
                                    </a>
                                </div>
                                <div class="col-20"style="height: 100%">
                                    <a onclick="catego('${categoryList[4].value!}')">
                                        <img src="${categoryList[4].img!}" class="round_icon"  alt="">
                                        <span style="font-size: 10px">${categoryList[4].name!}</span>
                                    </a>
                                </div>

                            </div>
                            <div class="row" style="height: 50%">
                                <div class="col-20"style="height: 100%">
                                    <a onclick="catego('${categoryList[5].value!}')">
                                        <img src="${categoryList[5].img!}" class="round_icon"  alt="">
                                        <span style="font-size: 10px">${categoryList[5].name!}</span>
                                    </a>
                                </div>   <div class="col-20"style="height: 100%">
                                <a onclick="catego('${categoryList[6].value!}')">
                                    <img src="${categoryList[6].img!}" class="round_icon"  alt="">
                                    <span style="font-size: 10px">${categoryList[6].name!}</span>
                                </a>
                            </div>   <div class="col-20"style="height: 100%">
                                <a onclick="catego('${categoryList[7].value!}')">
                                    <img src="${categoryList[7].img!}" class="round_icon"  alt="">
                                    <span style="font-size: 10px">${categoryList[7].name!}</span>
                                </a>
                            </div>   <div class="col-20"style="height: 100%">
                                <a onclick="catego('${categoryList[8].value!}')">
                                    <img src="${categoryList[8].img!}" class="round_icon"  alt="">
                                    <span style="font-size: 10px">${categoryList[8].name!}</span>
                                </a>
                            </div>   <div class="col-20"style="height: 100%">
                                <a onclick="catego('${categoryList[9].value!}')">
                                    <img src="${categoryList[9].img!}" class="round_icon"  alt="">
                                    <span style="font-size: 10px">${categoryList[9].name!}</span>
                                </a>
                            </div>
                            </div>

                        </div>
                    </div>

                </div>
                </#if>
            </div>

                <#list shopList as item>
                <a style="color: #0C0C0C" onclick="goshopdetail('${item.sellerId}')">
                <div class="card" style="height: 100px">
                    <div class="row" style="height: 100%">
                        <div class="col-30"><img class="shop" src="${item.shopImg!}" alt=""></div>
                        <div class="col-70"><h3>${item.shopName!}(
                             <#if item.shopAble == 1>
                                   正常营业
                             <#else>
                                    暂停营业
                             </#if>

                            )</h3>
                        <h6 style="margin-top: -15px;">
                            <script>
                                for (var i = 1;i<=${item.shopStar!};i++)
                                {
                                    document.writeln("<span class=\"icon icon-star\"></span>");
                                }
                            </script>

                            &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${item.shopStar!}
                        </h6>
                            <h6 style="margin-top: -20px;">${item.shopTypeName!}</h6>
                        </div>
                    </div>
                </div>
                </a>
            </#list>

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

        if ('${openId!}'!='')
        {
            setCookie("openId","${openId!}");
            setCookie("nickname","${nickname!}");
            setCookie("sexDesc","${sexDesc!}");
            setCookie("headingUrl","${headingUrl!}");

        }
        else {

            if (getCookie("openId")==null)
            {
                window.location.href="http://chenjiewen.natapp1.cc/sell/wechat/authorize?returnUrl=http://chenjiewen.natapp1.cc/sell/buyer/index";

            }
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
    function goshopdetail(sellerId) {

        window.location.href="/sell/buyer/product/list?sellerId="+sellerId;
    }

function catego(re) {
    window.location.href="/sell/buyer/indexByCategory?category="+re;
}
    function goOrder() {
        window.location.href="/sell/buyer/order?openId="+getCookie("openId");
    }
</script>

<script>
    // 添加'refresh'监听器
    $(document).on('refresh', '.pull-to-refresh-content',function(e) {
        history.go(0);
         console.log("刷新");
    });
function search1() {
    var sea = $("#search").val();

    if (sea==null||sea=='')
    {
        return;
    }
    else {
        window.location.href="/sell/buyer/indexBykey?key="+sea;
    }
}

</script>
</body>
</html>

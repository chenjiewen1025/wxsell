
<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                卖家管理系统
            </a>
        </li>

        <li>
            <a href="/sell/seller/order/list"><i class="fa fa-fw fa-list-alt"></i> 订单</a>
        </li>
        <li class="dropdown open">

                <li><a href="/sell/seller/product/list">商品管理</a></li>


        </li>
        <li class="dropdown open">

                <li><a href="/sell/seller/category/list">类目管理</a></li>

        </li>
        <li>
            <a href="/sell/seller/shop/list"><i class="fa fa-fw fa-list-alt"></i> 店铺管理</a>
        </li>
        <li>
            <a href="/sell/seller/password"><i class="fa fa-fw fa-list-alt"></i> 修改密码</a>
        </li>
        <li>
            <a href="/sell/seller/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
        </li>
    </ul>
</nav>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<#--弹窗-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单
            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>
    </div>
</div>
<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg" />
</audio>

<script>

    var websocket = null;
    if('WebSocket' in window) {
        websocket = new WebSocket('ws://35.220.208.164:8080/sell/webSocket');
    }else {
        alert('该浏览器不支持websocket!');
    }

    websocket.onopen = function (event) {
        console.log('建立连接');
    }

    websocket.onclose = function (event) {
        console.log('连接关闭');
    }

    websocket.onmessage = function (event) {
        console.log('收到消息:' + event.data)
        //弹窗提醒, 播放音乐
        $('#myModal').modal('show');

        document.getElementById("notice").play();
    }

    websocket.onerror = function () {
        alert('websocket通信发生错误！');
    }

    window.onbeforeunload = function () {
        websocket.close();
    }


</script>
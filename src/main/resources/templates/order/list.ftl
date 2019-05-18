<html>

<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

<#include "../common/nav.ftl">
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid ">
            <form class="layui-form" action="/sell/seller/order/list">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">手机号</label>
                    <div class="layui-input-inline">
                        <input type="tel" name="phone"
                         value="${phone!}"  lay-verify="required|phone" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 90px">订单状态</label>
                    <div class="layui-input-inline">
                        <select name="orderstatus" lay-filter="aihao">
                            <option value="3" <#if orderstatus==3> selected="selected"   </#if>>全部</option>
                            <option value="0" <#if orderstatus==0> selected="selected"   </#if>>新订单</option>
                            <option value="1"<#if orderstatus==1> selected="selected"   </#if>>完结</option>
                            <option value="2"<#if orderstatus==2> selected="selected"   </#if>>取消</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <button type="submit" class="layui-btn layui-btn-normal">搜索</button>
                </div>
            </form>

            </div>

            <table class="layui-hide"  lay-filter="content" id="content"></table>

        </div>
    </div>

</div>

<script>
  var dataList = ${orderMasterList};
  layui.use(['layer','form'], function(){
      var layer = layui.layer;
      var form = layui.form;

  });
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#content'
            ,data:dataList
            ,cols: [[
                {type:'numbers',title:'序号'}
                ,{field:'orderId', width:200, title: '订单id',totalRowText: '合计'}
                ,{field:'buyerName', title: '姓名'}
                ,{field:'buyerPhone',  title: '手机号', sort: true}
                ,{field:'buyerAddress',  title: '地址'}
                ,{field:'orderAmount', title: '金额',totalRow: true}
                ,{field:'orderStatusMes', title: '订单状态', sort: true}
                ,{field:'payStatusMes',  title: '支付状态', sort: true}
                ,{field:'createTime',  title: '创建时间',sort: true}
                ,{toolbar: '#barDemo', title: '操作'}

            ]]
            ,page: true
            ,even:true
            ,totalRow: true
            ,toolbar: true
        });

        //监听行工具事件
        table.on('tool(content)', function(obj){
            var id = obj.data.orderId;
             // console.log(id);

            if(obj.event === 'detail'){
                top.layer.open({
                    type: 2,
                    area: ['80%', '80%'],
                    title: '详情',
                    maxmin: true, //开启最大化最小化按钮
                    content: '/sell/seller/order/detail?orderId='+id,


                });

            }else if (obj.event === 'print')
            {
                var url = "/sell/seller/order/print?orderId="+id;
                window.open(url, '_blank');
            }

        })


    });


</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">详情</a>

    {{#  if(d.payStatus == '1' && d.orderStatus != '2'){ }}
    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="print">打印</a>
    {{#  } }}



</script>

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
        websocket = new WebSocket('ws://127.0.0.1:8080/sell/webSocket/'+${seller.sellerId});
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
</body>
</html>
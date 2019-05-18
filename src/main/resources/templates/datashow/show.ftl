<html>
<#include "../common/header.ftl">
<script src="/sell/echars/echarts.min.js"></script>
<body>
<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

        <div class="container-fluid layui-form">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>订单统计信息</legend>
            </fieldset>
            <form action="" class="layui-form">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label" style="width: 90px">日期范围</label>
                            <div class="layui-input-inline">
                                <input type="text" name="date" value="${date!}" class="layui-input" id="test6">
                            </div>
                        </div>


                    <div class="layui-inline">
                        <button class="layui-btn layui-btn-normal">搜索</button>
                    </div>

                 </div>
            </form>
            <div style="padding: 20px; background-color: #F2F2F2;">
                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md3">

                        <div class="layui-card">
                            <div class="layui-card-header">基本</div>
                            <div class="layui-card-body" style="height: 250px">
                            <div class="layui-col-md6">订单量：<h2 style="text-align: center">${statisticsBase.orderNum!}</h2></div>
                                <div class="layui-col-md6" style="margin-top: 10px">订单金额：<h2 style="text-align: center">${statisticsBase.amount!0}</h2></div>
                                <div class="layui-col-md6"style="margin-top: 10px">平均份单价：<h2 style="text-align: center">${statisticsBase.detailAvg!0}</h2></div>
                                <div class="layui-col-md6"style="margin-top: 10px">平均客单价：<h2 style="text-align: center">${statisticsBase.orderAvg!0}</h2></div>
                                <div class="layui-col-md6"style="margin-top: 10px">售出份数：<h2 style="text-align: center">${statisticsBase.detailNum!0}</h2></div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md3">
                        <div class="layui-card" >
                            <div class="layui-card-header">TOP</div>
                            <div class="layui-card-body"style="height: 250px">
                                <div class="layui-col-md12">销量最好商品TOP3（名称/销量）：
                                    <#if productTopList[0]??>
                    <h2 style="text-align: center">${productTopList[0].productName!0}/${productTopList[0].productQuantity!0}</h2>
                                    </#if>
                                    <#if productTopList[1]??>
                                    <h2 style="text-align: center">${productTopList[1].productName!0}/${productTopList[1].productQuantity!0}</h2>
                                    </#if>
                                     <#if productTopList[2]??>
                                     <h2 style="text-align: center">${productTopList[2].productName!0}/${productTopList[2].productQuantity!0}</h2>
                                     </#if>



                                </div>
                                <div class="layui-col-md12" style="margin-top: 10px">最高金额订单：<h2 style="text-align: center">
                                    <#if amountTop??>
                                    <a onclick="showdetail('${amountTop.id!}')">￥${amountTop.amount!}</a>
                                    </#if>

                                </h2><h5 style="text-align: center">点击查看</h5></div>

                            </div>
                            </div>
                        </div>

                    <div class="layui-col-md6">

                        <div class="layui-card">
                            <div class="layui-card-header">销量图</div>
                            <div class="layui-card-body" style="height: 250px">
                                <div id="productChar" style="height:250px;"></div>
                            </div>
                        </div>
                    </div>

                    <div class="layui-col-md5">
                        <div class="layui-card">
                            <div class="layui-card-header">各时段订单量图</div>
                            <div class="layui-card-body" style="height: 300px">
                                <div id="dayChar" style="height:300px;"></div>
                            </div>
                        </div>
                    </div>

                    <div class="layui-col-md7">
                            <div class="layui-card">
                                <div class="layui-card-header">时间订单量折线图</div>
                                <div class="layui-card-body layui-form" style="height: 300px">
                                    <div style="width: 150px">
                                    <select name="type" lay-filter="aihao" >
                                        <option value="0">前7天</option>
                                        <option value="1">前12月</option>
                                        <option value="2">按年份</option>
                                    </select>
                                </div>
                                    <div id="typeChar" style="height: 280px"></div>

                                </div>
                            </div>
                        </div>

                    </div>

            </div>
        </div>



    </div>



<script>

    layui.use('layer', function(){
        var layer = layui.layer;

    });
    layui.use(['laydate','form'], function(){
        var rate = layui.rate;
        //基础效果
        var form = layui.form;

        var laydate = layui.laydate;
        //日期范围
        laydate.render({
            elem: '#test6'
            ,range: true
        });

    });

    function showdetail(id) {
        top.layer.open({
            type: 2,
            area: ['80%', '80%'],
            title: '详情',
            maxmin: true, //开启最大化最小化按钮
            content: '/sell/seller/order/print?able=0&orderId='+id,

        });
    }

    function AddDate(date,addDays){ //date传入你需要的日期，格式"xxxx-xx-xx"。addDays传要加减的日期数，往前传正数，往后传负数
        var Dates = new Date(date);
        Dates.setDate(Dates.getDate() + addDays);
        var mon = Dates.getMonth() + 1,
                day = Dates.getDate();
        if(mon < 10){
            mon = "0" + mon;//月份小于10，在前面补充0
        }
        if(day < 10){
            day = "0" + day;//日小于10，在前面补充0
        }
        return Dates.getFullYear() + "-" + mon + "-" +day;
    }

</script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('productChar'));
    var myChart2 = echarts.init(document.getElementById('dayChar'));
    var myChart3 = echarts.init(document.getElementById('typeChar'));

    var productName = [];
    var productAmount = [];
    <#list productTopList as item>
        productName.push('${item.productName}');
        productAmount.push('${item.productQuantity}');
    </#list>

    var xp = ['${sevenDay.one}','${sevenDay.two}','${sevenDay.three}','${sevenDay.four}','${sevenDay.five}','${sevenDay.six}','${sevenDay.seven}'];


    var yp = [AddDate(new Date(),-1),
        AddDate(new Date(),-2),AddDate(new Date(),-3),
        AddDate(new Date(),-4),AddDate(new Date(),-5),AddDate(new Date(),-6),AddDate(new Date(),-7)];




    var option3 = {

        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: yp
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'line',
            data: xp
        }]
    };



    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '商品销量图'
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: productName
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: productAmount
        }]
    };

    var option2= {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['0点~7点','7点~11点','11点到15点','15点到19点','19点到23点',"23点到24点"]
        },
        series: [
            {
                name:'时间',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    <#if daydata??>
                    {value:${daydata.oneToSix!0}, name:'0点~7点'},
                    {value:${daydata.sevenToTen!0}, name:'7点~11点'},
                    {value:${daydata.elevenToFourteen!0}, name:'11点到15点'},
                    {value:${daydata.fifteenToEighteen!0}, name:'15点到19点'},
                    {value:${daydata.nineteenToTwentytwo!0}, name:'19点到23点'},
                    {value:${daydata.twentythreeToTwentyfour!0}, name:'23点到24点'}
                     </#if>

                ]
            }
        ]
    };

    myChart2.setOption(option2);

    myChart3.setOption(option3);

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>
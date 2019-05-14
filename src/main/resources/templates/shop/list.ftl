<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
<style>

</style>
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid layui-form">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>店铺基本信息</legend>
            </fieldset>
            <button class="layui-btn layui-btn-normal" onclick="edit()">修改</button>
            <div class="layui-form-item" pane="">
                <label class="layui-form-label" style="width: 90px">是否营业</label>
                <div class="layui-input-block">
                    <input type="checkbox"
                    <#if seller.shopAble == 1>
                    checked=""
                    </#if>
                           name="open"
                           lay-text="YES|ON"  lay-skin="switch" lay-filter="switchTest" title="开关">
                </div>

            </div>
            <div style="padding: 20px; background-color: #F2F2F2;">
                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md6">

                        <div class="layui-card">
                            <div class="layui-card-header">基本</div>
                            <div class="layui-card-body" style="height: 250px">
                                <div class="layui-col-md12">
                                <div class="layui-col-md6 center-block">

                                    <p class="bg-info">店铺名称：${seller.shopName!}</p>
                                    <p class="bg-success" style="margin-top: 15px;">创建时间：${seller.createTime!}</p>
                                    <p class="bg-warning" style="margin-top: 15px;">营业状态:
                                    <#if seller.shopAble! == 0>
                                    暂停营业
                                    <#else>
                                    正常营业
                                    </#if>
                                    </p>
                                    <p style="margin-top: 15px;" class="bg-info">营业类型：${seller.shopTypeName!}</p>
                                   <div style="margin-top: 15px;"> 评价：<div id="test1"></div></div>

                                </div>
                                <div class="layui-col-md6 center-block">
                                <img src="${seller.shopImg!}" height="200px" width="200px" alt="">
                                    头像
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md6">
                        <div class="layui-card" >
                            <div class="layui-card-header">店铺公告</div>
                            <div class="layui-card-body"style="height: 250px">
                            ${seller.shopDes!}
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md12">
                        <div class="layui-card">
                            <div class="layui-card-header">评价列表</div>
                            <div class="layui-card-body">
                                <p>1</p>
                                <p>2</p>
                                <p>3</p>
                                <p>4</p>
                            </div>
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
    layui.use(['rate','form'], function(){
        var rate = layui.rate;
        //基础效果
        var form = layui.form;
        //显示文字
        rate.render({
            elem: '#test1'
            ,value: ${seller.shopStar} //初始值
            ,text: true //开启文本
            ,readonly: true
        });

        //表单初始赋值
        form.val('example', {
            "switchTest": false //开关状态

        })
        //监听指定开关
        form.on('switch(switchTest)', function(data){
            if (this.checked)
            {
                able(1);
            }
            else {
                able(0);
            }


        });
    });

    function able(type) {
        $.ajax({
            type:"get",
            url: '/sell/seller/shop/able?type='+type,
            success:function (result) {
                if (result=='1')
                {
                    layer.msg("成功", function(){
                        window.parent.location.reload();//刷新父页面
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭

                    });
                }
                else {
                    layer.msg("失败");
                }
            },
            error:function () {
                layer.msg("异常");
            }
        });
    }

    function edit() {
        layer.open({
            type: 2,
            area: ['70%', '70%'],
            title: '修改',
            maxmin: true, //开启最大化最小化按钮
            content: '/sell/seller/shop/edit',


        });
    }
</script>

</body>
</html>
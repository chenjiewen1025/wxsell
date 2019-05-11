<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">


<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">

            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" id="input" method="post" >
                        <input name="sellerId" type="hidden" value="${(seller.sellerId)!''}">
                        <input name="shopType" type="hidden" value="${(seller.shopType)!''}">
                        <input name="shopAble" type="hidden" value="${(seller.shopAble)!''}">
                        <div class="form-group">
                            <label>商铺名称</label>
                            <input name="shopName" type="text" class="form-control" value="${(seller.shopName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>店铺公告</label>
                            <textarea cols="10" rows="8" name="shopDes" type="text" class="form-control"
                            >${(seller.shopDes)!''}</textarea>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <button type="button" class="layui-btn" id="img">
                                <i class="layui-icon">&#xe67c;</i>上传图片
                            </button>
                            <img height="150" id="shopImg" width="150"  src="${(seller.shopImg)!''}" alt="">
                            <a onclick="showimg()">大图</a>
                            <input name="shopImg" id="shopImg1" type="hidden" class="form-control" value="${(seller.shopImg)!''}"/>
                        </div>
                        <#--<div class="form-group">-->
                            <#--<label>经营类目</label>-->
                            <#--<select name="categoryType" class="form-control">-->
                                <#--<#list TypeList as category>-->
                                    <#--<option value="${category.categoryType}"-->
                                            <#--<#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>-->
                                                <#--selected-->
                                            <#--</#if>-->
                                        <#-->-->
                                        <#--${category.categoryName}-->
                                    <#--</option>-->
                                <#--</#list>-->
                            <#--</select>-->
                        <#--</div>-->

                        <button type="button" onclick="dosumit()" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>

</body>

<script>
    layui.use('layer', function(){
        var layer = layui.layer;

    });
    layui.use('upload', function(){
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#img' //绑定元素
            ,url: '/sell/seller/upload/addPhoto' //上传接口
            ,accept:'images'
            ,done: function(res){
                if (res[0].code == 0)
                {
                    console.log(res[0].filePath);

                    $("#shopImg1").val(res[0].filePath);

                    $("#shopImg").attr("src",res[0].filePath);
                }
              else {
                  layer.msg("上传出错")
                }
            }
            ,error: function(){
                //请求异常回调
                layer.msg("上传出错")
            }
        });
    });

    function dosumit(){
        $.ajax({
            type: "POST",//方法类型
            url: '/sell/seller/shop/save' ,
            data: $('#input').serialize(),
            success: function (result) {
                layer.msg(result+'3s后关闭', function(){
                    window.parent.location.reload();//刷新父页面
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭

                });
            },
            error : function() {
                layer.msg("异常！");
            }
        });
    }
function showimg() {
        var img = $("#shopImg1").val();
    window.open(img, '_blank');


}
</script>
</html>
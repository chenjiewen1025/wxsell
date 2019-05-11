<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">


<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">

            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" id="input" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="productName" type="text" class="form-control" value="${(productInfo.productName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="productStock" type="number" class="form-control" value="${(productInfo.productStock)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input name="productDescription" type="text" class="form-control" value="${(productInfo.productDescription)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <button type="button" class="layui-btn" id="img">
                                <i class="layui-icon">&#xe67c;</i>上传图片
                            </button>
                            <img height="150" id="productimg" width="150"  src="${(productInfo.productIcon)!''}" alt="">
                            <a onclick="showimg()">大图</a>
                            <input name="productIcon" id="productIcon" type="hidden" class="form-control" value="${(productInfo.productIcon)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                            <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                                selected
                                            </#if>
                                        >
                                        ${category.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
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

                    $("#productIcon").val(res[0].filePath);

                    $("#productimg").attr("src",res[0].filePath);
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
            url: '/sell/seller/product/save' ,
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
        var img = $("#productIcon").val();
    window.open(img, '_blank');


}
</script>
</html>
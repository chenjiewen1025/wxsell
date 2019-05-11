<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">

                    <form role="form" id="input" method="post" action="/sell/seller/category/save">
                        <div class="form-group">
                            <label>名字</label>
                            <input name="categoryName" style="width: 300px" type="text" class="form-control" value="${(category.categoryName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>type</label>
                            <input name="categoryType"style="width: 300px" type="number" class="form-control" value="${(category.categoryType)!''}"/>
                        </div>
                        <input hidden type="text" name="categoryId" value="${(category.categoryId)!''}">
                        <button type="button" onclick="dosumit()"  class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
<script>
    layui.use('layer', function(){
        var layer = layui.layer;

    });

    function dosumit(){
        $.ajax({
            type: "POST",//方法类型
            url: '/sell/seller/category/save' ,
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


</script>
</body>
</html>
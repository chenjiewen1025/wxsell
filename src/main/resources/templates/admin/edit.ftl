<html>
<#include "../common/header.ftl">

<body>

        <div class="container-fluid">

            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" id="input" method="post" >
                        <div class="form-group">
                            <label>名称</label>
                            <input name="name" type="text" class="form-control" value="${(name)!}"/>
                        </div>
                        <input type="hidden" name="id" value="${(id)!}">
                        <div class="layui-inline">

                            <label class="layui-form-label">图标</label>
                            <div class="layui-input-inline">
                                <input id="personImgIn1" type="hidden" name="img" lay-verify="required" class="layui-input">
                                <div class="layui-upload-drag" id="personImg1_1">
                                    <img height="120px" width="250px" src="" alt="" id="personImg1">
                                    <p>点击上传，或将文件拖拽到此处</p>
                                </div>
                            </div>

                        </div>
                    </form>
                    <button type="button" onclick="dosumit()" class="btn btn-default">提交</button>
                </div>
            </div>


</div>

</body>

<script>
    layui.use(['upload','layer'], function(){
        var layer = layui.layer;
        var $ = layui.jquery
                ,upload = layui.upload;

        upload.render({
            elem: '#personImg1_1'
            ,url: '/sell/seller/upload/addPhoto'
            ,done: function(res){
                if (res[0].code == 0)
                {

                        $("#personImgIn1").val(res[0].filePath);
                        $("#personImg1").attr("src",res[0].filePath);
                        layer.msg("检测成功")


                }
                else {
                    layer.msg("上传出错")
                }
            }
        });
    });

    function dosumit(){
        $.ajax({
            type: "POST",//方法类型
            url: '/sell/admin/save' ,
            data: $('#input').serialize(),
            success: function (result) {

                if (result=='1')
                {
                    layer.msg('保存成功！！3s后关闭', function(){
                        window.parent.location.reload();//刷新父页面
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭

                    });
                }else {
                    layer.msg('保存失败！！3s后关闭', function(){
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                }

            },
            error : function() {
                layer.msg("异常！");
            }
        });
    }

</script>
</html>
<html>
<#include "../common/header.ftl">


<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">

            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" id="input" method="post" >
                        <div class="form-group">
                            <label>原用户名</label>
                            <p>${(name)!}</p>
                        </div>
                        <div class="form-group">
                            <label>新用户名</label>
                            <input name="name" id="username" onBlur="isUse()" type="text" class="form-control" value="${(name)!}"/>
                        </div>
                        <div class="form-group">
                            <label>新密码</label>
                            <input name="password" id="password" type="password" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>原密码</label>
                            <input name="oldpassword" id="oldpassword" type="password" class="form-control"/>
                        </div>

                    </form>
                    <button type="button" onclick="dosumit()" class="btn btn-default">提交</button>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
<script>

    function check() {
        var password = $(" #password ").val();
        var oldpassword = $(" #oldpassword ").val();
       if (password==null||password=="")
        {
            alert("新密码不能为空");
            return false;
        }
        else if (oldpassword==null||oldpassword=="")
       {
           alert("旧密码不能为空");
           return false;
       }

            return true;
    }
    function isUse() {
        var username = $("#username").val();
        if (username==''||username=='${(name)!}')
        {
            return
        }
        $.ajax({
            url:'/sell/seller/isUse?name='+username,
            success:function (result) {
                if (result=='1')
                {
                    layer.msg("用户名可用")
                }
                else
                {
                    layer.msg("用户名重复，不可用，请重新输入");
                    $("#username").val("");
                    $("#username").focus();
                }

            }

        });

    }
</script>
<script>
    layui.use('layer', function(){
        var layer = layui.layer;

    });

    function dosumit(){
        if (check())
        {
            console.log("dd")
            $.ajax({
                type: "POST",//方法类型
                url: '/sell/seller/save' ,
                data: $('#input').serialize(),
                success: function (result) {

                    if (result=='1')
                    {
                        layer.msg('修改成功！！请重新登录', function(){
                            window.location.href="/sell/seller/logout";
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index); //再执行关闭

                        });
                    }else if (result=='0'){
                        layer.msg('密码错误，请检查！！3s后关闭', function(){
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index); //再执行关闭
                        });
                    }
                    else
                    {
                        layer.msg('出错！！3s后关闭', function(){
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

    }

</script>
</html>
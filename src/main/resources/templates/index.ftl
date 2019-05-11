<html>
<#include "common/header.ftl">

<body>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<#--主要内容content-->

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <p>
                <em>测试账户</em>  <strong>admin</strong>
            </p>
            <p>
                <em>密码</em>  <strong>admin</strong>
            </p>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-4 column">
                </div>
                <div class="col-md-4 column">
                    <form role="form" method="post" action="/sell/seller/login" onsubmit="return check()">
                        <div class="form-group">
                            <label for="exampleInputEmail1">账户</label><input type="text" name="username" class="form-control" id="username" />
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">密码</label><input type="password" name="password" class="form-control" id="password" />
                        </div>

                       <button type="submit" class="btn btn-default">登录</button>

                    </form>
                    <a type="button" class="btn btn-default" href="/sell/seller/apply/index">商家申请入驻</a>

                </div>
                <div class="col-md-4 column">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function check() {

        var username = $(" #username ").val();
        var password = $(" #password ").val();

        if (username==null||username=="")
        {
            alert("用户不能为空");
            return false;
        }

        else if (password==null||password=="")
        {
            alert("密码不能为空");
            return false;
        }

        else
            return true;
    }
</script>
</html>
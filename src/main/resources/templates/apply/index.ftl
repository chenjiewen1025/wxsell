<html>
<#include "../common/header.ftl">

<body>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<#--主要内容content-->

<div class="container">

    <div class="row clearfix">

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
            <legend>入驻商家申请</legend>
        </fieldset>
        <form id="input" class="layui-form layui-form-pane" action="/sell/seller/apply/save" >


            <div class="layui-form-item">
                <div class="layui-inline">

                    <label class="layui-form-label">登录名</label>
                    <div class="layui-input-inline">
                        <input onBlur="isUse()" id="username" type="text" name="username" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>

                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="password"  lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                    </div>

                </div>
            </div>


            <div class="layui-form-item">
                <div class="layui-inline">

                    <label class="layui-form-label">店铺名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="shopName" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>

                </div>
                <div class="layui-inline">

                    <label class="layui-form-label">经营类型</label>
                    <div class="layui-input-block">
                        <select name="shopType" lay-filter="aihao">
                             <#list list as category>
                                 <option value="${category.value}">${category.name}</option>
                             </#list>
                        </select>
                    </div>

                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">店铺地址</label>
                <div class="layui-input-block">
                    <input type="text" name="shopAddress" lay-verify="required" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">

                    <label class="layui-form-label">申请人姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="personName" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>

                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">身份证号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="personId" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>

                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">

                    <label class="layui-form-label">身份证正面</label>
                    <div class="layui-input-inline">
                        <input id="personImgIn1" type="hidden" name="personImg1" lay-verify="required" class="layui-input">
                        <div class="layui-upload-drag" id="personImg1_1">
                            <img height="120px" width="250px" src="" alt="" id="personImg1">
                            <p>点击上传，或将文件拖拽到此处</p>
                        </div>
                    </div>

                </div>
                <div class="layui-inline" style="margin-left: 200px;">
                    <label class="layui-form-label">身份证反面</label>
                    <div class="layui-input-inline">
                        <input  id="personImgIn2" type="hidden" name="personImg2" lay-verify="required" class="layui-input">
                        <div class="layui-upload-drag" id="personImg2_1">
                            <img height="120px" width="250px" src="" alt="" id="personImg2">
                            <p>点击上传，或将文件拖拽到此处</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">

                    <label class="layui-form-label">营业执照</label>
                    <div class="layui-input-inline">
                        <input id="shopImgIn1" type="hidden" name="shopImg1" lay-verify="required" class="layui-input">
                        <div class="layui-upload-drag" id="shopImg1_1">
                            <img height="120px" width="250px" src="" alt="" id="shopImg1">
                            <p>点击上传，或将文件拖拽到此处</p>
                        </div>
                    </div>

                </div>
                <div class="layui-inline" style="margin-left: 200px;">
                    <label class="layui-form-label">店铺实照</label>
                    <div class="layui-input-inline">
                        <input  id="shopImgIn2" type="hidden" name="shopImg2" lay-verify="required" class="layui-input">
                        <div class="layui-upload-drag" id="shopImg2_1">
                            <img height="120px" width="250px" src="" alt="" id="shopImg2">
                            <p>点击上传，或将文件拖拽到此处</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn" lay-submit="" lay-filter="demo2"  >提交</button>
            </div>
        </form>
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
<script>
    layui.use(['form', 'layedit', 'laydate','upload'], function(){
        var form = layui.form
                ,layer = layui.layer
                ,layedit = layui.layedit
                ,laydate = layui.laydate;
        var $ = layui.jquery
                ,upload = layui.upload;


        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length < 5){
                    return '标题至少得5个字符啊';
                }
            }
            ,pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });


        //监听提交
        form.on('submit(demo1)', function(data){
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            return false;
        });




//拖拽上传
        upload.render({
            elem: '#personImg1_1'
            ,url: '/sell/seller/upload/addPhoto'
            ,done: function(res){
                if (res[0].code == 0)
                {
                    console.log(res[0].filePath);
                    $("#personImgIn1").val(res[0].filePath);
                    $("#personImg1").attr("src",res[0].filePath);
                }
                else {
                    layer.msg("上传出错")
                }
            }
        });
        upload.render({
            elem: '#personImg2_1'
            ,url: '/sell/seller/upload/addPhoto'
            ,done: function(res){
                if (res[0].code == 0)
                {
                    console.log(res[0].filePath);
                    $("#personImgIn2").val(res[0].filePath);
                    $("#personImg2").attr("src",res[0].filePath);
                }
                else {
                    layer.msg("上传出错")
                }
            }
        });
        upload.render({
            elem: '#shopImg1_1'
            ,url: '/sell/seller/upload/addPhoto'
            ,done: function(res){
                if (res[0].code == 0)
                {
                    console.log(res[0].filePath);
                    $("#shopImgIn1").val(res[0].filePath);
                    $("#shopImg1").attr("src",res[0].filePath);
                }
                else {
                    layer.msg("上传出错")
                }
            }
        });
        upload.render({
            elem: '#shopImg2_1'
            ,url: '/sell/seller/upload/addPhoto'
            ,done: function(res){
                if (res[0].code == 0)
                {
                    console.log(res[0].filePath);
                    $("#shopImgIn2").val(res[0].filePath);
                    $("#shopImg2").attr("src",res[0].filePath);
                }
                else {
                    layer.msg("上传出错")
                }
            }
        });

    });

    function isUse() {
        var username = $("#username").val();
        if (username=='')
        {
            return
        }
        $.ajax({
            url:'/sell/seller/apply/isUse?username='+username,
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
</html>
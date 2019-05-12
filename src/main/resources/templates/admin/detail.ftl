<html>
<#include "../common/header.ftl">

<body>


    <#--主要内容content-->

        <div class="container-fluid">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>入驻申请图片信息</legend>
            </fieldset>

            <div style="padding: 20px; background-color: #F2F2F2;">
                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md6">
                        <div class="layui-card">
                            <div class="layui-card-header">营业执照</div>
                            <div class="layui-card-body" style="height: 280px">
                                <a href="${sellerForm.shopImg1}" target="_blank">
                                <img src="${sellerForm.shopImg1}" width="100%" height="250px" alt="">
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md6">
                        <div class="layui-card" >
                            <div class="layui-card-header">店铺实照</div>
                            <div class="layui-card-body"style="height: 280px">
                                <a href="${sellerForm.shopImg2}" target="_blank">
                                <img src="${sellerForm.shopImg2}" width="100%" height="250px" alt="">
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="layui-col-md6">

                        <div class="layui-card">
                            <div class="layui-card-header">身份证正面</div>
                            <div class="layui-card-body" style="height: 280px">
                                <a href="${sellerForm.personImg1}" target="_blank">
                                <img src="${sellerForm.personImg1}" width="100%" height="250px" alt="">
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md6">
                        <div class="layui-card" >
                            <div class="layui-card-header">身份证反面</div>
                            <div class="layui-card-body"style="height: 280px">
                                <a href="${sellerForm.personImg2}" target="_blank">
                                <img src="${sellerForm.personImg2}"width="100%" height="250px" alt="">
                                    </a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>




</body>
</html>
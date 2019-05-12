<html>

<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

<#include "../admin/nav.ftl">
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <button class="layui-btn" onclick="add()">新增</button>
            <table class="layui-hide"  lay-filter="content" id="content"></table>

        </div>
    </div>

</div>

<script>
  var dataList = ${list};
  layui.use('layer', function(){
      var layer = layui.layer;

  });
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#content'
            ,data:dataList
            ,cols: [[
                {type:'numbers',}
                ,{field:'name', width:180,title: '名称'}
                ,{field:'value', width:100, title: '映射值', sort: true}
                ,{field:'createTime',  title: '创建时间',sort: true}
                ,{field:'updateTime',  title: '更新时间',sort: true}
                ,{toolbar: '#barDemo', title: '操作'}
            ]]
            ,page: true
        });

        //监听行工具事件
        table.on('tool(content)', function(obj){
            var id = obj.data.id;
             var name = obj.data.name;

            if(obj.event === 'edit'){
                top.layer.open({
                    type: 2,
                    area: ['30%', '30%'],
                    title: '增加',
                    maxmin: true, //开启最大化最小化按钮
                    content: '/sell/admin/edit?id='+id+'&name='+name,
                });

            }

        })


    });

  function add() {
      top.layer.open({
          type: 2,
          area: ['30%', '30%'],
          title: '详情',
          maxmin: true, //开启最大化最小化按钮
          content: '/sell/admin/edit',
      });
  }
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
</script>

</body>
</html>
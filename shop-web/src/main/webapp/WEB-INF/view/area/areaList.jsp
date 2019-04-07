<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/3/12
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/md5.js"></script>
    <!--DataTables-->
    <link href="/js/DataTables/DataTables-1.10.18/css/jquery.dataTables.min.css" rel="stylesheet">
    <!--bootstrap-->
    <link href="/js/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/js/DataTables/DataTables-1.10.18/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/js/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <!--Ztree树-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
    <script src="/js/bootstrap/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/dataTables.bootstrap.min.js"></script>
    <!--日期插件-->
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker-master/moment-with-locales.js"></script>
    <script type="text/javascript"

            src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <!--bootbox-->
    <script src="<%=request.getContextPath()%>/js/bootbox/bootbox.min.js"></script>
    <!--Ztree-->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.core.js"></script>
    <script>
        //加载页面
        $(function () {
            //展示zTree树
            initzTree();
        });
        // 查询展示zTree树
        function initzTree() {
            var setting = {
                data: {
                    simpleData: {
                        enable: true,
                        pIdKey: "pid"
                    },
                    key: {
                        name: "name"
                    }
                },

            };
            $.ajax({
                type:"post",
                url:"<%=request.getContextPath()%>/area/findAreaList.jhtml",
                success:function (result) {
                   // alert(result)
                    if (result.code==200){
                        $.fn.zTree.init($("#treeDemo"), setting, result.data);
                    } else {
                        bootbox.alert({
                            message: '<span class="glyphicon glyphicon-remove"></span>获取部门信息失败',
                            size: 'small',
                            title: "提示信息"
                        });
                    }
                }
            })

        }

        //添加zTree树
        function showAddArea() {
            //获取zTree当前被选中的节点数据集合
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getSelectedNodes();
            //判断选择父节点不能不选择,也不能多选
            if (nodes.length==1) {
                //点击获取父级元素的id
                var v_id = nodes[0].id;
                console.log(v_id)
                //给html中的input框赋值用attr[把ID赋值给input
                // 框中]
                $("#pid").attr("value",v_id);
                var v_adddialog =bootbox.dialog({
                    title: '添加节点',
                    message: $("#formdiv").html(),
                    buttons: {
                        ok: {
                            label: '<span class="glyphicon glyphicon-ok"></span>添加',
                            className: 'btn-success',
                            callback: function(){
                                var v_pid=$("#pid",v_adddialog).val();
                                var v_name=$("#name",v_adddialog).val();
                                //往后台传参的第二种方式
                                var v_pame={};
                                v_pame.pid=v_pid;
                                v_pame.name=v_name;

                                $.ajax({
                                    url:"<%=request.getContextPath()%>/area/showAddArea.jhtml",
                                    type:"post",
                                    data:v_pame,
                                    success:function (result) {
                                         alert(34243);
                                        if (result.code==200){
                                            //添加到树中
                                           // var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                                            var v_newNode={};
                                            v_newNode.name=v_name;
                                            v_newNode.id=result.data;
                                            treeObj.addNodes(nodes[0], v_newNode);
                                        } else {
                                            bootbox.alert({
                                                message: '<span class="glyphicon glyphicon-remove"></span>添加部门信息失败',
                                                size: 'small',
                                                title: "提示信息"
                                            });
                                        }
                                    }
                                })
                            }
                        },
                        cancel: {
                            label: '<span class="glyphicon glyphicon-remove"></span>取消',
                            className: 'btn-danger',
                        }
                    },
                });

            }else {
                    bootbox.alert({
                        message: '<span class="glyphicon glyphicon-remove"></span>请选择一个父节点',
                        size: 'small',
                        title: "提示信息"
                    });
            }
        }

        //修改zTree树
        function editAreaDlg() {
            //获取zTree当前被选中的节点数据集合
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getSelectedNodes();
            //点击获取父级元素的id
            var v_ids = nodes[0].id;
            var v_name = nodes[0].name;
            //给html中的input框赋值用attr[把ID,deptName,remark赋值回显给input
            // 框中]
            $("#enit_pid").attr("value",v_ids);
            $("#enit_name").attr("value",v_name);
            //判断选择父节点不能不选择,也不能多选
            if (nodes.length==1) {
                var v_enitform = bootbox.dialog({
                    title: '更新节点',
                    message: $("#form").html(),
                    buttons: {
                        ok: {
                            label: '<span class="glyphicon glyphicon-ok"></span>更新',
                            className: 'btn-success',
                            callback: function(){
                                //向后台传参数新增
                                var v_id=$("#enit_pid",v_enitform).val();
                                var v_enit_name=$("#enit_name",v_enitform).val();
                                //往后台传参的第二种方式
                                var v_pame={};
                                v_pame.id=v_id;
                                v_pame.name=v_enit_name;
                                $.ajax({
                                    url:"<%=request.getContextPath()%>/area/editAreaDlg.jhtml",
                                    type:"post",
                                    data:v_pame,
                                    success:function (result) {
                                        if (result.code==200){
                                            //修改前台树结构,只需要修改名称即可
                                           // alert(v_enit_name)
                                           nodes[0].name = v_enit_name;
                                           treeObj.updateNode(nodes[0]);
                                        } else {
                                            bootbox.alert({
                                                message: '<span class="glyphicon glyphicon-remove"></span>修改部门信息失败',
                                                size: 'small',
                                                title: "提示信息"
                                            });
                                        }
                                    }
                                })
                            }
                        },
                        cancel: {
                            label: '<span class="glyphicon glyphicon-remove"></span>取消',
                            className: 'btn-danger',
                        }
                    },
                });
            }
            else {
                bootbox.alert({
                    message: '<span class="glyphicon glyphicon-remove"></span>请选择一个父节点',
                    size: 'small',
                    title: "提示信息"
                });
            }

        }

        //删除zTree树
        function deleteArea(){
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            //获取选中的节点
            var v_selectedNodes = treeObj.getSelectedNodes();
            if(v_selectedNodes.length>0){
                //运用递归找到本身和本身下的子节点
                var nodesArr=treeObj.transformToArray(v_selectedNodes);
                //console.log(nodesArr)
                //定义一个空数组
                var idArr=[];
                //循环递归里面的值,并且放到数组中
                for (var i=0;i<nodesArr.length;i++) {
                    //往数组中放值,只放id
                    idArr.push(nodesArr[i].id);
                }
                bootbox.confirm({
                    size: "small",
                    message: "你确定删除吗！！！",
                    callback: function(result){
                        if(result){
                            $.ajax({
                                url:'<%=request.getContextPath()%>/area/deleteArea.jhtml',
                                type: "post",
                                async: true,
                                //前台不仅可以传一个集合到后台,还可以是数组
                                data: {"ids": idArr},
                                success:function(res){
                                    if(res.code==200){
                                        bootbox.alert({
                                            message: '<span class="glyphicon glyphicon-ok"></span>删除成功',
                                            size: 'small',
                                            title: "提示信息"
                                        });
                                        //删除前台的Ztree树
                                        for (var i=0;i<nodesArr.length;i++) {
                                            //往数组中放值,只放id
                                            treeObj.removeNode(nodesArr[i]);
                                        }

                                    }else{
                                        bootbox.alert({
                                            message: '<span class="glyphicon glyphicon-exclamation-sign"></span>删除失败',
                                            size: 'small',
                                            title: "提示信息"
                                        });
                                    }
                                }
                            })
                        }
                    }
                })

            }else{
                bootbox.alert({
                    message: '<span class="glyphicon glyphicon-ok"></span>请选择要删除的部门！',
                    size: 'small',
                    title: "提示信息"
                });
            }


        }
    </script>
</head>
<body>
<!--地区的增删改查按钮-->
<div class="container-fluid" style="padding-left: 0px;">
    <div class="row">
        <div class="col-md-3" STYLE="background: #66F4DF">
            地区管理:
            <button type="button" class="btn btn-success" onclick="showAddArea()"><span
                    class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
            </button>
            <button type="button" class="btn btn-danger" onclick="deleteArea()"><span
                    class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
            </button>

            <button type="button" class="btn btn-primary" onclick="editAreaDlg()"><span
                    class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
            </button>

        </div>
    </div>
</div>
<!--地区的添加-->
<div id="formdiv" STYLE="display: none;">
    <form class="form-horizontal">
        <input type="text" id="pid">
        <div class="form-group">
            <label  class="col-sm-2 control-label">地区名称:</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="name" placeholder="地区名称">
            </div>
        </div>
    </form>
</div>
<!--地区的修改-->
<div id="form" STYLE="display: none;">
    <form class="form-horizontal">
        <input type="text" id="enit_pid">
        <div class="form-group">
            <label  class="col-sm-2 control-label">地区名称:</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="enit_name" placeholder="地区名称">
            </div>
        </div>
    </form>
</div>


<!--展示Ztree树-->
<div>
    <ul id="treeDemo" class="ztree"></ul>
</div>
</body>
</html>

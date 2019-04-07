<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 上次登录的时间 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/md5.js"></script>
    <!--DataTables-->
    <link href="/js/DataTables/DataTables-1.10.18/css/jquery.dataTables.min.css" rel="stylesheet">
    <!--bootstrap-->
    <link href="/js/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/js/DataTables/DataTables-1.10.18/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/js/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet" media="screen">
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
    <script type="text/javascript" src="/js/dateFormat.js"></script>
    <!--fileinput-->
    <link href="<%=request.getContextPath()%>/js/bootstrap-fileinput/css/fileinput.css" rel="stylesheet" type="text/css" />
    <script src="<%=request.getContextPath()%>/js/bootstrap-fileinput/js/fileinput.min.js"type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/js/bootstrap-fileinput/js/locales/zh.js"type="text/javascript"></script>


</head>
<body>
<!--部门的增删改查按钮-->
<div class="container-fluid" style="padding-left: 0px;">
    <div class="row">
        <div class="col-md-3" STYLE="background: #66F4DF">
            部门管理:
            <button type="button" class="btn btn-success" onclick="showAddDept()"><span
                    class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
            </button>
            <button type="button" class="btn btn-danger" onclick="deleteDept()"><span
                    class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
            </button>

            <button type="button" class="btn btn-primary" onclick="editDeptDlg()"><span
                    class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
            </button>
            <!--展示Ztree树-->
            <div id="DeptTreeDiv">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </div>

        <div class="col-md-9">
            <div class="panel-body" style="margin-left: 300px">
                <form class="form-horizontal" id="userForm">
                    <%--产品名称--%>
                    <div class="form-group">
                        <label for="userName" class="col-sm-2 control-label">用户名称</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="userName" placeholder="用户名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label">薪资:</label>
                        <div class="col-sm-4">
                            <div class="input-group">
                                <input type="text" class="input-sm form-control" id="minsalary"/>
                                <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-yen" aria-hidden="true"></span>
                                        </span>
                                <input type="text" class="input-sm form-control" id="maxsalary"/>
                            </div>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">生日范围:</label>
                        <div class="col-sm-4">
                            <div class="input-group">
                                <input type="text" class="form_datetime form-control" id="minbirthday"
                                />

                                <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span>
                                        </span>
                                <input type="text" class="form_datetime form-control" id="maxbirthday"
                                />
                            </div>
                        </div>
                    </div>
                    <div style="margin-left: 170px">
                        <button type="button" class="btn btn-primary btn-lg active" onclick="search()"><span
                                class="glyphicon glyphicon-ok" aria-hidden="true"></span>搜索
                        </button>
                        <button type="button" class="btn btn-default btn-lg active" onclick="resetZtree()"><span
                                class="glyphicon glyphicon-refresh" aria-hidden="true"></span>重置
                        </button>
                    </div>
                     <!--三级联查点击Ztree树时它的id被放到文本框中-->
                     <input type="text"  id="deptids"/>

                </form>
            </div>
            <!--用户的增删改查按钮-->
            <div style="background: darkgrey;text-align: right;margin-top: 25px">
                <button type="button" class="btn btn-info btn-lg active" onclick="toAddUser()"><span
                        class="glyphicon glyphicon-pencil" aria-hidden="true"></span>添加
                </button>

                <button type="button" class="btn btn-info dropdown-toggle btn-lg active" onclick="toupdateUser()"><span
                        class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>

                <button type="button" class="btn btn-danger dropdown-toggle btn-lg active" onclick="deleteUser()"><span
                        class="glyphicon glyphicon-remove" aria-hidden="true"></span>批量删除
                </button>

                <button type="button" class="btn btn-info dropdown-toggle btn-lg active" onclick="updatebatch()"><span
                        class="glyphicon glyphicon-pencil" aria-hidden="true"></span>批量修改部门
                </button>
                <button type="button" class="btn btn-primary btn-lg" onclick="exportDeptExcel()"><span
                        class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>用户导出Excel
                </button>


            </div>

            <%--用户列表--%>
            <div class="panel panel-primary">
                <div class="panel-heading">用户列表</div>
                <table class="table table-striped table-bordered" id="userTable" style="width: 100%;">
                    <thead>
                    <tr>
                        <th>选择:</th>
                        <th>编号:</th>
                        <th>用户名称:</th>
                        <th>用户状态:</th>
                        <th>真是姓名:</th>
                        <th>生日:</th>
                        <th>性别:</th>
                        <th>薪资:</th>
                        <th>所在部门:</th>
                        <th>头像:</th>
                        <th>操作:</th>
                    </tr>
                    </thead>

                    <tfoot>
                    <tr>
                        <th>选择:</th>
                        <th>编号:</th>
                        <th>用户名称:</th>
                        <th>用户状态:</th>
                        <th>真是姓名:</th>
                        <th>生日:</th>
                        <th>性别:</th>
                        <th>薪资:</th>
                        <th>所在部门:</th>
                        <th>头像:</th>
                        <th>操作:</th>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
<!--部门的添加-->
<div id="formdiv" STYLE="display: none;">
    <form class="form-horizontal">
        <input type="text" id="fatherid">
        <div class="form-group">
            <label for="userName" class="col-sm-2 control-label">项目名称:</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="deptName" placeholder="项目名称">
            </div>
        </div>
        <div class="input-group">
            <label for="userName" class="col-sm-2 control-label">描述:</label>
            <div class="col-md-4">
                <textarea id="remark" placeholder="写点什么吧..."></textarea>
            </div>
        </div>
    </form>
</div>
<!--部门的修改-->
<div id="enit_formdiv" STYLE="display: none;">
    <form class="form-horizontal">
        <input type="text" id="enit_id">
        <div class="form-group">
            <label for="userName" class="col-sm-2 control-label">项目名称:</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="enit_deptName" placeholder="项目名称">
            </div>
        </div>
        <div class="input-group">
            <label for="userName" class="col-sm-2 control-label">描述:</label>
            <div class="col-md-4">
                <textarea id="enit_remark" placeholder="写点什么吧..."></textarea>
            </div>
        </div>
    </form>
</div>
<!--用户的添加-->
<div class="container-fluid" STYLE="display: none;" id="from_user">
    <form class="form-horizontal" role="form">
        <div class="form-group">
            <label class="col-sm-2 control-label">用户名:</label>
            <div class="col-sm-3">
                <input class="form-control" type="text" id="user_userName"/>
            </div>
            <label class="col-sm-2 control-label">密码:</label>
            <div class="col-sm-3">
                <input class="form-control" type="password" id="user_userPassword"/>
            </div>

        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">真是姓名:</label>
            <div class="col-sm-3">
                <input class="form-control" type="text" id="user_userRealName"/>
            </div>
            <label class="col-sm-2 control-label">生日:</label>
            <div class="col-sm-3">
                <input class="form_datetime form-control" type="text" id="user_birthday"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">性别:</label>
            <div class="col-sm-3">
                女:<input type="radio" name="user_sex" value="0"/>
                男:<input type="radio" name="user_sex" value="1"/>
            </div>

            <label class="col-sm-2 control-label">薪资:</label>
            <div class="col-sm-3">
                <input class="form-control" type="text" id="user_salary"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">所在部门:</label>
            <div class="col-sm-3">
                <input type="text" class="form-control data-form-start" id="user_deptName">
                <input type="text" class="form-control data-form-start" id="user_id">
            </div>
            <div class="col-sm-2" style="padding: 0px;">
                <button type="button" class="btn btn-primary" onclick="EmpByDept()">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>选择部门
                </button>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">头像:</label>
            <div class="col-sm-8">
                <input type="file" name="headerImage" id="add_headerImage">
                <%--商品主图路径隐藏域--%>
                <input type="text" id="headerPath"/>
            </div>
        </div>
    </form>
</div>
<!--在做用户添加和修改时用的树弹框-->
<div id="DeptTreeDiv2" STYLE="display: none;">
    <ul id="treeDemo2" class="ztree"></ul>
</div>
<!--用户的修改-->



<script type="text/javascript">
    //使第三方插件在弹框中正常出现
    var addheaderImage;
    //加载页面
    $(function () {
        //使第三方插件在弹框中正常出现
        addheaderImage=$("#from_user").html();
        //初始化表格
        initUserTable();
        //点击行变色
        initBindEvent();
        //日期
        initDateTime();
        //展示zTree树
        initzTree();
        initHeaderImage();
        //fileinput图片插件上传
        initEditHeaderImage();
    })
    //bootstrap-datetimepicker-master时间
    function initDateTime() {
        $(".form_datetime").datetimepicker({
            format: "yyyy-mm-dd",
            // autoclose: true,
            // todayBtn: true,
            // pickerPosition: "bottom-left",
            // clearBtn:true
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,//显示‘今日’按钮
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
            clearBtn: true,//清除文本框按钮
            forceParse: 0

        });
    }


    //fileinput单个图片插件上传
    function initHeaderImage() {
        var s={
            //初始化上传文件框
            language: "zh",//配置语言
            showUpload: true, //显示整体上传的按钮
            showRemove: true,//显示整体删除的按钮
            uploadUrl: "/file/uploadHeaderImage.jhtml",//这个是配置上传调取的后台地址，本项目是SSM搭建的
            allowedPreviewTypes:['image'],//预览的文件类型
            allowedFileExtensions: ["jpg", "png"], /*上传文件格式限制*/
        };

        $('#add_headerImage').fileinput(s).on("fileuploaded", function (event, data, previewId, index) {
            var result =data.response;
            if (result.code==200){
                $("#headerPath",v_user).val(result.data);
            }
        });

    }



    //fileinput回显单个图片插件上传
    function initEditHeaderImage(imageArr) {
        var s={
            //初始化上传文件框
            language: "zh",//配置语言
            showUpload: true, //显示整体上传的按钮
            showRemove: true,//显示整体删除的按钮
            //图片回显
            initialPreview:[imageArr],
            initialPreviewAsData:true,//特别重要
            uploadUrl: "/file/uploadHeaderImage.jhtml",//这个是配置上传调取的后台地址，本项目是SSM搭建的
            allowedPreviewTypes:['image'],//预览的文件类型
            allowedFileExtensions: ["jpg", "png"], /*上传文件格式限制*/
        };


        $('#user_headerImage').fileinput(s).on("fileuploaded", function (event, data, previewId, index) {
            var result =data.response;
            if (result.code==200){
                $("#user_headerPath",userRowData).val(result.data);
            }
        });

    }





    //点击行变颜色
    var v_ids = [];
    //点击时修改整行用
    var userRowData;
   var checkedRow;
    var v_checkbox;
    function initBindEvent() {
        $("#userTable tbody").on("click", "tr", function () {
            checkedRow=$(this);
           v_checkbox = $(this).find("input[type='checkbox']")[0];
            //如果复选框被选中，则取消背景颜色，取消选中状态，取消对应的id
            if (v_checkbox.checked) {
                /*不被选中状态*/
                v_checkbox.checked = false;
                $(this).css("background", "");
                delCheckbox(v_checkbox.value);
                console.log(v_ids);
            } else {
                userRowData = userTable.row(this).data();
                /*被选中状态*/
                v_checkbox.checked = true;
                //如果没有被选中，则给他赋颜色，添加id到数组中
                $(this).css("background", "red");
                //把被选中的复选框添加id到数组中
                v_ids.push(v_checkbox.value);
                console.log(v_ids);
            }
        })
    }

    //删除数据库中值
    function delCheckbox(id) {
        for (var i = v_ids.length - 1; i >= 0; i--) {
            if (v_ids[i] == id) {
                v_ids.splice(i, 1);
                break;
            }
        }
    }

    //回显的值是否和数组的值对应
    function isExist(id) {
        for (var i = 0; i < v_ids.length; i++) {
            if (v_ids[i] == id) {
                return true;
            }
        }
    }

    //【初始化表格】
    var userTable;
    function initUserTable() {
        userTable = $('#userTable').DataTable({
            "processing": true,
            "serverSide": true,
            // 是否允许检索
            "searching": false,
            "ajax": {
                "url": "<%=request.getContextPath()%>/user/findUserList.jhtml",
                "type": "POST",
                //用于处理服务器端返回的数据。 dataSrc是DataTable特有的
                dataSrc: function (result) {
                    //把dataTable里的数据是放在最外面的data里 所以要提取出来
                    //这样result.data.data就能取到值了 否则前台数据能显示出来但是分页插件不显示几页几条
                    result.draw = result.data.draw;
                    result.recordsTotal = result.data.recordsTotal;
                    result.recordsFiltered = result.data.recordsFiltered;
                    return result.data.data;
                }
            },
            "drawCallback": function () {
                //获取表格里tbody里的所有tr里的复选框的值
                $("#userTable tbody tr input[type='checkbox']").each(function () {
                    var v_id = $(this).val();
                    /*如果这个v_id值存在，则返回上一页的时候，颜色会回显*/
                    if (isExist(v_id)) {
                        $(this).parents("tr").css("background", "red");
                        this.checked = true;
                    }
                    console.log(this);
                })
            },

            /*汉化*/
            "language": {
                "url": "/datatable/Chinese.json",
            },

            /*每页显示的条数*/
            // 页数选择下拉框内容
            //修改展示的条数
            "lengthMenu": [5, 10, 15, 30, 45],
            /*传的数据*/
            "columns": [
                {
                    /*选中复选框*/
                    "data": "id",
                    "render": function (d, x, r, z) {
                        return "<input type='checkbox' value='" + d + "'/>";
                    }
                },
                {"data": "id"},
                {"data": "userName"},
                {
                    "data": "status",
                    "render": function (data, type, row, mate) {
                        return data == 0 ? "正常" : "锁定"
                    }
                },
                {"data": "userRealName"},
                {
                    "data": "birthday",
                    "render": function (data, type, row, meta) {
                        //时间格式化
                        var date = new Date(data);
                        return date.toLocaleString();
                    }

                },
                {
                    "data": "sex",
                    // "render": function (data, type, row, mate) {
                    //     return data == 0 ? "女" : "男"
                    // }
                    "render": function (data, type, row, mate) {
                        if (data == '1') {
                            return "男";
                        } else {
                            return "女";
                        }
                    }
                },
                {"data": "salary"},
                {"data": "deptName"},
                {
                    "data": "headerPath",
                    render: function (data, type, row, meta) {
                        return "<img  width='80px' src='" + data + "'>";
                    }
                },

                {
                    "data": "id",
                    //渲染按钮
                    "render": function (data, type, row, mate) {
                        return "<div class='btn-group' role='group' aria-label='...'>" +
                            "<button type='button' class='btn btn-primary btn-lg active' onclick='userLockedStatus(\"" + data + "\")'>解锁用户</button>" +
                            "<button type='button' class='btn btn-primary btn-lg active' onclick='deleteUserById(\"" + data + "\")'>删除用户</button>" +
                            "</div>"
                    }

                }
            ],
            //汉化字体
            "language": {
                "url": "<%=request.getContextPath()%>/js/DataTables/Chinese.json"
            }
        });
    }

    //删除用户信息
    function deleteUserById(id) {
        bootbox.confirm({
            message: "您确定要删除吗?",
            size: "small",
            title: "提示信息",
            buttons: {
                confirm: {
                    label: '<span class="glyphicon glyphicon-ok"></span>确定',
                    className: 'btn-success'
                },
                cancel: {
                    label: '<span class="glyphicon glyphicon-remove"></span>取消',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if (result) {
                    $.ajax({
                        url: "<%=request.getContextPath()%>/user/deleteUserById.jhtml",
                        data: {
                            "id": id
                        },
                        type: "post",
                        dataType: "json",
                        success: function (result) {
                            if (result.code == 200) {
                                bootbox.alert({
                                    message: '<span class="glyphicon glyphicon-ok"></span>删除成功',
                                    size: 'small',
                                    title: "提示信息"
                                });
                                search(1);
                            } else {
                                bootbox.alert({
                                    message: '<span class="glyphicon glyphicon-remove"></span>删除失败',
                                    size: 'small',
                                    title: "提示信息"
                                });
                            }
                        }
                    });
                }
            }
        });


    }


    //1.【查询展示zTree树】
    var nodes;
    function initzTree() {
        setting = {
            //给Ztree树定义点击事件
            callback: {
                onClick: zTreeOnClick
            },
            data: {
                simpleData: {
                    enable: true,
                    pIdKey: "fatherid"
                },
                key: {
                    name: "deptName"
                }
            },

        };
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/dept/findDeptList.jhtml",
            success: function (result) {
                nodes = result.data;
                if (result.code == 200) {
                    $.fn.zTree.init($("#treeDemo"), setting, nodes);
                    $.fn.zTree.init($("#treeDemo2"), setting, nodes);
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
    //(1)条件查询方法【三个联动查询,条件查询和展示和Ztree树】
    function search() {
        var v_userName = $("#userName").val();
        var v_minsalary = $("#minsalary").val();
        var v_maxsalary = $("#maxsalary").val();
        var v_minbirthday = $("#minbirthday").val();
        var v_maxbirthday = $("#maxbirthday").val();
        var v_deptids=$("#deptids").val();
        //alert(v_deptids)
        //定义一个字符串
        var parm = {};
        parm.userName = v_userName;
        parm.minsalary = v_minsalary;
        parm.maxsalary = v_maxsalary;
        parm.minbirthday = v_minbirthday;
        parm.maxbirthday = v_maxbirthday;
        parm.deptids=v_deptids;
        userTable.settings()[0].ajax.data = parm;
        userTable.ajax.reload();
    }
    //(2)给Ztree树一个点击事件的方法【三个联动查询,条件查询和展示和Ztree树】
    var v_deptids=[];
    var v_nodeChildrenId=[];
        function zTreeOnClick() {
        //选择一个zTree树再次选择zTree树会把文本框中的值清空,再从选择一个赋值到文本框
        v_deptids=[];
        //获取zTree当前被选中的节点数据集合
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getSelectedNodes();
        if (nodes.length != 1){
            return ;
        }
          //获取选中节点的儿子
            var nodesChild = nodes[0].children;
            if(nodesChild!=null){
                for (var i=0;i<nodesChild.length;i++) {
                    v_nodeChildrenId.push(nodesChild[i].id);

                }
            }
        //运用递归找到本身和本身下的子节点
        var nodeArr = treeObj.transformToArray(nodes);
         //循环数组
        for (var i=0;i<nodeArr.length;i++) {
            // $("#deptids").val($("#deptids").val()+","+nodeArr[i].id)
            //传一个id判断是否相等,不相等说明没有被选过,放到数组中,相等就不再走if判断了
            if (!isexist(nodeArr[i].id)){
                //向数组中赋值
                v_deptids.push(nodeArr[i].id);
            }
        }
        //分割字符串,把前面的,号去掉
        $("#deptids").val(v_deptids.join(","));
        //条件查询时从新加载一下
        search();
    }
    //(3)当选过的Ztree树的id文本框中有就不再能选择【三个联动查询,条件查询和展示和Ztree树】
    //定义一个方法是用来判断再次点击Ztree树时和文本框中id相等时,就直接返回
    function isexist(id) {
        for (var i=0;i<v_deptids.length;i++) {
            if (v_deptids[i]==id){
                return true;
            }
        }
        
    }
    //重置按钮【三个联动查询,条件查询和展示和Ztree树】清空
    function resetZtree() {
        var v_userName = $("#userName").val("");
        var v_minsalary = $("#minsalary").val("");
        var v_maxsalary = $("#maxsalary").val("");
        var v_minbirthday = $("#minbirthday").val("");
        var v_maxbirthday = $("#maxbirthday").val("");
        var v_deptids=$("#deptids").val("");
        //清空数组
        v_deptids=[];
        //清除选中节点
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        treeObj.cancelSelectedNode();
        //刷新
        search();
    }
    //2.【添加zTree树】
    function showAddDept() {
        //获取zTree当前被选中的节点数据集合
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getSelectedNodes();
        //点击获取父级元素的id
        var v_id = nodes[0].id;
        //给html中的input框赋值用attr[把ID赋值给input
        // 框中]
        $("#fatherid").attr("value", v_id);
        //判断选择父节点不能不选择,也不能多选
        if (nodes.length == 1) {
            var v_adddialog = bootbox.dialog({
                title: '添加节点',
                message: $("#formdiv").html(),
                buttons: {
                    ok: {
                        label: '<span class="glyphicon glyphicon-ok"></span>添加',
                        className: 'btn-success',
                        callback: function () {
                            var v_fatherid = $("#fatherid", v_adddialog).val();
                            var v_deptName = $("#deptName", v_adddialog).val();
                            var v_remark = $("#remark", v_adddialog).val();
                            //往后台传参的第二种方式
                            var v_pame = {};
                            v_pame.fatherid = v_fatherid;
                            v_pame.deptName = v_deptName;
                            v_pame.remark = v_remark;
                            $.ajax({
                                url: "<%=request.getContextPath()%>/dept/addDept.jhtml",
                                type: "post",
                                data: v_pame,
                                success: function (result) {
                                    //alert(result.data);
                                    if (result.code == 200) {
                                        //添加到树中
                                        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                                        var v_newNode = {};
                                        v_newNode.deptName = v_deptName;
                                        v_newNode.remark = v_remark;
                                        v_newNode.id = result.data;
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

        } else {
            bootbox.alert({
                message: '<span class="glyphicon glyphicon-remove"></span>请选择节点',
                size: 'small',
                title: "提示信息"
            });
        }
    }
    //【修改zTree树】
    function editDeptDlg() {
        //获取zTree当前被选中的节点数据集合
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getSelectedNodes();
        //点击获取父级元素的id
        var v_ids = nodes[0].id;
        var v_deptNames = nodes[0].deptName;
        var v_remarks = nodes[0].remark;
        //给html中的input框赋值用attr[把ID,deptName,remark赋值回显给input
        // 框中]
        $("#enit_id").attr("value", v_ids);
        $("#enit_deptName").attr("value", v_deptNames);
        //文本域回显值用text
        $("#enit_remark").html(v_remarks);
        //判断选择父节点不能不选择,也不能多选
        if (nodes.length == 1) {
            var v_enitform = bootbox.dialog({
                title: '更新节点',
                message: $("#enit_formdiv").html(),
                buttons: {
                    ok: {
                        label: '<span class="glyphicon glyphicon-ok"></span>更新',
                        className: 'btn-success',
                        callback: function () {
                            //向后台传参数新增
                            var v_id = $("#enit_id", v_enitform).val();
                            var v_deptName = $("#enit_deptName", v_enitform).val();
                            var v_remark = $("#enit_remark", v_enitform).val();
                            //往后台传参的第二种方式
                            var v_pame = {};
                            v_pame.id = v_id;
                            v_pame.deptName = v_deptName;
                            v_pame.remark = v_remark;
                            $.ajax({
                                url: "<%=request.getContextPath()%>/dept/editDeptDlg.jhtml",
                                type: "post",
                                data: v_pame,
                                success: function (result) {
                                    if (result.code == 200) {
                                        //修改前台树结构,只需要修改名称即可
                                        nodes[0].deptName = v_deptName;
                                        nodes[0].remark = v_remark;
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
    //【删除zTree树】
    function deleteDept() {
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        //获取选中的节点
        var v_selectedNodes = treeObj.getSelectedNodes();
        if (v_selectedNodes.length > 0) {
            //运用递归找到本身和本身下的子节点
            var nodesArr = treeObj.transformToArray(v_selectedNodes);
            console.log(nodesArr)
            //定义一个空数组
            var idArr = [];
            //循环递归里面的值,并且放到数组中
            for (var i = 0; i < nodesArr.length; i++) {
                //往数组中放值,只放id
                idArr.push(nodesArr[i].id);
            }
            bootbox.confirm({
                size: "small",
                message: "你确定删除吗！！！",
                callback: function (result) {
                    if (result) {
                        $.ajax({
                            url: '<%=request.getContextPath()%>/dept/deleteDept.jhtml',
                            type: "post",
                            async: true,
                            //前台不仅可以传一个集合到后台,还可以是数组
                            data: {"ids": idArr},
                            success: function (res) {
                                if (res.code == 200) {
                                    bootbox.alert({
                                        message: '<span class="glyphicon glyphicon-ok"></span>删除成功',
                                        size: 'small',
                                        title: "提示信息"
                                    });
                                    //删除前台的Ztree树
                                    for (var i = 0; i < nodesArr.length; i++) {
                                        //往数组中放值,只放id
                                        treeObj.removeNode(nodesArr[i]);
                                    }

                                } else {
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

        } else {
            bootbox.alert({
                message: '<span class="glyphicon glyphicon-ok"></span>请选择要删除的部门！',
                size: 'small',
                title: "提示信息"
            });
        }


    }
    //批量删除用户
    function deleteUser() {
        //通过join将数组转换成字符串
        var ids = v_ids.join(",");
        if (ids.length > 0) {
            bootbox.confirm({
                message: "您确定要删除吗?",
                size: "small",
                title: "提示信息",
                buttons: {
                    confirm: {
                        label: '<span class="glyphicon glyphicon-ok"></span>确定',
                        className: 'btn-success'
                    },
                    cancel: {
                        label: '<span class="glyphicon glyphicon-remove"></span>取消',
                        className: 'btn-danger'
                    }
                },
                callback: function (result) {
                    if (result) {
                        $.ajax({
                            url: "<%=request.getContextPath()%>/user/deleteUser.jhtml",
                            type: "post",
                            data: {"ids": ids},
                            success: function (result) {
                                // console.log(result);
                                if (result.code == 200) {
                                    bootbox.alert({
                                        message: '<span class="glyphicon glyphicon-ok"></span>删除成功',
                                        size: 'small',
                                        title: "提示信息"
                                    });
                                    search(1);
                                } else {
                                    bootbox.alert({
                                        message: '<span class="glyphicon glyphicon-remove"></span>删除失败',
                                        size: 'small',
                                        title: "提示信息"
                                    });
                                }
                            }
                        })
                    }
                }
            })
        } else {
            bootbox.alert({
                message: '<span class="glyphicon glyphicon-hand-down"></span>请选择要删除的信息',
                size: 'small',
                title: "提示信息"
            });
        }

    }



    //解锁
    function userLockedStatus(id) {
        location.href = "<%=request.getContextPath()%>/user/userLockedStatus.jhtml?id=" + id;
    }

    //添加员工时点击选择部门方法
    var v_user;
    function EmpByDept() {
        $("#DeptTreeDiv2").html("<ul id='treeDemo2' class='ztree'></ul>");
        $.fn.zTree.init($("#treeDemo2"), setting, nodes);
        //对话框
        bootbox.dialog({
            title: '选择部门',
            message: $("#DeptTreeDiv2 ul"),
            size: 'small',
            buttons: {
                cancel: {
                    label: "取消",
                    className: 'btn-danger',
                    callback: function () {
                    }
                },
                ok: {
                    label: "确定",
                    className: 'btn-info',
                    callback: function () {
                        //获取当前节点
                        var treeObj = $.fn.zTree.getZTreeObj("treeDemo2");
                        var nodes = treeObj.getSelectedNodes();
                        if (nodes.length == 1) {
                            //添加
                            $("#user_id", v_user).val(nodes[0].id);
                            $("#user_deptName", v_user).val(nodes[0].deptName);
                            //修改
                            $("#user-id", userRowData).val(nodes[0].id);
                            $("#user-deptName", userRowData).val(nodes[0].deptName);
                        }else {
                            bootbox.alert({
                                message: '<span class="glyphicon glyphicon-ok"></span>请选择一个节点！',
                                size: 'small',
                                title: "提示信息"
                            });

                        }
                    }
                }
            }
        });
    }

    //【用户添加方法】
    function toAddUser() {
        v_user = bootbox.dialog({
            title: '添加用户',
            message: $("#from_user form"),
            buttons: {
                ok: {
                    label: '<span class="glyphicon glyphicon-ok"></span>添加用户',
                    className: 'btn-success',
                    callback: function (result) {
                        if (result) {
                            var v_userName = $("#user_userName", v_user).val();
                            var v_userPassword = hex_md5($("#user_userPassword", v_user).val());
                            var v_userRealName = $("#user_userRealName", v_user).val();
                            var v_birthday = $("#user_birthday", v_user).val();
                            var v_sex = $("input[name='user_sex']:checked", v_user).val();
                            var v_salary = $("#user_salary", v_user).val();
                            var v_id = $("#user_id", v_user).val();
                            var v_headerPath=$("#headerPath",v_user).val();
                            var parm = {};
                            parm.userName = v_userName;
                            parm.userPassword = v_userPassword;
                            parm.userRealName = v_userRealName;
                            parm.birthday = v_birthday;
                            parm.sex = v_sex;
                            parm.salary = v_salary;
                            parm.deptid = v_id;
                            parm.headerPath=v_headerPath;
                            $.ajax({
                                url: "<%=request.getContextPath()%>/user/addUser.jhtml",
                                type: "post",
                                data: parm,
                                success: function (result) {
                                    console.log(result);
                                    if (result.code == 200) {
                                        search();

                                    } else {
                                        bootbox.alert({
                                            message: '<span class="glyphicon glyphicon-remove"></span>' + result.msg,
                                            size: 'small',
                                            title: "提示信息"
                                        });
                                    }
                                }
                            })
                        }
                    },
                },
                cancel: {
                    label: '<span class="glyphicon glyphicon-remove"></span>取消',
                    className: 'btn-danger',
                }
            },
        });

        initDateTime();
        //再还
        $("#from_user").html(addheaderImage);
        initHeaderImage();
    }


    //批量修改用户中的部门信息
    function updatebatch(){
        if (v_ids.length==0){
            bootbox.alert({
                message: '<span class="glyphicon glyphicon-exclamation-sign"></span>请选择用户',
                size: 'small',
                title: "提示信息"
            });
        } else {
            v_user = bootbox.dialog({
                title: '批量修改用户部门',
                message: $("#DeptTreeDiv2 ul"),//弹出来Ztree树
                buttons: {
                    ok: {
                        label: '<span class="glyphicon glyphicon-ok"></span>批量修改用户部门',
                        className: 'btn-success',
                        callback: function (result) {
                            //获取当前节点
                            var treeObj = $.fn.zTree.getZTreeObj("treeDemo2");
                            var nodes = treeObj.getSelectedNodes();
                            if (nodes.length == 1) {
                               var deptid= nodes[0].id;
                                $.ajax({
                                    url: "<%=request.getContextPath()%>/user/updatebatch.jhtml",
                                    type: "post",
                                    data: {"ids":v_ids,"deptid":deptid},
                                    success: function (result) {
                                        if (result.code == 200) {
                                            search();
                                        } else {
                                            bootbox.alert({
                                                message: '<span class="glyphicon glyphicon-remove"></span>批量修改用户部门成功',
                                                size: 'small',
                                                title: "提示信息"
                                            });
                                        }
                                    }
                                })
                            }else {
                                bootbox.alert({
                                    message: '<span class="glyphicon glyphicon-exclamation-sign"></span>请选择一个用户',
                                    size: 'small',
                                    title: "提示信息"
                                });
                            }
                        },
                    },
                    cancel: {
                        label: '<span class="glyphicon glyphicon-remove"></span>取消',
                        className: 'btn-danger'
                    }
                },
            });
        }
    }

    //【用户回显】
    var userRowData;
    function toupdateUser() {
        userRowData=bootbox.dialog({
            title: '修改用户',
            //把div全部挖走了,隐藏掉了,form提取出来了
            message: $("#from-user").html(),
            buttons: {
                ok: {
                    label: '<span class="glyphicon glyphicon-ok"></span>更新用户',
                    className: 'btn-success',
                    callback: function (result) {
                        if (result) {
                            var v_id = $("#userid", userRowData).val();
                            var v_userName = $("#user-userName", userRowData).val();
                            var v_userRealName = $("#user-userRealName", userRowData).val();
                            var v_birthday = $("#user-birthday", userRowData).val();
                            var v_sex = $("input[name='user-sex']:checked", userRowData).val();
                            var v_salary = $("#user-salary", userRowData).val();
                            var v_deptid = $("#user-id", userRowData).val();
                            var v_headerPath=$("#user_headerPath",userRowData).val();
                            var v_old_headerPath=$("#old_headerPath",userRowData).val();

                            $.ajax({
                                url: "<%=request.getContextPath()%>/user/updateUser.jhtml",
                                type: "post",
                                data: {
                                    "id": v_id,
                                    "userName": v_userName,
                                    "userRealName": v_userRealName,
                                    "birthday": v_birthday,
                                    "sex": v_sex,
                                    "salary": v_salary,
                                    "deptid": v_deptid,
                                    "headerPath":v_headerPath,
                                    "oldheaderPath":v_old_headerPath

                                },
                                success: function (result) {
                                    console.log(result);
                                    if (result.code == 200) {
                                        search();
                                    } else {
                                        bootbox.alert({
                                            message: '<span class="glyphicon glyphicon-remove"></span>' + result.msg,
                                            size: 'small',
                                            title: "提示信息"
                                        });
                                    }
                                },

                            })
                        }
                    },
                },
                cancel: {
                    label: '<span class="glyphicon glyphicon-remove"></span>取消',
                    className: 'btn-danger',
                    callback: function (result) {
                        v_ids = [];
                        checkedRow.css("background", "");
                        v_checkbox.checked = false;
                    }

                }

            }
        })
        //获取选中的id
        var v_idArr = "";
        //获得当前行
        var v_selectedRow_count = $("#userTable tbody tr input[type='checkbox']:checked").length;
        //判断
        if (v_selectedRow_count == 1) {
            v_idArr = $("#userTable tbody tr input[type='checkbox']:checked")[0].value;
            //发送ajax请求
            $.ajax({
                url: "<%=request.getContextPath()%>/user/toupdateUser.jhtml",
                type: "post",
                data: {"id": v_idArr},
                success: function (result) {
                    if (result.code == 200) {
                        var userData = result.data;
                        $("#userid").val(userData.id);
                        $("#user-userName").val(userData.userName);
                        $("#user-userRealName").val(userData.userRealName);
                        $("#user-birthday").val(new Date(userData.birthday).Format("yyyy-MM-dd"));
                        $("[name='user-sex'][value='" + userData.sex + "']").prop("checked", true);
                        $("#user-salary").val(userData.salary);
                        $("#user-deptName").val(userData.deptName);
                        $("#user-id").val(userData.deptid);
                        //图片回显
                        initEditHeaderImage(userData.headerPath);
                        //回显老路径
                        $("#old_headerPath").val(userData.headerPath);
                    }
                }
            })
        }
        initDateTime(userRowData);
    }



    //按部门导出用户
    function exportDeptExcel(){
        if (v_nodeChildrenId.length>0){
            //找到form表单的ID
            var productForm = document.getElementById("userForm");
            productForm.action = "<%=request.getContextPath()%>/user/exportDeptExcel.jhtml?childNode="+v_nodeChildrenId;
            productForm.method = "post";
            productForm.submit();
            v_nodeChildrenId=[];

        } else {
            bootbox.alert({
                message: '<span class="glyphicon glyphicon-exclamation-sign"></span>请选择部门',
                size: 'small',
                title: "提示信息"
            });

        }


    }


</script>
<script type="text/html" id="from-user">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-2 control-label">用户名:</label>
                <div class="col-sm-3">
                    <input class="form-control" type="hidden" id="userid"/>
                    <input class="form-control" type="text" id="user-userName"/>
                </div>

            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">真是姓名:</label>
                <div class="col-sm-3">
                    <input class="form-control" type="text" id="user-userRealName"/>
                </div>
                <label class="col-sm-2 control-label">生日:</label>
                <div class="col-sm-3">
                    <input class="form_datetime form-control" type="text" id="user-birthday"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">性别:</label>
                <div class="col-sm-3">
                    女:<input type="checkbox" name="user-sex" value="0"/>
                    男:<input type="checkbox" name="user-sex" value="1"/>
                </div>

                <label class="col-sm-2 control-label">薪资:</label>
                <div class="col-sm-3">
                    <input class="form-control" type="text" id="user-salary"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">所在部门:</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control data-form-start" id="user-deptName">
                    <input type="text" class="form-control data-form-start" id="user-id">
                </div>
                <div class="col-sm-2" style="padding: 0px;">
                    <button type="button" class="btn btn-primary" onclick="EmpByDept()">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>选择部门
                    </button>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">头像:</label>
                <div class="col-sm-8">
                    <input type="file" name="headerImage" id="user_headerImage">
                    <%--商品新主图路径隐藏域--%>
                    <input type="text" id="user_headerPath"/>
                    <!--旧的路径-->
                    <input type="text" id="old_headerPath"/>


                </div>
            </div>
        </form>
</script>
</body>
</html>

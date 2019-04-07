<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/3/1
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--DataTables-->
    <link href="/js/DataTables/DataTables-1.10.18/css/jquery.dataTables.min.css" rel="stylesheet">
    <!--bootstrap-->
    <link href="/js/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/js/DataTables/DataTables-1.10.18/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/js/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet" media="screen">
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
    <script src="/js/bootstrap/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/dataTables.bootstrap.min.js"></script>
    <!--bootbox-->
    <script src="<%=request.getContextPath()%>/js/bootbox/bootbox.min.js"></script>
    <!--日期插件-->
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker-master/moment-with-locales.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
<script>
    //onReady【页面加载事件】
    $(function () {
        //初始化表格页面
        initbrandTable();
        //表格变色
        initBrandColor();
        initDateTime();
    });


    //bootstrap-datetimepicker-master时间
    function initDateTime() {
        $(".form_datetime").datetimepicker({
            format: "yyyy-mm-dd",
            autoclose: true,
            todayBtn: true,
            pickerPosition: "bottom-left",
            clearBtn:true

        });
    }
    //初始化表格
    var brandTable;
    function initbrandTable() {
        brandTable = $('#brandTable').DataTable({
            "processing": true,
            "serverSide": true,
            //去掉搜索框
            "searching": false,
            //修改展示的条数
            "lengthMenu": [5, 10, 15, 30, 45],
            //默认排序
            "order":[],
            "aoColumnDefs": [
                {
                    "bSortable": false, "aTargets": [ 0 , 1, 2,5]
                }
            ],
            "ajax": {
                "url": "<%=request.getContextPath()%>/brand/querylistbrand.jhtml",
                "type":"post",
                //使用ServerResponse来进行分页查询
                dataSrc: function (result) {
                    alert(result);
                    //把dataTable里的数据是放在最外面的data里 所以要提取出来
                    //这样result.data.data就能取到值了 否则前台数据能显示出来但是分页插件不显示几页几条
                    result.draw=result.data.draw;
                    result.recordsTotal=result.data.recordsTotal;
                    result.recordsFiltered=result.data.recordsFiltered;
                    return result.data.data;
                }
            },
            "drawCallback": function (x) {
                //获取当前表格所有行中的复选框
                $("#brandTable tbody tr input[type='checkbox']").each(function () {
                    var v_Id = $(this).val();
                    console.log(v_Id);
                    //当前值和数据组的值进行对比,如果一样就将背景颜色回填
                    if (isExist(v_Id)) {
                        $(this).closest("tr").css("background", "yellow");
                        this.checked = true;
                    }
                })
            },

            "columns": [
                {
                    "data": "id",
                    "render": function (data, type, row, meta) {
                        return "<input type='checkbox' value='" + data + "'/>"
                    }
                },

                {"data": "id"},
                {"data": "brandname"},
                {"data": "insertDateTime"},
                {"data": "updateDateTime"},
                {"data": "id",
                    //渲染按钮
                    "render": function (data, type, row, mate) {
                        return "<div class='btn-group' role='group' aria-label='...'>" +
                            "  <button type='button' class='btn btn-info dropdown-toggle' onclick='brandupdateto(\"" + data + "\")'>修改</button>" +
                            "  <button type='button' class='btn btn-danger dropdown-toggle' onclick='deletelistbrand(\"" + data + "\")'>删除</button>" +
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

    //表格点击变色
    var v_ids = [];

    function initBrandColor() {
        $("#brandTable tbody").on("click", "tr", function () {
            //找到当前行中的复选框的值
            var v_checkbox = $(this).find("input[type='checkbox']")[0];
            //当复选框默认被选中时
            if (v_checkbox.checked) {
                v_checkbox.checked = false;
                //背景颜色取消,
                $(this).css("background", "");
                //删除数组中的值
                delIds(v_checkbox.value);

            } else {
                v_checkbox.checked = true;
                $(this).css("background", "yellow");
                v_ids.push(v_checkbox.value);
                //console.log(v_ids);
            }
        })
    }

    //删除数组中的值
    function delIds(id) {
        for (var i = v_ids.length - 1; i >= 0; i--) {
            if (v_ids == id) {
                v_ids.splice(i, 1);

                break;
            }
        }
    }

    //比较数组中的值是否和返回的值一样
    function isExist(id) {
        for (var i = 0; i < v_ids.length; i++) {
            if (v_ids[i] == id) {
                return true;
            }
        }
    }
    /**
     * 条件查询传递参数
     */
    function search() {
        var v_brandname = $("#brandname").val();
        var v_mininsertDate = $("#mininsertDate").val();
        var v_maxinsertDate = $("#maxinsertDate").val();
        var v_minupdateDate = $("#minupdateDate").val();
        var v_maxupdateDate = $("#maxupdateDate").val();
        var param = {};
        param.brandname = v_brandname;
        param.mininsertDate = v_mininsertDate;
        param.maxinsertDate = v_maxinsertDate;
        param.minupdateDate = v_minupdateDate;
        param.maxupdateDate = v_maxupdateDate;
        brandTable.settings()[0].ajax.data = param;
        //重新加载
        brandTable.ajax.reload();
    }
   //添加
    function toaddlistbrand() {
        location.href="<%=request.getContextPath()%>/brand/toaddlistbrand.jhtml";
    }
    //回显
    function brandupdateto(id) {
        location.href="<%=request.getContextPath()%>/brand/brandupdateto.jhtml?id=" + id;
    }
    //删除
    function deletelistbrand(id) {
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
                        url: "<%=request.getContextPath()%>/brand/deletelistbrand.jhtml",
                        type: "post",
                        data: {"id": id},
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

                    })
                }
            }
        })
    }

    //批量删除
    function deletemore() {
        var rowids = v_ids.join(",");
        if (v_ids.length > 0) {
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
                if (result){
                    $.ajax({
                        url: "<%=request.getContextPath()%>/brand/deletemore.jhtml",
                        type: "post",
                        data: {"ids": rowids},
                        success: function (result) {
                            console.log(result);
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
        }else {
            bootbox.alert({
                message: '<span class="glyphicon glyphicon-hand-down"></span>请选择要删除的信息',
                size: 'small',
                title: "提示信息"
            });
        }
    }
</script>
</head>
<body>
<div class="container-fluid" style="padding-left: 0px;">
    <div class="row">
        <div>
            <div class="container-fluid">
                <div class="row">

                    <form class="form-horizontal" id="productFrom" method="post">
                        <fieldset>
                            <div style="width: 100%;background: lightskyblue">
                                <legend>品牌表条件查询</legend>
                            </div>
                            <div class="form-group">
                                <label for="brandName" class="col-md-2 control-label">品牌名称:</label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" id="brandname" placeholder="text">
                                </div>

                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">录入时间</label>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="text" class="form_datetime input-sm form-control" id="mininsertDate"
                                               class="dateTimeJs"/>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span>
                                        </span>
                                        <input type="text" class="form_datetime input-sm form-control" id="maxinsertDate"
                                               />
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">修改时间</label>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="text" class="form_datetime input-sm form-control" id="minupdateDate"/>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span>
                                        </span>
                                        <input type="text" class="form_datetime input-sm form-control" id="maxupdateDate"/>
                                    </div>
                                </div>
                            </div>



                        </fieldset>
                        <div style="text-align: center">
                            <button type="button" class="btn btn-primary btn-lg active" onclick="search()"><span
                                    class="glyphicon glyphicon-ok" aria-hidden="true"></span>搜索
                            </button>
                            <button type="button" class="btn btn-default btn-lg active"><span
                                    class="glyphicon glyphicon-refresh" aria-hidden="true"></span>重置
                            </button>
                        </div>
                    </form>
                </div>

            </div>
            <div style="background: darkgrey;text-align: right;margin-top: 25px">
                <button type="button" class="btn btn-info btn-lg active" onclick="toaddlistbrand();"><span
                        class="glyphicon glyphicon-pencil" aria-hidden="true"></span>添加品牌
                </button>
                <button type="button" class="btn btn-danger btn-lg active" onclick="deletemore();"><span
                        class="glyphicon glyphicon-remove" aria-hidden="true"></span>批量删除
                </button>
            </div>
            <div class="panel panel-primary">

                <div class="panel-body" style="background: lightskyblue">
                    品牌列表
                </div>
                <div class="panel-footer">
                    <table id="brandTable" class="table table-striped table-bordered" style="width:100%">
                        <thead>
                        <tr>
                            <th>复选框</th>
                            <th>序列</th>
                            <th>品牌名称</th>
                            <th>录入时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>复选框</th>
                            <th>序列</th>
                            <th>品牌名称</th>
                            <th>录入时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>


</body>
</html>

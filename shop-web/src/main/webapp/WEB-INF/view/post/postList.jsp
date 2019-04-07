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
    <link href="/js/DataTables/DataTables-1.10.18/css/jquery.dataTables.min.css" rel="stylesheet"/>
    <!--bootstrap-->
    <link href="/js/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/js/DataTables/DataTables-1.10.18/css/dataTables.bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/js/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet" media="screen"/>
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
            initpostTable();
            //表格变色
            initBrandColor();
            initDateTime();
            initArea(0);
        });



        /**
         * 初始化地区
         */
        function initArea(pid, obj) {

            $.ajax({
                url: "<%=request.getContextPath()%>/post/findAreaList.jhtml",
                type: "post",
                data: {"pid": pid},

                success: function (res) {
                   // alert(0);
                    var v_item = res.data.data;
                    if (obj) {
                        //删除父级元素所有的同级元素
                        $(obj).parent().nextAll().remove()
                    }
                    if (v_item.length == 0) {
                        //如果返回的数据是0条的话就直接返回 不用再拼接
                        return;
                    }
                    if (res.code == 200) {
                        var areaSelectDiv = "<div class=\"col-md-2\">\n" +
                            "<select class=\"form-control\" name=\"areaIds\" onchange='initArea(this.value,this)'>\n" +
                            "<option value=\"-1\">请选择</option>";


                        for (var i = 0; i < v_item.length; i++) {
                            var v_areaDate = v_item[i];
                            areaSelectDiv += "<option value='" + v_areaDate.id + "'>" + v_areaDate.name + "</option>";
                        }
                        areaSelectDiv += " </select>\n" +
                            "                    </div>";
                        $("#areaSelect").append(areaSelectDiv);

                    }

                }
            });
        }




        //bootstrap-datetimepicker-master时间
        function initDateTime() {
            $(".form_datetime").datetimepicker({
                format: "yyyy-mm-dd",
                autoclose: true,
                todayBtn: true,
                pickerPosition: "bottom-left",
                clearBtn: true

            });
        }


        //初始化表格
        var postTable;

        function initpostTable() {
            postTable = $('#postTable').DataTable({
                "processing": true,
                "serverSide": true,
                //去掉搜索框
                "searching": false,
                //修改展示的条数
                "lengthMenu": [5, 10, 15, 30, 45],
                //默认排序
                "order": [],
                "aoColumnDefs": [
                    {
                        "bSortable": false, "aTargets": [0, 1, 2, 5]
                    }
                ],
                "ajax": {
                    "url": "<%=request.getContextPath()%>/post/findPostList.jhtml",
                    "type": "post",
                    //使用ServerResponse来进行分页查询
                    dataSrc: function (result) {
                        //把dataTable里的数据是放在最外面的data里 所以要提取出来
                        //这样result.data.data就能取到值了 否则前台数据能显示出来但是分页插件不显示几页几条
                        result.draw = result.data.draw;
                        result.recordsTotal = result.data.recordsTotal;
                        result.recordsFiltered = result.data.recordsFiltered;
                        return result.data.data;
                    }
                },
                "drawCallback": function (x) {
                    //获取当前表格所有行中的复选框
                    $("#postTable tbody tr input[type='checkbox']").each(function () {
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
                    {"data": "userName"},
                    {"data": "phone"},
                    {"data": "email"},
                    {
                        "data": "birthday",
                        "render": function (data, type, row, meta) {
                            //时间格式化
                            var date = new Date(data);
                            return date.toLocaleString();
                        }

                    },
                    {
                        "data": "regTime",
                        "render": function (data, type, row, meta) {
                            //时间格式化
                            var date = new Date(data);
                            return date.toLocaleString();
                        }

                    },
                    {
                        "data": "lastLoginTime",
                        "render": function (data, type, row, meta) {
                            //时间格式化
                            var date = new Date(data);
                            return date.toLocaleString();
                        }
                    },

                    {"data": "address"},
                    {
                        "data": "id",
                        //渲染按钮
                        "render": function (data, type, row, mate) {
                            return "<div class='btn-group' role='group' aria-label='...'>" +
                                "  <button type='button' class='btn glyphicon glyphicon-refresh' onclick='toupdapost(\"" + data + "\")'>修改</button>" +
                                "  <button type='button' class='btn btn-info dropdown-toggle' onclick='postupdateto(\"" + data + "\")'>禁用</button>" +
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


        //回显
        function toupdapost(id) {
            location.href = "<%=request.getContextPath()%>/post/toupdapost.jhtml?id=" + id;
        }


        //表格点击变色
        var v_ids = [];
        function initBrandColor() {
            $("#postTable tbody").on("click", "tr", function () {
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
            var v_userName = $("#userName").val();
            var v_minbirthday = $("#minbirthday").val();
            var v_maxbirthday = $("#maxbirthday").val();
            var v_areaIds = "";
            $("select[name='areaIds']").each(function () {
                if (this.value != -1) {
                    v_areaIds += "," + this.value;
                }

            })
            console.log(v_areaIds)
            var param = {};


            param.userName = v_userName;
            param.minbirthday = v_minbirthday;
            param.maxbirthday = v_maxbirthday;
            param.areaIds = v_areaIds;
            postTable.settings()[0].ajax.data = param;
            //重新加载
            postTable.ajax.reload();
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
                                <legend>会员表条件查询</legend>
                            </div>
                            <div class="form-group">
                                <label for="userName" class="col-md-2 control-label">会员名称:</label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" id="userName" placeholder="text">
                                </div>

                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">生日范围</label>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="text" class="form_datetime input-sm form-control"
                                               id="minbirthday"/>
                                        <span class="input-group-addon">
    <span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span>
    </span>
                                        <input type="text" class="form_datetime input-sm form-control"
                                               id="maxbirthday"/>
                                    </div>
                                </div>
                            </div>


                            <%--下拉框--%>
                            <div class="form-group" id="areaSelect">
                                <label class="col-md-2 control-label">家庭地址:</label>
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

            <div class="panel panel-primary">

                <div class="panel-body" style="background: lightskyblue">
                    会员列表
                </div>
                <div class="panel-footer">
                    <table id="postTable" class="table table-striped table-bordered" style="width:100%">
                        <thead>
                        <tr>
                            <th>选择</th>
                            <th>序列</th>
                            <th>用户名称</th>
                            <th>手机号</th>
                            <th>邮箱</th>
                            <th>生日</th>
                            <th>注册时间</th>
                            <th>登录时间</th>
                            <th>户籍</th>
                            <th>操作</th>


                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>选择</th>
                            <th>序列</th>
                            <th>用户名称</th>
                            <th>手机号</th>
                            <th>邮箱</th>
                            <th>生日</th>
                            <th>注册时间</th>
                            <th>登录时间</th>
                            <th>户籍</th>
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

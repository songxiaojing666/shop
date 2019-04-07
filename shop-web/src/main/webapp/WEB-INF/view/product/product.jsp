<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 上次登录的时间 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/common/script.jsp"></jsp:include>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/myjs/common.js"></script>
    <!-- 通过用js的方法写动态下拉框的ajax直接引用 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/myjs/brand.js"></script>
    <script>
        //onReady【页面加载事件】
        $(function () {
            //初始化表格页面
            initProductTable();
            //动态下拉框的ajax页面加载
            //initbrandList("brandid");
            //动态下拉框的ajax页面加载
            initbrandList("brandid");

            //点击表格变色
            initColor();
            //时间插件
            initDateTime();
            //fileinput图片插件上传
            //initBrandLogo();
        });



        //bootstrap-datetimepicker-master时间
        function initDateTime() {
            $(".form_datetime").datetimepicker({
                format: "yyyy-mm-dd hh:mm:00",
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

        //初始化表格
        var productTable;
        function initProductTable() {
            productTable = $('#productTable').DataTable({
                "processing": true,
                "serverSide": true,
                //去掉搜索框
                "searching": false,
                //默认排序
                "order":[],
                "aoColumnDefs": [
                    {
                        "bSortable": false, "aTargets": [ 0 , 1, 5, 6, 7]
                    }
                ],
                //修改展示的条数
                "lengthMenu": [5, 10, 15, 30, 45],
                "ajax": {
                    "url": "<%=request.getContextPath()%>/product/querylist.jhtml",
                    "type":"post",
                    //用于处理服务器端返回的数据。 dataSrc是DataTable特有的
                    dataSrc: function (result) {
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
                    $("#productTable tbody tr input[type='checkbox']").each(function () {
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
                    {"data": "productName"},
                    {"data": "productPrice"},
                    {
                        "data": "insertDate",
                        "render": function (data, type, row, meta) {
                            //时间格式化
                            var date = new Date(data);
                            return date.toLocaleString();
                        }
                    },
                    {
                        "data": "updateDate",
                        "render": function (data, type, row, meta) {
                            //时间格式化
                            var date = new Date(data);
                            var y = date.getFullYear();
                            var M = date.getMonth() + 1;
                            var d = date.getDate();
                            var H = date.getHours();
                            var m = date.getMinutes();
                            var s = date.getSeconds();
                            return y + '-' + M + '-' + d + '  ' + H + ':' + m + ':' + s;
                        }

                    },
                    {"data": "brandname"},
                    {
                        "data": "image",
                        render: function (data, type, row, meta) {
                            return "<img  width='80px' src='" + data + "'>";
                        }
                    },
                    {
                        "data": 'id',
                        //渲染按钮
                        "render": function (data, type, row, mate) {
                            return "<div class='btn-group' role='group' aria-label='...'>" +
                                "  <button type='button' class='btn btn-info dropdown-toggle' onclick='toupdaproduct(\"" + data + "\")'>修改</button>" +
                                "  <button type='button' class='btn btn-danger dropdown-toggle' onclick='deleteproduct(\"" + data + "\")'>删除</button>" +
                                "<button type='button' class='btn btn-default' onclick='queryproductImage(\"" + data + "\")'>查看子图信息</button>" +
                                "</div>"
                        }
                    },

                ],
                //汉化字体
                "language": {
                    "url": "<%=request.getContextPath()%>/js/DataTables/Chinese.json"
                },




            });
        }

        //条件查询方法
        function search() {
            var v_productName = $("#productName").val();
            var v_minprice = $("#minprice").val();
            var v_maxprice = $("#maxprice").val();
            var v_mininsertDate = $("#mininsertDate").val();
            var v_maxinsertDate = $("#maxinsertDate").val();
            var v_minupdateDate = $("#minupdateDate").val();
            var v_maxupdateDate = $("#maxupdateDate").val();
            var v_brandid = $("#brandid").val();
            //定义一个字符串
            var parm = {"brand.id": v_brandid};
            parm.productName = v_productName;
            parm.minprice = v_minprice;
            parm.maxprice = v_maxprice;
            parm.mininsertDate = v_mininsertDate;
            parm.maxinsertDate = v_maxinsertDate;
            parm.minupdateDate = v_minupdateDate;
            parm.maxupdateDate = v_maxupdateDate;
            productTable.settings()[0].ajax.data = parm;
            productTable.ajax.reload();
        }

        //表格点击变色
        var v_ids = [];

        function initColor() {
            $("#productTable tbody").on("click", "tr", function () {
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

        //删除
        function deleteproduct(id) {
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
                            url: "<%=request.getContextPath()%>/product/deleteproduct.jhtml",
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

        //批量删除
        function deleteBatch() {
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
                                url: "<%=request.getContextPath()%>/product/deleteBatch.jhtml",
                                type: "post",
                                data: {"ids": ids},
                                success: function (result) {
                                    console.log(result);
                                    if (result.code == 200) {
                                        bootbox.alert({
                                            message: '<span class="glyphicon glyphicon-ok"></span>删除成功',
                                            size: 'small',
                                            title: "提示信息"
                                        });
                                        search(1);
                                    }else {
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


        //导出Excel表【js动态提交form表单】
        function exportExcel() {
            //找到form表单的ID
            var productForm = document.getElementById("productForm");
            productForm.action = "<%=request.getContextPath()%>/product/exportExcelproduct.jhtml";
            productForm.method = "post";
            productForm.submit();
        }

        //Excel品牌导出数据
        function exportBrandExcel() {
            //找到form表单的ID
            var productForm = document.getElementById("productForm");
            productForm.action = "<%=request.getContextPath()%>/product/exportBrandExcel.jhtml";
            productForm.method = "post";
            productForm.submit();
        }

        //添加页面
        function toAdd() {
            location.href = "<%=request.getContextPath()%>/product/toadd.jhtml";
        }

        //查询子图片
        function queryproductImage(id) {
            location.href = "<%=request.getContextPath()%>/product/queryproductImage.jhtml?id=" + id;
        }

        //回显页面
        function toupdaproduct(id) {
            location.href = "<%=request.getContextPath()%>/product/toupdaproduct.jhtml?id=" + id;
        }

        //跳转到index页面
        function index(){
            location.href="index.jsp";
        }

        // $(function(){
        //     initbrandList("brandid");
        //
        // })
        // //动态添加多张子图
        // function addImages() {
        //     //给多张图片文件域tr行设置一个唯一标识,找到当前行
        //     //jquery方法【after()在每个匹配的元素之后插入内容。last()获取最后个元素
        //     $("tr[name='childsImages']").last().after('<tr name="childsImages"><td>商品子图:</td><td><input type="file" name="childsImages"/><input type="button" value="-" onclick="removeRow(this);"/></td></tr>');
        // }
        // //一个-点击按钮【不选择多个图片取消掉选择图片的文本框】
        // function removeRow(obj) {
        //     //jquery方法【取得一个包含着所有匹配元素的祖先元素的元素集合（不包含根元素）。
        //     // 可以通过一个可选的表达式进行筛选。
        //     $(obj).parents("tr").remove();
        // }




    </script>

</head>
<body>
<div style="background: #66F4DF">
    <h3>欢迎<font color="red">${user.userName}</font>登陆成功！
        您上次登陆的时间是<font color="red"><f:formatDate value="${user.loginTimes}" pattern="yyyy-MM-dd HH:mm:ss" type="date" /></font>
        您今天登陆了<font color="red">${user.frequency}</font>次
        <font color="blue"><a href="/user/logout.jhtml">退出</a></font>
    </h3>

</div>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">飞狐教育</a>
        </div>


        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="<%=request.getContextPath()%>/product/toList.jhtml">产品后台管理
                    <span class="sr-only">(current)</span></a></li>
                <li><a href="<%=request.getContextPath()%>/brand/toBandList.jhtml">品牌后台管理</a></li>
                <li><a href="<%=request.getContextPath()%>/user/toQuery.jhtml">用户后台管理</a></li>
                <li><a href="<%=request.getContextPath()%>/log/toLog.jhtml">日志后台管理</a></li>
                <li><a href="<%=request.getContextPath()%>/area/toAreaList.jhtml">地区后台管理</a></li>
                <li><a href="<%=request.getContextPath()%>/post/toPostQuery.jhtml">会员后台管理</a></li>

            </ul>
            </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<!--条件查询和展示-->
<div class="container-fluid" style="padding-left: 0px;">
    <div class="row">
        <div>
            <div class="container-fluid">
                <div class="row">

                    <form class="form-horizontal" id="productForm">
                        <fieldset>
                            <legend>商品条件查询</legend>
                            <div class="form-group">
                                <label for="productName" class="col-md-2 control-label">商品名称</label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" id="productName" placeholder="text">
                                </div>
                                <label class="col-md-2 control-label">商品价格</label>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="text" class="input-sm form-control" id="minprice"/>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-yen" aria-hidden="true"></span>
                                        </span>
                                        <input type="text" class="input-sm form-control" id="maxprice"/>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">录入时间</label>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="text" class="form_datetime form-control" id="mininsertDate"
                                        />

                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span>
                                        </span>
                                        <input type="text" class="form_datetime form-control" id="maxinsertDate"
                                        />
                                    </div>
                                </div>
                                <label class="col-md-2 control-label">修改时间</label>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="text" class="form_datetime form-control" id="minupdateDate"
                                        />
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span>
                                        </span>
                                        <input type="text" class="form_datetime form-control" id="maxupdateDate"
                                        />
                                    </div>
                                </div>
                            </div>


                            <%--下拉框--%>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">商品品牌:</label>
                                <div class="col-sm-4">
                                    <!-- 样式1 -->
                                    <select class="form-control" id="brandid" name="brand.id">
                                        <option value="-1">请选择</option>
                                    </select>
                                </div>
                                <div class="col-sm-4 tips"></div>
                            </div>





                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-2 control-label">商品品牌</label>--%>
                                <%--<div class="col-sm-4">--%>
                                    <%--<!-- 样式1 -->--%>
                                    <%--<select class="form-control" id="brandid" name="brandname">--%>
                                        <%--<option value="-1">请选择</option>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                                <%--<div class="col-sm-4 tips"></div>--%>
                            <%--</div>--%>


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
                <button type="button" class="btn btn-default btn-lg" onclick="exportExcel()"><span
                        class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>产品Excel
                </button>
                <button type="button" class="btn btn-primary btn-lg" onclick="exportBrandExcel()"><span
                        class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>品牌导出Excel
                </button>
                <button type="button" class="btn btn-info btn-lg active" onclick="toAdd()"><span
                        class="glyphicon glyphicon-pencil" aria-hidden="true"></span>添加产品
                </button>
                <button type="button" class="btn btn-danger btn-lg active" onclick="deleteBatch()"><span
                        class="glyphicon glyphicon-remove" aria-hidden="true"></span>批量删除
                </button>
            </div>
            <div class="panel panel-primary">

                <div class="panel-body" style="background: #00b3ee">
                    商品列表
                </div>
                <div class="panel-footer">
                    <table id="productTable" class="table table-striped table-bordered" style="width:100%">
                        <thead>
                        <tr>
                            <th>选择:</th>
                            <th>产品名称:</th>
                            <th>产品价格:</th>
                            <th>录入时间:</th>
                            <th>修改时间:</th>
                            <th>品牌名:</th>
                            <th>产品图片:</th>
                            <th>操作:</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>选择:</th>
                            <th>产品名称:</th>
                            <th>产品价格:</th>
                            <th>录入时间:</th>
                            <th>修改时间:</th>
                            <th>品牌名:</th>
                            <th>产品图片</th>
                            <th>操作:</th>
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

<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/3/3
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/md5.js"></script>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/jquery-3.3.1.js"></script>
    <link rel="stylesheet" type="text/css" href="/js/DataTables/datatables.min.css"/>
    <link rel="stylesheet" type="text/css" href="/js/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <script type="text/javascript" src="/js/DataTables/datatables.min.js"></script>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/dataTables.bootstrap.min.js"></script>
    <!--bootbox-->
    <script src="<%=request.getContextPath()%>/js/bootbox/bootbox.min.js"></script>
    <script src="/js/bootstrap/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <!--日期插件-->
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker-master/moment-with-locales.js"></script>
    <script type="text/javascript"

            src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script>
    $(function () {
        initDateTime();
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

</script>
</head>
<body>
<div class="container-fluid">
    <fieldset>
        <div style="width: 100%;background: lightskyblue;text-align: center">
            <legend>修改用户:</legend>
        </div>
    <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/store/updatestore.jhtml" method="post">
        <input class="form_datetime form-control" type="hidden" name="id" value="${userinfo.id}"/>
        <div class="form-group">
            <label class="col-sm-2 control-label">真是姓名:</label>
            <div class="col-sm-3">
                <input class="form-control" type="text" name="userRealName" value="${userinfo.userRealName}"/>
            </div>
            <label class="col-sm-2 control-label">生日:</label>
            <div class="col-sm-3">
                <input class="form_datetime form-control" type="text" name="birthday" value="<f:formatDate value="${userinfo.birthday}"/>"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">性别:</label>
            <div class="col-sm-3">
                女:<input  type="checkbox" name="sex" value="0" ${userinfo.sex==0?"checked":""}/>
                男:<input  type="checkbox" name="sex" value="1" ${userinfo.sex==1?"checked":""}/>
            </div>

            <label class="col-sm-2 control-label">薪资:</label>
            <div class="col-sm-3">
                <input class="form-control" type="text" name="salary" value="${userinfo.salary}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">所在部门:</label>
            <div class="col-sm-3">
                <input class="form-control" type="password" name=""/>
        </div>

            <div class="clearfix form-actions" style="margin-left: 200px">
                <button class="btn btn-primary" type="button" onclick="addUser();">
                    <i class="glyphicon glyphicon-ok"></i>
                    更新
                </button>
                <button class="btn btn-default" type="reset">
                    <i class="glyphicon glyphicon-refresh"></i>
                    重置
                </button>
            </div>
        </div>
    </form>
    </fieldset>
</div>

</body>
</html>

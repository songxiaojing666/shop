<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/2/21
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册账号</title>
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
     //注册账号
     function addUser() {
         var v_userName=$("#userName").val();
         var v_userPassword=hex_md5($("#userPassword").val());
         var v_userRealName=$("#userRealName").val();
         var v_birthday=$("#birthday").val();
         var v_sex=$("input[name='sex']:checked").val();
         var v_salary=$("#salary").val();
         $.ajax({
             url:"<%=request.getContextPath()%>/user/addUser.jhtml",
             type:"post",
             data:{"userName":v_userName,
                  "userPassword":v_userPassword,
                  "userRealName":v_userRealName,
                 "birthday":v_birthday,
                 "sex":v_sex,
                 "salary":v_salary
             },
             success:function(result){
                 console.log(result);
                 if(result.code ==200){
                     location.href="<%=request.getContextPath()%>/user/toQuery.jhtml";
                 }else {
                     bootbox.alert({
                         message: '<span class="glyphicon glyphicon-remove"></span>'+result.msg,
                         size: 'small',
                         title: "提示信息"
                     });
                 }
             }
         })
     }
 </script>
</head>
<body>

</body>
</html>

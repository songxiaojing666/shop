<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/md5.js"></script>

<script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="/js/DataTables/datatables.min.css"/>
<link rel="stylesheet" type="text/css" href="/js/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<!--bootbox-->
<script src="<%=request.getContextPath()%>/js/bootbox/bootbox.min.js"></script>
 <script src="/js/bootstrap/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/DataTables/datatables.min.js"></script>
<script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/dataTables.bootstrap.min.js"></script>

    <script type="text/javascript">
        function loguser() {
            var v_userName = $("#userName").val();
            var v_userPassword = hex_md5($("#userPassword").val());
            var v_imgCode = $("#imgCode").val();
            $.ajax({
                url: "<%=request.getContextPath()%>/user/loguser.jhtml",
                type: "post",
                data: {
                    "userName": v_userName,
                    "userPassword": v_userPassword,
                    "imgCode": v_imgCode
                },
                success: function (result) {
                    console.log(result);
                    if (result.code == 200) {
                        location.href = "<%=request.getContextPath()%>/product/toList.jhtml";
                    } else {
                       // alert(result.msg);
                        bootbox.alert({
                            message: '<span class="glyphicon glyphicon-remove"></span>'+result.msg,
                            size: 'small',
                            title: "提示信息"
                        });

                    }

                }
            })
        }


        //验证码看不清的时候点a标签就会自动换
        function changeCode(){
            //获取时间戳,防止缓存
            var t=new Date();
            document.getElementById("imgCodeInfo").src="<%=request.getContextPath()%>/imgcode?r="+t;
        }

        <%--//注册方法--%>
        <%--function toaddUser() {--%>
            <%--location.href = "<%=request.getContextPath()%>/adduser.jsp";--%>
        <%--}--%>
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <form class="form-horizontal" action="<%=request.getContextPath()%>/brand/addlistbrand.jhtml" method="post">
            <fieldset>
                <div style="width: 100%;background: lightskyblue;text-align: center">
                    <legend>您好哦!请进行登录:</legend>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label">用户名:</label>
                    <div class="col-md-2">
                        <input type="text" id="userName" type="text" class="form-control"/>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-2 control-label">密码:</label>
                    <div class="col-md-2">
                        <input type="password" id="userPassword" type="text" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label">验证码:</label>
                    <div class="col-md-2">
                        <input type="text" id="imgCode" type="text" class="form-control"/>
                    </div>
                    <div class="col-md-2">
                        <img src="<%=request.getContextPath()%>/imgcode"id="imgCodeInfo">
                        <a href="#" onclick="changeCode()">看不清,换一张</a>
                    </div>
                </div>
            </fieldset>
            <div style="text-align: center">
                <input type="button" value="登录" class="btn btn-primary btn-lg active " aria-hidden="true" onclick="loguser();">
                <input type="reset" value="重置" class="btn btn-primary btn-lg active " aria-hidden="true">
                <%--<input type="button" value="注册" class="btn btn-primary btn-lg disabled" aria-hidden="true" onclick="toaddUser()">--%>
            </div>
        </form>
    </div>
</div>
</body>
</html>
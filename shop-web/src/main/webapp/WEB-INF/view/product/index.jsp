<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/jquery-3.3.1.js"></script>
    <link rel="stylesheet" type="text/css" href="/js/DataTables/datatables.min.css"/>
    <link rel="stylesheet" type="text/css" href="/js/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <!--bootbox-->
    <script src="<%=request.getContextPath()%>/js/bootbox/bootbox.min.js"></script>
    <script src="/js/bootstrap/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/DataTables/datatables.min.js"></script>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/dataTables.bootstrap.min.js"></script>

</head>
<body style="background: #2aabd2">
<%--<a href="<%=request.getContextPath()%>/product/toList.jhtml">查询产品信息</a>--%>
<%--<a href="<%=request.getContextPath()%>/brand/toBandList.jhtml">查询品牌信息</a>--%>
<%--<a href="<%=request.getContextPath()%>/user/toQuery.jhtml">查询用户信息</a>--%>
<%--<a href="<%=request.getContextPath()%>/log/toLog.jhtml">查询日志信息</a>--%>

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


<h3>欢迎<font color="red">${user.userName}</font>登陆成功！
    您上次登陆的时间是<font color="red"><f:formatDate value="${user.loginTimeSci}" pattern="yyyy-MM-dd HH:mm:ss" type="date" /></font>
    您今天登陆了<font color="red">${user.frequency}</font>次
</h3>


</body>
</html>
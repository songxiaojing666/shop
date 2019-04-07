<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/jquery-3.3.1.js"></script>
    <link rel="stylesheet" type="text/css" href="/js/DataTables/datatables.min.css"/>
    <link rel="stylesheet" type="text/css" href="/js/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <script type="text/javascript" src="/js/DataTables/datatables.min.js"></script>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/dataTables.bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">

        <form class="form-horizontal" action="<%=request.getContextPath()%>/brand/updatelistbrand.jhtml" method="post">
            <input type="hidden" name="id" value=${brandinfo.id }><br/>
            <fieldset>
                <div style="width: 100%;background: lightskyblue">
                    <legend>品牌修改:</legend>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">品牌名称</label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" name="brandname" value="${brandinfo.brandname}">
                    </div>
                </div>
            </fieldset>
            <div style="text-align: center">
                <input type="submit" value="Submit" class="btn btn-primary btn-lg active " aria-hidden="true">
                <input type="reset" value="Reset" class="btn btn-primary btn-lg active " aria-hidden="true">
            </div>
        </form>
    </div>
</div>




</body>
</html>
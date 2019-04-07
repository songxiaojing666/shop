<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>回显</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/myjs/common.js"></script>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- 通过用js的方法写动态下拉框的ajax直接引用 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/myjs/brand.js"></script>
    <!--DataTables和bootstrap-->
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/jquery-3.3.1.js"></script>
    <link href="/js/DataTables/DataTables-1.10.18/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="/js/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/js/DataTables/DataTables-1.10.18/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/dataTables.bootstrap.min.js"></script>

    <script type="text/javascript">
        $(function () {
            initbrandList("brandidid", "${productinfo.brand.id}");
        });
        //删除服务器上的老图片
        function deleteoldImage(obj,imageId) {
            //删除图片再刷新还有图片
            $(obj).parents("div[data-oldImage='oldImage']").remove();
            //每次删除的图片ID放到文本框中
            $("#ids").val($("#ids").val()+","+imageId);
        }

        //点+添加行
        function addRow() {
            $("div[name='childsImages']").last().after("<div class='form-group' name='childsImages'>" +
                "<label class='col-md-2 control-label'>商品子图</label>" +
                "<div class='col-md-4'>" +
                "<div style='float: left'>" +
                "<input type='file' name='childsImages'>" +
                "</div>" +
                "<div style='float: left'>" +
                "<input type='button' value='-' onclick='removeRow(this);'>" +
                "</div>" +
                "</div>" +
                "</div>");

        }
        //点击-移除行
        function removeRow(obj){
            $(obj).parents("div[name='childsImages']").remove();
        }
    </script>
</head>
<body>
<div class="col-sm-10" style="margin-top:-20px;margin-left:-30px">
    <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/product/updateproduct.jhtml" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${productinfo.id}"/>
        <legend></legend>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="ds_host">商品名称:</label>
            <div class="col-sm-4">
                <input class="form-control" id="ds_host" type="text" name="productName" value="${productinfo.productName}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="ds_name">商品价格:</label>
            <div class="col-sm-4">
                <input class="form-control" id="ds_name" type="text" name="productPrice" value="${productinfo.productPrice}"/>
            </div>
        </div>

        <div class="form-group">
            <%--下拉框--%>
            <div class="form-group">
                <label  class="col-sm-2 control-label">商品品牌:</label>
                <div class="col-sm-4">
                    <!-- 样式1 -->
                    <select class="form-control" name="brand.id" id="brandidid">
                        <option value="-1">请选择</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
                <div  class="row">
                    <label class="col-sm-2 control-label">商品图片</label>
                    <div class="col-sm-4 col-md-2">
                        <div class="thumbnail">
                            <img src="<%=request.getContextPath()%>${productinfo.image}" height="50px">
                            <input type="hidden" name="image" value="${productinfo.image}"></td>
                        </div>
                        <!--重新更新图片-->
                        <input type="file" name="shopFile"/>
                    </div>
                </div>
        </div>

        <%--缩略图--%>
        <div class="form-group">
        <div class="row">
            <label class="col-sm-2 control-label">老图片:</label>
            <c:forEach items="${productImages}" var="list">
            <div class="col-sm-4 col-md-2" data-oldImage="oldImage">
                <div class="thumbnail" style="text-align: center">
                    <img width="150px" height="150px" src="<%=request.getContextPath()%>${list.imagePath}">
                    <input type="button" value="删除" onclick="deleteoldImage(this,'${list.id}')"/>
                </div>
            </div>
        </c:forEach>
            <input type="text" id="ids" name="ids">
        </div>



        <div class="form-group" name="childsImages">
            <label class="col-md-2 control-label">商品子图</label>
            <div class="col-md-4">
                <div style="float: left">
                    <!--和单个上传一样一个name名-->
                    <input type="file" name="childsImages"/>
                </div>
                <div style="float: left">
                    <!--【一个+按钮给一个触发事件】-->
                    <input type="button" value="+" onclick="addRow();"/>
                </div>
            </div>
        </div>

</div>


        <div class="col-sm-4 tips"></div>

        <div class="clearfix form-actions" style="text-align:center">

            <button class="btn btn-primary" type="submit">
                <i class="glyphicon glyphicon-ok"></i>
                更新
            </button>
            <button class="btn btn-default" type="reset">
                <i class="glyphicon glyphicon-refresh"></i>
                重置
            </button>

        </div>
    </form>
</div>
</body>
</html>
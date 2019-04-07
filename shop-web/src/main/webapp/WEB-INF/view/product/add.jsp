<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjs/common.js"></script>
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
 $(function(){
	 initbrandList("brandid");

})
    //动态添加多张子图
    function addImages() {
        //给多张图片文件域tr行设置一个唯一标识,找到当前行
        //jquery方法【after()在每个匹配的元素之后插入内容。last()获取最后个元素
        $("tr[name='childsImages']").last().after('<tr name="childsImages"><td>商品子图:</td><td><input type="file" name="childsImages"/><input type="button" value="-" onclick="removeRow(this);"/></td></tr>');
    }
   //一个-点击按钮【不选择多个图片取消掉选择图片的文本框】
   function removeRow(obj) {
        //jquery方法【取得一个包含着所有匹配元素的祖先元素的元素集合（不包含根元素）。
        // 可以通过一个可选的表达式进行筛选。
     $(obj).parents("tr").remove();
 }
</script>
</head>
<body>
<!--/product/add.jhtml-->
<div class="col-sm-10" style="margin-top:-20px;margin-left:-30px">
    <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/product/add.jhtml"  method="post" enctype="multipart/form-data">
        <legend></legend>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="ds_host">商品名称:</label>
            <div class="col-sm-4">
                <input class="form-control" id="ds_host" type="text" name="productName"/>
            </div>

            <label class="col-sm-2 control-label" for="ds_name">商品价格:</label>
            <div class="col-sm-4">
                <input class="form-control" id="ds_name" type="text" name="productPrice"/>
            </div>
        </div>

        <div class="form-group">
            <%--下拉框--%>
            <div class="form-group">
                <label  class="col-sm-2 control-label">商品品牌:</label>
                <div class="col-sm-4">
                    <!-- 样式1 -->
                    <select class="form-control" name="brand.id" id="brandid">
                        <option value="-1">请选择</option>
                    </select>
                </div>
                <label class="col-sm-2 control-label">商品图片:</label>
                <div class="col-sm-4">
                    <input type="file" name="shopFile"/>
                </div>
                <div class="col-sm-4 tips"></div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">商品子图</label>
            <div class="col-sm-3">
                <table cellpadding="0px" cellspacing="0px" border="0px">
                    <tr name="childsImages">
                        <td> <input type="file" name="childsImages"/></td>
                        <!--【一个+按钮给一个触发事件】-->
                        <td><input type="button" value="+" onclick="addImages();"/></td>
                    </tr>
                </table>
            </div>
        </div>

        <div class="col-sm-4 tips"></div>

<div class="clearfix form-actions" style="text-align:center">

            <button class="btn btn-primary" type="submit">
                <i class="glyphicon glyphicon-ok"></i>
                添加
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
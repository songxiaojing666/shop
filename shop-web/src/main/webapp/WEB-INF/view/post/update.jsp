<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员修改</title>
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




</head>
<body>
<div class="container-fluid">
    <div class="row">

        <form class="form-horizontal" method="post" action="/post/updatepost.jhtml">
            <fieldset>
                <div style="width: 100%;background: lightskyblue">
                    <legend>会员修改</legend>
                </div>


                <div class="form-group">
                    <label class="col-md-2 control-label">用户名:</label>
                    <div class="col-md-4">
                        <input type="text" name="userName" value="${post.userName}" class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label">邮箱号：</label>
                    <div class="col-md-4">
                        <input type="text" name="email" value="${post.email}" class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label">生日：</label>
                    <div class="col-md-4">
                        <input type="text" name="birthday"  value="<f:formatDate value="${post.birthday}"/>" class="form_datetime inputdate" placeholder="生日">
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-2 control-label">地址：</label>
                    <div class="col-md-4">
                        <input type="text" name="address" id="areaName" value="${post.address}" class="form-control inputdate">
                    </div>
                </div>

                <input type="hidden" id="areaIds" name="areaIds"/>

                <div class="form-group" id="areaSelect">
                    <label class="col-md-2 control-label">
                        <button type="button" class="btn btn-primary btn-lg active" onclick="initArea(0)"><span
                                class="glyphicon glyphicon-ok" aria-hidden="true"></span>编辑
                        </button>
                    </label>
                </div>

            </fieldset>

            <div style="text-align: center">
                <input type="submit" class="btn btn-primary btn-lg active" value="修改">
                </input>
                <button type="button" class="btn btn-default btn-lg active"><span
                        class="glyphicon glyphicon-refresh" aria-hidden="true"></span>重置
                </button>

            </div>
            <input type="hidden" name="id" value="${post.id}">
        </form>
    </div>
</div>





<script>
    $(function () {
        initDateTime();//初始化时间
    })

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
    /**
     * 初始化地区
     */
    function initArea(pid,obj){
        //禁用编辑地区按钮
        $("#editAreaButton").attr("disabled",true);
        $.ajax({
            url:"<%=request.getContextPath()%>/post/findAreaList.jhtml",
            type: "post",
            data:{"pid":pid},
            success:function(res){
                var v_item = res.data.data;
                if(obj){
                    //删除父级元素所有的同级元素
                    $(obj).parent().nextAll().remove();
                    var v_areaIds = "";
                    var v_areaName = "";
                    $("select[name='areaId']").each(function () {
                        if(this.value!=-1){
                            v_areaIds += "," + this.value;
                            v_areaName += this.options[this.selectedIndex].text;
                        }
                    });
                    $("#areaName").val(v_areaName);
                    $("#areaIds").val(v_areaIds.substring(1));
                }
                if(v_item.length==0){
                    //如果返回的数据是0条的话就直接返回 不用再拼接
                    return;
                }

                if(res.code==200){

                    var areaSelectDiv="<div class=\"col-md-2\">\n" +
                        "<select class=\"form-control\" name=\"areaId\" onchange='initArea(this.value,this)'>\n" +
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
</script>
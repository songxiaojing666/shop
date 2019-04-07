<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/3/1
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--DataTables-->
    <link href="/js/DataTables/DataTables-1.10.18/css/jquery.dataTables.min.css" rel="stylesheet">
    <!--bootstrap-->
    <link href="/js/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/js/DataTables/DataTables-1.10.18/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/js/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet" media="screen">
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
    <script src="/js/bootstrap/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/DataTables/DataTables-1.10.18/js/dataTables.bootstrap.min.js"></script>
    <!--bootbox-->
    <script src="<%=request.getContextPath()%>/js/bootbox/bootbox.min.js"></script>
    <!-- 通过用js的方法写动态下拉框的ajax直接引用 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/myjs/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/myjs/brand.js"></script>
    <!--日期插件-->
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker-master/moment-with-locales.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">

        $(function(){
            initLogTable();
            initBindEvent();
            initDateTime();
        })
        function initDateTime() {
            $(".form_datetime").datetimepicker({
                format: "yyyy-mm-dd hh:mm:00",
                autoclose: true,
                todayBtn: true,
                pickerPosition: "bottom-left",
                clearBtn:true

            });
        }

        var v_ids = [];
        function initBindEvent() {
            $("#logTable tbody").on("click","tr",function () {

                var v_checkbox = $(this).find("input[type='checkbox']")[0];
                //如果复选框被选中，则取消背景颜色，取消选中状态，取消对应的id
                if(v_checkbox.checked){
                    /*不被选中状态*/
                    v_checkbox.checked=false;
                    $(this).css("background","");
                    delCheckbox(v_checkbox.value);
                    /*  console.log(v_ids);*/
                }else{

                    /*被选中状态*/
                    v_checkbox.checked=true;
                    //如果没有被选中，则给他赋颜色，添加id到数组中
                    $(this).css("background","#ccc");
                    //把被选中的复选框添加id到数组中
                    v_ids.push(v_checkbox.value);
                    /*console.log(v_ids);*/
                }
                /* $(this).css("background","red");*/
            })
        }

        function delCheckbox(id){
            for (var i = v_ids.length-1; i>=0;i--){
                if (v_ids[i] == id){
                    v_ids.splice(i,1);
                    break;
                }
            }
        }

        function isExist(id) {
            for (var i = 0;i<v_ids.length;i++){
                if(v_ids[i] == id){
                    return true;
                }
            }
        }


        var logTable;
        function initLogTable(){
            logTable =  $('#logTable').DataTable( {
                "processing": true,
                "serverSide": true,
                "order":[],
                // 是否允许检索
                "searching": false,

                "ajax": {
                    "url":"<%=request.getContextPath()%>/log/findlog.jhtml",
                    "type": "POST"
                },

                "drawCallback":function () {
                    //获取表格里tbody里的所有tr里的复选框的值
                    $("#logTable tbody tr input[type='checkbox']").each(function () {
                        var v_id = $(this).val();
                        /*如果这个v_id值存在，则返回上一页的时候，颜色会回显*/
                        if(isExist(v_id)){
                            $(this).parents("tr").css("background","#ccc");
                            this.checked = true;
                        }
                        console.log(this);
                    })
                },

                //汉化字体
                "language": {
                    "url": "<%=request.getContextPath()%>/js/DataTables/Chinese.json"
                },

                /*每页显示的条数*/
                // 页数选择下拉框内容
                "lengthMenu": [5, 10, 15],

                /*传的数据*/
                "columns": [

                    {
                        /*选中复选框*/
                        "data":"id",
                        "render":function (d,x,r,z) {
                            return "<input type='checkbox' value='"+d+"'/>";
                        }
                    },
                    { "data": "id" },
                    { "data": "userName" },
                    {"data":"info"},
                    {"data":"status",
                        "render": function (data, type, row, meta) {
                           return data==0?"失败":"成功";
                        }

                    },
                    {"data":"createTime",
                        "render": function (data, type, row, meta) {
                            //时间格式化
                            var date = new Date(data);
                            return date.toLocaleString();
                        }
                    },
                    {"data":"executeTime"},
                    {"data":"usercontent"}
                ]
            } );
        }
        /**
         * 条件查询传递参数
         */
        function search() {
            var v_userName=$("#userName").val();
            var v_status=$("[name='status']:checked").val();
            var v_mincreateTime=$("#mincreateTime").val();
            var v_maxcreateTime=$("#maxcreateTime").val();

            var v_minexecuteTime=$("#minexecuteTime").val();
            var v_maxexecuteTime=$("#maxexecuteTime").val();

            var param={};
            param.userName= v_userName;
            param.status=v_status;
            param.mincreateTime=v_mincreateTime;
            param.maxcreateTime=v_maxcreateTime;
            param.minexecuteTime=v_minexecuteTime;
            param.maxexecuteTime= v_maxexecuteTime;
            logTable.settings()[0].ajax.data=param;
            //重新加载
            logTable.ajax.reload();
        }
    </script>
</head>
<body>
<div class="container-fluid" style="padding-left: 0px;">
    <div class="row">
        <div>
            <div class="container-fluid">
                <div class="row">

                    <form class="form-horizontal" id="brandFrom" method="post">
                        <fieldset>
                            <div style="width: 100%;background: lightskyblue">
                                <legend>日志条件查询</legend>
                            </div>
                            <div class="form-group">
                                <label for="userName" class="col-md-2 control-label">用户名:</label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" id="userName" placeholder="text">
                                </div>

                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">状态</label>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="radio" value="0" name="status">失败
                                        <input type="radio" value="1" name="status">成功
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">创建时间</label>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="text" class="form_datetime input-sm form-control" id="mincreateTime"/>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span>
                                        </span>
                                        <input type="text" class="form_datetime input-sm form-control" id="maxcreateTime"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">耗费时间</label>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="text" class="input-sm form-control" id="minexecuteTime"/>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span>
                                        </span>
                                        <input type="text" class="input-sm form-control" id="maxexecuteTime"/>
                                    </div>
                                </div>
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

        </div>
    </div>

<div class="panel panel-primary">
    <div class="panel-heading">日志列表</div>
    <table class="table table-striped table-bordered"  id="logTable"  style="width: 100%;" >

        <thead>
        <tr>
            <th>选择</th>
            <th>编号</th>
            <th>用户名</th>
            <th>操作内容</th>
            <th>状态</th>
            <th>操作时间</th>
            <th>执行时间</th>
            <th>操作内容</th>


        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>选择</th>
            <th>编号</th>
            <th>用户名</th>
            <th>操作内容</th>
            <th>状态</th>
            <th>操作时间</th>
            <th>执行时间</th>
            <th>操作内容</th>

        </tr>
        </tfoot>
    </table>
</div>
</div>

</body>
</html>

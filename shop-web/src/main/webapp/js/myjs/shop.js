//日期input框初始化
function initDate() {
    $(".inputdate").datetimepicker({//选择年月日
        format: 'yyyy-mm-dd',
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
    $(".inputdateTime").datetimepicker({//选择年月日
        format: 'yyyy-mm-dd HH:mm:ss',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,//显示‘今日’按钮
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 1,  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
        clearBtn: true,//清除文本框按钮
        forceParse: 0
    });
}
//地区展示
function initArea(pid,obj) {
    $.ajax({
        url: "/post/findAreaList.jhtml",
        data: {
            "pid": pid
        },
        dataType: "jsonp",
        success: function (data) {
            //console.log(data.data.length)
            if (data.code == 200) {

                if (obj) {
                    //删除当前元素后的同级元素
                    $(obj).parent().nextAll().remove();

                }

                if (data.data.length==0){
                    return;
                }


                //头
                var v_selectHtml = "  <div class=\"col-md-6\">\n" +
                    "                        <select class=\"form-control input-lg\" name='areaSelect' onchange='initArea(this.value,this)'><option value='-1'>==请选择==</option>"

                //身体
                for (var i = 0; i < data.data.length; i++) {
                    //取值从下标为0开始取值
                    var v_item = data.data[i];
                    //console.log(data.data[i])
                    //使用拼接的方式给下拉框赋值
                    v_selectHtml += "<option value='" + v_item.id + "' areaName='"+v_item.name+"'>" + v_item.name + "</option>"
                }

                //结尾
                v_selectHtml += "        </select>\n" +
                    "                    </div>"
                $("#areaDiv").append(v_selectHtml);
            }
        }

    })
}
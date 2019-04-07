//动态下拉框的ajax
function initbrandList(eleId,brandid){
	$.ajax({
		url:contextPach+"/brand/list.jhtml",
		type:"post",
		success:function(result){
			console.log(result);
			if(result.code==200){
				var v_arrBrand=result.data;
				for (var i = 0; i < v_arrBrand.length; i++) {
					//向指定元素追加数据
					$("#"+eleId).append("<option value='"+v_arrBrand[i].id+"'>"+v_arrBrand[i].brandname+"</option>");
				}
				if(brandid){
					//回显
					$("#"+eleId).val(brandid);
				}

			}
		}
	})
}




//动态下拉框的ajax
// function initbrandList(eleId,brandid){
//     $.ajax({
//         url:contextPach+"/brand/list.jhtml",
//         type:"post",
//         success:function(result){
//             //alert(9);
//             //console.log(result);
//             if(result.code==200){
//                 var v_arrBrand=result.data;
//                 for (var i = 0; i < v_arrBrand.length; i++) {
//                     //向指定元素追加数据
//                     $("select[name='"+eleId+"']").append("<option value='"+v_arrBrand[i].id+"'>"+v_arrBrand[i].brandname+"</option>");
//                 }
//                 // if(brandid){
//                 // 	//回显
//                 // 	$("#"+eleId).val(brandid);
//                 // }
//
//             }
//         }
//     })
// }

var pageii;

/** 使用新版layui,定义layer和form模块 */
//var layer = layui.layer, form = layui.form;
var layer, form;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	form = layui.form();
});

$(function(){
	
	$("#cancelBtn").on('click',function(){
		window.parent.layer.close(parent.pageii);
	});
	
//	$("#checkSpecGroupAll").on('click', function(){
//		var _this = $(this);
//		if(_this.is(":checked")){
//			$("#dataList tbody tr :checkbox").each(function(){
//				$(this).attr("checked",true);
//			});
//		}else{
//			$("#dataList tbody tr :input[type='checkbox']:checked").each(function(){
//				$(this).attr("checked",false);
//			});
//		}
//	});
	
	$("#confirmBtn").on('click',function(){
		var _checked = $("#dataList tbody :input[type='radio']:checked");
		if(_checked.length != 1){
			layer.alert("请选择一个规格组", {icon : 8});
			return;
		}
		var _groupId = _checked.val();
		var _groupName = _checked.parent().next().text(), _groupAlias = _checked.parent().next().next().text();
		var _html = "<tr><td style='display:none;'>" + _groupId + "</td><td class='text-center'>"
					+ _groupName + "</td><td class='text-center'>"
					+ _groupAlias + "</td><td>"
					+ "<input class='form-control' type='text'></td><td class='text-center'>"
					+ "<button type='button' class='btn btn-danger delSpecGroup'>删除</button>"
					+ "</td></tr>";
		parent.$("#associateSpecGroupDataList tbody").append(_html);
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭   
		//parent.layer.closeAll('iframe');
	});
	
	
});
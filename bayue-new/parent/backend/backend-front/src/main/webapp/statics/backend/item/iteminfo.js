var pageii;

/** 使用新版layui,定义layer和form模块 */
// var layer = layui.layer, form = layui.form;
var layer, form;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	form = layui.form();
});

$(function() {
	
	$(".select2").select2();
	$(".select2").css("margin-left","1px");
	
	$("#addItemInfo").on('click',function(){
		pageii = layer.open({
			type : 2,
			title : '商品spu添加',
			offset: '5%',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fixed : false,
			scrollbar : false,
			area : [ '600px', '500px' ],
			content : domain + '/item/itemInfo/addItemInfo.htm',
		});
	});
	
	$(".editBtn").on('click',function(){
		var id = $(this).attr("param");
		pageii = layer.open({
			type : 2,
			title : '商品spu编辑',
			offset: '5%',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : [ '600px', '500px' ],
			content : domain + '/item/itemInfo/edit.htm?id='+id,
		});
	});
	
	// 根据选定的一级类别联动二级类别
	$("#largeId").change(this, function(v){
		$.post(domain+'/basedata/category/linkageAjaxJson',{"id":v.val},function(result){
			var _smallIdSelector = $("#smallId");
			if(!result){
				_smallIdSelector.empty().append("<option value=''>--请选择二级分类--</option>");
				return;
			}
			var res = JSON.parse(result);
			if(res){
				$("#select2-chosen-2").text("--请选择二级分类--").val("");
				_smallIdSelector.empty().append("<option value=''>--请选择二级分类--</option>");
			}
			$.each(res,function(i,v1){
//				$("#smallCateId").append("<option value='" + v1.id + "'>" + v1.name + "</option>");
				_smallIdSelector.append($("<option>").val(v1.id).text(v1.name));
			});
		},'text');
	});
	
	$("#saveBtn").on('click', function(){
		$.ajax({
			url : 'save',
			dataType : 'text',
			data : $('#itemInfoAddForm').serialize(),
			type : "post",
			cache : false,
			error : function(request){
				alert("Server Connection Failure...");
			},
			success : function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, 1, function() {
						parent.window.location.reload();
		            	parent.layer.close(parent.pageii);
					});
				} else {// 失败
					layer.alert(data.message, 8);
				}
			}
		});
	});
	
	$("#updateBtn").on('click',function(){
		$.ajax({
			url : 'update',
			dataType : 'text',
			data : $('#itemInfoEditForm').serialize(),
			type : "post",
			cache : false,
			error : function(request){
				alert("Server Connection Failure...");
			},
			success : function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, 1, function() {
						parent.window.location.reload();
		            	parent.layer.close(parent.pageii);
					});
				} else {// 失败
					layer.alert(data.message, 8);
				}
			}
		});
	});
	
});

/**
 * 取消按钮 关闭当前弹窗
 */
function cancel(){
	window.parent.layer.close(parent.pageii);
}
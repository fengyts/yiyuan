var pageii;
$(document).ready(function(){
	
	// 取消按钮 关闭当前弹窗
	$("#cancelbtn").on('click',function(){
		window.parent.layer.close(parent.pageii);
	});
	
	$("#specGroupAddbtn").on('click',function(){
		pageii = $.layer({
			type : 2,
			title : '规格组管理-->新增',
			border: [3, 0.3, '#000'],
			shade: [0.3, '#000'],
			shadeClose : true,
			maxmin : true,
			fix : false,
			area :['800px', '600px'],
			iframe : {
				src : domain + '/basedata/specGroup/add.htm'
			}
		});
	});
	
	$(".specGroupEditbtn").on('click',function(){
		var id = $(this).attr("param"); 
		pageii = $.layer({
			type : 2,
			title : '规格组管理-->编辑',
			border: [3, 0.3, '#000'],
			shade: [0.3, '#000'],
			shadeClose : true,
			maxmin : true,
			fix : false,
			area :['800px', '600px'],
			iframe: {
				src : domain + '/basedata/specGroup/edit.htm?id=' + id
			}
		});
	});
	
	$("#selectSpec").on('click',function(){
		pageii = $.layer({
			type : 2,
			title : '规格组新增-->选择规格',
			border: [3, 0.3, '#000'],
			shade: [0.3, '#000'],
			shadeClose : true,
			maxmin : true,
			fix : false,
			area : ['550px','450px'],
			iframe : {
				src : domain + '/basedata/specGroup/listSpec.htm'
			}
		});
	});
	
	$("#specList").on('click',".unboundSpec",function(){
		$(this).parent().parent().remove();
	});
	
	$("#specGroupSaveBtn").on('click',function(){
		var _specIds = $("input[name='specId']");
		if(_specIds.length < 1){
			alert("必须选中规格");
			return;
		}
		
		$.ajax({
			url : 'save',
			dataType : 'text',
			data : $('#specGroupAddForm').serialize(),
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
	
	$("#specGroupUpdateBtn").on('click',function(){
		var specGroupId = $("#specGroupId").val();
		var specData = getSpecData(specGroupId);
		var specGroupData = $('#specGroupEditForm').serialize();
		specGroupData += "&specs="+specData;
		
		$.ajax({
			url : 'update',
			dataType : 'text',
			data : specGroupData,
			//contentType : 'application/json;charset=utf-8',
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

function getSpecData(groupId){
	var _trs = $(".specTr");
	if(_trs && _trs.length < 1){
		return;
	}
	var json = {};
	var arr = new Array();
	$.each(_trs,function(i,v){
		var _tds = $(v).children();
		var _specId = _tds.eq(0).text(), _sort = _tds.eq(2).find(":input").val();
		var _spec = {"groupId":groupId,"specId":_specId,"sort":_sort};
		arr.push(_spec);
	});
	return JSON.stringify(arr);
	
}
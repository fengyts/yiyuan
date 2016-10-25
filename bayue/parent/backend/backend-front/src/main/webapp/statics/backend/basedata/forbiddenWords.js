var pageii;
$(function(){
	
	// 取消按钮 关闭当前弹窗
	$("#cancelbtn").on('click',function(){
		window.parent.layer.close(parent.pageii);
	});
	
	$("#forbiddenWordsAddbtn").on('click',function(){
		pageii = $.layer({
			type : 2,
			title : '违禁词管理-->新增',
			shadeClose : true,
			maxmin : true,
			fix : false,
			area: ['400px', 300],
			iframe : {
				src : domain + '/basedata/forbiddenWords/add.htm'
			}
		});
	});
	
	$(".forbiddenWordsEditbtn").on('click',function(){
		var id = $(this).attr("param"); 
		pageii = $.layer({
			type : 2,
			title : '违禁词管理-->编辑',
			shadeClose : true,
			maxmin : true,
			fix : false,
			area :['400px', 300],
			iframe: {
				src : domain + '/basedata/forbiddenWords/edit.htm?id=' + id
			}
		});
	});
	
	//保存新增
	$("#forbiddenWordsSaveBtn").on('click',function(){
		$.ajax({
			url : 'save',
			dataType : 'text',
			data : $('#forbiddenWordsAddForm').serialize(),
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
	
	//保存更新
	$("#forbiddenWordsUpdateBtn").on('click',function(){
		$.ajax({
			url : 'update',
			dataType : 'text',
			data : $('#forbiddenWordsEditForm').serialize(),
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
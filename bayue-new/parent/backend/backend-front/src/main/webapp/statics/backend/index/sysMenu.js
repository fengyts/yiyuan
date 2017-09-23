var pageii;
$(function(){
	
	$(".select2").select2();
	$(".select2").css("margin-left","1px");
	
	// 取消按钮 关闭当前弹窗
	$("#cancelbtn").on('click',function(){
		window.parent.layer.close(parent.pageii);
	});
	
	$("#menuAddBtn").on('click',function(){
		pageii=$.layer({
			type : 2,
			title : '菜单管理-->新增',
			shadeClose : true,
			maxmin : true,
			fix : false,
			area: ['600px', 400],
			iframe : {
				src : domain + '/index/sysMenu/add.htm'
			}
		});
	});
	
	$("#sysMenuSaveBtn").on('click',function(){
		$.ajax({
			url : 'save',
			dataType : 'text',
			data : $('#sysMenuAddForm').serialize(),
			type : "post",
			cache : false,
			success : function(data) {
//				var obj = new com.meitun.backend.form.ResultMessage(data);
				var res = JSON.parse(data);
				if (1 == res.result) {// 成功
					layer.alert(res.message, 1, function() {
//						location.href = 'list.htm';
						parent.window.location.reload();
		            	parent.layer.close(parent.pageii);
					});
				} else {// 失败
					layer.alert(res.message, 8);
				}
			}
		});
	});
	
	$(".editSysMenu").on('click',function(){
		var id = $(this).attr("param");
		pageii=$.layer({
			type : 2,
			title : '菜单管理-->编辑',
			shadeClose : true,
			maxmin : true,
			fix : false,
			area: ['600px', 400],
			iframe : {
				src : domain + '/index/sysMenu/edit.htm?id='+id
			}
		});
	});
	
	$("#sysMenuUpdateBtn").on('click',function(){
		$.ajax({
			url : 'update',
			dataType : 'text',
			data : $('#sysMenuEditForm').serialize(),
			type : "post",
			cache : false,
			success : function(data) {
//				var obj = new com.meitun.backend.form.ResultMessage(data);
				var res = JSON.parse(data);
				if (1 == res.result) {// 成功
					layer.alert(res.message, 1, function() {
//						location.href = 'list.htm';
						parent.window.location.reload();
		            	parent.layer.close(parent.pageii);
					});
				} else {// 失败
					layer.alert(res.message, 8);
				}
			}
		});
	});
	
});
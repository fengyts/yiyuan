var pageii;

/** 使用新版layui,定义layer和form模块 */
var layer, form;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	form = layui.form();
});

$(function() {
	
	
	$("#btnCancel").on('click',function(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭 
	});

	$("#sysRoleAddbtn").on('click', function() {
		layer.open({
			type : 2,
			title : '系统角色添加',
//			shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : ['600px','400px'],
			content :domain + '/sys/sysRole/add.htm',
		});
	});
	
	$("#sysRoleEditbtn").on('click',function(){
		layer.open({
			type : 2,
			title : '系统角色添加',
//			shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : ['600px','400px'],
			content :domain + '/sys/sysRole/edit.htm',
		});
	});
	
	$("#btnSubmit").on('click',function(){
		$.ajax({
			url : 'save',
			dataType : 'text',
			data : $('#sysRoleAddForm').serialize(),
			type : "post",
			cache : false,
			error : function(request){
				alert("Server Connection Failure...");
			},
			success : function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, {icon: 1}, function() {
						parent.window.location.reload();
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭 
					});
				} else {// 失败
					layer.alert(data.message, 8);
				}
			}
		});
	});

});
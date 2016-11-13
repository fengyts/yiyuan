/** 使用新版layui,定义layer和form模块 */
var layer, form;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	form = layui.form();
});

var pageii;

$(function() {
	
	// 加载角色树,list页面不加载
	if($("#isListForm").length < 1){
		initRolesTree();
	}

	$("#btnCancel").on('click', function() {
		var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
		parent.layer.close(index); // 再执行关闭
	});
		
	$(".resetPasswd").on('click',function(){
		layer.alert("亲，功能尚未上线");
	});

	$("#sysUserAddbtn").on('click', function() {
		layer.open({
			type : 2,
			title : '系统用户添加',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : [ '600px', '600px' ],
			content : domain + '/sys/sysUser/add.htm',
		});
	});

	$(".sysUserEditbtn").on('click', function() {
		var id = $(this).attr('param');
		layer.open({
			type : 2,
			title : '系统用户编辑',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : [ '600px', '600px' ],
			content : domain + '/sys/sysUser/edit.htm?userId=' + id,
		});
	});
	
	$("#btnSubmit").on('click',function(){
		var passwd1 = $("#password").val(),passwd2 = $("#pasword2").val();
		if(!passwd1 && !passwd2 && passwd1 != passwd2){
			layer.alert("密码不能为空或者两次输入密码不一样",{icon:8});
			return;
		}
		
		$.ajax({
			url : 'save',
			dataType : 'text',
			data : getData("#sysUserAddForm"),
			type : "post",
			cache : false,
			error : function(request) {
				alert("Server Connection Failure...");
			},
			success : function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, {icon : 1}, function() {
						parent.window.location.reload();
						var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
						parent.layer.close(index); // 再执行关闭
					});
				} else {// 失败
					layer.alert(data.message, {icon : 8});
				}
			}
		});
	});
	
	$("#sysUserUpdate").on('click',function(){
		$.ajax({
			url : 'update',
			dataType : 'text',
			data : getData("#sysUserUpdateForm"),
			type : "post",
			cache : false,
			error : function(request) {
				alert("Server Connection Failure...");
			},
			success : function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, {icon : 1}, function() {
						parent.window.location.reload();
						var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
						parent.layer.close(index); // 再执行关闭
					});
				} else {// 失败
					layer.alert(data.message, {icon : 8});
				}
			}
		});
	});
	

});


function getData(formId){
	var data = $(formId).serialize();
	
	var treeObj = $.fn.zTree.getZTreeObj("sysRoleTree");
	var nodes = treeObj.getCheckedNodes(true);
	
	if (nodes && nodes.length > 0) {
		var ids = "";
		$.each(nodes,function(i,v){
			ids += v.id + ",";
		});
		ids = ids.substring(0, ids.length - 1);
		
		data += "&roleIds=" + ids;
	}
	
	return data;
	
}

function initRolesTree(){
	var setting = {
			check : {
				enable : true,
				nocheckInherit : true,
			},
			view : {
				selectedMulti : false
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : null,
					rootPId : null
				}
			},
		// callback:{
		// 		beforeClick:function(id, node){
		// 			tree.checkNode(node, !node.checked, true, true);
		// 			return false;
		// 		}
		// }
		};
	
	var zNodes = function(){
		var data;
		$.ajax({
			url : domain + "/sys/sysRole/rolesAll",
			dataType : 'json',
			type : "post",
			cache : false,
			async : false, // 必须使用同步才能给变量data赋值
			success : function(result) {
				data = result;
			}
		});
		return data;
	}
	
	// 初始化树结构
	var treeObj = $.fn.zTree.init($("#sysRoleTree"), setting, zNodes());
	
	if($("#hiddenRoleIds").length > 0){
		var roleIds = $("#hiddenRoleIds").val();
		if(!roleIds){return;}
		var strs = roleIds.split(",");
		$.each(strs,function(i,v){
			var node = treeObj.getNodeByParam("id",v,null);
			try{
				treeObj.checkNode(node, true, false);
			}catch(e){}
		});
	}
		
}


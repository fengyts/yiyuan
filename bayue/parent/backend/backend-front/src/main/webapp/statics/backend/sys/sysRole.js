var pageii;

/** 使用新版layui,定义layer和form模块 */
var layer, form;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	form = layui.form();
});

$(function() {

	// 加载菜单树,list页面不加载
	if ($("#isListForm").length < 1) {
		menuTrees();
	}

	$("#btnCancel").on('click', function() {
		var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
		parent.layer.close(index); // 再执行关闭
	});

	$("#sysRoleAddbtn").on('click', function() {
		layer.open({
			type : 2,
			title : '系统角色添加',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : [ '600px', '600px' ],
			content : domain + '/sys/sysRole/add.htm',
		});
	});

	$(".sysRoleEditbtn").on('click', function() {
		var id = $(this).attr('param');
		layer.open({
			type : 2,
			title : '系统角色编辑',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : [ '600px', '600px' ],
			content : domain + '/sys/sysRole/edit.htm?id=' + id,
		});
	});

	/* 保存角色信息 */
	$("#btnSubmit").on('click', function() {
		$.ajax({
			url : 'save',
			dataType : 'text',
			data : getData(),
			type : "post",
			cache : false,
			error : function(request) {
				alert("Server Connection Failure...");
			},
			success : function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, {
						icon : 1
					}, function() {
						parent.window.location.reload();
						var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
						parent.layer.close(index); // 再执行关闭
					});
				} else {// 失败
					layer.alert(data.message, {
						icon : 8
					});
				}
			}
		});
	});

	/* 编辑角色信息 */
	$("#sysRoleUpdate").on('click', function() {
		$.ajax({
			url : 'update',
			dataType : 'text',
			data : $('#sysRoleEditForm').serialize(),
			type : "post",
			cache : false,
			error : function(request) {
				alert("Server Connection Failure...");
			},
			success : function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, {
						icon : 1
					}, function() {
						parent.window.location.reload();
						var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
						parent.layer.close(index); // 再执行关闭
					});
				} else {// 失败
					layer.alert(data.message, 8);
				}
			}
		});
	});

	// $("#btnSubmit").on('click',function(){
	// getData();
	// });

});

function getData() {
	var data = $('#sysRoleAddForm').serialize();

	var treeObj = $.fn.zTree.getZTreeObj("sysMenuTree");
	var nodes = treeObj.getCheckedNodes(true);
	
	if (nodes && nodes.length > 0) {
		var ids = "";
		$.each(nodes, function(i, v) {
			ids += v.id + ",";
		});
		ids = ids.substring(0, ids.length - 1);
		
		//获取编辑时菜单id,并判断是否有改动
		if($("#checkedMenuIds").length > 0){
			var checkedMenuIds = $("#checkedMenuIds").val();
			if(ids == checkedMenuIds){//菜单没变
				return data;
			}
		}
		
		data += "&menuIds=" + ids;
	}
	
	return data;

}

/* 菜单树 */
function menuTrees() {
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
				pIdKey : "parentId",
				rootPId : null
			}
		},
//	 callback:{
//	 		beforeClick:function(id, node){
//	 			tree.checkNode(node, !node.checked, true, true);
//	 			return false;
//	 		}
//	 }
	};

	var zNodes = function() {
		var data;
		$.ajax({
			url : domain + "/index/sysMenu/listAllMenus",
			dataType : 'json',
			type : "post",
			cache : false,
			async : false, // 必须使用同步才能给变量data赋值
			success : function(result) {
				data = result;
			}
		});
		return data;
	};

	// 初始化树结构
	var tree = $.fn.zTree.init($("#sysMenuTree"), setting, zNodes());
	// 默认展开全部节点
	tree.expandAll(true);

	var treeObj = $.fn.zTree.getZTreeObj("sysMenuTree");
	if($("#checkedMenuIds").length > 0){
		var checkedMenuIds = $("#checkedMenuIds").val().split(",");
		console.log(checkedMenuIds);
		if(checkedMenuIds){
			$.each(checkedMenuIds,function(i,v){
				var node = treeObj.getNodesByParam("id", v, null);
				if(node){
					treeObj.checkNode(treeObj.getNodesByParam("id", v, null)[0], true, false);
				}
			});
		}
	}
	
}

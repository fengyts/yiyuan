/** 使用新版layui,定义layer和form模块 */
var layer, form;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	form = layui.form();
});

var pageii;

$(function() {
	
	initRolesTree();

	$("#btnCancel").on('click', function() {
		var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
		parent.layer.close(index); // 再执行关闭
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
			content : domain + '/sys/sysUser/edit.htm?id=' + id,
		});
	});

});


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
	
}


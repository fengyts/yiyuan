var pageii;

/** 使用新版layui,定义layer和form模块 */
var layer, form;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	form = layui.form();
});

$(function() {

	$("#sysRoleAddbtn").on('click', function() {
		layer.open({
			type : 2,
			title : '系统角色添加',
//			shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			area : ['600px','500px'],
			content :domain + '/sys/sysRole/add.htm'
		});
	});

});
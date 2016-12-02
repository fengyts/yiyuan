var pageii;

/** 使用新版layui,定义layer和form模块 */
// var layer = layui.layer, form = layui.form;
var layer, form;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	form = layui.form();
});

$(function() {
	$("#addItemInfo").on('click',function(){
		pageii = layer.open({
			type : 2,
			title : '商品spu添加',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : [ '1000px', '600px' ],
			content : domain + '/item/itemInfo/addItemInfo.htm',
		});
	});
	
});
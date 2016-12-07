var pageii;

/** 使用新版layui,定义layer和form模块 */
//var layer = layui.layer, form = layui.form;
var layer, form;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	form = layui.form();
});

$(function(){
	
	$("#addItemDetail").on('click',function(){
		addTab("item_detail_add","商品新增","/item/itemDetail/add.htm");
	});
	
	/**
	 * 取消按钮 关闭当前iframe
	 */
	$("#cancelBtn").on('click',function(){
		parent.window.closeTab("item_detail_add");
	});
	
	/**
	 * 取消按钮 关闭当前弹窗
	 */
	$("#cancelLayerBtn").on('click',function(){
		parent.window.layer.close(parent.pageii);
	});
	
	$("#selectSPU").on('click',function(){
		pageii = layer.open({
			type : 2,
			title : '商品spu列表',
			offset: '5%',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fixed : false,
			scrollbar : false,
			area : [ '1000px', '600px' ],
			content : domain + '/item/itemInfo/initSpuList.htm',
		});
	});
	
	$("#confirmBtn").on('click',function(){
		
	});
	
	
});



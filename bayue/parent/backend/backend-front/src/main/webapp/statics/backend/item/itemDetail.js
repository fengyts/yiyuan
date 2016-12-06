var pageii,tab;
$(function(){
	
	$("#addItemDetail").on('click',function(){
		tab = addTab("item_detail_add","商品新增","/item/itemDetail/add.htm");
	});
	
	
});

/**
 * 取消按钮 关闭当前iframe
 */
function cancel(){
	parent.window.closeTab("item_detail_add");
}


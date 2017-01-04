var pageii;
$(function(){
	
	$("#associateTopicItem").on('click', function(){
		pageii = layer.open({
			type : 2,
			title : '专题添加商品',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : [ '800px', '550px' ],
			content : domain + '/topic/initItemDetailList.htm',
		});
		
	});
	
	$("#cancelAssociateTopicItemBtn").on('click', function(){
		parent.layer.close(parent.pageii);
	});
	
});
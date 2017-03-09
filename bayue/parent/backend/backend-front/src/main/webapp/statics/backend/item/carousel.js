var pageii;
$(function(){
	
	$("#carouselAddbtn").on('click',function(){
		pageii = layer.open({
			type : 2,
			title : '商品spu添加',
			offset: '5%',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fixed : false,
			scrollbar: false,
			area : [ '700px', '450px' ],
			content : domain + '/item/carousel/add.htm',
		});
	});
	
	$(".carouselEditbtn").on('click', function(){
		var id = $(this).attr('param');
		pageii = layer.open({
			type : 2,
			title : '商品spu编辑',
			offset: '5%',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : [ '700px', '450px' ],
			content : domain + '/item/carousel/edit.htm?id='+id,
		});
	});
	
	
	$("#saveBtn").on('click', function(){
		$.ajax({
			url : 'save',
			dataType : 'text',
			data : $('#carouselAddForm').serialize(),
			type : "post",
			cache : false,
			error : function(request){
				alert("Server Connection Failure...");
			},
			success : function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, 1, function() {
						parent.window.location.reload();
		            	parent.layer.close(parent.pageii);
					});
				} else {// 失败
					layer.alert(data.message, 8);
				}
			}
		});
	});
	
	$("#updateBtn").on('click',function(){
		$.ajax({
			url : 'update',
			dataType : 'text',
			data : $('#carouselEditForm').serialize(),
			type : "post",
			cache : false,
			error : function(request){
				alert("Server Connection Failure...");
			},
			success : function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, 1, function() {
						parent.window.location.reload();
		            	parent.layer.close(parent.pageii);
					});
				} else {// 失败
					layer.alert(data.message, 8);
				}
			}
		});
	});
	
	
	
	
});

function cancel(){
	window.parent.layer.close(parent.pageii);
}
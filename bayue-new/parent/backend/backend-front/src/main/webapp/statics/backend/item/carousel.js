var pageii;
$(function(){
	
	$("#carouselAddbtn").on('click',function(){
		pageii = layer.open({
			type : 2,
			title : '轮播图添加',
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
			title : '轮播图编辑',
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
		var _o = $.formDataJson("#carouselAddForm",true);
		$.ajaxFileUpload({
			url : 'save.htm',
			dataType : 'json',
			secureuri: false,
			fileElementId: 'picture',
			//data : $('#carouselAddForm').serialize(),
			data : {"data": _o},
			type : "post",
			cache : false,
			error : function(request){
				alert("Server Connection Failure...");
			},
			success : function(res) {
//				var data = JSON.parse(res);
				var data = res;
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
		var _o = $.formDataJson("#carouselEditForm");
		var _imgChanged = $("#imgChanged").val();  // 是否修改了图片
		
		$.ajaxFileUpload({
			url : 'update',
			dataType : 'text',
			secureuri: false,
			fileElementId: 'picture',
//			data : $('#carouselEditForm').serialize(),
			data: { "data" : _o , "imgChanged" : _imgChanged },
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
	
	
	// 选择图片时生成缩略图预览
	$("#picture").change(function(e) {
		$("#imgChanged").val('1');
        var file = e.target.files[0];
        imgview(file);
    });
	
});

function cancel(){
	window.parent.layer.close(parent.pageii);
}

/**
 * 上传图片预览
 * @param file
 */
function imgview(file) {
	if(typeof FileReader == 'undefined'){
		$('#preview').append('不能预览');
		return false;
	}
    var reader = new FileReader();
    reader.onload = function(e) {
        var $img = $('<img width="100px" heigth="100px">').attr("src", e.target.result);
        $('#preview').empty().append($img);
    }
    reader.readAsDataURL(file);
}
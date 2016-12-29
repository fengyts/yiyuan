var pageii;
$(function(){
	
	$("#addTopic").on('click', function(){
		addTab("topic_add","新增专题活动","/topic/add.htm?iframeName=" + window.name);
	});
	
	// 取消按钮
	$("#cancelTabBtn").on('click',function(){
		var type = $(this).attr("param");
		if(type){
			parent.window.closeTab("topic_edit"); // 关闭编辑tab页
		}else{
			parent.window.closeTab("topic_add"); // 关闭新增tab页
		}
	});
	
	$(".editBtn").on('click', function(){
		var topicId = $(this).attr("param");
		addTab("topic_edit","编辑专题活动","/topic/edit.htm?topicId=" + topicId + "&iframeName=" + window.name);
	});
	
	// 选择图片时生成缩略图预览
	$("#image").change(function(e) {
        var file = e.target.files[0];
        imgview(file);
    });
	
	
	$("#saveBtn").on('click',function(){
		var _data = $("#topicAddForm").serializeArray();
		
		var _o = $.formDataJson("#topicAddForm");
		
		$.ajaxFileUpload({
			url: 'save.htm',
			type: 'POST',
			secureuri: false,
			fileElementId: 'image',
			dataType: "json",
			data: { "data" : _o },
			success: function(response) {
				console.log(response);
//				layer.close(_loading);
				if (response.result == 1) {
					layer.alert(response.message, {icon:1}, function(){
						var listIframeName = $("#listIframeName").val();
						parent.window.frames[listIframeName].location.reload();
						parent.window.closeTab("topic_add");
					});
				} else {
					layer.msg(response.message, {icon:0});
				}
			},
			error: function() {
//				layer.close(_loading);
				layer.msg("请求失败", 1);
			}
		});
		
	});
	
});


function preview1(file) {
    var img = new Image(), url = img.src = URL.createObjectURL(file);
    var $img = $(img);
    img.onload = function() {
        URL.revokeObjectURL(url);
        $('#preview').empty().append($img);
    }
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

function getFormData(){}

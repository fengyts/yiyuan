var pageii;
$(function(){
	
	$("#addTopic").on('click', function(){
		addTab("topic_add","新增专题活动","/topic/add.htm?iframeName=" + window.name);
	});
	
	$("#cancelTabBtn").on('click',function(){
		parent.window.closeTab("topic_add");
	});
	
	$("#image").change(function(e) {
        var file = e.target.files[0];
        imgview(file);
    });
	
	
	$("#saveBtn").on('click',function(){
		var _data = $("#topicAddForm").serializeArray();
		console.log(_data);
		var _d = $("#topicAddForm").serialize();
//		console.log($("#topicAddForm").serialize());
		_d = _d.replace(new RegExp("&","g"),",");
		var _arr = _d.split("&");
//		console.log(_arr);
		_d = "{"+_d+"}";
		console.log(_r);
//		return ;
		
		
		$.ajaxFileUpload({
//			url: 'save.htm',
			type: 'POST',
			secureuri: false,
			fileElementId: 'image',
			dataType: "json",
			data: {"name":"abc","id":1},
			success: function(data) {
				layer.close(_loading);
				clearFile();
				if (data.code == 0) {
					layer.msg("上传成功", 1, 1);
					window.location.reload();
				} else {
					layer.msg(data.message);
				}
			},
			error: function() {
				layer.close(_loading);
				clearFile();
				layer.msg("请求失败", 1);
			}
		});
//		$.ajax({
//			url : 'save.htm',
//			dataType : 'text',
//			data : $("#topicAddForm").serialize(),
//			type : "post",
//			cache : false,
//			error : function(request){
//				alert("Server Connection Failure...");
//			},
//			success : function(res) {
//				var data = JSON.parse(res);
//				if (1 == data.result) {// 成功
//					layer.alert(data.message, 1, function() {
//					});
//				} else {// 失败
//					layer.alert(data.message, 8);
//				}
//			}
//		});
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
 

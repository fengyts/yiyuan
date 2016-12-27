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
 

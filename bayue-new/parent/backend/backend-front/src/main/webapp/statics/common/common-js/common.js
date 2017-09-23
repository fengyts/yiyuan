$(function(){
	//表格行，鼠标放上去变色
	/*着色方案1：
	$(".tr:odd").css("background", "#FFFCEA");
	$(".tr:odd").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#FFE1FF");
		}, function(){
			$(this).css("background-color", "#FFFCEA");
		});
	});
	$(".tr:even").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#FFE1FF");
		}, function(){
			$(this).css("background-color", "#fff");
		});
	}); */
	
	
	/*着色方案2
	$(".tr:odd").css("background", "#FFE1FF");
	$(".tr:even").attr("bg", "#FFE1FF");//#EED5D2  #DBDBDB  FFE1FF
	$(".tr:odd").attr("bg", "#ffffff");

	$(".tr").mouseover(function() {
		$(this).css("background", "#FFAEB9");//#FFAEB9 #A6A6A6  #bcd4ec
	}).mouseout(function() {
		var bgcolor = $(this).attr("bg");
		$(this).css("background",bgcolor);
	});*/
	
	
	$(".tr:odd").css("background-color", "#ecf6fc");
	$(".tr:odd").attr("bg", "#ecf6fc");
	$(".tr:even").attr("bg", "#ffffff");
	$(".tr").mouseover(function() {
		$(this).css("background", "#bcd4ec");
	}).mouseout(function() {
		var bgc = $(this).attr("bg");
		$(this).css("background", bgc);
	});
	

//	try {
//		/*ie6,7下拉框美化*/
//	    if ( $.browser.msie ){
//	    	if($.browser.version == '7.0' || $.browser.version == '6.0'){
//	    		$('.select').each(function(i){
//				   $(this).parents('.select_border,.select_containers').width($(this).width()+5); 
//				 });
//	    	}
//	    }
//	} catch (e) {
//		console.log(e.name+":"+e.message);
//	}
    
});

/**
 * 表单重置
 */
function dataReset(formId) {
	var formObj = document.getElementById(formId);
	if (formObj == undefined) {
		return;
	}
	for (var i = 0; i < formObj.elements.length; i++) {
		if (formObj.elements[i].type == "text") {
			formObj.elements[i].value = "";
		} else if (formObj.elements[i].type == "password") {
			formObj.elements[i].value = "";
		} else if (formObj.elements[i].type == "radio") {
			formObj.elements[i].checked = false;
		} else if (formObj.elements[i].type == "checkbox") {
			formObj.elements[i].checked = false;
		} else if (formObj.elements[i].type == "select-one") {
			formObj.elements[i].options[0].selected = true;
		} else if (formObj.elements[i].type == "select-multiple") {
			for (var j = 0; j < formObj.elements[i].options.length; j++) {
				formObj.elements[i].options[j].selected = false;
			}
		} else if (formObj.elements[i].type == "file") {
			var file = formObj.elements[i];
			if (file.outerHTML) {
				file.outerHTML = file.outerHTML;
			} else {
				file.value = ""; // FF(包括3.5)
			}
		} else if (formObj.elements[i].type == "textarea") {
			formObj.elements[i].value = "";
		}
	}
}

/***
 * 取消按钮
 * @param $
 * @returns {Function}
 */
function cancelButton(pageii){
	window.parent.layer.close(pageii);
}

/**
 * 消息提示
 */
//msg:消息提示文字
//icon:提示小图标,可选值 success, error ,warning, info
//time:持续时间,毫秒
(function($) {
	var $msgbox = function(options) {
		var defaults = {
			msg : '操作失败',
			icon : 'clear',
			time : '2000',
			callBack : null
		};
		var settings = jQuery.extend(defaults, options);
		var tipiconclass = "gtl_ico_" + settings.icon;
		$('#ts_Msgbox').remove();
		var box = "<div class=\"ts_msgbox_layer_wrap\" id=\"ts_Msgbox\" style=\"display:none\"><span class=\"ts_msgbox_layer\" style=\"z-index: 10000;\" id=\"mode_tips_v2\"><span class=\""
				+ tipiconclass
				+ "\"></span>"
				+ settings.msg
				+ "<span class=\"gtl_end\"></span></span></div>";
		$("body").append(box);
		$('#ts_Msgbox').fadeIn();
		window.setTimeout(function() {
					$('#ts_Msgbox').fadeOut(function() {
						if (settings.callBack != null
								&& typeof settings.callBack == 'function') {
							settings.callBack();
						}
					});
				}, settings.time);
	}
	$.msgbox = function(options) {
		return new $msgbox(options);
	}
	return $.msgbox;
})(jQuery);

//日期格式化扩展
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}
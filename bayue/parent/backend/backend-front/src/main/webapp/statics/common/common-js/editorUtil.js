$(function(){
	
	// 添加Cookie
	function addCookie(name, value, options) {
		if (arguments.length > 1 && name != null) {
			if (options == null) {
				options = {};
			}
			if (value == null) {
				options.expires = -1;
			}
			if (typeof options.expires == "number") {
				var time = options.expires;
				var expires = options.expires = new Date();
				expires.setTime(expires.getTime() + time * 1000);
			}
			document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path=" + options.path : "") + (options.domain ? "; domain=" + options.domain : ""), (options.secure ? "; secure" : "");
		}
	}

	// 获取Cookie
	function getCookie(name) {
		if (name != null) {
			var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
			return value ? decodeURIComponent(value[1]) : null;
		}
	}

	// 移除Cookie
	function removeCookie(name, options) {
		addCookie(name, null, options);
	}
	var sessionId = $('#sessionId').val();
	
	KindEditor.lang({
		clearcontent : '清空内容',
        //empty : '清空',
	});
	// 富文本编辑器
	KindEditor.ready(function(K) {
		K.DEBUG=true;
		editor = K.create("#kindEditor", {
			height: "300px",
			items: [
					"source","|", "justifyleft", "justifycenter", "justifyright",
					"justifyfull", "insertorderedlist", "insertunorderedlist", 
					"fontsize", "forecolor", "hilitecolor", "bold", "italic", 
					"underline", "strikethrough", "|", "image","multiimage","table",
					"|","link","unlink","|", "fullscreen", "clearhtml", "clearcontent"
				],
			langType: "zh_CN",
			filterMode: false,
//			uploadJson : domain + '/item/uploadItemEditor.htm?sid='+sessionId,
			uploadJson : domain + '/uploadImg/uploadItemEditor.htm',
			imageSizeLimit : "300KB",
			allowFileManager: false,
			afterChange: function() {
				this.sync();
			},
			afterUpload:function (data) { 
				if(data.message != null && data.message !="undefined"){
					alert(data.message);
				}
	        }
		});
		
	});
	
});

KindEditor.plugin('clearcontent', function(K) {
	var editor = this, name = 'clearcontent';
	// 点击图标时执行
	editor.clickToolbar(name, function() {
	//	editor.insertHtml('清空内容');
		editor.html("");
});
});
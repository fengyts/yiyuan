$(document).ready(function() {
	if ($.tools != null) {
		var $tab = $("#tab");
		// Tab效果
		$tab.tabs("table.tabContent, div.tabContent", {
			tabs : "input"
		});
	}

});

/** 打开新标签页 */
function addTab(id, text, tabUrl) {
	var tv = {};
	tv.linkId = id + "_link";
	tv.tabId = id;
	tv.url = tabUrl;
	tv.text = text;
	try {
		window.parent.showTab(tv);
	} catch (e) {
	}
}

function showTab(id, text, url) {
	var date = new Date();
	var tv = {
		linkId : date.getMilliseconds() + "_link",
		tabId : date.getMilliseconds(),
		url : url,
		text : text
	};
	try {
		window.parent.showTab(tv);
	} catch (e) {
	}
}

function showTabTest(id) {
	var date = new Date();
	var tv = {
		url : '/item/detail.htm?detailId=' + id,
		linkid : 'add_detail_' + date.getMilliseconds(),
		tabId : 'add_detail_' + date.getMilliseconds(),
		text : '编辑sku信息'
	};
	parent.window.showTab(tv);
}

// openTab("item_list_add","新增商品","/item/add.htm")


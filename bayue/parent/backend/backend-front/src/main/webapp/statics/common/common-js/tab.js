$(document).ready( function() {
	if ($.tools != null) {
		var $tab = $("#tab");
		// Tab效果
		$tab.tabs("table.tabContent, div.tabContent", {
			tabs: "input"
		});
	}

});

/** 弹出标签页 */
function addTab(id,text,tabUrl){
	var tv = {};
	tv.linkId = id+"_link";
	tv.tabId =  id;
	tv.url = tabUrl;
	tv.text = text;
	try{
		window.parent.showTab(tv);
	} catch(e){
	}
}


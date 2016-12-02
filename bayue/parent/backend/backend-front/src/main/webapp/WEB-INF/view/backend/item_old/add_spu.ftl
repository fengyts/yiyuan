<#include "/common/common.ftl"/> 

<@backend title="商品新增" 
js=[
'/statics/plugin/layer/layer.min.js',
'/statics/plugin/select2/js/select2.js',
'/statics/plugin/select2/js/select2Util.js',
'/statics/plugin/select2/js/select2_locale_zh-CN.js',
'/statics/backend/item/item_info.js'
] 
css=[
'/statics/common/common-css/common.css',
'/statics/common/common-css/style.css',
'/statics/plugin/select2/css/select2.css'
] >
<form id="inputForm" action="" method="post" enctype="multipart/form-data">

	<#include "/backend/item/subpages/add_itemInfo.ftl">
	
	<div class="tc">
		<#--
		<#if item.info.id?exists>
			<input type="button" value="返回" onclick="location.href='list.htm'" 	class="ext_btn m10" />
		<#else>
			<input type="button" value="保存" id="itemInfoSaveBtn" class="ext_btn ext_btn_submit m10" />
			<input type="button" value="返回" onclick="location.href='list.htm'" 	class="ext_btn m10" />	
		</#if>
		-->
		<input type="button" value="保存" id="itemInfoSaveBtn" class="btn btn82 btn_save2" />
		<input type="button" value="取消" id="cancelbtn" class="btn btn82 btn_del closebtn" />
	</div>
	
</form>
</@backend>

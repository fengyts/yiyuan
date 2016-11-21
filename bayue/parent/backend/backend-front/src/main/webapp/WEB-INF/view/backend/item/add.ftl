<#include "/common/common.ftl"/> 

<@backend title="商品新增" 
js=[
'/statics/plugin/layer/layer.min.js',
'/statics/plugin/select2/js/select2.js',
'/statics/plugin/select2/js/select2Util.js',
'/statics/plugin/select2/js/select2_locale_zh-CN.js',
'/statics/common/common-js/jquery.tools.js',
'/statics/plugin/editor/kindeditor-all.js',
'/statics/common/common-js/editorUtil.js', 
'/statics/common/common-js/tab.js',
'/statics/common/common-js/form.js',
'/statics/common/common-js/util.js',
'/statics/common/common-js/sortable.js',
'/statics/common/common-js/jquery.cookie.js',
'/statics/plugin/swfupload/js/fileprogress.js',
'/statics/plugin/swfupload/js/handlers.js',
'/statics/plugin/baidu_webuploader/webuploader.min.js',
'/statics/backend/item/item_copy.js',
'/statics/backend/item/item.js'
] 
css=[
'/statics/common/common-css/common.css',
'/statics/common/common-css/style.css',
'/statics/plugin/select2/css/select2.css',
'/statics/plugin/baidu_webuploader/webuploader.css',
'/statics/backend/item/imgupload.css'
] >
<form id="itemSaveForm" action="save.htm" method="post" enctype="multipart/form-data">
	<#--
	<#include "/backend/item/subpages/add_itemInfo.ftl">
		<div class="tc">
			<#if item.info.id?exists>
				<input type="button" value="返回" onclick="location.href='list.htm'" 	class="ext_btn m10" />
			<#else>
				<input type="button" value="保存" id="itemSaveBtn" class="ext_btn ext_btn_submit m10" />
				<input type="button" value="返回" onclick="location.href='list.htm'" 	class="ext_btn m10" />	
			</#if>
			
		</div>
	-->	
	
	<div id="forms" class="mt10">
        <div class="box">
        <div class="box_border">
         
	      <div class="box_top">
		  		<b class="pl15">商品SPU信息</b><font color="red">(带"*"为必填项)</font>
		  		<#--
				<span style="float: right;">
	                <input value="checkbox" type="checkbox">加入常用功能&nbsp;&nbsp;&nbsp;
		        </span>
		        -->
		  </div>
		  
		  <div class="box_center">
				<table id="spuTable" class="input commContent">
					<tr>
						<td colspan='4'>
							<input type="button" id="addNewSPU" class="ext_btn ext_btn_submit m10" value="新增SPU" paramType='1'/>
						</td>
					</tr>
					<tr id="oldSPUTr">
						<td class="td_right">SPU：</td>
						<td class="" colspan='3'>
							<input type="text" id="spu" name="spu" class="input-text lh25" size="20" maxlength="50" />
							<span id="promptMsg" style="color:red"></span>
						</td>
					</tr>
					<tr id="newSPUTr"></tr>
				</table>
		  </div>
		  
          <#include "/backend/item/add_detail.ftl" /> 
          
	      <div class="tc">
				<#if "${detail.status}" != "2">
					<input type="button" id="inputFormSaveBtn" style="text-align:right;" value="保存" class="ext_btn ext_btn_submit m10">
				</#if>
				<input type="hidden" value="${detailId}" name="detail.id"  id="detailId"/>
				<input type="hidden" name="skuListJson"  id="skuListJson"/>
				<input type="hidden" name="attrListJson"  id="attrListJson"/>
				<input type="hidden" name="attrItemJson"  id="attrItemJson"/>
				
				<input type="hidden" id="formToken" name="formToken"
					<#if formToken?exists>
						<#list formToken?keys as key> 
						<#if key=="${detailId}"> value="${formToken[key].token}"</#if>
						</#list>
					</#if> />
				<input type="button" value="返回" onclick="location.href='list.htm'"	class="ext_btn m10">
	   	  </div>
          
	    </div>
	    
	    </div>
	    
		    
	</div>
	
	
</form>
</@backend>

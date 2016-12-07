<#include "/common/common.ftl"/>

<@backend title="商品spu列表"
js=[
'/statics/plugin/layui-v1.0.2/layui/layui.js',
'/statics/backend/item/itemDetail.js'
]
css=[
'/statics/plugin/layui-v1.0.2/layui/css/layui.css'
]>

<div class="box">
<form class="jqtransform" method="post" id="itemInfoForm" action="${domain}/item/itemInfo/initSpuList.htm">
		<#-- 搜索表单模块 -->
		<div id="search_bar" class="box mt10">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">商品spu列表页面</b>
				</div>
				<div class="box_center pt10 pb10">
					<table class="form_table" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>SPU：</td>
						<td>
				  			<input type="text" id="spu" name="spu" value="${infoDO.spu}" class="input-text lh25" size="20">
						</td>
					</tr>
					</table>
				</div>
				<div class="box_bottom pb5 pt5 pr10 search_bar_btn" style="border-top:1px solid #dadada;">
				    <a href="javascript:void(0);">
				    	<input class="btn btn82 btn_search" onclick="$('#itemInfoForm').submit();" type="button" value="查询" name="button" />
				    </a>
				    <input class="btn btn82 btn_save2" type="button" id="confirmBtn" value="确定">
				    <input class="btn btn82 btn_del" type="button" id="cancelLayerBtn" value="取消"/>
				</div>
			</div>
		</div>
		
		<#-- 数据显示块 -->
		<div id="table" class="mt10">
			<div class="box span10 oh">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="dataList">
			    	<tr>
			    		<th width="2%"></th>
			    		<th width="5%">ID</th>
			    		<th>SPU</th>
			    		<th>商品显示名</th>
			    		<th>大类</th>
			    		<th>小类</th>
			    		<th>单位</th>
			    	</tr>
			    	<#if page.list?default([])?size!=0>
			    	<#list page.list as obj>
			    		<tr class="tr">
			    			<td class="td_center"><input type="radio" class="selectSpu"/></td>
			    			<td class="td_center">${obj.id}</td>
			    			<td class="td_center">${obj.spu}</td>
			    			<td class="td_center">${obj.mainTitle}</td>
			    			<td class="td_center">${obj.largeCateName}</td>
			    			<td class="td_center">${obj.smallCateName}</td>
			    			<td class="td_center">${obj.unitName}</td>
			    		</tr>
			    	</#list>
			    	</#if>
			    </table>
			</div>
			
			<div style="font-size:16px">
				<#if norecoders!=null>${noRecoders}</#if>
			</div>
		</div>
		

    <@pager  pagination=page  formId="itemInfoForm" />
 
</form>
</div>

</@backend>
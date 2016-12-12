<#include "/common/common.ftl"/>
<@backend title="商品spu管理" js=[
'/statics/plugin/layer/layer.min.js',
'/statics/plugin/layui-v1.0.2/layui/layui.js',
'/statics/common/common-js/tab.js',
'/statics/backend/item/itemDetail.js'
] 
css=[
] >

<div class="box">
<form class="jqtransform" method="post" id="itemDetailForm" action="${domain}/item/itemDetail/list.htm">
		<#-- 搜索表单模块 -->
		<div id="search_bar" class="box mt10">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">商品详情列表页面</b>
				</div>
				<div class="box_center pt10 pb10">
					<table class="form_table" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>SPU：</td>
						<td>
				  			<input type="text" id="spu" name="spu" value="${detialDO.spu}" class="input-text lh25" size="20">
						</td>
						<td>PRDID：</td>
						<td>
				  			<input type="text" id="prdid" name="prdid" value="${detialDO.prdid}" class="input-text lh25" size="20">
						</td>
						<td>商品名称:</td>
						<td>
				  			<input type="text" id="mainTitle" name="mainTitle" value="${detialDO.mainTitle}" class="input-text lh25" size="30">
						</td>
						<td>商品状态:</td>
						<td>
							<select class="select" id="status" name="status" style="width:80px;">
								<option value="">--全部--</option>
								<option value="0">未上架</option>
								<option value="1">已上架</option>
								<option value="2">作废</option>
							</select>
						</td>
					</tr>
					</table>
				</div>
				<div class="box_bottom pb5 pt5 pr10 search_bar_btn" style="border-top:1px solid #dadada;">
				    <a href="javascript:void(0);">
				    	<input class="btn btn82 btn_search" onclick="$('#itemDetailForm').submit();" type="button" value="查询" name="button" />
				    </a>
				    <input class="btn btn82 btn_add" type ="button" value="新增" id="addItemDetail" />
				</div>
			</div>
		</div>
		
		<#-- 数据显示块 -->
		<div id="table" class="mt10">
			<div class="box span10 oh">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="dataListDetail">
			    	<tr>
			    		<th width="5%"><input type='checkbox' id="checkAllDetail"/></th>
			    		<th>SPU</th>
			    		<th>PRDID</th>
			    		<th>商品显示名</th>
			    		<th>大类</th>
			    		<th>小类</th>
			    		<th>单位</th>
			    		<th>操作</th>
			    	</tr>
			    	<#if page.list?default([])?size!=0>
			    	<#list page.list as obj>
			    		<tr class="tr">
			    			<td class="td_center">
			    				<input type="checkbox" name="detailId" value="${obj.id}" />
			    			</td>
			    			<td class="td_center">${obj.spu}</td>
			    			<td class="td_center">${obj.prdid}</td>
			    			<td class="td_center">${obj.mainTitle}</td>
			    			<td class="td_center">${obj.largeCateName}</td>
			    			<td class="td_center">${obj.smallCateName}</td>
			    			<td class="td_center">${obj.unitName}</td>
			    			<td class="td_center">
			    				<a href="javascript:void(0);" param="${obj.id}">[编辑]</a>
			    			</td>
			    		</tr>
			    	</#list>
			    	</#if>
			    </table>
			</div>
			
			<div style="font-size:16px">
				<#if noRecords!=null>${noRecords}</#if>
			</div>
		</div>
		

    <@pager  pagination=page  formId="itemDetailForm" />
 
</form>
</div>

</@backend>
<#include "/common/common2.ftl" />
<@backend title="专题商品列表"
js=[
'/statics/common/common-js/tab.js',
'/statics/backend/promotion/topicItem.js'
]
css=[
]
>

<div class="box">
<form class="jqtransform" method="get" id="topicItemListForm" action="${domain}/topic/topicItemList?topicId=${topicId}">
		<input type="hidden" id="topicId" name="topicId" value="${topicId}">
		<#-- 搜索表单模块 -->
		<div id="search_bar" class="box mt10">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">专题商品列表页面</b>
				</div>
				<div class="box_center pt10 pb10">
					<table class="form_table" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>spu：</td>
						<td>
				  			<input type="text" id="spu" name="spu" value="${topicItemDO.spu}" class="input-text lh25" size="20">
						</td>
						<td>prdid：</td>
						<td>
				  			<input type="text" id="prdid" name="prdid" value="${topicItemDO.prdid}" class="input-text lh25" size="20">
						</td>
						<td>商品状态：</td>
						<td>
							<select name="status" class="select">
								<option value='' selected='selected'>全部</option>
								<option value='0' <#if topicItemDO.status==0>selected='selected'</#if>>无效</option>
								<option value='1' <#if topicItemDO.status==1>selected='selected'</#if>>有效</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>商品名称：</td>
						<td colspan="3">
							<input type="text" id="mainTitle" name="mainTitle" value="${topicItemDO.mainTitle}" class="input-text lh25" size="55">
						</td>
						<td>是否测试商品：</td>
						<td>
							<select name="isTest" class="select">
								<option value='' selected='selected'>全部</option>
								<option value='0' <#if topicItemDO.isTest==0>selected='selected'</#if>>否</option>
								<option value='1' <#if topicItemDO.isTest==1>selected='selected'</#if>>是</option>
							</select>
						</td>
					</tr>
					</table>
				</div>
				<div class="box_bottom pb5 pt5 pr10 search_bar_btn" style="padding-left:5px;border-top:1px solid #dadada;">
				    <a href="javascript:void(0);">
				    	<input class="ext_btn ext_btn_success" type="button" value="查询" onclick="$('#topicItemListForm').submit();" name="button" id="queryTopicItemListBtn" />
				    </a>
				    <input class="ext_btn ext_btn_submit" type ="button" value="关联商品" id="associateTopicItem" />
				</div>
			</div>
		</div>
		
		<#-- 数据显示块 -->
		<div id="table" class="mt10">
			<div class="box span10 oh">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="dataList">
			    	<thead>
			    	<tr>
			    		<th width="3%">全选<input type="checkbox" id="checkAllTopicItem"></th>
			    		<th width="4%">ID</th>
			    		<th>专题名称</th>
			    		<th>专题类型</th>
			    		<th>专题状态</th>
			    		<th>专题进度</th>
			    		<th>专题商品数量</th>
			    		<th>操作</th>
			    	</tr>
			    	<thead>
			    	<tbody id="topicItemList">
			    	<#if page.list?default([])?size!=0>
			    	<#list page.list as obj>
			    		<tr class="tr">
			    			<td><input type="checkbox" class="assTopicItem"></td>
			    			<td class="td_center">${obj.id}</td>
			    			<td class="td_center">${obj.name}</td>
			    			<td class="td_center">
			    				<#if obj.type == 1>单品团
			    				<#elseif obj.type == 2>主题团
			    				<#else>品牌团
			    				</#if>
			    			</td>
			    			<td class="td_center">
			    				<#if obj.status==0>无效
								<#else>有效
								</#if>
			    			</td>
			    			<td class="td_center">
								<#if obj.progress == 0>未开始
								<#elseif obj.progress == 1>进行中
								<#else>已结束
								</#if>
							</td>
							<td class="td_center"></td>
			    			<td class="td_center">
			    				<a href="javascript:void(0);" class="editBtn" param="${obj.id}">[编辑]</a>
			    				<a href="javascript:void(0);" class="associateItemBtn" param="${obj.id}">[关联商品]</a>
			    			</td>
			    		</tr>
			    	</#list>
			    	</#if>
					</tbody>
			    </table>
			</div>
			
			<div style="font-size:16px">
				<#if norecoders!=null>${noRecoders}</#if>
			</div>
		</div>
		

    <@pager  pagination=page  formId="topicItemListForm" />
 
</form>
</div>

</@backend>
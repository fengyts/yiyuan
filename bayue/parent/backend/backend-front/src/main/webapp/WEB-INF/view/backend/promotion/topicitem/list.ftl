<#include "/common/common.ftl" />
<@backend title="专题商品列表"
js=[
'/statics/common/common-js/tab.js',
'/statics/backend/promotion/topicItem.js'
]
css=[]
>

<div class="box">
<form class="jqtransform" method="post" id="topicForm" action="${domain}/topicItem/list.htm">
		<#-- 搜索表单模块 -->
		<div id="search_bar" class="box mt10">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">专题商品列表页面</b>
				</div>
				<div class="box_center pt10 pb10">
					<table class="form_table" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>专题ID:</td>
						<td>
				  			<input type="text" id="topicId" name="topicId" value="${topicItemDO.topicId}" class="input-text lh25" size="30">
						</td>
						<td>专题名称:</td>
						<td>
				  			<input type="text" id="topicName" name="topicName" value="${topicItemDO.topicName}" class="input-text lh25" size="30">
						</td>
						<td>是否测试商品:</td>
						<td>
							<select class="select" name="isTest">
								<option value=''>全部</option>
								<option value='0'>否</option>
								<option value='1'>是</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>商品名称:</td>
						<td>
				  			<input type="text" id="mainTitle" name="mainTitle" value="${topicItemDO.mainTitle}" class="input-text lh25" size="30">
						</td>
						<td>spu:</td>
						<td>
				  			<input type="text" id="spu" name="spu" value="${topicItemDO.spu}" class="input-text lh25" size="30">
						</td>
						<td>prdid:</td>
						<td>
				  			<input type="text" id="prdid" name="prdid" value="${topicItemDO.prdid}" class="input-text lh25" size="30">
						</td>
					</tr>
					<tr>
						<td>商品排序：</td>
						<td>
				  			<input type="text" id="sort" name="sort" value="${topicItemDO.sort}" class="input-text lh25" size="30">
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
					</table>
				</div>
				<div class="box_bottom pb5 pt5 pr10 search_bar_btn" style="padding-left:5px;border-top:1px solid #dadada;">
				    <a href="javascript:void(0);">
				    	<input class="btn btn82 btn_search" onclick="$('#topicForm').submit();" type="button" value="查询" name="button" />
				    </a>
				    <input class="btn btn82 btn_add" type ="button" value="新增" id="addTopic" />
				</div>
			</div>
		</div>
		
		<#-- 数据显示块 -->
		<div id="table" class="mt10">
			<div class="box span10 oh">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="dataList">
			    	<tr>
			    		<th width="5%">专题ID</th>
			    		<th>专题名称</th>
			    		<th>spu</th>
			    		<th>prdid</th>
			    		<th>商品名称</th>
			    		<th>市场价</th>
			    		<th>活动价格</th>
			    		<th>活动参与人数</th>
			    		<th>销售数量</th>
			    		<th>商品状态</th>
			    		<th>操作</th>
			    	</tr>
			    	<#if page.list?default([])?size!=0>
			    	<#list page.list as obj>
			    		<tr class="tr">
			    			<td class="td_center">${obj.topicId}</td>
			    			<td class="td_center">${obj.name}</td>
			    			<td class="td_center">${obj.spu}</td>
			    			<td class="td_center">${obj.prdid}</td>
			    			<td class="td_center">${obj.mainTitle}</td>
			    			<td class="td_center">${obj.basicPrice}</td>
			    			<td class="td_center">${obj.topicPrice}</td>
			    			<td class="td_center">${obj.snatchNumber}</td>
			    			<td class="td_center">${obj.saledAmount}</td>
							<td class="td_center">
								<#if obj.status != 'true'>无效
								<#else>有效
								</#if>
							</td>
			    			<td class="td_center">
			    				<a href="javascript:void(0);" class="editBtn" param="${obj.id}">[编辑]</a>
			    				<a href="javascript:void(0);" class="associateItemBtn" param="${obj.id}">[查看关联商品]</a>
			    			</td>
			    		</tr>
			    	</#list>
			    	</#if>
			    </table>
			</div>
			
			<div style="font-size:16px">
				<#if norecoders!=null>${noRecoders}</#if>
			</div>
		</div>
		

    <@pager  pagination=page  formId="topicForm" />
 
</form>
</div>

</@backend>
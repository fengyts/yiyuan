<#include "/common/common2.ftl" />

<@backend title="商品管理" js=[
'/statics/backend/promotion/topicItem.js'
] 
css=[
] >

<style>
	.panel-default {
	  border-color: #ddd;
	}
	.panel-default > .panel-heading {
	  color: #333;
	  background-color: #f5f5f5;
	  border-color: #ddd;
	  padding:5px;
	}
	.panel-default > .panel-heading + .panel-collapse > .panel-body {
	  border-top-color: #ddd;
	}
	.panel-default > .panel-heading .badge {
	  color: #f5f5f5;
	  background-color: #333;
	}
	.panel-default > .panel-footer + .panel-collapse > .panel-body {
	  border-bottom-color: #ddd;
	}
</style>

<div class="box">
<form class="jqtransform" method="get" id="initItemDetailForm" action="${domain}/topic/initItemDetailList.htm?topicId=${topicId}">
		<input type="hidden" id="topicId" name="topicId" value="${topicId}">
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
				  			<input type="text" id="spu" name="spu" value="${detailDO.spu}" class="input-text lh25" size="20">
						</td>
						<td>PRDID：</td>
						<td>
				  			<input type="text" id="prdid" name="prdid" value="${detailDO.prdid}" class="input-text lh25" size="20">
						</td>
					</tr>
					<tr>
						<td>商品名称:</td>
						<td>
				  			<input type="text" id="mainTitle" name="mainTitle" value="${detailDO.mainTitle}" class="input-text lh25" size="30">
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
				<div class="box_bottom pb5 pt5 pr10 search_bar_btn" style="margin-left:5px;border-top:1px solid #dadada;">
				    <a href="javascript:void(0);">
				    	<input class="btn btn82 btn_search" onclick="$('#initItemDetailForm').submit();" type="button" value="查询" name="button" />
				    </a>
				    <input class="btn btn82 btn_save2" type ="button" value="确定" id="associateTopicItemConfirm" />
				    <input class="btn btn82 btn_res" type ="button" value="取消" id="cancelAssociateTopicItemBtn" />
				</div>
			</div>
		</div>
		
		<#-- 数据显示块 -->
		<div id="table" class="mt10">
			<div class="panel panel-default">
				<div class="panel-heading">
					<input class="ext_btn ext_btn_submit" type="checkbox" id="itemStatus" name="itemStatus" />关联之后是否设置商品有效 &nbsp;&nbsp;&nbsp;
					<input class="ext_btn ext_btn_submit" type="checkbox" id="isTestItem" name="isTestItem" />是否测试商品
				</div>
			</div>
			<div class="box span10 oh">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="dataListDetail">
			    	<thead>
				    	<tr>
				    		<th width="6%">全选<input type='checkbox' id="checkAllDetail"/></th>
				    		<th style="display:none;"></th>
				    		<th style="display:none;"></th>
				    		<th>SPU</th>
				    		<th>PRDID</th>
				    		<th>商品显示名</th>
				    		<th>大类</th>
				    		<th style="display:none;"></th>
				    		<th>小类</th>
				    		<th>单位</th>
				    		<th>商品状态</th>
				    		<th>市场价(元)</th>
				    		<th>活动价(元)</th>
				    		<th>参与人数</th>
				    	</tr>
			    	</thead>
			    	<tbody id="dataBodyList">
				    	<#if page.list?default([])?size!=0>
				    	<#list page.list as obj>
				    		<tr class="tr">
				    			<td class="td_center">
				    				<input type="checkbox" name="detailId" param="${obj.id}" />
				    			</td>
				    			<td style="display:none;">${obj.itemId}</td>
				    			<td style="display:none;">${obj.id}</td>
				    			<td class="td_center">${obj.spu}</td>
				    			<td class="td_center">${obj.prdid}</td>
				    			<td class="td_center">${obj.mainTitle}</td>
				    			<td class="td_center">${obj.largeCateName}</td>
				    			<td style="display:none;">${obj.smallId}</td>
				    			<td class="td_center">${obj.smallCateName}</td>
				    			<td class="td_center">${obj.unitName}</td>
				    			<td class="td_center">
									<#if 0==obj.status>未上架
									<#elseif 1==obj.status>已上架
									<#else>作废
									</#if>
									<input type="hidden" value="${obj.status}">
								</td>
								<td class="td_center">${obj.basicPrice?string('0.00')}</td>
								<td class="td_center"><input type="text" class="input-text" size="15" value="${obj.topicPrice!obj.basicPrice?string('#.00')}"></td>
								<td class="td_center"><input type="text" class="input-text" size="15" value="${(obj.snatchNumber!obj.basicPrice?ceiling)!obj.topicPrice?ceiling}"></td>
				    		</tr>
				    	</#list>
				    	</#if>
					</tbody>
			    </table>
			</div>
			
			<div style="font-size:16px">
				<#if noRecords!=null>${noRecords}</#if>
			</div>
		</div>
		
    <@pager  pagination=page  formId="initItemDetailForm" />
 
</form>
</div>

</@backend>
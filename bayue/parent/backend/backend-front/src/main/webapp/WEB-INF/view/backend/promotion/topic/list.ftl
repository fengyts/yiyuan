<#include "/common/common.ftl" />
<@backend title=""
js=[
'/statics/common/common-js/tab.js',
'/statics/backend/promotion/topic.js'
]
css=[]
>

<div class="box">
<form class="jqtransform" method="post" id="topicForm" action="${domain}/topic/list.htm">
		<#-- 搜索表单模块 -->
		<div id="search_bar" class="box mt10">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">专题活动列表页面</b>
				</div>
				<div class="box_center pt10 pb10">
					<table class="form_table" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>专题编号：</td>
						<td>
				  			<input type="text" id="spu" name="spu" value="${topicDO.number}" class="input-text lh25" size="20">
						</td>
						<td>专题名称：</td>
						<td>
				  			<input type="text" id="spu" name="spu" value="${topicDO.name}" class="input-text lh25" size="20">
						</td>
						<td>专题状态：</td>
						<td>
							<select name="status" class="select">
								<option value='' selected='selected'>全部</option>
								<option value='0' <#if topicDO.status==0>selected='selected'</#if>>无效</option>
								<option value='1' <#if topicDO.status==1>selected='selected'</#if>>有效</option>
							</select>
						</td>
						<td>专题进度：</td>
						<td>
				  			<select name="progress" class="select">
								<option value='' selected='selected'>全部</option>
								<#list topicProgress as progress>
									<option value='${progress.code}'>${progress.desc}</option>
								</#list>
							</select>
						</td>
						<td>专题类型：</td>
						<td>
				  			<select name="type" class="select">
								<option value='' selected='selected'>全部</option>
								<#list topicType as type>
									<option value='${type.code}'>${type.desc}</option>
								</#list>
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
			    		<th width="5%">ID</th>
			    		<th>专题名称</th>
			    		<th>专题类型</th>
			    		<th>专题状态</th>
			    		<th>专题进度</th>
			    		<th>专题商品数量</th>
			    		<th>操作</th>
			    	</tr>
			    	<#if page.list?default([])?size!=0>
			    	<#list page.list as obj>
			    		<tr class="tr">
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
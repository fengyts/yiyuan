<#include "/common/common.ftl" />

<@backend title="商品新增" 
js=[
'/statics/plugin/layui-v1.0.2/layui/layui.js',
'/statics/plugin/bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js',
'/statics/backend/item/associateSpecGroup.js'
]
css=[
'/statics/common/common-css/common.css',
'/statics/common/common-css/style.css',
'/statics/plugin/bootstrap/bootstrap-3.3.5-dist/css/bootstrap.min.css'
]>

<div class="container panel-body box_border">

<form class="form-horizontal" role="form" id="associateSpecGroupForm" action="">
	<div class="form-group">
		<label for="firstname" class="text-center col-xs-2 control-label">规格组</label>
		<div class="col-xs-4">
			<input type="text" name="alias" class="form-control" size="20" placeholder="请输入规格租名称或别名" value='${specGroupDO.alias}'>
		</div>
		<div class="col-xs-4" style="margin-left:20px;">
			<button type="button" class="btn btn-primary" id="searchBtn">查询</button>
			<button type="button" class="btn btn-info" id="cancelBtn">取消</button>
			<button type="button" class="btn btn-success" id="confirmBtn">确定</button>
		</div>
	</div>
	<div class="form-group">
		<table class="list_table" id="dataList">
			<thead>
		    	<tr>
		    		<th class="text-center"></th>
				    <th class="text-center">规格组名称</th>
				    <th class="text-center">规格组别名</th>
				    <th class="text-center  col-md-4">规格组排序</th>
			    </tr>
			</thead>
			<tbody>
				<#if page.list?default([])?size!=0>
				<#list page.list as specGroup>
					<tr class="tr">
						<td class="text-center"><input type="radio" name="specGroupId" value="${specGroup.id}" /></td>
						<td class="text-center">${specGroup.name}</td>
						<td class="text-center">${specGroup.alias}</td>
						<td class="text-center">${specGroup.sort}</td>
					</tr>
				</#list>
				</#if>
			</tbody>
		</table>
	</div>
	
	
	<@pager  pagination=page  formId="associateSpecGroupForm" />  
</form>

</div>

</@backend>
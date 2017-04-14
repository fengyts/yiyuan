<#include "/common/common.ftl"/>
<@backend title="类别编辑" 
js=[
'/statics/plugin/layer/layer.min.js',
'/statics/plugin/jqgrid/js/jquery.jqGrid.min.js',
'/statics/plugin/jqgrid/js/i18n/grid.locale-cn.js',
'/statics/backend/basedata/category.js'
] 
css=[
'/statics/plugin/jqgrid/css/ui.jqgrid.css',
'/statics/plugin/jquery/jqueryui/css/cupertino/jquery-ui-1.9.2.custom.min.css'
]
>

<div id="forms" class="mt10">
    <div class="box">
        <span>${ansName}</span> 
	    <div class="box_border"> 
		   <form class="jqtransform" method="post" id="categoryEditForm">
		   
		     <table class="form_table" border="0" cellpadding="0" cellspacing="0">
		      	 <tr style="display:none;">
          			<td><input type="hidden" name="id" value="${categoryDO.id}" /></td>
          			<td><input type="hidden" name="level" value="${categoryDO.level}" /></td>
          			<td><input type="hidden" name="parentId" value="${categoryDO.parentId}" /></td>
          			<td><input type="hidden" name="oldStatus" value="${(categoryDO.status!true)?string('true','false')}"></td>
          		 </tr>
		      	 <tr>
					<td>名称:</td>
					<td><input type="text" name="name" class="input-text lh25" size="20" maxlength="50" value="${categoryDO.name}" /></td>
				 </tr>
				 <tr>
					<td>状态:</td>
					<td>
						有效: <input type="radio" name="status" value="1" <#if categoryDO.status=="true">checked='checked'</#if> />&nbsp;&nbsp;
						无效: <input type="radio" name="status" value="0" <#if categoryDO.status!="true">checked='checked'</#if> /> 
					</td>
				 </tr>
				 <tr>
					<td>备注:</td>
					<td><input type="text" name="remark" class="input-text lh25" size="20" maxlength="50" value="${categoryDO.remark}" /></td>
				 </tr>
				 
				 
				 <tr>
				    <td>
				       <div id="div1_submit" style="text-align:center;">
						   <input id="categoryUpdateBtn" class="btn btn82 btn_save2" type="button" value="保存" onclick="updateCategory();">			         
					   </div>
				    </td>
				    <td>
				       <div id="div1_submit" style="text-align:center;">
				           <input id="cancelbtn" class="btn btn82 btn_del closebtn" type="button" value="取消" name="button">
					   </div>
				    </td>
				 </tr>
				  
		     </table>
		       
		   </form>
	      
	    </div>
	</div>
</div>

</@backend>
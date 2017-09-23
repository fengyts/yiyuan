<#include "/common/common.ftl"/>
<@backend title="攻略编辑" 
js=[
'/statics/plugin/layer/layer.min.js',
'/statics/plugin/jqgrid/js/jquery.jqGrid.min.js',
'/statics/plugin/jqgrid/js/i18n/grid.locale-cn.js',
'/statics/backend/basedata/strategy.js'
] 
css=[
'/statics/plugin/jqgrid/css/ui.jqgrid.css',
'/statics/plugin/jquery/jqueryui/css/cupertino/jquery-ui-1.9.2.custom.min.css'
]
>

<div id="forms" class="mt10">
    <div class="box">
	    <div class="box_border"> 
	    
		   <form class="jqtransform" method="post" id="strategyEditForm">
		   
		     <table class="form_table" border="0" cellpadding="0" cellspacing="0">
		      	 <tr style="display:none;">
          			<td><input type="hidden" name="id" value="${strategyDO.id}" /></td>
          			<td><input type="hidden" name="level" value="${strategyDO.level}" /></td>
          		 </tr>
          		 <tr>
          		 	<td>所属模块</td>
          		 	<td>
          		 		<select name="parentId" class="select">
          		 			<option value="0" selected="selected">默认顶级</option>
          		 			<#if listParents?default([])?size!=0>
          		 				<#list listParents as parent>
          		 					<option value="${parent.id}" <#if parent.id==strategyDO.parentId>selected="selected"</#if>>${parent.module}</option>
          		 				</#list>
          		 			</#if>
          		 		</select>
          		 	</td>
          		 </tr>
		      	 <tr>
					<td>标题:</td>
					<td><input type="text" name="title" class="input-text lh25" size="20" maxlength="50" value="${strategyDO.title}" /></td>
				 </tr>
				 <tr>
					<td>内容:</td>
					<td>
						<textarea name="content" cols="50" rows="5" maxlength=5000>${strategyDO.content}</textarea>
					</td>
				 </tr>
				 <tr>
					<td>状态:</td>
					<td>
						有效: <input type="radio" name="status" value="1" <#if strategyDO.status=="true">checked='checked'</#if> />&nbsp;&nbsp;
						无效: <input type="radio" name="status" value="0" <#if strategyDO.status!="true">checked='checked'</#if> /> 
					</td>
				 </tr>
				 <tr>
					<td>备注:</td>
					<td><input type="text" name="remark" class="input-text lh25" size="20" maxlength="50" value="${strategyDO.remark}" /></td>
				 </tr>
				 
				 
				 <tr>
				    <td>
				       <div id="div1_submit" style="text-align:center;">
						   <input id="strategyUpdateBtn" class="btn btn82 btn_save2" type="button" value="保存" onclick="updateStrategy();">			         
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
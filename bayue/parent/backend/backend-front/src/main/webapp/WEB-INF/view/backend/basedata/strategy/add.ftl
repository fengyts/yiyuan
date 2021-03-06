<#include "/common/common.ftl"/>
<@backend title="攻略新增" 
js=[
'/statics/plugin/layer/layer.min.js',
'/statics/plugin/jqgrid/js/jquery.jqGrid.min.js',
'/statics/plugin/jqgrid/js/i18n/grid.locale-cn.js',
'/statics/backend/basedata/strategy.js'
] 
css=[]
>

<div id="forms" class="mt10">
    <div class="box">
	    <div class="box_border">
	    
		   <form class="jqtransform" method="post" id="strategyAddForm">
		   
		     <table class="form_table" border="0" cellpadding="0" cellspacing="0">
		      	 <tr>
					<td>所属模块:</td>
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
		     	 	<td>模块名称</td>
		     	 	<td><input type="text" name="module" class="input-text lh25" size="20" maxlength="50" /></td>
		     	 </tr>
				 <tr>
					<td valign="middle">标题:</td>
					<td><input type="text" name="title" class="input-text lh25" size="20" maxlength="50" /></td>
				 </tr>
				 <tr>
					<td valign="middle">内容:</td>
					<td>
						<textarea name="content" cols="50" rows="5" maxlength=5000></textarea>
					</td>
				 </tr>
				 <tr>
					<td>状态:</td>
					<td valign="middle" align="left">
						<input id="at1" type="radio" name="status" checked='checked' value="1" />
						<label for="at1" >有效</label>
						<input id="at2" type="radio" name="status" value="0" />
						<label for="at2" >无效</label>
					</td>
				 </tr>
				 <tr>
					<td valign="middle">备注:</td>
					<td><input type="text" name="remark" class="input-text lh25" size="20" maxlength="50"/></td>
				 </tr>
				 
				 
				 <tr>
				    <td>
				       <div id="div1_submit" style="text-align:center;">
						   <input id="strategySaveBtn" class="btn btn82 btn_save2" type="button" value="保存">			         
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
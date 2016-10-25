<#include "/common/common.ftl"/>
<@backend title="数据字典编辑" 
js=[
'/statics/plugin/layer/layer.min.js',
'/statics/backend/basedata/dictionary.js'
] 
css=[]
>

<div id="forms" class="mt10">
    <div class="box">
	    <div class="box_border">
	    
		   <form class="jqtransform" method="post" id="dictionaryEditForm">
		   
		     <table class="form_table" border="0" cellpadding="0" cellspacing="0">
		         <tr style="display:none">
		         	<td>
		         		<input type="hidden" name="id" value="${dictionaryDO.id}" />
		         		<input type="hidden" name="code" value="${dictionaryDO.code}" />
		         	</td>
		         </tr>
		      	 <tr>
					<td>名称:</td>
					<td><input type="text" name="name" class="input-text lh25" size="20" maxlength="50" value="${dictionaryDO.name}" /></td>
				 </tr>
				 <tr>
					<td>code:</td>
					<td id="idCodeTd">
						<span>
							<select name="code" class="select">
								<option value="">--请选择--</option>
								<#list listCode as c>
									<option value="${c.code}" <#if dictionaryDO.code==c.code>selected='selected'</#if>>${c.code}</option>
								</#list>
							</select>
						</span>
						</span>
						<span>
							<input type="button" id="addNewCode" class="ext_btn ext_btn_submit m10" value="新增字典类别码"/>
						</span>
					</td>
				 </tr>
				 <tr>
					<td>状态:</td>
					<td valign="middle" align="left">
						<input id="at1" type="radio" name="status" value="1" <#if dictionaryDO.status=='true'>checked='checked'</#if> />
						<label for="at1" >有效</label>
						<input id="at2" type="radio" name="status" value="0" <#if dictionaryDO.status!='true'>checked='checked'</#if>/>
						<label for="at2" >无效</label>
					</td>
				 </tr>
				 <tr>
					<td>排序值:</td>
					<td><input type="text" name="sortNo" class="input-text lh25" size="20" maxlength="50" value="${dictionaryDO.sortNo}" /></td>
				 </tr>
				 <tr>
					<td>备注:</td>
					<td><input type="text" name="remark" class="input-text lh25" size="20" maxlength="50" value="${dictionaryDO.remark}" /></td>
				 </tr>
				 
				 
				 <tr>
				    <td>
				       <div id="div1_submit" style="text-align:center;">
						   <input id="dictionaryUpdateBtn" class="btn btn82 btn_save2" type="button" value="保存">			         
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
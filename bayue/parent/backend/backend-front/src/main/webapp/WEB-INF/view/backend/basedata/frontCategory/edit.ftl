<#include "/common/common.ftl"/>
<@backend title="类别编辑" 
js=[
'/statics/plugin/layer/layer.min.js',
'/statics/plugin/jqgrid/js/jquery.jqGrid.min.js',
'/statics/plugin/jqgrid/js/i18n/grid.locale-cn.js',
'/statics/backend/basedata/frontCategory.js'
] 
css=[
]
>

<div id="forms" class="mt10">
    <div class="box">
        <span>${ansName}</span> 
	    <div class="box_border"> 
		   <form class="jqtransform" method="post" id="frontCategoryEditForm">
		   
		     <table class="form_table" border="0" cellpadding="0" cellspacing="0">
		      	 <tr style="display:none;">
          			<td><input type="hidden" name="id" value="${fcate.id}" /></td>
          			<td><input type="hidden" name="level" value="${fcate.level}" /></td>
          			<td><input type="hidden" name="parentId" value="${fcate.parentId}" /></td>
          			<td><input type="hidden" name="oldStatus" value="${(fcate.status!true)?string('true','false')}"></td>
          			<td><input type="hidden" name="frontCategoryId" value="${fcate.frontCategoryId}"></td>
          		 </tr>
		      	 <tr>
					<td>名称:</td>
					<td><input type="text" name="name" class="input-text lh25" size="20" maxlength="50" value="${fcate.name}" /></td>
				 </tr>
				 <tr>
					<td>状态:</td>
					<td>
						有效: <input type="radio" name="status" value="1" <#if fcate.status=="true">checked='checked'</#if> />&nbsp;&nbsp;
						无效: <input type="radio" name="status" value="0" <#if fcate.status!="true">checked='checked'</#if> /> 
					</td>
				 </tr>
				 <tr>
					<td>是否突出展示:</td>
					<td valign="middle" align="left">
						<input id="at1" type="radio" name="isHighlight" value="1" <#if fcate.isHighlight=="true">checked</#if> />
						<label for="at1" >是</label>
						<input id="at2" type="radio" name="isHighlight" value="0" <#if fcate.isHighlight!="true">checked</#if> />
						<label for="at2" >否</label>
					</td>
				 </tr>
				 <tr>
					<td>是否发布:</td>
					<td valign="middle" align="left">
						<input id="at1" type="radio" name="isPublish" checked='checked' value="1" <#if fcate.isPublish=="true">checked</#if> />
						<label for="at1" >是</label>
						<input id="at2" type="radio" name="isPublish" value="0" <#if fcate.isPublish!="true">checked</#if> />
						<label for="at2" >否</label>
					</td>
				 </tr>
				 <tr>
					<td>logo地址:</td>
					<td><input type="text" name="logoUrl" class="input-text lh25" size="60" maxlength="50" value="${fcate.logoUrl}" /></td>
				 </tr>
				 <tr>
					<td>顺序:</td>
					<td><input type="text" name="sort" class="input-text lh25" size="60" maxlength="50" value="${fcate.sort}" /></td>
				 </tr>
				 <tr>
				 	<td>跳转方式:</td>
				 	<td>
				 		<select name="linkType" class="select">
							<#--<option value=''>--请选择--</option>-->
							<#list linkTypes as t>
								<option value="${t.code}" <#if fcate.linkType == t.code>selected</#if>>${t.desc}</option>
							</#list>
						</select>
				 	</td>
				 </tr>
				 <tr>
					<td>关联后台品类</br>(大类,多个大类用英文逗号分隔):</td>
					<td><input type="text" name="largeCategoryIds" class="input-text lh25" size="60" maxlength="50" placeholder="例:1,8,55" value="${fcate.largeCategoryIds}" /></td>
				 </tr>
				 <tr>
					<td>关联后台品类</br>(小类,多个小类用英文逗号分隔):</td>
					<td><input type="text" name="smallCategoryIds" class="input-text lh25" size="60" maxlength="50" placeholder="例:2,3,9" value="${fcate.smallCategoryIds}" /></td>
				 </tr>
				 <tr>
					<td>跳转链接地址:</td>
					<td><input type="text" name="linkUrlPc" class="input-text lh25" size="60" maxlength="50" value="${fcate.linkUrlPc}" /></td>
				 </tr>
				 <tr>
					<td>跳转链接内容:</td>
					<td><input type="text" name="linkContent" class="input-text lh25" size="60" maxlength="50" value="${fcate.linkContent}" /></td>
				 </tr>
				 
				 
				 <tr>
				    <td>
				       <div id="div1_submit" style="text-align:center;float:left;display:inline;" >
						   <input id="categoryUpdateBtn" class="btn btn82 btn_save2" type="button" value="保存" onclick="updateFrontCategory();">			         
					   </div>
				       <div id="div1_submit" class="pl10" style="text-align:center;display:inline;">
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
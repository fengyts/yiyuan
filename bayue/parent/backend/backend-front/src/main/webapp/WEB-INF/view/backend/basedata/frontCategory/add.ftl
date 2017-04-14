<#include "/common/common.ftl"/>
<@backend title="类别编辑" 
js=[
'/statics/plugin/layer/layer.min.js',
'/statics/plugin/jqgrid/js/jquery.jqGrid.min.js',
'/statics/plugin/jqgrid/js/i18n/grid.locale-cn.js',
'/statics/backend/basedata/frontCategory.js'
] 
css=[]
>

<div id="forms" class="mt10">
    <div class="box">
	    <div class="box_border">
	    
		   <form class="jqtransform" method="post" id="frontCategoryAddForm">
		   
		     <table class="form_table" border="0" cellpadding="0" cellspacing="0">
		      	 <tr>
					<td>类别名称:</td>
					<td><input type="text" name="name" class="input-text lh25" size="60" maxlength="50" /></td>
				 </tr>
				 <tr>
					<td>级别:</td>
					<td>
						<select name="level" class="select">
							<#--<option value=''>--请选择--</option>-->
							<option value='1' selected='selected'>一级</option>
							<option value='2'>二级</option>
						</select>
					</td>
				 </tr>
				 <tr>
					<td>父级:</td>
					<td>
						<select name="parentId" class="select">
							<option value='0'>--请选择--</option> <#-- 默认一级 -->
							<#if listFirst?default([])?size != 0>
								<#list listFirst as fir>
									<option value="${fir.id}">${fir.name}</option>
								</#list>
							</#if>
						</select>
						<span style="color:red;">添加一级分类可以不选</span>
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
					<td>是否突出展示:</td>
					<td valign="middle" align="left">
						<input id="at1" type="radio" name="isHighlight" value="1" />
						<label for="at1" >是</label>
						<input id="at2" type="radio" name="isHighlight" checked='checked' value="0" />
						<label for="at2" >否</label>
					</td>
				 </tr>
				 <tr>
					<td>是否发布:</td>
					<td valign="middle" align="left">
						<input id="at1" type="radio" name="isPublish" checked='checked' value="1" />
						<label for="at1" >是</label>
						<input id="at2" type="radio" name="isPublish" value="0" />
						<label for="at2" >否</label>
					</td>
				 </tr>
				 <tr>
					<td>logo地址:</td>
					<td><input type="text" name="logoUrl" class="input-text lh25" size="60" maxlength="50" /></td>
				 </tr>
				 <tr>
					<td>顺序:</td>
					<td><input type="text" name="sort" class="input-text lh25" size="60" maxlength="50" /></td>
				 </tr>
				 <tr>
				 	<td>跳转方式:</td>
				 	<td>
				 		<select name="linkType" class="select">
							<#--<option value=''>--请选择--</option>-->
							<#list linkTypes as linkType>
								<option value="${linkType.code}">${linkType.desc}</option>
							</#list>
						</select>
				 	</td>
				 </tr>
				 <tr>
					<td>关联后台品类</br>(大类,多个大类用英文逗号分隔):</td>
					<td><input type="text" name="largeCategoryIds" class="input-text lh25" size="60" maxlength="50" placeholder="例:1,8,55" /></td>
				 </tr>
				 <tr>
					<td>关联后台品类</br>(小类,多个小类用英文逗号分隔):</td>
					<td><input type="text" name="smallCategoryIds" class="input-text lh25" size="60" maxlength="50" placeholder="例:2,3,9" /></td>
				 </tr>
				 <tr>
					<td>跳转链接地址:</td>
					<td><input type="text" name="linkUrlPc" class="input-text lh25" size="60" maxlength="50" /></td>
				 </tr>
				 <tr>
					<td>跳转链接内容:</td>
					<td><input type="text" name="linkContent" class="input-text lh25" size="60" maxlength="50" value="" /></td>
				 </tr>
				 
				 <tr>
				    <td>
				       <div id="div1_submit" style="text-align:center;float:left;display:inline;" >
						   <input id="frontCategorySaveBtn" class="btn btn82 btn_save2" type="button" value="保存">			         
					   </div>
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
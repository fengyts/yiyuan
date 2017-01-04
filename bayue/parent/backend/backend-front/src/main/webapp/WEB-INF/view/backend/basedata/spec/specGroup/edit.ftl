<#include "/common/common.ftl"/>
<@backend title="规格编辑" js=[
'/statics/plugin/layer/layer.min.js',
'/statics/backend/basedata/specGroup.js'
] css=[
]>

	<div id="forms" class="mt10">
        <div class="box">
          <div class="box_border"> 
          
          <form class="jqtransform" method="post" id="specGroupEditForm">
	          <table class="form_table" border="0" cellpadding="0" cellspacing="0">
	          		<tr style="display:none;">
	          			<td>
	          				<input type="hidden" name="id" value="${specGroupDO.id}" />
	          				<input type="hidden" name="oldspecGroup" value="${specGroupDO.name}" />
	          			</td>
	          		</tr>
	          		<tr>
						<td>名称:</td>
						<td>
						<input type="text" name="name" class="input-text lh25" size="20" maxlength="50" value="${specGroupDO.name}" /></td>
					</tr>
					<tr>
						<td>别名:</td>
						<td>
						<input type="text" name="alias" class="input-text lh25" size="20" maxlength="50" value="${specGroupDO.alias}" /></td>
					</tr>
					<tr>
						<td>编号:</td>
						<td>
						<input type="text" name="code" class="input-text lh25" size="20" maxlength="50" value="${specGroupDO.code}" /></td>
					</tr>
					<tr>
						<td>状态:</td>
						<td>
							有效：<input type="radio" name="status" value="1" <#if specGroupDO.status=="true">checked="checked"</#if> /> &nbsp;&nbsp;
							无效：<input type="radio" name="status" value="0" <#if specGroupDO.status!="true">checked="checked"</#if> />
						</td>
					</tr>
					<tr>
						<td>排序值:</td>
						<td>
							<input type="text" name="sort" class="input-text lh25" size="20" maxlength="50" value="${specGroupDO.sort}"/>
						</td>
					</tr>
					<tr>
						<td>备注:</td>
						<td>
							<input type="text" name="remark" class="input-text lh25" size="20" maxlength="50" value="${specGroupDO.remark}"/>
						</td>
					</tr>
			  </table>
			  
			  <#-- 水平分割线 -->
		  	  <#--
			  	  <div>
				  	  <hr style="color:#d3dbde"/>
				  </div>
			  -->
			  
			  <table id="specList" width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
			  	  <tr>
					  <td colspan="4" style="text-align:left;">
						  <input type="button" class="ext_btn ext_btn_submit m10" id="selectSpec" value="添加规格" />
					  </td>
				  </tr>
				  <tr>
					  <th width="30">ID</th>
					  <th width="50">spec</th>
					  <th width="30">sort</th>
					  <th width="50">状态</th>
					  <th width="50">操作</th>
				  </tr>
				  <#if listSpec?default([])?size!=0>
				  	 <#list listSpec as spec>
				  	 	<tr class="specTr">
				  	 		<td class="td_center">${spec.id}</td>
				  	 		<td class="td_center">${spec.spec}</td>
				  	 		<td class="td_center"><input type="text" class="input-text" size="10" value="${spec.sort}"/></td>
				  	 		<td class="td_center">${(spec.status=='true')?string("有效","无效")}</td>
				  	 		<td class="td_center">
				  	 			<a href="javascript:void(0);" class="unboundSpec">删除</a>
				  	 		</td>
				  	 	</tr>
				  	 </#list>
				  </#if>
			  </table>
			  
			  <#-- 水平分割线 -->
			  <#--
			  	  <div>
				  	  <hr style="color:#d3dbde"/>
				  </div>
			  -->
			  
			  
		  </form>
		  
		  </div>
		  
		  <table>	
				<tr>
				    <td>
				       <div id="div1_submit" style="text-align:center;">
						   <input id="specGroupUpdateBtn" class="btn btn82 btn_save2" type="button" value="保存">			         
					   </div>
				    </td>
				    <td>
				       <div id="div1_submit" style="text-align:center;">
				           <input id="cancelbtn" class="btn btn82 btn_del closebtn" type="button" value="取消" name="button">
					   </div>
				    </td>
				</tr>
					
		 </table>
		  
		</div>
	</div>
		
</@backend>
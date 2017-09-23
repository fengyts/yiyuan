<#include "/common/common.ftl"/>
<@backend title="规格编辑" js=[
'/statics/plugin/layer/layer.min.js',
'/statics/backend/basedata/spec.js'
] css=[
]>

	<div id="forms" class="mt10">
        <div class="box">
          <div class="box_border"> 
          
          <form class="jqtransform" method="post" id="specEditForm">
	          <table class="form_table" border="0" cellpadding="0" cellspacing="0">
	          		<tr style="display:none;">
	          			<td>
	          				<input type="hidden" name="id" value="${specDO.id}" />
	          				<input type="hidden" name="oldSpec" value="${specDO.spec}" />
	          			</td>
	          		</tr>
	          		<tr>
						<td>规格:</td>
						<td>
						<input type="text" name="spec" class="input-text lh25" size="20" maxlength="50" value="${specDO.spec}" /></td>
					</tr>
					<tr>
						<td>编号:</td>
						<td>
						<input type="text" name="code" class="input-text lh25" size="20" maxlength="50" value="${specDO.code}" /></td>
					</tr>
					<tr>
						<td>状态:</td>
						<td>
							有效：<input type="radio" name="status" value="1" <#if specDO.status=="true">checked="checked"</#if> /> &nbsp;&nbsp;
							无效：<input type="radio" name="status" value="0" <#if specDO.status!="true">checked="checked"</#if> />
						</td>
					</tr>
					<tr>
						<td>排序值:</td>
						<td>
							<input type="text" name="sort" class="input-text lh25" size="20" maxlength="50" value="${specDO.sort}"/>
						</td>
					</tr>
					<tr>
						<td>备注:</td>
						<td>
							<input type="text" name="remark" class="input-text lh25" size="20" maxlength="50" value="${specDO.remark}"/>
						</td>
					</tr>
					
					<tr>
					    <td>
					       <div id="div1_submit" style="text-align:center;">
							   <input id="specUpdateBtn" class="btn btn82 btn_save2" type="button" value="保存">			         
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
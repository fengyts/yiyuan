<#include "/common/common.ftl"/>
<@backend title="规格组增加" js=[
'/statics/plugin/layer/layer.min.js',
'/statics/backend/basedata/specGroup.js'
] css=[
]>

	<div id="forms" class="mt10">
        <div class="box">
          <div class="box_border"> 
          
          <form class="jqtransform" method="post" id="specGroupAddForm">
	          <table class="form_table" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>名称:</td>
						<td><input type="text" name="name" class="input-text lh25" size="20" maxlength="50"/></td>
					</tr>
					<tr>
						<td>别名:</td>
						<td>
						<input type="text" name="alias" class="input-text lh25" size="20" maxlength="50" /></td>
					</tr>
					<tr>
						<td>编号:</td>
						<td>
						<input type="text" name="code" class="input-text lh25" size="20" maxlength="50" /></td>
					</tr>
					<tr>
						<td>状态:</td>
						<td>
							有效：<input type="radio" name="status" checked="checked" /> &nbsp;&nbsp;
							无效：<input type="radio" name="status" />
						</td>
					</tr>
					<tr>
						<td>排序值:</td>
						<td>
							<input type="text" name="sort" class="input-text lh25" size="20" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<td>备注:</td>
						<td>
							<input type="text" name="remark" class="input-text lh25" size="20" maxlength="50"/>
						</td>
					</tr>
			  </table>
			  
			  <#-- 水平分割线 -->
		  	  <div>
			  	  <hr style="color:#FFF0F5"/>
			  	  <input type="button" class="ext_btn ext_btn_submit m10" id="selectSpec" value="添加规格" />
			  </div>
			  <#--
		  	  <tr>
				  <td colspan="5" style="text-align:left;">
					  <input type="button" class="ext_btn ext_btn_submit m10" id="selectSpec" value="添加规格" />
				  </td>
			  </tr>
			  -->
			  <table id="specList" width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
				  <tr>
					  <th width="30">ID</th>
					  <th width="50">spec</th>
					  <th width="30">sort</th>
					  <th width="50">状态</th>
					  <th width="50">操作</th>
				  </tr>
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
					   <input id="specGroupSaveBtn" class="btn btn82 btn_save2" type="button" value="保存">			         
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
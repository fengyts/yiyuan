<#include "/common/common.ftl"/>
<@backend title="类别编辑" 
js=[
'/statics/plugin/layer/layer.min.js',
'/statics/backend/basedata/category.js'
] 
css=[]
>

<div id="forms" class="mt10">
    <div class="box">
	    <div class="box_border">
	    
	    
	    	<div class="box_center pt10 pb10">
	              <table class="form_table" border="0" cellpadding="0" cellspacing="0">
	              	<tr>
		              	<td>菜单编号：</td>
		              	<td><input class="input-text lh25" type="text" id="menucode" name="id" value="${sysMenuDO.id}"/></td>
		              	<td>菜单名称：</td>
		              	<td><input class="input-text lh25" type="text" id="name" name="name" value="${sysMenuDO.name}"/></td>
		              	<td>是否有效：</td>
		              	<td>
		              		<#assign statusTemp=sysMenuDO.state/>
		              		<select name="state" class="select">
		              			<option value="" <#if statusTemp==null>selected="selected"</#if>>全部</option>
		              			<option value="1" <#if statusTemp??&&statusTemp?string=="true">selected="selected"</#if>>有效</option>
		              			<option value="0" <#if statusTemp??&&statusTemp?string=="false">selected="selected"</#if>>无效</option>
		              		</select>
		              	</td>
	              	</tr>
	              </table>
	        </div>
	     
	       <span>${ansName}</span> 
		   <form class="jqtransform" method="post" id="sysMenuListForm">
		   
		     <table class="form_table" border="0" cellpadding="0" cellspacing="0">
		      	 <tr style="display:none;">
          			<td><input type="hidden" name="id" value="${categoryDO.id}" /></td>
          		 </tr>	
		      	 <tr>
					<td>类别:</td>
					<td><input type="text" name="name" class="input-text lh25" size="20" maxlength="50" value="${categoryDO.name}" /></td>
				 </tr>
				 
				 
				 <tr>
				    <td>
				       <div id="div1_submit" style="text-align:center;">
						   <input id="sysMenuUpdateBtn" class="btn btn82 btn_save2" type="button" value="保存">			         
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
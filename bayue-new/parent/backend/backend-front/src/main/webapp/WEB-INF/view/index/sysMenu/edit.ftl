<#include "/common/common.ftl"/>
<@backend title="系统菜单编辑" js=[
'/statics/plugin/jquery/jquery-ui-1.9.2.custom/js/jquery-ui-1.9.2.custom.js',
'/statics/plugin/select2/js/select2.js',
'/statics/plugin/select2/js/select2Util.js',
'/statics/plugin/select2/js/select2_locale_zh-CN.js',
'/statics/plugin/layer/layer.min.js',
'/statics/backend/index/sysMenu.js'
] css=[
'/statics/plugin/select2/css/select2.css',
'/statics/plugin/jquery/jquery-ui-1.9.2.custom/css/base/jquery-ui-1.9.2.custom.css'
]>

	<div id="forms" class="mt10">
        <div class="box">
          <div class="box_border"> 
          
          <form class="jqtransform" method="post" id="sysMenuEditForm">
	          <table class="form_table" border="0" cellpadding="0" cellspacing="0">
	          		<tr style="display:none;">
	          			<td><input type="hidden" name="id" value="${sysMenuDO.id}" /></td>
	          		</tr>
	          		<tr>
						<td>菜单类型:</td>
						<td>
							<select name="menuType" class="select">
								<option value="1" <#if 1==sysMenuDO.menuType>selected = "selected"</#if>>导航菜单</option>
								<option value="2" <#if 2==sysMenuDO.menuType>selected = "selected"</#if>>(主/子)菜单</option>
								<option value="3" <#if 3==sysMenuDO.menuType>selected = "selected"</#if>>按钮</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>菜单名称:</td>
						<td><input type="text" name="name" class="input-text lh25" size="50" maxlength="50" value="${sysMenuDO.name}"/></td>
					</tr>
					<tr>
						<td>菜单编码:</td>
						<td><input type="text" name="code" class="input-text lh25" size="50" maxlength="50" value="${sysMenuDO.code}"/></td>
					</tr>
					<tr>
						<td>菜单url:</td>
						<td><input type="text" name="url" class="input-text lh25" size="50" maxlength="50" value="${sysMenuDO.url}"/></td>
					</tr>
					<tr>
						<td>父级菜单:</td>
						<td>
							<select id="parentId" name="parentId" class="select2" style="width:200px; margin-left: 1px">
								<option value="" selected="selected">--请选择--</option>
								<#if parentMenus?default([])?size!=0>
									<#list parentMenus as menuDO>
										<option value="${menuDO.id}" <#if sysMenuDO.parentId == menuDO.id>selected="selected"</#if>>${menuDO.name}</option>
									</#list>
								</#if>
							</select>
						</td>
					</tr>
					<tr>
						<td>菜单状态:</td>
						<td>
							<select class="select" name="status">
								<option value="1" <#if sysMenuDO.status==true>selected="selected"</#if>>有效</option>
								<option value="0" <#if sysMenuDO.status==false>selected="selected"</#if>>无效</option>
								<#-- 效果同上
								<option value="1" <#if sysMenuDO.status=="1">selected="selected"</#if>>有效</option>
								<option value="0" <#if sysMenuDO.status=="0">selected="selected"</#if>>无效</option>
								-->
							</select>
						</td>
					</tr>
					<tr>
						<td>排序:</td>
						<td><input type="text" id="sort" name="sort" class="input-text lh25" size="20" maxlength="50" value="${sysMenuDO.sort}"/></td>
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
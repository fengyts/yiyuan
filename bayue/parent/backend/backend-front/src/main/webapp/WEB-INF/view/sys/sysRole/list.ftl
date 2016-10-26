<#include "/common/common.ftl" />
<@backend title="系统用户管理" 
js=[
'/statics/plugin/layui-v1.0.2/layui/layui.js',
'/statics/backend/sys/sysRole.js'
] css=[
'/statics/plugin/layui-v1.0.2/layui/css/layui.css'
]>

<form class="jqtransform" method="post" id="sysRoleForm" action="${domain}/sys/sysRole/list.htm">
    <div >
	   <div>	      
	       <table class="form_table" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td>角色名称:</td>
                  <td>
                  	<input type="text" name="sysRole" class="input-text lh25" size="20" value='${sysRoleDO.loginName}'>
                  </td>                 
                  <td>状态:</td>
	              <td>
	                 <span class="fl">
	                   <div class="select_border"> 
		                     <div class="select_containers "> 
		                     <select name="status" class="select"> 
				                 <option value=''      <#if  sysRoleDO.status==null>selected='selected'</#if>>全部</option> 
			                     <option value="true"  <#if  sysRoleDO.status??&&sysRoleDO.status?string=='true'>selected='selected'</#if>>有效</option> 
			                     <option value="false" <#if  sysRoleDO.status??&&sysRoleDO.status?string=='false'>selected='selected'</#if>>无效</option> 
		                     </select> 
		                     </div>                      
	                   </div>
	                 </span>
	              </td>
                </tr>
           </table>
	   </div>
	
	  <div class="box_bottom pb5 pt5 pr10" style="border-top:1px solid #dadada;">
              <div class="search_bar_btn" style="text-align:center;">
                 <input class="btn btn82 btn_search" type="submit" value="查询"/ >
                 <input class="btn btn82 btn_res " type="button" value="重置"  onclick="dataReset('sysRoleForm')" />
                 <input id="sysRoleAddbtn" class="btn btn82 btn_add addSysRole" type="button" value="新增" />
              </div>
        </div>
    </div>
    
    <div id="table" class="mt10">
        <div class="box span10 oh">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                	  <th widt="10">序号</th>	
                      <th width="50">角色名称</th>	
	                  <th width="50">编码</th>
	                  <th width="50">状态</th>
	                  <th width="200">创建时间</th>
	                  <th width="200">修改时间</th>
					  <th width="50">操作</th>
                </tr>
            	<#if page.list?default([])?size!=0>
		            <#list page.getList() as sysRole>
		                <tr class="tr">
				              <td class="td_center">${sysRole.id}</td>
				              <td class="td_center">${sysRole.name}</td>
		 					  <td class="td_center">${sysRole.code}</td>
				              <td class="td_center"><#assign sta="${sysRole.status}" /><#if sta=='true'>有效<#else>无效</#if></td>
				              <td class="td_center">${sysRole.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
		                      <td class="td_center">${sysRole.modifyTime?string("yyyy-MM-dd HH:mm:ss")}</td>  
				              <td class="td_center">
				              	<a href="javascript:void(0);" class="editcatabtn sysRoleEditbtn" param='${sysRole.id}'>[编辑]</a> &nbsp;
				              	<a href="javascript:void(0);" class="editcatabtn journalReview" param='${sysRole.id}'>[日志]</a>
				              </td>	
			             </tr>
			        </#list>
            	</#if>
            </table>
	    </div>
	</div>
	
	<@pager  pagination=page  formId="sysRoleForm" />  
	
</form>

</@backend>

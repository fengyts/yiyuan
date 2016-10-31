<#include "/common/common.ftl" />
<@backend title="系统用户管理" 
js=[
'/statics/plugin/layer/layer.min.js',
'/statics/backend/sys/sysUser.js'
] css=[
]>

<form class="jqtransform" method="post" id="sysUserForm" action="${domain}/sys/sysUser/list.htm">
    <div >
	   <div>	      
	       <table class="form_table" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td>登录账号:</td>
                  <td>
                  	<input type="text" name="sysUser" class="input-text lh25" size="20" value='${sysUserDO.loginName}'>
                  </td>                 
                  <td>姓名:</td>
                  <td>
                  	<input type="text" name="sysUser" class="input-text lh25" size="20" value='${sysUserDO.userName}'>
                  </td>
                  <td>状态:</td>
	              <td>
	                 <span class="fl">
	                   <div class="select_border"> 
		                     <div class="select_containers "> 
		                     <select name="status" class="select"> 
				                 <option value=''      <#if  sysUserDO.status==null>selected='selected'</#if>>全部</option> 
			                     <option value="true"  <#if  sysUserDO.status??&&sysUserDO.status?string=='true'>selected='selected'</#if>>有效</option> 
			                     <option value="false" <#if  sysUserDO.status??&&sysUserDO.status?string=='false'>selected='selected'</#if>>无效</option> 
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
                 <input class="btn btn82 btn_res " type="button" value="重置"  onclick="dataReset('sysUserForm')" />
                 <input id="sysUserAddbtn" class="btn btn82 btn_add addcatabtn" type="button" value="新增" />
              </div>
        </div>
    </div>
    
    <div id="table" class="mt10">
        <div class="box span10 oh">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                      <th width="50">登录账号</th>	
	                  <th width="50">姓名</th>
	                  <th width="100">手机号</th>
	                  <th width="100">email</th>
	                  <th width="50">状态</th>
	                  <th width="200">创建时间</th>
	                  <th width="200">更新时间</th>
					  <th width="50">操作</th>
                </tr>
            	<#if page.list?default([])?size!=0>
		            <#list page.getList() as sysUser>
		                <tr class="tr">
				              <td class="td_center">${sysUser.loginName}</td>
		 					  <td class="td_center">${sysUser.userName}</td>
				              <td class="td_center">${sysUser.mobile}</td>		            
				              <td class="td_center">${sysUser.email}</td>		            
				              <td class="td_center"><#assign sta="${sysUser.status}" /><#if sta=='true'>有效<#else>无效</#if></td>
				              <td class="td_center">${sysUser.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
		                      <td class="td_center">${sysUser.modifyTime?string("yyyy-MM-dd HH:mm:ss")}</td>  
				              <td class="td_center">
				              	<a href="javascript:void(0);" class="editcatabtn sysUserEditbtn" param='${sysUser.id}'>[编辑]</a> &nbsp;
				              	<a href="javascript:void(0);" class="editcatabtn journalReview" param='${sysUser.id}'>[日志]</a>
				              </td>	
			             </tr>
			        </#list>
            	</#if>
            </table>
	    </div>
	</div>
	
	<@pager  pagination=page  formId="sysUserForm" />  
	
</form>

</@backend>

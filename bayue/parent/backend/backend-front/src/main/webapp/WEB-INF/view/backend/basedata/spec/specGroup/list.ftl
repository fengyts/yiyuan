<#include "/common/common.ftl"/>
<@backend title="规格组管理" 
js=[
'/statics/plugin/layer/layer.min.js',
'/statics/backend/basedata/specGroup.js'
] css=[
]>

<form class="jqtransform" method="post" id="specGroupForm" action="${domain}/basedata/specGroup/list.htm">
    <div >
	   <div>	      
	       <table class="form_table" border="0" cellpadding="0" cellspacing="0">
                <tr>                 
                  <td>规格组</td>
                  <td>
                  	<input type="text" name="alias" class="input-text lh25" size="20" value='${specGroupDO.alias}'>
                  </td>
                  <td>状态</td>
	              <td>
	                 <span class="fl">
	                   <div class="select_border"> 
	                     <div class="select_containers "> 
	                     <select name="status" class="select"> 
			                 <option value=''      <#if  specGroupDO.status==null>selected='selected'</#if>>全部</option> 
		                     <option value="true"  <#if  specGroupDO.status??&&specGroupDO.status?string=='true'>selected='selected'</#if>>有效</option> 
		                     <option value="false" <#if  specGroupDO.status??&&specGroupDO.status?string=='false'>selected='selected'</#if>>无效</option> 
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
                 <input class="btn btn82 btn_res " type="button" value="重置"  onclick="dataReset('specGroupForm')" />
                 <input id="specGroupAddbtn" class="btn btn82 btn_add addcatabtn" type="button" value="新增" />
              </div>
        </div>
    </div>
    
    <div id="table" class="mt10">
        <div class="box span10 oh">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                      <th width="30">ID</th>
                      <#--<th width="50">编号</th>-->	
	                  <th width="100">规格组</th>
	                  <th width="150">规格组别名</th>
	                  <th width="50">排序值</th>
	                  <th width="50">状态</th>
	                  <th width="200">创建时间</th>
	                  <th width="200">更新时间</th>
					  <th width="100">备注</th>
					  <th width="50">操作</th>
                </tr>
            	<#if page.list?default([])?size!=0>
		            <#list page.getList() as specGroup>
		                <tr class="tr">
				              <td class="td_center">${specGroup.id}</td>
				              <#--<td class="td_center">${specGroup.code}</td>-->
		 					  <td class="td_center">${specGroup.name}</td>
		 					  <td class="td_center">${specGroup.alias}</td>
		 					  <td class="td_center">${specGroup.sort}</td>
				              <td class="td_center"><#assign sta="${specGroup.status}" /><#if sta=='true'>有效<#else>无效</#if></td>
				              <td class="td_center">${specGroup.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
		                      <td class="td_center">${specGroup.modifyTime?string("yyyy-MM-dd HH:mm:ss")}</td>  
		                      <td class="td_center">${specGroup.remark}</td>					  
				              <td class="td_center">
				              	<a href="javascript:void(0);" class="editcatabtn specGroupEditbtn" param='${specGroup.id}'>[编辑]</a> &nbsp;
				              	<a href="javascript:void(0);" class="editcatabtn journalReview" param='${specGroup.id}'>[日志]</a>
				              </td>
			             </tr>
			        </#list>
            	</#if>
            </table>
	    </div>
	</div>
	
	<@pager  pagination=page  formId="specGroupForm" />  
	
</form>

</@backend>

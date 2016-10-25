<#include "/common/common.ftl"/>
<@backend title="规格管理" 
js=[
'/statics/plugin/layer/layer.min.js',
'/statics/backend/basedata/listSpec.js'
] css=[
]>

<form class="jqtransform" method="post" id="specListForm" action="${domain}/basedata/specGroup/listSpec.htm">
    <div >
	   <div>	      
	       <table class="form_table" border="0" cellpadding="0" cellspacing="0">
                <tr>                 
                  <td>规格</td>
                  <td>
                  	<input type="text" name="spec" class="input-text lh25" size="20" value='${specDO.spec}'>
                  </td>
                  <td>状态</td>
	              <td>
	                 <span class="fl">
	                   <div class="select_border"> 
	                     <div class="select_containers "> 
	                     <select name="status" class="select"> 
			                 <option value=''      <#if  specDO.status==null>selected='selected'</#if>>全部</option> 
		                     <option value="true"  <#if  specDO.status??&&specDO.status?string=='true'>selected='selected'</#if>>有效</option> 
		                     <option value="false" <#if  specDO.status??&&specDO.status?string=='false'>selected='selected'</#if>>无效</option> 
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
                 <input class="btn btn82 btn_res " type="button" value="重置"  onclick="dataReset('specListForm')" />
                 <input id="cancelbtn" class="btn btn82 btn_del closebtn" type="button" value="取消" name="button">
                 <input id="selectSpecConfirmBtn" class="btn btn82 btn_add addcatabtn" type="button" value="确定" />
              </div>
        </div>
    </div>
    
    <div id="tableSpec" class="mt10">
        <div class="box span10 oh">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                	<th width="20"></th>	
	                <th width="30">ID</th>
	                <th width="50">规格编号</th>	
		            <th width="50">规格</th>
		            <th width="50">排序值</th>
		            <th width="50">状态</th>
					<th width="100">备注</th>
                </tr>
            	<#if page.list?default([])?size!=0>
		            <#list page.getList() as spec>
		                <tr class="tr">
			                <td class="td_center">
			                	<input type="checkbox" name="specIds" value="${spec.id}"/>
			                </td>	
					        <td class="td_center">${spec.id}</td>
					        <td class="td_center">${spec.code}</td>
			 				<td class="td_center">${spec.spec}</td>
			 				<td class="td_center">${spec.sort}</td>
					        <td class="td_center"><#assign sta="${spec.status}" /><#if sta=='true'>有效<#else>无效</#if></td>
			                <td class="td_center">${spec.remark}</td>					  
			            </tr>
			        </#list>
            	</#if>
            </table>
	    </div>
	</div>
	
	<@pager  pagination=page  formId="specListForm" />  
	
</form>

</@backend>

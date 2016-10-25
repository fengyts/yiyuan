<#include "/common/common.ftl"/>
<@backend title="违禁词管理" 
js=[
'/statics/plugin/layer/layer.min.js',
'/statics/backend/basedata/dictionary.js'
] css=[
]>

<form class="jqtransform" method="post" id="dictionaryForm" action="${domain}/basedata/dictionary/list.htm">
    <div >
	   <div>	      
	       <table class="form_table" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td>名称:</td>
                  <td><input type="text" name="name" class="input-text lh25" size="20" value='${dictionaryDO.name}'></td>
                  <td>状态:</td>
	              <td>
	                 <span class="fl">
	                   <div class="select_border"> 
	                     <div class="select_containers "> 
	                     <select name="status" class="select"> 
			                 <option value=''      <#if  dictionaryDO.status==null>selected='selected'</#if>>全部</option> 
		                     <option value="true"  <#if  dictionaryDO.status??&&dictionaryDO.status?string=='true'>selected='selected'</#if>>有效</option> 
		                     <option value="false" <#if  dictionaryDO.status??&&dictionaryDO.status?string=='false'>selected='selected'</#if>>无效</option> 
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
                 <input class="btn btn82 btn_res " type="button" value="重置"  onclick="dataReset('dictionaryForm')" />
                 <input id="dictionaryAddbtn" class="btn btn82 btn_add addcatabtn" type="button" value="新增" />
              </div>
        </div>
    </div>
    
    <div id="table" class="mt10">
        <div class="box span10 oh">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                      <th width="50">ID</th>
	                  <th width="150">名称</th>
	                  <th width="50">code</th>
	                  <th width="80">状态</th>
	                  <th width="50">排序值</th>
	                  <th width="150">创建时间</th>
	                  <th width="150">更新时间</th>
					  <th width="100">备注</th>
					  <th width="50">操作</th>
                </tr>
            	<#if page.list?default([])?size!=0>
		            <#list page.getList() as dictionaryDO>
		                <tr class="tr" >
				              <td class="td_center">${dictionaryDO.id}</td>
				              <td class="td_center">${dictionaryDO.name}</td>
		 					  <td class="td_center">${dictionaryDO.code}</td>
				              <td class="td_center"><#assign sta="${dictionaryDO.status}" /><#if sta=='true'>有效<#else>无效</#if></td>
				              <td class="td_center">${dictionaryDO.sortNo}</td>		            
				              <td class="td_center">${dictionaryDO.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
		                      <td class="td_center">${dictionaryDO.modifyTime?string("yyyy-MM-dd HH:mm:ss")}</td>  
		                      <td class="td_center">${dictionaryDO.remark}</td>					  
				              <td class="td_center">
				              	<a href="javascript:void(0);" class="editcatabtn dictionaryEditbtn" param='${dictionaryDO.id}'>[编辑]</a> &nbsp;&nbsp;&nbsp;
				              	<a href="javascript:void(0);" class="editcatabtn journalReview" param='${dictionaryDO.id}'>[日志]</a></td>	
			             </tr>
			        </#list>
            	</#if>
            </table>
	    </div>
	</div>
	
	<@pager  pagination=page  formId="dictionaryForm" />  
	
</form>

</@backend>

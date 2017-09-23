<#include "/common/common.ftl"/> 
<@backend title="商品列表"
js=[]
css=[]>

<form class="jqtransform" method="post" id="itemForm" action="${domain}/item/itemDetail/listDetail.htm">
    <div >
	   <div>	      
	       <table class="form_table" border="0" cellpadding="0" cellspacing="0">
		       <tr>                 
		           <td>spu</td>
		           <td>
		           		<input type="text" id="spu" name="spu" class="input-text lh25" size="20" value='${itemDO.spu}'>
		           </td>
		           <td>prdid</td>
		           <td>
		           		<input type="text" id="prdid" name="prdid" class="input-text lh25" size="20" value='${itemDO.prdid}'>
		           </td>
	           </tr>
		   </table>
	   </div>
	   
	   
	   <div class="box_bottom pb5 pt5 pr10" style="border-top:1px solid #dadada;">
              <div class="search_bar_btn" style="text-align:center;">
                 <input class="btn btn82 btn_search" type="submit" value="查询"/ >
                 <input class="btn btn82 btn_res " type="button" value="重置"  onclick="dataReset('specForm')" />
                 <input id="specAddbtn" class="btn btn82 btn_add addcatabtn" type="button" value="新增" />
              </div>
        </div>
        
        <div id="table" class="mt10">
	        <div class="box span10 oh">
	            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
	            	<tr>
	                	<th width="30">ID</th>
	                	<th width="50">操作</th>
	                </tr>
	                <#if page.list?default([])?size!=0>
		            <#list page.getList() as spec>
			            <tr class="tr" >
				            <td class="td_center">${item.id}</td>
				            <td class="td_center">
					            <a href="javascript:void(0);" class="editcatabtn specEditbtn" param='${spec.id}'>[编辑]</a> &nbsp;
					            <a href="javascript:void(0);" class="editcatabtn journalReview" param='${spec.id}'>[日志]</a>
				            </td>
				        </tr>
			        </#list>
	        		</#if>
	            </table>
	         </div>
        </div>
	   
	</div>
	
	
	
	<@pager  pagination=page  formId="itemForm" />  
</form>	     

</@backend>
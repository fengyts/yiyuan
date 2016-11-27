<#include "/common/common.ftl"/>
<@backend title=""
js=[]
css=[]
>

<form method="post" action="${domain}/item/list.htm" id="itemSearchForm">
	<div id="search_bar" class="mt10">
		<div class="box">
			<div class="box_border">
			<table class="form_table" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>spu</td>
					<td>
						<input class="input-text lh25" size="20" id="spu" name="spu" value="${infoDO.spu}">
					</td>
				</tr>
				<tr>
					<td class="td_right"><span class="requiredField">*</span>商品类别：</td>
					<td class="">
					<span class="fl"> 
						<#if item.info.id?exists>
								<input type="hidden" id="spuId" name="infoId" value="${item.info.id}" />
						</#if>
						<select name="largeId" class="select2" id="largeId" style="width:200px; margin-left: 1px">
								<option value="">--请选择一级分类--</option>
							<#list categoryFirList as category>
								<option value="${category.id}"  <#if item.info.largeId==category.id> selected</#if> >${category.name} </option>
							</#list>
						</select>
						<select name="smallId" class="select2" id="smallId" style="width:250px; margin-left: 1px" >
							<option value="">--请选择二级分类--</option>
						</select>
					</span>
					</td>
				</tr>
			</table>
			</div>
		</div>
	</div>
	
	<div  class="box">
		<div id="table" class="mt10">
	        <div class="box span10 oh">
	            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
	            	<tr>
	            	</tr>
	            </table>
	        </div>
	    </div>
	</div>
	
	
</form>

</@backend>
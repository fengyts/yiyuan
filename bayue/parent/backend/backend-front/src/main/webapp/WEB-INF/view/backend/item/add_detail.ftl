<style type="text/css">
	.ke-dialog{
		top:15%;
	}
</style>
<input type="hidden" value="${sessionId}" id="sessionId" />

<div class="box_border">
	<div class="box_top">
		<b class="pl15">商品信息</b>
	</div>
	
	<div class="box_center">
		<table class="input tabContent">
				<tr>
					<td class="td_right">prdid:</td>
					<td >
						<span>系统自动生成</span>
					</td>	
					<td rowspan=5  class="td_right"><span class="requiredField">*</span>规格维度:</td>
					<td rowspan=5  style="width:40%">
					 	<ul>
					 	  <#if detailSpecList?default([])?size !=0>
					 	  <#list detailSpecList as t>
					 	  	<li>
					 	  		<#if "${t.specGroupId}" =="-1">
					 	  			均码  
					 	  		<#else>
					 	  		${t.specGroupName}:${t.specName}
					 	  		</#if>
					 	  		
					 	  	</li>
					 	  </#list>
					 	  <#else>
					 	    <li>均码  </li>
					 	  </#if>
					 	</ul>
					</td>
				</tr>
				<tr>
					 <td class="td_right"><span class="requiredField">*</span>商品名称:</td>
					 <td>
					 	<input type="text" class="input-text lh30" size="60" name="mainTitle" id="mainTitle" maxlength=60 onMouseOver="this.title=this.value" />
					 	<input type="hidden" name="itemTitle" id="itemTitle" maxlength=60 />
					 </td>
				</tr>
				<tr>
					 <td class="td_right"><span class="requiredField">*</span>副标题:</td> 
					 <td><input type="text" class="input-text lh30" size="60" name="subTitle" id="mainTitle" maxlength=60 onMouseOver="this.title=this.value" /></td>
				</tr>
				<tr>
					<td class="td_right">
						<#--<span class="requiredField">*</span>-->
						条形码:
					</td>
					<td style="width:45%">
						<input type="text" class="input-text lh30" size="25" id="barcode" name="barcode" maxlength=9 />
					</td>
				</tr>
				<tr>
					 <td class="td_right">备注:</td>
					 <td ><input type="text" class="input-text lh30" size="60" name="detailRemark" id="detailRemark" maxlength=60 onMouseOver="this.title=this.value" /></td>
				</tr>
				
		</table>		
	</div>
</div>	
	
<div class="box_border">
	<div class="box_top">
		<b class="pl15">基本信息</b>
	</div>
	
	<div class="box_center">
		<table class="input tabContent">
			<tr>
				<#--
				<td class="td_right" rowspan="2"><span class="requiredField">*</span>商品卖点(副标题):</td>
				<td rowspan="2">
					<textarea name="detail.subTitle" cols="30" rows="3" id="subTitle" maxlength=100 onMouseOver="this.title=this.value" >${((detail.subTitle))?xhtml}</textarea>
				</td>
				-->
				<td class="td_right"><span class="requiredField">*</span>市场价:</td>
				<td>
					<input type="text" id="basicPrice" name="basicPrice" maxlength=9 class="input-text lh30" size="10">
				</td>
				<td class="td_right">生产厂家:</td>
				<td colspan='3'>
					<input type="text" name="manufacturer" maxlength=100 class="input-text lh30" size="25" onMouseOver="this.title=this.value" />
				</td>
			</tr>
			<tr>
				<td class="td_right"><span class="requiredField">*</span>商品类型:</td>
				<td class="">
				  <span class="fl">
				  	<select	name="itemType" class="select">
						<option value="1" <#if "${itemType}"=="1"> selected</#if>>正常商品</option>
					</select>
				  </span>
				</td>
				<td class="td_right">体积:</td>
				<td class="" colspan='3'>
				<input type="text" class="input-text lh30" size="5" style="line-height:normal;text-align:center;" name="volumeLength" id="volumeLength" maxlength=9 placeholder="长" />
				<input type="text" class="input-text lh30" size="5"	style="line-height:normal;text-align:center;" name="volumeWidth" id="volumeWidth" maxlength=9 placeholder="宽" />
				<input type="text" class="input-text lh30" size="5"	style="line-height:normal;text-align:center;" name="volumeHigh" id="volumeHigh" maxlength=9 placeholder="高" />&nbsp;cm
				</td>
			</tr>
			<tr>
				<td class="td_right">
					毛重:
				</td>
				<td>
					<input type="text" class="input-text lh30" size="5" id="weight" maxlength=9 name="weight" />&nbsp;g
				</td>
				<td class="td_right">净重:</td>
				<td>
					<input type="text" class="input-text lh30" size="5" value="${detail.weightNet}" id="weightNet" maxlength=9 name="detail.weightNet" />&nbsp;g
				</td>
				<#--
				<td class="td_right">箱规:</td>
				<td>
					<input type="text" class="input-text lh30" size="25" name="detail.cartonSpec" value="${((detail.cartonSpec))?xhtml}" id="cartonSpec" maxlength=60 onMouseOver="this.title=this.value" />
				</td>
				-->
			</tr>
			<tr>
				<td class="td_right">规格:</td>
				<td >
					<input type="text" class="input-text lh30" size="25" name="specifications" id="specifications" maxlength=60 onMouseOver="this.title=this.value" />
				</td>
				<td class="td_right">无理由退货天数:</td>
				<td >
					<input type="text" class="input-text lh30" size="25" name="returnDays" id="returnDays" maxlength=60 onMouseOver="this.title=this.value" />
				</td>
				<#--
				<td class="td_right">箱规:</td>
				<td>
					<input type="text" class="input-text lh30" size="25" name="cartonSpec" id="cartonSpec" maxlength=60 onMouseOver="this.title=this.value" />
				</td>
				-->	
			</tr>
		</table>
		</div>
	</div>
	
	<#include "/backend/item/subpages/add_attribute.ftl">
	
	<div class="box_border">
		<div class="box_top">
			<b class="pl15">
			图片与详情
			</b>
		</div>
		<#--
		<#include "/backend/item/subpages/add_pictures.ftl">
		-->
		
		<#include "/backend/item/uploadImg.ftl"/>
		
		<#--
		<#include "/backend/item/subpages/add_description.ftl">
		<input type="button" id="copyPicAndDetail"   value="复制" class="ext_btn ext_btn_submit m10" " />
		<span>复制图片、详情到不同的Prd下面</span>
		-->
		
	</div>
	
	
	<#--
	<div class="box_border">
		<div class="box_top">
			<b class="pl15">供应商信息</b>
		</div>
		<div class="box_center">
		<div class="">
			如果prdid已经关联了自营（美囤妈妈）的商家，就不能再选择了
			<input type="button" id="selectSupplierbtn"  value="选择供应商" class="ext_btn ext_btn_submit m10" status="${hasMeitunSeller}">
		</div>
		<table class="input tabContent" id="suppListTable">
			<tr>
				<th width="60">SKU编号</th>
				<th width="200">商家名称</th>
				<th width="100">商家商品码（条形码）</th>
				<th width="60">市场价</th>
				<th width="60">试用邮费</th>
				<th width="60">上下架</th>
				<th width="60">类型</th>
				<th width="60" style="text-align:left"><input type="checkbox" id="choosePullFlag">拉取</th>
				<th width="60" style="text-align:left"><input type="checkbox" id="chooseNoRecommend">推荐</th>
				<th width="100" style="text-align:left"><input type="checkbox" id="chooseItemStatus">批量上架</th>
			</tr>
			<#list detailSkuList as l >
			<tr class="skuList">
			<td width="60">${l.sku}</td>
				<input type="hidden" name="skuId" value="${l.id}" />
				<input type="hidden" name="supplierId" value="${l.spId}" />
				<td width="200">${l.spName}</td>
				<td width="200">${l.barcode}</td>
				<td width="60"><input type="text" class="input-text lh30 ml20" size="5" value="${l.basicPrice}" maxlength=9 name="basicPrice" /></td>
				<td width="60">
				<input type="hidden" class="input-text lh30 ml20" size="5" value="${l.sort}" maxlength=4 name="sort" />
				<input type="text" class="input-text lh30 ml20" size="5" value="${l.postage}" maxlength=4 name="postage" />
				</td>
				<td width="60">
					<#if "${l.status}"=="0"> 未上架</#if>
					<#if "${l.status}"=="1"> 已上架</#if>
				    <#if "${l.status}"=="2"> 作废</#if>
				</td>
				<td width="60">
					<#if "${l.saleType}"=="0">
						美囤妈妈
					</#if>
					<#if "${l.saleType}"=="1">
						商家
					</#if>
					
				</td>
				<td width="60"> <input type="checkbox" name="pullFlag" <#if "${l.pullFlag}"=="0"> checked </#if> value="${l.pullFlag}" >拉取</td>
				<td width="60"> <input type="checkbox" name="noRecommend" <#if "${l.noRecommend}"=="1"> checked </#if> value="${l.noRecommend}" >推荐</td>
				操作类型 自营的，可以添加供应商
			    <td width="100">
			        <#if "${l.status}"=="2"><#else>
			         <input type="checkbox" name="status" <#if "${l.status}"=="1"> checked </#if> value="${l.status}" >上架
				    	<a id="${l.id}" href="javascript:void(0);" class="cancelSkuBtn">[作废]</a>
					   	<#if l.saleType==0 >
							<a id="${l.id}" href="javascript:void(0);" class="querySkuSupplier">[供应商]</a>
						<#else>
					  </#if>
					  <#if detail.wavesSign==2 > 
				   	 </br>
						<a id="${l.id}" href="javascript:void(0);" class="querySkuArtNumber">[备案信息]</a>
					 </#if> 
			        </#if>		    
				</td>
			</tr>	
			</#list>
			
		</table>
	 </div>
	 -->
	 
	 
</div>

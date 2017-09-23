<div class="box_border">
	<div class="box_top">
		<b class="pl15">商品SPU信息</b><font color="red">(带"*"为必填项)</font>
		<#--
		<span style="float: right;">
            <input value="checkbox" type="checkbox">加入常用功能&nbsp;&nbsp;&nbsp;
        </span>
        -->
	</div>
	<!-- 基础信息  -->
	<div class="box_center">
		<table class="input commContent">
			<tr>
				<td class="td_right">
					SPU：
				</td>
				<td class="" colspan=1>
					<#if item.info.spu?exists>
						${item.info.spu}
					<#else>
						<span>系统自动生成</span>
					</#if>
				</td>
			</tr>
			<!--初始化列表查询-->
			<input type="hidden" id="largeIdHidden" value="${item.info.largeId}" />
			<input type="hidden" id="smallIdHidden" value="${item.info.smallId}" />
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
			<tr>
				<td class="td_right">
					<span class="requiredField">*</span>单位：
				</td>
				<td class="">
					<select name="unitId" class="select2" id="unitId"  style="width:150px; margin-left:1px" >
						<option value="">--请选择单位--</option>
						<#list unitList as unit>
							<option value="${unit.id}"  <#if "${unit.id}"=="${item.info.unitId}">selected</#if> >${unit.name}</option>
						</#list>
				    </select>		
				</td>
			</tr>
			<tr>
				<td class="td_right">
					<span class="requiredField">*</span>SPU名称：
				</td>
				<td class="">
					<input type="text" name="mainTitle" id="mainTitle" value="${((item.info.mainTitle)!'')?xhtml}" 	class="input-text lh30" size="60" maxlength=60  onMouseOver="this.title=this.value" />
				</td>
			</tr>
			<tr>
				<td class="td_right">备注：</td>
				<td class="" colspan=3>
					<input type="text" id="spuRemark" value="${((item.info.remark))?xhtml}" name="spuRemark" class="input-text lh30" size="60" maxlength=60 onMouseOver="this.title=this.value" />
				</td>
			</tr>
		</table>
	</div>
</div>
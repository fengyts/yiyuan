<#include "/common/common1.ftl"/> 

<@backend title="" 
	js=[
	'/statics/plugin/jqgrid/js/jquery.jqGrid.min.js',
	'/statics/plugin/jqgrid/js/i18n/grid.locale-cn.js',
	'/statics/backend/basedata/frontCategory.js'
	] 
	css=[] 
>

<form id="inputForm" action="uploadFCate.htm" method="post" enctype="multipart/form-data">
	<div style="height:30px;"></div>
	<div style="border:1px solid #ccc ;">
		<div style="text-align:left; height:50px;">
			上传文件(<font color="red">请使用下载的模板上传</font>)：<input type="file" value="上传" id= "fcExcel" name="fcExcel" >
			<input type="button" value="上传"  class="ext_btn ext_btn_submit m10 dataFormSave" id="uploadFC">
		</div>
		<div style="text-align:left;height:30px;">
			导入模板：
			<#--
			<span style="display:none;">商家：</span>  
			<select name="supplierId" id="supplierId" style="border:1px solid #ccc ;display:none;">
				<option value=""  >--请选择供应商--</option>
				<#list supplierList as s>
					<option value="${s.id}">${s.name}</option>
				</#list>
			</select>
			<span style="display:none;">品牌:</span>
	   		<select name="brandId" id="brandId" style="border:1px solid #ccc ;display:none;">
				<option value="">--请选择品牌--</option>
				<#list brandList as brand>
					<option value="${brand.id}">${brand.name}</option>
				</#list>
		    </select>
		    -->		
			<a id="downTemplateBtn" href="#">[下载模板]</a> <span class="color307fb1">请先导出完整的excel模板.</span>
			<input type="hidden" value="fcExcel" name="fieName" />
		</div>
		<div class="color307fb1">
			<h3>警告：请仔细阅读以下的使用说明：</h3>
			<ul>
				<#--
				<li>1.基础数据（如：大中小类，单位，品牌，规格等）在“基础数据页”有显示，请以此为准；如发现缺少需要的数据，请联系美囤联系人添加</li>
				<li>2.用户输入“**名称”后，灰色栏位的“**ID”数值会根据“基础资料页”的对应关系自动带出，如果灰色底纹栏位显示“*N/A”，请检查数据是否准确</li>
				<li>3.灰色底纹的范例数据中带有公式（vlookup），请不要删除，下拉后对应的公式会复制到下一单元格</li>
				<li>4.商品无销售规格组及规格，请默认空</li>
				<li>5.商家必须指定具体的供应商，不能使用美囤妈妈做商家</li>
				<li>6.Excel表头(第一行)的文字不能被修改</li>
				<li>7.Excel的行数不能超过1000（除了第一行表头外）,excel大小不能超过5M</li>
				-->
			</ul>
		</div>
	</div>	
</form>
</@backend>

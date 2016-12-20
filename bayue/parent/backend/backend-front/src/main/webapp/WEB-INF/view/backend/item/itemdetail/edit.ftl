<#include "/common/common.ftl" />

<@backend title="商品编辑" 
js=[
'/statics/plugin/layui-v1.0.2/layui/layui.js',
'/statics/plugin/bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js',
'/statics/plugin/My97DatePicker/WdatePicker.js',
'/statics/plugin/editor/kindeditor-all-min.js',
'/statics/common/common-js/editorUtil.js',
'/statics/plugin/baidu_webuploader/webuploader.min.js',
'/statics/common/imgupload/upload.js',
'/statics/backend/item/itemDetail.js'
]
css=[
'/statics/plugin/bootstrap/bootstrap-3.3.5-dist/css/bootstrap.min.css',
'/statics/plugin/baidu_webuploader/webuploader.css',
'/statics/plugin/editor/themes/custom/style.css',
'/statics/plugin/baidu_webuploader/image-upload/style.css'
]>


<div class="panel-body box_border">
<input type="hidden" id="listIframeName" value="${listIframeName}">
<form id="itemDetailEditForm" action="" class="form-horizontal dr-form-bordered">
	<div style="display:none;">
		<input type="hidden" id="id" name="id" value="${detailDO.id}" />
	</div>
	<div class="form-group">
		<div class="col-md-4" style="padding-left:50px;color:red;">注：标注*为必填项</div>
	</div>
	
	<hr/>
	<div class="box_top" style="margin-bottom:20px;">
		<b class="pl15">SPU信息</b>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">SPU<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<div class="input-group">
				<input type="text" class="form-control" readonly="readonly" id="spu" name="spu" placeholder="请选择spu信息" value="${detailDO.spu}">
				<input type="hidden" id="itemId" name="itemId" value="${detailDO.itemId}"/>
				<span class="btn btn-default btn-sm input-group-addon" id="selectSPU">
					<span class="glyphicon glyphicon-search"></span>
					选择SPU
				</span>
			</div>
		</div>
		<label class="col-md-2 control-label">SPU名称<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="mainTitle" name="mainTitle" readonly="readonly" value="${detailDO.mainTitle}"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">大类<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="largeCateName" name="largeCateName" readonly="readonly" value="${detailDO.largeCateName}"/>
			<input type="hidden" id="largeId" name="largeId" value="${detailDO.largeId}" />
		</div>
		<label class="col-md-2 control-label">小类<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="smallCateName" name="smallCateName" readonly="readonly" value="${detailDO.smallCateName}"/>
			<input type="hidden" id="smallId" name="smallId" value="${detailDO.smallId}" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">单位<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="unitName" name="unitName" readonly="readonly" value="${detailDO.unitName}"/>
			<input type="hidden" id="unitId" name="unitId" value="${detailDO.unitId}"/>
		</div>
	</div>
	
	<div class="box_top" style="margin-bottom:20px;">
		<b class="pl15">商品基础信息</b>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">PRDID<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="prdid" name="prdid" value="${detailDO.prdid}" placeholder="保存时由系统生成" readonly="readonly"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">商品名称<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="itemTitle" name="itemTitle" value="${detailDO.itemTitle}"/>
		</div>
		<label class="col-md-2 control-label">副标题<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="subTitle" name="subTitle" value="${detailDO.subTitle}"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">市场价<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="basicPrice" name="basicPrice" value="${detailDO.basicPrice}"/>
		</div>
		<label class="col-md-2 control-label">状态<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<select class="form-control" id="status" name="status">
				<#--
				<option value="0">未上架</option>
				<option value="1">已上架</option>
				<option value="2">作废</option>
				-->
				<#list itemStatus as sta>
					<option value = '${sta.code}' <#if sta.code==detailDO.status>selected</#if> >${sta.desc}</option>
				</#list>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">商品类型</label>
		<div class="col-md-4">
			<select class="form-control" id="itemType" name="itemType">
				<#--
				<option value="1">正常商品</option>
				<option value="2">服务商品</option>
				<option value="3">二手商品</option>
				-->
				<#list itemType as stt>
					<option value = '${stt.code}' <#if stt.code==detailDO.itemType>selected</#if> >${stt.desc}</option>
				</#list>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">备注</label>
		<div class="col-md-4">
			<textarea class="form-control" rows="2"  id="remark" name="remark" value="${detailDO.remark}"></textarea>
		</div>
	</div>
	<hr/>
	<div class="form-group">
		<label class="control-label">
			<button type="button" class="btn btn-primary ml15" style="margin-bottom:10px;" id="associateSpecGroup">关联规格信息</button>
		</label>
		<div class="col-md-12">
		<table class="list_table" id="associateSpecGroupDataList">
			<thead>
		    	<tr>
		    		<th class="display"></th>
				    <th class="text-center">规格组名称</th>
				    <th class="text-center">规格组别名</th>
				    <th class="text-center col-md-2">规格组排序</th>
				    <th class="text-center col-xs-1">操作</th>
			    </tr>
			</thead>
			<tbody>
				<#list detailSpecGroups as specs>
					<tr class="tr">
						<td style="display:none;">${specs.specGroupId}</td>
						<td class="text-center">${specs.groupName}</td>
						<td class="text-center">${specs.groupNameAlias}</td>
						<td class="text-center">${specs.sort}</td>
						<td class="text-center"><button type='button' class='btn btn-danger delSpecGroup'>删除</button></td>
					</tr>
				</#list>
			</tbody>
		</table>
		</div>
	</div>
	
	<#-- 商品上传图片 -->
	<div class="form-group">
			<#include "/common/upload_picture.ftl"/>
		<div class="col-md-12">
		</div>
	</div>
	
	<div class="box_top" style="margin-bottom:5px;">
		<b class="pl15">商品描述信息</b>
	</div>
	<div class="form-group">
		<div class="col-md-12">
			<#include "/common/description.ftl"/>
		</div>
	</div>
	
	<hr/>
	<div>
		<div class="col-sm-12 panel-toolbar text-left dr-slash-text" id="operateBtn">
			<div class="col-md-4"></div>
			<div>
				<a href="javascript:void(0);" class="btn btn-info" id="cancelTabItemEditBtn">取消</a>
				<a href="javascript:void(0);" class="btn btn-primary" id="updateItemDetailBtn">保存</a>
			</div>
		</div>
	</div>
	
</form>
</div>

</@backend>

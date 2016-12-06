<#include "/common/common.ftl" />

<@backend title="商品新增spu" 
js=[
'/statics/plugin/layui-v1.0.2/layui/layui.js',
'/statics/plugin/select2/js/select2.js',
'/statics/plugin/select2/js/select2Util.js',
'/statics/plugin/select2/js/select2_locale_zh-CN.js',
'/statics/plugin/bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js',
'/statics/plugin/My97DatePicker/WdatePicker.js',
'/statics/backend/item/itemDetail.js'
]
css=[
'/statics/common/common-css/common.css',
'/statics/common/common-css/style.css',
'/statics/plugin/bootstrap/bootstrap-3.3.5-dist/css/bootstrap.min.css',
'/statics/plugin/select2/css/select2.css'
]>


<div class="panel-body box_border">
<form id="itemInfoAddForm" action="" class="form-horizontal dr-form-bordered">
	<div style="display:none;">
		<#if infoDO.id?exists>
			<input type="hidden" id="id" name="id" value="${detailDO.id}" />
		</#if>
	</div>
	<div class="form-group">
		<div class="col-md-4" style="padding-left:50px;color:red;">注：标注*为必填项</div>
	</div>
	<hr/>
	
	<div class="form-group">
		<label class="col-md-2 control-label">SPU</label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="spu" name="spu" value="${detailDO.spu}" placeholder="保存时由系统生成" readonly="readonly"/>
		</div>
		<label class="col-md-2 control-label">PRDID</label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="prdid" name="prdid" value="${detailDO.prdid}" placeholder="保存时由系统生成" readonly="readonly"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">SPU名称<span class="dr-asterisk">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="mainTitle" name="mainTitle" value="${detailDO.mainTitle}"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">备注</label>
		<div class="col-md-4">
			<textarea class="form-control" rows="3"  id="remark" name="remark" value="${detailDO.remark}"></textarea>
		</div>
	</div>
	
	<hr/>
	<div>
		<div class="col-sm-12 panel-toolbar text-left dr-slash-text" id="operateBtn">
			<a href="javascript:void(0);" class="btn btn-warning"  onclick="cancel();" id="cancelBtn">取消</a>
			<a href="javascript:void(0);" class="btn btn-primary" id="saveBtn">保存</a>
		</div>
	</div>
	
</form>
</div>

</@backend>

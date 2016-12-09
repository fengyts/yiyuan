<#include "/common/common.ftl" />

<@backend title="商品新增" 
js=[
'/statics/plugin/layui-v1.0.2/layui/layui.js',
'/statics/plugin/bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js',
'/statics/plugin/My97DatePicker/WdatePicker.js',
'/statics/backend/item/itemDetail.js'
]
css=[
'/statics/common/common-css/common.css',
'/statics/common/common-css/style.css',
'/statics/plugin/bootstrap/bootstrap-3.3.5-dist/css/bootstrap.min.css'
]>


<div class="panel-body box_border">
<form id="itemInfoAddForm" action="" class="form-horizontal dr-form-bordered">
	<div style="display:none;">
		<input type="hidden" id="id" name="id" value="${detailDO.id}" />
	</div>
	<div class="form-group">
		<div class="col-md-4" style="padding-left:50px;color:red;">注：标注*为必填项</div>
	</div>
	<hr/>
	
	<div class="form-group">
		<label class="col-md-2 control-label">SPU<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<div class="input-group">
				<input type="text"  class="form-control" readonly="readonly" id="spu" name="spu" value="${detailDO.spu}">
				<input type="hidden" id="itemId" name="itemId" value="${detailDO.itemId}"/>
				<span class="btn btn-default btn-sm input-group-addon" id="selectSPU">
					<span class="glyphicon glyphicon-search"></span>
					选择SPU
				</span>
			</div>
		</div>
		<label class="col-md-2 control-label">PRDID<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="prdid" name="prdid" value="${detailDO.prdid}" placeholder="保存时由系统生成" readonly="readonly"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">SPU名称<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="mainTitle" name="mainTitle" readonly="readonly" value="${detailDO.mainTitle}"/>
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
			<input type="text" class="form-control" id="basePrice" name="basePrice" value="${detailDO.basePrice}"/>
		</div>
		<label class="col-md-2 control-label">状态<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="status" name="status" value="${detailDO.status}"/>
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

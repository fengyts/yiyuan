<#include "/common/common1.ftl" />

<@backend title="首页轮播图新增" 
js=[
'/statics/backend/item/carousel.js'
]
css=[
'/statics/plugin/bootstrap/bootstrap-3.3.5-dist/css/bootstrap.min.css',
'/statics/common/common-css/common.css',
'/statics/common/common-css/style.css'
]>


<div class="panel-body box_border">
<form id="carouselAddForm" action="" class="form-horizontal dr-form-bordered">
	
	<div class="input-group">
		<div class="col-md-4" style="padding-left:50px;color:red;">注：标注*为必填项</div>
	</div>
	<hr/>
	<div class="form-group">
		<label class="control-label text-center col-xs-3">图片地址<span class="dr-asterisk">*</span></label>
		<div class="col-xs-8">
			<input type="text" class="form-control" id="picture" name="picture" value="${carouselDO.picture}"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label text-center col-xs-3">轮播图顺序<span class="dr-asterisk">*</span></label>
		<div class="col-xs-8">
			<input type="text" class="form-control" id="sort" name="sort" value="${carouselDO.sort}"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label text-center col-xs-3">图片链接地址<span class="dr-asterisk">*</span></label>
		<div class="col-xs-8">
			<input type="text" class="form-control" id="linkUrl" name="linkUrl" value="${carouselDO.linkUrl}"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label text-center col-xs-3">轮播图状态<span class="dr-asterisk">*</span></label>
		<div class="col-xs-8 radio">
			<label>
				<input type="radio" name="status" checked='checked'/>有效
			</label>&nbsp;&nbsp;&nbsp;&nbsp;
			<label>
				<input type="radio" name="status"/>无效
			</label>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label text-center col-xs-3">备注</label>
		<div class="col-xs-8">
			<input type="text" class="form-control" id="remark" name="remark" value="${carouselDO.remark}"/>
		</div>
	</div>
	
	<div class="col-sm-12 panel-toolbar text-left dr-slash-text" id="operateBtn">
		<a href="javascript:void(0);" class="btn btn-info"  onclick="cancel()" id="cancelBtn">取消</a>
		<a href="javascript:void(0);" class="btn btn-primary" id="saveBtn">保存</a>
	</div>
	
</form>
</div>

</@backend>

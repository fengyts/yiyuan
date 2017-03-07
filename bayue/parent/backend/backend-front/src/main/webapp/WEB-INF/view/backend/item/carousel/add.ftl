<#include "/common/common.ftl" />

<@backend title="首页轮播图新增" 
js=[
'/statics/plugin/layui-v1.0.2/layui/layui.js'
]
css=[
]>


<div class="panel-body box_border">
<form id="carouselAddForm" action="" class="form-horizontal dr-form-bordered">
	
	<div>
		<div class="col-sm-12 panel-toolbar text-left dr-slash-text" id="operateBtn">
			<a href="javascript:void(0);" class="btn btn-info"  onclick="cancel()" id="cancelBtn">取消</a>
			<a href="javascript:void(0);" class="btn btn-primary" id="saveBtn">保存</a>
		</div>
	</div>
	
</form>
</div>

</@backend>

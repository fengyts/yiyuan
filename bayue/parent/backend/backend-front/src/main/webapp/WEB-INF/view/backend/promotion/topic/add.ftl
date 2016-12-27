<#include "/common/common.ftl" />

<@backend title="商品新增" 
js=[
'/statics/plugin/My97DatePicker/WdatePicker.js',
'/statics/backend/promotion/topic.js'
]
css=[
'/statics/plugin/bootstrap/bootstrap-3.3.5-dist/css/bootstrap.min.css'
]>


<div class="panel-body box_border">
<input type="hidden" id="listIframeName" value="${listIframeName}">
<form id="itemDetailAddForm" action="" class="form-horizontal dr-form-bordered">
	<div style="display:none;">
		<input type="hidden" id="id" name="id" value="${detailDO.id}" />
	</div>
	<div class="form-group">
		<div class="col-md-4" style="padding-left:50px;color:red;">注：标注*为必填项</div>
	</div>
	
	<div class="box_top" style="margin-bottom:20px;">
		<b class="pl15">专题信息</b>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">专题名称<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="name" name="name" value="${topicDO.name}"/>
		</div>
		<label class="col-md-2 control-label">是否测试专场<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<select class="form-control" id="isTest" name="isTest">
				<option value='0'>否</option>
				<option value='1'>是</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">专题类型<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<select class="form-control" id="type" name="type">
				<#list topicType as type>
					<option value='${type.code}'>${type.desc}</option>
				</#list>
			</select>
		</div>
		<label class="col-md-2 control-label">专题状态<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<select class="form-control" id="status" name="status">
				<option value='1'>有效</option>
				<option value='0'>无效</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">是否长期有效<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<select class="form-control" id="durationType" name="durationType">
				<option value='1'>长期有效</option>
				<option value='0'>固定期限</option>
			</select>
		</div>
		<label class="col-md-2 control-label">专题进度<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<select class="form-control" id="type" name="type">
				<#list topicProgress as progress>
					<option value='${progress.code}'>${progress.desc}</option>
				</#list>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">专题开始时间<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control Wdate" id="startTime" name="startTime" value="${topicDO.startTime}" 
        		onFocus="WdatePicker({dateFmt: 'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}',readOnly: true});$(this).css('background-color','#ffffff');"/>
		</div>
		<label class="col-md-2 control-label">专题结束时间<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control Wdate" id="endTime" name="endTime" value="${topicDO.endTime}" 
    			onFocus="WdatePicker({dateFmt: 'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}',readOnly: true});$(this).css('background-color','#ffffff');"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">专题排序<span class="dr-asterisk requiredField">(默认为0)</span></label>
		<div class="col-md-4">
			<#-- 默认排序值为0 -->
			<input type="text" class="form-control" id="sort" name="sort" value="0" />
		</div>
		<label class="col-md-2 control-label">是否锁定排序位置<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<select class="form-control" id="sortLock" name="sortLock">
				<option value='0'>未锁定</option>
				<option value='1'>锁定</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">专题所需积分<span class="dr-asterisk requiredField">(默认为0)</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="needPoint" name="needPoint" value="0" />
		</div>
		<label class="col-md-2 control-label">积分抵扣额度</label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="pointDeduction" name="pointDeduction" value="${topicDO.pointDeduction}" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">专题描述</label>
		<div class="col-md-4">
			<textarea class="form-control" rows="2" id="description" name="description" value="${topicDO.description}"></textarea>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">备注</label>
		<div class="col-md-4">
			<textarea class="form-control" rows="2" id="remark" name="remark" value="${topicDO.remark}"></textarea>
		</div>
	</div>
	
	<#-- 商品上传图片 -->
	<div class="form-group">
		<label class="col-md-2 control-label">选择专题图片</label>
		<div class="col-md-4">
			<input type="file" value="浏览" id="image" name="image">
			<div id="preview"></div>
		</div>
	</div>
	
	
	<hr/>
	<div>
		<div class="col-sm-12 panel-toolbar text-left dr-slash-text" id="operateBtn">
			<div class="col-md-4"></div>
			<div>
				<a href="javascript:void(0);" class="btn btn-info" id="cancelTabBtn">取消</a>
				<a href="javascript:void(0);" class="btn btn-primary" id="saveBtn">保存</a>
			</div>
		</div>
	</div>
	
</form>
</div>

</@backend>

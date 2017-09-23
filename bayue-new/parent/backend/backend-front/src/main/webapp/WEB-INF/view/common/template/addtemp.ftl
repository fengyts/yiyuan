<#include "/common/common.ftl" />

<@backend title="商品新增spu" 
js=[
'/statics/plugin/layui-v1.0.2/layui/layui.js',
'/statics/plugin/select2/js/select2.js',
'/statics/plugin/select2/js/select2Util.js',
'/statics/plugin/select2/js/select2_locale_zh-CN.js',
'/statics/plugin/bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js',
'/statics/plugin/My97DatePicker/WdatePicker.js',
'/statics/backend/item/iteminfo.js'
]
css=[
'/statics/common/common-css/common.css',
'/statics/common/common-css/style.css',
'/statics/plugin/bootstrap/bootstrap-3.3.5-dist/css/bootstrap.min.css',
'/statics/plugin/select2/css/select2.css'
]>


<div class="panel-body">
<form id="" action="" class="form-horizontal dr-form-bordered">
	<div class="form-group">
		<label class="col-md-2 control-label">合同编号</label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="contractNo" value="${contract.contractNo }" placeholder="保存时由系统生成" readonly="readonly"/>
		</div>
		<label class="col-md-2 control-label">贷款种类<span class="dr-asterisk">*</span></label>
		<div class="col-md-4">
			<select id="loanType" class="form-control" >
				<option value="">--all--</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">借款公司<span class="dr-asterisk">*</span></label>
		<div class="col-md-4">
			<div class="input-group">
				<input type="text" class="form-control" id="debtorName" value="${debtor.debtorName }" title="${debtor.debtorName }" readonly="readonly"/>
				<input type="hidden" class="form-control" id="debtorId" value="${debtor.id }"/>
				<span class="btn btn-default btn-sm input-group-addon" style="border-left: none;" onclick="clearDebtor();"><span class="glyphicon glyphicon-trash"></span> 清除</span>
				<span class="btn btn-default btn-sm input-group-addon" onclick="selectDebtor();"><span class="glyphicon glyphicon-search"></span> 选择</span>
			</div>
		</div>
		<label class="col-md-2 control-label">贷款人<span class="dr-asterisk">*</span></label>
		<div class="col-md-4">
			<div class="input-group">
				<input type="text" class="form-control" id="lenderName" value="${lender.lenderName }" title="${lender.lenderName }" readonly="readonly"/>
				<input type="hidden" class="form-control" id="lenderId" value="${lender.id }"/>
				<span class="btn btn-default btn-sm input-group-addon" style="border-left: none;" onclick="clearLender();"><span class="glyphicon glyphicon-trash"></span> 清除</span>
				<span class="btn btn-default btn-sm input-group-addon" onclick="selectLender();"><span class="glyphicon glyphicon-search"></span> 选择</span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">起息日<span class="dr-asterisk">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control Wdate" id="valueDate" value="${contract.valueDate }" 
            	onFocus="WdatePicker({dateFmt: 'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'dueDate\')}',readOnly: true});$(this).css('background-color','#ffffff');"/>
		</div>
		<label class="col-md-2 control-label">到期日<span class="dr-asterisk">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control Wdate" id="dueDate" value="${contract.dueDate }" 
        		onFocus="WdatePicker({dateFmt: 'yyyy-MM-dd',minDate:'#F{$dp.$D(\'valueDate\')}',readOnly: true});$(this).css('background-color','#ffffff');"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">贷款金额<span class="dr-asterisk">*</span></label>
		<div class="col-md-4">
			<div class="input-group">
				<input type="text" class="form-control" id="loanAmount" maxlength="16" value="" pattern="###0.00#"/>
				<span class="input-group-addon">元</span>
			</div>
		</div>
		<label class="col-md-2 control-label">年利率<span class="dr-asterisk">*</span></label>
		<div class="col-md-4">
			<div class="input-group">
				<input type="text" class="form-control" id="annualInterestRate" maxlength="8" value="fdfd" pattern="###0.00#"/>
				<span class="input-group-addon">%</span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">境内境外<span class="dr-asterisk">*</span></label>
		<div class="col-md-4">
			<select id="domesticOrAbroad" name="domesticOrAbroad" class="form-control" >
				<option value="1">境内</option>
				<option value="2">境外</option>
			</select>
		</div>
	</div>
	
	<hr/>
	<div>
		<div class="col-sm-12 panel-toolbar text-left dr-slash-text" id="operateBtn">
			<a href="javascript:void(0);" onclick="cancel()" class="btn btn-warning" id="cancelBtn">取消</a>
			<a href="javascript:void(0);" onclick="save()" class="btn btn-primary">保存</a>
		</div>
	</div>
	
</form>
</div>

</@backend>

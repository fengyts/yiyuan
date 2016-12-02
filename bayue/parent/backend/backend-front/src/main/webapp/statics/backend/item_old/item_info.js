var pageii;
$(document).ready(function(){
	
	$(".select2").select2();
	$(".select2").css("margin-left","1px");
	
	// 取消按钮 关闭当前弹窗
	$("#cancelbtn").on('click',function(){
		window.parent.layer.close(parent.pageii);
	});
	
	//获取类别
	$("#largeId").change(this,function(v){
		$.post(domain+'/basedata/category/linkageAjaxJson',{"id":v.val},function(result){
			var _smallIdSelector = $("#smallId");
			if(!result){
				_smallIdSelector.empty().append("<option value=''>--请选择二级分类--</option>");
				return;
			}
			var res = JSON.parse(result);
			if(res){
				$("#select2-chosen-2").text("--请选择二级分类--").val("");
				_smallIdSelector.empty().append("<option value=''>--请选择二级分类--</option>");
			}
			$.each(res,function(i,v1){
//				$("#smallCateId").append("<option value='" + v1.id + "'>" + v1.name + "</option>");
				_smallIdSelector.append($("<option>").val(v1.id).text(v1.name));
			});
		},'text');
	});
	
	
	//保存spu按钮
	$("#itemInfoSaveBtn").on('click',function(){
		//window.parent.$("#newSPUTr").empty();
		
		var _largeId = $("#largeId :selected").val(), _largeName = $("#largeId :selected").text(),
		_smallId = $("#smallId :selected").val(), _smallName = $("#smallId :selected").text(),
		_unitId = $("#unitId :selected").val(), _unitName = $("#unitId :selected").text(),
		_mainTitle = $("#mainTitle").val(), _remark=$("#spuRemark").val();
		
		if(!_largeId || !_smallId || !_unitId || !mainTitle || mainTitle == ''){
			alert("分类、单位和SPU名称都不能为空");
			return;
		}
		
		var _trParent = "<td colspan='4'><table class='input tabContent mt10'><tr>" +
		"<td class='td_right'>商品类别:</td>" +
		"<td width='430'><input type='hidden' name='largeId' value='" + _largeId + "'/>" +
		"<input type='hidden' name='smallId' value='" + _smallId + "'/>" +
		_largeName + "   >>   " + _smallName + "</td>" +
		"<td class='td_right'>单位:</td><td>" +
		"<input type='hidden' name='unitId' value='" + _unitId + "'/>" + _unitName +
		"</td></tr><tr>" +
		"<td class='td_right'>SPU名称:</td><td>" +
		"<input type='hidden' name='mainTitle' value='" + _mainTitle + "'/>" + _mainTitle +
		"</td><td class='td_right'>备注:</td>" +
		"<td>" +
		"<input type='hidden' name='remark' value='" + _remark + "'/>" + _remark +
		"</td></tr></table></td>";
		
		window.parent.$("#oldSPUTr").empty();
		window.parent.$("#newSPUTr").html(_trParent);
		window.parent.$("#addNewSPU").attr("paramType",'0').attr("value","取消新增");
		
		//window.parent.$("#newSPUTr").append(_trParent);
		//window.parent.$("#addNewSPU").attr("disabled",true).css("background",'gray');
		//window.parent.$("#spu").attr("disabled",true);
		
		var index = parent.layer.getFrameIndex(window.name); // 获取当前窗体索引
		parent.layer.close(index); 
	});
	
	
});
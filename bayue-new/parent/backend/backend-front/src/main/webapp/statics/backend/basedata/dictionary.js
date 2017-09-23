var pageii;
$(document).ready(function(){
	
	// 取消按钮 关闭当前弹窗
	$("#cancelbtn").on('click',function(){
		window.parent.layer.close(parent.pageii);
	});
	
//	$("#addNewCode").on('click',function(){
////		$("#selectCode").attr("disabled",true);
//		if($("#inputNewCode").length != 0){return;}
//		var _parent = $(this).parent();
//		_parent.prev().find("select[name='code']").attr("disabled",true);
//		_parent.parent().parent().after("<tr><td></td>" +
//				"<td><input type='text' id='inputNewCode' class='input-text lh25' name='code'/>" +
//				"<input type='button' id='cancelNewCode' name='cancelAddCode' class='ext_btn ext_btn_submit m10' value='取消' />" +
//				"</td></tr>");
//	});
//	$(".form_table").on('click',"input[name='cancelAddCode']",function(){
//		alert(123);
////		$(this).parent().parent().remove();
//	});
	
	$(".form_table").on('click',"#addNewCode",function(){//点击新增code按钮
		var _tdNewCode = "<td id='idCodeTd'>" +
				"<input type='text' id='inputNewCode' class='input-text lh25' name='code'/>" +
				"<input type='button' id='cancelNewCode' name='cancelAddCode' class='ext_btn ext_btn_submit m10' value='取消' />" +
				"</td>";
		$(_tdNewCode).replaceAll($(this).parent().parent());
	});
	$(".form_table").on('click',"#cancelNewCode",function(){//点击取消新增code按钮
		var _tdOldCode = "<td id='idCodeTd'><span>" +
				"<select name='code' class='select' id='selectCode'>"+
					"<option value='' selected='selected'>--请选择--</option>";
		var _tdOldCode1 = "</select></span>" +
				"<span><input type='button' id='addNewCode' class='ext_btn ext_btn_submit m10' value='新增字典类别码'/>" +
				"</span></td>";
		$.post("getCode",function(res){
			var _codeJson = JSON.parse(res);
			var _code = $("input[name='code']").val();
			$.each(_codeJson,function(i,v){
				var _option;
				if(_code && _code == v.code){
					_option = "<option value='"+v.code+"' selected='selected'>"+v.code+"</option>";
				}else{
					_option = "<option value='"+v.code+"'>"+v.code+"</option>";
				}
				_tdOldCode += _option;
			});
			
//			$(_tdOldCode + _tdOldCode1).replaceAll("#idCodeTd");
			$("#idCodeTd").replaceWith(_tdOldCode + _tdOldCode1);
		},'text');
	});
	
	$("#dictionaryAddbtn").on('click',function(){
		pageii = $.layer({
			type : 2,
			title : '数据字典管理-->新增',
			shadeClose : true,
			maxmin : true,
			fix : false,
			area: ['500px', 400],
			iframe : {
				src : domain + '/basedata/dictionary/add.htm'
			}
		});
	});
	
	
	$(".dictionaryEditbtn").on('click',function(){
		var id = $(this).attr("param");
		pageii = $.layer({
			type : 2,
			title : '数据字典管理-->编辑',
			shadeClose : true,
			maxmin : true,
			fix : false,
			area: ['500px', 400],
			iframe : {
				src : domain + '/basedata/dictionary/edit.htm?id='+id
			}
		});
	});
	
	//保存新增
	$("#dictionarySaveBtn").on('click',function(){
		$.ajax({
			url : 'save',
			dataType : 'text',
			data : $('#dictionaryAddForm').serialize(),
			type : "post",
			cache : false,
			error : function(request){
				alert("Server Connection Failure...");
			},
			success : function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, 1, function() {
						parent.window.location.reload();
		            	parent.layer.close(parent.pageii);
					});
				} else {// 失败
					layer.alert(data.message, 8);
				}
			}
		});
	});
	
	//保存更新
	$("#dictionaryUpdateBtn").on('click',function(){
		$.ajax({
			url : 'update',
			dataType : 'text',
			data : $('#dictionaryEditForm').serialize(),
			type : "post",
			cache : false,
			error : function(request){
				alert("Server Connection Failure...");
			},
			success : function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, 1, function() {
						parent.window.location.reload();
		            	parent.layer.close(parent.pageii);
					});
				} else {// 失败
					layer.alert(data.message, 8);
				}
			}
		});
		
	});
	
	
});
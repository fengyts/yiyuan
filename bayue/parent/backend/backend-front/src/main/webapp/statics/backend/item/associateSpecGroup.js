var pageii;

/** 使用新版layui,定义layer和form模块 */
//var layer = layui.layer, form = layui.form;
var layer, form;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	form = layui.form();
});

$(function(){
	
	$("#cancelBtn").on('click',function(){
		window.parent.layer.close(parent.pageii);
	});
	
	$("#checkSpecGroupAll").on('click', function(){
		var _this = $(this);
		if(_this.is(":checked")){
			$("#dataList tbody tr :checkbox").each(function(){
				$(this).prop("checked",true);
			});
		}else{
			$("#dataList tbody tr :input[type='checkbox']:checked").each(function(){
				$(this).prop("checked",false);
			});
		}
	});
	
	
	//确认选择规格组按钮
	$("#confirmBtn").on('click',function(){
		var _checked = $("#dataList tbody :input[type='checkbox']:checked");
		if(_checked.length < 1){
			layer.alert("请选择一个规格组", {icon : 8});
			return;
		}
		
		var _parentSpecs = parent.$("#associateSpecGroupDataList tbody tr");
		var _html = "", _flag = false;
		_checked.each(function(){
			var _this = $(this).parent(), _groupId = _this.next().text(),
				_groupName = _this.next().next().text(), _groupAlias = _this.next().next().next().text();
			
			_parentSpecs.each(function(){ // 校验是否已经绑定
				var _pGroupId = $(this).children().eq(1).text();
				if(_pGroupId == _groupId){
					_flag = true;
					return false;
				}
			});
			
			if(_flag){ return ;}
			
			_html += "<tr><td class='display'></td><td style='display:none;'>" + _groupId + "</td><td class='text-center'>"
					  + _groupName + "</td><td class='text-center'>"
					  + _groupAlias + "</td><td>"
					  + "<input class='form-control' type='text' value='0'></td><td class='text-center'>"
					  + "<button type='button' class='btn btn-danger delSpecGroup'>删除</button>"
					  + "</td></tr>";
		});
		
		if(_flag){
			layer.alert("请不要重复绑定", {icon : 0});
			return;
		}
		
		parent.$("#associateSpecGroupDataList tbody").append(_html);
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭   
		
		//parent.layer.closeAll('iframe');
	});
	
	
});
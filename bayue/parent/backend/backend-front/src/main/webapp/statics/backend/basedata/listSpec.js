$(function() {

	// 取消按钮 关闭当前弹窗
	$("#cancelbtn").on('click', function() {
		window.parent.layer.close(parent.pageii);
	});

	$("#selectSpecConfirmBtn").on('click',function() {
			var _checkedTd = $("#tableSpec").find("input[type='checkbox']:checked");
			if (_checkedTd.length < 1) {
				alert("至少选中一个规格");
				return;
			}
			
			var _hasSelected = false, _parentView = "";
			var _parentSelected = window.parent.$("#specList").find("input[name='specId']");
			$.each(_checkedTd, function(i, v) {
				var _id, _spec, _sort, _status;
				var _tds = $(this).parent().parent().children();
				_id = _tds.eq(1).text();
				_spec = _tds.eq(3).text();
				_sort = _tds.eq(4).text();
				_status = _tds.eq(5).text();
				
				if(_parentSelected.length > 0){
					$.each(_parentSelected,function(i,v){
						var _v = $(v).val();
						if(_v == _id){
							alert("该规格已经选中了,不要重复关联");
							_hasSelected = true;
							return;
						}
					});
				}
				
				if(_hasSelected){
					return;
				}
				
				var _parentTr = "<tr  class='specTr'>" + "<td class='td_center'>" + _id
						+ "<input type='hidden' name='specId' value='" + _id + "'/>"
						+ "</td>" + "<td class='td_center'>" + _spec
						+ "</td>" + "<td class='td_center'><input type='text' class='input-text' size='10' value='" + _sort + "'/>"
						+ "</td>" + "<td class='td_center'>" + _status
						+ "</td>" + "<td class='td_center'><a href='javascript:void(0);' class='unboundSpec'>删除</a>"
						+ "</td>" + "</tr>";
				_parentView += _parentTr;
			});
			
			window.parent.$("#specList").append(_parentView);
			
			var _index = parent.layer.getFrameIndex(window.name);//获取当前窗体索引
			parent.layer.close(_index);
			
	});

});
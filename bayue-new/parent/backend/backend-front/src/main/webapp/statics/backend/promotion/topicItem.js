var pageii;
$(function() {
	
//	$("#queryTopicItemListBtn").on('click', function(){
//		var _url = domain + "/topic/topicItemList?topicId=" + $("#topicId").val();
//		$("#topicItemListForm").attr('action', _url).submit();
//	});
	
	// 专题关联商品
	$("#associateTopicItem").on('click', function() {
		var _topicId = $("#topicId").val();
		pageii = layer.open({
			type : 2,
			title : '专题添加商品',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : [ '1000px', '550px' ],
			content : domain + '/topic/initItemDetailList.htm?topicId=' + _topicId,
		});

	});
	
	// 专题商品列表页面新增按钮点击事件
	$("#addTopicItem").on('click', function(){
		layer.alert("亲，功能尚未上线,请从专题管理里面关联商品", {icon:1});
	});

	$("#cancelTabBtn,#cancelAssociateTopicItemBtn").on('click', function() {
		parent.layer.close(parent.pageii);
	});
	
//	$(".editTopicItemBtn").on('click', function(){
//		var id = $(this).attr('param');
//		pageii = layer.open({
//			type : 2,
//			title : '专题编辑商品',
//			// shadeClose : true,
//			shade : 0.3,
//			maxmin : true,
//			fix : false,
//			scrollbar : false,
//			area : [ '1000px', '550px' ],
//			content : domain + '/topicItem/edit.htm?id=' + id,
//		});
//	});

	// 全选商品
	$("#checkAllDetail").on('click', function() {
		if ($(this).is(":checked")) {
			$("#dataBodyList :checkbox").prop('checked', true);
		} else {
			$("#dataBodyList :checkbox").prop('checked', false);
		}
	});
	$("#checkAllTopicItem").on('click', function() {
		if ($(this).is(":checked")) {
			$("#topicItemList :checkbox").prop('checked', true);
		} else {
			$("#topicItemList :checkbox").prop('checked', false);
		}
	});
	
	
	//批量修改商品
	$("#batchUpdateTopicItem").on('click', function(){
//		var _checked = $("#topicItemList tr :checked :eq(0)");
		var _checked = $(".checkedTopicItem:checked");
		if(_checked.length < 1){
			layer.alert("请选择商品", {icon:5});
			return ;
		}
		var _paramSingleTopic = $(this).attr('param'), _data = new Array(), _item = {};
		_checked.each(function(i, v){
			var _tds = $(v).parent().nextAll();
			_item.id = _tds.eq(0).text();
			if(_paramSingleTopic){ // 如果是具体专题的商品列表页
				_item.topicPrice = _tds.eq(6).find(":input").val();
				_item.snatchNumber = _tds.eq(7).find(":input").val();
				_item.status = _tds.eq(8).find(":selected").val();
			}else{ // 所有专题所有商品
				_item.topicPrice = _tds.eq(8).find(":input").val();
				_item.snatchNumber = _tds.eq(9).find(":input").val();
				_item.status = _tds.eq(10).find(":selected").val();
			}
			_data.push(_item);
		});
		
		$.ajax({
			url : domain + '/topicItem/update.htm',
			dataType : 'text',
//			data : { "itemList" : _d, "itemStatus" : _itemStatus, "topicId" : _topicId, "isTest" : _isTestItem },
			data : { "data" : JSON.stringify(_data)},
			type : "post",
			cache : false,
			error : function(request){
				layer.close(index);
				layer.alert("Server Connection Failure...",{icon:0});
			},
			success : function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, {icon:1}, function() {
						window.location.reload();
					});
				} else {// 失败
					layer.alert(data.message, { icon : 0 });
				}
			}
		});
		
	});

	// 确定添加按钮
	$("#associateTopicItemConfirm").on('click', function() {
		var _checked = $("#dataBodyList :checkbox:checked");
		if (_checked.length < 1) {
			layer.alert( "请至少选择一个商品", {icon : 0} );
			return;
		}
		
		var _flag = false;
		
		layer.confirm('确定添加？', { icon : 3, title : '提示'}, 
			function(index) {
				var _topicId = $("#topicId").val(), _itemStatus = $("#itemStatus").is(":checked"), // 商品在专题中的状态
					_isTestItem = $("#isTestItem").is(":checked"), _data = new Array();
				_checked.each(function( i,v ){
					var _tds = $(v).parent().siblings(); // 不包含$(v)本身的所有同辈元素
					// var _tr = $(v).parent().parent(), _tds = _tr.children();
					
					var _itemId = _tds.eq(0).text(),
						_detailId = _tds.eq(1).text(),
						_spu = _tds.eq(2).text(),
						_prdid = _tds.eq(3).text(),
						_mainTitle = _tds.eq(4).text(),
						_smallId = _tds.eq(6).text(),
						_itemSalesStatus = _tds.eq(9).find(":input").val();//商品上下架状态
						_basicPrice = _tds.eq(10).text(),
						_topicPrice = _tds.eq(11).find(":input").val(),
						_snatchNumber = _tds.eq(12).find(":input").val();
					
					if(_itemStatus && _itemSalesStatus != 1){ // 只有上架状态的商品才能在专题中设置为有效
						layer.alert( "[" + _mainTitle + "]-prdid:["+ _prdid +"]商品是未上架状态, 不能在专题中设置为有效", {icon : 0} );
						_flag = true;
						return;
					}
						
					var _item = {
							"topicId" : _topicId, "status" : _itemStatus, "isTest" : _isTestItem, 
							"itemId" : _itemId, "detailId" : _detailId, "spu" : _spu, "prdid" : _prdid,
							"mainTitle" : _mainTitle, "smallId" : _smallId, "basicPrice" : _basicPrice,
							"topicPrice" : _topicPrice, "snatchNumber" : _snatchNumber
							};
					
					_data.push(_item);
					
				} );
				
				if(_flag){ // 只有此处阻断,才会弹出上面each中专题商品是否能设置有效的校验
					return;
				}
				
				var _d = JSON.stringify(_data);
				
				$.ajax({
					url : domain + '/topicItem/save.htm',
					dataType : 'text',
//					data : { "itemList" : _d, "itemStatus" : _itemStatus, "topicId" : _topicId, "isTest" : _isTestItem },
					data : { "itemList" : _d },
					type : "post",
					cache : false,
					error : function(request){
						layer.close(index);
						layer.alert("Server Connection Failure...",{icon:0});
					},
					success : function(res) {
						var data = JSON.parse(res);
						if (1 == data.result) {// 成功
							layer.alert(data.message, {icon:1}, function() {
								parent.window.location.reload();
								parent.layer.close(parent.pageii);
							});
						} else {// 失败
							layer.close(index);
							layer.alert(data.message, { icon : 0 });
						}
					}
				});
				
				// layer.close(index);
			}
		);

	});

});
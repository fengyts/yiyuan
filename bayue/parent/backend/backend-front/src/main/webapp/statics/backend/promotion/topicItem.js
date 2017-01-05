var pageii;
$(function() {
	
//	$("#queryTopicItemListBtn").on('click', function(){
//		var _url = domain + "/topic/topicItemList?topicId=" + $("#topicId").val();
//		$("#topicItemListForm").attr('action', _url).submit();
//	});

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

	$("#cancelAssociateTopicItemBtn").on('click', function() {
		parent.layer.close(parent.pageii);
	});

	// 全选商品
	$("#checkAllDetail").on('click', function() {
		if ($(this).is(":checked")) {
			$("#dataBodyList :checkbox").prop('checked', true);
		} else {
			$("#dataBodyList :checkbox").prop('checked', false);
		}
	});

	// 确定添加按钮
	$("#associateTopicItemConfirm").on('click', function() {
		var _checked = $("#dataBodyList :checkbox:checked");
		if (_checked.length < 1) {
			layer.alert("请至少选择一个商品", {
				icon : 0
			});
			return;
		}

		layer.confirm('确定添加？', { icon : 3, title : '提示'}, 
			function(index) {
				var _topicId = $("#topicId").val(), _itemStatus = $("#itemStatus").is(":checked"), _data = new Array();
				_checked.each(function( i,v ){
					var _tds = $(v).parent().siblings(); // 不包含$(v)本身的所有同辈元素
					// var _tr = $(v).parent().parent(), _tds = _tr.children();
					var _itemId = _tds.eq(0).text(),
						_detailId = _tds.eq(1).text(),
						_spu = _tds.eq(2).text(),
						_prdid = _tds.eq(3).text(),
						_mainTitle = _tds.eq(4).text(),
						_smallId = _tds.eq(6).text(),
						_itemStatus = _tds.eq(9).find(":input").val(),
						_basicPrice = _tds.eq(10).text(),
						_topicPrice = _tds.eq(11).find(":input").val(),
						_snatchNumber = _tds.eq(12).find(":input").val();
					var _item = {"itemId" : _itemId, "detailId" : _detailId, "spu" : _spu, "prdid" : _prdid,
							"mainTitle" : _mainTitle, "smallId" : _smallId, "basicPrice" : _basicPrice,
							"topicPrice" : _topicPrice, "snatchNumber" : _snatchNumber};
					_data.push(_item);
				});
				
				var _d = JSON.stringify(_data);
				console.log(_d);
				
				$.ajax({
					url : domain + '/topicItem/save.htm',
					dataType : 'text',
					data : { "itemList" : _d, "itemStatus" : _itemStatus, "topicId" : _topicId },
					type : "post",
					cache : false,
					error : function(request){
						alert("Server Connection Failure...");
					},
					success : function(res) {
						var data = JSON.parse(res);
						if (1 == data.result) {// 成功
							layer.alert(data.message, {icon:1}, function() {
								parent.window.location.reload();
								parent.layer.close(parent.pageii);
							});
						} else {// 失败
							layer.alert(data.message, 8);
						}
					}
				});
				
				// layer.close(index);
			}
		);

	});

});
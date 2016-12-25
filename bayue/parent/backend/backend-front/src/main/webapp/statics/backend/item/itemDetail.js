var pageii;

/** 使用新版layui,定义layer和form模块 */
//var layer = layui.layer, form = layui.form;
var layer, form;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	form = layui.form();
});

$(function(){
	
	//商品描述信息kindeditor placeholder信息
	$("#description").attr("placeholder","请输入商品描述信息");
	
	$("#addItemDetail").on('click',function(){
		addTab("item_detail_add","商品新增","/item/itemDetail/add.htm?iframeName=" + window.name);
	});
	
	/**
	 * 新增页面取消按钮 关闭当前tab页
	 */
	$("#cancelTabBtn").on('click',function(){
		parent.window.closeTab("item_detail_add");
	});
	
	$(".editDetail").on('click',function(){
		var detailId = $(this).attr("param");
		addTab("item_detail_edit","商品编辑","/item/itemDetail/edit.htm?iframeName=" + window.name 
				+ "&detailId=" + detailId);
	});
	
	/** 编辑页面取消按钮  关闭当前tab页*/
	$("#cancelTabItemEditBtn").on('click',function(){
		parent.window.closeTab("item_detail_edit");
	});
	
	/**
	 * 取消按钮 关闭当前弹窗
	 */
	$("#cancelLayerBtn").on('click',function(){
		parent.window.layer.close(parent.pageii);
	});
	
	/** 弹出spu列表选择spu信息 */
	$("#selectSPU").on('click',function(){
		pageii = layer.open({
			type : 2,
			title : '商品spu列表',
			offset: '5%',
			// shadeClose : true,
			// shade : 0.3,
			maxmin : true,
			fixed : false,
			scrollbar : false,
			area : [ '700px', '500px' ],
			content : domain + '/item/itemInfo/initSpuList.htm',
		});
	});
	
	/** 获取spu信息*/
	$("#confirmBtn").on('click',function(){
		var _radio = $(".selectSpu:checked");
		if(_radio.length != 1){
			layer.alert("必须选择spu信息",{icon:8});
			return;
		}
		var _tds = _radio.parent().nextAll();
		var _itemId = _tds.eq(0).text(), _spu = _tds.eq(1).text(), 
		_mainTitle = _tds.eq(2).text(), 
		_largeName = _tds.eq(3).text(), _largeId = _tds.eq(4).text(),
		_smallName = _tds.eq(5).text(), _smallId = _tds.eq(6).text(),
		_unitName = _tds.eq(7).text(), _unitId = _tds.eq(8).text();
		
		parent.window.$("#itemId").val(_itemId);
		parent.window.$("#spu").val(_spu);
		parent.window.$("#mainTitle").val(_mainTitle);
		parent.window.$("#largeName").val(_largeName);
		parent.window.$("#largeId").val(_largeId);
		parent.window.$("#smallName").val(_smallName);
		parent.window.$("#smallId").val(_smallId);
		parent.window.$("#unitName").val(_unitName);
		parent.window.$("#unitId").val(_unitId);
		parent.window.layer.close(parent.pageii);
	});
	
	
	/** 选择规格信息 */
	$("#associateSpecGroup").on('click', function(){
		pageii = layer.open({
			type : 2,
			title : '规格组列表',
			offset: '5%',
			maxmin : true,
			fixed : false,
			scrollbar : false,
			area : [ '700px', '500px' ],
			content : domain + '/item/itemDetail/initSpecGroup.htm',
		});
	});
	
	//规格组删除按钮
	$("#associateSpecGroupDataList tbody").on('click',".delSpecGroup",function(){
		$(this).parent().parent().remove();
	});
	
	//商品详情保存按钮
	$("#saveBtn").on('click',function(){
		var specGroupData = getSpecGroupData();
//		var _html = editor.html();
		var _s = JSON.stringify(specGroupData);
		
		var _data = $("#itemDetailAddForm").serialize();
		_data += "&specGroupStrs=" + _s;
		
		$.ajax({
			url : 'save.htm',
			dataType : 'text',
			data : _data,
			type : "post",
			cache : false,
			error : function(request){
				alert("Server Connection Failure...");
			},
			success : function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, 1, function() {
						//'mainIframe_tabli_14'
						var listIframeName = $("#listIframeName").val();
						parent.window.frames[listIframeName].location.reload();
						parent.window.closeTab("item_detail_add");
					});
				} else {// 失败
					layer.alert(data.message, 8);
				}
			}
		});
	});
	
	//商品详情编辑保存按钮
	$("#updateItemDetailBtn").on('click',function(){
		var specGroupData = getSpecGroupData();
//		var _html = editor.html();
		var _s = JSON.stringify(specGroupData);
		
		var _data = $("#itemDetailEditForm").serialize();
		_data += "&specGroupStrs=" + _s;
		
		$.ajax({
			url : 'update.htm',
			dataType : 'text',
			data : _data,
			type : "post",
			cache : false,
			error : function(request){
				alert("Server Connection Failure...");
			},
			success : function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, 1, function() {
						//'mainIframe_tabli_14'
						var listIframeName = $("#listIframeName").val();
						parent.window.frames[listIframeName].location.reload();
						parent.window.closeTab("item_detail_edit");
					});
				} else {// 失败
					layer.alert(data.message, 8);
				}
			}
		});
	});
	
	
	//商品详情列表页全选按钮
	$("#checkAllDetail").on('click', function(){
		if($(this).is(":checked")){
			$("#dataListDetail tr").each(function(){
				$(this).find(":checkbox").prop('checked',true);
			});
		}else{
			$("#dataListDetail tr").each(function(){
				$(this).find(":checkbox").attr('checked',false);
			});
		}
	});
	
	$("#batchOffSales, #batchOnSales, #batchCancellation").on('click', function(){
		var _status = $(this).attr('param');
		var _checkedData = $("#dataBodyList input:checked");
		if(_checkedData.length < 1){
			layer.alert("请至少选择一条记录", 8);
			return false;
		}
		var _ids = "";
		$.each(_checkedData, function(){
			_ids += $(this).attr('param')+",";
		});
		_ids = _ids.substring(0,_ids.length-1);
		$.post("batchHandleItemStatus.htm", {"ids" : _ids, "status" : _status}, function(response){
			if(1 == response.result){
				layer.alert(response.message, {icon:6}, function(){
					window.location.reload(this);
				});
			}else{
				layer.alert(response.message, {icon:5});
			}
		}, 'json');
	});
	
});

//获取规格组数据
function getSpecGroupData(){
	var data = new Array();
	$("#associateSpecGroupDataList tbody tr").each(function(){
		var _record = {}, _children = $(this).children();
		_record.id = _children.eq(0).text();
		_record.specGroupId = _children.eq(1).text();
		var _sort = _children.eq(4).find(":input").val();
		if(!_sort){
			_sort = 0;
		}
		_record.sort = _sort;
		data.push(_record);
	});
	return data;
}




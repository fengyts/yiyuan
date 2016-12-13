var pageii;

/** 使用新版layui,定义layer和form模块 */
//var layer = layui.layer, form = layui.form;
var layer, form;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	form = layui.form();
});

$(function(){
	
	$("#addItemDetail").on('click',function(){
		addTab("item_detail_add","商品新增","/item/itemDetail/add.htm");
	});
	
	/**
	 * 取消按钮 关闭当前tab页
	 */
	$("#cancelTabBtn").on('click',function(){
		parent.window.closeTab("item_detail_add");
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
		_mainTitle = _tds.eq(2).text(), _largeName = _tds.eq(3).text(),
		_smallName = _tds.eq(4).text(), _unitName = _tds.eq(5).text();
		
		parent.window.$("#itemId").val(_itemId);
		parent.window.$("#spu").val(_spu);
		parent.window.$("#mainTitle").val(_mainTitle);
		parent.window.$("#largeName").val(_largeName);
		parent.window.$("#smallName").val(_smallName);
		parent.window.$("#unitName").val(_unitName);
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
		var _html = editor.html();
		console.log(_html);
		var _mainTitle = $("#itemTitle").val();
		console.log("itemTitle:"+_mainTitle);
		console.log(_mainTitle.isEmpty());
//		$.myajax('save',$("#itemDetailAddForm").serialize());
	});
	
	
	//列表页全选按钮
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
	
});

//获取规格组数据
function getSpecGroupData(){
	var data = new Array();
	var _record = {};
	$("#associateSpecGroupDataList tbody tr").each(function(){
		var _children = $(this).children();
		_record.specGroupId = _children.eq(0).text();
		var _sort = _children.eq(3).find(":input").val();
		if(!_sort){
			_sort = 0;
		}
		_record.sort = _sort;
		data.push(_record);
	});
	return data;
}




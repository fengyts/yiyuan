var pageii;
$(function(){
	
	// 取消按钮 关闭当前弹窗
	$("#cancelbtn").on('click',function(){
		window.parent.layer.close(parent.pageii);
	});
	
	
	var colNames = ['ID','模块','标题', '内容', '级别','状态','备注','操作'];
	var colModel = [
	   	         {name:'id',index:'id', width:20,align:"center"},
	   	         {name:'module',index:'module', width:20,align:"center"},
		         {name:'title',index:'title', width:100,align:"center", sortable:false},
		         {name:'content',index:'content', width:300, align:"left", sortable:false},
		         {name:'level',index:'level', width:20, align:"center", hidden:true, sortable:false},
		         {name:'status',index:'status', width:50, align:"center", sortable:false,
		        	 formatter:function(cellValue,options,rowObject){
		        		 if(cellValue){
		        			 return "有效";
		        		 }else{
		        			 return "无效";
		        		 }
		        	 }
		         },
		         {name:'remark',index:'remark', width:100, align:"center", sortable:false},
		         {name:'操作', index:'操作', width:80, align:"center",
		        	 formatter:function(cellValue,options,rowObject){
		        		 var str = "<a href='javascript:void(0);'onclick='strategyEdit(" 
		        			 + rowObject.id + ");'><font color='blue'>编辑</font></a>";
		        		 return str;
		        	 }
		         }
		       ];
	
	$("#tree").jqGrid({
		url:"strategyJsonData",
		datatype: "json",
		
		loadonce:true,
	    height: "500",
//		autoheight:true,
	    autowidth:true,
        colNames:colNames,
        colModel:colModel,
        rowNum:10,
        rowList:[5,10,20,30],
        sortname: 'module',
        viewrecords: true,
	    multiselect: false,
	    
	    pager: "false",
	    scrollrows:true,//使得选中的行显示到可视区域
	    
	    grouping:true,
        groupingView : {
          groupField : ['module'],
          groupColumnShow : [false],// 是否显示分组列
          groupCollapse : false , // 加载数据时是否只显示分组的组信息
          groupText : [ '<b>{0} - {1} 条记录</b>' ], // 表头显示的数据以及相应的数据量
          groupCollapse : false , // 加载数据时是否只显示分组的组信息
          groupDataSorted : true , // 分组中的数据是否排序
          groupOrder : [ 'asc' ], // 分组后的排序
        }
	    
	});
	
	
	//新增攻略
	$("#strategyAddBtn").on('click',function(){
		pageii = $.layer({
			type : 2,
			title : '攻略管理-->新增',
			shadeClose : true,
			maxmin : true,
			fix : false,
			area: ['600px', 400],
			iframe : {
				src : domain + '/basedata/strategy/add.htm'
			}
		});
	});
	
	$("#strategySaveBtn").on('click',function(){
		saveStrategy();
	});
	
	
});


function strategyEdit(id){
	if(!id){
		alert("页面获取id异常！");
		return;
	}
	pateii = $.layer({
		type : 2,
		title : '攻略管理-->编辑',
		shadeClose : true,
		maxmin : true,
		fix : false,
		area: ['600px', 400],
		iframe : {
			src : domain + '/basedata/strategy/edit.htm?id='+id
		}
	});
	
}

function saveStrategy(){
	$.ajax({
		url : 'save',
		dataType : 'text',
		data : $('#strategyAddForm').serialize(),
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
}

function updateStrategy(){
	$.ajax({
		url : 'update',
		dataType : 'text',
		data : $('#strategyEditForm').serialize(),
		type : "post",
		cache : false,
		error : function(request){
			alert("Server Connection Failure...");
		},
		success : function(res) {
			var data = JSON.parse(res);
			if (1 == data.result) {// 成功
				layer.alert(data.message, 1, function() {
//					location.href = 'list.htm';
					parent.window.location.reload();
	            	parent.layer.close(parent.pageii);
				});
			} else {// 失败
				layer.alert(data.message, 8);
			}
		}
	});
}
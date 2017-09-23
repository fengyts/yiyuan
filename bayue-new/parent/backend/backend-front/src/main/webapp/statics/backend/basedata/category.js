var pageii;
$(function(){
	
	// 取消按钮 关闭当前弹窗
	$("#cancelbtn").on('click',function(){
		window.parent.layer.close(parent.pageii);
	});
	
	/*
	var jsonData={
		"rows":[
			{
			    "id": "1",
			    "name": "家居","code":"01","remark":"","status":"true",
			    "level":"1", "parentId":"0", "isLeaf":false, "expanded":false
			},
			{
			    "id": "2",
			    "name": "沙发","code":"0101","remark":"","status":"true",
			    "level":"2", "parentId":"1", "isLeaf":true, "expanded":false
			},
			{
			    "id": "3",
			    "name": "厨卫","code":"0102","remark":"","status":"true",
			    level:"2", parentId:"1", isLeaf:true, expanded:false
			},
			{
			    "id": "4",
			    "name": "电子数码","code":"02","remark":"","status":"true",
			    level:"1", parentId:"0", isLeaf:false, expanded:false
			},
			{
			    "id": "5",
			    "name": "平板","code":"0201","remark":"","status":"true",
			    level:"2", parentId:"4", isLeaf:true, expanded:false
			},
			{
			    "id": "6",
			    "name": "相机","code":"0202","remark":"","status":"true",
			    level:"2", parentId:"4", isLeaf:true, expanded:false
			}
		 ]
	};
	*/
	
	var colNames = ['ID','父级ID','名称', '编码', '级别','状态','备注','操作'];
	var colModel = [
	   	         {name:'id',index:'id', width:20, hidden:true},
	   	         {name:'parentId',index:'parentId', width:20, hidden:true},
		         {name:'name',index:'name', width:200, sortable:false, 
	   	        	 formatter:function(cellValue,options,rowObject){
	   	        		 return rowObject.name + "(" + rowObject.id +")";
	   	        	 }
		         },
		         {name:'code',index:'code', width:50, align:"center", sortable:false},
		         {name:'level',index:'level', width:80, align:"center", sortable:false, 
		        	 formatter:function(cellValue,options,rowObject){
			        	 if(1 == cellValue){
			        		 return "<font style='color:red;font-weight:bolder'>一级</font>";
			        	 }else{
			        		 return "二级";
			        	 }
			         }
		         },
		         {name:'status',index:'status', width:50, align:"center", sortable:false,
		        	 formatter:function(cellValue,options,rowObject){
			        	 if(cellValue == true){
			        		 return "有效";
			        	  }else{
			        		 return "无效";
			        	  }
		        	 }
		         },
		         {name:'remark',index:'remark', width:80, align:"center", sortable:false},
		         {name:'操作', index:'操作', width:100, align:"center",
		        	 formatter:function(cellValue,options,rowObject){
		        		 var editOption = "<a href='javascript:void(0);' onclick='categoryEdit(" + rowObject.id + ");'><font color='blue'>编辑</font></a> &nbsp";
		        		 var logOption = "<a href='javascript:void(0);'><font color='blue'>日志</font></a>";
		        		 return editOption + logOption;
		        	 }
		         }
		       ];
	
	var lastSel;
	$("#tree").jqGrid({
		treeGrid: true,  
	    treeGridModel: 'adjacency',
	    ExpandColumn: 'name',  
	    ExpandColClick: true,
	    
//	    datastr:jsonData,
//	    datatype: "jsonstring",
	    
		url:"categoryJsonData",
		datatype: "json",
	    
		loadonce:true,
	    height: "300",
	    autowidth:true,
        colNames:colNames,
        colModel:colModel,
        rowNum:10,
        rowList:[5,10,20,30],
//        pager: '#pager',
        sortname: 'id',
        viewrecords: true,
//        sortorder: "desc",
	    multiselect: false,
	    
	    pager: "false",
	   // multiselect: true,
	    scrollrows:true,//使得选中的行显示到可视区域
	    //   caption: 'none',  
	    jsonReader: {  
	        root: "rows",  
	        repeatitems: false  
	    },
	    treeReader : {  
            level_field: "level",  
            parent_id_field: "parentId",
            leaf_field: "isLeaf",
            expanded_field: "expanded"
        },
        onSelectRow: function(id){
		      if(id && id!==lastSel){
		         $('#tree').restoreRow(lastSel);
		         lastSel=id;
		      } else{
		    	  $("#tree").resetSelection(); 
		    	  lastSel=null;
		      }
		 }
	    
	});
	
	
	//新增类别
	$("#categoryAddBtn").on('click',function(){
		pageii = $.layer({
			type : 2,
			title : '类别管理-->新增',
			border: [3, 0.3, '#000'],
			shadeClose : true,
			shade: [0.3, '#000'],
			maxmin : true,
			fix : false,
			area: ['400px', 300],
			iframe : {
				src : domain + '/basedata/category/add.htm'
			}
		});
	});
	
	//保存新增
	$("#categorySaveBtn").on('click',function(){
		addCategory();
	});
	
	
});

function categoryEdit(id){
	pageii=$.layer({
		type : 2,
		title : '类别管理-->编辑',
		shadeClose : true,
		maxmin : true,
		fix : false,
		area: ['400px', 300],
		iframe : {
			src : domain + '/basedata/category/edit.htm?id='+id
		}
	});
}

function updateCategory(){
	$.ajax({
		url : 'update',
		dataType : 'text',
		data : $('#categoryEditForm').serialize(),
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

function addCategory(){
	$.ajax({
		url : 'save',
		dataType : 'text',
		data : $('#categoryAddForm').serialize(),
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
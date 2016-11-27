var pageii;
$(function(){
	
	imgUpload();
	
	$(".select2").select2();
	$(".select2").css("margin-left","1px");
	
	//新增spu
	$("#addNewSPU").on('click',function(){
		var paramType = $(this).attr("paramType");
		if('1' == paramType){
			pageii = $.layer({
				type : 2,
				title : '商品管理-->新增SPU',
				shadeClose : true,
				maxmin : true,
				fix : false,
				area: ['800px', 500],
				iframe : {
					src : domain + '/item/itemInfo/addItemInfo.htm'
				}
			});
		}else{
			$("#newSPUTr").empty();
			
			var _oldSPUTr = "<td class=\"td_right\">SPU：</td>"+
						"<td class=\"\" colspan='3'>"+
						"<input type=\"text\" id=\"spu\" name=\"spu\" class=\"input-text lh25\" size=\"20\" maxlength=\"50\" />"+
						"</td>";
			$("#oldSPUTr").html(_oldSPUTr);
			$("#addNewSPU").attr("paramType",'1').attr("value","新增SPU");
		
		}
	});
	
	//输入spu时校验spu
	$("#spu").on('blur',function(){
		var _this = $(this);
		var spu = _this.val();
		if(!spu) {
			$("#promptMsg").empty(); 
			return;
		}
		var regex = /^\d*$/;
		if(!regex.test(spu)){
			$("#promptMsg").text("SPU输入有误,只能包含数字");
			return;
		}
		$("#promptMsg").empty();
		
		$.post(domain + '/item/itemInfo/itemInfoJson',{"spu":spu},function(data){
			$("#promptMsg").empty();
			if(!data){
				$("#promptMsg").text("该spu不存在");
				return;
			}
			
		},'text');
		
	});
	
	$("#inputFormSaveBtn").on('click',function(){
		$.ajax({
			url : 'save',
			dataType : 'text',
			data : $('#itemSaveForm').serialize(),
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

function imgUpload(){
	if($( "#picker" ).is("div")){
		var uploader = WebUploader.create({
 			auto:true,
		    // swf文件路径
		    swf: domain + '/statics/backend/js/webuploader/Uploader.swf',
		    // 文件接收服务端。
		    server: domain+'/upload/img/item',
		    // 选择文件的按钮。可选。
		    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
		    pick: {id:'#picker',multiple:true},
		    duplicate:true,
		    // 只允许选择图片文件。
		    accept: {
		    	title: 'Images',
	            extensions: 'gif,jpg,jpeg,bmp,png',
	            mimeTypes: 'image/*'
		    },
		    fileSingleSizeLimit :204800,
		    fileNumLimit  :10
		});
		uploader.addButton({
		    id: '#submitIMG',
		    innerHTML: '上传文件'
		});
		
		/** 当有文件添加进来的时候**/
		uploader.on( 'filesQueued', function( files ) {
			var  newNum= files.length;
			var fileNum = $("input[name='picList']").length;
			if(newNum+fileNum>10){
				alert( 'Error:超过图片数量限制！ ' );
				for(var i=0;i<fileNum;i++){
					uploader.removeFile( files[i],true );
				}
				return;
			}
		});
	    uploader.onError = function( code ) { 
	               switch(code) {
	                   case 'F_EXCEED_SIZE':
	                       text = '文件大小超出,最大200K';
	                       break;
	                   case 'F_DUPLICATE':
	                       text = '不能选择重复的文件！';
	                       break;
	                   default:
	                       text = '上传失败，请重试';
	                       break; 
	           }
	        alert( 'Error: ' + text );
	    };
		uploader.on( 'uploadSuccess', function( file,response ) {
			if(response.code&&response.code=='F_EXCEED_SIZE_LIMIT'){
				var  text = '文件大小超出,最大200K';
				 uploader.removeFile( file,true );
				alert( 'Error: ' + text );
				return;
			}
			//var  newNum= uploader.getFiles().length;
			var fileNum = $("input[name='picList']").length;
			if(fileNum>9){
				 uploader.removeFile( file );
				return false;
			}
			 setTimeout(function () {
				 var $li=$("#sortable");
				    var  imgdiv ="<li class='ui-state-default' ><div class='item'> <img  src='"+response.path+"' ondblclick='imgremove(this)'/><input name='picList' type='hidden' value='"+response.key+"'>";
				    $li.append(imgdiv);
				    var el = document.getElementById('sortable');
					new Sortable(el);
			 }, 1000);
			
	       
		});
		
		/*uploader.on( 'uploadError', function(file,reason) {
			alert(reason);
			 switch(reason) {
             case 'F_EXCEED_SIZE':
                 text = '文件大小超出,最大200K';
                 break;
             case 'F_DUPLICATE':
                 text = '不能选择重复的文件！';
                 break;
             default:
                 text = '上传失败，请重试';
                 break; 
			     }
			 uploader.removeFile( file );
			  alert( 'Error: ' + text );
		});*/
	
	}
}


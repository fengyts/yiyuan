$(function(){
	if($( "#picker" ).is("div")){
		var uploader = WebUploader.create({
 			auto:true,
		    // swf文件路径
		    swf: domain + '/statics/plugin/baidu_webuploader/Uploader.swf',
		    // 文件接收服务端。
		    server: domain+'/uploadItemFiles',
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
		    fileSingleSizeLimit :2048000
		    /*fileNumLimit  :10*/
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
				for(var i=0;i<newNum;i++){
					uploader.removeFile( files[i],true );
				}
				return false;
			}
		});
	    uploader.onError = function( code ) {
	    	console.log(code);
	    	alert(code);
	               switch(code) {
	                   case 'F_EXCEED_SIZE':
	                       text = '文件大小超出,最大200K';
	                       break;
	                   case 'F_DUPLICATE':
	                       text = '不能选择重复的文件！';
	                       break;
	                   case 'Q_EXCEED_NUM_LIMIT':
	                	   text = '超过图片数量限制！';
	                	   break;
	                   default:
	                       text = '上传失败，请重试';
	                       break; 
	           }
	        alert( 'Error: ' + text );
	    };
		uploader.on( 'uploadSuccess', function( file,response ) {
			console.log("response:"+JSON.stringify(response));
			console.log("response.key:"+response.key);
			console.log(response.code);
			if(response.code&&response.code=='F_EXCEED_SIZE_LIMIT'){
				alert(response.code);
				var  text = '文件大小超出,最大200K';
				 uploader.removeFile( file,true );
				alert( 'Error: ' + text );
				return false;
			}
			//var  newNum= uploader.getFiles().length;
			var fileNum = $("input[name='picList']").length;
			if(fileNum>9){
				 uploader.removeFile( file );
				return false;
			}
			 setTimeout(function () {
				 var $li=$("#sortable");
				 console.log("key:"+response.key);
				 console.log("path:"+response.path);
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
});
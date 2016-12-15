$(document).ready(function() {

	test();
	
	$("#thelist").on('dblclick','.item',function(){
		$(this).remove();
	});

});

function test() {
	
	var $_fileSingleSize = 2000 * 1024; //200KB
	var $_fileNums = 3;//最多3张图片
	 // 优化retina, 在retina下这个值是2
	var ratio = window.devicePixelRatio || 1;
	// 缩略图大小
    var thumbnailWidth = 110 * ratio,
    	thumbnailHeight = 110 * ratio;
    
    // 所有文件的进度信息，key为file id
    var percentages = {};
    // 判断浏览器是否支持图片的base64
    var isSupportBase64 = ( function() {
        var data = new Image();
        var support = true;
        data.onload = data.onerror = function() {
            if( this.width != 1 || this.height != 1 ) {
                support = false;
            }
        }
        data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
        return support;
    } )();
    
    //若需要控制旋转,则需要定义该变量
//    var supportTransition = (function(){
//        var s = document.createElement('p').style,
//        r = 'transition' in s ||
//                'WebkitTransition' in s ||
//                'MozTransition' in s ||
//                'msTransition' in s ||
//                'OTransition' in s;
//	    s = null;
//	    return r;
//    })();

	// 文件上传
	jQuery(function() {
		var $ = jQuery, $list = $('#thelist ul'), $btn = $('#ctlBtn'), state = 'pending', uploader;
		uploader = WebUploader.create({
			//退拽图片容器
			dnd :'#uploader',
			// 不压缩image
			resize : false,
			// swf文件路径
			swf : './expressInstall.swf',
			// 文件接收服务端。
			server:domain+'/upload/img/item.htm',
			// 选择文件的按钮。可选。
			// 内部根据当前运行是创建，可能是input元素，也可能是flash.
			pick : {id:'#picker',label:"点击选择图片",multiple:true},
			accept: 
				{
				    title: 'Images',
				    extensions: 'gif,jpg,jpeg,bmp,png',
				    mimeTypes: 'image/*'
				},
			thumb: 
				{
					width: 110,
				    height: 110,
				    // 图片质量，只有type为`image/jpeg`的时候才有效。
				    quality: 70,
				    // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
				    allowMagnify: true,
				    // 是否允许裁剪。
				    crop: true,
				    // 为空的话则保留原有图片格式。
				    // 否则强制转换成指定的类型。
				    type: 'image/jpeg'
				},
			//文件大小和数量限制
			//fileNumLimit : 3,
			fileSizeLimit: 5 * 1024 * 1024,    // 5 M
            fileSingleSizeLimit: $_fileSingleSize    // 200 KB
		});
		
		//console.log(uploader._widgets[4]);//获取Queue

		uploader.on( 'beforeFileQueued', function(file){
			var _size = file.size;
			if(_size > $_fileSingleSize){
				alert("单张图片大小不得超过200kb");
				return false;
			}
			var _fileNums = this.getFiles().length + 1;
			if(_fileNums > $_fileNums){
				alert("最多3张图片");
				return false;
			}
		});
		
		// 当有文件添加进来的时候
		uploader.on('fileQueued', function(file) {
			var $li = $('<li id="' + file.id + '" class="item">'
					+ '<p class="title">' + file.name + '</p>'	
					+ '<p class="imgWrap"></p></li>'),
				$btns = $('<div class="file-panel">'
					+ '<span class="delete">删除</span>'
					+ '<span class="moveRight">向右移动</span>'
					+ '<span class="moveLeft">向左移动</span>'
					+ '</div>').appendTo($li),
				$wrap = $li.find( 'p.imgWrap' );
			
			$list.append($li);
			
            //创建浏览缩略图
			this.makeThumb( file, function(error, src){
				 var img;

                 if ( error ) {
                     $wrap.text( '不能预览' );
                     return;
                 }

                 if( isSupportBase64 ) {
                     img = $('<img src="'+src+'">');
                     $wrap.empty().append( img );
                 }
			}, thumbnailWidth, thumbnailHeight);
			file.rotation = 0;
			
			$li.on( 'mouseenter', function() {
                $btns.stop().animate({height: 30});
            });
            $li.on( 'mouseleave', function() {
                $btns.stop().animate({height: 0});
            });
            $btns.on( 'click', 'span', function() {
                var index = $(this).index(), deg;

                switch ( index ) {
                    case 0:
                        uploader.removeFile( file );
                        return;
                    case 1:
                        file.rotation += 90;
                        break;
                    case 2:
                        file.rotation -= 90;
                        break;
                }
                
                /*//若需要控制图片旋转时需要,否则上面代码
                 *  switch ( index ) {
	                    case 1:
	                        file.rotation += 90;//不定义supportTransition则无效
	                        break;
	                    case 2:
	                        file.rotation -= 90;//不定义supportTransition则无效
	                        break;
                	}
                	将会无效
                 */
                /*
                  if ( supportTransition ) {
                    deg = 'rotate(' + file.rotation + 'deg)';
                    $wrap.css({
                        '-webkit-transform': deg,
                        '-mos-transform': deg,
                        '-o-transform': deg,
                        'transform': deg
                    });
                } else {
                    $wrap.css( 'filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')');
                }*/
                
            });
			
		});
		
		
		

		// 文件上传过程中创建进度条实时显示。
		uploader.on( 'uploadProgress', function(file, percentage) {
			var $li = $('#' + file.id), $percent = $li
					.find('.progress .progress-bar');

			// 避免重复创建
			if (!$percent.length) {
				$percent = $( '<div class="progress progress-striped active">'
							+ '<div class="progress-bar" role="progressbar" style="width: 0%">'
							+ '</div>' + '</div>').appendTo($li).find('.progress-bar');
			}

			$li.find('p.state').text('上传中');

			$percent.css('width', percentage * 100 + '%');
		});

		uploader.on('uploadSuccess', function(file) {
			$('#' + file.id).find('p.state').text('已上传');
		});

		uploader.on('uploadError', function(file) {
			$('#' + file.id).find('p.state').text('上传出错');
		});

		uploader.on('uploadComplete', function(file) {
			$('#' + file.id).find('.progress').fadeOut();
		});

		uploader.on('all', function(type) {
			if (type === 'startUpload') {
				state = 'uploading';
			} else if (type === 'stopUpload') {
				state = 'paused';
			} else if (type === 'uploadFinished') {
				state = 'done';
			}

			if (state === 'uploading') {
				$btn.text('暂停上传');
			} else {
				$btn.text('开始上传');
			}
		});

		$btn.on('click', function() {
			if (state === 'uploading') {
				uploader.stop();
			} else {
				uploader.upload();
			}
		});
	});

}


<#--
<#include "/common/common.ftl"/>

<@backend title=""
js=[
'/statics/plugin/baidu_webuploader/webuploader.min.js',
'/statics/backend/test.js'
]
css=[
'/statics/plugin/baidu_webuploader/webuploader.css'
]
>
-->
<#assign domain="${requestContextPath.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	
	<style type="text/css">
		.box{
			border:1px solid #a1a1a1;
			border-radius:10px;
			padding:10px 0px 10px 10px;
		}
	
	
	
*{margin:0;padding:0;list-style-type:none;}
body{font-family:Arial;font-size:12pt;color:#333;}
h1{font-size:16pt;}
h2{font-size:13pt;}
/* demo */
.demo{padding:20px;width:800px;margin:20px auto;border:solid 1px black;}
.demo h2{margin:30px 0 20px 0;color:#3366cc;}
/* dragfunction */
.dragfunction{margin:40px 0 0 0;}
.dragfunction dt{height:30px;font-weight:800;}
.dragfunction dd{line-height:22px;padding:0 0 20px 0;color:#5e5e5e;}
/* dragsort */
.dragsort-ver li{height:30px;line-height:30px;}
.dragsort{width:350px;list-style-type:none;margin:0px;}
.dragsort li{float:left;padding:5px;width:100px;height:100px;}
.dragsort div{width:90px;height:50px;border:solid 1px black;background-color:#E0E0E0;text-align:center;padding-top:40px;}
.placeHolder div{background-color:white!important;border:dashed 1px gray!important;}
</style>
	
	<link rel="stylesheet" type="text/css" href="${domain}/statics/plugin/baidu_webuploader/webuploader.css">
	<link rel="stylesheet" type="text/css" href="${domain}/statics/plugin/baidu_webuploader/image-upload/style.css" />
	<script src="${domain}/statics/plugin/jquery/jquery-1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${domain}/statics/plugin/baidu_webuploader/webuploader.min.js"></script>
	<script type="text/javascript" src="${domain}/statics/common/common-js/jquery.dragsort-0.4.min.js"></script>
	<script type="text/javascript" src="${domain}/statics/backend/test.js"></script>
	<script>var domain = "${domain}";</script>
<head>
<body>
	<div id="uploader" class="wu-example box">
		<!--用来存放文件信息-->
		<div id="thelist" class="uploader-list">
			<ul class="filelist" id="dragsort">
			</ul>
		</div>
		<div class="btns">
		    <div id="picker">点击选择图片</div>
		    <button id="ctlBtn" class="btn btn-default">开始上传</button>
		</div>
	</div>
	<input name="list1SortOrder" type="hidden" />
	
</body>
</html>


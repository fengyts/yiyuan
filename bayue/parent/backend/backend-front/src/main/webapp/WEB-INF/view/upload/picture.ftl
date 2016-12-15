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
	
	<style>
		.box{
			border:1px solid #a1a1a1;
			border-radius:10px;
			padding:10px 0px 10px 10px;
		}
	</style>
	
	<link rel="stylesheet" type="text/css" href="${domain}/statics/plugin/baidu_webuploader/webuploader.css">
	<link rel="stylesheet" type="text/css" href="${domain}/statics/plugin/baidu_webuploader/image-upload/style.css" />
	<script src="${domain}/statics/plugin/jquery/jquery-1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${domain}/statics/plugin/baidu_webuploader/webuploader.min.js"></script>
	<script type="text/javascript" src="${domain}/statics/backend/test.js"></script>
	<script>var domain = "${domain}";</script>
<head>
<body>
	<div id="uploader" class="wu-example box">
		<!--用来存放文件信息-->
		<div id="thelist" class="uploader-list">
			<ul class="filelist"></ul>
		</div>
		<div class="btns">
		    <div id="picker">点击选择图片</div>
		    <button id="ctlBtn" class="btn btn-default">开始上传</button>
		</div>
	</div>
</body>
</html>


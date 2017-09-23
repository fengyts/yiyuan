<#include "/common/common.ftl" />
<@backend title="百度上传组件测试" 
js=['/statics/plugin/baidu_webuploader/webuploader.min.js',
'/statics/backend/test.js'
]
css=['/statics/plugin/baidu_webuploader/webuploader.css']>

<div>
	
	<form id="uploadTestFrom" method="post" action="" enctype="multipart/form-data">
		<#include "/upload/add_picture.ftl">
	</form>
	
</div>

</@backend>
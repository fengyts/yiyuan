<#macro snatch title="一元购pc前端页面" 
	js=[] 
	css=[] 
	>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${domain}/statics/common/common-css/msgbox.css">
	<link rel="stylesheet" href="${domain}/statics/common/common-css/common.css">
	<link rel="stylesheet" href="${domain}/statics/common/common-css/main.css">
	<#--
	<link rel="stylesheet" type="text/css" href="${domain}/statics/plugin/bootstrap/bootstrap-3.3.5-dist/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${domain}/statics/plugin/bootstrap/bootstrap-3.3.5-dist/css/bootstrap.min.css">
	-->

	<script type="text/javascript" src="${domain}/statics/plugin/jquery/jquery-1.9.1/jquery.min.js"></script>
	<#--
	<script type="text/javascript" src="${domain}/statics/plugin/bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${domain}/statics/plugin/other/colResizable-1.3.min.js"></script>
	-->
	
	<script type="text/javascript" src="${domain}/statics/common/common-js/jquery.custom.extends.js"></script>
	<script type="text/javascript" src="${domain}/statics/common/common-js/common.js"></script>
	<script type="text/javascript" src="${domain}/statics/common/common-js/util.js"></script>
	<script type="text/javascript" src="${domain}/statics/common/common-js/favrite.js"></script>
		
	<script>
		var domain = "${domain}";
	</script>
	
	<#list css as file>   
		<#if file?lower_case?starts_with('http://')>
			<link rel="stylesheet" href="${file}?v=${version}" />
		<#else>
			<link rel="stylesheet" href="${domain}${file}?v=${version}" />	
		</#if>		
	</#list>
	<#list js as file>
		<#if file?lower_case?starts_with('http://')>   		
			<script type="text/javascript" src="${file}?v=${version}"></script>
		<#else>
			<script type="text/javascript" src="${domain}${file}?v=${version}"></script>
		</#if>
	</#list>
</head>
<body>
	<div class="container_custom">
		<#nested/>
	</div>
</body>
</html>
</#macro>
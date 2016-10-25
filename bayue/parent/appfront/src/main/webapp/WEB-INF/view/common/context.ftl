<#macro appfront title="" 
	js=[] 
	css=[] 
>
	
<!doctype html>
<html lang="zh-CN">
	<head>
	   <meta charset="UTF-8">
	   <#--
	   <link rel="stylesheet" href="${domain}/statics/common/common-css/msgbox.css">
	   <link rel="stylesheet" href="${domain}/statics/common/common-css/common.css">
	   <link rel="stylesheet" href="${domain}/statics/common/common-css/main.css">
	   -->
	   <meta name="viewport" content="initial-scale=1, maximum-scale=1">
	   <link rel="shortcut icon" href="/favicon.ico">
	   <meta name="apple-mobile-web-app-capable" content="yes">
	   <meta name="apple-mobile-web-app-status-bar-style" content="black">
	   
	   <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.css">
	   <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.css">
	   
	   <script src="${domain}/statics/plugin/jquery/jquery-1.9.1/jquery.min.js"></script>
	   <#--<script type="text/javascript" src="${domain}/statics/plugin/other/colResizable-1.3.min.js"></script>-->
	   <#--<script type="text/javascript" src="${domain}/statics/common/common-js/common.js"></script>-->
	   
	   <script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.js' charset='utf-8'></script>
	   <script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.js' charset='utf-8'></script>
	   <script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.js' charset='utf-8'></script>
	   
	   <script>var domain = "${domain}";</script>
	   
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
	
		<div  class="container">
			<#nested/>
		</div>
		
	</body>
	
</html>
</#macro>
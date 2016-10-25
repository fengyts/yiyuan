<#macro backend title="一元购后台管理系统" 
	js=[] 
	css=[] 
	>
<!doctype html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
   <link rel="stylesheet" href="${domain}/statics/common/common-css/msgbox.css">
   <link rel="stylesheet" href="${domain}/statics/common/common-css/common.css">
   <link rel="stylesheet" href="${domain}/statics/common/common-css/main.css">
   <!--供应商common css start-->
   <#--<link rel="stylesheet" href="${domain}/statics/supplier/css/supplier_common.css">-->
   <!--供应商common css end-->
   <script src="${domain}/statics/plugin/jquery/jquery-1.9.1/jquery.min.js"></script>
   <#--<script type="text/javascript" src="${domain}/statics/plugin/other/colResizable-1.3.min.js"></script>-->
   <script type="text/javascript" src="${domain}/statics/common/common-js/common.js"></script>	
   <script type="text/javascript" src="${domain}/statics/common/common-js/favrite.js"></script>	
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
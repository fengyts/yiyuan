<#include "/common/common.ftl" />
<!doctype html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${domain}/statics/common/index.css">
  <link rel="stylesheet" href="${domain}/statics/common/base.css">
  <link rel="stylesheet" href="${domain}/statics/common/main.css">
  <#--
  <link rel="stylesheet" href="${domain}/statics/common/css1.css">
  -->
  <link rel="stylesheet" href="${domain}/statics/common/common.css">
  <#include "/common/domain.ftl" />
  <script type="text/javascript" src="${domain}/statics/plugin/jquery/jquery-1.9.1/jquery.min.js"></script>
  
   <script>
      //var domain = "${domain}";
      //var snatch_domain_http = "${domain}";
      //var search_domain = "${domain}";
  </script>
  
</head>
<body>
<#include "/header.ftl" />

<div class="container">
	<#-- 轮播图 -->
	<div class="wrap">
	<#include "/global/carousel.ftl" />
	</div>
	<#--热销商品模块 -->
	<#include "/subs/hotItem.ftl" />
	<#-- 专场特卖 -->
	<#include "/subs/topicSpecially.ftl" />
	<#-- 即将上线 -->
	<#include "/subs/prepareOnLine.ftl" />
		
</div>


<#include "/footer.ftl" />
<#include "/common/common-js.ftl">
<script type="text/javascript" src="${domain}/statics/common/js/index.js"></script>

</body>
</html>

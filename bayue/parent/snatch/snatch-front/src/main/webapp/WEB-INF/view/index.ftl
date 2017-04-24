<#include "/common/common.ftl" />
<!doctype html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${domain}/statics/common/index.css">
  <link rel="stylesheet" href="${domain}/statics/common/base.css">
  <link rel="stylesheet" href="${domain}/statics/common/main.css">
  <link rel="stylesheet" href="${domain}/statics/common/css.css">
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

<div class="article">
<div>
</div>
<#--
<img width="1920" height="325" src="http://img02.meituncdn.com/group1/M00/AF/EB/wKgyOlfordmAaAJiAADReUZ3pf0291.jpg">
<#include "/global/carousel.ftl" />
-->

<#-- 轮波图 -->
	<#if roll?exists>
	<div class="slider" id="JS_slider">
		<div class="wrap">
			<ul class="pic">
				<#list roll as ad>
					<#if ad_index == 0>
						<li><a target="_blank"  href="${ad.link!}"  data-track="homepage_carousel_pic_p${ad_index}"><img width="1920" height="325" src="${ad.imageSrc!}" /></a></li>
					</#if>
					<#if ad_index != 0>
						<li><a target="_blank"  href="${ad.link!}"  data-track="homepage_carousel_pic_p${ad_index}"><img width="1920" height="325" data-lazyload="${ad.imageSrc!}" /></a></li>
					</#if>
				</#list>
			</ul>
		<#if roll?exists>
			<#if (roll?size!=1)>
				<span class="prev"><i class="iconfont icon-jiantouzuo"  data-track="homepage_carousel_change_c01"></i></span>
				<span class="next"><i class="iconfont icon-jiantouyou"  data-track="homepage_carousel_change_c02"></i></span>
			</#if>
		</#if>
		</div>
	</div>	
	</#if>	

</div>


<#include "/footer.ftl" />
<#include "/common/common-js.ftl">
<script type="text/javascript" src="${domain}/statics/common/js/index.js"></script>


</body>
</html<

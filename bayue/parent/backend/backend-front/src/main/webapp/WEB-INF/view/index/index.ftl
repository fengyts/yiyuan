<#include "/common/common.ftl"/>
<!doctype html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${domain}/statics/themes/base.css">
  <link rel="stylesheet" href="${domain}/statics/themes/layout.css">
  <link rel="stylesheet" href="${domain}/statics/plugin/layer/skin/layer.css">
  
  <!--和iframe内部相同的样式 start-->
  <link rel="stylesheet" href="${domain}/statics/common/common-css/main.css">
  <link rel="stylesheet" href="${domain}/statics/common/common-css/common.css">
  <link rel="stylesheet" href="${domain}/statics/common/common-css/style.css">
  <!--和iframe内部同样式引用end-->
  
  <#--<script src="${domain}/statics/common/common-js/jquery-1.9.1.min.js"></script>-->
  <script src="${domain}/statics/plugin/jquery/jquery-1.9.1/jquery.min.js"></script>
  <#--<script src="${domain}/statics/common/common-js/jquery.form.min.js"></script>-->
  <script src="${domain}/statics/plugin/jquery/jquery.form.2.2.7.js"></script>
  <script src="${domain}/statics/common/common-js/jquery.SuperSlide.js"></script>
  <script src="${domain}/statics/plugin/layer/layer.min.js"></script>
  <script src="${domain}/statics/plugin/layer/extend/layer.ext.js"></script>
  <#--<script src="${domain}/statics/supplier/js/supplier_layer.js"></script>-->
  <script src="${domain}/statics/plugin/jquery/jqueryui/js/jquery-ui-1.9.2.custom.min.js"></script>
  <script src="${domain}/statics/plugin/jquery/jqueryui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
  <#--<script type="text/javascript" charset="utf-8" src="${domain}/statics/supplier/component/date/WdatePicker.js"></script>-->

  <script src="${domain}/statics/themes/layout.js"></script>
  <script>var domain = "${domain}";</script>

  
  <title>后台首页</title>
</head>
<body>
<#include "/index/header.ftl">
<div id="main">
	<div id="east">
		<div class="east-menu">
			<h2>
				<a href="javascript:void(0);">菜单标题</a>
			</h2>
			<ul>
				<li>
					<a href="">菜单项</a>
				</li>
			</ul>
		</div>
	</div>
	<div id="east-seperate">
		<div class="icon-v icon-left">
			图标
		</div>
	</div>
	<div id="west">
		<div id="menubar">
			<a href="javascript:void(0);" id="full_screen_btn" title="全屏">全屏</a>
			<a href="javascript:;" id="btn_tabs_prev" title="后退"></a>
			<a href="javascript:;" id="btn_tabs_next" title="前进"></a>
			<div id="menubar_tabs" class="menubar_tabs">
				<h3 id="tabh3_0">
				<a id="tabli_0" href="javascript:void(0);" onclick="changeContentTab('tabli_0');return false;" class="index-nav currenttab">首&nbsp;&nbsp;页
							&nbsp;
			   	</a>
			   	</h3>		
			</div>
		</div>
		<iframe id="mainIframe_tabli_0" marginheight="100px" marginwidth="10px"
			class="mainIframe" src="${domain}/favrite/list.htm" scrolling="auto" 
			frameborder="0">
		</iframe>
	</div>
</div>
<div style="z-index:9999; background-color:#000; opacity:0.3; filter:alpha(opacity=30);display:none;" class="xubox_shade" id="layAllDiv"></div>

</body>
</html>
   
 
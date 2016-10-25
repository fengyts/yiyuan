<#include "/common/common.ftl"/> 
<@front title="积分规则管理/规则列表"
js=[]  
css=[
   '/statics/plugin/assets/css/fonts/linecons/css/linecons.css',
   '/statics/plugin/assets/css/fonts/fontawesome/css/font-awesome.min.css',
   '/statics/plugin/assets/css/xenon-core.css',
   '/statics/plugin/assets/css/xenon-forms.css',
   '/statics/plugin/assets/css/xenon-components.css',
   '/statics/plugin/assets/css/xenon-skins.css',
   '/statics/plugin/assets/css/custom.css'
] >
    <div class="page-error centered mt30">
		
		<div class="error-symbol">
			<i class="fa-warning"></i>
		</div>
		
		<h3>
			错误 404
			<small>页面未找到</small>
		</h3>
		<p>${exception.message!'很抱歉！您访问的页面不存在......'}</p>
	</div>
	
	<div class="page-error-search centered">
		<a href="javascript:void(0);" class="go-back">
			<i class="fa-angle-left"></i>
			关闭
		</a>
	</div>
	<div class="panel panel-default"><!-- Add class "collapsed" to minimize the panel -->
						<div class="panel-heading">
							<h3 class="panel-title">Normal panel with controls</h3>
							
							<div class="panel-options">
								<a href="#">
									<i class="linecons-cog"></i>
								</a>
								
								<a data-toggle="panel" href="#">
									<span class="collapse-icon">&ndash;</span>
									<span class="expand-icon">+</span>
								</a>
								
								<a data-toggle="reload" href="#">
									<i class="fa-rotate-right"></i>
								</a>
								
								<a data-toggle="remove" href="#">
									×
								</a>
							</div>
						</div>
						
						<div class="panel-body">
							
							<p>Now indulgence dissimilar for his thoroughly has terminated. Agreement offending commanded my an. Change wholly say why eldest period. Are projection put celebrated particular unreserved joy unsatiable its. In then dare good am rose bred or. On am in nearer square wanted. </p>
							<p>She travelling acceptance men unpleasant her especially entreaties law. Law forth but end any arise chief arose. Old her say learn these large. Joy fond many ham high seen this. Few preferred continual sir led incommode neglected. Discovered too old insensible collecting unpleasant but invitation. </p>
			
						</div>
					</div>
	<script>
		var tabId = $("iframe",window.parent.document).last().attr("id") || "";
		$(function(){
			$(".go-back").click(function(){
				if("" != tabId) {
					tabId = tabId.replace("mainIframe_","");
					parent.window.closeTab(tabId);
				}
			});
		});
	</script>
</@front>
   
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
<#--
<#include "/global/carousel.ftl" />
<img width="1920" height="325" src="http://img02.meituncdn.com/group1/M00/AF/EB/wKgyOlfordmAaAJiAADReUZ3pf0291.jpg">
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
<script type="text/javascript" src="//dl.ntalker.com/js/xn6/ntkfstat.js?siteid=kf_9780" charset="utf-8"></script>
<script src="//static.meitun.com/js/lib.js?version="></script>
<script src="//static.meitun.com/js/widgets.js?version="></script>
<script src="//static.meitun.com/meitun/domain/domain.js?version="></script>
<script src="//static.meitun.com/meitun/meitun/js/head.js?version="></script>
<script src="//static.meitun.com/meitun/meitun/js/alert.js?version="></script>
<script src="//static.meitun.com/meitun/meitun/js/metun-front.js?version="></script>
<script src="//static.meitun.com/user/js/user-static/remote/jquery.haitao.collection.js?version="></script>
<script src="//static.meitun.com/user/js/jquery.user.login.js?version="></script>
<script src="//static.meitun.com/user/js/user-static/remote/jquery.user.bindMobile.js?version="></script>
<script type="text/temp" id="sale_today_temp">
		<li>
			<a href=""><img src="http://placehold.it/1180x360"></a>
			<div class="topic-info">
				<div class="dst">
					距抢购结束<span data-cd="1,19,12,26"></span>
					<p class="dleft"></p>
					<p class="dright"></p>
				</div>
				<div class="section">
					<div class="title">
						<img src="http://placehold.it/65x42" alt="" />
						<div class="ms">
							<div class="sup">AUSTRALIA</div>
							<div class="sub">
								澳洲直供 包邮包税
								<a href="#" class="clt">
									<i class="iconfont icon-llalbumdetaildiggno"></i> <em>收藏</em>
								</a>
							</div>
						</div>
					</div>
					<div class="ctxt">
						<div class="sup">贝拉米有机果泥 5口味</div>
						<div class="sub">澳洲原装进口 100%纯天然新鲜果蔬</div>
					</div>
					<div class="pay">
						<div class="sup">美囤价：<b class="rmb">&yen;</b><span class="price1">125</span><span class="price2">.00</span></div>
						<div>参考价：<b class="rmb">&yen;</b>180.00</div>
						<div class="discount end">
							<div class="dis">6.9 <span class="font">折</span></div>
							<div class="flash">售罄</div>
						</div>
					</div>
				</div>
			</div>
		</li>
		</script>


		<script type="text/javascript">
			function countDeadtime(){
					$(".dst span[check=false]").each(function(i) {
					var milliseconds = $(this).attr("dcd");
					if (milliseconds > 0) {
						var counttime = "0,0,0,0";
						var day = parseInt((parseInt(milliseconds)) / (24 * 60 * 60 * 1000));
						var sur1 = milliseconds - day * 24 * 60 * 60 * 1000;

						var hour = parseInt(sur1 / (60 * 60 * 1000));
						var sur2 = sur1 - hour * 60 * 60 * 1000;

						var min = parseInt(sur2 / (60 * 1000));
						var sur3 = sur2 - min * 60 * 1000;

						var mis = parseInt(sur3 / (1000));
						counttime = day + "," + hour + "," + min + "," + mis;
						$(this).attr("data-cd", counttime);
						$(this).cd();
						$(this).attr("check", "true");

					}
				});
			}
			$(function(){
				
				countDeadtime();

				$(".idTabs1").scrollToFixed();
/*
				$('.footer').addClass('scrollblock');
				$('.pic').height( $(window).height() );

				var scrollorama = $.scrollorama({ blocks:'.scrollblock' });

				scrollorama.animate('#p1 .pic',{ delay: 400, duration: 300, property:'opacity', end:0.2 })
				.animate('#p1 .pic',{ delay: 200, duration: 600, property:'zoom', end:1.5 })
				.animate('#p1 .txt',{ duration:500, property:'bottom', end: -500 })
				.animate('#p1 .txt',{ duration:500, property:'opacity', end: 0 })
				.animate('#p2 .pic',{ delay: 1000, duration:300, property:'opacity', end: 0.2 })
				.animate('#p2 .pic',{ delay: 1000, duration:400, property:'zoom', end: 1.5 });


				/**
			 * banner切换
			 */
			$('#JS_slider .pic').switchable({
				triggers: '&nbsp;',
				panels : 'li',
				effect: 'fade',
				prev: '.prev',
				next: '.next',
				autoplay: true,
				interval: 5,
				onSwitch: function(a,b){
					var img = this.container.find('img').eq(b),
							src = img.attr('data-lazyload');
					if( !src ){
						return;
					}
					img.attr('src', src );
					img.removeAttr('data-lazyload');
				}
			});

	(function($){
		$.Lazyload = {
			pageTop: function(settings) {
	            var oDoc = document; 
	            return oDoc.documentElement.clientHeight + Math.max(oDoc.documentElement.scrollTop, oDoc.body.scrollTop) + 
	            settings.threshold;
	        },
	        prestrain: function(settings){
	        	//console.log($('#sale_today li:last-child').offset().top + '---' + this.pageTop(settings))
	        	var $el = $(settings.obj);
	        	if($(settings.obj).length < 1) {
	        		return ;
	        	}
	        	if($(settings.obj).offset().top <= this.pageTop(settings)){
	        		settings.callback && settings.callback();
	        	}
	        }
		}

		$.fn.Lazyload = function(options){
			var settings = {
				obj: '',
				threshold: 0,
				event: "scroll",
				effect: "fadeIn", 
				callback: function() {},
				container: window     
			}
			$.extend(true, settings, options || {});
			//$.Lazyload.prestrain(settings);
			$(settings.container).bind(settings.event, function() {
	            $.Lazyload.prestrain(settings);
	        });
		}
	})(jQuery);
	
	// 商品滚动加载:
			
	var $elComingSoon = $('#sale_today'),
		comingSoonCount = $elComingSoon.attr('count') || 1,
		comingAjaxStatus = false;	
		$(document).Lazyload({
			obj: '.loading',
			threshold: 100,
			callback: function () {	
				// 没有更多数据
				if(comingAjaxStatus || $elComingSoon.attr('count') == -1){
					return;
				}
				
				var trailerda = "loadcount="+ ++comingSoonCount;
				//取首页明日预告
				$.ajax({
							   	 url:meitun_domain+"/haitaodynamic?callback=?",
							   	 data:trailerda,
							   	 type:'post',
							   	 dataType:'json',
							   	 cache:false,
							   	 beforeSend: function(){
							   		comingAjaxStatus = true;
							   	 },
							   	 success:function(result){
							   	 	comingAjaxStatus = false;
							   		 if(result.dateSize == 1 ){
							   		 var html = "";
										 for(var i = 0;i<result.data.length; i++){
										 	var topic = result.data[i];
										 	if(topic["backgroundImg"]!= null && topic["backgroundImg"]!='' && topic["backgroundImg"]!='null'){
										 	html += "<li>";
										 	html += "<div class='img-con'>";
											html += "<a href='"+topic["backgroundUrl"]+"' target='_blank'><img src='"+topic["backgroundImg"]+"' width='750px' height='328px'>";
											html += "<div class=\"corner\"></div></a>";
											html += "</div>";
											html += "<ul class='list-goods'>";
											topicSkuList = topic["topicSkuList"];
											if(topic["topicSkuList"]!= null){
												for(var j = 0; j < topicSkuList.length; j++){
												html += "<li>";
												html += "<a href='//item.meitun.com/itemDetail/"+topicSkuList[j].topicId+"-0-"+topicSkuList[j].sku+".htm?topicId="+topicSkuList[j].topicId+"'  target='_blank' >";
												html += "<div class='img'><img src='"+topicSkuList[j].topicImage+"' alt='' /></div>";
												html += "<div class='right'>";
												html += "<h3>"+topicSkuList[j].name+"</h3>";
												if(topicSkuList[j].hotTitle!= ""){
													html += "<p>"+topicSkuList[j].hotTitle+"</p>";
												}
												
												html += "<div class='price'><span class='new-price's>￥"+topicSkuList[j].topicPrice+"</span><span class='old-price'><del>￥"+topicSkuList[j].mtPrice+"</del></span></div>	";
												html += "</div>";
												html += "</a >";
												html += "</li>";
											  }
										     }	
											
											}
											
											html += "</ul>";
											
											html += "</li>";
										 }
							   		 	$('#sale_today').append(html);
							   		 	$elComingSoon.attr('count', comingSoonCount);
							   		 	collection.init();
							   		 	countDeadtime();
							   		 }else{
							   		 	$elComingSoon.attr('count', -1);
							   			$('.loading').eq(0).hide();
							   		 }
							   		
									
							   	 },
							   	 error:function(XMLHttpRequest, textStatus, errorThrown){
							//          alert(errorThrown);
							        }
							
							 });
							
			}
		});
		

	
				var tabSwitch = false;
				$(".idTabs1").idTabs(function(id) {
					if(tabSwitch) {
						var itop = $(".cms").length > 0 ? ($(".cms").position().top + 370) : 600;
						$("html,body").animate({scrollTop: itop},300);
					}
					tabSwitch = true;
					return true;
				});

			});
		
		</script>

</body>
</html<

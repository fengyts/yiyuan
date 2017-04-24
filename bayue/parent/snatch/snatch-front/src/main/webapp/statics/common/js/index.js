// var TIMER_TIME = "";
//var snatch_domain_http = "${domain}";
$(function(){

	//按回车搜索
	$("#js_keywords").on("keydown", function(e) {
		var key = e.keyCode || e.which;
		var $elKeywords = $('#js_keywords'),
		value = $.trim($elKeywords.val());
		if (key===13) {
			 _myuInfo(2,'search_text',$elKeywords);
			$("#js_search_submit").click();
		}
	});
	
	//点击搜索
	$('#js_search_submit').click(function(){
		var $elKeywords = $('#js_keywords'),
			value = $.trim($elKeywords.val()),
			reg = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]"),
			url = '';
//			url = 'http://search.meitun.com/searchpage?key=';

		if(value.length > 0 && !reg.test(value)){
			window.location.href = url + value;  
		}else{
			//alert('请输入合法关键字');
			$elKeywords.focus();
		}
	});
	
	//导航栏效果
	$('.nav a').each(function(){
		if( location.pathname.length < 2 ){
			$(this).parent().addClass('active');
			return false;
		}
		if( this.href.indexOf(location.pathname) > 0 ){
			var li = $(this).parents('li');
			if( li.parent().hasClass('nav-2nd') ) li = li.parents('li');
			li.addClass('active');
		}
	});

	//取头部图片广告位
	//得到用户信息
	 $.ajax({
//	   	 url:snatch_domain_http+"/headAd?callback=?",
	   	 data:null,
	   	 type:'post',
	   	 dataType:'json',
	   	 cache:false,
	   	 success:function(result){
	   		 if(result.data.ad != null && result.data.ad != ""){
			  		$(".adver").html(result.data.ad);
	   		 }
	   	 },
	   	 error:function(XMLHttpRequest, textStatus, errorThrown){
	//          alert(errorThrown);
	        }
	 });
	 
	//右侧悬浮层
	var aside = $('.aside');
	var timer = 0;
	aside.find('.panel').parent().hover(function() {
		var $this = $(this),
			$panel = $this.find('.panel');
		if (timer) {
			timer = 0
		}
		timer = setTimeout(function() {
			if (!timer) {
				return
			}
			$panel.css('display', 'inline');

			$panel.transition({
				translate: [0, 0],
				opacity: 1
			});
		}, 200)
	}, function() {
		var $this = $(this),
			$panel = $this.find('.panel');
		if (timer) {
			timer = 0
		}
		$panel.transition({
			translate: [-50, 0],
			opacity: 0
		}, function() {
			$(this).hide();
		});
	});
	aside.find('.gotop a').click(function() {
		$('body,html').animate({
			'scrollTop': 0
		});
		return false;
	});

	
//	if(location.pathname!="/rushed"&&location.pathname!="/single"&&location.pathname!="/trailer"&&location.pathname!="/"){
//		$('.line').hide();
//	}else{
//		var left = $('.sitetop .nav li:not(.line)').siblings('.act').position().left,
//		line = $('.sitetop .nav li:not(.line)').siblings('.line');
//		line.transition({ x : left });
//	
//		$('.sitetop .nav').on('mouseenter', 'li:not(.line)', function(){
//			var left = $(this).position().left,
//					line = $(this).siblings('.line');
//			line.stop().transition({ x : left });
//		}).on('mouseleave', function(){
//			var left = $(this).find('.act').position().left,
//					line = $(this).find('.line');
//			line.transition({ x : left });
//		})
//	}
	
	
	//手机浮层 
	$(".phonever").hover(function() {
		$(".overcode").show();
	}, function() {
		$(".overcode").hide();
	});
	
	var ajaxflag = true;
	
	//购物车、手机美囤悬浮层
	$(".cart-warp").hover(function() {
		if($(this).attr("id") == "shop_index_fixedbar"){
			if(ajaxflag) {
				ajaxflag = false;
				//得到购物信息
				 $.ajax({
//			    	 url:snatch_domain_http+"/getShoppingline?callback=?",
			    	 data:null,
			    	 type:'post',
			    	 dataType:'json',
			    	 cache:false,
			    	 success:function(result){
			    		 if(result.data != null && result.data != "" && result.data.shopping != null && result.data.shopping != ""){
			    			 $("#shop_index_fixedbar").html(result.data.shopping);
			    			 //document.getElementById("shop_index").innerHTML=result.data.shopping;
			    			 //时间跟购物车取成一致
//			    			 getShoppingTime();
			    			 
			    			 $('#minishoppngTimer').cd();
			    		 }
			    		 
			    	 },
			    	 error:function(XMLHttpRequest, textStatus, errorThrown){
	//		           alert(errorThrown);
			         }
			 
				 });
			}
		}
		$(this).addClass("on");
		$(".mini-cart, .overcode", this).show();
		
	}, function() {
		ajaxflag = true;
		$(this).removeClass("on");
		$(".mini-cart, .overcode", this).hide();
	});
	
	
	//地区cookies信息
	  var arr,reg=new RegExp("(^| )meitunindexaddr=([^;]*)(;|$)");
	  if(arr=document.cookie.match(reg)){
				//得到中国分区
			 $.ajax({
//		    	 url:snatch_domain_http+"/addrMessage?callback=?",
		    	 data:null,
		    	 type:'post',
		    	 dataType:'json',
		    	 cache:false,
		    	 success:function(result){
		    		 if(result.data.addr != null && result.data.addr != ""){
		    			 $(".area  .area-list").append(result.data.addr);
//		    			 $("#ar-contxt > table").append(result.data.addrFloat);
		    			area = $('.area  .area-list');
		    			area.find('li').removeClass('act');
		    			area.find('li:contains("'+$("[addrid="+unescape(arr[2])+"] ").html()+'")').addClass('act');
		    			area.find('.tag span').text($("[addrid="+unescape(arr[2])+"] ").html());
//		    			$("#area_header").show();
		    			
		    		 }
		    	 },
		    	 error:function(XMLHttpRequest, textStatus, errorThrown){
//		           alert(errorThrown);
		         }
		 
		  });
			
		}else{
			var url=document.referrer;
			 $.ajax({
//		    	 url:snatch_domain_http+"/addrMessage?callback=?",
		    	 data:null,
		    	 type:'post',
		    	 dataType:'json',
		    	 cache:false,
		    	 success:function(result){
		    		 if(result.data.addr != null && result.data.addr != ""){
			    		  $(".area  .area-list").append(result.data.addr);
//						  $(".area").append(result.data.addr);
//						  $("#area_header").append(result.data.addr);
			    		
						  	area = $('.area  .area-list');
				 			area.find('li').removeClass('act');
//				 			area.find('dd span:contains("'+$(".area [addrid="+unescape(arr[2])+"] ").html()+'")').addClass('act');
				 			var are = "上海";
				 		
				 			if(result.data.city != null && result.data.city != ""){
				 				var c = result.data.city;
				 				area.find('li:contains("'+c+'")').addClass('act');
					 			area.find('.tag span').text(c);
					 			are = c;
				 			}else{
					 			area.find('li:contains("'+'上海'+'")').addClass('act');
					 			area.find('.tag span').text("上海");
				 			}
//				 			$("#area_header").show();
				 			//地址的cookies   这里面默认的全是上海 
							var exp_head = new Date(); 
						    exp_head.setTime(exp_head.getTime() + 365*24*60*60*1000); //设定一年
//				 			document.cookie = "meitunindexaddr="+area.find('li:contains("'+are+'") a').attr("addrid")+";expires=" + exp_head.toGMTString()+";path=/;domain="+all_domain;
//				 			document.cookie = "meitunindexareaId=" + area.find('li:contains("'+are+'") a').attr("areaid") + ";expires=" + exp_head.toGMTString()+";path=/;domain="+all_domain;
				 			
				 			 addcookies("meitunindexaddr", area.find('li:contains("'+are+'") a').attr("addrid"), 365*24*60*60,all_domain);
				 			 addcookies("meitunindexareaId", area.find('li:contains("'+are+'") a').attr("areaid"), 365*24*60*60,all_domain);
		    		 }
		    	 },
		    	 error:function(XMLHttpRequest, textStatus, errorThrown){
//		           alert(errorThrown);
		         }
		 
		  });
			 
			 var temparr,tempreg=new RegExp("(^| )meitunfloag=([^;]*)(;|$)"); //首页宣传图片

			 if(location.pathname!="/"){
				 //$(".wel-area").show();
			 }else{
				 if(temparr=document.cookie.match(tempreg)){
					 //$(".wel-area").show();
				 }else{
					 
				 }
			 }
				 
		}
	
	
		$(".wel-area").on('click', '.close', function() {
			$(".wel-area").remove();
			return false;
		});
	
		
	
	//地区选择
	var area = $('.area  .area-list');
	area.removeClass('active');
	if (area.is("div")) {
		area.hover(function(){
			$(this).addClass('active');

		},function(){
			$(this).removeClass('active');

		}).on('click', 'li', function(){

			area.find('li').removeClass('act');
			$(this).addClass('act');
			area.find('.tag span').text($(this).text());
			area.removeClass('active');
			//地址的cookies
			var areaId = area.find('li:contains("'+$(this).text()+'") a').attr("areaid");
			var addId = area.find('li:contains("'+$(this).text()+'") a').attr("addrid");
			var exp_head = new Date(); 
		    exp_head.setTime(exp_head.getTime() + 365*24*60*60*1000); //设定一年
		   //exp.setTime(exp.getTime() + 2*1000); //设定2个小时
//		   document.cookie = "meitunindexaddr="+addId+";expires=" + exp_head.toGMTString()+";path=/;domain="+all_domain; 
//		   document.cookie = "meitunindexareaId=" + areaId + ";expires=" + exp_head.toGMTString()+";path=/;domain="+all_domain;
		   
		   addcookies("meitunindexaddr", addId, 365*24*60*60,all_domain);
		   addcookies("meitunindexareaId", areaId, 365*24*60*60,all_domain);
		   
		});
	}

	//购物车悬浮窗
	$(".checkout").hover(function() {
		if( $(this).find('dl').css('display')!='none' ){
			return;
		}
		$(this).find(".tag").addClass('on');
		$(this).find('dl').slideDown(200);
	}, function() {
		$(this).find('dl').slideUp(200);
		$(this).find(".tag").removeClass('on');
	}).on("click", ".tag", function(){
		$(this).parent().find('dl').slideUp(200, function(){
			$(this).parent().find(".tag").removeClass('on');
		});
	});

	//加载购物车和用户
	loadUserAndShopoing();
	
	//加个退出事件
	$("#welUser").on("click","a:contains('退出')",function(){
		
//		babyTree.logOut();
		$(this).attr("href",user_domain+"/user/logout");
	});

	
		$('#ar-contxt').on('click', 'td span', function(){
			var area = $(this).parents('#ar-contxt');
			area.find('td span').removeClass('act');
			$(this).addClass('act');
			area.siblings('.close').click();
			
			area = $('.header .area');
			area.find('dd span').removeClass('act');
			area.find('dd span:contains("'+$(this).text()+'")').addClass('act');
			area.find('.tag em').text($(this).text());
				//地址的cookies
			var areaId = area.find('dd span:contains("'+$(this).text()+'")').attr("areaid");
			var exp_head = new Date(); 
		   exp_head.setTime(exp_head.getTime() + 365*24*60*60*1000); //设定一年
		   //exp.setTime(exp.getTime() + 2*1000); //设定2个小时
//		   document.cookie =  "meitunindexaddr=" + $(this).attr("addrid") + ";expires=" + exp_head.toGMTString()+";path=/;domain="+all_domain;
//		   document.cookie =  "meitunindexareaId=" + areaId + ";expires=" + exp_head.toGMTString()+";path=/;domain="+all_domain;
		   addcookies("meitunindexaddr", $(this).attr("addrid"), 365*24*60*60,all_domain);
		   addcookies("meitunindexareaId", areaId, 365*24*60*60,all_domain);
		});
		//分类导航加载
		frontCategory.init();
	
});

/*** 分类导航开始 ***/
var frontCategory = function(){
		var Category = {
			pCategory:"",
			cCategory:new Array()
		},b = {
			loadData:function(){
				$.ajax({
			    	 url:snatch_domain_http+"/loadfc?callback=?",
			    	 data:null,
			    	 type:'post',
			    	 dataType:'json',
			    	 cache:false,
			    	 success:function(result){
			    		 if(result.data != null && result.data.length > 0){
			    			 b.showData(result.data);
			    		 }
			    	 },
			    	 error:function(XMLHttpRequest, textStatus, errorThrown){
//			           alert(errorThrown);
			         }
			 
			  });
			},showData:function(lst){
				$(lst).each(function(index){
					Category.pCategory += '<li>';
					//Category.pCategory += '<a data-track="classify_frist'+(index+1)+'" href="'+(this.isUrlLink?this.pcUrlLink:search_domain+"/search/itempage?fcategid="+this.id)+'">'+this.name+'</a>';
					Category.pCategory += this.name;
					Category.pCategory += '<i class="iconfont icon-jiantouyou"></i></li>';
					var s = '<div class="ctgy-block" style="display: none;">';
					if(this.childs != null && this.childs.length > 0){
						s+='<ul class="cg-pdts clearfix">';
						$(this.childs).each(function(n){
							s+='<li><a data-track="classify_second'+(n+1)+'" href="'+(this.isUrlLink?this.pcUrlLink:search_domain+"/searchnavipage?fiCatgid="+this.id+'&itemName='+this.name)+'">';
							s+='<div class="pdt-icon"><img src="'+this.logoUrl+'" alt=""></div><div class="pdt-desc">'+this.name+'</div></a></li>';
							/*s += '<div class="ctgy-group"><div class="cg-tit"><a href="'+(this.isUrlLink?this.pcUrlLink:search_domain+"/search/itempage?fcategid="+this.id)+'">'+this.name+(this.isHighlight ? '' : '<sup>HOT</sup>')+'</a></div>';
							if(this.childs != null && this.childs.length > 0){
								var th = '<div class="cg-items clearfix">';
								$(this.childs).each(function(){
									th += '<a href="'+(this.isUrlLink?this.pcUrlLink:search_domain+"/search/itempage?fcategid="+this.id)+'" title="">'+this.name+(this.isHighlight ? '' : '<sup>HOT</sup>')+'</a>';
								});
								th += '</div>';
								s += th;
							}
							s += '</div>';*/
						});
						s += '</ul>';
					}
					s += '</div>';
					Category.cCategory[Category.cCategory.length] = s;
				});
				var all = '<div class="ctgy-layer"><ul class="layer-left">'+Category.pCategory+'</ul>';
				if(Category.cCategory != null && Category.cCategory.length > 0){
					all += '<div class="layer-right">';
					$(Category.cCategory).each(function(){
						all += this;
					});
					all += '</div>';
				}
				all += '</div>';
				$('.ctgy .icon-jiantouxia').after(all);
				b.bindShow();
			},bindShow:function(){
				$('.ctgy').on("mouseenter",">a",function(event){
					event.preventDefault();
					$(".ctgy-layer .layer-left > li").removeClass("active");
					$(".ctgy-layer .layer-left > li").eq(0).addClass("active");
					$(".layer-right .ctgy-block").hide().eq(0).show();
				}),$(".ctgy-layer .layer-left").on("mouseenter", ">li", function(event) {
					event.preventDefault();
					$(".ctgy-layer .layer-left > li").removeClass("active");
					var ind = $(this).addClass("active").index();
					$(".layer-right .ctgy-block").hide().eq(ind).show();
				});
			}
		},init = {
				loadData:b.loadData
		};
		return {
			init:init.loadData
		};
}();
/*** 分类导航结束 ***/

//document.write(" <script language=\"javascript\" src=\""+user_domain+"\/static\/js\/user-static\/remote\/jquery.babytree.sync.js\" > <\/script>");
//加载用户信息和购物车信息
function  loadUserAndShopoing(){
	
	
	//得到用户信息
		 $.ajax({
//	    	 url:snatch_domain_http+"/userInfo?callback=?",
	    	 data:null,
	    	 type:'post',
	    	 dataType:'json',
	    	 cache:false,
	    	 success:function(result){
	    		 if(result.data.user != null && result.data.user != ""){
			  		$("#welUser").html("您好，欢迎来到美囤妈妈！<a href=\"http:"+user_domain+"/user/userCenter/myMeitun\"  data-track=\"homepage_header_toolbar_myTun\">"+result.data.user+"</a>[<a href=\"javascript:void(0);\" data-track=\"homepage_header_toolbar_quit\">退出</a>]");
			  		$(".user-info").html("您好，<a href=\"http:"+user_domain+"/user/userCenter/myMeitun\" >"+result.data.user+"</a>");
	    		 }else{
	    			 $("#welUser").html("您好，欢迎来到美囤妈妈！ <a class=\"btn\" href=\"http:"+user_domain+"/user/toLogin\"  data-track=\"homepage_header_toolbar_login\">登录</a><b>|</b><a class=\"btn\" href=\"https:"+user_domain+"/user/toRegister\"  data-track=\"homepage_header_toolbar_regester\">免费注册</a>");
	    			 $(".user-info").html("您好，请<a  data-track=\"homepage_sidebar_account_login\" href=\"http:"+user_domain+"/user/toLogin\" >登录</a>｜<a  data-track=\"homepage_sidebar_account_resgeter\" href=\"https:"+user_domain+"/user/toRegister\">注册</a>");
	    		 }
	    	 },
	    	 error:function(XMLHttpRequest, textStatus, errorThrown){
//	           alert(errorThrown);
	         }
	 
	  });
		
		//得到购物信息
		 $.ajax({
//	    	 url:snatch_domain_http+"/getShoppingCount?callback=?",
	    	 data:null,
	    	 type:'post',
	    	 dataType:'json',
	    	 cache:false,
	    	 success:function(result){
//	    		 var shopSizeHtm = '<div class="tag bag-bg pl40"><i class="fa fa-shopping-cart sign"></i><a href="'+salesorder_domain+'/cart/cart">购物车</a><span class="sign">（</span><a href="'+salesorder_domain+'/cart/cart" class="cartnum sign">'+result.dateSize+'</a><span class="sign">）</span></div>';
	    		 $("#shopingSize").html(result.dateSize);
	    		 $("#asideShopCount").html(result.dateSize);
	    			
	    		 
	    	 },
	    	 error:function(XMLHttpRequest, textStatus, errorThrown){
//	           alert(errorThrown);
	         }
	 
		 });
		
	
}

//给商品详情加侧栏购物车闪动效果
function loadAsideShopping(){
	//得到购物信息
	 $.ajax({
//   	 url:snatch_domain_http+"/getShoppingCount?callback=?",
   	 data:null,
   	 type:'post',
   	 dataType:'json',
   	 cache:false,
   	 success:function(result){
//   		 var shopSizeHtm = '<div class="tag bag-bg pl40"><i class="fa fa-shopping-cart sign"></i><a href="'+salesorder_domain+'/cart/cart">购物车</a><span class="sign">（</span><a href="'+salesorder_domain+'/cart/cart" class="cartnum sign">'+result.dateSize+'</a><span class="sign">）</span></div>';
   		 $("#shopingSize").html(result.dateSize);
   		 
   		 
   		 var asidecart = $("#asideShopCount").parent().parent();
   		
   		 asidecart.transition({
							'color' : '#fff',
							'backgroundColor' : '#ED5565'
						}).transition({
							'color' : '',
							'backgroundColor' : ''
						});
						asidecart.find('b').transition({
							'color' : '#ED5565',
							'backgroundColor' : '#fff'
						}).transition({
							'color' : '',
							'backgroundColor' : ''
						});
   		 
   		 $("#asideShopCount").html(result.dateSize);
   			
   		 
   	 },
   	 error:function(XMLHttpRequest, textStatus, errorThrown){
//          alert(errorThrown);
        }

	 });
	
}


//购物车删除 
function del(skuCode,topicId,promotionId,promotionType,redisCartLineKey){
	if(skuCode == null || skuCode == '' || typeof(skuCode)=="undefined"){
		console.log("skuCode is null");
		return;
	}
	if(!promotionId || typeof(promotionId)=="undefined"){
		promotionId = 0;
	}
	if(!promotionType || typeof(promotionType)=="undefined"){
		promotionType = 0;
	}
	if(!topicId || typeof(topicId)=="undefined"){
		topicId = 0;
	}
	if(!redisCartLineKey || typeof(redisCartLineKey)=="undefined"){
		redisCartLineKey = '';
	}
	 var data = "skuCode="+skuCode+"&topicId="+topicId+"&promotionId="+promotionId+"&promotionType="+promotionType+"&redisCartLineKey="+redisCartLineKey;
	 $.ajax({
//    	 url:snatch_domain_http+"/deleShoppingline?callback=?",
    	 data:data,
    	 type:'post',
    	 dataType:'json',
    	 cache:false,
    	 success:function(result){
    		 if(result.data != null && result.data != "" && result.data.shopping != null && result.data.shopping != ""){
    			 $("#shop_index_fixedbar").html(result.data.shopping);
    			 $('.checkout-fix > .tag >a > span').html(result.data.shoppingsize);
    			 //时间跟购物车取成一致
//    			 getShoppingTime();
    			 
    			 
    			 var asidecart = $("#asideShopCount").parent().parent();
	    		 
	    		 asidecart.transition({
								'color' : '#fff',
								'backgroundColor' : '#ED5565'
							}).transition({
								'color' : '',
								'backgroundColor' : ''
							});
							asidecart.find('b').transition({
								'color' : '#ED5565',
								'backgroundColor' : '#fff'
							}).transition({
								'color' : '',
								'backgroundColor' : ''
							});
	    		 
	    		 $("#asideShopCount").html(result.data.shoppingsize);
    			 
    			 
    			 $('#minishoppngTimer').cd();

    		 }
    		 
    	 },
    	 error:function(XMLHttpRequest, textStatus, errorThrown){
//           alert(errorThrown);
         }
 
	 });
}

function addcookies(name, value, maxage,domain){
	var data = "name="+name+"&value="+value+"&maxage="+maxage+"&domain="+domain;
	$.ajax({
//		 url:snatch_domain_http + "/setcook?callback=?",
		 data:data,
		 type:'post',
		 dataType:'json',
		 cache:false,
		 success:function(result){
		 },
		 error:function(XMLHttpRequest, textStatus, errorThrown){
	    }
	
	});
	
}

var windowname = 1;
//联系客服 
function connectcustomer(){
//   		var w = window.open('', 'newwindow'+ ++windowname, 'width=100%,height=100%, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
//   		w.location="http://v2.live800.com/live800/chatClient/chatbox.jsp?companyID=459883&configID=124406&jid=1737204076"; 
//   		w.location = snatch_domain_http+"/customs";
   		//window.open(snatch_domain_http+"/customs","_blank");
	try{
		NTKF.im_openInPageChat();
	}catch(exception) {
		_originalAlert("客服系统繁忙，请稍后再试！");
	}
}

//取得购物车时间
function getShoppingTime(){
	//时间跟购物车取成一致
	$.ajax({
		 url:salesorder_domain_http + "/order/timer?callback=?",
		 data:null,
		 type:'post',
		 dataType:'json',
		 cache:false,
		 success:function(result){
				
				var milliseconds = result;	// 
				var minutes = parseInt(milliseconds / 1000 / 60);
				var seconds = parseInt(milliseconds / 1000) % 60;
				
				var day = milliseconds/(24*60*60*1000);
				
				$('#minishoppngTimer').attr("data-cd","0,0,"+minutes+","+seconds+"");
				$('#minishoppngTimer').cd();
				// 
//				var countDown = new timer(0, 0, minutes, seconds);
//				countDown.onlive = function(t) {
//					TIMER_TIME = t;
//					$('#minishoppngTimer').html(TIMER_TIME);
//				};
//				countDown.ondie = function() {
//					countDown.total = 5 * 60;
//					countDown.begin();
//				};
//				countDown.begin();
			 
		 },
		 error:function(XMLHttpRequest, textStatus, errorThrown){
	    }
	
	});
}


//领取优惠卷
function receiveCoupon(couponID){
	
	//得到用户信息
	 $.ajax({
//    	 url:snatch_domain_http+"/userInfo?callback=?",
    	 data:null,
    	 type:'post',
    	 dataType:'json',
    	 cache:false,
    	 success:function(result){
    		 if(result.data.user != null && result.data.user != ""){
    		 	bindMobileForm.checkBindMobile({
						success:function(){
							receiveCouponOper(couponID);
							
						},
						error:function(){
							
						}
						
					});
    		 }else{
    		 	loginForm.init(function(){
					bindMobileForm.checkBindMobile({
						success:function(){
							receiveCouponOper(couponID);
						},
						error:function(){
							
						}
					});
					
					
				})
    		 }
    		 
    	 },
    	 error:function(XMLHttpRequest, textStatus, errorThrown){
         }
 
  });
}		

function receiveCouponOper(couponID){
		//得到用户信息
	 $.ajax({
//    	 url:snatch_domain_http+"/receiveCoupon/"+couponID+"?callback=?",
    	 data:null,
    	 type:'post',
    	 dataType:'json',
    	 cache:false,
    	 success:function(result){
    		 if(result.result != null){
    		 	if(result.result == 1){
    		 		alert("领取成功");
    		 	}else if(result.result == 0){
    		 		alert(result.message);
    		 	}
    		 	
    		 }else{
    		 	alert("领取失败");
    		 }
    		 
    	 },
    	 error:function(XMLHttpRequest, textStatus, errorThrown){
         }
 
  });
}

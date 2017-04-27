<#--导航栏固定在顶端-->
<script type="text/javascript">
	$(function(){
		var nav=$(".nav"); //得到导航对象
		var win=$(window); //得到窗口对象
		var sc=$(document);//得到document文档对象。
		win.scroll(function(){
			if(sc.scrollTop()>=100){
				nav.addClass("fixednav"); 
				$(".navTmp").fadeIn(); 
			}else{
				nav.removeClass("fixednav");
				$(".navTmp").fadeOut();
			}
		})  
	})
</script>
<style type="text/css">
.fixednav {
    position: fixed;
    top: 0px;
    left: 0px;
    width: 100%;
    z-index: 1;
}
</style>
<div class="sitetop">
	<div class="topbar">
		<div class="wrap">
			<div class="info">
				<a href="http://user.meitun.com/user/myOrder/myOrderManage" data-track="homepage_header_toolbar_myOrder" rel="nofollow">个人中心</a>
				<b>|</b>
				<a href="http://user.meitun.com/user/userCenter/myMeitun" data-track="homepage_header_toolbar_myTun" rel="nofollow">我的宝库</a>
				<b>|</b>
				<span>
					<a href="javascript:;" onclick="connectcustomer();" data-track="homepage_header_toolbar_service" rel="nofollow">在线客服</a>
				</span>
			</div>
			<div class="links">
				<span class="area" style="display:none;">
					您的位置：
					<div class="area-list active" style="top:0;">
						<a href="javascript:void(0);" class="tag"><span></span><i class="iconfont icon-xiajiantou"></i></a>
					</div>
				</span>
				<span id="welUser">您好，欢迎来到盘蛇岛！</span>
			</div>
		</div>
	</div>
	
	<div class="header" id="header">
		<div class="logo">
			<div class="lcon"> 
				<a href="http://www.meitun.com/" data-track="homepage_header_middle_logo">
					<H1>
					<img src="${domain}/statics/images/jubaopen1.jpg" alt="美囤妈妈，最懂育儿" width="300" height="65">
					<img src="${domain}/statics/images/jubaopen.png" alt="美囤妈妈，最懂育儿" width="300" height="65">
					</H1>
				</a>
			</div><!-- http://192.168.58.129/group1/M00/00/00/wKg6gVjU2a2ADs8MAAA-5HtDiBc314.jpg //static.meitun.com/img/logo.png -->
		</div>
		<div class="activity">
			<ul>
				<li>
					<i class="header-icon icon-header-nuskin"></i>
					<label>八月炸正品保证</label>
				</li>
				<li>
					<i class="header-icon icon-header-barter"></i>
					<label>7天无理由退货</label>
				</li>
				<li>
					<i class="header-icon icon-header-kids"></i>
					<label>宝佳优选</label>
				</li>
				<li class="cart-warp" id="shop_index_fixedbar">
					<div class="cart-info"  >
						<i class="iconfont icon-konggouwuche"></i> <a href="//salesorder.meitun.com/cart/cart" data-track="homepage_header_toolbar_miniCart">购物车<span id="shopingSize" class="cartnum">0</span>件</a>
						<!-- <a href="/order" class="cartnum">3</a> -->					
					</div>
				</li>
			</ul>
		</div>
		<div class="nav">
			<ul>
				<li class="ctgy">
					<a href="" data-track="classify_goods">商品分类</a><i class="iconfont icon-jiantouxia"></i>
				</li>
				<li><a href="//www.meitun.com/" data-track="homepage_header_navigation_home">首 页</a></li>
				<li><a href="//www.meitun.com/mall">盘蛇宝岛</a></li>
				<li><a href="//www.meitun.com/haitao" data-track="homepage_header_navigation_haitao">全球购</a></li>
				<!--
				<li>
					<a href="javascript:void(0);">旗舰店</a>
					<ul class="nav-2nd mt_qjd">
						<li class="nbt"><a href="//www.meitun.com/friso"><div class="friso"></div>美素佳儿官方旗舰店</a></li>
						<li><a href="//www.meitun.com/philipsavent"><div class="xay"></div>新安怡官方旗舰店</a></li>
						<li><a href="//www.meitun.com/topic-9557.htm"><div class="wns"></div>郑州维纳斯官方旗舰店</a></li>
						<li><a href="//www.meitun.com/wyeth"><div class="wyeth"></div>惠氏官方旗舰店</a></li>
						<li><a href="//www.meitun.com/meadjohnson"><div class="meadjohnson"></div>美赞臣官方旗舰店</a></li>
					</ul>
				</li>
				-->
				<!--
				<li>
					<a href="javascript:void(0);">奶粉/纸尿裤</a>
					<ul class="nav-2nd only-txt">
						<li class="nbt">
							<a href="javascript:void(0);">奶粉</a>
							<ul class="nav-3rd">
								<li><a href="//www.meitun.com/topic-12037.htm">大牌行货</a></li>
								<li><a href="//www.meitun.com/topic-1547.htm">海外正品</a></li>
							</ul>
						</li>
						<li>
							<a href="javascript:void(0);">纸尿裤</a>
							<ul class="nav-3rd">
								<li><a href="//www.meitun.com/topic-1525.htm">大牌行货</a></li>
								<li><a href="//www.meitun.com/topic-10754.htm">海外正品</a></li>
							</ul>
						</li>
					</ul>
				</li>
				-->
				<li><a href="//www.meitun.com/topic-12687.htm">跨境购</a></li>
				<li class="mtgzs"><a href="//www.meitun.com/activity/mtgzs" target="_blank">消费者告知书</a></li>
				<li class="mtgzs"><a href="//www.meitun.com/activity/aqxts" target="_blank">寻宝攻略</a></li>
				<li class="search">
					<div class="search-wrap clearfix">
						<div class="from-search" style="width:130px">
							<input type="text" style="width:126px" id="js_keywords" placeholder="" >
						</div>
						<span data-track="search_text" class="from-search-btn"  id="js_search_submit"><i></i></span>
					</div>
				</li>
			</ul>
		</div>
		<div class="area" id="area_header" style="display:none;">
			<span class="tag">
				<em>请选择城市</em>
				<i class="fa fa-angle-down"></i>
			</span>
		</div>
	</div>
	
</div>
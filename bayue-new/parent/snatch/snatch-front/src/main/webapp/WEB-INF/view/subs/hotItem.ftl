<#--热销商品模块 -->
<div class="section" id="hot" style="margin-top:10px;">
	<div class="s-hd">
		<h3 class="title">热门宝贝</h3>
	</div>
	<div class="s-bd ui-clr">
		<div class="fn-clear">
			<ul class="win-list ui-clr">
				<#list hotItems as item>
				<li  item="261">
	            	<a class="pic v scrollLoadingDiv" title="${item.itemTitle}" href="./item/261-20.html" target="_blank"><img alt="${item.itemTitle}" src="${img_domain}/${item.picture}"></a> 
	            	<a class="title" title="${item.itemTitle}" href="./item/261-20.html" target="_blank"><#--(第<span item="no">20</span>期)-->${item.itemTitle}</a>
		            <p class="num">总需：${item.snatchNumber} 人次</p>
		            <div class="progressBar">
        				<p class="progressBar-wrap">
            				<span style="width:${item.ratio!0}"></span>
        				</p>
	        			<div class="progressBar-txt">
		            		<div class="txt-l">
		                		<p><b>${item.currentParticipant!0}</b></p>
		                		<p>已参与人次</p>
		            		</div>
				            <div class="txt-r">
				                <p><b>${item.snatchNumber - item.currentParticipant!0}</b></p>
				                <p>剩余人次</p>
				            </div>
				        </div>
			    	</div>
					<div class="btn-go-box">
						<a class="btn-go" href="javascript:void(0)" onclick="addToCart('44766','buy')">立即夺宝</a>
						<a class="btn-go btn-go-cart" href="javascript:void(0)" onclick="addToCart('44766','',this,'02')"></a>
					</div>
	            </li>
				</#list>
				<#--
				<li  item="261">
	            	<a class="pic v scrollLoadingDiv" title="乐视乐2手机（X620）32GB " href="./item/261-20.html" target="_blank"><img alt="乐视乐2手机（X620）32GB " src="http://img.vip.xunlei.com/img/banner/201605051648281834_230x230.png"></a> 
	            	<a class="title" title="乐视乐2手机（X620）32GB " href="./item/261-20.html" target="_blank">(第<span item="no">20</span>期) 乐视乐2手机（X620）32GB </a>
		            <p class="num">总需：159 人次</p>
		            <div class="progressBar">
        				<p class="progressBar-wrap">
            				<span style="width:74.84%"></span>
        				</p>
	        			<div class="progressBar-txt">
		            		<div class="txt-l">
		                		<p><b>119</b></p>
		                		<p>已参与人次</p>
		            		</div>
				            <div class="txt-r">
				                <p><b>40</b></p>
				                <p>剩余人次</p>
				            </div>
				        </div>
			    	</div>
					<div class="btn-go-box">
						<a class="btn-go" href="javascript:void(0)" onclick="addToCart('44766','buy')">立即夺宝</a>
						<a class="btn-go btn-go-cart" href="javascript:void(0)" onclick="addToCart('44766','',this,'02')"></a>
					</div>
	            </li>
	            -->
	            
			</ul>
		</div>
	</div>
</div>
<#--热销商品模块结束 -->

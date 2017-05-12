<#--即将上线商品模块 -->
<div class="section" id="hot" style="margin-top:10px;">
	<div class="s-hd">
		<h3 class="title">即将上线</h3>
	</div>
	<div class="s-bd ui-clr">
		<div class="fn-clear">
			<ul class="win-list ui-clr">
				<#list hotItems as item>
				<li  item="261">
	            	<a class="pic v scrollLoadingDiv" title="${item.itemTitle}" href="./item/261-20.html" target="_blank"><img alt="${item.itemTitle}" src="${img_domain}/${item.picture}"></a> 
	            	<a class="title" title="${item.itemTitle}" href="./item/261-20.html" target="_blank">${item.itemTitle}</a>
		            <p class="num">总需：${item.snatchNumber} 人次</p>
		            <#--
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
			    	-->
	            </li>
				</#list>
	            
			</ul>
		</div>
	</div>
</div>
<#--即将上线商品模块结束 -->

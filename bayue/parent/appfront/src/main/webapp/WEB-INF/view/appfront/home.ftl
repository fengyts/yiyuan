<#include "/common/common.ftl"/>
<@appfront title="" 
js=[
'/statics/appfront/home.js'
] 
css=[
'/statics/common/common.css'
]>
	
    <div class="page-group">
        <div class="page page-current">
        <!-- 你的html代码 -->
        
        <header class="bar bar-nav">
              <a class="button button-link button-nav pull-left" href="/demos/card">
                  <span class="icon icon-left"></span>
                 	 返回
              </a>
              <h1 class="title">我的生活</h1>
          </header>
          <nav class="bar bar-tab">
              <a class="tab-item external active" href="#">
                  <span class="icon icon-home"></span>
                  <span class="tab-label">首页</span>
              </a>
              <a class="tab-item external" href="#">
                  <span class="icon icon-me"></span>
                  <span class="tab-label">我</span>
              </a>
              <a class="tab-item external" href="#">
                  <span class="icon icon-star"></span>
                  <span class="tab-label">收藏</span>
              </a>
              <a class="tab-item external" href="setting.htm">
                  <span class="icon icon-settings"></span>
                  <span class="tab-label">设置</span>
              </a>
          </nav>
        
        
        
         <div class="content">
              <!-- 这里是页面内容区 -->
              
              <div class="content-block">
			      <p><a href="#" class="alert-text">Alert With Text</a></p>
			      <p><a href="#" class="alert-text-title">Alert With Text and Title</a></p>
			      <p><a href="#" class="alert-text-title-callback">Alert With Text and Title and Callback</a></p>
			      <p><a href="#" class="alert-text-callback">Alert With Text and Callback</a></p>
		     </div>
              
              <div class="page-index">
                  <div class="card">
                      <div style="background-image:url(//gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i3/TB10LfcHFXXXXXKXpXXXXXXXXXX_!!0-item_pic.jpg_250x250q60.jpg)" valign="bottom" class="card-header color-white no-border">旅途的山</div>
                      <div class="card-content">
                          <div class="card-content-inner">
                              <p class="color-gray">发表于 2015/01/15</p>
                              <p>此处是内容...</p>
                          </div>
                      </div>
                      <div class="card-footer">
                          <a href="#" class="link">赞1</a>
                          <a href="#" class="link" id="more">更多</a>
                      </div>
                  </div>
                  <div class="card">
                      <div style="background-image:url(//gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i3/TB10LfcHFXXXXXKXpXXXXXXXXXX_!!0-item_pic.jpg_250x250q60.jpg)" valign="bottom" class="card-header color-white no-border">旅途的山</div>
                      <div class="card-content">
                          <div class="card-content-inner">
                              <p class="color-gray">发表于 2015/01/15</p>
                              <p>此处是内容...</p>
                          </div>
                      </div>
                      <div class="card-footer">
                          <a href="#" class="link">赞2</a>
                          <a href="page.htm" class="link">更多</a>
                      </div>
                  </div>
                  <div class="card">
                      <div style="background-image:url(//gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i3/TB10LfcHFXXXXXKXpXXXXXXXXXX_!!0-item_pic.jpg_250x250q60.jpg)" valign="bottom" class="card-header color-white no-border">旅途的山</div>
                      <div class="card-content">
                          <div class="card-content-inner">
                              <p class="color-gray">发表于 2015/01/15</p>
                              <p>此处是内容...</p>
                          </div>
                      </div>
                      <div class="card-footer">
                          <a href="#" class="link">赞</a>
                          <a href="#" class="link">更多</a>
                      </div>
                  </div>
              </div>
         </div>
        
        
        </div>
    </div>


	<script type="text/javascript">
		$.init();
	<script/>

</@appfront>
<#include "/common/common1.ftl"/>

<@backend title="轮播图列表"
js=[
'/statics/backend/item/carousel.js'
]
css=[
]>

<style>
table{
	table-layout:fixed;
}
.tdcontent{
	overflow:hidden;white-space:nowrap;text-overflow:ellipsis;
}
</style>

<div class="box">
<form class="jqtransform" method="post" id="carouselForm" action="${domain}/item/carousel/list.htm">
	
	<div class="box_bottom pb5 pt5 pr10" style="border-top:1px solid #dadada;">
              <div class="search_bar_btn" style="text-align:left;">
                 <#--
                 <input class="btn btn82 btn_search" type="submit" value="查询"/ >
                 <input class="btn btn82 btn_res " type="button" value="重置"  onclick="dataReset('carouselForm')" />
                 -->
                 <input id="carouselAddbtn" class="btn btn82 btn_add addcatabtn" type="button" value="新增" />
              </div>
        </div>
    </div>
	
	
	<div id="table" class="mt10">
        <div class="box span10 oh">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                      <th width="30">ID</th>
	                  <th width="150">图片地址</th>
	                  <th width="60">轮播图顺序</th>
	                  <th width="150">轮播图链接地址</th>
	                  <th width="50">图片状态</th>
	                  <th width="100">备注</th>
	                  <th width="200">创建时间</th>
					  <th width="50">操作</th>
                </tr>
            	<#if page.list?default([])?size!=0>
		            <#list page.getList() as carousel>
		                <tr class="tr" >
				              <td class="td_center">${carousel.id}</td>
		 					  <td class="td_center tdcontent" title="${carousel.picture}">${carousel.picture}</td>
		 					  <td class="td_center">${carousel.sort}</td>
				              <td class="td_center tdcontent" title="${carousel.linkUrl}">${carousel.linkUrl}</td>		            
				              <td class="td_center"><#assign sta="${carousel.status}" /><#if sta=='true'>有效<#else>无效</#if></td>
		                      <td class="td_center">${carousel.remark}</td>					  
				              <td class="td_center">${carousel.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
				              <td class="td_center">
					              <a href="javascript:void(0);" style="color:blue;" class="carouselEditbtn" param='${carousel.id}'>[编辑]</a> &nbsp;
					              <a href="javascript:void(0);" style="color:blue;" class="journalReview" param='${carousel.id}'>[日志]</a>
				              </td>	
			             </tr>
			        </#list>
            	</#if>
            </table>
	    </div>
	</div>
	
</form>
</div>

</@backend>
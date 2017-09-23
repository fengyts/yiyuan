<#--
	分页标签：用于显示数据分页链接。
	pagination：分页对象。
	url：链接地址
	showPageLinkCount:显示的页数链接数
	isShowMoreLI:是否显示“<li>...</li>”提示更多还有更多页数
	isNeedPageTo：是否显示转到指定页数的表单
-->
<#macro pager pagination url="" formId="" showPageLinkCount=10 isShowMoreLI=true isNeedPageTo=true  >
	<#if (pagination.getTotalPageCount())??>
		<#if (pagination.getTotalPageCount()>=1)>
		    <#assign firstPageUrl=url+"?pageNo=1&pageSize="+(pagination.pageSize)>  
            <#assign prePageUrl=url+"?pageNo="+(pagination.pageNo-1)+"&pageSize="+(pagination.pageSize)>  
            <#assign nextPageUrl=url+"?pageNo="+(pagination.pageNo+1)+"&pageSize="+(pagination.pageSize)>  
            <#assign lastPageUrl=url+"?pageNo="+(pagination.getTotalPageCount())+"&pageSize="+(pagination.pageSize)> 
            <#assign ss="${formId}"> 
            <div id="pageNodiv"></div>          
			<ul class="mypager">
				<li class="firstPage">
					<#if (pagination.pageNo>1)>
						<a class="vvssso" href="javascript:void(0);" param="1" >首页</a>
					<#else>
						<span>首页</span>
					</#if>
				</li>
				<li class="prePage">
					<#if (pagination.pageNo>1)>
						<a class="vvssso" href="javascript:void(0);" param="${pagination.pageNo-1}"  >上一页</a>
					<#else>
						<span>上一页</span>
					</#if>
				</li>
				<@outPutPageNo pagination=pagination url=url  formId=formId showPageLinkCount=showPageLinkCount isShowMoreLI=isShowMoreLI  />
		    
				<li class="nextPage">
					<#if (pagination.pageNo<pagination.getTotalPageCount())>
						<a class="vvssso" href="javascript:void(0);" param="${pagination.pageNo+1}" >下一页</a>
					<#else>
						<span>下一页</span>
					</#if>
				</li>
			
				<li class="lastPage">
					<#if (pagination.pageNo<pagination.getTotalPageCount())>
						<a class="vvssso" href="javascript:void(0);" param="${pagination.getTotalPageCount()}" >尾页</a>
					<#else>
						<span>尾页</span>
					</#if>
				</li>
				
				<li class="pageInfo">
					共 ${pagination.getTotalPageCount()} 页
				</li>
				<li class="">每页条数
					<select id="pageSizeaaa" name="pageSize" onchange="pageSizeChange()">
					<option <#if pagination.getPageSize()=='10'>selected='selected'</#if> value='10'>10</option>
					<option <#if pagination.getPageSize()=='20'>selected='selected'</#if> value='20'>20</option>
					<option <#if pagination.getPageSize()=='30'>selected='selected'</#if> value='30'>30</option>
					<option <#if pagination.getPageSize()=='50'>selected='selected'</#if> value='50'>50</option>
					</select>
				</li>		
				<#if isNeedPageTo>
					<li class="pageTo">
							转到第&nbsp;<input type="text" id="pageToNum"  value="" class="pageToNum">&nbsp;页
							<input type="button" id="submitButton4Page" class="formButton" value="确定" onclick="pageNoSelect();" hidefocus=""> 
						<script type="text/javascript">
							 jQuery(document).ready(function(){   
							  $('.vvssso').on('click',function(){
							  var pageNo= $(this).attr('param');			
						      $("input[name='pageNo']").remove();		
							  var hidden="<input type='hidden'  name='startPage' value='"+pageNo+"'/><input type='hidden'  name='pageNo' value='"+pageNo+"'/>"; 
							  $("#pageNodiv input[type='hidden']").remove();
							  $("#pageNodiv").append(hidden);
							    var submithref="${formId}";
							    document.getElementById(submithref).submit();	
							 });							    
							});
							function _jumpTo(pageNo) {
								var count=${pagination.getTotalPageCount()};	
								if (/^\d+$/.test(pageNo)) {
									if(pageNo>count ){
										return count;
									}else if(pageNo<=0){
										return 1;
									} else{
									return pageNo;
									}
								}else{
									return 1;
								}	
						    }; 					  
						
							function pageNoSelect()	{
								  var els=$("#pageSizeaaa").val();
								  var pageNo=$("#pageToNum").val();
								  var pageNom= _jumpTo(pageNo);		
						          $("input[name='pageNo']").remove();		
							      var hidden="<input type='hidden'  name='pageNo' value='"+pageNom+"'/>"; 
							      $("#pageNodiv").append(hidden);
							      var submithref="${formId}";
							      document.getElementById(submithref).submit();
						    };
							function pageSizeChange(){
								  var els=$("#pageSizeaaa").val();
						          $("input[name='pageNo']").remove();		
							      var hidden="<input type='hidden'  name='pageNo' value='1'/>"; 
							      $("#pageNodiv").append(hidden);
							      var submithref="${formId}";
							      document.getElementById(submithref).submit();
						   };
						</script>
					</li>
				</#if>
			</ul>
		</#if>
	</#if>
</#macro>

<#--
	输出分页链接。如果当前页超过 显示的页数链接数 的一半，则当前页居中显示。例如：当前第10页，总共20页，那么显示第6~第15页分页链接，且第10页居中。
	pagination：分页对象。
	url：链接地址
	showPageLinkCount:显示的页数链接数
	isShowMoreLI:是否显示“<li>...</li>”提示更多还有更多页数0
-->
<#macro outPutPageNo pagination url formId showPageLinkCount isShowMoreLI >

	<#--
		└────────────────────────────────────────────────┘
		A												              B
		
		└───────┴────────┘
		A1		PageNo	B1
		
		A->B:getTotalPageCount()
		A1:startIndex
		B1:endIndex
		A1->B1:showPageLinkCount
		A1->PageNo:spaceOFStartIndexToPageNo
		A1->B1 -1:spaceOFStartIndexToPageLinkCount
	-->

	<#if (showPageLinkCount%2==1)>
		<#assign spaceOFStartIndexToPageNo=((showPageLinkCount+1)/2)-1>
	<#else>
		<#assign spaceOFStartIndexToPageNo=(showPageLinkCount/2)-1>
	</#if>
	
	<#assign spaceOFStartIndexToPageLinkCount=(showPageLinkCount-1)>
	
	<#if ((pagination.pageNo-spaceOFStartIndexToPageNo) <= 1) || (pagination.getTotalPageCount()<=showPageLinkCount)>
		<#assign startIndex=1>
		<#assign isNeedStartMore=false>
	<#else>
		<#assign startIndex=(pagination.pageNo-spaceOFStartIndexToPageNo)>
		<#assign isNeedStartMore=true>
	</#if>
	
	<#if ((startIndex+spaceOFStartIndexToPageLinkCount) < pagination.getTotalPageCount())>
		<#assign endIndex=startIndex+spaceOFStartIndexToPageLinkCount>
		<#assign isNeedEndMore=true>
	<#else>
		<#assign endIndex=(pagination.getTotalPageCount())>
		<#assign isNeedEndMore=false>
	</#if>

	<#if isNeedStartMore&&isShowMoreLI><li>...</li></#if>
	<#list startIndex..endIndex as i>
		<#if pagination.pageNo != i>
			<li>
				<a class="vvssso" href="javascript:void(0);" param="${i}">${i}</a>
			</li>
		<#else>
			<li class="currentPage">
				<span>${i}</span>
			</li>
		</#if>
	</#list>
	<#if isNeedEndMore&&isShowMoreLI><li>...</li></#if>
	
	<style type="text/css" mce_bogus="1">
	.mypager {float: right; clear: both; margin-top: 5px;}
	.mypager li{line-height: 18px; display: block; float: left; padding: 0px 5px; margin: 0px 3px; font-size: 12px; border: 1px solid #cccccc;}
	.mypager li:hover{color: #ff9900; border: 1px solid #ff9900;}
	.mypager li:hover a{color: #ff9900;}
	.mypager li a{color: #464646;}
	.mypager li span{color: #cfcfcf;}
	.mypager li.currentPage{border: 1px solid #ff9900; background-color: #ff9900;}
	.mypager li.currentPage span{font-weight: bold; color: #ffffff;}
	.mypager li.pageInfo{color: #464646; border: none; background: none;}
	.mypager li.pageTo{height:20px; color: #464646; border: none; background: none;}
	.mypager li.pageTo input{line-height: 20px;}
	.mypager li.pageTo input.pageToNum{width: 20px; height: 18px; margin-top: 0px; border: 1px solid #cccccc; display:table-cell; vertical-align:top;}
	.mypager li.pageTo input.formButton {width: 40px; background: #F3F3F3;  height: 20px;}
	.mypager li.pageTo input.formButton:hover {background: #ff9900; color: #ffffff;}
	</style>
</#macro>
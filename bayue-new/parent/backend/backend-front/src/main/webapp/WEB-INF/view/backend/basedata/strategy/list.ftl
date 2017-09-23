<#include "/common/common.ftl"/>
<@backend title="攻略列表" js=[
'/statics/plugin/layer/layer.min.js',
'/statics/plugin/jqgrid/js/jquery.jqGrid.min.js',
'/statics/plugin/jqgrid/js/i18n/grid.locale-cn.js',
'/statics/backend/basedata/strategy.js'
] css=[
'/statics/plugin/jqgrid/css/ui.jqgrid.css',
'/statics/plugin/jquery/jqueryui/css/cupertino/jquery-ui-1.9.2.custom.min.css'
]>

<style>
	/* jqgrid 表格内容换行  */
	.ui-jqgrid tr.jqgrow td {
		  white-space: normal !important;
	   /* height:auto; */
		  vertical-align:text-top;
		  padding-top:2px;
	 }
</style>

<form class="jqtransform" method="post" id="strategyListForm" action="${domain}/basedata/strategy/list.htm">
	<div id="search_bar" class="mt10">
		<div class="box">
		
			<div class="box_border">

			<div class="box_bottom pb5 pt5 pr10" style="border-top:1px solid #dadada;">
		        <div class="search_bar_btn" style="text-align:center;">
		              <input class="btn btn82 btn_search" id="searthAtt" type="submit" value="查询" />
		              <input class="btn btn82 btn_res " type="button" value="重置"  onclick="dataReset('strategyListForm')" />
		              <input id="strategyAddBtn" class="btn btn82 btn_add " type="button" value="新增" name="button"/>
		        </div>
            </div>
            
            </div>
        </div>
    </div>
</form>


<table id="tree"></table>
<div id="pager"></div>


</@backend>
<#include "/common/common.ftl"/>
<@backend title="品类列表" js=[
'/statics/plugin/layer/layer.min.js',
'/statics/plugin/jqgrid/js/jquery.jqGrid.min.js',
'/statics/plugin/jqgrid/js/i18n/grid.locale-cn.js',
'/statics/backend/basedata/category.js'
] css=[
'/statics/plugin/jqgrid/css/ui.jqgrid.css',
'/statics/plugin/jquery/jqueryui/css/cupertino/jquery-ui-1.9.2.custom.min.css'
]>

<form class="jqtransform" method="post" id="categoryListForm" action="${domain}/basedata/category/list.htm">
	<div id="search_bar" class="mt10">
		<div class="box">
		
			<div class="box_border">

			<div class="box_bottom pb5 pt5 pr10" style="border-top:1px solid #dadada;">
		        <div class="search_bar_btn" style="text-align:center;">
		              <input class="btn btn82 btn_search" id="searthAtt" type="submit" value="查询" />
		              <input class="btn btn82 btn_res " type="button" value="重置"  onclick="dataReset('categoryListForm')" />
		              <input id="categoryAddBtn" class="btn btn82 btn_add " type="button" value="新增" name="button"/>
		        </div>
            </div>
            
            </div>
        </div>
    </div>
</form>


<table id="tree"></table>
<div id="pager"></div>


</@backend>
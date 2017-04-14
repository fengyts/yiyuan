<#include "/common/common1.ftl"/>
<@backend title="前台分类列表" js=[
'/statics/plugin/jqgrid/js/jquery.jqGrid.min.js',
'/statics/plugin/jqgrid/js/i18n/grid.locale-cn.js',
'/statics/backend/basedata/frontCategory.js'
] css=[
'/statics/plugin/jqgrid/css/ui.jqgrid.css',
'/statics/plugin/jquery/jqueryui/css/cupertino/jquery-ui-1.9.2.custom.min.css'
]>

<form class="jqtransform" method="post" id="frontCategoryListForm" action="${domain}/basedata/frontCategory/list.htm">
	<div id="search_bar" class="mt10">
		<div class="box">
		
			<div class="box_border">

			<div class="box_bottom pb5 pt5 pr10" style="border-top:1px solid #dadada;">
		        <div class="search_bar_btn pl10">
		              <input class="btn btn82 btn_search" id="searthAtt" type="submit" value="查询" />
		              <input class="btn btn82 btn_res " type="button" value="重置"  onclick="dataReset('frontCategoryListForm')" />
		              <input id="frontCategoryAddBtn" class="btn btn82 btn_add " type="button" value="新增" name="button"/>
		              <input id="fcImportBtn" class="btn btn82 btn_import" type="button" value="导入" name="button"/>
		        </div>
            </div>
            
            </div>
        </div>
    </div>
</form>


<table id="tree"></table>
<div id="pager"></div>


</@backend>
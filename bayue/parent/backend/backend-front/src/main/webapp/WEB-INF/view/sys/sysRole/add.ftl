<#include "/common/common.ftl"/>
<@backend title="角色增加" 
js=[
'/statics/plugin/layui-v1.0.2/layui/layui.js',
'/statics/backend/sys/sysRole.js'
] 
css=['/statics/plugin/bootstrap/bootstrap-3.3.5-dist/css/bootstrap.min.css']>

<style type="text/css">
		.self_table td{
	    	vertical-align: middle !important;
	    }
	    .left{
	    	text-align:left !important;
	    }
	    .self_table td{
	    	vertical-align: middle !important;
	    }
	    .right{
	    	text-align:right !important;
	    }
	    .box_border{border:1px solid #d3dbde;}
	    .box {
		    position:absolute; 
		    width:90%;
		    padding-bottom:10%;
		    margin-top:15px;
	    } 
    	.self_label {
    		width: 10.666666666666664%;
    	}
    	.self_input {
    		width: 50.666666666666664%;
    	}
    	.fo{
    		color:red;
    	}
    	.self_connector {
    	    display: table-cell;
		    white-space: nowrap;
		    vertical-align: middle;
    		padding: 6px 7px;
		    font-size: 14px;
		    font-weight: normal;
		    line-height: 1;
		    color: #555555;
		    text-align: center;
		    /* background-color: #eeeeee;
		    border: 1px solid #cccccc; */
		    border-radius: 4px;
    	}
</style>



<div class="container-fluid">

<form class="form-horizontal box box_border" role="form" id="sysRoleAddForm">

	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">角色名称<span class="fo">*</span></label>
		<div class="col-sm-10">
			<input type="text" class="form-control self_input" id="name" name="name" placeholder="请输入角色名称">
		</div>
	</div>
	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">编码(如果不填则默认为角色名称)</label>
		<div class="col-sm-10">
			<input type="text" class="form-control self_input" id="code" name="code" placeholder="请输入编码">
		</div>
	</div>
	<div class="form-group">
		<label class="checkbox-inline">状态:</label>
		<label class="checkbox-inline">
			<input type="radio" name="status" id="status" value="1" checked>有效
		</label>
		<label class="checkbox-inline">
			<input type="radio" name="status" id="status" value="0">无效
		</label>
	</div>
    
    <div class="col-sm-6 panel-toolbar dr-slash-text">
		<input id="btnCancel" class="btn btn-warning" type="button" value="取消"/>
		<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>
	</div>
	
	
</form>


</div>




</@backend>
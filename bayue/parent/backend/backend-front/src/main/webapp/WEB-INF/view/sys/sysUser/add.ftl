<#include "/common/common.ftl"/>
<@backend title="系统用户增加" 
js=[
'/statics/plugin/layui-v1.0.2/layui/layui.js',
'/statics/plugin/ztree/zTree_v3-master/js/jquery.ztree.all.min.js',
'/statics/backend/sys/sysUser.js'
] 
css=[
'/statics/plugin/bootstrap/bootstrap-3.3.5-dist/css/bootstrap.min.css',
'/statics/plugin/ztree/zTree_v3-master/css/zTreeStyle/zTreeStyle.css'
]>

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
    	.tree{
    		padding-left:15px;
    	}
</style>

<div class="container-fluid panel-body box_border">

<form class="form-horizontal dr-form-bordered" role="form" id="sysUserAddForm">

	<div class="form-group">
		<label for="firstname" class="col-xs-2 control-label" style="text-align:right;">登陆账号<span class="fo">*</span></label>
		<div class="col-sm-10">
			<input type="text" class="form-control self_input" id="loginName" name="loginName" placeholder="请输入登陆账号">
		</div>
	</div>
	<div class="form-group">
		<label for="lastname" class="col-xs-2 control-label" style="text-align:right;">姓名<span class="fo">*</span></label>
		<div class="col-sm-10">
			<input type="text" class="form-control self_input" id="userName" name="userName" placeholder="请输入姓名">
		</div>
	</div>
	<div class="form-group">
		<label for="lastname" class="col-xs-2 control-label" style="text-align:right;">email<span class="fo">*</span></label>
		<div class="col-sm-10">
			<input type="text" class="form-control self_input" id="email" name="email" placeholder="请输入email">
		</div>
	</div>
	<div class="form-group">
		<label for="lastname" class="col-xs-2 control-label" style="text-align:right;">手机号<span class="fo">*</span></label>
		<div class="col-sm-10">
			<input type="text" class="form-control self_input" id="mobile" name="mobile" placeholder="请输入手机号">
		</div>
	</div>
	<div class="form-group">
		<label for="lastname" class="col-xs-2 control-label" style="text-align:right;">密码<span class="fo">*</span></label>
		<div class="col-sm-10">
			<input type="password" class="form-control self_input" id="password" name="password" placeholder="请输入密码">
		</div>
	</div>
	<div class="form-group">
		<label for="lastname" style="float:left;">再次输入密码<span class="fo">*</span></label>
		<div class="col-sm-10">
			<input type="password" class="form-control self_input" id="password2" name="password2" placeholder="请再次输入密码">
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
	
	<hr/>
	<label class="control-label">用户角色</label>
	<div class="form-group tree">
		<div class="zTreeDemoBackground left" style="padding-bottom:50px;">
			<ul id="sysRoleTree" class="ztree"></ul>
		</div>
	</div>
	<hr/>
    
    <div class="col-sm-6 panel-toolbar dr-slash-text">
		<input id="btnCancel" class="btn btn-warning" type="button" value="取消"/>
		<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>
	</div>
	
	
</form>

</div>




</@backend>
<#include "/common/common.ftl"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
	<title>盘蛇后台管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${domain}/statics/plugin/font-awesome-4.7.0/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="${domain}/statics/plugin/bootstrap/bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${domain}/statics/themes/login_style.css" />
	<#--
	-->
	<script>
		var domain = "${domain}";
	</script>
	<script type="text/javascript" src="${domain}/statics/plugin/jquery/jquery-1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${domain}/statics/plugin/bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${domain}/statics/plugin/other/supersized.3.2.7.min.js"></script>
	<script type="text/javascript" src="${domain}/statics/plugin/other/supersized-init.js"></script>
</head>
<style type="text/css">
	.form-group {
		width: 70%;
	    min-width: 380px;
	}
	
	.msg{
		text-align:center !important;
		color:red;
	}
</style>

<body>
<div class="dr-login-page">
	<form id="loginForm" action="${domain}/doLogin" method="post">
		<h3 class="form-signin-heading">盘蛇后台管理系统</h3>
		<div class="form-group">
			<span style="color:red;" id="message">${message}</span>
		</div>
		<div class="form-group">
			<div style="display: flex;">
				<div style="float: left; width: 100%;">
					<div class="input-group">
		  				<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
						<input type="text" id="username" name="username" placeholder="用户名" value="${username}" class="form-control" maxlength="50"/>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div style="display: flex;">
				<div style="float: left; width: 100%;">
					<div class="input-group">
		  				<span class="input-group-addon" ><span class="glyphicon glyphicon-lock"></span></span>
						<input type="password" id="password" name="password" placeholder="密码" class="form-control" maxlength="12" />
					</div>
				</div>
			</div>
		</div>
		<#if kaptchaFlag??&&kaptchaFlag=="true">
			<div class="form-group">
				<div style="display: flex;">
					<div style="float: left; width: 65%;">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-picture"></span></span>
							<input type="text" id="kaptchaInput" name="kaptcha" placeholder="图形验证码" class="form-control" maxlength="5" />
						</div>
					</div>
					<div style="float: right; width: 40%; text-align: right;">
					<img id="kaptcha" src="${domain}/kaptcha.htm" style="cursor: pointer; width: 100px; height: 34px;border: 1px solid #cdcdcd;" 
						onclick="changeKaptcha()" alt="图形验证码" title="点击换一张" />
					</div>
				</div>
			</div>
		</#if>
		<div class="form-group">
			<div style="display: flex;">
				<div style="float: left; width: 100%;" class="text-center">
	                    <span class="col-md-8">
	                        <button class="btn btn-primary btn-lg btn-block" type="button" onclick="loginSubmit();">登 录</button>
	                    </span>
						<span style="top:30%;" class="col-md-4">
							<input type="checkbox" name="rememberMe" id="rememberMe">记住我
						</span>
				</div>
			</div>
		</div>
	</form>
</div>
</body>
<script type="text/javascript">
	if (window != top) {
		top.location.href = location.href;
	}
	function loginSubmit(){
	    var username = $("#username").val();
	    var password=$("#password").val();
	    if(!username || null==username || ""==username){
		   $("#message").html("请输入用户名");
		   return false;
	    }
	    if(!password || null==password || ""==password){
		   $("#message").html("请输入密码");
		   return false;
	    }
	    if($("#kaptchaInput").length > 0){
			var kaptchaInput = $("#kaptchaInput").val();
			if(!kaptchaInput || null==kaptchaInput || ""==kaptchaInput){
				$("#message").html("请输入验证码");
				return false;
			}
	    }
	
		$("#loginForm").submit();
	}
	
	function changeKaptcha(){
		$("#kaptcha").attr("src","${domain}/kaptcha.htm?random=" + Math.floor(Math.random() * 100)).fadeIn();
	}
   
</script>
</html>

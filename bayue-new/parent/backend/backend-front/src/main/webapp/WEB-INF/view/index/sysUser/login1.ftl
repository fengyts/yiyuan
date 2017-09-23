<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Backend - 用户登录</title>
	
	<script>
	    if(window.top!=window.self){//存在父页面
		    window.top.location.href="/index";
		}
	</script>
	
	<#include "/common/common.ftl">

	<link rel="stylesheet" href="${login}/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="${login}/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${login}/css/bootstrap.css">
	<link rel="stylesheet" href="${login}/css/xenon-core.css">
	<link rel="stylesheet" href="${login}/css/xenon-forms.css">
	<link rel="stylesheet" href="${login}/css/xenon-components.css">
	<link rel="stylesheet" href="${login}/css/xenon-skins.css">
	<link rel="stylesheet" href="${login}/css/custom.css">

	<script src="${login}/js/jquery-1.11.1.min.js"></script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	<style>
		#has_sub_btn,#sub_btn,#forget_pass_btn{
		    background-color: #cc80ff !important;
		    border-radius: 5px;
		}
		
		#has_sub_btn:hover,#sub_btn:hover,#forget_pass_btn:hover{
		    background-color: #bb57ff !important;
		    border:1px solid #bb57ff !important;
		    box-shadow:0 0 5px #000;
		}
		
		#has_sub_btn:active,#sub_btn:active,#forget_pass_btn:active{
		    background-color: #bb57ff !important;
		    border:1px solid #bb57ff !important;
		    box-shadow:0 0 5px #000;
		}
		
		#has_sub_btn:focus,#sub_btn:focus,#forget_pass_btn:focus{
		    background-color: #bb57ff !important;
		    border:1px solid #bb57ff !important;
		    box-shadow:0 0 5px #000;
		}
		
		.getcode,.btn-danger{
			 border-radius: 5px;
			 height:40px !important;
		}
		
		.getcode:hover,.btn-danger:hover{
		    box-shadow:0 0 5px #000;
		}
		
		.form-group .form-control{
			 border-radius: 5px;
		}
	</style>
	
</head>
<body class="page-body login-page login-light">

	
	<div class="login-container" style="max-width: 465px;">
	
		<div class="login-main-div row">
		
			<div class="col-sm-12">
				<div class="errors-container"></div>
				<@shiro.guest>
				<form role="form" id="login" class="login-form fade-in-effect" autocomplete="off" method="post">
					<div class="login-header">
						<img src="${login}/images/logo3.png" width="120" />
						<span>美囤妈妈后台管理系统</span>
					</div>
					
					<div class="form-group">
						<input type="text" class="form-control"  placeholder="登录名" name="loginName" id="loginName" autocomplete="off" />
					</div>
					
					<div class="form-group">
						<input type="password"  class="form-control" placeholder="密码" name="password" id="password" autocomplete="off" />
					</div>
					
					<div class="form-group col-sm-8" style="padding:0px;margin-right:5px;">
						<input type="text"  class="form-control" placeholder="短信校验码" name="smsCode" id="smsCode" autocomplete="off" />
					</div>
					
					<div class="form-group" style="width:130px;margin-left:275px;">
						<button type="button" class="getcode btn btn-info  btn-block text-left">
							获取短信验证码
						</button>
					</div>
					<div class="form-group">
						<button id="sub_btn" type="submit" class="btn btn-primary  btn-block text-left">
							<i class="fa-lock"></i>
							登      录
						</button>
					</div>
					<div class="login-footer">
						<a href="javascript:void(0);" class="forget-password">忘记密码</a>
					</div>
				</form>
				<form role="form" id="forgetPassword" style="display:none;" class="login-form fade-in-effect" autocomplete="off" method="post">
					<div class="login-header">
						<img src="${login}/images/logo3.png" width="120" />
						<span>美囤妈妈后台管理系统</span>
					</div>
					
					<div class="form-group">
						<input type="text" class="form-control" placeholder="登录名" name="loginName" id="loginName" autocomplete="off" />
					</div>
					<div class="form-group col-sm-8" style="padding:0px;margin-right:5px;">
						<input type="text"  class="form-control" placeholder="短信校验码" name="smsCode" id="smsCode" autocomplete="off" />
					</div>
					<div class="form-group" style="width:130px;margin-left:275px;">
						<input id="forget_pass_btn" type="button" class="btn btn-primary  btn-block text-left" value="获取新密码" />
					</div>
					<div class="login-footer">
						<a href="javascript:void(0);" class="return-login">返回登录</a>
					</div>
				</form>
				</@shiro.guest>
				<@shiro.user>
					<form action="${base}/index"  role="form" class="login-form fade-in-effect" autocomplete="off" method="post">
						<div class="login-header">
							<img src="${login}/images/logo3.png" width="120" />
							<span>美囤妈妈后台管理系统</span>
						</div>
						<div class="form-group">
							<label class="control-label" for="username">登录名</label>
							<input type="text" disabled="true" class="form-control" autocomplete="off" value="欢迎您,<@shiro.principal property="userName" />!" />
							<input type="hidden" name="requestToken" id="requestToken" value="${requestToken!}">
						</div>
						<div class="form-group">
							<button id="has_sub_btn" type="submit" class="btn btn-primary  btn-block text-center">
								进入首页
							</button>
						</div>
						<div class="form-group">
							<button type="button" onclick="javascript:window.location.href='${base}/logout';" class="btn btn-danger btn-block text-center">
								注销
							</button>
						</div>
					</form>
				</@shiro.user>
			</div>
		</div>
	</div>

	<script src="${login}/js/bootstrap.min.js"></script>
	<script src="${login}/js/TweenMax.min.js"></script>
	<script src="${login}/js/resizeable.js"></script>
	<script src="${login}/js/joinable.js"></script>
	<script src="${login}/js/xenon-api.js"></script>
	<script src="${login}/js/xenon-toggles.js"></script>
	<script type="text/javascript" src="${validation}/jquery.validate.min.js" ></script>
	<script type="text/javascript" src="${validation}/jquery.validate.method.min.js" ></script>
	<script src="${login}/js/toastr/toastr.min.js"></script>
	<script src="${login}/js/xenon-custom.js"></script>

</body>
</html>

<script type="text/javascript">
var inId;
var j = 60;
$(function(){
	setTimeout(function(){ $(".fade-in-effect").addClass('in'); }, 1);
	
	bindForgetPassword();
	bindSms();
	
	$(".forget-password").click(function(){
		$(".login-main-div").slideUp(400,function(){
			$("#login").hide();
			$("#forgetPassword").show();
			$(".login-main-div").slideDown(400);
		});
	});
	
	$(".return-login").click(function(){
		$(".login-main-div").slideUp(400,function(){
			$("#forgetPassword").hide();
			$("#login").show();
			$(".login-main-div").slideDown(400);
			$(".errors-container .alert").hide().slideDown(200);
		});
	});
	
	
	$("#login").validate({
		rules: {
	        loginName:{required: true},
	        password:{required: true},
	        smsCode:{required: true,smsCode:true}
	    },
	    messages: {
	        loginName: {required:"请输入用户名"},
	        password: {required:"请输入密码"},
	        smsCode: {required:"请输入短信校验码"}
	    },
		
		submitHandler: function(form){
			
			$("#sub_btn").attr("disable","disabled");
			$("#sub_btn").html("登录中...");
	        $.ajax({
	            url: "${base}/doLogin",
	            data: $("#login").serialize(),
	            type:'POST',
	            dataType:'json',
	            success : function(result) {
	            	show_loading_bar({
						delay: .5,
						pct: 1000,
						finish: function(){
							if (result.state) {
								window.location.href="${base}/index";
								
							} else {
								$("#sub_btn").removeAttr("disabled");
								$(".errors-container").html('<div class="alert alert-danger">\
									<button type="button" class="close" data-dismiss="alert">\
										<span aria-hidden="true">&times;</span>\
										<span class="sr-only">Close</span>\
									</button>\
									' + result.message + '\
								</div>');
								
								$(".errors-container .alert").hide().slideDown();
								$(form).find('#password').select();
								$("#sub_btn").html("登      录");
							}
						}
					});
					$(".errors-container .alert").slideUp('fast');
				}
	        });
	
			
		}
	});
	
	$("#forgetPassword").validate({
		rules: {
	        loginName:{required: true},
	        smsCode:{required: true,smsCode:true}
	    },
	    messages: {
	        loginName: {required:"请输入用户名"},
	        smsCode: {required:"请输入短信校验码"}
	    },
		
		submitHandler: function(form){
			
			unBindForgetPassword();
			$("#forget_pass_btn").val("获取中...");
	        $.ajax({
	            url: "${base}/forgetPassword",
	            data: $("#forgetPassword").serialize(),
	            type:'POST',
	            dataType:'json',
	            success : function(result) {
	            	show_loading_bar({
						delay: .5,
						pct: 1000,
						finish: function(){
							bindForgetPassword();
							$("#forget_pass_btn").val("获取新密码");
							if (result.state) {
								$(".errors-container").html('<div class="alert alert-info">\
									<button type="button" class="close" data-dismiss="alert">\
										<span aria-hidden="true">&times;</span>\
										<span class="sr-only">Close</span>\
									</button>新密码已经发送至您的手机，请登录后及时修改密码</div>');
							} else {
								$(".errors-container").html('<div class="alert alert-danger">\
									<button type="button" class="close" data-dismiss="alert">\
										<span aria-hidden="true">&times;</span>\
										<span class="sr-only">Close</span>\
									</button>\
									' + result.message + '\
								</div>');
							}
								$(".errors-container .alert").hide().slideDown();			
						}
					});
					$(".errors-container .alert").slideUp('fast');
				}
	        });
	
			
		}
	});
	
	$("#login .form-group:has(.form-control):first .form-control").focus();
	
});

function bindForgetPassword(){
	$("#forget_pass_btn").bind("click",function(){
		$("#forgetPassword").submit();
	});
}

function unBindForgetPassword(){
	$("#forget_pass_btn").unbind();
}

function unBindSms(){
 	$(".getcode").unbind();
}
 
function bindSms(){
	$(".getcode").bind("click",function(){getSms();});
}
 
function codeMiao(){
	j--;
	$(".getcode").html(j+"秒后重新获得");
	if(j<=0) {
		bindSms();
		$(".getcode").html("获取短信验证码");
		window.clearInterval(inId);
		j=60;
	}
}

function getSms(){
	var loginName = $("#loginName").val();
		
	if(null == loginName || "" == loginName){
		$(".errors-container").html('<div class="alert alert-danger"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>请输入登录名 </div>');
		$(".errors-container .alert").hide().slideDown();
		return;
	}
		
	unBindSms();
	$(".getcode").html("正在发送....");
	$.post("${base}/smsCode",{loginName:loginName},function(result){
		if (result.state) {
			$(".getcode").html(j+"秒后重新获得");
			inId = window.setInterval("codeMiao()",1000);
		} else {
			bindSms();
			$(".getcode").html("获取短信验证码");
			$(".errors-container").html('<div class="alert alert-danger">\
				<button type="button" class="close" data-dismiss="alert">\
					<span aria-hidden="true">&times;</span>\
					<span class="sr-only">Close</span>\
				</button>\
				' + result.message + '\
			</div>');
			
			$(".errors-container .alert").hide().slideDown();			
			
		}
	},"json");

}


</script>
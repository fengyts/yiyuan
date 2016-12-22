<#include "/common/common.ftl"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
	<title>盘蛇后台管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${domain}/statics/login/style_old.css" />
	<script src="${domain}/statics/plugin/jquery/jquery-1.9.1/jquery.min.js"></script>
</head>
<style type="text/css">
	.msg{
		text-align:center !important;
		color:red;
	}
</style>

<body>
<center>
  <br><br><br><br><br><br><br><br><br>
  <table width="684" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="292" align="center" valign="top" background="${domain}/statics/login/images/LoginBg.jpg">
      <table width="350" height="200" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="70" align="center"><h3>盘蛇后台管理系统</h3></td>
        </tr>
        <tr>
          <td align="center" valign="top">
             <form id="loginForm" action="${domain}/doLogin" method="post">
	             <table width="100%" heigth="100%" border="0" cellspacing="0" cellpadding="0">
		                <tr>
		                  <td height="20" colspan="2" align="center" class="STYLE2">
			                  <span style="color:red;" id="message">${message}</span>
		                  </td>
		                </tr>
		                
		                <tr>
		                  <td width="37%" height="30" align="right" class="STYLE2">用户名：</td>
		                  <td width="300" align="left"><input type="text" class="text1" name="username" id="username" value="${username}" /></td>
		                </tr>
		                <tr>
		                  <td height="30" align="right" class="STYLE2">密码：</td>
		                  <td align="left"><input type="password" class="text1"  name="password" id="password" value="${password}" /></td>
		                </tr>
		                <#if kaptchaFlag??&&kaptchaFlag=="true">
		                <tr>
			                <td colspan="2">
				                <div style="padding-left:80px;float:left;">
				                	<input type="text" id="kaptchaInput" name="kaptcha" placeholder="请输入验证码" style="width:100px;height:30px;">
				                </div>
				                <div>
									<img id="kaptcha" src="${domain}/kaptcha.htm" style="cursor: pointer; width: 100px; height: 30px;border: 1px solid #cdcdcd;" onclick="changeKaptcha()" alt="图形验证码" title="点击换一张" />
								</div>
							</td>
		                </tr>
		                </#if>
		                <tr>
		                	<td colspan="2">
			                	<div style="float:left;padding-left:100px;margin-top:10px;">
			                		<input type="checkbox" name="rememberMe" id="rememberMe">记住我
			                	</div>
			                	<div style="padding-left:180px;margin-top:10px;">
			                		<input type="button" name="login" id="button" value="登录" onclick="loginSubmit();">
			                    </div>
		                    </td>
		                </tr>
	              </table>
              </form>
          
          </td>
        </tr>
      </table></td>
    </tr>
  </table>


</center>
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

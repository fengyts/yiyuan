<#include "/common/common.ftl"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
  <title>盘蛇后台管理系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="${domain}/statics/login/style.css" />
</head>
<style type="text/css">
	.msg{
		text-align:center !important;
		color:red;
	}
</style>
<script type="text/javascript" language="JavaScript">

	function loginSubmit(){
		checkParam();
		$("#loginForm").submit();
	}
	
	function checkParam(){
	   if($("#username").val()){  
		   alert("请输入用户名！"); 
		   return false;
	   }
	   if($("password").val()){
		   alert("请输入密码！");
		   return false;
	   }
	      
	}
	
	function isNull(str){
		if ( str == "" ) return true;
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		return re.test(str);
	}
   
</script>

<body>
<center>
  <br><br><br><br><br>
  <table width="684" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="292" align="center" valign="top" background="${domain}/statics/login/Images/LoginBg.jpg">
      <table width="350" height="201" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="72" align="center"><h3>盘蛇后台管理系统</h3></td>
        </tr>
        <tr>
          <td align="center" valign="top">
             <form name="loginForm" action="${domain}/doLogin" method="post" onSubmit="return mycheck()">
	             <table width="100%" heigth="100%" border="0" cellspacing="0" cellpadding="0">
		                <tr>
		                  <td height="20" colspan="2" align="center" class="STYLE2">
			                  <span style="color:red;">${message}</span>
		                  </td>
		                </tr>
		                
		                <tr>
		                  <td width="37%" height="30" align="right" class="STYLE2">用户名：</td>
		                  <td width="300" align="left"><input type="text" name="username" id="username" class="text1" /></td>
		                </tr>
		                <tr>
		                  <td height="30" align="right" class="STYLE2">密码：</td>
		                  <td align="left"><input type="password" name="password" id="password" class="text1" /></td>
		                </tr>
		                <tr>
		                	<td colspan="2">
			                	<div style="float:left;padding-left:100px;margin-top:10px;">
			                		<input type="checkbox" name="rememberMe" id="rememberMe">记住我
			                	</div>
			                	<div style="padding-left:180px;margin-top:10px;">
			                		<input type="submit" name="button" id="button" value="登录" onclick="loginSubmit();">
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
</html>

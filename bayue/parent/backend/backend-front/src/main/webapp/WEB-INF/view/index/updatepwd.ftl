
<div class="mt10" id="forms">
<div class="box_border">
	<div class="box_center ">
		<form id="updatePasswordForm" class="editRole" autocomplete="off">
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="form_table pt15 pb15">
			<tbody>
				<tr>
					<td class="td_right">原始密码：</td>
					<td class="">
						<input type="hidden" id="id" name="id" value="${user.id!}"/>
						<input class="input-text lh30" size="30" type="password" id="password" name="password"/>
						<span id="oldPwdMessage" style="color:red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_right">新密码：</td>
					<td class="">
						<input class="input-text lh30" size="30" type="password" id="password1" name="password1"/>
						<span id="pwdMessage1" style="color:red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_right">确认密码：</td>
					<td class="">
						<input class="input-text lh30" size="30" type="password" id="password2" name="password2"/>
						<span id="pwdMessage2" style="color:red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_right" colspan="2" align="center">
					<div id="div1_submit" style="text-align:left;padding-left:100px;">
						<input id="updatePasswdBtn" class="btn btn82 btn_save2 btn_updatePassword_submit" type="button" value="提交">	
						<input id="cancelbtn" class="btn btn82 btn_del closebtn" type="button" value="取消">
					</div>
					</td>
				</tr>
			</tbody>
			</table>
		</form>
		
	</div>
	
</div>
</div>

<script type="text/javascript">
	//关闭弹出层
	$('.tabclose,#cancelbtn').click(function(){
		layer.close(tabi);
	});
	
	var _src = domain + "/statics/plugin/formValidator/images/onCorrect.gif";
	
	$("#password").blur(function(){
		var v = $(this).val();
		if(!v){
			$("#oldPwdMessage").html("请输入原密码");
			return;
		}
		$("#oldPwdMessage").empty().html("<img src="+ _src + "/><font color='green'>OK</font>");
	});
	$("#password1").blur(function(){
		var v1 = $(this).val();
		if(!v1){
			$("#pwdMessage1").html("请输入新密码");
			return;
		}
		$("#pwdMessage1").empty().html("<img src="+ _src + "/><font color='green'>OK</font>");
	});
	$("#password2").blur(function(){
		var v2 = $(this).val(), v1 = $("#password1").val() ;
		if(!v2 || v2 != v1){
			$("#pwdMessage2").html("不能为空或者两次输入密码不一样");
			return;
		}
		$("#pwdMessage2").empty().html("<img src="+ _src + "/><font color='green'>OK</font>");
	});
	
	$("#updatePasswdBtn").on('click',function(){
		var v = $("#password").val(), v1 = $("#password1").val(), v2 = $("#password2").val();
		//layer.alert("亲，功能尚未上线");
		$.ajax({
			url : domain + '/sys/sysUser/updatePassword',
			dataType : 'json',
			data : {"password":v,"password1":v1,"password2":v2},
			type : "post",
			cache : false,
			error : function(request){
				alert("Server Connection Failure...");
			},
			success : function(res) {
				//var data = JSON.parse(res);
				if (1 == res.result) {// 成功
					layer.alert("修改成功，请重新登陆", 1, function() {
						parent.window.location.reload();
		            	parent.layer.close(parent.pageii);
					});
				} else {// 失败
					layer.alert(res.message, 8);
				}
			}
		});
		
	});
	
</script>

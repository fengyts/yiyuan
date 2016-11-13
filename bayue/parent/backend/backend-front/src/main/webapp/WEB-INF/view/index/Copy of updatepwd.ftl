<#include "/common/common.ftl"/> 
<div class="tab updatePassword-tab">
    <span class="tabmove"></span>
    <div class="tabtit">
        <span class="tabnow">修改密码</span>
        <span><label class="error updatePass-errorMessage"></label></span>
    </div>
    <ul class="tab_main">
        <li class="tab-updatePassword">
        
        </li>
    </ul>
    <div>
    	<div>
	    	请输入原密码：<input type="text" name="oldPasswd" class="input-text lh25" size="30" maxlength="50"/></br></br>
	    	请输入新密码：<input type="text" name="password1" class="input-text lh25" size="30" maxlength="50"/></br></br>
	    	再次输入密码：<input type="text" name="password2" class="input-text lh25" size="30" maxlength="50"/></br></br>
    	</div>
	   <div id="div1_submit" style="text-align:left;">
		   <input id="updatePasswd" class="btn btn82 btn_save2" type="button" value="保存">	
		   <input id="cancelbtn" class="btn btn82 btn_del closebtn" type="button" value="取消" name="button">
	   </div>
    </div>
    <span class="tabclose" title="关闭">X</span>
</div>
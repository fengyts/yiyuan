<#include "/common/common.ftl"/>
<@backend title="违禁词增加" js=[
'/statics/plugin/layer/layer.min.js',
'/statics/backend/basedata/forbiddenWords.js'
] css=[
]>

	<div id="forms" class="mt10">
        <div class="box">
          <div class="box_border"> 
          
          <form class="jqtransform" method="post" id="forbiddenWordsAddForm">
	          <table class="form_table" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>违禁词:</td>
						<td><input type="text" name="words" class="input-text lh25" size="20" maxlength="50"/></td>
					</tr>
					<tr>
						<td>状态:</td>
						<td>
							有效：<input type="radio" name="status" checked="checked" /> &nbsp;&nbsp;
							无效：<input type="radio" name="status" />
						</td>
					</tr>
					<tr>
						<td>备注:</td>
						<td><input type="text" name="remark" class="input-text lh25" size="20" maxlength="50"/></td>
					</tr>
					
					<tr>
					    <td>
					       <div id="div1_submit" style="text-align:center;">
							   <input id="forbiddenWordsSaveBtn" class="btn btn82 btn_save2" type="button" value="保存">			         
						   </div>
					    </td>
					    <td>
					       <div id="div1_submit" style="text-align:center;">
					           <input id="cancelbtn" class="btn btn82 btn_del closebtn" type="button" value="取消" name="button">
						   </div>
					    </td>
					</tr>
					
			  </table>
		  </form>
		  
		  </div>
		</div>
	</div>
		

</@backend>
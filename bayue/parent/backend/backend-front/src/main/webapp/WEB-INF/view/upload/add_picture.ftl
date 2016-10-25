<style>
.file-item ul {list-style:none;}
.file-item ul li {width:100px;height:100px;float:left;margin:10px 0px 0px 10px;}
.file-item .item {width:100px;height:100px;line-height:100px;text-align:center;cursor:pointer;background:#ccc;}
.file-item .item img {width:100px;height:100px;border-radius:6px;}
</style>
<table class="input tabContent">
	<tr>
		<td>
		
		<div id="uploader" class="file-item">
		 <div class="btns">
	        <div id="picker">选择图片</div><span style="margin-left:0px;">可以上传 jpg;jpeg;png;bmp类型图片（图片<font color="red">不可重复</font>，最少3张，最多10张）,图片长宽为800像素,最大200 kb  第一张默认为主图（<font color="red">拖动</font>图片可以改变图片顺序,<font color="red">双击</font>图片进行删除）</span>
	    </div>
		<div id="thelist" class="uploader-list">
		 <div class="file-item thumbnail">
		 <ul id='sortable'>
		<#list picList as picture>
		     <li class="ui-state-default" ><div class="item" >
               <img src="<@itemImageDownload code='${picture}' width='80'/>"   ondblclick='imgremove(this)'  />
				<input name='picList' type='hidden' value='${picture}'/>
			</div></li>	   
		</#list>
		</ul>
		   </div>
		</div>
		
 		</div>
		
		</td>
	</tr>
</table>
<script>
	var el = document.getElementById('sortable');
	var sorta= new Sortable(el);
	function  imgremove(obj){
		var $obj=$(obj);
		$obj.parent().parent().remove();
	}
</script>

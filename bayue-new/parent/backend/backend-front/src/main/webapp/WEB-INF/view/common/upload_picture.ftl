<#--
<link rel="stylesheet" href="${domain}/statics/plugin/baidu_webuploader/image-upload/style.css" />
-->
<div id="wrapper">
	<div id="container">
	    <!--头部，相册选择和格式选择-->
	    <div id="uploader">
	    	
	        <div class="queueList">
	        	<div id="dndArea">
	        		<div id="filePicker"></div>
	        	</div>
	        	<#-- 编辑图片时图片回显 -->
	        	<#if picUrlList?default([])?size!=0>
	        		<ul class="filelist">
	        		<#list picUrlList as picUrl>
	        			<li>
	        				<p class="imgWrap">
	        					<img src="${picUrl.picture}" />
	        				</p>
	        			</li>
	        		</#list>
	        		</ul>
	        	</#if>
	        	<#--
	            <div id="dndArea" class="placeholder">
	                <div id="filePicker"></div>
	                <p>或将照片拖到这里，单次最多可选300张</p>
	            </div>
	        	-->
	        </div>
	        <div class="statusBar" style="display:none;">
	            <div class="progress" >
	                <span class="text">0%</span>
	                <span class="percentage"></span>
	            </div>
	            <div class="info"></div>
	            <div class="btns">
	                <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
	            </div>
	        </div>
				        
	    </div>
	    <!-- 上传成功后文件url路径 -->
	    <div id="imgReturnUrls" style="display:none;">
	    	<input type="hidden" name="picUrls" id="picUrlsInput">
	    </div>
	    
	</div>
</div>
<#--
<script type="text/javascript" src= "${domain}/statics/common/imgupload/upload.js">
-->
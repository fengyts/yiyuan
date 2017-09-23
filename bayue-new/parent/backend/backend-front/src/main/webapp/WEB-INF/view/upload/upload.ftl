<#include "/common/common.ftl">

<@backend title="" js=[
'/statics/plugin/baidu_webuploader/webuploader.min.js'
] css=[
'/statics/plugin/baidu_webuploader/webuploader.css'
]>

    <div id="uploader">
        <div class="queueList">
            <div id="dndArea" class="placeholder">
                <div id="filePicker"></div>
                <p>或将照片拖到这里，单次最多可选300张</p>
            </div>
        </div>
        <div class="statusBar" style="display:none;">
            <div class="progress">
                <span class="text">0%</span>
                <span class="percentage"></span>
            </div>
            <div class="info"></div>
            <div class="btns">
                <div id="filePicker2"></div>
                <div class="uploadBtn">开始上传</div>
            </div>
        </div>
    </div>
    <#--<script type="text/javascript" src="/common/plugin/webuploader/webuploader.upload.js"></script>-->
    <script>
        // 实例化
        var uploader = WebUploader.create({
            pick: {
                id: '#filePicker',
                label: '点击选择图片'
            },
            formData: {
                uid: 123
            },
            dnd: '#dndArea',//拉拽区域div的id
            paste: '#uploader',//黏贴区域
            swf: '/statics/plugin/baidu_webuploader/Uploader.swf',
            chunked: false,
            chunkSize: 512 * 1024,
            server: '/imgUpload/uploadImg.htm',//上传的URL
            // runtimeOrder: 'flash',
             accept: {
                 title: 'Images',
                 extensions: 'gif,jpg,jpeg,bmp,png,jar'
             },
            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,
            fileNumLimit: 300,
            fileSizeLimit: 200 * 1024 * 1024,    // 200 M
            fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
        });
        /** 附件函数
        uploader.on( 'uploadSuccess', function( type ) {
           alert(11000);
        });
        */
    </script>
</body>

</@backend>

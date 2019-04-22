<div id="${id}_wup" class="wup_container ">
  <input id="${id}" name="${bizType}" value="" class="wup_input image "
    data-msg-required="请上传图片"/>
  <input id="${id}__del" name="${bizType}__del" value="" type="hidden"/>
  <div class="area">
    <div id="${id}Uploader" class="wup_img">
      <div class="statusBar" style="display:none;">
        <div class="progress">
            <span class="text">0%</span>
            <span class="percentage"></span>
        </div>
        <div class="info"></div>
        <div class="btns">
            <div id="${id}filePicker2" class="webuploader-container"></div>
            <div class="uploadBtn state-pedding">开始上传</div>
        </div>
      </div>
      <div class="queueList">
        <ul id="${id}fileLists" class="filelist"></ul>
        <div id="${id}dndArea" class="placeholder">
          <div id="${id}filePicker" class="webuploader-container"></div>
          <p>或将照片拖到这里，最多可选 300 张</p>
        </div>
      </div>
    </div> 
  </div>
</div>
<script type="text/javascript">
  $(function() {
    $('#${id}Uploader').webuploader({
      id: '${id}',
      bizKey: '${bizKey}',
      bizType: '${bizType}',
      readonly: false,
      returnPath: false,
      filePathInputId: '',
      fileNameInputId: '',
      uploadType: 'image',
      imageAllowSuffixes: '.gif,.bmp,.jpeg,.jpg,.ico,.png,.tif,.tiff,',
      mediaAllowSuffixes: '.flv,.swf,.mkv,webm,.mid,.mov,.mp3,.mp4,.m4v,.mpc,.mpeg,.mpg,.swf,.wav,.wma,.wmv,.avi,.rm,.rmi,.rmvb,.aiff,.asf,.ogg,.ogv,',
      fileAllowSuffixes: '.doc,.docx,.rtf,.xls,.xlsx,.csv,.ppt,.pptx,.pdf,.vsd,.txt,.md,.xml,.rar,.zip,7z,.tar,.tgz,.jar,.gz,.gzip,.bz2,.cab,.iso,.ipa,.apk,',
      maxFileSize: 500*1024*1024,
      maxUploadNum: 300,
      imageMaxWidth: 1024,
      imageMaxHeight: 768,
      isLazy: false,
      preview: ''
    });
  }); 
</script>
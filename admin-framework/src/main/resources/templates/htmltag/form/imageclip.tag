<input id="${name}" name="${name}" type="hidden" value="" class="">
<a id="${name}Button" href="javascript:" class="btn btn-default ${btnClass! }">${btnText }</a>
<script>
$("#${name}Button,#${imageId}").click(function(){
  js.layer.open({
    type: 2,
    maxmin: true,
    shadeClose: true,
    title: '图片裁剪',
    area: [(js.layer.$(js.layer.window).width() - 150) + 'px',
           (js.layer.$(js.layer.window).height() - 100) + 'px'],
    content: '${ctxPath}/tags/imageclip',
    contentFormData: {
      circle: '${circle}',
      imageSrc: $("#${imageId}").attr('src'),
      imageDefaultSrc: '${imageDefaultSrc}'
    },
    btn: ['<i class="fa fa-check"></i> 确定',
      '<i class="fa fa-eraser"></i> 清除',
      '<i class="fa fa-close"></i> 关闭'],
    btn1: function(index, layero){
      var win = js.layer.iframeWindow(index);
      win.$("#btnGetImageBase64").click();
      $("#${imageId}").attr("src", win.imageBase64);
      $("#${name}").val(win.imageBase64).change();
      try { $('#${name}').valid(); }catch(e){}
    },
    btn2: function(index, layero){
      $("#${imageId}").attr("src","${imageDefaultSrc}");
            $("#${name}").val("EMPTY").change();
    }
  });
});
</script>
<div class="input-group">
  <span class="input-group-addon"><i id="${id }Icon" class="${value!'icon-grid' }"></i></span>
  <input id="${id }" name="${name }" type="text" value="${value }" class="form-control ">
  <span class="input-group-btn"><a id="${id }Button" href="javascript:" class="btn btn-default"><i class="fa fa-search"></i></a></span>
</div>
<script>
$("#${id }Button").click(function(){
  js.layer.open({
    type: 2,
    maxmin: true,
    shadeClose: true,
    title: '图标选择',
    area: [(js.layer.$(js.layer.window).width() - 100) + 'px',
           (js.layer.$(js.layer.window).height() - 100) + 'px'],
    content: '${ctxPath}/tags/iconselect?value='+$("#menuIcon").val(),
    success: function(layero, index){
      var info = '<font color="red" class="pull-left mt10">提示：双击选择图标。</font>';
      layero.find('.layui-layer-btn').append(info);
    },
    btn: ['<i class="fa fa-close"></i> 关闭',
      '<i class="fa fa-eraser"></i> 清除'],
    btn1: function(index, layero){
      var win = js.layer.iframeWindow(index);
      var icon = win.$("#icon").val();
      $("#${id }Icon").attr("class", 'fa fa-fw ' + icon);
      $("#${id }").val(icon).change();
      try { $('${id }').valid(); }catch(e){}
    },
    btn2: function(index, layero){
      $("#${id }Icon").attr("class", "fa fa-fw");
            $("#${id }").val("").change();
    }
  });
});
</script>
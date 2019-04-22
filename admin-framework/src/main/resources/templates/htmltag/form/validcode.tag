<div class="input-group">
  <span class="input-group-addon">验证码：</span>
  <input type="text" id="${name }" name="${name }" class="form-control" required="true" data-msg-required="请填写验证码" remote="/validCode" data-msg-remote="验证码不正确." aria-required="true">
  <span class="input-group-addon p0">
    <img id="${name }Img" class="${name }Img" title="看不清，点击图片刷新" src="/validCode?1539653151250" alt="验证码" style="width:100px;">
  </span>
</div>
<script>
var validCodeImgRefresh = function(){
  var src = '/validCode?'+new Date().getTime();
  $('#${name }Img').attr('src',src).removeClass('hide');
}
$('#${name }Img').click(function(){
  validCodeImgRefresh();
  $('#${name }').val('').focus();
});
$('#${name }').focus(function(){
  if($('#${name }Img').attr('src')==''){
    $('#${name }Img').click();
  }
});
setTimeout(function(){
  validCodeImgRefresh();
}, 1000);
</script>
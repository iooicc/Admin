<%
var id=has(id)?id:"", name=has(name)?name:"", value=has(value)?value:"";
if(has(path)) {
	name=path;
  var form=@this.getParentByTagName('form');
  if(form!=null) {
    value=getProperty(form.attrs['model'], path);
  }
} else {
  
}
%>
<script id="${id }UE" name="${name }" type="text/plain" style="width:100%;height:${height!200}px;">${value}</script>
<script type="text/javascript">
var ${id }UE;
$(function() {
  ${id }UE = UE.getEditor('${id }UE', {
    maximumWords: ${maxlength!10000}, 
    toolbars: [['fullscreen', 'undo', 'redo', '|', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 
        'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 
        'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|', 'simpleupload', 'insertimage', 
        'emotion', 'scrawl', 'insertvideo', 'music', 'attachment']],  
    readonly: false,
    initialFrameHeight: ${height!200},
  });
  // 更新编辑器内容  ${id }UE.updateContent();
  ${id }UE.updateContent = function(){
    if (!${id }UE.hasContents()){
      $('#${id }').val("").change();
    }else{
      var html = ${id }UE.getContent().replace('<!--HTML-->','');
      $('#${id }').val("<!--HTML-->" + html).change();
    }
    if (typeof window.webuploaderRefresh == 'function'){
      window.webuploaderRefresh();
    }
  };
    // 编辑器加载完成事件
  ${id }UE.ready(function(){
    ${id }UE.addListener('contentchange', function(){
      ${id }UE.updateContent();
    });
    if (typeof window.webuploaderRefresh == 'function'){
      window.webuploaderRefresh();
    }
  });
});
$('#${id }').parents('form').submit(function(){
  ${id }UE.updateContent();
});
</script>
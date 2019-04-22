<%
var id=has(id)?id:"", name=has(name)?name:"", value=has(value)?value:"";
var text="";
if(has(path)) {
  name=path;
  var form=@this.getParentByTagName('form');
  if(form!=null) {
    value=getProperty(form.attrs['model'], path);
    text=getProperty(form.attrs['model'], labelPath);
  }
} else {
  
}
%>
<div class="input-group treeselect" id="${id }Div" data-url="${url }">
  <input id="${id }Code" type="hidden" <%if(has(name) && name!="") { %>name="${name }"<%} %> value="${value }" class="isReset"/>
  <input id="${id }Name" type="text" <%if(has(labelPath) && labelPath!="") { %>name="${labelPath }"<%} %> value="${text }" class="form-control ${class }" readonly="readonly" placeholder="" />
  <span class="input-group-btn"><a id="${id }Button" href="javascript:" class="btn btn-default "><i class="fa fa-search"></i></a></span>
</div>
<script>
$("#${id }Button,#${id }Name").click(function(){
  if ($("#${id }Button").hasClass("disabled")){
    return true;
  }
  var options = {
    type: 2,
    maxmin: true,
    shadeClose: true,
    title: '${title}',
    area: ['300px', '400px'],
    content: '${ctxPath}/tags/treeselect',
    contentFormData: {
      url: $('#${id }Div').attr('data-url'),
      checkbox: 'false',
      expandLevel: '-1',
      selectCodes: $("#${id }Code").val(),
      isReturnValue: 'false',
      
      pIdKey: '${pIdKey!'pid'}'
    },
    success: function(layero, index){
      if ($(js.layer.window).width() < 300
        || $(js.layer.window).height() < 400){
        js.layer.full(index);
      }
    },
    btn: ['<i class="fa fa-check"></i> 确定'],
    btn1: function(index, layero){
      var win = js.layer.iframeWindow(index);
      win.$('#keyword').val('').change();
      var codes = [], names = [], nodes;
      if ("false" == "true"){
        nodes = win.tree.getCheckedNodes(true);
      }else{
        nodes = win.tree.getSelectedNodes();
      }
      for(var i=0; i<nodes.length; i++) {
        var code = nodes[i]['false'=='true'?'value':'id'], name = nodes[i]['name'];
        codes.push(code.replace(/^u_/g,''));
        names.push(name.replace(/\([0-9]*\)/g,''));
        break;
      }
      if(typeof treeselectCheck == 'function'){
        if (!treeselectCheck('parent', nodes)){
          return false;
        }
      }
      $("#${id }Code").val(codes.join(',')).change();
      $("#${id }Name").val(names.join(',')).change();
      try { $('#${id }Code,#${id }Name').valid(); }catch(e){}
      if(typeof treeselectCallback == 'function'){
        treeselectCallback('parent', 'ok', index, layero, nodes);
      }
    }
  };
  options.btn.push('<i class="fa fa-eraser"></i> 清除');
  options['btn'+options.btn.length] = function(index, layero){
    $("#${id }Code").val('').change();
    $("#${id }Name").val('').change();
    if(typeof treeselectCallback == 'function'){
      treeselectCallback('parent', 'clear', index, layero);
    }
  };
  options.btn.push('<i class="fa fa-close"></i> 关闭');
  options['btn'+options.btn.length] = function(index, layero){
    if(typeof treeselectCallback == 'function'){
      treeselectCallback('parent', 'cancel', index, layero);
    }
  };
  js.layer.open(options);
});
</script>
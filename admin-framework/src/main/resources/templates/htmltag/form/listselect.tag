<div class="input-group treeselect" id="${id}Div" data-url="${url}">
  <input id="${id}Code" type="hidden" name="" value="" class="isReset"/>
  <input id="${id}Name" type="text" name="" value="" class="form-control  " readonly="readonly" />
  <span class="input-group-btn">
  <a id="${id}Button" href="javascript:" class="btn btn-default "><i class="fa fa-search"></i></a>
  </span>
</div>
<script>
$("#${id}Button,#${id}Name").click(function(){
  if ($("#${id}Button").hasClass("disabled")){
    return true;
  }
  var selectData = {},
  boxWidth = $(top.window).width() - 100,
  boxHeight = $(top.window).height() - 100;
  boxWidth = boxWidth < 350 ? 350 : boxWidth;
  boxHeight = boxHeight < 250 ? 250 : boxHeight;
  if(typeof listselectGetSelectData == 'function'){
    selectData = listselectGetSelectData('${id}');
  }else{
    var codes = $('#${id}Code').val(), names = $('#${id}Name').val();
    if(codes != null && codes != "" && names != null && names != ""){
      var codesArr = codes.split(","), namesArr = names.split(",");
      if (codesArr && namesArr && codesArr.length == namesArr.length){
        for(var i=0; i<codesArr.length; i++) {
          selectData[codesArr[i]] = {${itemCode}: codesArr[i], ${itemName}: namesArr[i]}
        }
      }
    }
  }
  var options = {
    type: 2,
    maxmin: true,
    shadeClose: true,
    title: '${title}',
    area: [boxWidth+'px', boxHeight+'px'],
    content: $('#${id}Div').attr('data-url'),
    contentFormData: {
      checkbox: 'true',
      selectData: js.encodeUrl(JSON.stringify(selectData))
    },
    success: function(layero, index){
      if ($(js.layer.window).width() < boxWidth
        || $(js.layer.window).height() < boxHeight){
        js.layer.full(index);
      }
    },
    btn: ['<i class="fa fa-check"></i> 确定'],
    btn1: function(index, layero){
      var win = js.layer.iframeWindow(index);
      selectData = win.getSelectData();
      if(typeof listselectCheck == 'function'){
        if (!listselectCheck('${id}', selectData)){
          return false;
        }
      }
      if(typeof listselectSetSelectData == 'function'){
        listselectSetSelectData('${id}', selectData);
      }else{
        var codes = [], names = [];
        $.each(selectData, function(key, value){
          codes.push(value['${itemCode}']);
          names.push(value['${itemName}']);
        });
          $('#${id}Code').val(codes.join(',')).change();
          $('#${id}Name').val(names.join(',')).change();
      }
      try { $('#${id}Code,#${id}Name').valid(); }catch(e){}
      if(typeof listselectCallback == 'function'){
        listselectCallback('${id}', 'ok', index, layero, selectData);
      }
    }
  };
  options.btn.push('<i class="fa fa-close"></i> 关闭');
  options['btn'+options.btn.length] = function(index, layero){
    if(typeof listselectCallback == 'function'){
      listselectCallback('${id}', 'cancel', index, layero);
    }
  };
  js.layer.open(options);
});
</script></div>
<%
var id=has(id)?id:"", name=has(name)?name:"", value=has(value)?value:"";
var items=items![];
var itemLabel=itemLabel!"dictLabel", itemValue=itemValue!"dictValue";

if(has(path)) {
  name=path;
  var form=@this.getParentByTagName('form');
  if(form!=null) {
    value=getProperty(form.attrs['model'], path);
  }
} else {
  
}

if(has(dictType)) {
	var list=@DictUtil.getDictList(dictType);
	items = list;
}
%>
<span class="icheck">
<%
for(var ii in items![]) {
  var checked="";
  if(value+""==ii[itemValue]) checked="checked";
%>
<label><input type="radio" name="${name }" value="${ii[itemValue] }" class="${class }" ${checked }>${ii[itemLabel] }</label>
<%} %>
</span>
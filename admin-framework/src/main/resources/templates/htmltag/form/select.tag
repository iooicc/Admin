<%
var id=has(id)?id:"", name=has(name)?name:"", value=has(value)?value:"";
var items=items![];
var itemLabel=itemLabel!"dictLabel", itemValue=itemValue!"dictValue";
var multiple=multiple!'false';
var values=[];

if(has(path)) {
  name=path;
  var form=@this.getParentByTagName('form');
  if(form!=null) {
    value=form.attrs['model'][path];
  }
} else {
}

if(has(dictType)) {
  var list=@DictUtil.getDictList(dictType);
  items = list;
}

if(multiple=='true') {
	values=strutil.split(value, ",");
} else {
	values=[value+""];
}
%>
<select <%if(id!="") { %>id="${id }"<%} %> name="${name }" class="${class!} hide" <%if(multiple=='true') { %>multiple="true"<%} %>>
<%if(blankOption!'false'=='true') { %>
<option value="">&nbsp;</option>
<%} %>
<%
for(var ii in items![]) {
  var selected="";
  if(array.contain(values, ii[itemValue]+"")) selected="selected";
%>
<option value="${ii[itemValue] }" ${selected } >${ii[itemLabel] }<%if(displayValue!"false"=="true") {%> / ${ii[itemValue] }<%} %></option>
<%} %>
</select>
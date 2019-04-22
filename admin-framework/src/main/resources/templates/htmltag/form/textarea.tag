<%
var id=has(id)?id:"", name=has(name)?name:"", value=has(value)?value:"";
if(has(path)) {
  id=path;
  name=path;
	var form=@this.getParentByTagName('form');
	if(form!=null) {
		value=form.attrs['model'][path];
	}
} else {
	
}
%>
<textarea id="${id }" name="${name }" rows="${rows!1 }" <%if(has(maxlength)){ %>maxlength="${maxlength }"<%} %> class="${class }" ${this.exts }>${value }</textarea>
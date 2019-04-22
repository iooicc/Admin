<%
var province="", provincename="";
var city="", cityname="";
var district="", districtname="";

var form=@this.getParentByTagName('form');
if(form!=null) {
  province=getProperty(form.attrs['model'], "province");
  provincename=getProperty(form.attrs['model'], "provincename");
  city=getProperty(form.attrs['model'], "city");
  cityname=getProperty(form.attrs['model'], "cityname");
  district=getProperty(form.attrs['model'], "district");
  districtname=getProperty(form.attrs['model'], "districtname");
} else {
  
}
%>
<div id="zhen" datas-id="" class="citySelect"></div>
<input type="hidden" autocomplete="off" class="hidCode_state" name="province" value="${province }">
<input type="hidden" autocomplete="off" class="hidCode_city"  name="city"     value="${city }">
<input type="hidden" autocomplete="off" class="hidCode_town"  name="district" value="${district }">
<input type="hidden" autocomplete="off" class="hid_state" name="provincename" value="${provincename }">
<input type="hidden" autocomplete="off" class="hid_city"  name="cityname"     value="${cityname }">
<input type="hidden" autocomplete="off" class="hid_town"  name="districtname" value="${districtname }">    
<input type="hidden" autocomplete="off" class="hid_datas" value="${province },${city },${district }" name="xzqh">

<script>
var cityControls = function() {
    var hid_state = $(".hid_state").val()||"请选择省";
    var hid_city = $(".hid_city").val()||"请选择市";
    var hid_town = $(".hid_town").val()||"请选择区";
    var hidCode_state = $(".hidCode_state").val()||"";
    var hidCode_city = $(".hidCode_city").val()||"";
    var hidCode_town = $(".hidCode_town").val()||"";
    $("#zhen").SnAddress({
      columns: [
          {state: "prov", text: "请选择省", hide: false, addclass: ""},
          {state: "city", text: "请选择市", hide: false, addclass: ""},
          {state: "area", text: "请选择区", hide: false, addclass: ""},
          {state: "town", text: "请选择乡镇", hide: false, addclass: ""}
      ],
      url: '${url}',
      complete: function (items,bool) {
        if(bool) {
        //组件初始化被执行
          $(".hid_datas").val(hidCode_state+";"+hidCode_city+";"+hidCode_town);
        } else {
          //选择地址后被执行
          var arr=new Array();
          $.each(items,function(index,value){
            arr[index]=value.code;  
          }) ;
          $(".hid_state").val(items[0].name);
          $(".hid_city").val(items[1].name);
          $(".hid_town").val(items[2].name);
          $(".hidCode_state").val(items[0].code);
          $(".hidCode_city").val(items[1].code);
          $(".hidCode_town").val(items[2].code);
          
          $(".hid_datas").val(arr.join(";"));
        }
      },
      stepMet: function(selected, active_count, g) {
    	  if(typeof stepMet == 'function'){
    		  stepMet(selected, active_count, g)
    	  }
      }
    }, [
        {name: hid_state, code: hidCode_state, id: ''},
        {name: hid_city, code: hidCode_city, id: ''},
        {name: hid_town, code: hidCode_town, id: ''}
    ]);
};
$(function() {
  cityControls();
});
</script>
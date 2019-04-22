page
===
select
@pageTag(){
t1.*
@}
from sys_dict_type t1
where 1=1
@if(!isEmpty(userName) && userName!='') {
and t1.user_name like #'%'+userName+'%'#
@}
@if(!isEmpty(orgCode) && orgCode!='') {
and t1.org_code = #orgCode#
@}



@pageIgnoreTag(){
order by t1.create_date desc
@}

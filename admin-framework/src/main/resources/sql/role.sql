findList
===
select
@pageTag(){
t1.role_code as id,
t1.*
@}
from sys_role t1
where 1=1
@if(!isEmpty(roleName) && roleName!='') {
and t1.role_name like #'%'+roleName+'%'#
@}
@if(!isEmpty(roleCode) && roleCode!='') {
and t1.role_code like #'%'+roleCode+'%'#
@}
@if(!isEmpty(isSys) && isSys!='') {
and t1.is_sys = #isSys#
@}
@pageIgnoreTag(){
order by t1.create_date desc
@}

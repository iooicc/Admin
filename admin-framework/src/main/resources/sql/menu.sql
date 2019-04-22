findList
===
select
t1.menu_code as id,
t1.*
from sys_menu t1
where 1=1
@if(!isEmpty(menuName) && menuName!='') {
and t1.menu_name like #'%'+menuName+'%'#
@}

@if(!isEmpty(parentCode) && parentCode!='') {
and t1.parent_code = #parentCode#
@}
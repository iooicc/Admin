findList
===
select
@pageTag(){
t1.user_code as id,
t1.*
@}
from sys_user t1
where 1=1
@if(!isEmpty(userName) && userName!='') {
and t1.user_name like #'%'+userName+'%'#
@}
@if(!isEmpty(roleCode) && roleCode!='') {
and exists(select 1 from sys_user_role t2 where t1.user_code=t2.user_code and t2.role_code=#roleCode#)
@}


@pageIgnoreTag(){
order by t1.create_date desc
@}

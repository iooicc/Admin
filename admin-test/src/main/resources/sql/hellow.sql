findList
===
select
@pageTag(){
t1.user_code as id,
t1.user_code as uid,
t1.user_name
@}
from sys_user t1
where 1=1
@if(!isEmpty(userName) && userName!='') {
and t1.user_name like #'%'+userName+'%'#
@}

@pageIgnoreTag(){
order by t1.create_date desc
@}
findList
===
select
@pageTag(){
t1.id as id,
t1.*
@}
from sys_msg_inner t1
where 1=1
@if(!isEmpty(msgTitle) && msgTitle!='') {
and t1.msg_title like #'%'+msgTitle+'%'#
@}

@pageIgnoreTag(){
order by t1.create_date desc
@}
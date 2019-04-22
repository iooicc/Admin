findList
===
select
@pageTag(){
t1.id as id,
t1.*
@}
from sys_msg_inner_record t1
 LEFT JOIN sys_msg_inner t2 ON t1.msg_inner_id=t2.id
 where 1=1
@if(!isEmpty(receiveUserName) && receiveUserName!='') {
and t1.receive_user_name like #'%'+receiveUserName+'%'#
@}

@if(!isEmpty(readStatus) && readStatus!='') {
and t1.read_status = #readStatus#
@}
@if(!isEmpty(receiveUserCode) && receiveUserCode!='') {
and t1.receive_user_code = #receiveUserCode#
@}

@pageIgnoreTag(){
order by t2.content_level DESC,t1.read_date desc
@}
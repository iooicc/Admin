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
@if(!isEmpty(orgCode) && orgCode!='') {
and t1.org_code = #orgCode#
@}
@if(!isEmpty(corpCode) && corpCode!='') {
and t1.corp_code = #corpCode#
@}


@pageIgnoreTag(){
order by t1.create_date desc
@}

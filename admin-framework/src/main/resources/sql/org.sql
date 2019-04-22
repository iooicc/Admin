findList
===
select
t1.org_code as id,
t1.*
from sys_org t1
where 1=1
@if(!isEmpty(orgName) && orgName!='') {
and t1.org_name like #'%'+orgName+'%'#
@}

@if(!isEmpty(parentCode) && parentCode!='') {
and t1.parent_code = #parentCode#
@}

@if(!isEmpty(corpCode) && corpCode!='') {
and t1.corp_code = #corpCode#
@}
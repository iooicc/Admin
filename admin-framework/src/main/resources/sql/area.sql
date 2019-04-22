findList
===
select
t1.area_code as id,
t1.*
from sys_area t1
where 1=1
 AND t1.status <> '1' 
@if(!isEmpty(areaCode) && areaCode!='') {
and t1.area_code like #'%'+areaCode+'%'#
@}
@if(!isEmpty(areaName) && areaName!='') {
and t1.area_name like #'%'+areaName+'%'#
@}

@if(!isEmpty(status) && status!='') {
and t1.status = #status#
@}
@if(!isEmpty(parentCode) && parentCode!='') {
and t1.parent_code = #parentCode#
@}
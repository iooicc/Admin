findList
===
select
@pageTag(){
t1.*
@}
from sys_config t1
where 1=1
@if(!isEmpty(configName) && configName!='') {
and t1.config_name like #'%'+configName+'%'#
@}
@if(!isEmpty(configKey) && configKey!='') {
and t1.config_key like #'%'+configKey+'%'#
@}
@if(!isEmpty(isSys) && isSys!='') {
and t1.is_sys = #isSys#
@}
@pageIgnoreTag(){
order by t1.create_date desc
@}

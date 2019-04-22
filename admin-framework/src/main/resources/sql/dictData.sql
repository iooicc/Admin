findList
===
select
t1.dict_code as id,
t1.*
from sys_dict_data t1
where 1=1
@if(!isEmpty(dictType) && dictType!='') {
and t1.dict_type = #dictType#
@}

@if(!isEmpty(parentCode) && parentCode!='') {
and t1.parent_code = #parentCode#
@}

@if(!isEmpty(dictLabel) && dictLabel!='') {
and t1.dict_label like #'%'+dictLabel+'%'#
@}

@if(!isEmpty(dictValue) && dictValue!='') {
and t1.dict_value like #'%'+dictValue+'%'#
@}

@if(!isEmpty(isSys) && isSys!='') {
and t1.is_sys = #isSys#
@}

@if(!isEmpty(status) && status!='') {
and t1.status = #status#
@}
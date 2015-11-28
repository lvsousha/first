<#list tables as table>
create table ${table.prefix}${table.name}s(
<#list table.fields as field>
	${field.prefix}${field.name} ${field.jdbcType} ${field.simpleConstraint}<#if field_has_next>,</#if>
</#list>
<#list table.foreigns as foreign>
	${foreign}<#if foreign_has_next>,</#if>
</#list>
)
<#list table.indexs as ind>
${index}<#if index_has_next>,</#if>
</#list>
</#list>
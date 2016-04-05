package com.zcl.model;
import java.io.Serializable;

<#list table?keys as tablename>
public class ${tablename?cap_first} implements Serializable {
	private static final long serialVersionUID = 1447039680623L;
	<#list table[tablename] as column>
		<#list column?keys as datatype>
	private ${datatype} ${column[datatype]};
		</#list>
	</#list>
	
	<#list table[tablename] as column>
		<#list column?keys as datatype>
	public void set${column[datatype]?cap_first}(${datatype} ${column[datatype]}){
		this.${column[datatype]} = ${column[datatype]};
	}

	public ${datatype} get${column[datatype]?cap_first}(){
		return ${column[datatype]};
	}
		</#list>
	</#list>
}
</#list>
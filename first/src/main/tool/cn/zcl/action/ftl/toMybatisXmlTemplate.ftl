<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#list table?keys as tablename>
<mapper namespace="com.zcl.dao.${tablename?cap_first}Dao">

    <resultMap type="com.zcl.model.${tablename?cap_first}" id="${tablename}Result">
    	<#list table[tablename] as column>
    		<#list column?keys as datatype>
        		<result property="${column[datatype]}" column="${column[datatype]}" />
        	</#list>
        </#list>
    </resultMap>

    <insert id="insert" parameterType="com.zcl.model.${tablename?cap_first}">
		insert into ent_${tablename}(
				<#list table.fields as field>
				<#if field_index gt 0 >
					<#if field_has_next>
						${field.prefix}${field.name},
					<#else>
						${field.prefix}${field.name}
					</#if>
				</#if>
		        </#list>
		) values (
				<#list table.fields as field>
				<#if field_index gt 0 >
				<#if field.reference??>
					<choose><when test="${field.name} == null">NULL</when><otherwise>${r'#{'}${field.name}.id,jdbcType=INTEGER${r'}'}</otherwise></choose><#if field_has_next>,</#if>
				<#else>
					${r'#{'}${field.name},jdbcType=${field.insertType}${r'}'}<#if field_has_next>,</#if>
				</#if>
				</#if>
		        </#list>
		)
	</insert>

    <select id="selectAll" resultMap="${table.name}Result">
        select *
        	from ${table.prefix}${table.name}
    </select>

    <select id="select" parameterType="int" resultMap="${table.name}Result">
        select *
        	from ${table.prefix}${table.name}s where ${table.columnPrefix}id=${r'#{'}id${r'}'}
    </select>
    
    <select id="selectByName" parameterType="String" resultMap="${table.name}Result">
    select *
    	from ${table.prefix}${table.name}s where ${table.columnPrefix}name=${r'#{'}name${r'}'}
</select>

    <update id="update" parameterType="com.zcl.model.${table.name?cap_first}">
        update
        	${table.prefix}${table.name}s set
        	<#list table.fields as field>
        		<#if field.reference??>
        			${field.prefix}${field.name}=<choose><when test="${field.name} == null">NULL</when><otherwise>${r'#{'}${field.name}.id,jdbcType=INTEGER${r'}'}</otherwise></choose><#if field_has_next>,</#if>
        		<#else>
        			${field.prefix}${field.name}=${r'#{'}${field.name}${r'}'}<#if field_has_next>,</#if>
        		</#if>
	        </#list>
        	where ${table.columnPrefix}id=${r'#{'}id${r'}'}
    </update>

    <delete id="delete" parameterType="int">
        delete
        	from ${table.prefix}${table.name}s
        	where ${table.columnPrefix}id=${r'#{'}id${r'}'}
    </delete>

</mapper>
</#list>
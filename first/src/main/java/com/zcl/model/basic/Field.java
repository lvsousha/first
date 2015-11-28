package com.zcl.model.basic;

public class Field {

	private String prefix;         //列前缀

	private String name;           //列名

	private String jdbcType;       //数据库类型

	private String javaType;	   //java类型

	private Integer precision;     //decimal 的精度 ， 默认 64

	private Integer scale;         //decimal 的精度 ， 默认 4

	private Integer length;        //String  的长度 ， 默认64

	private boolean nullable;	   //可以为空

	private String constraint;     //约束  primary ，foreign ， unique

	private String simpleConstraint;  //建表语句里的约束的描述

//	private String foreignDescription;

//	private String primaryDescription;

	private boolean isIndex;          //是否是索引

//	private String indexDescription;

//	private String description;

	private String reference;        //外键表名
	
	private String insertType;       //避免mybatis   insert 错误

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public boolean isIndex() {
		return isIndex;
	}

	public void setIndex(boolean isIndex) {
		this.isIndex = isIndex;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

//	public String getIndexDescription() {
//		return indexDescription;
//	}
//
//	public void setIndexDescription(String indexDescription) {
//		this.indexDescription = indexDescription;
//	}

//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

//	public String getForeignDescription() {
//		return foreignDescription;
//	}
//
//	public void setForeignDescription(String foreignDescription) {
//		this.foreignDescription = foreignDescription;
//	}

//	public String getPrimaryDescription() {
//		return primaryDescription;
//	}
//
//	public void setPrimaryDescription(String primaryDescription) {
//		this.primaryDescription = primaryDescription;
//	}

	public String getSimpleConstraint() {
		return simpleConstraint;
	}

	public void setSimpleConstraint(String simpleConstraint) {
		this.simpleConstraint = simpleConstraint;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getInsertType() {
		return insertType;
	}

	public void setInsertType(String insertType) {
		this.insertType = insertType;
	}



}

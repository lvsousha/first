package com.zcl.model.basic;

import java.util.List;

public class Table {

	private String prefix = "zcl_";//表前缀

	private String name;           //表名，没有s

	private List<Field> fields;   //

	private String columnPrefix; //列前缀

	private List<String> foreigns; //建表语句的外键描述

	private List<String> indexs;   //建表语句的索引描述

	private String description;    
	
	private List<String> javaPackages;      //新建.java文件时需要引入的包

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

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public String getColumnPrefix() {
		return columnPrefix;
	}

	public void setColumnPrefix(String columnPrefix) {
		this.columnPrefix = columnPrefix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getForeigns() {
		return foreigns;
	}

	public void setForeigns(List<String> foreigns) {
		this.foreigns = foreigns;
	}

	public List<String> getIndexs() {
		return indexs;
	}

	public void setIndexs(List<String> indexs) {
		this.indexs = indexs;
	}

	public List<String> getJavaPackages() {
		return javaPackages;
	}

	public void setJavaPackages(List<String> javaPackages) {
		this.javaPackages = javaPackages;
	}


}

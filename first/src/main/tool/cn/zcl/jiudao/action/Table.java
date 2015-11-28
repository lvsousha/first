package cn.zcl.jiudao.action;

import java.util.List;

public class Table {

	private String basepack;
	private String tablename;
	private List<Field> imports;
	private List<Field> fields;
	private String prefix;
	public String getBasepack() {
		return basepack;
	}
	public void setBasepack(String basepack) {
		this.basepack = basepack;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public List<Field> getImports() {
		return imports;
	}
	public void setImports(List<Field> imports) {
		this.imports = imports;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}

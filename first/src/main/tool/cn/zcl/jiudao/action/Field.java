package cn.zcl.jiudao.action;

public class Field {

	private String typename;
	private String name;
	private String jdbctype;
	private Boolean istable = false;
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJdbctype() {
		return jdbctype;
	}
	public void setJdbctype(String jdbctype) {
		this.jdbctype = jdbctype;
	}
	public Boolean getIstable() {
		return istable;
	}
	public void setIstable(Boolean istable) {
		this.istable = istable;
	}

}

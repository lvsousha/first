package com.zcl.model;
import java.io.Serializable;

public class Shareholder implements Serializable {
	private static final long serialVersionUID = 1447039680623L;
	private String orderno;
	private String regno;
	private String shaname;
	private String person_id_bocom;
	private String subconam;
	private String regcapcur;
	private String conform;
	private String fundedratio;
	private String condate;
	
	public void setOrderno(String orderno){
		this.orderno = orderno;
	}

	public String getOrderno(){
		return orderno;
	}
	public void setRegno(String regno){
		this.regno = regno;
	}

	public String getRegno(){
		return regno;
	}
	public void setShaname(String shaname){
		this.shaname = shaname;
	}

	public String getShaname(){
		return shaname;
	}
	public void setPerson_id_bocom(String person_id_bocom){
		this.person_id_bocom = person_id_bocom;
	}

	public String getPerson_id_bocom(){
		return person_id_bocom;
	}
	public void setSubconam(String subconam){
		this.subconam = subconam;
	}

	public String getSubconam(){
		return subconam;
	}
	public void setRegcapcur(String regcapcur){
		this.regcapcur = regcapcur;
	}

	public String getRegcapcur(){
		return regcapcur;
	}
	public void setConform(String conform){
		this.conform = conform;
	}

	public String getConform(){
		return conform;
	}
	public void setFundedratio(String fundedratio){
		this.fundedratio = fundedratio;
	}

	public String getFundedratio(){
		return fundedratio;
	}
	public void setCondate(String condate){
		this.condate = condate;
	}

	public String getCondate(){
		return condate;
	}
}

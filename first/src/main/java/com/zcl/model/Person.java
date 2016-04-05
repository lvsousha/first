package com.zcl.model;
import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = 1447039680623L;
	private String orderno;
	private String regno;
	private String pername;
	private String person_id_bocom;
	private String position;
	private String personamount;
	
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
	public void setPername(String pername){
		this.pername = pername;
	}

	public String getPername(){
		return pername;
	}
	public void setPerson_id_bocom(String person_id_bocom){
		this.person_id_bocom = person_id_bocom;
	}

	public String getPerson_id_bocom(){
		return person_id_bocom;
	}
	public void setPosition(String position){
		this.position = position;
	}

	public String getPosition(){
		return position;
	}
	public void setPersonamount(String personamount){
		this.personamount = personamount;
	}

	public String getPersonamount(){
		return personamount;
	}
}

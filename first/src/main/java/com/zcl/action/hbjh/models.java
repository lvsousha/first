package com.zcl.action.hbjh;

import java.util.List;

import com.zcl.model.Basic;
import com.zcl.model.Person;
import com.zcl.model.Shareholder;

public class models {

	private List<Basic> basics;
	private List<Person> persons;
	private List<Shareholder> shareholders;
	private Integer total;

	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<Basic> getBasics() {
		return basics;
	}
	public void setBasics(List<Basic> basics) {
		this.basics = basics;
	}
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	public List<Shareholder> getShareholders() {
		return shareholders;
	}
	public void setShareholders(List<Shareholder> shareholders) {
		this.shareholders = shareholders;
	}
}

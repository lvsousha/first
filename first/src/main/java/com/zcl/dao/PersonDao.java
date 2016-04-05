package com.zcl.dao;

import java.util.List;
import java.util.Map;

import com.zcl.model.Person;

public interface PersonDao {

	public List<Person> selectPersonByOrderno(Map<String,String> orderno); 
	public Integer countPersonByOrderno(Map<String,String> orderno); 
	

}

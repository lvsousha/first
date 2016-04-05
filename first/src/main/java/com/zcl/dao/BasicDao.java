package com.zcl.dao;

import java.util.List;
import java.util.Map;

import com.zcl.model.Basic;

public interface BasicDao {

	public List<Basic> selectAll(Map<String,Integer> parameters);
	public Basic select();
	public Integer count();
	public Basic select(String entname);

}

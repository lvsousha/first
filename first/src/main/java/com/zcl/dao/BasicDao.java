package com.zcl.dao;

import java.util.List;

import com.zcl.model.Basic;

public interface BasicDao {

	public List<Basic> selectAll();
	public Basic select();
	public Basic select(String entname);

}

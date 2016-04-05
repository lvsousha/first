package com.stone.mapper;

import java.util.List;

import com.zcl.model.Basic;

public interface BasicDao {

	public List<Basic> selectAll();
	public Basic select(String entname);

}

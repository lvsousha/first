package com.zcl.dao;

import java.util.List;
import java.util.Map;

import com.zcl.model.Shareholder;

public interface ShareholderDao {

	public List<Shareholder> selectShareholderByOrderno(Map<String,String> order);

	public Integer countShareholderByOrderno(Map<String, String> parameters);

}

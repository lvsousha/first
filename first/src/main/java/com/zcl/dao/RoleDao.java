package com.zcl.dao;

import com.zcl.model.Role;
import java.util.List;

public interface RoleDao {

	public Role select(int id);
	public Role selectByName(String name);
	public List<Role> selectAll();
	public Integer insert(Role role);
	public Integer delete(int id);

}
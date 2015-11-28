package com.stone.mapper;

import com.stone.model.Role;

public interface RoleDao {

	public Role select(int id);
	public Integer insert(Role role);
	public Integer delete(int id);

}

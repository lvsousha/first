package com.stone.mapper;

import com.stone.model.Permission;

public interface PermissionDao {

	public Permission select(int id);
	public Integer insert(Permission permission);
	public Integer delete(int id);

}

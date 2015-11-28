package com.stone.mapper;

import java.util.List;
import java.util.Set;

import com.stone.model.User;

public interface UserDao {

	public User select(int id);
	public User select(String username);
	public List<User> selectUsers();
	public Integer insert(User user);
	public Integer delete(int id);
	public Integer update(int id);
	public Set<String> selectRoles(int id);
	public Set<String> selectPermissions(int id);

}

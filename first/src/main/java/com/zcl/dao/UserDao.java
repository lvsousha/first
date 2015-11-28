package com.zcl.dao;

import com.zcl.model.User;
import java.util.List;

public interface UserDao {

	public User select(int id);
	public User selectByName(String name);
	public List<User> selectAll();
	public Integer insert(User user);
	public Integer delete(int id);

}
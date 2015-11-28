//package com.stone.service;
//
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.stone.mapper.UserDao;
//import com.stone.model.User;
//
//@Service
//@Transactional
//public class UserService {
//
//	@Autowired
//	UserDao userDao;
//
//	public Set<String> findRoles(String username) {
//		User user = userDao.select(username);
//		Set<String> roles = userDao.selectRoles(user.getId());
//		return roles;
//	}
//
//	public Set<String> findPermissions(String username) {
//		User user = userDao.select(username);
//		Set<String> permissions = userDao.selectPermissions(user.getId());
//		return permissions;
//	}
//
//	public User findByUsername(String username) {
//		User user = userDao.select(username);
//		return user;
//	}
//}

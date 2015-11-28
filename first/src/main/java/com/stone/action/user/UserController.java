//package com.stone.action.user;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.stone.mapper.UserDao;
//import com.stone.model.User;
//import com.stone.service.UserService;
//
//@Controller
//@RequestMapping("/content/user")
//public class UserController {
//
//	@Autowired
//	UserService userservice;
//
//	@Autowired
//	UserDao userDao;
//
//	@ResponseBody
//	@RequestMapping(value="/list")
//	public Object list(User user){
//		Map<String,Object> result = new HashMap<String,Object>();
//		List<User> users = userDao.selectAll();
//		result.put("user", users);
//		return result;
//	}
//
//	@ResponseBody
//	@RequestMapping(value="/create")
//	public Object create(User user){
//		Map<String,Object> result = new HashMap<String,Object>();
//		userDao.insertUser(user);
//		result.put("user", user);
//		return result;
//	}
//}

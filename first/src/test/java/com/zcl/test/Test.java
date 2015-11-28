package com.zcl.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zcl.action.password.Encryption;

import com.zcl.dao.UserDao;
import com.zcl.model.User;

public class Test {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("start:"+(new Date()));
		ApplicationContext ac = new ClassPathXmlApplicationContext(
						"classpath*:spring/spring-cotextConfig.xml");
		UserDao userDao = (UserDao) ac.getBean("userDao");
		User user = new User();
		user.setName("admin");
		user.setPassword("admin");
//		user.setLocked(false);
		user.setPassword(Encryption.md5(user.getPassword(), user.getName()+user.getPassword()));
//		userDao.insert(user);
		userDao.select(1);
//		List<User> users = userDao.selectAll();
//		for(User user : users)
//			System.out.println(user.getUsername());
		System.out.println("end:"+(new Date()));
	}

}

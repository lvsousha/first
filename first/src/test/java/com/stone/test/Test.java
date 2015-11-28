//package com.stone.test;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.stone.mapper.UserDao;
//import com.stone.model.User;
//
//public class Test {
//
//	@SuppressWarnings({ "deprecation", "resource" })
//	public static void main(String[] args) {
//		System.out.println("start:"+(new Date()).getSeconds());
//		ApplicationContext ac = new ClassPathXmlApplicationContext(
//						"classpath*:spring/spring-Test.xml");
//		UserDao userDao = (UserDao) ac.getBean("userDao");
//		List<User> users = userDao.selectAll();
//		for(User user : users)
//			System.out.println(user.getUsername());
//		System.out.println("end:"+(new Date()).getSeconds());
//	}
//
//}

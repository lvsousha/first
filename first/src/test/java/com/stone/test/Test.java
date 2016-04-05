package com.stone.test;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zcl.dao.BasicDao;
import com.zcl.model.Basic;

public class Test {


	public static void main(String[] args) {
		Test test = new Test();
		if(test.isChinese("88989809-8"))
			System.out.println("带有中文符号");;
		
	}
	
	public boolean isChinese(String str){
		char[] charArray = str.toCharArray();
		for(int i=0;i<charArray.length;i++){
			if((charArray[i]>= 0x4e00) && (charArray[i]<= 0x9fbb)){
				System.out.println("TRUE");
				return true;				
			}
		}
		System.out.println("FALSE");
		return false;
	}
	
	@SuppressWarnings("resource")
	public void testMybatis(){
		System.out.println("START");
		ApplicationContext ac = new ClassPathXmlApplicationContext(
						"classpath*:spring/spring-cotextConfig.xml");
//		UserDao userdao = (UserDao) ac.getBean("userDao");
//		List<Basic> users = userdao.selectAll();
		BasicDao basicdao = (BasicDao) ac.getBean("basicDao");
		List<Basic> basics = basicdao.selectAll(new HashMap<String,Integer>());
		for(int i=0;i<=2;i++)
			System.out.println(basics.get(i).getEntname());
		System.out.println("END");
	}

}

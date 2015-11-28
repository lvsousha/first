package com.stone.test;

import org.springframework.core.io.ClassPathResource;

public class Test_resource {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ClassPathResource cpr = new ClassPathResource(
				"classpath:spring-contextConfig.xml");
		System.out.println();
	}

}

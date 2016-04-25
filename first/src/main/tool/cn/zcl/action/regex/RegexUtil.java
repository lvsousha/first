package cn.zcl.action.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

	public void regexTest(){
		Pattern pattern = Pattern.compile("^Java.*");
		Matcher matcher = pattern.matcher("Java 是一门编程语言");
		boolean b = matcher.matches();
		System.out.println("查找已Java开头，任意结尾的字符串："+b);
		//
		pattern = Pattern.compile("\\{\\d*\\}");
		matcher = pattern.matcher("{2323}");
		System.out.println(matcher.replaceFirst("Java"));
		//
		System.out.println("已多个条件分割字符串");
		pattern = Pattern.compile("[, |]+");
		String [] strs = pattern.split("Java Hello World  Java,Hello,,World|Sun");
		for(String str : strs){
			System.out.print(str+"--");
		}
		System.out.println("\n替换第一个符合正则表达式的数据");
		pattern = Pattern.compile("正则表达式");
		matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World");
		System.out.println(matcher.replaceFirst("Java"));
		System.out.println("文字替换（置换字符）");
		matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World");
		StringBuffer sbr = new StringBuffer();
		while(matcher.find()){
			matcher.appendReplacement(sbr, "Java");
		}
		matcher.appendTail(sbr);
		System.out.println(sbr.toString());
	}
	
	
	public static void main(String[] args) {
		RegexUtil ru = new RegexUtil();
		ru.regexTest();
	}

}

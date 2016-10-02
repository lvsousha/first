package cn.zcl.action.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

	public List<String> getFirstMatchContenet(String orign,String regex){
		List<String> ls=new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(orign);
        if(matcher.find()){
        	ls.add(matcher.group());
        }
        return ls;
	}
	
	
	public static void main(String[] args) {
		// 按指定模式在字符串查找
		
		String s = "doeses";
		System.out.println(s.replaceAll("do(es)?", "K"));
		
	      String line = "This order was placed for QT3000! OK?";
	      String pattern = "(.*)(\\d+)(.*)";

	      // 创建 Pattern 对象
	      Pattern r = Pattern.compile(pattern);

	      // 现在创建 matcher 对象
	      Matcher m = r.matcher(line);
	      System.out.println(m.groupCount());
	      if (m.find( )) {
	         System.out.println("Found value: " + m.group(0) );
	         System.out.println("Found value: " + m.group(1) );
	         System.out.println("Found value: " + m.group(2) );
	         System.out.println("Found value: " + m.group(3) );
	      } else {
	         System.out.println("NO MATCH");
	      }
		
	}

}

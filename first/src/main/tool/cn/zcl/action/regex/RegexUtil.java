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
		RegexUtil ru = new RegexUtil();
		String orign = "&amp;amp;lt;p&amp;amp;gt;&amp;amp;#xA;&amp;amp;lt;title&amp;amp;gt;&amp;amp;lt;/title&amp;amp;gt;&amp;amp;#xA;&amp;amp;lt;/p&amp;amp;gt;&amp;amp;#xA;&amp;amp;#xA;";
//		String regex = "（20\\d{2}）(\\W)+(\\d)+号";
//		List<String> matchs = ru.getFirstMatchContenet(orign, regex);
//		for(String match : matchs){
//			System.out.println(match);
//		}
		System.out.println(orign.replaceAll("[[amp;][lt;][gt;][#xA;]]", ""));
		
	}

}

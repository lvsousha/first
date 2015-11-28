package cn.zcl.action.ftl;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FreeMarkerUtil fmu = new FreeMarkerUtil();
		Map<String, Object> maps = new HashMap<String, Object>() ;
		fmu.printToJava("null.ftl", maps, "User");
	}

}

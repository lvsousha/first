package cn.main;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import cn.zcl.action.fileutil.FileUtil;
import cn.zcl.action.ftl.FreeMarkerUtil;
import cn.zcl.action.jdbc.DatabaseToJava;
import cn.zcl.action.jdbc.JdbcUtil;

public class CreateJavaFromDatabase {

	public static void main(String[] args) {
		FreeMarkerUtil fmu = new FreeMarkerUtil();
		JdbcUtil ju = new JdbcUtil();
		DatabaseToJava dbtj = new DatabaseToJava();
		ResourceBundle rb = java.util.ResourceBundle.getBundle("properties.sqlserver.jdbc");
		try {
			Connection connection = ju.connect(rb.getString("jdbc.driverClassName"), rb.getString("jdbc.url"), rb.getString("jdbc.username"), rb.getString("jdbc.password"));
			List<Map<String, List<Map<String,Object>>>> tables = dbtj.getTables_test(connection);
			Map<String, Object> root = new HashMap<String, Object>();
			for(Map<String, List<Map<String,Object>>> table : tables){
				root.put("table", table);
				fmu.printToJava("toJavaTemplate.ftl", root, FileUtil.firstToUpcase(table.keySet().iterator().next()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

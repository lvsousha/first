package cn.zcl.action.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class DatabaseToJava {

	public static void main(String[] args) {
		JdbcUtil ju = new JdbcUtil();
		DatabaseToJava ttj = new DatabaseToJava();
		ResourceBundle rb = java.util.ResourceBundle.getBundle("properties.sqlserver.jdbc");
		try {
			Connection connection = ju.connect(rb.getString("jdbc.driverClassName"), rb.getString("jdbc.url"), rb.getString("jdbc.username"), rb.getString("jdbc.password"));
			List<Map<String, List<Map<String,Object>>>> tables = ttj.getTables_test(connection);
			for(Map<String, List<Map<String,Object>>> table : tables){
				String tablename = table.keySet().iterator().next();
				System.out.println(tablename);
				for(Map<String,Object> columns : table.get(tablename)){
					for(String datatype : columns.keySet()){
						System.out.println(datatype+":"+columns.get(datatype).toString());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public List<Map<String, List<Map<String,Object>>>> getTables_test(Connection conn){
		List<Map<String, List<Map<String,Object>>>> tables_list = new ArrayList<>();
		try {			
			DatabaseMetaData dbMetaData = conn.getMetaData();
			String[] types = { "TABLE" };
            ResultSet tables = dbMetaData.getTables(null, null, "%", types);
            while(tables.next()){
            	Map<String, List<Map<String,Object>>> after_tables = new HashMap<>();
            	String tablename = tables.getString("TABLE_NAME");         	
            	List<Map<String,Object>> after_columns = new ArrayList<>();
            	ResultSet from_columns = dbMetaData.getColumns(null, null, tablename, "%");
            	while(from_columns.next()){
            		Map<String,Object> map = new HashMap<>();
            		String columnName = from_columns.getString("COLUMN_NAME");//列名
                    int dataType = from_columns.getInt("DATA_TYPE"); //对应的java.sql.Types类型
//                    String dataTypeName = from_columns.getString("TYPE_NAME");//java.sql.Types类型   名称
                    if(dataType == 4)
                    	map.put("Integer",columnName.toLowerCase());
                    else if(dataType == 12)
                    	map.put("String",columnName.toLowerCase());
                    else if(dataType == -7)
                    	map.put("Boolean",columnName.toLowerCase());
                    after_columns.add(map);
            	}
            	after_tables.put(tablename.split("_")[1].toLowerCase(), after_columns);
            	tables_list.add(after_tables);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tables_list;
	}

}

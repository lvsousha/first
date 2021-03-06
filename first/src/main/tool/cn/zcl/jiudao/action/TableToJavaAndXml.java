package cn.zcl.jiudao.action;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import cn.zcl.action.fileutil.FileUtil;
import cn.zcl.action.ftl.FreeMarkerUtil;
import cn.zcl.action.jdbc.JdbcUtil;
import cn.zcl.action.xml.XmlUtil;

public class TableToJavaAndXml {

	public String basepack = "com.zcl.model";

	public Map<String, Table> getTables(Connection conn){
		Map<String, Table> toJavaTables = new HashMap<String, Table>();
		try {
			Map<String, String> exitTables = new HashMap<String, String>();
			DatabaseMetaData dbMetaData = conn.getMetaData();
			String[] types = { "TABLE" };
            ResultSet tables = dbMetaData.getTables(null, null, "%", types);
            while(tables.next()){
            	String tableName = tables.getString("TABLE_NAME");  //表名
            	exitTables.put(tableName.subSequence(4, tableName.length()-1).toString(), tableName.subSequence(4, tableName.length()-1).toString());
            }
            tables = dbMetaData.getTables(null, null, "%", types);
            while (tables.next()) {
                Table table = new Table();
                List<Field> fields = new ArrayList<Field>();
                List<Field> imports = new ArrayList<Field>();
                String tableName = tables.getString("TABLE_NAME");  //表名
                System.out.println(tableName.subSequence(4, tableName.length()-1));
                table.setTablename(tableName.subSequence(4, tableName.length()-1).toString());
                ResultSet columns = dbMetaData.getColumns(null, null, tableName, "%");
                while (columns.next()){
                	Field field = new Field();
                    String columnName = columns.getString("COLUMN_NAME");//列名
                    int dataType = columns.getInt("DATA_TYPE"); //对应的java.sql.Types类型
                    String dataTypeName = columns.getString("TYPE_NAME");//java.sql.Types类型   名称
                    table.setPrefix(columnName.split("_")[0]+"_");
                    field.setName(columnName.split("_")[1]);
                    field.setJdbctype(dataTypeName);
                    if(exitTables.get(columnName.split("_")[1]) != null){
                    	field.setTypename(FileUtil.firstToUpcase(columnName.split("_")[1]));
                    	field.setIstable(true);
                    	imports.add(field);
                    }
                    else{
                    	if(dataType == 4)
                    		field.setTypename("Integer");
                        if(dataType == 12)
                        	field.setTypename("String");
                        if(dataType == -7)
                        	field.setTypename("Boolean");
                    }
                    fields.add(field);
                    System.out.println(field.getTypename()+"  "+table.getPrefix()+field.getName()+"   "+field.getJdbctype());
                }
                table.setFields(fields);
                table.setImports(imports);
                table.setBasepack(basepack);
                toJavaTables.put(table.getTablename(), table);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toJavaTables;
	}

	public void createBasicFileAboutMybatis(Connection conn){
		Map<String, Table> tables = getTables(conn);
//		FreeMarkerUtil fmu = new FreeMarkerUtil();
		Iterator<String> keys = tables.keySet().iterator();
		while(keys.hasNext()){
			String key = keys.next();
			Table table = tables.get(key);
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("table", table);
//			fmu.printToJava("toJavaTemplate.ftl", root, FileUtil.firstToUpcase(table.getTablename()));
//			fmu.printToXml("toMybatisXmlTemplate.ftl", root, FileUtil.firstToUpcase(table.getTablename()));
//			fmu.printToInterface("toInterfaceTemplate.ftl", root, FileUtil.firstToUpcase(table.getTablename()));
//			fmu.printToSql("toSqlTemplate.ftl", root, null);
		}
	}

	public void toSql(Connection conn) throws Exception{
		XmlUtil xmlUtil = new XmlUtil();
		List<com.zcl.model.basic.Table> tables = xmlUtil.readToTables("src/main/tool/cn/zcl/action/xml/example.xml");
		System.out.println(tables.size());
		FreeMarkerUtil fmu = new FreeMarkerUtil();
		for(com.zcl.model.basic.Table table : tables){
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("table", table);
//			fmu.printToJava("toJavaTemplate.ftl", root, FileUtil.firstToUpcase(table.getTablename()));
//			fmu.printToXml("toMybatisXmlTemplate.ftl", root, FileUtil.firstToUpcase(table.getTablename()));
//			fmu.printToInterface("toInterfaceTemplate.ftl", root, FileUtil.firstToUpcase(table.getTablename()));
			fmu.printToSql("toSqlTemplate.ftl", root, null);
		}
	}

	public static void main(String[] args) {

		JdbcUtil ju = new JdbcUtil();
		TableToJavaAndXml ttj = new TableToJavaAndXml();
		ResourceBundle rb = java.util.ResourceBundle.getBundle("properties.sqlserver.jdbc");
		try {
			Connection connection = ju.connect(rb.getString("jdbc.driverClassName"), rb.getString("jdbc.url"), rb.getString("jdbc.username"), rb.getString("jdbc.password"));
//			ttj.createBasicFileAboutMybatis(connection);
			ttj.getTables(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}

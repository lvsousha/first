package cn.zcl.action.xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zcl.model.basic.Table;

import cn.zcl.action.fileutil.FileUtil;
import cn.zcl.action.ftl.FreeMarkerUtil;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XmlUtil xmlUtil = new XmlUtil();
		try {
			List<Table> tables = xmlUtil.readToTables("src/main/resources/schema/schema.xml");
			FreeMarkerUtil fmu = new FreeMarkerUtil();
			Map<String, Object> roots = new HashMap<String, Object>();
			roots.put("tables", tables);
			fmu.printToSql("toSqlTemplate.ftl", roots, null);
			for(Table table : tables){
				Map<String, Object> root = new HashMap<String, Object>();
				root.put("table", table);
				fmu.printToJava("toJavaTemplate.ftl", root, FileUtil.firstToUpcase(table.getName()));
				fmu.printToXml("toMybatisXmlTemplate.ftl", root, FileUtil.firstToUpcase(table.getName()));
				fmu.printToInterface("toInterfaceTemplate.ftl", root, FileUtil.firstToUpcase(table.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

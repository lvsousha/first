package cn.zcl.action.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zcl.model.basic.Field;
import com.zcl.model.basic.Table;

import cn.zcl.action.fileutil.FileUtil;

public class XmlUtil {

	public Document getDocument(){
		Document document = DocumentHelper.createDocument();
		return document;
	}

	public Document getDocument(String path) throws Exception{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(path));
		return document;
	}

	@SuppressWarnings("unchecked")
	public List<Table> readToTables(String path) throws Exception{
		List<Table> tables = new ArrayList<Table>();
		Map<String, Table> tableMaps = new HashMap<String, Table>();
		Document document = getDocument(path);
		Element root = document.getRootElement();
		List<Element> table_nodes = root.elements();
		for(Element table_node : table_nodes){
			Table table = new Table();
			List<String> foreigns = new ArrayList<String>();
			List<String> indexs = new ArrayList<String>();
			List<Field> fields = new ArrayList<Field>();
			List<String> javaPackages = new ArrayList<String>();
			Boolean hasDate = false;
			Boolean hasDecimal = false;
			List<Attribute> table_node_attributes = table_node.attributes();
			for(Attribute table_node_attribute : table_node_attributes){
				String name = table_node_attribute.getName();
				String value = table_node_attribute.getValue();
				if(name.equals("name"))
					table.setName(value);
				if(name.equals("columnPrefix"))
					table.setColumnPrefix(value);
				if(name.equals("descr"))
					table.setDescription(value);
			}
//			System.out.println();
			List<Element> field_nodes = table_node.elements();
			for(Element field_node : field_nodes){
				Field field = new Field();
				field.setPrefix(table.getColumnPrefix());
				List<Attribute> field_node_attributes = field_node.attributes();
				for(Attribute field_node_attribute : field_node_attributes){
					String name = field_node_attribute.getName();
					String value = field_node_attribute.getValue();
					if(name.equals("name"))
						field.setName(value);
					if(name.equals("precision"))
						field.setPrecision(Integer.parseInt(value));
					if(name.endsWith("scale"))
						field.setScale(Integer.parseInt(value));
					if(name.equals("null"))
						field.setNullable(value.equals("true")?true:false);
					if(name.equals("key"))
						field.setConstraint(value);
					if(name.equals("table"))
						field.setReference(value);
					if(name.equals("index"))
						field.setIndex(value=="true"?true:false);
					if(name.equals("length"))
						field.setLength(Integer.parseInt(value));
				}
				if(field_node.getName().equals("int")){
					field.setJdbcType("int");
					field.setJavaType("Integer");
					field.setInsertType("INTEGER");
				}
				if(field_node.getName().equals("varchar")){
					if(field.getLength() == null)
						field.setLength(64);
					field.setJdbcType("varchar("+field.getLength()+")");
					field.setJavaType("String");
					field.setInsertType("VARCHAR");
				}
				if(field_node.getName().equals("date")){
					field.setJdbcType("datetime");
					field.setJavaType("Date");
					field.setInsertType("DATE");
					hasDate = true;
				}
				if(field_node.getName().equals("decimal")){
					if(field.getPrecision() == null)
						field.setPrecision(64);
					if(field.getScale() == null)
						field.setScale(4);
					field.setJdbcType("decimal("+field.getPrecision()+","+field.getScale()+")");
					field.setJavaType("BigDecimal");
					field.setInsertType("DECIMAL");
					hasDecimal = true;
				}
				if(field_node.getName().equals("bool")){
					field.setJdbcType("bit");
					field.setJavaType("Boolean");
					field.setInsertType("BIT");
				}
				if(field_node.getName().equals("ref")){
					field.setJdbcType("int");
					field.setJavaType(FileUtil.firstToUpcase(field.getReference()));
					field.setConstraint("foreign");
				}
				field.setSimpleConstraint(field.isNullable()?"NULL":"NOT NULL");
				if(field.getConstraint()!=null && field.getConstraint().equals("primary"))
					field.setSimpleConstraint(field.isNullable()?"NULL":"NOT NULL"+" IDENTITY PRIMARY KEY");
				if(field.getConstraint()!=null && field.getConstraint().equals("unique"))
					field.setSimpleConstraint((field.isNullable()?"NULL":"NOT NULL")+" UNIQUE");
				if(field.getConstraint()!=null && field.getConstraint().equals("foreign")){
					Table t = tableMaps.get(field.getReference());
					foreigns.add(
							"foreign key ("+field.getPrefix()+field.getName()+") references "+t.getPrefix()+t.getName()+"s("+t.getColumnPrefix()+"id)");
				}
				if(field.isIndex())
					indexs.add("create index idx_"+field.getName()+" on "+table.getPrefix()+table.getName()+"("+field.getPrefix()+field.getName()+");");
				fields.add(field);
			}
			if(hasDate)
				javaPackages.add("java.util.Date");
			if(hasDecimal)
				javaPackages.add("java.math.BigDecimal");
			table.setJavaPackages(javaPackages);
			table.setFields(fields);
			table.setForeigns(foreigns);
			table.setIndexs(indexs);
			tables.add(table);
			tableMaps.put(table.getName(), table);
		}
		return tables;
	}

	public void toXmlString(Map<String, List<Map<String, String>>> models) {
		Document document = DocumentHelper.createDocument();
		Iterator<String> beannames = models.keySet().iterator();
		Element beans = document.addElement("beans");
		for(;beannames.hasNext();){
			String beanname = beannames.next();
			Element bean = beans.addElement("bean");
			List<Map<String, String>> properties_values = models.get(beanname);
			for(int i = 0 ; i < properties_values.size(); i++){
				Map<String, String> properties = properties_values.get(i);
				Iterator<String> propertynames = properties.keySet().iterator();
				if(i == 0){
					for(;propertynames.hasNext();){
						String p = propertynames.next();
						bean.addAttribute(p, properties.get(p));
					}
				}else{
					Element property = bean.addElement("property");
					for(;propertynames.hasNext();){
						String p = propertynames.next();
						property.addAttribute(p, properties.get(p));
					}
				}
			}
		}
		System.out.println(beans.asXML());
	}

	public static String firstToUpperCase(String s){
		char[] cs=s.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
	}
}

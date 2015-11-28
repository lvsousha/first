package com.stone.test.database;

import java.io.*;
import java.util.*;

import org.dom4j.*;
import org.dom4j.io.*;

public class Dom4j {

	public static void main(String[] args) {
		
        SAXReader reader = new SAXReader();
        Document document;
		try {
			document = reader.read(new File("src/main/resources/schema/schema.xml"));
	        Element node = document.getRootElement();
	        listNodes(node);
//	        writer(document); 
		} catch (Exception e) {
			e.printStackTrace();
		}          
	}
	
	@SuppressWarnings("unchecked")
	public static void listNodes(Element node) {  
        System.out.println("当前节点的名称：：" + node.getName());  
        // 获取当前节点的所有属性节点  
		List<Attribute> list = node.attributes();  
        // 遍历属性节点  
        for (Attribute attr : list) {  
            System.out.println(attr.getText() + "-----" + attr.getName()  
                    + "---" + attr.getValue());  
        }  
  
        if (!(node.getTextTrim().equals(""))) {  
            System.out.println("文本内容：：：：" + node.getText());  
        }  
  
        // 当前节点下面子节点迭代器  
        Iterator<Element> it = node.elementIterator();  
        // 遍历  
        while (it.hasNext()) {  
            // 获取某个子节点对象  
            Element e = it.next();  
            // 对子节点进行遍历  
            listNodes(e);  
        }  
    } 
	
	public static void writer(Document document) throws Exception {  
        // 紧凑的格式  
        // OutputFormat format = OutputFormat.createCompactFormat();  
        // 排版缩进的格式  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        // 设置编码  
        format.setEncoding("UTF-8");  
        // 创建XMLWriter对象,指定了写出文件及编码格式  
        // XMLWriter writer = new XMLWriter(new FileWriter(new  
        // File("src//a.xml")),format);  
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
                new FileOutputStream(new File("src//a.xml")), "UTF-8"), format);  
        // 写入  
        writer.write(document);  
        // 立即写入  
        writer.flush();  
        // 关闭操作  
        writer.close();  
    }

}

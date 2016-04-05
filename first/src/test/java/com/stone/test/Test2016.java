package com.stone.test;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Test2016 {

	public static void main(String[] args) {
		String result = "<DATA><ORDERLIST><ITEM><UID>C24D78C0EC14127E</UID><READSTATUS>0</READSTATUS><PAGESIZE>50</PAGESIZE><PAGENO>1</PAGENO><PAGECOUNT>0</PAGECOUNT><ROWCOUNT>0</ROWCOUNT><STARTQUERYDATE>2015-10-01</STARTQUERYDATE><ENDQUERYDATE>2015-12-20</ENDQUERYDATE><FINISHTIME>2016-03-06 11:24:31</FINISHTIME></ITEM></ORDERLIST><RESULT></RESULT></DATA>";
		List<String> orderchanges = new ArrayList<String>();
//		if (result.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")) {
			Document list_doc = Jsoup.parse(result);
			Element list_node_result = list_doc.select("DATA").get(0).select("result").get(0);
			System.out.println(list_node_result);
			System.out.println(list_node_result.children()==null);
			for(Element list_item : list_node_result.children()){
				String ss = list_item.select("orderno").get(0).text()+"~"+list_item.select("changeno").get(0).text();
				orderchanges.add(ss);
			}
//		}
			System.out.println(orderchanges.size());
		for(String orderchange : orderchanges){
			System.out.println(orderchange);
		}
		System.out.println("END");

	}

}

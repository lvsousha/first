package cn.zcl.action.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/**
 * 解析html文件
 * @author zhengchanglin
 *
 */
public class JsoupUtil {

	public Document explainString(String str){
		Document doc = Jsoup.parse(str);
		System.out.println(doc.html());
		return doc;
	}
	
	public Document explainUrl(String url) throws Exception{
		Document doc = Jsoup.connect(url).get();
		return doc;
	}
	
	
}

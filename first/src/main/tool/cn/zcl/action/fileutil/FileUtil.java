package cn.zcl.action.fileutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUtil {

	@SuppressWarnings("resource")
	public StringBuffer readTxtFile(File file){
		StringBuffer sb = new StringBuffer();
		try{
			BufferedReader bd = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String s = bd.readLine();
			while(s != null){
				sb.append(s);
				s = bd.readLine();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return sb;
	}

	/**
	 * 首字母大写
	 * @param str 需要该行为的字符串
	 * @return
	 */
	public static String firstToUpcase(String str){
		byte[] bytes = str.getBytes();
		bytes[0] = (byte) (bytes[0] - 32);
		return new String(bytes);
	}

	/**
	 * 获取属性文件里某个属性的值
	 * @param path 路径
	 * @param property 属性名（键）
	 * @return
	 */
	public static String getProperty(String path, String property){
		ResourceBundle rb = java.util.ResourceBundle.getBundle(path); //path=properties.sqlserver.jdbc
		return rb.getString(property);
	}

	/**
	 * 获取上传文件的输入流
	 * @param request  web请求对象
	 * @return
	 * @throws IOException
	 */
	public static MultipartFile getValueFromFilefield(HttpServletRequest request) throws IOException{
	      MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	      //获得上传文件的名称
	      Iterator<String> iter = multiRequest.getFileNames();
	      //如果有的话就依次取出来
	      while (iter.hasNext()){
	        //包装过的文件流
	        MultipartFile file = multiRequest.getFile((String)iter.next());
	        //这里要进行判断，即使是空值，没有上传内容，file都是有值（空值）的，而文件流大小要大于0才是有上传的东西
	        if (file.getSize() > 0)   
				return file;            
	        }		
	      return null;
	}
}

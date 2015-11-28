package cn.zcl.action.fileutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

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

	public static String firstToUpcase(String str){
		byte[] bytes = str.getBytes();
		bytes[0] = (byte) (bytes[0] - 32);
		return new String(bytes);
	}
}

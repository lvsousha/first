package cn.zcl.action.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Test {
	public static String ip = "210.22.132.126";
	public static String user = "seven-eleven";
	public static String password = "3\\FqkFn8";
//	public static String basepath = "D:\\FTP";
	FTPClient ftp;

    public static void main(String[] args){
    	Test test = new Test();
    	try {
			test.receiveFiles();
			System.out.println("1111");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }


	public void receiveFiles() throws Exception{
		connect(ip,user,password);
		download();
		closeFtp();
	}
	public void connect(String ip, String user, String password) throws IOException{
		ftp  =  new FTPClient();
		ftp.connect(ip);
		ftp.login(user, password);
		ftp.changeWorkingDirectory("report/");
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		ftp.setControlEncoding("UTF-8");
	}

	public void closeFtp(){
		try {
			ftp.logout();
			ftp.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void download() throws Exception{
//		try{
			FTPFile[] fs = ftp.listFiles();
			for(FTPFile ff : fs){
				if(ff.getName().equals("SDXPASS20151112.txt")){
					File f = new File("D://FTP");
					if(!f.exists())
						f.mkdir();
					File localFile = new File("D://FTP"+"/"+ff.getName());
					OutputStream os = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), os);
					os.close();
				}
			}
//		}catch (IOException e) {
//			e.printStackTrace();
//		}

	}
}

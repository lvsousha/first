package cn.zcl.action.ftp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Ftp {

	FTPClient ftp = new FTPClient();

	public void connect(FtpModel model){
		try{
			if(model.getPort() != null)
				ftp.connect(model.getIp(),model.getPort());
			else
				ftp.connect(model.getIp());
			ftp.login(model.getUsername(), model.getPassword());
			ftp.changeWorkingDirectory(model.getRemotePath());
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.setControlEncoding("UTF-8");
		}catch(SocketException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void closeFtp(){
		try {
			ftp.logout();
			ftp.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件名必须是英文名
	 * @param file 本地文件
	 */
	public void upload(File file){
		try{
			if(file.isDirectory()){
				ftp.makeDirectory(file.getName());
				ftp.changeWorkingDirectory(file.getName());
				File[] files = file.listFiles();
				for(int num=0; num<files.length; num++){
					upload(files[num]);
				}
			}else{
				FileInputStream in = new FileInputStream(file);
				ftp.storeFile(file.getName(), in);
				in.close();
				System.out.println("成功上传文件："+file.getName());
			}
		}catch(SocketException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param bytes 本地数据
	 * @param filename 远程文件名
	 */
	public void upload(byte[] bytes, String filename){
		try{
			InputStream in = new ByteArrayInputStream(bytes);
			ftp.storeFile(filename, in);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void download(FtpModel model){
		try{
			ftp.changeWorkingDirectory(model.getRemoteFilePath());
			FTPFile[] fs = ftp.listFiles();
			for(FTPFile ff : fs){
				if(ff.getName().equals(model.getRemoteFileName())){
					File f = new File(model.getLocalFilePath());
					if(!f.exists())
						f.mkdir();
					File localFile = new File(model.getLocalFilePath()+"/"+ff.getName());
					OutputStream os = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), os);
					os.close();
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}

	}
}

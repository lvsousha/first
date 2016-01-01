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

	public FTPClient ftpClient = new FTPClient();

	public void connect(FtpModel model){
		try{
			if(model.getPort() != null)
				ftpClient.connect(model.getIp(),model.getPort());
			else
				ftpClient.connect(model.getIp());
			ftpClient.login(model.getUsername(), model.getPassword());
			ftpClient.changeWorkingDirectory(model.getRemotePath());
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.setControlEncoding("UTF-8");
		}catch(SocketException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void closeFtp(){
		try {
			ftpClient.logout();
			ftpClient.disconnect();
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
				ftpClient.makeDirectory(file.getName());
				ftpClient.changeWorkingDirectory(file.getName());
				File[] files = file.listFiles();
				for(int num=0; num<files.length; num++){
					upload(files[num]);
				}
			}else{
				FileInputStream in = new FileInputStream(file);
				ftpClient.storeFile(file.getName(), in);
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
	 * @param in 输入流
	 * @param fileName 文件名
	 * @throws IOException
	 */
	public void upload(InputStream in, String fileName) throws IOException{
		ftpClient.storeFile(fileName, in);
	}

	/**
	 *
	 * @param bytes 本地数据
	 * @param filename 远程文件名
	 */
	public void upload(byte[] bytes, String filename){
		try{
			InputStream in = new ByteArrayInputStream(bytes);
			ftpClient.storeFile(filename, in);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 上传到指定的文件夹
	 * @param model
	 */
	public void upload(FtpModel model){
		
	}
	
	/**
	 * 下载指定文件
	 * @param model
	 */
	public void download(FtpModel model){
		try{
			ftpClient.changeWorkingDirectory(model.getRemoteFilePath());
			FTPFile[] fs = ftpClient.listFiles();
			for(FTPFile ff : fs){
				System.out.println(ff.getName());
				if(ff.getName().equals(model.getDownloadFileName())){
					File f = new File(model.getLocalFilePath());
					if(!f.exists())
						f.mkdir();
					File localFile = new File(model.getLocalFilePath()+"/"+ff.getName());
					if(ff.isDirectory())
						localFile.mkdir();
					else{
						OutputStream os = new FileOutputStream(localFile);
						ftpClient.retrieveFile(ff.getName(), os);
						os.close();
					}					
					System.out.println("成功下载文件："+ff.getName());
					break;
				}else{
					if(ff.isDirectory()){
						File file = new File(model.getLocalFilePath()+"/"+ff.getName());
						if(!file.exists())
							file.mkdir();
						model.setLocalFilePath(model.getLocalFilePath()+"/"+ff.getName());
						model.setRemoteFilePath(ff.getName());
						download(model);
						ftpClient.changeToParentDirectory();
					}
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}

	}
}

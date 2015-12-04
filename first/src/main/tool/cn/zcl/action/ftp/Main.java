package cn.zcl.action.ftp;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FtpModel ftpmodel = new FtpModel();
		ftpmodel.setIp("192.168.1.100");
		ftpmodel.setUsername("lvdousha");
		ftpmodel.setPassword("123456");
		ftpmodel.setRemotePath("FTP/");
		ftpmodel.setLocalFilePath("E:\\FTP");
		ftpmodel.setDownloadFileName("child2.txt");
//		File file = new File("D://test");
		Ftp ftp = new Ftp();
		ftp.connect(ftpmodel);
		ftp.download(ftpmodel);
		ftp.closeFtp();
	}

}

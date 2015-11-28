package cn.zcl.action.ftp;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FtpModel ftpmodel = new FtpModel();
		ftpmodel.setIp("192.168.10.51");
		ftpmodel.setUsername("zcl");
		ftpmodel.setPassword("123456");
		ftpmodel.setRemoteFilePath("test/");
		ftpmodel.setRemoteFileName("Deliveries20151014.xls");
//		File file = new File("D://test");
		Ftp ftp = new Ftp();
		ftp.connect(ftpmodel);
		ftp.download(ftpmodel);
		ftp.closeFtp();
	}

}

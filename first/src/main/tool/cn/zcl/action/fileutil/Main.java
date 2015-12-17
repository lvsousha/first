package cn.zcl.action.fileutil;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		String remotePath = FileUtil.getProperty("properties.stone", "path.remote");
		System.out.println(remotePath);
		File remoteFile = new File(remotePath);
		if(!remoteFile.exists()){
			System.out.println("NOT EXIST");
			remoteFile.mkdirs();
		}
			
	}

}

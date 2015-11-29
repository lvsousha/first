package com.zcl.action.ftp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stone.common.Tree;
import com.zcl.dao.UserDao;

import cn.zcl.action.ftp.Ftp;
import cn.zcl.action.ftp.FtpModel;

@Controller
@RequestMapping("/content/ftp")
public class FtpController {


	@Autowired
	UserDao userDao;

	@ResponseBody
	@RequestMapping(value="/remoteFiles")
	public Object create(FtpModel ftpmodel, HttpServletRequest request){
		ftpmodel.setIp("192.168.1.101");
		ftpmodel.setUsername("lvdousha");
		ftpmodel.setPassword("lvdousha");
		ftpmodel.setRemotePath("FTP/");
		Ftp f = new Ftp();
		f.connect(ftpmodel);
		Tree node = null;
		try {
			node = getTree(null, f.ftp);
		} catch (Exception e) {
			e.printStackTrace();
		}				
		return node;
	}
	
	public Tree getTrees(FtpModel model, FTPClient ftp){
		Tree folder = Tree.folder(null, null);
		List<Tree> children = new ArrayList<Tree>();
		try{
			ftp.changeWorkingDirectory(model.getRemoteFilePath());
			FTPFile[] fs = ftp.listFiles();
			for(FTPFile ff : fs){
				if(ff.isDirectory()){
					children.add(getTree(ff,ftp));
				}else{
					children.add(Tree.leaf(ff.getName(), ff));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		folder.setChildren(children);
		return folder;
	}
	
	public Tree getTree(FTPFile file, FTPClient ftp) throws Exception{
		Tree folder = null;
		if(file == null){
			folder = Tree.folder(null, null);
		}else{
			folder = Tree.folder(file.getName(), file);
			ftp.changeWorkingDirectory(file.getName());
		}
		
		List<Tree> children = new ArrayList<Tree>();
		FTPFile[] fs = ftp.listFiles();
		for(FTPFile ff : fs){
			if(ff.isDirectory()){
				children.add(getTree(ff,ftp));
			}else{
				children.add(Tree.leaf(ff.getName(), ff));
			}
		}
		folder.setChildren(children);
		return folder;
	}
	
}

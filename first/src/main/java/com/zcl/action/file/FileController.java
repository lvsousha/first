package com.zcl.action.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.stone.common.Tree;

import cn.zcl.action.fileutil.FileUtil;

/**
 * Created by xin on 14-5-20.
 */
@Controller
@RequestMapping("/content/file")
public class FileController {

	// 返回的map，会自动封装成json
	Map<String, Object> outmap = new HashMap<String, Object>();

	@RequestMapping("/upload")
	@ResponseBody
	public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response) {

		/**
		 * 对上传文件进行处理
		 */
		ServletContext servletContext = request.getSession().getServletContext(); // 获取servlet上下文
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(servletContext);// spring的文件处理解析类，包装了servlet的上下文。
		boolean hasUploadAvatar = false; // 是否有上传文件
		if (multipartResolver.isMultipart(request)) {// 如果是multipart的提交，这个判断有点多余，不过还是加上吧！
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;// 把request请求进行升级，request有的，它都有
			Iterator<String> iter = multiRequest.getFileNames();// 获得上传文件的名称
			while (iter.hasNext()) { // 如果有的话就依次取出来
				MultipartFile file = multiRequest.getFile((String) iter.next());// 包装过的文件流
				if (file.getSize() > 0) {// 这里要进行判断，即使是空值，没有上传内容，file都是有值（空值）的，而文件流大小要大于0才是有上传的东西
					String imagename = file.getOriginalFilename();// 获得上传文件原始名
					try {
						File localFile = new File(FileUtil.getProperty("properties.stone", "path.remote"), imagename);
						System.out.println(localFile.getAbsolutePath());
						file.transferTo(localFile);// 直接写入到后台服务器，简单且快
						outmap.put("success", true);
						outmap.put("msg", "添加成功！");
						return outmap;
					} catch (Exception e) {
						e.printStackTrace();
						outmap.put("success", false);
						outmap.put("msg", "系统出错");
						return outmap;
					}
				}
			}
		}
		if (!hasUploadAvatar) {
			outmap.put("success", true);
			outmap.put("msg", "添加成功！");
			return outmap;
		}
		return null;
	}

	@SuppressWarnings("resource")
	@ResponseBody
	@RequestMapping(value = "/download")
	public void fileDownload(String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
		File file = new File(path);
		byte[] date = new byte[1024];
		int len = 0;
		FileInputStream in = new FileInputStream(file);
		String fileName = file.getName();
		// 以流的形式下载文件
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + "\"");
		BufferedOutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		while ((len = in.read(date)) != -1) {
			toClient.write(date, 0, len);
        }
		toClient.flush();
		toClient.close();
	}

	@ResponseBody
	@RequestMapping(value = "/remoteFiles")
	public Object create(HttpServletRequest request) {
		Tree node = null;
		String path = FileUtil.getProperty("properties.stone", "path.remote");
		try {
			node = getTree(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return node;
	}
  
	public Tree getTree(String path) throws Exception {
		File file = new File(path);
		Tree fold = new Tree();
		List<Tree> children = new ArrayList<Tree>();
        if (file.exists()) {
            fold = Tree.folder(file.getName(), file);
        	File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println(file.getAbsolutePath()+"文件夹是空的!");
                return fold;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        Tree leaf = getTree(file2.getAbsolutePath());
                        children.add(leaf);
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                        Tree leaf = Tree.leaf(file2.getName(), file2);
                        children.add(leaf);
                    }
                }
                fold.setChildren(children);
                return fold;
            }
        }else{
            System.out.println(path+"文件不存在!");
            return null;
        }
	}
}

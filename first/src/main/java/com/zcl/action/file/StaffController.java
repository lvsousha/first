package com.zcl.action.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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

/**
 * Created by xin on 14-5-20.
 */
@Controller
@RequestMapping("/content/staff")
public class StaffController {

  //返回的map，会自动封装成json
  Map<String,Object> outmap = new HashMap<String, Object>();

  @RequestMapping("/add")
  @ResponseBody
  public Map<String,Object> add(StaffVo staffVo, HttpServletRequest request, HttpServletResponse response){
    
    /**
     * 对上传文件进行处理
     */
    //获取servlet上下文
    ServletContext servletContext = request.getSession().getServletContext();
    //spring的文件处理解析类，包装了servlet的上下文。
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(servletContext);
    //是否有上传文件
    boolean hasUploadAvatar = false ;
    //如果是multipart的提交，这个判断有点多余，不过还是加上吧！
    if (multipartResolver.isMultipart(request)) {
      //把request请求进行升级，request有的，它都有，
      MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
      //获得上传文件的名称
      Iterator<String> iter = multiRequest.getFileNames();
      //如果有的话就依次取出来
      while (iter.hasNext()){
        //包装过的文件流
        MultipartFile file = multiRequest.getFile((String)iter.next());
        //这里要进行判断，即使是空值，没有上传内容，file都是有值（空值）的，而文件流大小要大于0才是有上传的东西
        if (file.getSize() > 0){
          //获得上传文件原始名
          String imagename = file.getOriginalFilename();
          //算出后缀名
          String ext = imagename.substring(imagename.lastIndexOf(".")) ;
          //对文件类型进行判断，这个操作也可以在前台进行处理，在前台进行处理比较好，前后台都进行处理最稳妥
          List<String> fileTypes = new ArrayList<String>();
          fileTypes.add(".jpg");
          fileTypes.add(".jpeg");
          fileTypes.add(".bmp");
          fileTypes.add(".gif");
          fileTypes.add(".png");
          try { //是图片再进行处理
          if (fileTypes.contains(ext.toLowerCase())){
            //文件名为：唯一的工号 + avatar + 系统时间 + 后缀
            String fileName ="avatar" + System.currentTimeMillis() + ext ;
            //文件夹;String imagepath = request.getRealPath("/upload");过时的方法，用request.getSession().getServletContext()代替
            String avatarFolder = servletContext.getRealPath("/");
//            String avatarFolder1 = this.getClass().getClassLoader().getResource("/").getPath();
//            String avatarFolder2 = request.getContextPath();
//            String avatarFolder3 = this.getClass().getClassLoader().getResource("/").getPath();
            System.out.println(avatarFolder);
//            System.out.println(avatarFolder1);
//            System.out.println(avatarFolder2);
//            System.out.println(avatarFolder3);
            File localFile = new File(avatarFolder,fileName);           
              //直接写入到后台服务器，简单且快
              file.transferTo(localFile);
              //保存资料到数据库中
              outmap.put("success",true);
              outmap.put("msg","添加成功！");
              return outmap ;
          } else {
            outmap.put("success",false);
            outmap.put("msg","图片格式出错！");
            return outmap ;
          }
            } catch (IOException e) {
              e.printStackTrace();
              outmap.put("success",false);
              outmap.put("msg","系统出错");
              return outmap ;
            }
        }
      }
    }
    if (!hasUploadAvatar){
      outmap.put("success",true);
      outmap.put("msg","添加成功！");
      return outmap ;
    }
    return null ;
  }
  
  @ResponseBody
  @RequestMapping(value = "/download")
  public void fileDownload(HttpServletRequest request,
          HttpServletResponse response) throws IOException {
      File file = new File(request.getSession().getServletContext().getRealPath("/")+"/"+"txt.txt");
      byte[] fileStream = file.toString().getBytes();
      String fileName = file.getName();
       
      // 以流的形式下载文件
      response.setContentType("application/octet-stream");
      response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gb2312"), "ISO8859-1" ) + "\"");
      OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
      toClient.write(fileStream);
      toClient.flush();
      toClient.close();
  }
}

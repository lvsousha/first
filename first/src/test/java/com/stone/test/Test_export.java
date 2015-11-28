//package com.stone.test;
//
//import java.io.*;
//import java.util.*;
//
//import net.sf.jxls.transformer.XLSTransformer;
//
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.stone.mapper.UserDao;
//import com.stone.model.User;
//
//public class Test_export {
//
//	@SuppressWarnings("resource")
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		ApplicationContext cpr = new ClassPathXmlApplicationContext(
//				"classpath*:spring-cotextConfig.xml");
////		UserService userService = (UserService) cpr.getBean("userService");
//		UserDao userDao = (UserDao) cpr.getBean("userDao");
////		String templateFileName= request.getServletContext().getRealPath("/") + "/resources/templateFileName.xls";
//		String templateFileName = "d:/template/xls/template/test.xlsx";
//		String destFileName= "d:/template/xls/output/destFileName.xlsx";
//        //模拟数据
//        List<User> staff = new ArrayList<User>();
//        Map<String,Object> beans = new HashMap<String,Object>();
//        staff = userDao.selectAll();
//        beans.put("users", staff);
//        XLSTransformer transformer = new XLSTransformer();
//        InputStream in=null;
//        OutputStream out= null;
//        //设置响应
////        response.setHeader("Content-Disposition", "attachment;filename=" + destFileName);
////        response.setContentType("application/vnd.ms-excel");
//        try {
//            in=new BufferedInputStream(new FileInputStream(templateFileName));
//            Workbook workbook=transformer.transformXLS(in, beans);
////            out=response.getOutputStream();
//            out = new BufferedOutputStream(new FileOutputStream(destFileName));
//            //将内容写入输出流并把缓存的内容全部发出去
//            workbook.write(out);
//            out.flush();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (in!=null){try {in.close();} catch (IOException e) {}}
//            if (out!=null){try {out.close();} catch (IOException e) {}}
//        }
//	}
//
//}

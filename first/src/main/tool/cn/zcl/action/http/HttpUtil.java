package cn.zcl.action.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	 /**
     * post方式提交表单（模拟用户登录请求）
     */
    public void postForm() {
        
        CloseableHttpClient httpclient = HttpClients.createDefault();// 创建默认的httpClient实例.        
        HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceJ.action");// 创建httppost        
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();// 创建参数队列
        formparams.add(new BasicNameValuePair("username", "admin"));
        formparams.add(new BasicNameValuePair("password", "123456"));
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println("--------------------------------------");
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                    System.out.println("--------------------------------------");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {// 关闭连接,释放资源            
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
    public void post() {       
        CloseableHttpClient httpclient = HttpClients.createDefault();// 创建默认的httpClient实例.        
        HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceJ.action");// 创建httppost        
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();// 创建参数队列
        formparams.add(new BasicNameValuePair("type", "house"));
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println("--------------------------------------");
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                    System.out.println("--------------------------------------");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {// 关闭连接,释放资源            
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送 get请求
     */
    public void get() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://www.baidu.com/");// 创建httpget
            System.out.println("executing request " + httpget.getURI());            
            CloseableHttpResponse response = httpclient.execute(httpget);// 执行get请求.
            try {                
                HttpEntity entity = response.getEntity();// 获取响应实体
                System.out.println("--------------------------------------");                
                System.out.println("Status:"+response.getStatusLine());// 打印响应状态
                if (entity != null) {                   
                    System.out.println("ContentLength: " + entity.getContentLength());// 打印响应内容长度                    
                    System.out.println("Content: " + EntityUtils.toString(entity));// 打印响应内容
                }
                System.out.println("------------------------------------");
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {// 关闭连接,释放资源            
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上传文件
     */
//    public void upload() {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        try {
//            HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceFile.action");
//
//            FileEntity bin = new FileEntity(new File("F:\\image\\sendpix0.jpg"));
//            StringEntity comment = new StringEntity("A binary file of some kind", ContentType.TEXT_PLAIN);
//
//            HttpEntity reqEntity = HttpEntityWrapper.create().addPart("bin", bin).addPart("comment", comment).build();
//
//            httppost.setEntity(reqEntity);
//
//            System.out.println("executing request " + httppost.getRequestLine());
//            CloseableHttpResponse response = httpclient.execute(httppost);
//            try {
//                System.out.println("----------------------------------------");
//                System.out.println(response.getStatusLine());
//                HttpEntity resEntity = response.getEntity();
//                if (resEntity != null) {
//                    System.out.println("Response content length: " + resEntity.getContentLength());
//                }
//                EntityUtils.consume(resEntity);
//            } finally {
//                response.close();
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}

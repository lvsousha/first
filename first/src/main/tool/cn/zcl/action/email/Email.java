package cn.zcl.action.email;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Email {

	protected final Log logger = LogFactory.getLog(getClass());




//	public boolean send(EmailModel mail) {
//        // 发送email
//        HtmlEmail email = new HtmlEmail();
//        try {
//            email.setHostName(mail.getHost());// 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
//            email.setCharset(EmailModel.ENCODEING);// 字符编码集的设置
//            email.addTo(mail.getReceiver());// 收件人的邮箱
//            email.setFrom(mail.getSender(), mail.getName());// 发送人的邮箱
//            email.setAuthentication(mail.getUsername(), mail.getPassword());// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
//            email.setSubject(mail.getSubject());// 要发送的邮件主题
//            email.setMsg(mail.getMessage());// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
//            email.send();// 发送
//            if (logger.isDebugEnabled()) {
//                logger.debug(mail.getSender() + " 发送邮件到 " + mail.getReceiver());
//            }
//            return true;
//        } catch (EmailException e) {
//            e.printStackTrace();
//            logger.info(mail.getSender() + " 发送邮件到 " + mail.getReceiver()
//                    + " 失败");
//            return false;
//        }
//    }

	public static void main(String[] args) {
		EmailModel mail = new EmailModel();
		Email email = new Email();
        mail.setHost("smtp.163.com"); // 设置邮件服务器,如果不用163的,自己找找看相关的
        mail.setSender("zclvideo@163.com");
        mail.setReceiver("zclvideo@163.com"); // 接收人
        mail.setUsername("zclvideo@163.com"); // 登录账号,一般都是和邮箱名一样吧
        mail.setPassword("lrnhzqarplzpdyjg"); // 发件人邮箱的登录密码
        mail.setSubject("aaaaaaaaa");
        mail.setMessage("bbbbbbbbbbbbbbbbb");
        email.send(mail);
        System.out.println("SUCCESS");
    }


	/**
      * @param args
      * @throws Exception
      */
     public void send(EmailModel mail){
    	 try {
	    	 Properties prop = new Properties();
	         prop.setProperty("mail.host", mail.getHost());
	         prop.setProperty("mail.transport.protocol", "smtp");
	         prop.setProperty("mail.smtp.auth", "true");
	         Session session = Session.getInstance(prop);//1、创建session
//	         session.setDebug(true);//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
	         Transport ts = session.getTransport();//2、通过session得到transport对象
//	         ts.connect("smtp.163.com", "zclvideo@163.com", "lrnhzqarplzpdyjg");//3、连上邮件服务器，需要发件人提供邮箱的用户名和密码进行验证
	         ts.connect(mail.getHost(), mail.getSender(), mail.getPassword());
	         Message message = createImageMail(session, mail);//4、创建邮件
	         ts.sendMessage(message, message.getAllRecipients());//5、发送邮件
	         ts.close();
    	 } catch (Exception e) {
           e.printStackTrace();
           logger.info(mail.getSender() + " 发送邮件到 " + mail.getReceiver()+ " 失败");
       }
     }

     public Session createSession(EmailModel mail){
    	 Properties prop = new Properties();
         prop.setProperty("mail.host", mail.getHost());
         prop.setProperty("mail.transport.protocol", "smtp");
         prop.setProperty("mail.smtp.auth", "true");
         Session session = Session.getInstance(prop);//1、创建session
//         session.setDebug(true);//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
         return session;
     }

     public static MimeMessage createImageMail(Session session, EmailModel mail) throws Exception {
    	 MimeMessage message = new MimeMessage(session);//创建邮件
         message.setFrom(new InternetAddress(mail.getSender()));//发件人
         message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getReceiver()));//收件人
//         message.setRecipients(Message.RecipientType.CC,InternetAddress.parse("zclvideo@163.com"));//抄送人
//         message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse("zclvideo@163.com"));//密送人

         message.setSubject("带图片的邮件");//邮件标题
         MimeBodyPart text = new MimeBodyPart();// 准备邮件正文数据
         text.setContent("这是一封邮件正文带图片", "text/html;charset=UTF-8");
         MimeBodyPart image = new MimeBodyPart();// 准备图片数据
         FileDataSource dataSource = new FileDataSource("D:\\test.jpg");
         DataHandler dh = new DataHandler(dataSource);
         image.setDataHandler(dh);
         image.setFileName(dataSource.getName());

//         MimeMultipart mm = new MimeMultipart();// 描述数据关系
         MimeMultipart mm = new MimeMultipart("mixed");// 描述数据关系
         mm.addBodyPart(text);
//         mm.addBodyPart(image);

         message.setContent(mm);
         message.saveChanges();

         message.writeTo(new FileOutputStream("E:\\Email\\ImageMail.eml"));//将创建好的邮件写入到E盘以文件的形式进行保存

         return message;//返回创建好的邮件
     }
}

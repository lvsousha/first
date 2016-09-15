package cn.zcl.action.password;

import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.eclipse.jetty.util.security.Credential.MD5;

public class Encryption {
	
	private static final String HMAC_SHA1 = "HmacSHA1";     
    private static final String ENCODING = "UTF-8";

	public static String base64(String str){
		String base64 = Base64.encodeBase64String(str.getBytes());
		System.out.println(base64);
		return base64;
	}

	public static String hex(String str){
		String hex = Hex.encodeHexString(str.getBytes());
		System.out.println(hex);
		return hex;
	}

	public static String md5(String str, String salt){
		String sha = new Sha256Hash(str, salt).toString();
		System.out.println(sha);
		return sha;
	}
	
	public static byte[] getSignature(byte[] data, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {  
        SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);  
        Mac mac = Mac.getInstance(HMAC_SHA1);  
        mac.init(signingKey);  
        byte[] rawHmac = mac.doFinal(data);  
        return rawHmac;  
    }

	/** 
     * 获取MD5 结果字符串 
     *  
     * @param source 
     * @return 
     */  
    public static String encode(byte[] source) {  
        String s = null;  
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
        try {  
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");  
            md.update(source);  
            byte tmp[] = md.digest();   
            char str[] = new char[16 * 2];   
            int k = 0;   
            for (int i = 0; i < 16; i++) {   
                byte byte0 = tmp[i];   
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];   
                str[k++] = hexDigits[byte0 & 0xf];   
            }  
            s = new String(str);   
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return s;  
    }  
      
    public static String getMD5(String source) {  
        return (source == null || "".equals(source)) ? "" : getMD5(source);  
    }
    
    public static String encodeBase64(byte[]input) throws Exception{  
        Class<?> clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod= clazz.getMethod("encode", byte[].class);  
        mainMethod.setAccessible(true);  
         Object retObj=mainMethod.invoke(null, new Object[]{input});  
         return (String)retObj;  
    }
    
    public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
    
	public static void main(String[] args) throws Exception {
//		Encryption e = new Encryption();
//		Encryption.base64("hello");
//		Encryption.hex("hello");
//		Encryption.md5("hello", "123");
//		https://api.thinkpage.cn/v3/weather/now.json?location=beijing&ts=1473321996&ttl=3000&uid=U1D39FC8C8&sig=Tn1oDyU8uCaV8ZbImG5FGU90te8=
		String data = "ts=1473321996&ttl=3000&uid=U1D39FC8C8";
		String key = "isxsnikgqomz9sxv";
		String datas = "ts=1443079775&ttl=30&uid=U123456789";
		String keys = "secret";
		String test = "dTYeoN8WdOfW4PiwgEdLa0gWFzo=";
		try {
			byte[] encryData = Encryption.getSignature(data.getBytes(ENCODING), key.getBytes(ENCODING));
			String encode = Encryption.encodeBase64(encryData);
			System.out.println(encode);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

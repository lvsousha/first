package cn.zcl.action.password;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.crypto.hash.Sha256Hash;

public class Encryption {

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

	public static void main(String[] args) {
//		Encryption e = new Encryption();
//		Encryption.base64("hello");
//		Encryption.hex("hello");
//		Encryption.md5("hello", "123");
		Encryption.md5("hello", "abc");
	}

}

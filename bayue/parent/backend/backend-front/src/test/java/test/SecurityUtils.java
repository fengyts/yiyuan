//package test;
//
//import org.apache.shiro.codec.Base64;
//import org.apache.shiro.crypto.hash.Md5Hash;
//
//public class SecurityUtils {
//	
//	public static String encriptyBase64(String str){
//		return Base64.encodeToString(str.getBytes());
//	}
//	
//	public static String decEncBase64(String str){
//		return Base64.decodeToString(str);
//	}
//	
//	public static String md5(String str){
//		return new Md5Hash(str, "test").toString();
//	}
//
//	public static void main(String[] args) {
//		String str = SecurityUtils.md5("123456");
//		System.out.println(str);
//	}
//	
//}

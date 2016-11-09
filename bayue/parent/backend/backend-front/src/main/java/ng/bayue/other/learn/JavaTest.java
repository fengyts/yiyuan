package ng.bayue.other.learn;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import ng.bayue.util.SecurityUtil;

public class JavaTest {
	
	public static void test(){
		//原始 密码 
		String source = "admin";
		//盐
		String salt = "afc0f8db123621b989c0ae68e8792e62";
		String str = SecurityUtil.encryptMD5(salt);
		System.out.println(str);
		System.out.println(SecurityUtil.encryptMD5(source+str));
	}
	
	public static void test1(){
		//原始 密码 
		String source = "superadmin";
		//盐
		String salt = "afc0f8db123621b989c0ae68e8792e62";
		//散列次数
		int hashIterations = 2;
		//上边散列1次：f3694f162729b7d0254c6e40260bf15c
		//上边散列2次：36f2dfa24d0a9fa97276abbe13e596fc
		
		
		//构造方法中：
		//第一个参数：明文，原始密码 
		//第二个参数：盐，通过使用随机数
		//第三个参数：散列的次数，比如散列两次，相当 于md5(md5(''))
//		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
//		String password_md5 =  md5Hash.toString();
//		System.out.println(password_md5);
		
		//第一个参数：散列算法 
		SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
		System.out.println(simpleHash.toString());//cad86dfc11166803d1dbd559d8afe1e1
	}
	
	public static void xor(){
		String str = "abcde";
		int a = 2;
		int b = a << 1 + 1;
		System.out.println(b);
	}
	
	public static void main(String[] args) {
		test1();
	}
	
	
	public class Passwords {
		   public static final String ALGORITHM = "PBKDF2WithHmacSHA1";
		   public static final int ITERATION_COUNT = 8192;
		   public static final int KEY_SIZE = 160;

		   public byte[] hashPassword(char[] password, byte[] salt)
		          throws GeneralSecurityException {
		      return hashPassword(password, salt, ITERATION_COUNT, KEY_SIZE);
		   }

		   public byte[] hashPassword(char[] password, byte[] salt,
		          int iterationCount, int keySize) throws GeneralSecurityException {
		      PBEKeySpec spec = new PBEKeySpec(password, salt, iterationCount, keySize);
		      SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
		      return factory.generateSecret(spec).getEncoded();
		   }

	}
	
	
	
}



package test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

import ng.bayue.item.domain.ItemDetailDO;
import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.domain.dto.ItemDTO;
import ng.bayue.util.SecurityUtil;


public class Test {
	
	public static void test(){
		// 原始 密码
		String source = "admin";
		// 盐
		String salt = "15bc08f47ebb0a077c2378bd88781f2a";
		byte[] bytes = source.getBytes();
		byte[] saltb = salt.getBytes();
		byte[] hash = SecurityUtil.hash(bytes, saltb, 2);
		String s = new String(SecurityUtil.encode(hash));
		System.out.println(s);
//		System.out.println(SecurityUtil.encode(SecurityUtil.encrypt(bytes)));
//		System.out.println(SecurityUtil.encode(SecurityUtil.encrypt(SecurityUtil.encrypt(bytes))));
	}
	
	public static void test1(){
		//原始 密码 
		String source = "admin";
		//盐
		String salt = "15bc08f47ebb0a077c2378bd88781f2a";
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
	
	public static void main(String[] args) {
		test();
		test1();
	}

}

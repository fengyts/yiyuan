package test;

import java.io.File;

import ng.bayue.fastdfs.DfsUtils;
import ng.bayue.redis.RedisCacheUtil;

public class UtilTest {
	
	public static void testRedisCache(){
		String testStrCache = RedisCacheUtil.getRedisCacheString("test1");
		boolean setCache = RedisCacheUtil.setRedisCache("test1", "testhahaha", null);
		System.out.println(testStrCache);
		System.out.println(setCache);
		boolean setCacheStr = RedisCacheUtil.setRedisCacheString("test2", "testhahahtest2", 50);
		System.out.println(setCacheStr);
		
	}
	
	public static void testFastDfs(){
		String imgPath = "E:/test/Koala.jpg";
		File file = new File(imgPath);
		String dfsPath = DfsUtils.uploadFile(file);
		System.out.println(dfsPath);
	}
	
	public static void main(String[] args) {
		
//		UtilTest.testRedisCache();
		testFastDfs();
		
	}

}

package test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import ng.bayue.snatch.domain.promotion.TopicDO;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class TestJava {
	
	
	@Test
	public void fileTest(){
		File file = new File("E:/wanmei/elements.data");
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			int p = (bis.read() << 8) + bis.read();
			String str = Integer.toHexString(p);
			System.out.println(str);
			byte[] bytes = new byte[1024];
			int len = 0;
			len = bis.read(bytes);
//			String res1 = new String(bytes,0,len);
			String res1 = Integer.toBinaryString(len);
			System.out.println(res1);
//			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "iso-8859-1");
//			char[] cbuf = new char[1024];
//			int len = 0;
//			String res = "";
//			while(-1 != (len = isr.read(cbuf))){
//				res += new String(cbuf,0,len);
//				System.out.println(res);
//			}
		} catch (IOException e) {
			
		}
	}

	@Test
	public void test() {
//		String str= "[{\"specGroupId\":\"2\",\"sort\":0},{\"specGroupId\":\"2\",\"sort\":0}]";
//		List<DetailSpecDO> list = new ArrayList<DetailSpecDO>();
//		JSONArray arr = (JSONArray) JSONArray.parse(str);
//		list = JSONArray.parseArray(str, DetailSpecDO.class);
//		System.out.println(list.size());
		
		String str = "{id:\'\',name:\'\',isTest:0,type:0,status:1,durationType:1,progress:0,startTime:\'\',endTime:\'\',sort:0,sortLock:0,needPoint:0,pointDeduction:\'\',description:\'\',remark:\'\'}";
		String str1 = "{'id':'','name':'','isTest':'0','type':'0','status':'1','durationType':'1','progress':'0','startTime':'','endTime':'','sort':'0','sortLock':'0','needPoint':'0','pointDeduction':'','description':'','remark':''}";
		TopicDO topicDO = JSONObject.parseObject(str1, TopicDO.class);
		System.out.println(topicDO.toString());
	}
	
	
	
	public static void main(String[] args) {
		
		
	}

	
	
		

}

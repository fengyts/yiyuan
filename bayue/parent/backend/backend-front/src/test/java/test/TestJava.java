package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ng.bayue.item.domain.DetailSpecDO;
import ng.bayue.promotion.domain.TopicDO;

public class TestJava {

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
	
		

}

package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;

import ng.bayue.item.domain.DetailSpecDO;

public class TestJava {

	@Test
	public void test() {
		String str= "[{\"specGroupId\":\"2\",\"sort\":0},{\"specGroupId\":\"2\",\"sort\":0}]";
		List<DetailSpecDO> list = new ArrayList<DetailSpecDO>();
		JSONArray arr = (JSONArray) JSONArray.parse(str);
		list = JSONArray.parseArray(str, DetailSpecDO.class);
		System.out.println(list.size());
	}

}

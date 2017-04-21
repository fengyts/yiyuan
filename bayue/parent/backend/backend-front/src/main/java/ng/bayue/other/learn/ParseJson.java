package ng.bayue.other.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;

import ng.bayue.base.domain.SpecGroupLinkDO;

public class ParseJson {
	
	@SuppressWarnings("unused")
	private static final String jsonStr = "{\"主键\":\"3\",\"担保编号\":\"DBBH00000003\",\"担保类型\":\"03\",\"担保名称\":\"坝上街A1地块 东方资产9亿\",\"合同\":\"2\",\"评估价值\":\"900000000.00\",\"登记日期\":\"2016-11-18\",\"抵押物所属企业\":\"恒盛恒茂合肥\","
			+ "\"抵押物详情\":\"项目涵盖了坝上街A1及A2南北两处地块，总占地面积约为171.52亩（114347.9平方米），规划总建筑面积为130.077万平方米，容积率8.2。其中：地上建筑面积：96.85万平方米，计容面积93.77万平方米；地下建筑面积：33.22万平方米。南块（A2）四证齐全，北块（A1）已有《国有土地使用证》、《建设用地规划许可证》，可以用于抵押。"
			+ "\n项目总整体投资（含A1地块与A2地块）为98.4亿元，其中，土地拆迁款等13.1亿元，建设投资（含土建、装修；地上、地下；公共基础设施）66亿元，工程建设预备费用3.2亿元，管理费用2.3亿，营销费用4.9亿元，财务费用8.9亿元。目前已投24.2亿，其中，自有资金投入7.7亿元，对外融资16.5亿。项目建设还需投入约75.2亿资金，预计整体实现销售收入132亿元。\",\"备注\":\"测试用数据\"}";
	
	/**
	 * <pre>
	 * 使用net.sf.json解析json字符串时，字符串中不能包含特殊字符，否则会抛异常，可以使用alibaba的fastjson
	 * </pre>
	 *
	 * @param str
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public static void parse(String str){
		net.sf.json.JSONObject o = net.sf.json.JSONObject.fromObject(str);
		Set set = o.keySet();
		Iterator<?> iterator = o.keys();
		Iterator<?> it = set.iterator();
		while(iterator.hasNext()){
			Object key = iterator.next();
			System.out.println(key);
			String value = (String) o.get(key);
			System.out.println(value);
		}
		System.out.println();
	}
	
	public static void parse1(String str){
		com.alibaba.fastjson.JSONObject o = com.alibaba.fastjson.JSONObject.parseObject(str);
		Set<String> set = o.keySet();
		
		Iterator<?> it = set.iterator();
		while(it.hasNext()){
			String key = (String) it.next();
			String value = o.getString(key);
			System.out.println(key);
			System.out.println(value);
		}
	}
	
	public static void test(){
		String jsonData = "[{\"groupId\""+":"+"1"+","+"specId"+":"+"1"+","+"sort"+":"+"2"+"}]";
		List<SpecGroupLinkDO> list = JSONArray.parseArray(jsonData, SpecGroupLinkDO.class);
	}
	
	
	public static void main(String[] args) {
		String str = "{\"主键\":\"10\",\"贷款人编号\":\"DKR000010\",\"贷款人名称\":\"测试\",\"贷款人类别\":\"Personal\"}";
//		ParseJson.parse(jsonStr);
//		parse1(jsonStr);
		String str1 = "abcdef\rabcdef\nabcdef";
		char[] c = str1.toCharArray();
		List<Integer> index = new ArrayList<Integer>();
		List<String> list = new ArrayList<String>();
		for(int i= 0;i<c.length;i++){
			char cc = c[i];
			if(cc == '\n' || cc == '\r'){
				index.add(i);
				continue;
			}
			list.add(Character.toString(cc));
		}
		System.out.println(list);
		System.out.println("temp:"+Arrays.toString(list.toArray(new String[0])));
		
		final String R = Character.toString('\r');
		final String N = Character.toString('\n');;
		for(int i : index){
			list.add(i, R);
		}
		System.out.println("rn:"+list);
		
		String regex = "?:\r|\n";
		String r = "\r";
		System.out.println(str1.contains("\r"));
		str1 = str1.replaceAll("[?:\r\n]", "");
		System.out.println("str1:"+str1);
	}
	
	
	
}


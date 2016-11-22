package ng.bayue.other.learn;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;



public class ParseJson {
	
	@SuppressWarnings({ "unused", "rawtypes" })
	public static void parse(String str){
		net.sf.json.JSONObject o = net.sf.json.JSONObject.fromObject(str);
		Set set = o.keySet();
		Iterator<?> iterator = o.keys();
		Iterator<?> it = set.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		String str = "{\"主键\":\"10\",\"贷款人编号\":\"DKR000010\",\"贷款人名称\":\"测试\",\"贷款人类别\":\"Personal\"}";
		ParseJson.parse(str);
		
		SortedSet<Integer> set = new TreeSet<Integer>();
		set.add(31);
		set.add(22);
		set.add(100);
		set.add(8);
		System.out.println(set.toString());
	}
	
	
	
}


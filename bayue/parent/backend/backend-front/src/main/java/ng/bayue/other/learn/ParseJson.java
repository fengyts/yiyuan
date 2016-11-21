package ng.bayue.other.learn;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;



public class ParseJson {
	
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static void parse(String str){
		net.sf.json.JSONObject o = net.sf.json.JSONObject.fromObject(str);
		Set set = o.keySet();
		Iterator<Object> iterator = o.keys();
		Iterator<Object> it = set.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		String str = "{\"主键\":\"10\",\"贷款人编号\":\"DKR000010\",\"贷款人名称\":\"测试\",\"贷款人类别\":\"Personal\"}";
		ParseJson.parse(str);
	}
	
	
	
}


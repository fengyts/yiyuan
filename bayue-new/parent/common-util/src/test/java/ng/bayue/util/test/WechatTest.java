package ng.bayue.util.test;

import weixin.popular.api.TokenAPI;
import weixin.popular.bean.token.Token;

public class WechatTest {
	
	//373192335@qq.com
	private final static String appid = "wxa0e3c71e4ba40148";
	private final static String secret = "268520f379eb378234b9fffc2c8f7d05"; 
			
	
	public static void test(){
		Token token = TokenAPI.token(appid,secret);
		System.out.println(token.getAccess_token());
	}
	
	
	public static void main(String[] args) {
		test();
	}
	
}

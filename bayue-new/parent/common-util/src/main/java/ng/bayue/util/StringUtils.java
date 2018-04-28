package ng.bayue.util;

import java.util.Random;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

	 /**
     * 获取随机len位整数
     * 仅支持1到9位
     * @param len 
     * @return
     */
    public static int getRandomNum(int len) {
    	if (len < 1 || len > 9) {
    		throw new IllegalArgumentException("参数len必须满足：>=1&&<=9");
    	}
    	int n = (int)Math.pow(10, (len -1));
    	return new Random().nextInt(9 * n) + n;
    }
    
    /**
     * 获取随机len位字符
     * @param len
     * @return
     */
    public static String getRandomChar(int len){
    	StringBuilder result = new StringBuilder();
    	for (int i = 0; i < len; i ++) {
    		char c = (char)(new Random().nextInt(26) + 65);
    		result.append(String.valueOf(c));
    	}
		return result.toString();
	}
    
    /**
	 * 用*替换部分字符
	 * @param str 待替换的字符
	 * @param start 开始索引
	 * @param end 结束索引
	 * @return
	 */
	public static String replaceWithAsterisk(String str, int start, int end) {
		if (isBlank(str) || start < 0 || start > end 
				|| (str.length() - 1) < end ) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.substring(0, start));
		for (int i = 0; i < (end - start + 1); i ++) {
			sb.append("*");
		}
		sb.append(str.substring((end + 1)));
		return sb.toString();
	}
    
    /**
     * 获取受保护的手机号
	 * 将手机号中间四位转为*
	 * @param mobile
	 * @return
	 */
	public static String securityMobile(String mobile) {
		return replaceWithAsterisk(mobile, 3, 6);
	}
	
	
}

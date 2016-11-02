package ng.bayue.other.learn;

import java.security.SecureRandom;
import java.text.DecimalFormat;

public class DecimalFormatTest {
	
	public static void test(){
		String pattern = "##,###.##";
		DecimalFormat df = new DecimalFormat(pattern);
		String str = df.format(123456.34);
		System.out.println(str);
		
	}
	
	
	public static void main(String[] args) {
//		test();
		System.out.println(Integer.MAX_VALUE);
	}
	
}

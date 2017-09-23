package ng.bayue.util;

import java.util.Arrays;

public class LettersAndNumberUtil {

	private static final int[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	private static final char[] letters = { 
			'a', 'b', 'c', 'd', 'e', 'f', 
			'g', 'h', 'i', 'j', 'k', 'l', 
			'm', 'n', 'o', 'p', 'q', 'r', 
			's', 't', 'u', 'v', 'w', 'x', 
			'y', 'z' 
	};
	
	private static final int TYPE_NUMBER = 0;
	
	private static final int TYPE_LETTER = 1;
	
	private static final int TYPE_ALL = 2;

	private static char[] initAllChar(){
		char[] allChars = new char[36];
//		allChars = Arrays.copyOf(letters, letters.length);
		System.arraycopy(letters, 0, allChars, 0, letters.length);
		System.out.println(allChars.length);
		System.out.println(Arrays.toString(allChars));
		return allChars;
	}
	
	public static void main(String[] args) {
//		initAllChar();
	}

}

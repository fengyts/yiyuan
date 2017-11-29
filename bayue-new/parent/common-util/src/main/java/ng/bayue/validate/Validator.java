package ng.bayue.validate;

import java.util.regex.Pattern;

import ng.bayue.constants.RegexConstant;

public class Validator implements RegexConstant {
	
	public static boolean isNumber(String src) {
		return Pattern.matches(NUMBER_ONLY, src);
	}

	public static boolean isMobile(String src) {
		return Pattern.matches(MOBILE, src);
	}

	public static boolean isEmail(String src) {
		return Pattern.matches(EMAIL, src);
	}

	public static boolean isPassword(String src) {
		return Pattern.matches(PASSWORD, src);
	}

	public static boolean isPasswordStrong(String src) {
		return Pattern.matches(PASSWORD_STRONG, src);
	}

}

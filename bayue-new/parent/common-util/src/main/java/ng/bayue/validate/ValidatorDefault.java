package ng.bayue.validate;

import java.util.regex.Pattern;

import ng.bayue.constants.RegexConstant;
import ng.bayue.util.StringUtils;

@Deprecated
public class ValidatorDefault implements RegexConstant {

	private String src;
	private boolean validated = true;

	public ValidatorDefault(String src) {
		if (StringUtils.isBlank(src)) {
			this.validated = false;
		}
		this.src = src;
	}

	public boolean isNumber() {
		return validated ? Pattern.matches(NUMBER_ONLY, src) : false;
	}

	public boolean isMobile() {
		return validated ? Pattern.matches(MOBILE, src) : false;
	}

	public boolean isEmail() {
		return validated ? Pattern.matches(EMAIL, src) : false;
	}

	public boolean isPassword() {
		return validated ? Pattern.matches(PASSWORD, src) : false;
	}

	public boolean isPasswordStrong() {
		return validated ? Pattern.matches(PASSWORD_STRONG, src) : false;
	}

	public static void main(String[] args) {
		ValidatorDefault vd = new ValidatorDefault("32");
		boolean flag = vd.isNumber();
		System.out.println(flag);
	}

}

package ng.bayue.constants;

/**
 * <pre>
 * 正则表达式规则,参考文章<href>https://c.runoob.com/front-end/854</href>
 * </pre>
 *
 * @author lenovopc
 * @version $Id: RegexConstant.java, v 0.1 2017年11月27日 下午12:58:13 lenovopc Exp $
 */
public interface RegexConstant {

	/** 纯数字 */
	public static final String NUMBER_ONLY = "^[0-9]*$";
	/** 手机号码 */
	public static final String MOBILE = "^[1][3,4,5,7,8][0-9]{9}$";
	/** 手机号码1 */
	public static final String MOBILE1 = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
	/** 固话 */
	public static final String TEL_PHONE = "^(\\(\\d{3,4}-)|\\d{3.4}-)?\\d{7,8}$";
	/** 密码(以字母开头，长度在6~18之间，只能包含字母、数字和下划线) */
	public static final String PASSWORD = "^[a-zA-Z]\\w{5,17}$";
	/** 强密码 :必须包含大小写字母和数字的组合，不能使用特殊字符,6~18位 */
	public static final String PASSWORD_STRONG = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,18}$";
	/** email */
	public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	/** 份证号 */
	public static final String IDENTITY_CARD = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
	/** IP */
	public static final String IP_ADDRESS = "((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))";

	/** 汉字 */
	public static final String CHINESS_CHAR = "^[\\u4e00-\\u9fa5]{0,}$";
	/** 日期 */
	public static final String DATE = "^\\d{4}-\\d{1,2}-\\d{1,2}";

}

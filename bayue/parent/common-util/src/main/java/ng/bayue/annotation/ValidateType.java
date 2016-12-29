package ng.bayue.annotation;

public enum ValidateType {

	/** 校验基本类型或者字符串非空  */
	NOTBLANK, 
	/** 校验基本类型、字符串以及集合非空  */
	NOTEMPTY, 
	/** 校验是否数字  */
	NUMERIC, 
	/** 校验邮箱 */
	EMAIL;

}

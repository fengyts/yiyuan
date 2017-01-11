package ng.bayue.annotation;

/**
 * <pre>
 * 实体属性有效性校验类型枚举
 * </pre>
 *
 * @author lenovopc
 * @version $Id: ValidateType.java, v 0.1 2017年1月11日 下午5:22:50 lenovopc Exp $
 */
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

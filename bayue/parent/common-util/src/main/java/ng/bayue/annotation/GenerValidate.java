package ng.bayue.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface GenerValidate {

	/**
	 * <pre>
	 * 校验提示消息
	 * </pre>
	 *
	 * @return
	 */
	String message() default "数据不能为空";

	/**
	 * <pre>
	 * 正则表达式校验
	 * </pre>
	 *
	 * @return
	 */
	String regex() default "";

	/**
	 * <pre>
	 * 校验类型
	 * </pre>
	 *
	 * @return
	 */
	ValidateType[] type() default ValidateType.NOTBLANK;
	

}

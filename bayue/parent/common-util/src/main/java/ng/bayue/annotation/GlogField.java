package ng.bayue.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 通用日志注解
 * </pre>
 *
 * @author lenovopc
 * @version $Id: GlogField.java, v 0.1 2016年12月29日 上午10:54:33 lenovopc Exp $
 */
@Documented // 说明该注解将被包含在javadoc中
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ ElementType.FIELD, ElementType.METHOD }) // 定义注解的作用目标**作用范围字段、枚举的常量/方法
public @interface GlogField {

	String name() default "";

	String value() default "";

	String description() default "";

}

package ng.bayue.snatch.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 使用该注解的类或方法必须经过签名认证
 * </pre>
 *
 * @author fengyts
 * @version $Id: Authentication.java, v 0.1 2017年5月28日 下午1:43:54 fengyts Exp $
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authentication {

}

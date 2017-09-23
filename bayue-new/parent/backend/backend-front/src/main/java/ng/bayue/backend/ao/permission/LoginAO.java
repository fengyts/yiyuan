package ng.bayue.backend.ao.permission;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginAO {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	 public boolean loginIn(final String loginName, final String password, final boolean rememberMe) throws Exception {
	        // subject理解成权限对象。类似user
	        Subject subject = SecurityUtils.getSubject();
	        // 创建用户名和密码的令牌
//	        ShiroUPToken token = new ShiroUPToken(loginName, password);
	        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
	        token.setRememberMe(rememberMe);
	        // 记录该令牌，
	        try {
	            // 调用subject.login方法进行登录，其会自动委托给SecurityManager.login方法进行登录；
	            /*
	             * 1、收集用户身份/凭证，即如用户名/密码； 2、调用Subject.login进行登录，如果失败将得到相应的AuthenticationException异常，根据异常提示用户错误信息；否则登录成功；
	             * 3、最后调用Subject.logout进行退出操作。
	             */
	            subject.login(token);
	            /**
	             * @author warlock
	             * 修改为前置认证授权
	             * 
	             * */
	        } catch (ExcessiveAttemptsException e) {
	            logger.error("login fail:" + e.getMessage(), e);
	            throw new Exception(e.getMessage());
	        } catch (LockedAccountException ex) {
	            logger.error("login fail:" + ex.getMessage(), ex);
	            throw new Exception("账号已被锁定");
	        } catch (DisabledAccountException ex) {
	            logger.error("login fail:" + ex.getMessage(), ex);
	            throw new Exception("账号已被禁用");
	        } catch (UnknownAccountException ex) {
	            logger.error("login fail:" + ex.getMessage(), ex);
	            throw new Exception("用户名或密码错误");
	        } catch (IncorrectCredentialsException ex) {
	            logger.error("login fail:" + ex.getMessage(), ex);
	            throw new Exception("用户名或密码错误");
	        } catch (AuthenticationException e) {
	            logger.error("login fail:" + e.getMessage(), e);
	            throw new Exception(e.getMessage());
	        }
	        return true;
	    }
	
	
}

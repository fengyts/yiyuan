package ng.bayue.backend.shiro_old;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;


/**
 * 
 * <pre>
 * 
 * </pre>
 *
 * @author yuwenjie
 * @version $Id: ShiroFilter.java, v 0.1 2014年12月30日 下午8:13:36 yuwenjie Exp $
 */
public class ShiroFilter extends FormAuthenticationFilter {

	static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	static final String DEFAULT_USERTYPE_PARAM = "userType";
	private String userTypeParam = DEFAULT_USERTYPE_PARAM;

	public String getUserType() {
		return userTypeParam;
	}

	protected String getUserType(ServletRequest request) {
		return WebUtils.getCleanParam(request, getUserType());
	}

	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		String userType = getUserType(request);
		request.setAttribute("userType", userType);
		return new ShiroUPToken(username, password.toCharArray(), rememberMe,
				host, captcha, userType);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {//已登录
			if (isLoginSubmission(request, response)) {
				return executeLogin(request, response);
			} else {
				return true;
			}
		} else {//未登录或者cookie登录
			Subject subject = SecurityUtils.getSubject();
			HttpServletResponse res = (HttpServletResponse) response;
			HttpServletRequest req = (HttpServletRequest) request;
			 if (req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ //如果是ajax请求响应头会有，x-requested-with  
				 res.setContentType("text/html;charset=utf-8");
//				 res.setHeader("errorCode", ErrorCode.USER_UN_LOGIN.code.toString());
//				 res.addHeader("errorMessage", ErrorCode.USER_UN_LOGIN.value);
//				 res.addHeader("returnUrl", UriConstant.USER.SPACE + "/toLogin");
				 res.setHeader("errorCode", "未登录");
				 res.addHeader("errorMessage", "登录失败");
				 res.addHeader("returnUrl", "/toLogin");
				 return false;
            }else{
            	if(null == subject || null == subject.getPrincipal()){
   				 	saveRequestAndRedirectToLogin(request, response);
   				 	return false;
            	}else return true;
            }
		}
	}

}

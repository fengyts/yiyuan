package ng.bayue.backend.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class ShiroFilter extends FormAuthenticationFilter {

	static final String DEFAULT_KAPTCHA_PARAM = "kaptcha";
	private String kaptchaParam = DEFAULT_KAPTCHA_PARAM;

	public String getKaptchaParam() {
		return kaptchaParam;
	}

	protected String getKaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getKaptchaParam());
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
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		String kaptcha = getKaptcha(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		return new MyUsernamePasswordToken(username, password.toCharArray(), rememberMe, host,
				kaptcha);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {
		if (isLoginRequest(request, response)) {// 已登录
			if (isLoginSubmission(request, response)) {
				return executeLogin(request, response);
			} else {
				return true;
			}
		} else {// 未登录或者cookie登录
			Subject subject = SecurityUtils.getSubject();
			if (null == subject || null == subject.getPrincipal()) {
				saveRequestAndRedirectToLogin(request, response);
				return false;
			} else
				return true;

		}
	}

}
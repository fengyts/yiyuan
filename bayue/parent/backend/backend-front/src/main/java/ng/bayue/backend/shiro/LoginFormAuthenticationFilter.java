package ng.bayue.backend.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;

import com.google.code.kaptcha.Constants;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author lenovopc
 * @version $Id: LoginFormAuthenticationFilter.java, v 0.1 2016年11月7日 下午2:46:57
 *          lenovopc Exp $
 */
public class LoginFormAuthenticationFilter extends FormAuthenticationFilter {

	@Value("#{meta['kaptchaFlag']}")
	private Boolean kaptchaFlag;

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (kaptchaFlag) {
			HttpServletRequest hsRequest = (HttpServletRequest) request;
			HttpSession session = hsRequest.getSession();
			String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
			String kaptcha = request.getParameter("kaptcha");
			if (StringUtils.isNotBlank(kaptcha)&&StringUtils.isNotBlank(code) && !kaptcha.equals(code)) {
				hsRequest.setAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, "kaptchaError");
				hsRequest.setAttribute("backViewUsername", request.getParameter(DEFAULT_USERNAME_PARAM));
				hsRequest.setAttribute("backViewPassword", request.getParameter(DEFAULT_PASSWORD_PARAM));
				return true;
			}
		}
		return super.onAccessDenied(request, response);
	}

}

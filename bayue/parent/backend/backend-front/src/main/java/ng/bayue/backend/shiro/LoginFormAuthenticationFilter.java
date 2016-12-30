package ng.bayue.backend.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Value;

import com.google.code.kaptcha.Constants;

import ng.bayue.backend.constant.BackendConstant;

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

	@Value("#{meta['login.kaptchaFlag']}")
	private Boolean kaptchaFlag;

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (kaptchaFlag) {
			HttpServletRequest hsRequest = (HttpServletRequest) request;
			HttpSession session = hsRequest.getSession();
			String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
			String kaptcha = request.getParameter("kaptcha");
			if(StringUtils.isBlank(kaptcha) || StringUtils.isBlank(code)){
				hsRequest.setAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, BackendConstant.SysUser.KAPTCHAEMPTY);
				return true;
			}
			if (!kaptcha.equals(code)) {
				hsRequest.setAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, BackendConstant.SysUser.KAPTCHAERROR);
				hsRequest.setAttribute("backViewUsername", request.getParameter(DEFAULT_USERNAME_PARAM));
				hsRequest.setAttribute("backViewPassword", request.getParameter(DEFAULT_PASSWORD_PARAM));
				return true;
			}
		}
		boolean flag = super.onAccessDenied(request, response);
		return flag;
	}
	
	/** 
	 * 重写登录成功后跳转页面，防止当访问的url是iframe的页面的时候，session又过期了，跳转到登陆页，完成登陆操作后，返回了只有iframe的页面
	 * @param request
	 * @param response
	 * @throws Exception
	 * @see org.apache.shiro.web.filter.authc.AuthenticationFilter#issueSuccessRedirect(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request, response, getSuccessUrl());
	}

}

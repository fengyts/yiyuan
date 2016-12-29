package ng.bayue.backend.controller.permission;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ng.bayue.backend.constant.BackendConstant;
import ng.bayue.backend.util.UserHandler;

@Controller
public class LoginController {

	// private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("#{meta['login.kaptchaFlag']}")
	private Boolean kaptchaFlag;

	@RequestMapping({ "/login" })
	public String login(Model model, HttpServletRequest request, String kickout) {
		//已经登录，跳转首页
		Object principal = UserHandler.getSubject().getPrincipal();
		if(principal != null){
			return "redirect:/index";
		}
		
		model.addAttribute("kaptchaFlag", kaptchaFlag);
		if (StringUtils.isNotEmpty(kickout) && "1".equals(kickout)) {
			model.addAttribute("message", "被踢出登录");
		}
		return "/index/sysUser/login";
	}

	/**
	 * <pre>
	 * 登录
	 * </pre>
	 *
	 * @param request
	 * @param loginName
	 *            登录名
	 * @param passwd
	 *            密码
	 * @param rememberMe
	 *            是否记住我
	 * @param securityCode
	 *            验证码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doLogin")
	public String doLogin(HttpServletRequest request, Model model) throws Exception {
		//已经登录，跳转首页
		Object principal = UserHandler.getSubject().getPrincipal();
		if(principal != null){
			return "redirect:/index";
		}
		
		// 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		String exceptionClassName = (String) request
				.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		// 根据shiro返回的异常类路径判断，抛出指定异常信息
		String prompt = "";
		if (exceptionClassName != null) {
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				// 最终会抛给异常处理器
				// throw new Exception("账号不存在");
				prompt = "账号不存在";
				// model.addAttribute("prompt", "账号不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
				// throw new Exception("用户名/密码错误");
				prompt = "用户名/密码错误";
				// model.addAttribute("prompt", "用户名/密码错误");
			} else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
				// throw new LockedAccountException("账号被锁定");
				prompt = "账号被锁定";
				// model.addAttribute("prompt", "账号被锁定");
			} else if (BackendConstant.SysUser.KAPTCHAERROR.equals(exceptionClassName)) {
				model.addAttribute("username", request.getAttribute("backViewUsername"));
				model.addAttribute("password", request.getAttribute("backViewPassword"));
				prompt = "验证码错误";
			} else if(BackendConstant.SysUser.KAPTCHAEMPTY.equals(exceptionClassName)){
				prompt = "验证码为空";
			}else{
				// 最终在异常处理器生成未知错误
				// throw new Exception();
				prompt = "服务器异常";
				// model.addAttribute("prompt", "服务器异常");
			}
		}
		model.addAttribute("message", prompt);
		// 此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
		// 登陆失败还到login页面
		model.addAttribute("kaptchaFlag", kaptchaFlag);
		return "/index/sysUser/login";
	}

}

package ng.bayue.backend.controller.permission;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ng.bayue.backend.ao.permission.LoginAO;
import ng.bayue.backend.ao.sysUser.SysUserAO;

@Controller
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysUserAO sysUserAO;

	@Autowired
	private LoginAO loginAO;

	@RequestMapping({ "/login" })
	public String login(Model model) {
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
	public String doLogin(HttpServletRequest request) throws Exception {
		//如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		//根据shiro返回的异常类路径判断，抛出指定异常信息
		if(exceptionClassName!=null){
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				//最终会抛给异常处理器
				throw new Exception("账号不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(
					exceptionClassName)) {
				throw new Exception("用户名/密码错误");
			} else if("randomCodeError".equals(exceptionClassName)){
				throw new Exception("验证码错误 ");
			}else {
				throw new Exception();//最终在异常处理器生成未知错误
			}
		}
		//此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
		//登陆失败还到login页面
		return "redirect:/login";
	}

}

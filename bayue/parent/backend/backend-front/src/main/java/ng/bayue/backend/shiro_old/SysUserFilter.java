package ng.bayue.backend.shiro_old;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import ng.bayue.backend.constant.BackendConstant;
import ng.bayue.backend.domain.SysUserDO;
import ng.bayue.backend.service.SysUserService;

/**
 * 
 * <pre>
 * 
 * </pre>
 *
 * @author yuwenjie
 * @version $Id: SysUserFilter.java, v 0.1 2014年12月30日 下午8:13:43 yuwenjie Exp $
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private SysUserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest req, ServletResponse resp, Object mappedValue) throws Exception {
    	HttpServletRequest request = (HttpServletRequest) req;
    	HttpServletResponse response = (HttpServletResponse) resp;
        SysUserDO user = (SysUserDO)SecurityUtils.getSubject().getPrincipal();
        if (null == user) { //未登录
            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ //如果是ajax请求响应头会有，x-requested-with  
            	 response.setContentType("text/html;charset=utf-8");
//            	 response.setHeader("errorCode", ErrorCode.USER_UN_LOGIN.code.toString());
//            	 response.addHeader("errorMessage", ErrorCode.USER_UN_LOGIN.value);
//            	 response.addHeader("returnUrl", UriConstant.USER.SPACE + "/toLogin");
            	 response.setHeader("errorCode", "未登录");
            	 response.addHeader("errorMessage", "登录失败");
            	 response.addHeader("returnUrl", "/toLogin");
            }else{
            	request.getSession().setAttribute("returnUrl", request.getRequestURI());
//    			response.sendRedirect(UriConstant.USER.SPACE + "/toLogin");
    			response.sendRedirect("/toLogin");
            }  
            return false;
        }else  request.setAttribute(BackendConstant.SessionKey.USER, user);
        
        return true;
    }
}

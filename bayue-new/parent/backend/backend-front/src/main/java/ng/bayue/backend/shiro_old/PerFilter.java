package ng.bayue.backend.shiro_old;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ng.bayue.backend.domain.SysMenuDO;


/**
 * <pre>
 *
 * </pre>
 *
 * @author yuwenjie
 * @version $Id: PerFilter.java, v 0.1 2015年1月29日 下午6:22:11 yuwenjie Exp $
 */
public class PerFilter extends PathMatchingFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unchecked")
    @Override
    protected boolean onPreHandle(final ServletRequest request, final ServletResponse response, final Object mappedValue) throws Exception {

        HttpServletRequest req = (HttpServletRequest) request;
        // HttpServletResponse resp = (HttpServletResponse) response;

        Map<String, SysMenuDO> map = (Map<String, SysMenuDO>) req.getSession().getAttribute("menuMap");
        String servletPath = req.getServletPath();
        logger.debug("请求:" + servletPath);
        if (null != map && (map.containsKey(servletPath))) {
            logger.debug("校验请求:" + servletPath);
            SecurityUtils.getSubject().checkPermissions(servletPath);
            logger.debug("拥有[" + servletPath + "]权限");
        }

//       if (req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { // 如果是ajax请求响应头会有，x-requested-with
        // resp.setContentType("text/html;charset=utf-8");
        // resp.setHeader("errorCode", ErrorCode.USER_UN_LOGIN.code.toString());
        // resp.addHeader("errorMessage", ErrorCode.USER_UN_LOGIN.value);
        // resp.addHeader("returnUrl", UriConstant.USER.SPACE + "/toLogin");
//        } else {
            // req.getSession().setAttribute("returnUrl", req.getRequestURI());
            // resp.sendRedirect(UriConstant.USER.SPACE + "/toLogin");
//        }
        return true;
    }
}

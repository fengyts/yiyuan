package ng.bayue.backend.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class UrlFilter extends HttpServlet implements Filter  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8532360569620733186L;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;     
        String baseUrl = request.getRequestURI();
        if(!baseUrl.startsWith("/statics")){
        	request.getSession().setAttribute("relUrl", handleRequestUrl(baseUrl));
        }
        chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		super.init();
	}
	
	/**
	 * 处理请求的url
	 * 
	 * @return
	 */
	private String handleRequestUrl(String baseUrl){
		String retUrl = "";
		if(StringUtils.isNotBlank(baseUrl)){
			baseUrl = baseUrl.replaceAll("\\\\", "/");
			baseUrl = baseUrl.replaceAll("//", "/");
			retUrl = baseUrl;
			if(retUrl.indexOf("?")>0){
				retUrl = retUrl.substring(0,retUrl.indexOf("?"));
			}
			if(retUrl.indexOf(";")>0){
				retUrl = retUrl.substring(0,retUrl.indexOf(";"));
			}
		}
		return retUrl;
	}

}

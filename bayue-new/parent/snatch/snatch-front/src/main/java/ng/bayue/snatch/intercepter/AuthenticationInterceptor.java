package ng.bayue.snatch.intercepter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ng.bayue.snatch.auth.AuthError;
import ng.bayue.snatch.auth.Authentication;
import ng.bayue.util.SecurityUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

	private static Integer EXPIRE_HOURS = 3; // 时间戳3小时过期
	private static String DATE_PATTERN = "yyyyMMddHHmmss";

//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
//			Object handler) throws Exception {
//
//		String token = request.getParameter("token");
//		String timestamp = request.getParameter("timestamp");
//		String version = request.getParameter("version");
//
//		String signature = request.getParameter("signature");
//
//		HandlerMethod method = null;
//		if (handler instanceof HandlerMethod) {
//			method = (HandlerMethod) handler;
//		} else {
//			return true;
//		}
//		if (null != method.getMethodAnnotation(Authentication.class)
//				|| null != method.getMethod().getDeclaringClass()
//						.getAnnotation(Authentication.class)) { // 需要权限验证
//			return validateSign(response, token, timestamp, version, signature);
//		}
//
//		return true;
//	}

//	private boolean validateSign(HttpServletResponse response, String token, String timestamp,
//			String version, String signature) {
//		if (StringUtils.isBlank(timestamp) || StringUtils.isBlank(token)
//				|| StringUtils.isBlank(signature) || StringUtils.isBlank(version)) {
//			logger.info("参数异常！");
//			return errorMsg(AuthError.UNPASS_SIGN, response);
//		}
//
//		try {
//			Date date = new SimpleDateFormat(DATE_PATTERN).parse(timestamp);
//			if (validateTimeStamp(date)) {
//				return errorMsg(AuthError.TIME_STAMP_EXPIRE, response);
//			}
//		} catch (Exception e) {
//			return errorMsg(AuthError.TIME_STAMP_PATTERN, response);
//		}
//
//		String encryptionData = token + timestamp + version;
//
//		String signed = SecurityUtil.encryptMD5(encryptionData);
//		if (signed.equalsIgnoreCase(signature)) {
//			return true;
//		}
//
//		return errorMsg(AuthError.UNPASS_SIGN, response);
//	}

	/**
	 * 校验时间戳是否过期
	 * 
	 * @param date
	 * @return true:过期;false:未过期
	 */
	private boolean validateTimeStamp(Date date) {
		if (DateUtils.addHours(date, EXPIRE_HOURS).before(new Date())) {
			return true;
		}
		return false;
	}

//	private boolean errorMsg(AuthError error, HttpServletResponse response) {
//		try {
//			response.setCharacterEncoding(CharsetConstant.UTF8);
//			response.getWriter().print(
//					new ResultMessage(ResultMessage.Failure, error.code.toString(), error.cnName));
//		} catch (IOException e) {
//			logger.error("返回响应结果时IO异常", e);
//		}
//		return false;
//	}

}

package ng.bayue.common;

/**
 * <pre>
 * controller 通用入参校验错误信息类
 * </pre>
 *
 * @author lenovopc
 * @version $Id: ErrorMessage.java, v 0.1 2017年1月11日 下午5:19:26 lenovopc Exp $
 */
public class ErrorMessage {

	public static final boolean DEFAULT_HAS_ERROR = false;

	public boolean hasError = DEFAULT_HAS_ERROR;

	public String message;

	public ErrorMessage() {
	}

	public ErrorMessage(boolean hasError, String message) {
		this.hasError = hasError;
		this.message = message;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorMessage [hasError=" + hasError + ", message=" + message + "]";
	}
	
	

}

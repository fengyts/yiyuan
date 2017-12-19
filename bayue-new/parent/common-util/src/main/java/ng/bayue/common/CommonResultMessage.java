package ng.bayue.common;

import java.io.Serializable;

public class CommonResultMessage implements Serializable {

	private static final long serialVersionUID = -4866444202460607383L;

	/** 成功 */
	public static final int Success = 1;

	/** 失败 */
	public static final int Failure = 0;

	/** 默认操作结果 */
	public static final String Message = CommonMessages.HandleSuccess;

	/** 状态码(成功或失败),错误码 */
	protected int result = Success;

	protected String message = Message;

	/** 附带数据 */
	protected Object data;
	
//	/** 错误码 */
	// 使用result代替
//	protected Integer errorCode;

	public CommonResultMessage() {
	}

	// public ResultMessage(int result) {
	// this.result = result;
	// }

	public CommonResultMessage(Object data) {
		this.data = data;
	}

	public CommonResultMessage(int result, String message) {
		// this.result = result;
		// this.message = message;
		this(result, message, null);
	}

	public CommonResultMessage(String message, Object data) {
		this.message = message;
		this.data = data;
	}

	public CommonResultMessage(int result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}
	
//	public CommonResultMessage(int result, Integer errorCode, String message, Object data) {
//		this.result = result;
//		this.errorCode = errorCode;
//		this.message = message;
//		this.data = data;
//	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * <pre>
	 * 校验入参为空时返回值
	 * </pre>
	 *
	 * @return
	 */
	public static CommonResultMessage validParamResult() {
		return new CommonResultMessage(Failure, CommonMessages.ParameterNull);
	}

	/**
	 * <pre>
	 * 校验数据已经存在时返回值
	 * </pre>
	 *
	 * @return
	 */
	public static CommonResultMessage validIsExist() {
		return new CommonResultMessage(Failure, CommonMessages.IsExist);
	}

	/**
	 * <pre>
	 * 校验数据正在使用
	 * </pre>
	 *
	 * @return
	 */
	public static CommonResultMessage validIsUsed() {
		return new CommonResultMessage(Failure, CommonMessages.IsUsed);
	}

	/**
	 * <pre>
	 * 校验必填参数项为空时返回值
	 * </pre>
	 *
	 * @param parameters
	 * @return
	 */
	public static CommonResultMessage validParameterNull(String... parameters) {
		if (null == parameters) {
			return new CommonResultMessage(Failure, CommonMessages.ParameterNull);
		}
		return new CommonResultMessage(Failure, CommonMessages.parameterErrMsgs(parameters));
	}

	/**
	 * <pre>
	 * 服务器内部异常
	 * </pre>
	 *
	 * @return
	 */
	public static CommonResultMessage serverInnerError() {
		return new CommonResultMessage(Failure, CommonMessages.ServerInnerError);
	}

	public static CommonResultMessage success() {
		return new CommonResultMessage(Success, CommonMessages.HandleSuccess);
	}
	
	public static CommonResultMessage failure(){
		return new CommonResultMessage(Failure, CommonMessages.HandleFailure);
	}
	
	public static CommonResultMessage failure(String message){
		return new CommonResultMessage(Failure, message);
	}
	
}

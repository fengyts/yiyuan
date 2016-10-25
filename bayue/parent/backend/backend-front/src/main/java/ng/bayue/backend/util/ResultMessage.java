package ng.bayue.backend.util;

import java.io.Serializable;

public class ResultMessage implements Serializable{

	private static final long serialVersionUID = -4866444202460607383L;

	/** 成功 */
	public static final int Success = 1;

	/** 失败 */
	public static final int Failure = 0;
	
	/** 默认操作结果  */
	public static final String Message = Messages.HandleSuccess;

	private int result = Success;

	private String message = Message;

	private Object data;
	
	public ResultMessage(){}
	
//	public ResultMessage(int result) {
//		this.result = result;
//	}
	
	public ResultMessage(Object data) {
		this.data = data;
	}

	public ResultMessage(int result, String message) {
//		this.result = result;
//		this.message = message;
		this(result,message,null);
	}
	
	public ResultMessage(String message, Object data){
		this.message = message;
		this.data = data;
	}

	public ResultMessage(int result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}

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
	public static ResultMessage validParamResult(){
		return new ResultMessage(Failure,Messages.ParameterNull);
	}
	
	/**
	 * <pre>
	 * 校验入参存在时返回值
	 * </pre>
	 *
	 * @return
	 */
	public static ResultMessage validIsExist(){
		return new ResultMessage(Failure,Messages.IsExist);
	}
	
	/**
	 * <pre>
	 * 校验必填参数项为空时返回值
	 * </pre>
	 *
	 * @param parameters
	 * @return
	 */
	public static ResultMessage validParameterNull(String... parameters){
		return new ResultMessage(Failure,Messages.parameterErrMsgs(parameters));
	}

}

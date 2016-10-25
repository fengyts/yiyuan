package ng.bayue.util;

import java.io.Serializable;

@SuppressWarnings({ "unused" })
public class ResultMessage implements Serializable {

	private static final long serialVersionUID = -6241835606147031976L;

	/** 成功 */
	public static final int Success = 1;
	/** 失败 */
	public static final int Failure = 0;
	/** 成功时通用提示消息  */
	public static final String operSuccess = "操作成功！";
	/** 失败时通用提示消息  */
	public static final String operFailure = "操作失败！";

	private int result = Success;
	private String message = operSuccess;
	private Object data;

	public ResultMessage() {
	}

	public ResultMessage(int result, String message) {
		this.result = result;
		this.message = message;
	}

	public ResultMessage(Object data) {
		this.data = data;
	}

	public ResultMessage(String message, Object data) {
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

}

package ng.bayue.backend.util;

import java.io.Serializable;
import java.util.Arrays;

public final class Messages implements Serializable {

	/**  */
	private static final long serialVersionUID = -8253156726606395186L;
	
	public static final String ServerInnerError = "服务器内部错误";

	public static final String HandleSuccess = "操作成功";

	public static final String ParameterError = "参数错误";
	
	public static final String ParameterNull = "参数为空";

	public static final String HandleFailure = "操作失败";
	
	public static final String IsExist = "记录已经存在";
	
	/** 冒号 */
	private static final String Colon = ":";

	public static String parameterErrMsgs(String... parameters){
		return ParameterNull + Colon + Arrays.toString(parameters);
	}
	

}

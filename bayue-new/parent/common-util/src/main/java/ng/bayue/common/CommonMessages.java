package ng.bayue.common;

import java.io.Serializable;
import java.util.Arrays;

public class CommonMessages implements Serializable{

	private static final long serialVersionUID = 6578934110905066296L;
	
	public static final String ServerInnerError = "操作失败,服务器内部错误";

	public static final String HandleSuccess = "操作成功";

	public static final String ParameterError = "参数错误";
	
	public static final String ParameterNull = "输入的数据为空";

	public static final String HandleFailure = "操作失败";
	
	public static final String IsExist = "数据已经存在";
	
	public static final String IsUsed = "正在使用中";
	
	public static final String UNLOGIN = "尚未登陆";
	
	public static final String ReqException = "请求异常";
	
	/** 冒号 */
	private static final String Colon = ":";

	public static String parameterErrMsgs(String... parameters){
		return ParameterNull + Colon + Arrays.toString(parameters);
	}
	

}

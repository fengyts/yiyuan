package ng.bayue.snatch.auth;

public enum AuthError {
	VERIFY_EXCEPTION(1001, "权限验证处理异常"), 
	UNPASS_AUTH(1002, "权限验证不通过"), 
	UNPASS_SIGN(1003, "SIGN签名验证不通过"), 
	ACCESS_ILLEGAL_DATA(1004, "越权访问数据"), 
	TIME_STAMP_EXPIRE(1005, "时间戳过期"), 
	TIME_STAMP_PATTERN(1006, "时间戳格式错误");

	public Integer code;
	public String cnName;

	AuthError(Integer code, String cnName) {
		this.code = code;
		this.cnName = cnName;
	}

	public static String getCnName(Integer code) {
		if (code != null) {
			for (AuthError entry : AuthError.values()) {
				if (entry.code.intValue() == code) {
					return entry.cnName;
				}
			}
		}
		return null;
	}

}

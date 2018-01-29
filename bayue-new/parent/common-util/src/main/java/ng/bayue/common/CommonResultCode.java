package ng.bayue.common;


public class CommonResultCode {

	 public enum SystemError {
		 	REQ_ERROR(-1, "请求异常"),
	        SYSTEM_ERROR(0, "系统内部错误"), 
	        PARAM_ERROR(1, "入参错误"),
	        MD5_ENCRYPT_ERROR(2,"MD5加密错误"),
	        SYSTEM_PARAM_NOT_NULL(100,"系统级别参数不可为空'"),
	        PROCESS_SIZE_UNMATCH_LIMIT(101,"处理列表数量不符合限制"),
	        ACCESS_OVERLOAD(102,"访问频率超过限制"),
		 	UN_LOGIN(999, "尚未登录");
	        
	        public Integer code;
	        public String desc;

	        SystemError(Integer code, String desc) {
	            this.code = code;
	            this.desc = desc;
	        }

	        public static String getDesc(Integer code) {
	            if (code != null) {
	                for (SystemError entry : SystemError.values()) {
	                    if (entry.code.intValue() == code) {
	                        return entry.desc;
	                    }
	                }
	            }
	            return null;
	        }
	    }

	    public enum AuthError {
	        VERIFY_EXCEPTION(1001, "权限验证处理异常"), 
	        UNPASS_AUTH(1002, "权限验证不通过"),
	        UNPASS_SIGN(1003,"SIGN签名验证不通过"),
	        ACCESS_ILLEGAL_DATA(1004,"越权访问数据"),
	        TIME_STAMP_EXPIRE(1005,"时间戳过期"),
	        TIME_STAMP_PATTERN(1006,"时间戳格式错误");

	        public Integer code;
	        public String desc;

	        AuthError(Integer code, String desc) {
	            this.code = code;
	            this.desc = desc;
	        }

	        public static String getDesc(Integer code) {
	            if (code != null) {
	                for (AuthError entry : AuthError.values()) {
	                    if (entry.code.intValue() == code) {
	                        return entry.desc;
	                    }
	                }
	            }
	            return null;
	        }
	    }
	    
	    public enum BusinessError {
	    	BUSINESS_PROCESS_ERROR(3,"业务处理失败"),
	    	PROMOTION_HAS_END(2001, "活动已经结束"),
	    	USEABLE_CURRENCY_NOT_ENOUGH(2002, "西币不足"),
	    	ONCE_EVERY_TIME(2003, "请求太频繁了"),
	    	EXCHANGE_COUNT_NOT_ENOUGH(2004, "库存不足"),
	    	HAS_EXCHANGED(2005, "已经兑换过了"),
	    	BUSINESS_IS_BUSY(2006, "业务繁忙");
	    	
	    	public Integer code;
	        public String desc;

	        BusinessError(Integer code, String desc) {
	            this.code = code;
	            this.desc = desc;
	        }

	        public static String getDesc(Integer code) {
	            if (code != null) {
	                for (BusinessError entry : BusinessError.values()) {
	                    if (entry.code.intValue() == code) {
	                        return entry.desc;
	                    }
	                }
	            }
	            return null;
	        }
	    }

}

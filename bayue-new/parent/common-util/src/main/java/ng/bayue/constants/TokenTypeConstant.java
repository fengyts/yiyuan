package ng.bayue.constants;

/**
 * <pre>
 * token key 类型前缀
 * </pre>
 *
 * @author fengyts
 * @version $Id: TokenTypeConstant.java, v 0.1 2017年12月2日 下午9:11:12 fengyts Exp
 *          $
 */
public interface TokenTypeConstant {
	/** 找回密码全局token key前缀 */
	public static final String FG_PWD_TK_GOLBAL = "FG_PWD_TK";
	/** 找回密码步骤token key前缀 */
	public static final String FG_PWD_TK_STEP = "FG_PWD_TK_STEP";

	/**
	 * <pre>
	 * 业务token类型枚举类
	 * </pre>
	 *
	 * @author fengyts
	 * @version $Id: TokenTypeConstant.java, v 0.1 2017年12月16日 下午6:57:22 fengyts
	 *          Exp $
	 */
	public static enum BusinessTokenTypeEnum {
		/** 竞拍 */
		AUCTION_AUCTION("01", "AUCTION_AUCTION"),
		/** 兑换 */
		AUCTION_EXCHANGE("02", "AUCTION_EXCHANGE");

		private String code;
		private String codeType;

		private BusinessTokenTypeEnum(String code, String codeType) {
			this.code = code;
			this.codeType = codeType;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getCodeType() {
			return codeType;
		}

		public void setCodeType(String codeType) {
			this.codeType = codeType;
		}

	}
}

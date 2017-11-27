package ng.bayue.constants;

public interface CommonConstant {

	/**
	 * <pre>
	 * 状态是否有效
	 * </pre>
	 *
	 */
	public interface STATUS {
		/** 有效 */
		public int VALID = 1;
		/** 有效 */
		public boolean TRUE = Boolean.TRUE;
		/** 无效 */
		public int INVALID = 0;
		/** 无效 */
		public boolean FALSE = Boolean.FALSE;
	}
	
	/**
	 * <pre>
	 * 是否成功
	 * </pre>
	 *
	 * @author fengyts
	 * @version $Id: CommonConstant.java, v 0.1 2016年7月23日 上午10:23:03 fengyts Exp $
	 */
	public interface IsSuccess{
		/** 成功 */
		int SUCCESS = 1;
		boolean SUCCESS_BOOL = Boolean.TRUE;
		/** 失败 */
		int FAILURE = 0;
		boolean FAILURE_BOOL = Boolean.FALSE;
	}
	
	/**
	 * <pre>
	 * 操作的结果状态码
	 * </pre>
	 *
	 * @author fengyts
	 * @version $Id: CommonConstant.java, v 0.1 2016年7月23日 上午10:23:37 fengyts Exp $
	 */
	public interface ResultStatusCode{
		int FAILURE = -1;
		boolean FAILURE_BOOL = Boolean.FALSE;
	}

}

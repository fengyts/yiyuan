package ng.bayue.base.constant;

public final class CategoryConstant {
	
	
	/**
	 * 品类级别
	 * 
	 */
	public interface LEVEL{
		/**
		 * <pre>
		 * 目前只有2级,SMALL暂未使用,为预留级别
		 * </pre>
		 */
		int SMALL = 3;
		/**
		 * <pre>
		 * 目前只到此级别
		 * </pre>
		 */
		int MIDDLE = 2;
		int LARGE = 1;
	}
	
	/**
	 * 品类状态
	 * 
	 */
	public interface STATUS{
		/** 有效 */
		int VALID = 1;
		boolean TRUE = Boolean.TRUE;
		/** 无效 */
		int INVALID = 0;
		boolean FALSE = Boolean.FALSE;
	}
	
}

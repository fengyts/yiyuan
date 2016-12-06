package ng.bayue.item.constant;

;

/**
 * spu,prdid编码生成 常量
 * 
 * @author haisheng.Long 2016-12-06 09:07:19
 */
public interface CodeConstant {

	/** 生成spu，prdid，sku编码 初始化的值1 */
	public static final int CODE_INIT_VALUE = 1;
	
	public static final int ZERO = 0;
	
	public static final String ZERO_STR = "0";

	/**
	 * <pre>
	 * 生成编码类型
	 * </pre>
	 *
	 * @author lenovopc
	 * @version $Id: CodeConstant.java, v 0.1 2016年12月6日 上午9:22:31 lenovopc Exp
	 *          $
	 */
	public interface CodeType {
		/** spu */
		public static final int SPU_CODE = 1;
		/** prdid */
		public static final int PRDID_CODE = 2;
	}

	/**
	 * <pre>
	 * 生成编码流水码设置
	 * </pre>
	 *
	 * @author lenovopc
	 * @version $Id: CodeConstant.java, v 0.1 2016年12月6日 上午9:54:55 lenovopc Exp
	 *          $
	 */
	public interface RunningWaterCode {
		/** spu为小类code加上4位流水码 */
		public static final int SPU_CODE = 4;
		/** prdid为spu加上2位流水码 */
		public static final int PRDID_CODE = 2;
	}

}

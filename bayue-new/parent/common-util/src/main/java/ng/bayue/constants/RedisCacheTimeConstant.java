package ng.bayue.constants;

/**
 * <pre>
 * 缓存时间常量,单位：秒
 * </pre>
 *
 * @author fengyts
 * @version $Id: RedisCacheTimeConstant.java, v 0.1 2016年5月29日 下午8:52:27 fengyts
 *          Exp $
 */
public final class RedisCacheTimeConstant {
	
	/** redis超时,60秒 */
	public static final int TIME_OUT = 60;
	
	/** 1分钟 */
	public static final int ONE_MINUTES = 60;
	
	/** 默认时间5分钟  */
	public static final int DEFAULT_TIME = 60 * 5;

	/** 10分钟 */
	public static final int TEN_MINUTES = 60 * 10;
	/** 半小时 */
	public static final int HALF_HOUR = 60 * 30;
	/** 一小时 */
	public static final int HOUR = 60 * 60;
	/** 一天 */
	public static final int DAY = 60 * 60 * 24;
	/** 一周 */
	public static final int WEEK = 60 * 60 * 24 * 7;
	/** 半个月 */
	public static final int HALF_MONTH = 60 * 60 * 15;
	/** 一个月 */
	public static final int MONTH = 60 * 60 * 30;

}

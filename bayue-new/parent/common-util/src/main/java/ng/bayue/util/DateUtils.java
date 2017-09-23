package ng.bayue.util;


public class DateUtils {
	
	/** 常用日期格式  */
	private static final String[] parsePatterns = {
			"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "MM-dd",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "MM/dd",
			"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM", "MM.dd"
		};
	
	/** 默认日期格式: yyyy-MM-dd HH:mm:ss */
	public static final String DEFAULT_FORMAT = Format.YYYYMMDDHHMMSS;
	
	/**
	 * <pre>
	 * 常用日期格式
	 * </pre>
	 *
	 * @author lenovopc
	 * @version $Id: DateUtils.java, v 0.1 2017年2月24日 下午4:11:33 lenovopc Exp $
	 */
	public static final class Format {
		/** yyyy-MM-dd HH:mm:ss */
		static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
		/** yyyy/MM/dd HH:mm:ss */
		static final String YYYYMMDDHHMMSS1 = "yyyy/MM/dd HH:mm:ss";
		/** yyyy.MM.dd HH:mm:ss */
		static final String YYYYMMDDHHMMSS2 = "yyyy.MM.dd HH:mm:ss";
		
		/** yyyy-MM-dd HH:mm */
		static final String YYYYMMDHHMM = "yyyy-MM-dd HH:mm";
		/** yyyy/MM/dd HH:mm */
		static final String YYYYMMDHHMM1 = "yyyy/MM/dd HH:mm";
		/** yyyy.MM.dd HH:mm */
		static final String YYYYMMDHHMM2 = "yyyy.MM.dd HH:mm";
		
		/** yyyy-MM-dd */
		static final String YYYYMMDD = "yyyy-MM-dd";
		/** yyyy/MM/dd */
		static final String YYYYMMDD1 = "yyyy/MM/dd";
		/** yyyy.MM.dd */
		static final String YYYYMMDD2 = "yyyy.MM.dd";
		
		/** yyyy-MM */
		static final String YYYYMM = "yyyy-MM";
		/** yyyy/MM */
		static final String YYYYMM1 = "yyyy/MM";
		/** yyyy.MM */
		static final String YYYYMM2 = "yyyy.MM";
		
		/** MM-dd */
		static final String MMDD = "MM-dd";
		/** MM/dd */
		static final String MMDD1 = "MM/dd";
		/** MM.dd */
		static final String MMDD2 = "MM.dd";
		
	}
	
}

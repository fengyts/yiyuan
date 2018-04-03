package ng.bayue.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;


public class DateUtils extends org.apache.commons.lang3.time.DateUtils{
	
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
		public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
		/** yyyy/MM/dd HH:mm:ss */
		public static final String YYYYMMDDHHMMSS1 = "yyyy/MM/dd HH:mm:ss";
		/** yyyy.MM.dd HH:mm:ss */
		public static final String YYYYMMDDHHMMSS2 = "yyyy.MM.dd HH:mm:ss";
		
		/** yyyy-MM-dd HH:mm */
		public static final String YYYYMMDHHMM = "yyyy-MM-dd HH:mm";
		/** yyyy/MM/dd HH:mm */
		public static final String YYYYMMDHHMM1 = "yyyy/MM/dd HH:mm";
		/** yyyy.MM.dd HH:mm */
		public static final String YYYYMMDHHMM2 = "yyyy.MM.dd HH:mm";
		
		/** yyyy-MM-dd */
		public static final String YYYYMMDD = "yyyy-MM-dd";
		/** yyyy/MM/dd */
		public static final String YYYYMMDD1 = "yyyy/MM/dd";
		/** yyyy.MM.dd */
		public static final String YYYYMMDD2 = "yyyy.MM.dd";
		/** yy-MM-dd */
		public static final String YYMMDD = "yy-MM-dd";
		
		/** yyyy-MM */
		public static final String YYYYMM = "yyyy-MM";
		/** yyyy/MM */
		public static final String YYYYMM1 = "yyyy/MM";
		/** yyyy.MM */
		public static final String YYYYMM2 = "yyyy.MM";
		
		/** MM-dd */
		public static final String MMDD = "MM-dd";
		/** MM/dd */
		public static final String MMDD1 = "MM/dd";
		/** MM.dd */
		public static final String MMDD2 = "MM.dd";
		
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		if (date == null) {
			return StringUtils.EMPTY;
		}
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, Format.YYYYMMDD);
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, DEFAULT_FORMAT);
	}
	
	/**
     * 获取指定时间所在天的开始时间，0时0分0秒0毫秒
     * @param cal
     * @return
     */
	public static Date getDayBegin(Date date) {
		if (null == date) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getDayBegin(cal);
	}
	
	/**
     * 获取指定时间所在天的开始时间，0时0分0秒0毫秒
     * @param cal
     * @return
     */
	public static Date getDayBegin(Calendar cal) {
		if (null == cal) {
			return null;
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}
	
	/**
     * 获取指定时间所在天的结束时间，23时59分59秒999毫秒
     * @param cal
     * @return
     */
	public static Date getDayEnd(Date date) {
		if (null == date) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getDayEnd(cal);
	}
	
	/**
     * 获取指定时间所在天的结束时间，23时59分59秒999毫秒
     * @param cal
     * @return
     */
	public static Date getDayEnd(Calendar cal) {
		if (null == cal) {
			return null;
		}
		cal.add(Calendar.DATE, 1);
		cal.add(Calendar.MILLISECOND, -1);

		return cal.getTime();
	}
	
	
	
	
}

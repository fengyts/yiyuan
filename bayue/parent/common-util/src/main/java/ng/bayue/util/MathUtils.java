package ng.bayue.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 数学运算
 *
 * @author fengyts
 * @version 2015年1月19日 下午1:56:15
 */
public final class MathUtils {

	/** 保留2位小数 */
	public static final int SCALE_2 = 2;
    /** 保留3位小数 */
    public static final int SCALE_3 = 3;
    
    /** 格式化输出 :2位小数 */
    public static final String FORMAT_SCALE = "0.00";
    
    /** 千分位格式 */
    public static final String FORMAT_THOUSANDS = "###,###.00";
    
    /** 百分比形式表示,例：0.67表示为67% */
    public static final String FORMAT_PERCENT = "#%";
    
    /**  百分比形式表示,保留2位小数,例：0.67表示为67.00%  */
    public static final String FORMAT_PERCENT_SCALE = "0.00%"; 
    		

    private MathUtils() {
    }

    /**
     * 加法
     *
     * @param num1
     * @param num2
     * @return
     */
    public static <M extends Number, N extends Number> BigDecimal add(final M num1, final N num2) {
        return toBigDecimal(num1).add(toBigDecimal(num2));
    }
    
    /**
     * 加法
     *
     * @param num1
     * @return
     */
    @SafeVarargs
	public static <M extends Number, N extends Number> BigDecimal addMore(final M ... nums) {
    	BigDecimal sum = BigDecimal.ZERO;
    	for (M item : nums) {  
    		sum = sum.add(toBigDecimal(item));
    	}  
        return sum;
    }

    /**
     * 减法
     *
     * @param num1
     * @param num2
     * @return
     */
    public static <M extends Number, N extends Number> BigDecimal subtract(final M num1, final N num2) {
        return toBigDecimal(num1).subtract(toBigDecimal(num2));
    }

    /**
     * 乘法
     *
     * @param num1
     * @param num2
     * @return
     */
    public static <M extends Number, N extends Number> BigDecimal multiply(final M num1, final N num2) {
        return toBigDecimal(num1).multiply(toBigDecimal(num2));
    }

    /**
     * 除法
     *
     * @param num1
     * @param num2
     * @return
     */
    public static <M extends Number, N extends Number> BigDecimal divide(final M num1, final N num2) {
        return toBigDecimal(num1).divide(toBigDecimal(num2), SCALE_3, RoundingMode.HALF_UP);
    }
    
    /**
     *  除法
     *
     * @param num1
     * @param num2
     * @param hasScale : 是否保留小数,默认保留2位小数,四舍五入方式
     * @param scale 保留小数位数,默认2
     * @return
     */
    public static <M extends Number, N extends Number> BigDecimal divide(final M num1, final N num2, final boolean hasScale, int scale) {
    	if(hasScale){
    		return toBigDecimal(num1).divide(toBigDecimal(num2), scale, RoundingMode.HALF_UP);
    	}
        return toBigDecimal(num1).divide(toBigDecimal(num2), SCALE_2, RoundingMode.HALF_UP);
    }

    /**
     * 格式化成价格格式（保留2位小数，四舍五入），例：1.99, 2.00
     *
     * @return
     */
    public static BigDecimal formatToPrice(final BigDecimal num) {
        return null == num ? BigDecimal.ZERO : num.setScale(2, RoundingMode.HALF_UP);
    }

    private static <N extends Number> BigDecimal toBigDecimal(final N num) {
        return null == num ? BigDecimal.ZERO : new BigDecimal(num.toString());
    }
    
    /**
     * 格式化数字
     *
     * @param num
     * @param format 输出格式
     * @return
     */
    public static String formatNum(final double num, final String format){
    	java.text.DecimalFormat df = new DecimalFormat(format);
    	return df.format(num);
    }
    
    
}

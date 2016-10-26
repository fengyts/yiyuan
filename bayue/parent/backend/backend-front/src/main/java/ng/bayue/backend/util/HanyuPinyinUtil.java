package ng.bayue.backend.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class HanyuPinyinUtil {

	private static final Logger logger = LoggerFactory.getLogger(HanyuPinyinUtil.class);

	public static String hanyuToPinyin(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		String result = "";
		for (char ch : str.toCharArray()) {
			result += toPinyin(ch);
		}
		return result;
	}

	private static String toPinyin(Character ch) {
		if (null == ch || ' ' == (char) ch) {
			return null;
		}

		HanyuPinyinOutputFormat hpf = new HanyuPinyinOutputFormat();
		hpf.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		hpf.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		hpf.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);

		String[] result = null;
		try {
			// 如果不是汉字则返回null
			result = PinyinHelper.toHanyuPinyinStringArray(ch, hpf);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			logger.info("汉字转拼音异常");
		}
		return null == result || result.length < 1 ? "" : result[0];
	}

	public static boolean isChineseChar(Character ch) {
		if (null == ch || ' ' == (char) ch) {
			return false;
		}
		String[] result = PinyinHelper.toHanyuPinyinStringArray(ch);
		return null == result || result.length < 1 ? false : true;
	}
	
	@Deprecated
	public static boolean isChineseChar(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		return m.find() ? true : false;
	}

	/**
	 * 文本截取，按照给定的长度截取字符，一个中文占两个英文字符宽度
	 * 
	 * @param input
	 *            输入文本
	 * @param maxLen
	 *            英文字符宽度，最大值
	 * @return
	 */
	public static String subString(String input, Integer maxLen) {
		if (StringUtils.isNotBlank(input)) {
			int len = 0;
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < input.length(); i++) {
				char c = input.charAt(i);
				if (isLetter(c)) {
					len = len + 1;
				} else {
					len = len + 2;
				}
				if (len > maxLen) {
					break;
				}
				sb.append(c);
			}
			if (len <= maxLen) {
				return input;
			}
			return sb.toString() + "...";
		} else {
			return input;
		}
	}

	/**
	 * 判断是不是字母数字或单字节字符
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	public static void main(String[] args) {
		String str = HanyuPinyinUtil.hanyuToPinyin("等等d");
		System.out.println(str);
		// System.out.println(isHanzi('地'));
	}

}

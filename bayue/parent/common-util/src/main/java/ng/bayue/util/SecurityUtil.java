package ng.bayue.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityUtil {

	public static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

	private static final String MD5 = "MD5";

	private static final int ZERO = 0;

	/**
	 * <pre>
	 * MD5加密,默认32位
	 * </pre>
	 *
	 * @param str
	 * @return
	 */
	public static String encryptMD5(String source) {
		if (null == source || "".equals(source)) {
			return null;
		}
		try {
			MessageDigest md = MessageDigest.getInstance(MD5);
			byte[] data = source.getBytes();
			md.update(data);
			byte[] b = md.digest();
			int len = b.length;
			int r;
			StringBuffer res = new StringBuffer();
			for (int i = 0; i < len; i++) {
				r = b[i];
				if (r < 0) {
					r += 256;
				}
				if (r < 16) {
					res.append(ZERO);
				}
				res.append(Integer.toHexString(r));
			}
			return res.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error("MD5加密异常:{}", e);
		}
		return null;
	}

	/**
	 * <pre>
	 * 16位MD5加密
	 * </pre>
	 *
	 * @param src
	 * @return
	 */
	public static String encrypt16MD5(String src) {
		return encryptMD5(src).substring(8, 24);
	}

	public static void main(String[] args) {
		String str = SecurityUtil.encryptMD5("abcde");// ab56b4d92b40713acc5af89985d4b786
		System.out.println(str);
	}

}

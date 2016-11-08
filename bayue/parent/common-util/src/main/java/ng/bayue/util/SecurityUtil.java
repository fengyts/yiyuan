package ng.bayue.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityUtil {

	public static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

	private static final String MD5 = "MD5";

	private static final String SHA = "SHA";

	private static final String SHA1 = "SHA1";

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
		byte[] data = source.getBytes();
		return encryptMD5(data);
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

	public static String encryptMD5(byte[] bytes) {
		if (null == bytes) {
			return null;
		}
		try {
			MessageDigest md = MessageDigest.getInstance(MD5);
			md.update(bytes);
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
	 * 随机盐生成类
	 * </pre>
	 *
	 * @author lenovopc
	 * @version $Id: SecurityUtil.java, v 0.1 2016年11月8日 下午1:32:17 lenovopc Exp $
	 */
	public static class Salt {
		
		/**
		 * 16位
		 */
		private static final int LENGTH16 = 16;
		
		/**
		 * 32位
		 */
		private static final int LENGTH32 = 32;

		public static byte[] provideSalt() {
			byte[] bytes = new byte[LENGTH32];
			Random rd = new SecureRandom();
			rd.nextBytes(bytes);
			return bytes;
		}
	}

	public static void main(String[] args) {
		String str = SecurityUtil.encryptMD5("admin");// 900150983cd24fb0d6963f7d28e17f72
		System.out.println(str);
		String salt = new String(SecurityUtil.Salt.provideSalt());
		System.out.println(encryptMD5(salt));
	}

}

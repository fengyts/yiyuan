package ng.bayue.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 安全类，包含MD5加密、SHA1、hash多次散列等
 * </pre>
 *
 * @author lenovopc
 * @version $Id: SecurityUtil.java, v 0.1 2016年11月9日 上午11:19:53 lenovopc Exp $
 */
public final class SecurityUtil {

	public static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

	private static final String MD5 = "MD5";

	private static final String SHA1 = "SHA1";

	/**
	 * 散列次数,默认散列1次
	 */
	private static final int DEFAULT_HASH_ITERATIONS = 1;

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

	/**
	 * <pre>
	 * 无迭代单次加密
	 * </pre>
	 *
	 * @param bytes
	 * @return
	 */
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
	 * 使用指定的 source 数组对摘要进行最后更新，然后完成摘要计算。
	 * </pre>
	 *
	 * @param source
	 * @return 存放哈希值结果的 byte 数组。
	 */
	public static byte[] encrypt(byte[] source) {
		try {
			MessageDigest md = MessageDigest.getInstance(MD5);
			md.reset();
			return md.digest(source);
		} catch (NoSuchAlgorithmException e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * <pre>
	 * 加密算法
	 * </pre>
	 *
	 * @param source
	 *            需要散列的明文
	 * @param salt
	 *            散列时加入的盐值
	 * @param hashIterations
	 *            哈希算法散列次数
	 * @return
	 */
	public static byte[] hash(byte[] source, byte[] salt, int hashIterations) {
		if (null == source || null == salt) {
			return null;
		}
		hashIterations = hashIterations < 1 ? DEFAULT_HASH_ITERATIONS : hashIterations;
		try {
			MessageDigest digest = MessageDigest.getInstance(MD5);
			if (salt != null) {
				digest.reset();
				digest.update(salt);
			}
			byte[] hashed = digest.digest(source);
			int iterations = hashIterations - 1; // already hashed once above
			// iterate remaining number:
			for (int i = 0; i < iterations; i++) {
				digest.reset();
				hashed = digest.digest(hashed);
			}
			return hashed;
		} catch (NoSuchAlgorithmException e) {
			logger.error("", e);
		}
		return null;
	}

	public static byte[] hash(String source, String salt, int hashIterations) {
		if (StringUtils.isEmpty(source) || StringUtils.isEmpty(salt)) {
			return null;
		}
		return hash(source.getBytes(), salt.getBytes(), hashIterations);
	}

	public static String hashToStr(String source, String salt, int hashIterations) {
		byte[] bytes = hash(source, salt, hashIterations);
		return encode(bytes);
	}

	/**
	 * <pre>
	 * 输出为16进制
	 * </pre>
	 */
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
			'b', 'c', 'd', 'e', 'f' };
	
	private static final char[] CHARSET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	/**
	 * <pre>
	 * 编码指定字节数组返回字符串,编码长度增加data长度的一倍,即16位会返回32位长度的,32位会返回64位长度的
	 * </pre>
	 *
	 * @param data
	 * @return
	 */
	public static String encode(byte[] data) {
		int l = data.length;
		char[] out = new char[l << 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}
		return new String(out);
	}

	/**
	 * <pre>
	 * 随机盐生成类
	 * </pre>
	 *
	 * @author lenovopc
	 * @version $Id: SecurityUtil.java, v 0.1 2016年11月8日 下午1:32:17 lenovopc Exp
	 *          $
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

		/**
		 * <pre>
		 * 生成随机盐16位
		 * </pre>
		 *
		 * @return
		 */
		public static byte[] provideSalt() {
			return provider(LENGTH16);
		}
		
		/**
		 * <pre>
		 * 生成随机盐32位
		 * </pre>
		 *
		 * @return
		 */
		public static byte[] provideSalt32 (){
			return provider(LENGTH32);
		}

		private static byte[] provider(int len) {
			byte[] bytes = new byte[len];
			Random rd = new SecureRandom();
			rd.nextBytes(bytes);
			return bytes;
		}
		
	}

	public static void main(String[] args) {
		// 原始 密码
		String source = "admin";
		// 盐
		String salt = "15bc08f47ebb0a077c2378bd88781f2a";
		byte[] bytes = source.getBytes();
		byte[] saltb = salt.getBytes();
		byte[] hash = hash(bytes, saltb, 1);
		String s = new String(encode(hash));
//		System.out.println("hash2:\n" + s);
		
		String pwd = "abc123A";
//		String st = encode(SecurityUtil.Salt.provideSalt());
		String st = "ee2373745ab6b214a5941b9cb281025f";
		String enpwd = hashToStr(pwd, st, 2);
		System.out.println(st);
		System.out.println(enpwd);
	}

}

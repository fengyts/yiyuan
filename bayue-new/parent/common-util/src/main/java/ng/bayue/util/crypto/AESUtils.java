package ng.bayue.util.crypto;

import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * <pre>
 * AES加密工具类
 * </pre>
 *
 * @author lenovopc
 * @version $Id: AESUtils.java, v 0.1 2017年11月29日 下午3:06:12 lenovopc Exp $
 */
public class AESUtils {

	public static final String CHART_SET_UTF8 = "UTF-8";
	public static final String CHART_SET = "GB18030";
	
	public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	public static final String SECRET_KEY= "f5e_E&D8$hAX5pG%";
	public static final String IV = "\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0";
	
	private String randomStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; // 随机产生的字符串
	private Random random = new Random();

	private Cipher cipher;

	private SecretKey secretKey;
	private IvParameterSpec ivspec;

	public AESUtils() {
		try {
			cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES"); 
			ivspec = new IvParameterSpec(IV.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public void initKey(String password) {

		secretKey = new SecretKeySpec(password.getBytes(), "AES");
	}

	public void initCipher(String transformation) {
		try {
			cipher = Cipher.getInstance(transformation);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public String encrypt(String plaintext) {
		// 输出buffer
		ByteArrayOutputStream outbuf = new ByteArrayOutputStream();
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			byte[] data = plaintext.getBytes(CHART_SET_UTF8);
			byte[] encryptedBlock = cipher.doFinal(data);
			// 追加结果到输出buffer中
			outbuf.write(encryptedBlock);
			outbuf.flush();
			outbuf.close();
		} catch (Exception e) {
			return null;
		}

		return Base64.encodeBase64String(outbuf.toByteArray()); // ciphertext
	}

	public String decrypt(String cryptedBase64Str) {
		// 输出buffer
		ByteArrayOutputStream outbuf = new ByteArrayOutputStream();
		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			// 转换得到字节流
			byte[] data = Base64.decodeBase64(cryptedBase64Str);
			byte[] decryptedBlock = cipher.doFinal(data);
			// 追加结果到输出buffer中
			outbuf.write(decryptedBlock);
			outbuf.flush();
			outbuf.close();
			return outbuf.toString(CHART_SET_UTF8);//对解密后的数据指定输出编码格式GBK
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getRandomKey(int len) {
		StringBuilder key = new StringBuilder();
		for (int i = 1; i <= len; i++) {
			key.append(String.valueOf(randomStr.charAt(random.nextInt(randomStr.length()))));
		}
		return key.toString();
	}
	
	/**
	 * 微信小程序用户数据解密
	 * @param encryptedData
	 * @param key
	 * @param iv
	 * @return
	 */
	public String decrypt(String encryptedData, String key, String iv) {
		Key secretKey = new SecretKeySpec(Base64.decodeBase64(key), "AES");
		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey, generateIV(Base64.decodeBase64(iv)));
			byte[] result = cipher.doFinal(Base64.decodeBase64(encryptedData));
			return new String(result, CHART_SET_UTF8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public AlgorithmParameters generateIV(byte[] iv) throws Exception{
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }
	
	public static void main(String[] args) {
		AESUtils aes = new AESUtils();
		String str = "123abc";
		String enc = aes.encrypt(str);
		System.out.println(enc);
	}

}

package ng.bayue.util.crypto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESUtils {

	/**
	 * AES加密帮助类
	 * 
	 * @author sk.chen
	 * @version 2017-05-12
	 */

	public static final String CHART_SET = "GB18030";
	public static final String CHART_SET_UTF8 = "UTF-8";

	public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	public static final String IV = "\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0";

	private Cipher cipher;

	private SecretKey secretKey;
	private IvParameterSpec ivspec;

	public AESUtils() {
			try {
				cipher = Cipher.getInstance(CIPHER_ALGORITHM);

				secretKey = null;
				ivspec = new IvParameterSpec(IV.getBytes());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			}
		}

	/**
	 * <pre>
	 * 微信小程序数据解密
	 * </pre>
	 *
	 * @param encryptedData
	 * @param key
	 * @param iv
	 * @return
	 */
	public String decrypt(String encryptedData, String key, String iv) {
		Key secretKey = new SecretKeySpec(Base64.decodeBase64(key), "AES");

		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey, generateIV(Base64.decodeBase64(iv)));// 初始化
			byte[] result = cipher.doFinal(Base64.decodeBase64(encryptedData));
			return new String(result, CHART_SET_UTF8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static AlgorithmParameters generateIV(byte[] iv) throws Exception {
		AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
		params.init(new IvParameterSpec(iv));
		return params;
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

	public String encrypt(String plaintext) throws UnsupportedEncodingException {
		// 转换得到字节流
		byte[] data = plaintext.getBytes(CHART_SET);

		// 输出buffer
		ByteArrayOutputStream outbuf = new ByteArrayOutputStream();

		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

			byte[] encryptedBlock = cipher.doFinal(data);
			// 追加结果到输出buffer中
			outbuf.write(encryptedBlock);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

		return Base64.encodeBase64String(outbuf.toByteArray()); // ciphertext
	}

	public String decrypt(String cryptedBase64Str) {
		// 转换得到字节流
		byte[] data = Base64.decodeBase64(cryptedBase64Str);

		// 输出buffer
		ByteArrayOutputStream outbuf = new ByteArrayOutputStream();

		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);

			byte[] decryptedBlock = cipher.doFinal(data);
			// 追加结果到输出buffer中
			outbuf.write(decryptedBlock);
			outbuf.flush();
			outbuf.close();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

		try {
			return outbuf.toString(CHART_SET);// 对解密后的数据指定输出编码格式GBK
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		 AESUtils aes = new AESUtils();
		 aes.initKey("1234567812345678");
		 try {
		 String encrpt = aes.encrypt("123abc");
		 System.out.println(encrpt);
		 System.out.println(aes.decrypt(encrpt));
		 } catch (UnsupportedEncodingException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
	}

}

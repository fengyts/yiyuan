package ng.bayue.other.learn;

import java.util.Arrays;  

import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.PBEKeySpec;  
import javax.crypto.spec.PBEParameterSpec;  
  
/** 
 * PBE算法 对称加密 password-based encryption 
 * @author stone 
 * @date 2014-03-10 23:41:35 
 */  
public class PBE {  
    static final String KEY_ALGORITHM = "PBEWithMD5AndDES";  
//  static byte[] salt = "xiaoyang".getBytes(); // 盐：Salt must be 8 bytes long  
    static byte[] salt = "哈皮aa".getBytes(); // 盐：Salt must be 8 bytes long  哈皮，在utf8中是6个字节  
    static int iterationCount = 888; //循环次数  
    static Cipher cipher;  
      
    public static void main(String[] args) throws Exception {  
        byte[] encrypt = encrypt("中华人民admin*&(*S&6");  
        System.out.println("PBE加密后：" + Arrays.toString(encrypt));  
          
        System.out.println("PBE解密后：" + decrypt(encrypt));  
    }  
      
    /** 
     * 使用PBE 算法 加密 
     * @return  加密后的字符数组 
     * @throws Exception 
     */  
    static byte[] encrypt(String str) throws Exception {  
        cipher = Cipher.getInstance(KEY_ALGORITHM);  
          
        //使用SecretKeyFactory 生成key  
        SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_ALGORITHM);  
        PBEKeySpec keySpec = new PBEKeySpec("shizongyin".toCharArray());  
        SecretKey key = factory.generateSecret(keySpec);  
        System.out.println("key:" + Arrays.toString(key.getEncoded()));  
          
        cipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(salt, iterationCount));//使用加密模式初始化 密钥  
        return cipher.doFinal(str.getBytes()); //按单部分操作加密或解密数据，或者结束一个多部分操作。  
    }  
      
    /** 
     *  
     * @param encrypt 
     * @return 
     * @throws Exception 
     */  
    static String decrypt(byte[] encrypt) throws Exception {  
        cipher = Cipher.getInstance(KEY_ALGORITHM);  
          
        //使用SecretKeyFactory 生成key  
        SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_ALGORITHM);  
        PBEKeySpec keySpec = new PBEKeySpec("shizongyin".toCharArray());  
        SecretKey key = factory.generateSecret(keySpec);  
        System.out.println("key:" + Arrays.toString(key.getEncoded()));  
          
        cipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(salt, iterationCount));//使用加密模式初始化 密钥  
        byte[] result = cipher.doFinal(encrypt); //按单部分操作加密或解密数据，或者结束一个多部分操作。  
          
        return new String(result);  
    }  
      
}
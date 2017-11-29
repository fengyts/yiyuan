package ng.bayue.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * 图形验证码帮助类
 * @author sk.chen
 * @version 2017-05-11
 */
public class CaptchaGenerator {
	private Random random = new Random();
	private String randomSrcStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // 允许字符串的范围

	private int width = 120; // 图片宽
	private int height = 34; // 图片高
	private int lineSize = 60; // 干扰线数量
	private int stringNum = 5; // 随机产生字符数量
	private int font = 24; // 字体大小

	/**
	 * 获得字体
	 * @return
	 */
	private Font getFont() {
		return new Font("Fixedsys", Font.CENTER_BASELINE, font);
	}

	/**
	 * 获得颜色
	 * @param fc
	 * @param bc
	 * @return
	 */
	private Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc - 16);
		int g = fc + random.nextInt(bc - fc - 14);
		int b = fc + random.nextInt(bc - fc - 18);
		return new Color(r, g, b);
	}
	
	/**
	 * 生成验证码
	 * @return
	 */
	public String generateCaptcha() {
		/*StringBuilder randomStr = new StringBuilder();
		for (int i = 1; i <= stringNum; i++) {
			randomStr.append(getRandomString(random.nextInt(randomSrcStr.length())));
		}
		return randomStr.toString();*/
		return generateCaptcha(stringNum);
	}
	
	public String generateCaptcha(int length) {
		if(length < 1){
			length = stringNum;
		}
		StringBuilder randomStr = new StringBuilder();
		for (int i = 1; i <= length; i++) {
			randomStr.append(getRandomString(random.nextInt(randomSrcStr.length())));
		}
		return randomStr.toString();
	}
	
	
	/**
	 * 将验证码绘制成图片并且以Base64字符串返回
	 * @param randomStr 验证码
	 * @return
	 */
	public String toImageBase64(String randomStr) {
		// BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, font));
		g.setColor(getRandColor(110, 133));
		// 绘制干扰线
		for (int i = 0; i <= lineSize; i++) {
			drowLine(g);
		}
		// 绘制随机字符
		drowString(g, randomStr);
		g.dispose();
		try {
			 ByteArrayOutputStream out = new ByteArrayOutputStream();
			 ImageIO.write(image, "jpeg", out);
			 byte[] imageBytes = out.toByteArray();
			 return Base64.encodeBase64String(imageBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 绘制字符串
	 * @param g
	 * @param randomStr
	 */
	private void drowString(Graphics g, String randomStr) {
		if (g == null || StringUtils.isBlank(randomStr)) {
			return ;
		}
		g.setFont(getFont());
		g.setColor(new Color(0, 0, 0));
		for (int i = 0; i < randomStr.length(); i ++) {
			g.drawString(randomStr.substring(i, i + 1), 18 * (i + 1), 28);
		}
	}

	/**
	 * 绘制干扰线
	 * @param g
	 */
	private void drowLine(Graphics g) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(13);
		int yl = random.nextInt(15);
		g.drawLine(x, y, x + xl, y + yl);
	}

	/**
	 * 获取随机的字符
	 * @param num
	 * @return
	 */
	public String getRandomString(int num) {
		return String.valueOf(randomSrcStr.charAt(num));
	}
}

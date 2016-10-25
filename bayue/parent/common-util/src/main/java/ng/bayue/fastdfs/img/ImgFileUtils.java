package ng.bayue.fastdfs.img;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ng.bayue.util.FileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImgFileUtils {

	private final static Logger logger = LoggerFactory
			.getLogger(ImgFileUtils.class);

	/** 等比例缩放 */
	private final static int EQUAL_PROPORTION = 1;
	/** 按照长度、宽度缩放 */
	private final static int WIDTH_HEIGHT_PROPORTION = 2;

	/**
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @param file : 源图片
	 * @param outPath ：处理后图片保存路径
	 * @param scale ： 缩放比例
	 * @param flag ：缩小-true,放大-false
	 * @return
	 */
	public static String zoomImgScale(File file, String outPath, int scale, boolean flag) {
		if (!file.exists()) {
			logger.info("源图片不存在");
			return null;
		}
		try {
			BufferedImage bimg = ImageIO.read(file);
			int width = bimg.getWidth();
			int height = bimg.getHeight();
			if (flag) {
				width *= scale;
				height *= scale;
			} else {
				width /= scale;
				height /= scale;
			}
			Image img = bimg.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			BufferedImage targ = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
			Graphics graphics = targ.getGraphics();
			graphics.drawImage(img, 0, 0, null);
			graphics.dispose();

			outPath = null == outPath ? FileUtils.copyFileNameAndCreateBySuffixOne(file.getAbsolutePath()) : outPath;
			ImageIO.write(targ, "jpg", new File(outPath));
			return outPath;
			
		} catch (IOException e) {
			logger.error("", e);
		}
		return null;
	}

	public static void main(String[] args) {
		File file = new File("E:/test/a.jpg");
		ImgFileUtils.zoomImgScale(file, null, 2, true);
	}

}

//package ng.bayue.util.test;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//
//import ng.bayue.util.image.ImageSizer;
//
//public class Test {
//
//	public static void testImagePress() {
//		ImageSizer image = new ImageSizer();
//		File oldFile = new File("E:/test/a.jpg");
//		 try {
//		 File destFile = new File("E:/test/press.jpg");
//		 image.imageZip(oldFile, destFile, null, 1024, 512, 1.0f);
//		 } catch (IOException e) {
//		 e.printStackTrace();
//		 }
//
//		// BufferedImage srcBufferedImage;
//		// try {
//		// File destFile = new File("E:/test/press1.jpg");
//		// srcBufferedImage = ImageIO.read(oldFile);
//		// image.zoom(srcBufferedImage, destFile, "jpg", 512, 314);
//		// } catch (IOException e) {
//		// e.printStackTrace();
//		// }
//
//		/*try {
//			File destFile = new File("E:/test/press3.jpg");
//			image.resize(oldFile, destFile, 512, ".jpg");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}*/
//		
//	}
//
//
//	public static void main(String[] args) {
//		 testImagePress();
//	}
//
//}

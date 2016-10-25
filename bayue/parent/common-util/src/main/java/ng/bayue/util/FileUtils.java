package ng.bayue.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * <pre>
	 * 获取文件扩展名，带"."，例：.jpg
	 * </pre>
	 *
	 * @param fileName
	 * @return
	 */
	public static String getFileExtension(String fileName) {
		if (null == fileName || "".equals(fileName)) {
			return null;
		}
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	public static String getFileExtension(File file){
		if(!file.exists()){
			logger.info("file is not exists!");
			return null;
		}
		return getFileExtension(file.getName());
	}
	
	/**
	 * <pre>
	 * 获取文件前缀名称
	 * </pre>
	 *
	 * @param fileName
	 * @return
	 */
	public static String prefixFileName(String fileName){
		if(null == fileName || "".equals(fileName)){
			return null;
		}
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
	
	public static String prefixFileName(File file){
		if(!file.exists()){
			logger.info("file is not exists!");
			return null;
		}
		return prefixFileName(file.getName());
	}

	/**
	 * <pre>
	 * 文件创建，该目录已经存在要创建的文件则在文件名后面加1
	 * </pre>
	 *
	 * @param filePath
	 * @return
	 */
	public static String copyFileNameAndCreateBySuffixOne(String filePath) {
		if (null == filePath || "".equals(filePath)) {
			return null;
		}
		return copyFileNameAndCreateBySuffixOne(new File(filePath));
	}

	public static String copyFileNameAndCreateBySuffixOne(File file) {
		if(null == file){ return null;}
		if (file.exists()) {
			String fileName = file.getName();
			String fileSuffix = getFileExtension(fileName);
			String filePrefixName = fileName.substring(0,fileName.lastIndexOf("."));
			String finFileName = filePrefixName + "1" + fileSuffix;
			String temPath = file.getParent() + File.separator + finFileName;
			String finPath = copyFileNameAndCreateBySuffixOne(temPath);
			return finPath;
		}
		return file.getAbsolutePath();
	}
	
	
	public static void main(String[] args) {
//		String str = FileUtils.copyFileNameAndCreateBySuffixOne("E:/test/a.jpg");
		String filePath = "E:/test/a.jpg";
		String str = prefixFileName(new File(filePath));
		System.out.println(str);
	}
	
}

package ng.bayue.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ng.bayue.constants.CharsetConstant;

public class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	private static final String UTF8 = "UTF-8";

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

	public static String getFileExtension(File file) {
		if (null == file || !file.exists()) {
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
	public static String prefixFileName(String fileName) {
		if (null == fileName || "".equals(fileName)) {
			return null;
		}
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	public static String prefixFileName(File file) {
		if (!file.exists()) {
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

	/**
	 * <pre>
	 * 文件创建，该目录已经存在要创建的文件则在文件名后面加1
	 * </pre>
	 *
	 * @param file
	 * @return
	 */
	public static String copyFileNameAndCreateBySuffixOne(File file) {
		if (null == file) {
			return null;
		}
		if (file.exists()) {
			String fileName = file.getName();
			String fileSuffix = getFileExtension(fileName);
			String filePrefixName = fileName.substring(0, fileName.lastIndexOf("."));
			String finFileName = filePrefixName + "1" + fileSuffix;
			String temPath = file.getParent() + File.separator + finFileName;
			String finPath = copyFileNameAndCreateBySuffixOne(temPath);
			return finPath;
		}
		return file.getAbsolutePath();
	}

	/**
	 * <pre>
	 * 获取文件编码方式
	 * </pre>
	 *
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String getEncoding(File file) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		int p = (bis.read() << 8) + bis.read();
		System.out.println(Integer.toHexString(p));
		String code = "get encoding failure !";
		bis.close();
		switch (p) {
		case 0xefbb:
			code = "UTF-8";
			break;
		case 0xfffe:
			code = "Unicode";
			break;
		// case 0xfeff:
		// code = "Unicode Big Endian";
		// break;
		case 0xfeff:
			code = "UTF-16BE";
			break;
		case 0xe4b8:
			code = "UTF-8";
			break;
		case 0xe59c:
			code = "UTF-8";
			break;
		case 0x6874:
			code = "GBK";
			break;
		case 0xa1b6:
			code = "GB2312";
			break;
		case 0x5c75:
			code = "ASCII";
			break;
		// default://0x6874
		// code = "GBK";
		}
		return code;
	}

	/**
	 * <pre>
	 * 读取文件从startNum行开始，读取countLineNum行
	 * </pre>
	 *
	 * @param file
	 * @param startNum
	 *            开始读取的行号
	 * @param countLineNum
	 *            需要读取的行数
	 * @param encoding
	 *            指定读取文件的编码方式,如果不指定，默认为UTF-8
	 * @return
	 * @throws IOException
	 */
	public static String textFileReaderByLineNumber(File file, int startNum, int countLineNum, String encoding)
			throws IOException {
		if (null == file || !file.exists()) {
			logger.info("文件不存在");
			throw new FileNotFoundException();
			// return ;
		}
		if (startNum < 0 || countLineNum < 1) {
			logger.info("行号必须大于0且至少读取1行");
			throw new IOException("行号必须大于0且至少读取1行");
		}
		if (StringUtils.isBlank(encoding)) {
			encoding = UTF8;
		}
		BufferedReader br = new LineNumberReader(new InputStreamReader(new FileInputStream(file), encoding));
		StringBuffer sb = new StringBuffer();
		String line = null;
		long currentNum = 0; // 当前行号
		long end = startNum + countLineNum;
		boolean blankFlag = true; // 是否连续空白行
		while (null != (line = br.readLine())) {
			if (currentNum >= end) {
				break;
			}
			if (currentNum >= startNum) {
				if (line == null || "".equals(line) && blankFlag) { // 去掉空白行
					sb.append("\n");
					blankFlag = false;
				} else {
					if (line.length() > 113) { // 长度超过113时换行
						StringBuffer is = new StringBuffer(line);
						is.insert(113, "\n");
						line = is.toString();
					}
					sb.append(line);
					blankFlag = true;
				}
			}
			currentNum++;
		}
		try {
			if (null != br)
				br.close();
		} catch (Exception e) {
			throw new IOException();
		}
		return sb.toString();
	}

	public static String inputStreamToString(InputStream is, String charset) {
		if (StringUtils.isBlank(charset)) {
			charset = CharsetConstant.UTF8;
		}
		try {
			final Reader reader = new InputStreamReader(is, charset);
			StringBuilder result = new StringBuilder();
			int len = 0;
			final char[] cbuf = new char[1024];
			while (-1 != (len = reader.read(cbuf))) {
				result.append(cbuf, 0, len);
			}
			return result.toString();
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		}
		return null;
	}

	public static String inputStreamToString(InputStream is, Charset charset) {
		if (null == charset) {
			charset = CharsetConstant.Charset_UTF8;
		}
		return inputStreamToString(is, charset);
	}

	public static void main(String[] args) {
		// String str =
		// FileUtils.copyFileNameAndCreateBySuffixOne("E:/test/a.jpg");
		// String filePath = "E:/test/a.jpg";
		// String str = prefixFileName(new File(filePath));
		// System.out.println(str);
		Integer a = null;
		int b = a;

		String filePath = "E:/test/测试用的文档.txt";
		String filePath1 = "E:/test/绝世武神.txt";
		File file = new File(filePath1);
		try {
			// String encoding = getEncoding(file);
			// System.out.println(encoding);

			String content = textFileReaderByLineNumber(file, 222155, 100, "GBK");
			// String content = textFileReaderByLineNumber(file, 0, 8, "GBK");
			System.out.println(content);
		} catch (IOException e) {
			logger.error("", e);
		}

	}

}

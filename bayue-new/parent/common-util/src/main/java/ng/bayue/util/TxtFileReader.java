package ng.bayue.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

public class TxtFileReader {

	private static final String UTF8 = "UTF-8";

	private String fielPath;
	private File file;

	public TxtFileReader(String filePath) {
		this.fielPath = filePath;
	}

	public TxtFileReader(File file) {
		this.file = file;
	}

	/**
	 * <pre>
	 * 根据字符串获取行号
	 * </pre>
	 *
	 * @param txt
	 * @return
	 * @throws Exception
	 */
	public int getLineNumByTxt(String txt, String encoding) throws Exception {
		if (StringUtils.isBlank(txt)) {
			throw new IllegalArgumentException("参数不能为空");
		}
		BufferedReader br = null;
		try {
			br = new LineNumberReader(new InputStreamReader(new FileInputStream(file), encoding));
			String line = null;
			int lineNum = 0;
			while (null != (line = br.readLine())) {
				lineNum++;
				if (line.contains(txt)) {
					return lineNum;
				}
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			throw new Exception(e);
		} finally {
			if (null != br) {
				br.close();
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		String filePath1 = "E:/test/武炼巅峰.txt";
		File file = new File(filePath1);
//		String encoding = FileUtils.getEncoding(file);
//		System.out.println(encoding);
		
//		TxtFileReader fr = new TxtFileReader(file);
//		int lineNum = fr.getLineNumByTxt("第三千八百二十一章", UTF8);
//		System.out.println(lineNum);
		
		String res = FileUtils.textFileReaderByLineNumber(file, 555965, 200, UTF8);
		System.out.println(res);
	}

}

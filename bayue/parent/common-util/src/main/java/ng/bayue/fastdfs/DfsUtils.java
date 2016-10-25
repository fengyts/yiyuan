package ng.bayue.fastdfs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ng.bayue.factory.TrackerServerFactory;
import ng.bayue.factory.TrackerServerPool;
import ng.bayue.util.FileUtils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * fastdfs工具类，已废弃，建议使用DfsService接口
 * </pre>
 *
 * @author fengyts
 * @version $Id: DfsUtils.java, v 0.1 2016年7月23日 上午10:50:42 fengyts Exp $
 */
@Deprecated
public class DfsUtils {

	private final static Logger logger = LoggerFactory.getLogger(DfsUtils.class);

	private final static String config = "dfs_client.conf";

	private static StorageClient storageClient = null;
	private static StorageClient1 storageClient1 = null;

	private final static String group1 = "group1";

	private static TrackerServerPool trackerServerPool;
	private static TrackerServer trackerServer = null;
	
//	static{
//		try {
//			ClientGlobal.init(config);
//		} catch (IOException | MyException e) {
//			logger.info("fastdfs 加载配置文件错误：{}", e);
//		}
//	}

	/**
	 * <pre>
	 * 获取连接，单线程
	 * </pre>
	 *
	 * @return
	 * @throws IOException
	 */
	@Deprecated
	private static TrackerServer getTrackerServer() throws IOException {
//		try {
//			ClientGlobal.init(config);
//		} catch (MyException e) {
//			logger.error("", e);
//		}
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		return trackerServer;
	}
	
	/**
	 * <pre>
	 * 初始化连接池
	 * </pre>
	 *
	 * @return
	 */
	private static TrackerServerPool initTrackerServerPool(){
//		TrackerServerFactory factory = new TrackerServerFactory(new String[]{"192.168.235.128:22122"});
		TrackerServerFactory factory = new TrackerServerFactory(null);
		GenericObjectPoolConfig gopConfig = new GenericObjectPoolConfig();
		trackerServerPool = new TrackerServerPool(factory, gopConfig);
		return trackerServerPool;
	}

	/**
	 * <pre>
	 * 获取链接，从连接池
	 * </pre>
	 *
	 * @return
	 */
	private static TrackerServer getTrackerServerByPool() {
		initTrackerServerPool();
		try {
//			 TrackerServer trackerServer = trackerServerPool.borrowObject();
			trackerServer = trackerServerPool.borrowObject();
			return trackerServer;
		} catch (Exception e) {
			logger.error("get trakerServer from trackerServer pool exception:{}", e);
		}
		return null;
	}

	private static void returnTrackerToPool(TrackerServer trackerServer) {
		try {
			if (null != trackerServer) {
				trackerServerPool.returnObject(trackerServer);
			}
		} catch (Exception e) {
			logger.info("return trackerServer failure:{}", e);
			// TODO: handle exception
		}
	}

	/**
	 * <pre>
	 * 初始化dfs连接接配置
	 * </pre>
	 *
	 * @return
	 */
	public static StorageClient initConnection() {
		try {
			ClientGlobal.init(config);
//			 trackerServer = getTrackerServer();
//			 TrackerServer trackerServer = getTrackerServerByPool();
//			trackerServer = getTrackerServerByPool();
			getTrackerServerByPool();
			StorageClient storageClient = new StorageClient(trackerServer, null);
			return storageClient;
//		} catch (IOException | MyException e) {
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * <pre>
	 * 初始化fastdfs连接，使用StorageClient1
	 * </pre>
	 *
	 * @return
	 */
	public static StorageClient1 initConnection1() {
		try {
			ClientGlobal.init(config);
			// TrackerServer trackerServer = getTrackerServerByPool();
//			trackerServer = getTrackerServerByPool();
			getTrackerServerByPool();
			StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
			return storageClient1;
		} catch (IOException | MyException e) {
			logger.error("", e);
		}
		return null;
	}

	// /**
	// * <pre>
	// * 获取文件扩展名
	// * </pre>
	// *
	// * @param fileName
	// * @return
	// */
	// public static String fileExtension(String fileName) {
	// if (null == fileName || "".equals(fileName)) {
	// return null;
	// }
	// return fileName.substring(fileName.lastIndexOf(".") + 1);
	// }

	/**
	 * <pre>
	 * 文件上传，返回路径，格式：group1/M00/00/00/wKgSgFdNUu-APoloAAvqH_kipG8186.jpg
	 * </pre>
	 *
	 * @param file
	 * @return
	 */
	public static String uploadFile(File file) {
		if (!file.exists()) {
			logger.info("要上传的文件不存在,请检查！file:{}", file.getName());
			return null;
		}
		try {
			storageClient = initConnection();
			NameValuePair[] metaList = new NameValuePair[] {};
			String[] result = storageClient.upload_file(file.getAbsolutePath(),
					FileUtils.getFileExtension(file.getName()), metaList);
			if (null != result && 2 != result.length) {
				logger.info("文件服务器错误，上传失败，file:{}", file.getName());
			}
			return result[0] + "/" + result[1];
		} catch (IOException | MyException e) {
//		} catch (Exception e) {
			logger.info("fastdfs图片上传错误:{}", e);
		} finally {
			returnTrackerToPool(trackerServer);
		}
		return "";
	}

	public static String[] batchUploadFiles(File[] files) {
		if (null == files) {
			return null;
		}
		/*
		 * int len = files.length; String[] urls = new String[len]; for (int i =
		 * 0; i < len; i++) { String url = uploadFile(files[i]); urls[i] = url;
		 * } return urls;
		 */

		List<String> listUrls = new ArrayList<String>();
		for (File file : files) {
			logger.info("batchUploadFiles,file name:{}", file.getName());
			String url = uploadFile(file);
			if (null == url || "".equals(url)) {
				logger.info("batchUploadFiles break,batchUploadFiles failure,file:{}", file.getName());
				break;
			}
			listUrls.add(url);
		}
		String[] urls = listUrls.toArray(new String[0]);
		return urls;
	}

	/**
	 * <pre>
	 * 使用storageClient 和storageClient1的区别：
	 * 删除时，storageClient 文件名参数为：String file = "M00/00/00/wKgSgFdNXxOAOF6EAAvqH_kipG8559.jpg";
	 * storageClient1 文件名参数为：String file = "group1/M00/00/00/wKgSgFdNXxOAOF6EAAvqH_kipG8559.jpg";
	 * 
	 * 删除成功时dfs服务器返回值为0；
	 * </pre>
	 *
	 * @param fileName
	 * @return
	 */
	public static int deleteFile(String fileName) {
		if(null == fileName || "".equals(fileName)){
			logger.info("dfs-server delete file broken:the require delete file is not exist,please check it");
			return -1;
		}
		try {
			 storageClient = initConnection();
			 int res = storageClient.delete_file(group1, fileName);
//			storageClient1 = initConnection1();
//			int res = storageClient1.delete_file1(fileName);
//			if (0 != res) {
//				logger.info("file delete failure,file:{}", fileName);
//			}
			return res;
		} catch (IOException | MyException e) {
			logger.error("", e);
		}finally{
			returnTrackerToPool(trackerServer);
		}
		return -1;
	}
	
	public static int deleteFile1(String fileName){
		if(null == fileName || "".equals(fileName)){
			logger.info("dfs-server delete file broken:the require delete file is not exist,please check it");
			return -1;
		}
		storageClient1 = initConnection1();
		try {
			if(!fileName.startsWith(group1)){
				fileName = group1 + "/" + fileName;
			}
			int res = storageClient1.delete_file1(fileName);
			if (0 != res) {
				logger.info("file delete failure,file:{}", fileName);
			}
			return res;
		} catch (IOException | MyException e) {
			logger.error("", e);
		}finally{
			returnTrackerToPool(trackerServer);
		}
		return -1;
	}

	public static int[] batchDeleteFiles(String... fileNames) {
		if (null == fileNames) {
			return null;
		}
		int len = fileNames.length;
		int[] ress = new int[len];
		for (int i = 0; i < len; i++) {
			String fileName = fileNames[i];
			int res = deleteFile(fileName);
			if (0 == res) {
				logger.info("batchDeleteFiles is failure,file:{}", fileName);
				break;
			}
			ress[i] = res;
		}
		return ress;
	}

	/**
	 * <pre>
	 * 文件下载
	 * </pre>
	 *
	 * @param fileName
	 * @return
	 */
	public static byte[] downloadFile(String fileName) {
		storageClient1 = initConnection1();
		try {
			byte[] b = storageClient1.download_file1(fileName);
			return b;
		} catch (IOException | MyException e) {
			logger.error("", e);
		}finally{
			returnTrackerToPool(trackerServer);
		}
		return null;
	}

	public static FileInfo getFileInfo(String filePath) {
		FileInfo info = null;
		storageClient = initConnection();
		try {
			info = storageClient.get_file_info(group1, filePath);
			System.out.println(info);
		} catch (IOException | MyException e) {
			logger.error("", e);
		}finally{
			returnTrackerToPool(trackerServer);
		}
		return info;
	}

	public static void fileWriter(byte[] b, String targetFile) {
		final String targetPath = "E:/test/";
		if (null == b) {
			logger.info("write file failure: the source file is empty!");
			return;
		}
		if (null == targetFile || "".equals(targetFile)) {
			targetFile = targetPath + UUID.randomUUID().toString() + ".jpg";
		}
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File(targetFile));
			os.write(b);
			os.flush();
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				if (os != null)
					os.close();
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}
	
	public static void testConfig() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(config)));
			String line = "";
			while (null != (line = br.readLine())) {
				System.out.println(line);
			}
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				if (null != br)
					br.close();
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}

	public static void testDfs() {
		// download
		String fileName = "group1/M00/00/00/wKjrgFd5sT6ANcDsAAvqH_kipG807..jpg";
		byte[] b = DfsUtils.downloadFile(fileName);
		DfsUtils.fileWriter(b, "E:/test/ab.jpg");
	}
	
	public static void testConn(){
		try {
			TrackerServer ts = getTrackerServer();
			System.out.println(ts);
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("", e);
		}
	}

	public static void main(String[] args) {
		
//		testConn();//连接测试
		// DfsUtils.testConfig();//测试配置文件
		String fileName = "M00/00/00/wKjrgFd5IiSACX8fAAvqH_kipG8401.jpg";

//		 String fileName1 = "M00/00/00/wKjrgFeQgQqACazkAAvqH_kipG8490.jpg";
//		 FileInfo info = DfsUtils.getFileInfo(fileName1);
//		 Long size = info.getFileSize();
//		 System.out.println(size);

		File file = new File("E:/test/Koala.jpg");
		String path = DfsUtils.uploadFile(file);
		System.out.println(path);

//		 String file = "M00/00/00/wKjrgFd5sLaAIesTAAvqH_kipG8634_big.jpg";
//		 int res = DfsUtils.deleteFile1(file);
//		 System.out.println(res);
		
//		 DfsUtils.testDfs();
	}

}

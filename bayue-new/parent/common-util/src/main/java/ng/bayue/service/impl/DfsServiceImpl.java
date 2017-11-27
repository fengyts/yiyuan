package ng.bayue.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ng.bayue.common.CommonMessages;
import ng.bayue.common.CommonResultMessage;
import ng.bayue.factory.TrackerServerPool;
import ng.bayue.service.DfsService;
import ng.bayue.util.FileUtils;

public class DfsServiceImpl implements DfsService {

	private static Logger logger = LoggerFactory.getLogger(DfsServiceImpl.class);

	/** 组名 */
	private static final String GROUP1 = "group1";
	/** 操作失败结果码 */
	private static final int FAILURE = -1;

	private TrackerServerPool trackerServerPool = null;

	// private TrackerServer trackerServer = null;

	public DfsServiceImpl(TrackerServerPool trackerServerPool) {
		this.trackerServerPool = trackerServerPool;
	}

	private TrackerServer getTrackerServerByPool() {
		try {
			return trackerServerPool.borrowObject();
			// trackerServer = trackerServerPool.borrowObject();
			// return trackerServer;
		} catch (Exception e) {
			logger.error("get trakerServer from trackerServer pool exception:{}", e);
		}
		return null;
	}

	private void returnTrackerToPool(TrackerServer trackerServer) {
		try {
			if (null != trackerServer) {
				trackerServerPool.returnObject(trackerServer);
			}
		} catch (Exception e) {
			logger.info("return trackerServer to pool failure:{}", e);
		}
	}

	private StorageClient getStorageClient() {
		TrackerServer trackerServer = getTrackerServerByPool();
		// trackerServer = getTrackerServerByPool();
		return new StorageClient(trackerServer, null);
	}

	private StorageClient1 getStorageClient1() {
		TrackerServer trackerServer = getTrackerServerByPool();
		// trackerServer = getTrackerServerByPool();
		return new StorageClient1(trackerServer, null);
	}

	private String upload(String groupName, File file) {
		TrackerServer trackerServer = null;
		try {
			// StorageClient client = getStorageClient();
			try {
				trackerServer = trackerServerPool.borrowObject();
			} catch (Exception e) {
				logger.error("get trakerServer from trackerServer pool exception:{}", e);
				return null;
			}
			StorageClient client = new StorageClient(trackerServer, null);

			String[] result = null;
			NameValuePair[] metaList = new NameValuePair[] {};
			if (StringUtils.isEmpty(groupName)) {
				result = client.upload_file(file.getAbsolutePath(),
						FileUtils.getFileExtension(file), metaList);
			} else {
				result = client.upload_file(groupName, file.getName(),
						FileUtils.prefixFileName(file), file.getAbsolutePath(),
						FileUtils.getFileExtension(file), metaList);
			}
			if (null != result && 2 != result.length) {
				logger.info("文件服务器错误，上传失败，file:{}", file.getName());
			}
			return result[0] + "/" + result[1];
		} catch (IOException | MyException e) {
			logger.info("fastdfs图片上传错误:{}");
		} finally {
			returnTrackerToPool(trackerServer);
		}

		return null;
	}

	private String upload1(String groupName, File file) {
		TrackerServer trackerServer = null;
		try {
			// StorageClient1 client1 = getStorageClient1();
			try {
				trackerServer = trackerServerPool.borrowObject();
			} catch (Exception e) {
				logger.error("get trakerServer from trackerServer pool exception:{}", e);
				return null;
			}
			StorageClient1 client1 = new StorageClient1(trackerServer, null);

			String dfsPath = null;
			NameValuePair[] metaList = new NameValuePair[] {};
			if (StringUtils.isEmpty(groupName)) {
				dfsPath = client1.upload_file1(file.getAbsolutePath(),
						FileUtils.getFileExtension(file), metaList);
			} else {
				dfsPath = client1.upload_file1(groupName, file.getAbsolutePath(),
						FileUtils.getFileExtension(file), metaList);
			}
			if (StringUtils.isEmpty(dfsPath)) {
				logger.info("fastdfs图片上传错误:{}");
				return null;
			}
			return dfsPath;
		} catch (IOException | MyException e) {
			logger.info("fastdfs图片上传错误:{}", e);
		} finally {
			returnTrackerToPool(trackerServer);
		}
		return null;
	}

	@Override
	public String uploadFile(File file) {
		if (!file.exists()) {
			logger.info("fastdfs upload exception:the file -{}- is not exists!",
					file.getAbsolutePath());
			return null;
		}
		return upload(null, file);
	}

	@Override
	public String uploadFile1(File file) {
		if (!file.exists()) {
			logger.info("fastdfs upload exception:the file -{}- is not exists!",
					file.getAbsolutePath());
			return null;
		}
		return upload1(null, file);
	}

	@Override
	public String uploadFile(File file, String groupName) {
		if (!file.exists()) {
			logger.info("fastdfs upload exception:the file -{}- is not exists!",
					file.getAbsolutePath());
			return null;
		}
		if (StringUtils.isEmpty(groupName)) {
			logger.info("fastdfs upload exception,groupName is null");
			return null;
		}
		return upload(groupName, file);
	}

	@Override
	public String uploadFile1(File file, String groupName) {
		if (StringUtils.isEmpty(groupName)) {
			logger.info("fastdfs upload exception,groupName is null");
			return null;
		}
		if (StringUtils.isEmpty(groupName)) {
			logger.info("fastdfs upload exception,groupName is null");
			return null;
		}
		return upload1(groupName, file);
	}

	@Override
	public String[] batchUploadFiles(File... files) {
		if (null == files) {
			return null;
		}
		List<String> listUrls = new ArrayList<String>();
		for (File file : files) {
			logger.info("batchUploadFiles,file name:{}", file.getName());
			String url = uploadFile1(file);
			if (null == url || "".equals(url)) {
				logger.info("batchUploadFiles break,batchUploadFiles failure,file:{}",
						file.getName());
				break;
			}
			listUrls.add(url);
		}
		String[] urls = listUrls.toArray(new String[0]);
		return urls;
	}

	@Override
	public int deleteFile(String groupName, String fileId) {
		if (null == fileId || "".equals(fileId)) {
			logger.info("dfs-server delete file broken:the require delete file is not exist,please check it");
			return FAILURE;
		}
		TrackerServer trackerServer = null;
		try {
			// StorageClient storageClient = getStorageClient();
			try {
				trackerServer = trackerServerPool.borrowObject();
			} catch (Exception e) {
				logger.error("get trakerServer from trackerServer pool exception:{}", e);
				return FAILURE;
			}
			StorageClient storageClient = new StorageClient(trackerServer, null);

			if (StringUtils.isEmpty(groupName)) {
				groupName = GROUP1;
			}
			int res = storageClient.delete_file(groupName, fileId);
			if (0 != res) {
				logger.info("file delete failure,file:{}", fileId);
				return FAILURE;
			}
			return res;
		} catch (IOException | MyException e) {
			logger.info("fastdfs delete file failure:{}", e);
		} finally {
			returnTrackerToPool(trackerServer);
		}
		return FAILURE;
	}

	@Override
	public int deleteFile1(String fileId) {
		if (null == fileId || "".equals(fileId)) {
			logger.info("dfs-server delete file broken:the require delete file is not exist,please check it");
			return FAILURE;
		}
		TrackerServer trackerServer = null;
		try {
			// StorageClient1 storageClient1 = getStorageClient1();
			try {
				trackerServer = trackerServerPool.borrowObject();
			} catch (Exception e) {
				logger.error("get trakerServer from trackerServer pool exception:{}", e);
				return FAILURE;
			}
			StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);

			if (!fileId.startsWith(GROUP1)) {
				fileId = GROUP1 + "/" + fileId;
			}
			int res = storageClient1.delete_file1(fileId);
			if (0 != res) {
				logger.info("file delete failure,file:{}", fileId);
				return FAILURE;
			}
			return res;
		} catch (IOException | MyException e) {
			logger.info("fastdfs delete file failure:{}", e);
		} finally {
			returnTrackerToPool(trackerServer);
		}
		return FAILURE;
	}

	@Override
	public CommonResultMessage batchDeleteFiles(String... fileIds) {
		if (null == fileIds) {
			return null;
		}
		boolean isSuccess = true;
		List<String> listSuccess = new ArrayList<String>();
		List<String> listFailure = new ArrayList<String>();

		int len = fileIds.length;
		int[] ress = new int[len];
		for (int i = 0; i < len; i++) {
			String fileId = fileIds[i];
			int res = deleteFile1(fileId);
			ress[i] = res;
			if (0 != res) {
				logger.info("fastdfs server exception,batch delete file failure:{}", fileId);
				isSuccess = false;
				listFailure.add(fileId);
			} else {
				listSuccess.add(fileId);
			}
		}
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("success", listSuccess);
		map.put("failure", listFailure);
		if (isSuccess) {
			return new CommonResultMessage(CommonResultMessage.Success, CommonMessages.HandleFailure, map);
		}
		return new CommonResultMessage();
	}

	@Override
	public byte[] downloadFile(String fileId) {
		TrackerServer trackerServer = null;
		try {
			// StorageClient1 storageClient1 = getStorageClient1();
			try {
				trackerServer = trackerServerPool.borrowObject();
			} catch (Exception e) {
				logger.error("get trakerServer from trackerServer pool exception:{}", e);
				return null;
			}
			StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);

			byte[] b = storageClient1.download_file1(fileId);
			return b;
		} catch (IOException | MyException e) {
			logger.info("fastdfs download file exception:{}", e);
		} finally {
			returnTrackerToPool(trackerServer);
		}
		return null;
	}

	@Override
	public FileInfo getFileInfo(String groupName, String fileId) {
		TrackerServer trackerServer = null;
		try {
			// StorageClient1 storageClient1 = getStorageClient1();
			try {
				trackerServer = trackerServerPool.borrowObject();
			} catch (Exception e) {
				logger.error("get trakerServer from trackerServer pool exception:{}", e);
				return null;
			}
			StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);

			FileInfo info = storageClient1.get_file_info(groupName, fileId);
			return info;
		} catch (IOException | MyException e) {
			logger.error("fastdfs get fileInfo exception:{}", e);
		} finally {
			returnTrackerToPool(trackerServer);
		}
		return null;
	}

	@Override
	public FileInfo getFileInfo1(String fileId) {
		TrackerServer trackerServer = null;
		try {
			// StorageClient1 storageClient1 = getStorageClient1();
			try {
				trackerServer = trackerServerPool.borrowObject();
			} catch (Exception e) {
				logger.error("get trakerServer from trackerServer pool exception:{}", e);
				return null;
			}
			StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);

			FileInfo info = storageClient1.get_file_info1(fileId);
			return info;
		} catch (IOException | MyException e) {
			logger.error("fastdfs get fileInfo exception:{}", e);
		} finally {
			returnTrackerToPool(trackerServer);
		}
		return null;
	}

	@Override
	public NameValuePair[] getFileMeta(String fileId) {
		TrackerServer trackerServer = null;
		try {
			trackerServer = trackerServerPool.borrowObject();
			StorageClient1 client1 = new StorageClient1(trackerServer, null);
			NameValuePair[] metaList = client1.get_metadata1(fileId);
			return metaList;
		} catch (Exception e) {
			logger.error("fastdfs get NameValuePair exception:{}", e);
		} finally {
			returnTrackerToPool(trackerServer);
		}
		return null;
	}

}

package ng.bayue.service;

import java.io.File;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.FileInfo;

import ng.bayue.common.CommonResultMessage;

public interface DfsService {
	
	/**
	 * <pre>
	 * 文件上传，该方法采用StorageClient,建议使用uploadFile1(File file)方法。
	 * </pre>
	 *
	 * @param file
	 * @return
	 */
	@Deprecated
	public abstract String uploadFile(File file);
	
	public abstract String uploadFile1(File file);
	
	/**
	 * <pre>
	 * 文件上传，该方法采用StorageClient,建议使用uploadFile1(File file, String groupName)方法。
	 * </pre>
	 *
	 * @param file
	 * @param groupName
	 * @return
	 */
	@Deprecated
	public abstract String uploadFile(File file, String groupName);
	
	public abstract String uploadFile1(File file, String groupName);

	public abstract String[] batchUploadFiles(File... files);
	
	
	/**
	 * <pre>
	 * 使用storageClient 和storageClient1的区别：
	 * 删除时，storageClient 文件名参数为：String file = "M00/00/00/wKgSgFdNXxOAOF6EAAvqH_kipG8559.jpg";
	 * storageClient1 文件名参数为：String file = "group1/M00/00/00/wKgSgFdNXxOAOF6EAAvqH_kipG8559.jpg";
	 * 
	 * 删除成功时dfs服务器返回值为0,失败则返回-1
	 * </pre>
	 *
	 * @param fileId
	 * @return
	 */
	@Deprecated
	public abstract int deleteFile(String groupName, String fileId);
	
	public abstract int deleteFile1(String fileId);

	/**
	 * <pre>
	 * 返回值ResultMessage:如果有删除失败的项，则ResultMessage.getData()项是一个Map<string,List<String>> map,
	 * map的key分别为success和failure,分别包含成功和失败的fileId
	 * </pre>
	 *
	 * @param fileIds
	 * @return
	 * 		
	 */
	public abstract CommonResultMessage batchDeleteFiles(String... fileIds);
	
	public abstract byte[] downloadFile(String fileId);
	
	@Deprecated
	public abstract FileInfo getFileInfo(String groupName, String fileId);
	
	public abstract FileInfo getFileInfo1(String fileId);
	
	public abstract NameValuePair[] getFileMeta(String fileId);

}

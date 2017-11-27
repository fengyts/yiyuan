package ng.bayue.backend.ao.utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ng.bayue.common.CommonResultMessage;
import ng.bayue.constants.CommonConstant;
import ng.bayue.service.DfsService;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DfsAO {

	private static final Logger logger = LoggerFactory.getLogger(DfsAO.class);

	@Autowired
	private DfsService dfsService;

	public String uploadFile(File file) {
		if (!file.exists()) {
			logger.info("file is not exists - {}", file.getAbsolutePath());
			return null;
		}
		String dfsPath = dfsService.uploadFile1(file);
		return dfsPath;

	}

	public String[] batchUploadFile(File[] files) {
		if (null == files || 0 == files.length) {
			return null;
		}
		String[] result = dfsService.batchUploadFiles(files);
		return result;
	}

	public int deleteFile(String fileId) {
		if (StringUtils.isEmpty(fileId)) {
			logger.info("file is not exists - {}", fileId);
			return CommonConstant.ResultStatusCode.FAILURE;
		}
		int res = dfsService.deleteFile1(fileId);
		return res;
	}

	public boolean batchDeleteFile(String[] fileIds) {
		if (null == fileIds || 0 == fileIds.length) {
			return false;
		}
		CommonResultMessage msg = dfsService.batchDeleteFiles(fileIds);
		if (CommonResultMessage.Success != msg.getResult()) {
			@SuppressWarnings("unchecked")
			Map<String, List<String>> map = (Map<String, List<String>>) msg.getData();
//			List<String> listSuccess = map.get("success");
			List<String> listFailure = map.get("failure");
			logger.info("fastdfs batch delete file failure,failures:{}",
					Arrays.toString(listFailure.toArray(new String[0])));
			return false;
		}
		return true;
	}

	public void getFileMetaData(String fileId) {
		if (StringUtils.isEmpty(fileId)) {
			logger.info("file is not exists - {}", fileId);
			return;
		}
		NameValuePair[] nvps = dfsService.getFileMeta(fileId);
		if (null != nvps) {
			for (NameValuePair nvp : nvps) {
//				System.out.println(nvp.getName() + ": " + nvp.getValue());
			}
		}
	}

}

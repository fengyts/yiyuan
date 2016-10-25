package ng.bayue.backend.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ng.bayue.backend.ao.FileUploadAO;
import ng.bayue.backend.util.ResultMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Controller
public class FileUploadController {
	
	private Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	private FileUploadAO fileUploadAO;
	
	@RequestMapping({"/file/upload"})
	@ResponseBody
	public ResultMessage fileUpload(HttpServletRequest request,MultipartFile part){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile multipartFile = null;
		for(Map.Entry<String, MultipartFile> entry : fileMap.entrySet()){
			multipartFile = entry.getValue();
		}
		
		try {
			InputStream fis = multipartFile.getInputStream();
			ResultMessage msg = fileUploadAO.uploadFile(fis);
			return msg;
		} catch (IOException e) {
			logger.error("", e);
		}
		return null;
		
	}

}

package ng.bayue.backend.controller.upload;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

@Controller
public class TestUpload {
	
	private final Logger logger = LoggerFactory.getLogger(TestUpload.class);
	
	@RequestMapping({"/upload"})
	public String upload(){
		return "/upload/upload";
	}
	
	@RequestMapping({"/addpicture"})
	public String addpicture(){
		return "/upload/add_picture";
	}
	
	@RequestMapping("/picture")
	public String upup(){
		return "/upload/picture";
	}
	
	@RequestMapping(value="/uploadItemFiles",method=RequestMethod.POST,produces="text/json")
	@ResponseBody
	public String uploadMultiFiles(HttpServletRequest request) {
		String savePath = request.getSession().getServletContext().getRealPath("/upload");
		if(StringUtils.isBlank(savePath)){
			logger.error("图片上传路径配置错误");
			return null;
		}
		//dfs返回的路径
		String dfsPath = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		//上传文件名称
		String fileName = null;
		JSONObject json = new JSONObject();
		for(Map.Entry<String, MultipartFile> entity : fileMap.entrySet()){
			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			long fileSize=mf.getSize();
			if (fileSize>2048000) {
//			if (fileSize>1024000) {
				json.put("code", "F_EXCEED_SIZE_LIMIT");
				json.put("msg", "文件大小超过限制！");
				logger.error("文件大小超过限制！");
				return json.toJSONString();
			}
			
			json.put("fileName", mf.getOriginalFilename());
			if (fileName.lastIndexOf(".") >= 0){
				fileName = UUID.randomUUID().toString() + "." + 
						fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
			}
			File file = new File(savePath, fileName);
			if(!file.exists()){
				Boolean b = file.mkdirs();
				if(!b) {
					logger.error("创建文件失败"+fileName);
				}
			}
			try{
				mf.transferTo(file);
//				dfsPath =  dfsAO.uploadPic(file);
			}catch(IOException e){
				fileName = null;
				logger.error("文件上传时保存出错！");
			}finally{
				FileUtils.deleteQuietly(file);
			}
		}
		fileName ="a.jpg";
		if(StringUtils.isBlank(fileName)){
			json.put("type", "error");
			json.put("errorCode", "uploadError");
		}else{
//			json.put("path", dfsDomainUtil.getSnapshotUrl(dfsPath, 80));
			json.put("path", "http://192.168.18.128/group1/M00/00/00/wKgSgFdNUu-APoloAAvqH_kipG8186.jpg");
			json.put("key","/a.jpg");
			json.put("type", "success");
		}
		return json.toJSONString();
	}
}

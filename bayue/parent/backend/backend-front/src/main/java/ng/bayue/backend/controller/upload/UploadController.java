package ng.bayue.backend.controller.upload;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import ng.bayue.backend.ao.utils.DfsAO;
import ng.bayue.fastdfs.ImageUrlUtil;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping({"/upload"})
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Value("${dfs.uploadTempPath}")
	private String uploadTempPath;
//	@Value("${item.picture.MaxSize}")
	@Value("#{meta['item.picture.MaxSize']}")
	private int itemPictureMaxSize;
	
//	@Value("${dfs.group1.host}")
//	private String host;
	
	@Autowired
	private DfsAO dfsAO;
	
	@Autowired
	private ImageUrlUtil imageUrlUtil;
	
	@RequestMapping({"/img/item"})
	@ResponseBody
	public String itemImgUpload(HttpServletRequest request){
//		System.out.println(itemPictureMaxSize1);
		String savePath = request.getSession().getServletContext().getRealPath(uploadTempPath);
		if(StringUtils.isEmpty(savePath)){
			logger.info("图片临时上传到本地时获取路径错误!");
			return null;
		}
		
		String dfsPath = null;
		String fileName = null;
		JSONObject obj = new JSONObject();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String,MultipartFile> map = multipartRequest.getFileMap();
		for(Map.Entry<String, MultipartFile> entry:map.entrySet()){
			MultipartFile mf = entry.getValue();
			fileName = mf.getOriginalFilename();
			int size = (int) mf.getSize();
			if(size > itemPictureMaxSize){
				obj.put("code", "F_EXCEED_SIZE_LIMIT");
				obj.put("msg", "文件大小超过限制！");
				logger.error("文件大小超过限制！");
				return obj.toJSONString();
			}
			obj.put("fileName", fileName);
			if (fileName.lastIndexOf(".") >= 0){
				fileName = UUID.randomUUID().toString() + "." + 
						fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
			}
			
			File file = new File(savePath, fileName);
			if(!file.exists()){
				Boolean b = file.mkdirs();
				if(!b) {
					logger.error("创建文件失败"+fileName);
					System.out.println("创建文件失败"+fileName);
				}
			}
			try {
				mf.transferTo(file);
				dfsPath =  dfsAO.uploadFile(file);
			} catch (IllegalStateException | IOException e1) {
//				fileName = null;
				logger.info("文件上传时保存出错:{}",e1);
			}finally{
				FileUtils.deleteQuietly(file);
			}
			
		}
		
		if(StringUtils.isBlank(fileName)){
			obj.put("type", "error");
			obj.put("errorCode", "uploadError");
		}else{
//			String wholePath = "http://"+host.substring(0, host.indexOf(":"))+"/"+dfsPath;
//			obj.put("path", wholePath);
//			obj.put("path", dfsPath);
			obj.put("path", imageUrlUtil.getFileFullUrl(dfsPath));
			obj.put("key",dfsPath);
			obj.put("type", "success");
		}
		return obj.toJSONString();
		
	}

}

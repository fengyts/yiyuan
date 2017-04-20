package ng.bayue.backend.ao.promotion;

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ng.bayue.backend.util.ResultMessage;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.common.Page;
import ng.bayue.fastdfs.DfsUtils;
import ng.bayue.fastdfs.ImageUrlUtil;
import ng.bayue.promotion.domain.TopicDO;
import ng.bayue.promotion.dto.TopicDTO;
import ng.bayue.promotion.service.TopicService;
import ng.bayue.service.DfsService;

@Service
public class TopicAO {

	private static final Logger logger = LoggerFactory.getLogger(TopicAO.class);
	
	@Value("${dfs.uploadTempPath}")
	private String uploadTempPath;
	
	@Autowired
	private TopicService topicService;
	@Autowired
	private DfsService dfsService;
	@Autowired
	private ImageUrlUtil imageUrlUtil;

	public Page<TopicDTO> queryPageList(TopicDO topicDO, Integer pageNo, Integer pageSize) {
		Page<TopicDTO> page = topicService.queryPageListByTopicDOAndStartPageSize(topicDO, pageNo, pageSize);
		return page;
	}

	public TopicDO selectById(Long topicId) {
		if (null == topicId) {
			return null;
		}
		TopicDO topicDO = topicService.selectById(topicId);
		String topicImage = topicDO.getImage();
		topicImage = imageUrlUtil.getFileFullUrl(topicImage);
		topicDO.setImage(topicImage);
		return topicDO;
	}

	public ResultMessage save(TopicDO topicDO, Map<String, MultipartFile> map) {
		Long userId = UserHandler.getUser().getId();
		Date date = new Date();
		topicDO.setCreateTime(date);
		topicDO.setCreateUserId(userId);
		topicDO.setModifyTime(date);
		topicDO.setModifyUserId(userId);
		
		// 上传图片
		if (null != map && !map.isEmpty()) {
			String image = uploadTopicImage(map);
			if (StringUtils.isEmpty(image)) {
				return new ResultMessage(ResultMessage.Failure, "上传图片失败");
			}
			topicDO.setImage(image);
		}
		
		Long id = topicService.insert(topicDO);
		if (id < 1) {
			return ResultMessage.serverInnerError();
		}
		return new ResultMessage();
	}

	public ResultMessage update(TopicDO topicDO, Map<String, MultipartFile> map) {
		topicDO.setModifyTime(new Date());
		topicDO.setModifyUserId(UserHandler.getUser().getId());

		if (null != map && !map.isEmpty()) {
			String image = uploadTopicImage(map);
			if (StringUtils.isEmpty(image)) {
				return new ResultMessage(ResultMessage.Failure, "上传图片失败");
			}
			topicDO.setImage(image);
		}

		int res = topicService.update(topicDO, false);
		if (res < 1) {
			return ResultMessage.serverInnerError();
		}
		return new ResultMessage();
	}

	private String uploadTopicImage(Map<String, MultipartFile> map) {
		String savePath = uploadTempPath;
		if(StringUtils.isEmpty(savePath)){
			logger.info("图片临时上传到本地时获取路径错误!");
			return null;
		}
		
		MultipartFile mf = null;
		String fileName = null;
		String dfsPath = null;
		for(Map.Entry<String, MultipartFile> entry : map.entrySet()){
			mf = entry.getValue();
			fileName = mf.getOriginalFilename();
			File file = new File(savePath, fileName);
			if(!file.exists()){
				Boolean b = file.mkdirs();
				if(!b) {
					logger.info("创建专题失败,上传专题图片异常,创建文件失败"+fileName);
					return null;
				}
			}
			try {
				mf.transferTo(file);
				dfsPath = dfsService.uploadFile1(file);
				return dfsPath;
			} catch (Exception e) {
				logger.info("创建专题失败,上传专题图片dfs异常:{}",e);
			} finally {
				FileUtils.deleteQuietly(file);
			}
		}
//		String image = "http://pic56.nipic.com/file/20141227/19674963_215052431000_2.jpg";
//		return image;
		return null;
	}
	
	

}

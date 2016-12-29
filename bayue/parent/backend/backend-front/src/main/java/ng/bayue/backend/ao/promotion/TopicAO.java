package ng.bayue.backend.ao.promotion;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ng.bayue.backend.util.ResultMessage;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.promotion.domain.TopicDO;
import ng.bayue.promotion.service.TopicService;
import ng.bayue.util.Page;

@Service
public class TopicAO {

	private static final Logger logger = LoggerFactory.getLogger(TopicAO.class);
	
	@Autowired
	private TopicService topicService;
	
	public Page<TopicDO> queryPageList(TopicDO topicDO, Integer pageNo, Integer pageSize){
		Page<TopicDO> page = topicService.queryPageListByTopicDOAndStartPageSize(topicDO, pageNo, pageSize);
		return page;
	}
	
	public TopicDO selectById(Long topicId){
		if(null == topicId){
			return null;
		}
		return topicService.selectById(topicId);
	}
	
	public ResultMessage save(TopicDO topicDO, Map<String, MultipartFile> map){
		Long userId = UserHandler.getUser().getId();
		Date date = new Date();
		topicDO.setCreateTime(date);
		topicDO.setCreateUserId(userId);
		topicDO.setModifyTime(date);
		topicDO.setModifyUserId(userId);
		if(null != map && !map.isEmpty()){
			String image = uploadTopicImage(map);
			if(StringUtils.isEmpty(image)){
				return new ResultMessage(ResultMessage.Failure, "上传图片失败");
			}
			topicDO.setImage(image);
		}
		Long id = topicService.insert(topicDO);
		if(id < 1){
			return ResultMessage.serverInnerError();
		}
		return new ResultMessage();
	}
	
	private String uploadTopicImage (Map<String, MultipartFile> map){
		Map.Entry<String, MultipartFile> entry = (Entry<String, MultipartFile>) map.entrySet();
		MultipartFile file = entry.getValue();
		return null;
	}
	
}

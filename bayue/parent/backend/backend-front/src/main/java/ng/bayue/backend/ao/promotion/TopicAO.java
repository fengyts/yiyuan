package ng.bayue.backend.ao.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}

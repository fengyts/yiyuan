package ng.bayue.backend.ao.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.promotion.domain.TopicItemDO;
import ng.bayue.promotion.service.TopicItemService;
import ng.bayue.util.Page;

@Service
public class TopicItemAO {
	
	private static final Logger logger = LoggerFactory.getLogger(TopicItemAO.class);
	
	@Autowired
	private TopicItemService topicItemService;
	
	public Page<TopicItemDO> queryTopicItemList(TopicItemDO topicItemDO, Integer pageNo, Integer pageSize){
		Page<TopicItemDO> pageData = topicItemService.queryPageListByTopicItemDOAndStartPageSize(topicItemDO, pageNo, pageSize);
		return pageData;
	}

}

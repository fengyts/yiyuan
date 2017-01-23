package ng.bayue.backend.ao.promotion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.backend.util.ResultMessage;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.promotion.domain.TopicItemDO;
import ng.bayue.promotion.dto.TopicItemDTO;
import ng.bayue.promotion.service.TopicItemService;
import ng.bayue.util.Page;

@Service
public class TopicItemAO {
	
	private static final Logger logger = LoggerFactory.getLogger(TopicItemAO.class);
	
	@Autowired
	private TopicItemService topicItemService;
	
	public Page<TopicItemDTO> queryTopicItemList(TopicItemDO topicItemDO, Integer pageNo, Integer pageSize){
		Page<TopicItemDTO> pageData = topicItemService.queryPageListByTopicItemDOAndStartPageSize(topicItemDO, pageNo, pageSize);
		return pageData;
	}
	
	public ResultMessage save(List<TopicItemDO> list){
		if(CollectionUtils.isEmpty(list)){
			return ResultMessage.validParameterNull("");
		}
		Date date = new Date();
		Long userId = UserHandler.getUser().getId();
		List<Long> detailIds = new ArrayList<Long>();
		for(TopicItemDO item : list){
			item.setCreateTime(date);
			item.setCreateUserId(userId);
			item.setModifyTime(date);
			item.setModifyUserId(userId);
			if(null == item.getIsHot()){
				item.setIsHot(false);
			}
			if(null == item.getSort()){
				item.setSort(0);
			}
			if(null == item.getHasInventory()){
				item.setHasInventory(true);
			}
			detailIds.add(item.getDetailId());
		}
		
		Long topicId = list.get(0).getTopicId();
		List<TopicItemDO> existList = topicItemService.existTopicItem(topicId, detailIds);
		if(CollectionUtils.isNotEmpty(existList)){
			return ResultMessage.validIsExist();
		}
		int res = topicItemService.insertBatch(list);
		if(res < 1){
			return ResultMessage.serverInnerError();
		}
		return new ResultMessage();
	}
	
	public TopicItemDTO selectTopicItemById(Long id){
		if(null == id){return null;}
		TopicItemDTO topicItemDO = topicItemService.selectById(id);
		return topicItemDO;
	}
	
	public ResultMessage update(List<TopicItemDO> list){
		if(CollectionUtils.isEmpty(list)){
			return ResultMessage.validParameterNull("");
		}
		Date date = new Date();
		Long userId = UserHandler.getUser().getId();
		for(TopicItemDO item : list){
			item.setModifyTime(date);
			item.setModifyUserId(userId);
		}
		int res = topicItemService.updateBatch(list);
		if(res < 1){
			return ResultMessage.serverInnerError();
		}
		return new ResultMessage();
	}

}

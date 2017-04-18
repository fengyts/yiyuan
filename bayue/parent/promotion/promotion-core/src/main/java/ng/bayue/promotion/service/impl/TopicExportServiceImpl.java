package ng.bayue.promotion.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.common.Page;
import ng.bayue.constant.CommonConstant;
import ng.bayue.promotion.domain.TopicDO;
import ng.bayue.promotion.domain.TopicItemDO;
import ng.bayue.promotion.dto.TopicDTO;
import ng.bayue.promotion.dto.TopicItemDTO;
import ng.bayue.promotion.enums.TopicProgressEnum;
import ng.bayue.promotion.exception.DAOException;
import ng.bayue.promotion.persist.dao.TopicDAO;
import ng.bayue.promotion.persist.dao.TopicItemDAO;
import ng.bayue.promotion.service.TopicExportService;
import ng.bayue.promotion.service.TopicItemService;
import ng.bayue.promotion.service.TopicService;

@Service(value = "topicExportService")
public class TopicExportServiceImpl implements TopicExportService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TopicDAO topicDAO;
	@Autowired
	private TopicItemDAO topicItemDAO;
	@Autowired
	private TopicService topicService;
	@Autowired
	private TopicItemService topicItemService;

	@Override
	public Page<TopicDTO> queryTopicListByPage(Integer pageNo, Integer pageSize) {
		TopicDO topicDO = new TopicDO();
		topicDO.setStatus(CommonConstant.STATUS.VALID);
		topicDO.setProgress(TopicProgressEnum.InProgress.getCode());
		return topicService.queryPageListByTopicDOAndStartPageSize(topicDO, pageNo, pageSize);
	}

	@Override
	public Page<TopicItemDTO> queryTopicItemByTopicId(Long topicId, Integer pageNo, Integer pageSize) {
		TopicItemDO topicItemDO = new TopicItemDO();
		topicItemDO.setTopicId(topicId);
		topicItemDO.setStatus(CommonConstant.STATUS.TRUE);
		return topicItemService.queryPageListByTopicItemDOAndStartPageSize(topicItemDO, pageNo, pageSize);
	}

	@Override
	public List<TopicItemDTO> listItemHot(Integer pageNo, Integer pageSize) {
		try {
			TopicItemDO topicItemDO = new TopicItemDO();
			topicItemDO.setStartPage(pageNo);
			topicItemDO.setPageSize(pageSize);
			topicItemDO.setIsHot(true);
			topicItemDO.setIsTest(false);
			topicItemDO.setStatus(CommonConstant.STATUS.TRUE);
			List<TopicItemDTO> result = topicItemDAO.selectDynamicPageQuery(topicItemDO);
			
			return result;
		} catch (DAOException e) {
			logger.error("", e);
		}
		return null;
	}

	@Override
	public Page<TopicItemDTO> queryPageListItemHot(Integer pageNo, Integer pageSize) {
		return null;
	}

}

package ng.bayue.promotion.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.common.Page;
import ng.bayue.constant.CommonConstant;
import ng.bayue.item.domain.ItemDetailDO;
import ng.bayue.item.domain.ItemPicturesDO;
import ng.bayue.item.service.ItemDetailService;
import ng.bayue.item.service.ItemPicturesService;
import ng.bayue.promotion.domain.TopicDO;
import ng.bayue.promotion.domain.TopicItemDO;
import ng.bayue.promotion.domain.TopicItemProgressDO;
import ng.bayue.promotion.dto.TopicDTO;
import ng.bayue.promotion.dto.TopicItemDTO;
import ng.bayue.promotion.enums.TopicProgressEnum;
import ng.bayue.promotion.exception.DAOException;
import ng.bayue.promotion.persist.dao.TopicDAO;
import ng.bayue.promotion.persist.dao.TopicItemDAO;
import ng.bayue.promotion.persist.dao.TopicItemProgressDAO;
import ng.bayue.promotion.service.TopicExportService;
import ng.bayue.promotion.service.TopicItemService;
import ng.bayue.promotion.service.TopicService;
import ng.bayue.util.MathUtils;

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
	@Autowired
	private TopicItemProgressDAO progressDAO;
	@Autowired
	private ItemDetailService itemDetailService;
	@Autowired
	private ItemPicturesService picturesService;

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
			packageTopicItemDTO(result);
			return result;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	@Override
	public Page<TopicItemDTO> queryPageListItemHot(Integer pageNo, Integer pageSize) {
		return null;
	}
	
	private void packageTopicItemDTO(List<TopicItemDTO> list){
		if(CollectionUtils.isEmpty(list)){
			return;
		}
		List<Long> topicItemIds = new ArrayList<Long>();
		List<Long> detailIds = new ArrayList<Long>();
		for(TopicItemDTO dto : list){
			topicItemIds.add(dto.getId());
			detailIds.add(dto.getDetailId());
		}
		List<TopicItemProgressDO> progressList = progressDAO.selectByIds(topicItemIds);
		List<ItemDetailDO> listDetails = itemDetailService.selectByIds(detailIds);
		List<ItemPicturesDO> listPictures = picturesService.selectByDetailIds(detailIds);
		if(CollectionUtils.isEmpty(progressList) || CollectionUtils.isEmpty(listDetails) || CollectionUtils.isEmpty(listPictures)){
			return ;
		}
		for(TopicItemDTO dto : list){
			long id = dto.getId();
			for(TopicItemProgressDO progress : progressList){
				long id1 = progress.getTopicItemId();
				if(id == id1){
					dto.setCurrentParticipant(progress.getCurrentParticipant());
					dto.setRatio(MathUtils.formatNum(MathUtils.divide(progress.getCurrentParticipant(), dto.getSnatchNumber(), true, 2).doubleValue(), 
							MathUtils.FORMAT_PERCENT_SCALE));
					break;
				}
			}
			
			for(ItemDetailDO detail : listDetails){
				long id1 = detail.getId();
				if(id == id1){
					dto.setItemTitle(detail.getItemTitle());
					dto.setMainTitle(detail.getMainTitle());
					break;
				}
			}
			
			for(ItemPicturesDO picture : listPictures){
				long id1 = picture.getDetailId();
				if(id == id1){
					dto.setPicture(picture.getPicture());
					break;
				}
			}
			
		}
		
		
		
	}

}

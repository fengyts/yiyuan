package ng.bayue.promotion.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.promotion.domain.TopicItemDO;
import ng.bayue.promotion.dto.TopicItemDTO;
import ng.bayue.promotion.exception.DAOException;
import ng.bayue.promotion.exception.ServiceException;
import ng.bayue.promotion.persist.dao.TopicItemDAO;
import ng.bayue.promotion.service.TopicItemService;
import ng.bayue.util.Page;

@Service(value = "topicItemService")
public class TopicItemServiceImpl implements TopicItemService {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private TopicItemDAO topicItemDAO;

	@Override
	public Long insert(TopicItemDO topicItemDO) throws ServiceException {
		try {
			return topicItemDAO.insert(topicItemDO);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	// @Override
	// public int updateById(TopicItemDO topicItemDO) throws ServiceException {
	// try {
	// return (Integer) topicItemDAO.updateById(topicItemDO);
	// }catch(DAOException e){
	// logger.error(e);
	// throw new ServiceException(e);
	// }
	// }

	@Override
	public int update(TopicItemDO topicItemDO, boolean isAllField) throws ServiceException {
		try {
			if (isAllField) {
				return (Integer) topicItemDAO.update(topicItemDO);
			} else {
				return (Integer) topicItemDAO.updateDynamic(topicItemDO);
			}
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) topicItemDAO.deleteById(id);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	// @Override
	// public int updateDynamic(TopicItemDO topicItemDO) throws ServiceException
	// {
	// try {
	// return (Integer) topicItemDAO.updateDynamic(topicItemDO);
	// }catch(DAOException e){
	// logger.error(e);
	// throw new ServiceException(e);
	// }
	// }

	@Override
	public TopicItemDTO selectById(Long id) throws ServiceException {
		try {
			return topicItemDAO.selectById(id);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(TopicItemDO topicItemDO) throws ServiceException {
		try {
			return topicItemDAO.selectCountDynamic(topicItemDO);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<TopicItemDTO> selectDynamic(TopicItemDO topicItemDO) throws ServiceException {
		try {
			return topicItemDAO.selectDynamic(topicItemDO);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	private List<TopicItemDTO> selectDynamicPageQuery(TopicItemDO topicItemDO) throws ServiceException {
		try {
			return topicItemDAO.selectDynamicPageQuery(topicItemDO);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	public Page<TopicItemDTO> queryPageListByTopicItemDO(TopicItemDO topicItemDO) {
		if (topicItemDO != null) {
			Long totalCount = this.selectCountDynamic(topicItemDO);
			List<TopicItemDTO> resultList = this.selectDynamicPageQuery(topicItemDO);

			Page<TopicItemDTO> page = new Page<TopicItemDTO>();
			page.setPageNo(topicItemDO.getStartPage());
			page.setPageSize(topicItemDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<TopicItemDTO>();
	}

	public Page<TopicItemDTO> queryPageListByTopicItemDOAndStartPageSize(TopicItemDO topicItemDO, int startPage,
			int pageSize) {
		if (topicItemDO != null && startPage > 0 && pageSize > 0) {
			topicItemDO.setStartPage(startPage);
			topicItemDO.setPageSize(pageSize);
			return this.queryPageListByTopicItemDO(topicItemDO);
		}
		return new Page<TopicItemDTO>();
	}

	@Override
	public int insertBatch(List<TopicItemDO> list) throws ServiceException {
		if (CollectionUtils.isEmpty(list)) {
			return -1;
		}
		try {
			return topicItemDAO.insertBatch(list);
		} catch (DAOException e) {
			logger.error("", e);
		}
		return -1;
	}

	@Override
	public List<TopicItemDO> existTopicItem(Long topicId, List<Long> detailIds) throws ServiceException {
		if (null == topicId || CollectionUtils.isEmpty(detailIds)) {
			return null;
		}
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("topicId", topicId);
			param.put("detailIds", detailIds);
			return topicItemDAO.existTopicItem(param);
		} catch (DAOException e) {
			logger.error("", e);
		}
		return null;
	}

	@Override
	public List<TopicItemDO> validItemStatus(List<Long> detailIds) throws ServiceException {
		if (CollectionUtils.isEmpty(detailIds)) {
			return null;
		}
		try {
			return topicItemDAO.validItemStatus(detailIds);
		} catch (DAOException e) {
			logger.error("", e);
		}
		return null;
	}

	@Override
	public int updateBatch(List<TopicItemDO> list) throws ServiceException {
		if(CollectionUtils.isEmpty(list)){
			return -1;
		}
		int res = -1;
		try {
			res = topicItemDAO.updateBatch(list);
		} catch (DAOException e) {
			logger.error("", e);
		}
		return res;
	}

}

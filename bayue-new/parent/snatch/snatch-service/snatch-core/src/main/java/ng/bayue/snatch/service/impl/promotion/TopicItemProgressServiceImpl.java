package ng.bayue.snatch.service.impl.promotion;

import java.util.List;

import ng.bayue.common.Page;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.snatch.domain.promotion.TopicItemProgressDO;
import ng.bayue.snatch.exception.ServiceException;
import ng.bayue.snatch.persist.dao.promotion.TopicItemProgressDAO;
import ng.bayue.snatch.service.promotion.TopicItemProgressService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "topicItemProgressService")
public class TopicItemProgressServiceImpl implements TopicItemProgressService {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private TopicItemProgressDAO topicItemProgressDAO;

	@Override
	public Long insert(TopicItemProgressDO topicItemProgressDO) throws ServiceException {
		try {
			return topicItemProgressDAO.insert(topicItemProgressDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	// @Override
	// public int updateById(TopicItemProgressDO topicItemProgressDO) throws
	// ServiceException {
	// try {
	// return (Integer) topicItemProgressDAO.updateById(topicItemProgressDO);
	// }catch(DAOException e){
	// logger.error(e);
	// throw new ServiceException(e);
	// }
	// }

	@Override
	public int update(TopicItemProgressDO topicItemProgressDO, boolean isAllField) throws ServiceException {
		try {
			if (isAllField) {
				return (Integer) topicItemProgressDAO.update(topicItemProgressDO);
			} else {
				return (Integer) topicItemProgressDAO.updateDynamic(topicItemProgressDO);
			}
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) topicItemProgressDAO.deleteById(id);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	// @Override
	// public int updateDynamic(TopicItemProgressDO topicItemProgressDO) throws
	// ServiceException {
	// try {
	// return (Integer) topicItemProgressDAO.updateDynamic(topicItemProgressDO);
	// }catch(DAOException e){
	// logger.error(e);
	// throw new ServiceException(e);
	// }
	// }

	@Override
	public TopicItemProgressDO selectById(Long id) throws ServiceException {
		try {
			return topicItemProgressDAO.selectById(id);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(TopicItemProgressDO topicItemProgressDO) throws ServiceException {
		try {
			return topicItemProgressDAO.selectCountDynamic(topicItemProgressDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<TopicItemProgressDO> selectDynamic(TopicItemProgressDO topicItemProgressDO) throws ServiceException {
		try {
			return topicItemProgressDAO.selectDynamic(topicItemProgressDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	private List<TopicItemProgressDO> selectDynamicPageQuery(TopicItemProgressDO topicItemProgressDO)
			throws ServiceException {
		try {
			return topicItemProgressDAO.selectDynamicPageQuery(topicItemProgressDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Page<TopicItemProgressDO> queryPageListDynamic(TopicItemProgressDO topicItemProgressDO)
			throws ServiceException {
		if (topicItemProgressDO != null) {
			Long totalCount = this.selectCountDynamic(topicItemProgressDO);

			Page<TopicItemProgressDO> page = new Page<TopicItemProgressDO>();
			page.setPageNo(topicItemProgressDO.getStartPage());
			page.setPageSize(topicItemProgressDO.getPageSize());
			page.setTotalCount(totalCount.intValue());

			if (null != totalCount && totalCount.longValue() > 0) {
				List<TopicItemProgressDO> resultList = this.selectDynamicPageQuery(topicItemProgressDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<TopicItemProgressDO>();
	}

	@Override
	public Page<TopicItemProgressDO> queryPageListDynamicAndStartPageSize(TopicItemProgressDO topicItemProgressDO,
			Integer startPage, Integer pageSize) throws ServiceException {
		if (topicItemProgressDO != null && startPage > 0 && pageSize > 0) {
			topicItemProgressDO.setStartPage(startPage);
			topicItemProgressDO.setPageSize(pageSize);
			return this.queryPageListDynamic(topicItemProgressDO);
		}
		return new Page<TopicItemProgressDO>();
	}

	@Override
	public int updateByTopicItemId(TopicItemProgressDO itemProgressDO) throws ServiceException {
		try {
			return topicItemProgressDAO.updateByTopicItemId(itemProgressDO);
		} catch (CommonDAOException e) {
			logger.error("", e);
		}
		return -1;
	}

	@Override
	public List<TopicItemProgressDO> selectByTopicItemIds(List<Long> topicItemIds) {
		if (CollectionUtils.isEmpty(topicItemIds)) {
			return null;
		}
		return topicItemProgressDAO.selectByIds(topicItemIds);
	}

}

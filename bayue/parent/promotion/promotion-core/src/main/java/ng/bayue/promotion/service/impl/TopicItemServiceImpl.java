package ng.bayue.promotion.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.promotion.domain.TopicItemDO;
import ng.bayue.promotion.exception.DAOException;
import ng.bayue.promotion.exception.ServiceException;
import ng.bayue.promotion.persist.dao.TopicItemDAO;
import ng.bayue.promotion.service.TopicItemService;
import ng.bayue.util.Page;

@Service(value="topicItemService")
public class TopicItemServiceImpl  implements TopicItemService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private TopicItemDAO topicItemDAO;

	@Override
	public Long insert(TopicItemDO topicItemDO) throws ServiceException {
		try {
			return topicItemDAO.insert(topicItemDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(TopicItemDO topicItemDO) throws ServiceException {
//		try {
//			return (Integer) topicItemDAO.updateById(topicItemDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(TopicItemDO topicItemDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) topicItemDAO.update(topicItemDO);
			}else{
				return (Integer) topicItemDAO.updateDynamic(topicItemDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) topicItemDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(TopicItemDO topicItemDO) throws ServiceException {
//		try {
//			return (Integer) topicItemDAO.updateDynamic(topicItemDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public TopicItemDO selectById(Long id) throws ServiceException {
		try {
			return topicItemDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(TopicItemDO topicItemDO) throws ServiceException {
		try {
			return topicItemDAO.selectCountDynamic(topicItemDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<TopicItemDO> selectDynamic(TopicItemDO topicItemDO) throws ServiceException {
		try {
			return topicItemDAO.selectDynamic(topicItemDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<TopicItemDO> selectDynamicPageQuery(TopicItemDO topicItemDO) throws ServiceException {
		try {
			return topicItemDAO.selectDynamicPageQuery(topicItemDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<TopicItemDO> queryPageListByTopicItemDO(TopicItemDO topicItemDO) {
		if (topicItemDO != null) {
			Long totalCount = this.selectCountDynamic(topicItemDO);
			List<TopicItemDO> resultList = this.selectDynamicPageQuery(topicItemDO);

			Page<TopicItemDO> page = new Page<TopicItemDO>();
			page.setPageNo(topicItemDO.getStartPage());
			page.setPageSize(topicItemDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<TopicItemDO>();
	}

	public Page<TopicItemDO> queryPageListByTopicItemDOAndStartPageSize(TopicItemDO topicItemDO,int startPage,int pageSize){
		if (topicItemDO != null && startPage>0 && pageSize>0) {
			topicItemDO.setStartPage(startPage);
			topicItemDO.setPageSize(pageSize);
			return this.queryPageListByTopicItemDO(topicItemDO);
		}
		return new Page<TopicItemDO>();
	}

}

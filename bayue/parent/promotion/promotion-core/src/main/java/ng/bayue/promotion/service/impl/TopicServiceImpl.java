package ng.bayue.promotion.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.promotion.domain.TopicDO;
import ng.bayue.promotion.exception.DAOException;
import ng.bayue.promotion.exception.ServiceException;
import ng.bayue.promotion.persist.dao.TopicDAO;
import ng.bayue.promotion.service.TopicService;
import ng.bayue.util.Page;

@Service(value="topicService")
public class TopicServiceImpl  implements TopicService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private TopicDAO topicDAO;

	@Override
	public Long insert(TopicDO topicDO) throws ServiceException {
		try {
			return topicDAO.insert(topicDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(TopicDO topicDO) throws ServiceException {
//		try {
//			return (Integer) topicDAO.updateById(topicDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(TopicDO topicDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) topicDAO.update(topicDO);
			}else{
				return (Integer) topicDAO.updateDynamic(topicDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) topicDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(TopicDO topicDO) throws ServiceException {
//		try {
//			return (Integer) topicDAO.updateDynamic(topicDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public TopicDO selectById(Long id) throws ServiceException {
		try {
			return topicDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(TopicDO topicDO) throws ServiceException {
		try {
			return topicDAO.selectCountDynamic(topicDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<TopicDO> selectDynamic(TopicDO topicDO) throws ServiceException {
		try {
			return topicDAO.selectDynamic(topicDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<TopicDO> selectDynamicPageQuery(TopicDO topicDO) throws ServiceException {
		try {
			return topicDAO.selectDynamicPageQuery(topicDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<TopicDO> queryPageListByTopicDO(TopicDO topicDO) {
		if (topicDO != null) {
			Long totalCount = this.selectCountDynamic(topicDO);
			List<TopicDO> resultList = this.selectDynamicPageQuery(topicDO);

			Page<TopicDO> page = new Page<TopicDO>();
			page.setPageNo(topicDO.getStartPage());
			page.setPageSize(topicDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<TopicDO>();
	}

	public Page<TopicDO> queryPageListByTopicDOAndStartPageSize(TopicDO topicDO,int startPage,int pageSize){
		if (topicDO != null && startPage>0 && pageSize>0) {
			topicDO.setStartPage(startPage);
			topicDO.setPageSize(pageSize);
			return this.queryPageListByTopicDO(topicDO);
		}
		return new Page<TopicDO>();
	}

}

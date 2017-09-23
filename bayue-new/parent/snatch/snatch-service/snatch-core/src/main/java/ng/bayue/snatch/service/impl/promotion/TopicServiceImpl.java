package ng.bayue.snatch.service.impl.promotion;

import java.util.List;

import ng.bayue.common.Page;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.snatch.domain.promotion.TopicDO;
import ng.bayue.snatch.dto.promotion.TopicDTO;
import ng.bayue.snatch.exception.ServiceException;
import ng.bayue.snatch.persist.dao.promotion.TopicDAO;
import ng.bayue.snatch.service.promotion.TopicService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="topicService")
public class TopicServiceImpl  implements TopicService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private TopicDAO topicDAO;

	@Override
	public Long insert(TopicDO topicDO) throws ServiceException {
		try {
			return topicDAO.insert(topicDO);
		}catch(CommonDAOException e){
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
		}catch(CommonDAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) topicDAO.deleteById(id);
		}catch(CommonDAOException e){
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
	public TopicDTO selectById(Long id) throws ServiceException {
		try {
			return topicDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(TopicDO topicDO) throws ServiceException {
		try {
			return topicDAO.selectCountDynamic(topicDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<TopicDTO> selectDynamic(TopicDO topicDO) throws ServiceException {
		try {
			return topicDAO.selectDynamic(topicDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<TopicDTO> selectDynamicPageQuery(TopicDO topicDO) throws ServiceException {
		try {
			return topicDAO.selectDynamicPageQuery(topicDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<TopicDTO> queryPageListByTopicDO(TopicDO topicDO) {
		if (topicDO != null) {
			Long totalCount = this.selectCountDynamic(topicDO);
			List<TopicDTO> resultList = this.selectDynamicPageQuery(topicDO);

			Page<TopicDTO> page = new Page<TopicDTO>();
			page.setPageNo(topicDO.getStartPage());
			page.setPageSize(topicDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<TopicDTO>();
	}

	public Page<TopicDTO> queryPageListByTopicDOAndStartPageSize(TopicDO topicDO,int startPage,int pageSize){
		if (topicDO != null && startPage>0 && pageSize>0) {
			topicDO.setStartPage(startPage);
			topicDO.setPageSize(pageSize);
			return this.queryPageListByTopicDO(topicDO);
		}
		return new Page<TopicDTO>();
	}

}

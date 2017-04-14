package ng.bayue.base.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ng.bayue.base.domain.FrontCategoryLinkDO;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.base.persist.dao.FrontCategoryLinkDAO;
import ng.bayue.base.service.FrontCategoryLinkService;
import ng.bayue.exception.DAOException;
import ng.bayue.common.Page;

@Service(value="frontCategoryLinkService")
public class FrontCategoryLinkServiceImpl  implements FrontCategoryLinkService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private FrontCategoryLinkDAO frontCategoryLinkDAO;

	@Override
	public Long insert(FrontCategoryLinkDO frontCategoryLinkDO) throws ServiceException {
		try {
			return frontCategoryLinkDAO.insert(frontCategoryLinkDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(FrontCategoryLinkDO frontCategoryLinkDO) throws ServiceException {
//		try {
//			return (Integer) frontCategoryLinkDAO.updateById(frontCategoryLinkDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(FrontCategoryLinkDO frontCategoryLinkDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) frontCategoryLinkDAO.update(frontCategoryLinkDO);
			}else{
				return (Integer) frontCategoryLinkDAO.updateDynamic(frontCategoryLinkDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) frontCategoryLinkDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(FrontCategoryLinkDO frontCategoryLinkDO) throws ServiceException {
//		try {
//			return (Integer) frontCategoryLinkDAO.updateDynamic(frontCategoryLinkDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public FrontCategoryLinkDO selectById(Long id) throws ServiceException {
		try {
			return frontCategoryLinkDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(FrontCategoryLinkDO frontCategoryLinkDO) throws ServiceException {
		try {
			return frontCategoryLinkDAO.selectCountDynamic(frontCategoryLinkDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<FrontCategoryLinkDO> selectDynamic(FrontCategoryLinkDO frontCategoryLinkDO) throws ServiceException {
		try {
			return frontCategoryLinkDAO.selectDynamic(frontCategoryLinkDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<FrontCategoryLinkDO> selectDynamicPageQuery(FrontCategoryLinkDO frontCategoryLinkDO) throws ServiceException {
		try {
			return frontCategoryLinkDAO.selectDynamicPageQuery(frontCategoryLinkDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	
	@Override
	public Page<FrontCategoryLinkDO> queryPageListDynamic(FrontCategoryLinkDO frontCategoryLinkDO) throws ServiceException{
		if (frontCategoryLinkDO != null) {
			Long totalCount = this.selectCountDynamic(frontCategoryLinkDO);

			Page<FrontCategoryLinkDO> page = new Page<FrontCategoryLinkDO>();
			page.setPageNo(frontCategoryLinkDO.getStartPage());
			page.setPageSize(frontCategoryLinkDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<FrontCategoryLinkDO> resultList = this.selectDynamicPageQuery(frontCategoryLinkDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<FrontCategoryLinkDO>();
	}
	
	@Override
	public Page<FrontCategoryLinkDO> queryPageListDynamicAndStartPageSize(FrontCategoryLinkDO frontCategoryLinkDO, Integer startPage, Integer pageSize) throws ServiceException {
		if (frontCategoryLinkDO != null && startPage>0 && pageSize>0) {
			frontCategoryLinkDO.setStartPage(startPage);
			frontCategoryLinkDO.setPageSize(pageSize);
			return this.queryPageListDynamic(frontCategoryLinkDO);
		}
		return new Page<FrontCategoryLinkDO>();
	}

	@Override
	public int insertBatch(List<FrontCategoryLinkDO> list) {
		if(CollectionUtils.isEmpty(list)){
			return -1;
		}
		return frontCategoryLinkDAO.insertBatch(list);
	}
	
	
}

package ng.bayue.base.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.base.domain.FrontCategoryDO;
import ng.bayue.base.persist.dao.FrontCategoryDAO;
import ng.bayue.base.service.FrontCategoryService;
import ng.bayue.common.Page;
import ng.bayue.exception.DAOException;
import ng.bayue.exception.ServiceException;

@Service(value="frontCategoryService")
public class FrontCategoryServiceImpl  implements FrontCategoryService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private FrontCategoryDAO frontCategoryDAO;

	@Override
	public Long insert(FrontCategoryDO frontCategoryDO) throws ServiceException {
		try {
			return frontCategoryDAO.insert(frontCategoryDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(FrontCategoryDO frontCategoryDO) throws ServiceException {
//		try {
//			return (Integer) frontCategoryDAO.updateById(frontCategoryDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(FrontCategoryDO frontCategoryDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) frontCategoryDAO.update(frontCategoryDO);
			}else{
				return (Integer) frontCategoryDAO.updateDynamic(frontCategoryDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) frontCategoryDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(FrontCategoryDO frontCategoryDO) throws ServiceException {
//		try {
//			return (Integer) frontCategoryDAO.updateDynamic(frontCategoryDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public FrontCategoryDO selectById(Long id) throws ServiceException {
		try {
			return frontCategoryDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(FrontCategoryDO frontCategoryDO) throws ServiceException {
		try {
			return frontCategoryDAO.selectCountDynamic(frontCategoryDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<FrontCategoryDO> selectDynamic(FrontCategoryDO frontCategoryDO) throws ServiceException {
		try {
			return frontCategoryDAO.selectDynamic(frontCategoryDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<FrontCategoryDO> selectDynamicPageQuery(FrontCategoryDO frontCategoryDO) throws ServiceException {
		try {
			return frontCategoryDAO.selectDynamicPageQuery(frontCategoryDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	
	@Override
	public Page<FrontCategoryDO> queryPageListDynamic(FrontCategoryDO frontCategoryDO) throws ServiceException{
		if (frontCategoryDO != null) {
			Long totalCount = this.selectCountDynamic(frontCategoryDO);

			Page<FrontCategoryDO> page = new Page<FrontCategoryDO>();
			page.setPageNo(frontCategoryDO.getStartPage());
			page.setPageSize(frontCategoryDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<FrontCategoryDO> resultList = this.selectDynamicPageQuery(frontCategoryDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<FrontCategoryDO>();
	}
	
	@Override
	public Page<FrontCategoryDO> queryPageListDynamicAndStartPageSize(FrontCategoryDO frontCategoryDO, Integer startPage, Integer pageSize) throws ServiceException {
		if (frontCategoryDO != null && startPage>0 && pageSize>0) {
			frontCategoryDO.setStartPage(startPage);
			frontCategoryDO.setPageSize(pageSize);
			return this.queryPageListDynamic(frontCategoryDO);
		}
		return new Page<FrontCategoryDO>();
	}

	@Override
	public int insertBatch(List<FrontCategoryDO> list) {
		if(CollectionUtils.isEmpty(list)){
			return -1;
		}
//		return frontCategoryDAO.insertBatch(list);
		for(FrontCategoryDO fc : list){
			try {
				insert(fc);
			} catch (ServiceException e) {
				logger.error("", e);
			}
		}
		return -1;
	}
	
	
}

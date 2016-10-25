package ng.bayue.base.service.impl;

import java.util.List;

import ng.bayue.base.domain.DictionaryDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.base.persist.dao.DictionaryDAO;
import ng.bayue.base.service.DictionaryService;
import ng.bayue.util.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="dictionaryService")
public class DictionaryServiceImpl  implements DictionaryService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private DictionaryDAO dictionaryDAO;

	@Override
	public Long insert(DictionaryDO dictionaryDO) throws ServiceException {
		try {
			return dictionaryDAO.insert(dictionaryDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(DictionaryDO dictionaryDO) throws ServiceException {
//		try {
//			return (Integer) dictionaryDAO.updateById(dictionaryDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(DictionaryDO dictionaryDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) dictionaryDAO.update(dictionaryDO);
			}else{
				return (Integer) dictionaryDAO.updateDynamic(dictionaryDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) dictionaryDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(DictionaryDO dictionaryDO) throws ServiceException {
//		try {
//			return (Integer) dictionaryDAO.updateDynamic(dictionaryDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public DictionaryDO selectById(Long id) throws ServiceException {
		try {
			return dictionaryDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(DictionaryDO dictionaryDO) throws ServiceException {
		try {
			return dictionaryDAO.selectCountDynamic(dictionaryDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<DictionaryDO> selectDynamic(DictionaryDO dictionaryDO) throws ServiceException {
		try {
			return dictionaryDAO.selectDynamic(dictionaryDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<DictionaryDO> selectDynamicPageQuery(DictionaryDO dictionaryDO) throws ServiceException {
		try {
			return dictionaryDAO.selectDynamicPageQuery(dictionaryDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<DictionaryDO> queryPageListByDictionaryDO(DictionaryDO dictionaryDO) {
		if (dictionaryDO != null) {
			Long totalCount = this.selectCountDynamic(dictionaryDO);
			List<DictionaryDO> resultList = this.selectDynamicPageQuery(dictionaryDO);

			Page<DictionaryDO> page = new Page<DictionaryDO>();
			page.setPageNo(dictionaryDO.getStartPage());
			page.setPageSize(dictionaryDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<DictionaryDO>();
	}

	public Page<DictionaryDO> queryPageListByDictionaryDOAndStartPageSize(DictionaryDO dictionaryDO,int startPage,int pageSize){
		if (dictionaryDO != null && startPage>0 && pageSize>0) {
			dictionaryDO.setStartPage(startPage);
			dictionaryDO.setPageSize(pageSize);
			return this.queryPageListByDictionaryDO(dictionaryDO);
		}
		return new Page<DictionaryDO>();
	}

	@Override
	public List<DictionaryDO> listAllCode() {
		return dictionaryDAO.listAllCode();
	}

	@Override
	public List<DictionaryDO> selectByIds(List<Long> ids) {
		if(CollectionUtils.isEmpty(ids)){
			return null;
		}
		return dictionaryDAO.selectByIds(ids);
	}
	

}

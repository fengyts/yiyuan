package ng.bayue.base.service.impl;

import java.util.List;

import ng.bayue.base.domain.SpecDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.base.persist.dao.SpecDAO;
import ng.bayue.base.service.SpecService;
import ng.bayue.util.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="specService")
public class SpecServiceImpl  implements SpecService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SpecDAO specDAO;

	@Override
	public Long insert(SpecDO specDO) throws ServiceException {
		try {
			return specDAO.insert(specDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(SpecDO specDO) throws ServiceException {
//		try {
//			return (Integer) specDAO.updateById(specDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(SpecDO specDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) specDAO.update(specDO);
			}else{
				return (Integer) specDAO.updateDynamic(specDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) specDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(SpecDO specDO) throws ServiceException {
//		try {
//			return (Integer) specDAO.updateDynamic(specDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public SpecDO selectById(Long id) throws ServiceException {
		try {
			return specDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(SpecDO specDO) throws ServiceException {
		try {
			return specDAO.selectCountDynamic(specDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<SpecDO> selectDynamic(SpecDO specDO) throws ServiceException {
		try {
			return specDAO.selectDynamic(specDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<SpecDO> selectDynamicPageQuery(SpecDO specDO) throws ServiceException {
		try {
			return specDAO.selectDynamicPageQuery(specDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	
	@Override
	public Page<SpecDO> queryPageListBySpecDO(SpecDO specDO) {
		if (specDO != null) {
			Long totalCount = this.selectCountDynamic(specDO);
			List<SpecDO> resultList = this.selectDynamicPageQuery(specDO);

			Page<SpecDO> page = new Page<SpecDO>();
			page.setPageNo(specDO.getStartPage());
			page.setPageSize(specDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<SpecDO>();
	}
	
	@Override
	public Page<SpecDO> queryPageListBySpecDOAndStartPageSize(SpecDO specDO,int startPage,int pageSize){
		if (specDO != null && startPage>0 && pageSize>0) {
			specDO.setStartPage(startPage);
			specDO.setPageSize(pageSize);
			return this.queryPageListBySpecDO(specDO);
		}
		return new Page<SpecDO>();
	}

	@Override
	public List<SpecDO> selectByIds(List<Long> ids) {
		if(CollectionUtils.isEmpty(ids)){
			logger.info("根据规格id列表查询规格信息入参为空!");
			return null;
		}
		try {
			List<SpecDO> list = specDAO.selectByIds(ids);
			return list;
		} catch (DAOException e) {
			logger.error("根据规格id列表查询规格信息异常:{}", e);
		}
		return null;
	}

}

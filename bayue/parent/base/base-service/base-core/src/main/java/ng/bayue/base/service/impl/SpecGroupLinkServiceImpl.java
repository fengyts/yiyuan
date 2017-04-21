package ng.bayue.base.service.impl;

import java.util.List;

import ng.bayue.base.domain.SpecGroupLinkDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.base.persist.dao.SpecGroupLinkDAO;
import ng.bayue.base.service.SpecGroupLinkService;
import ng.bayue.common.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="specGroupLinkService")
public class SpecGroupLinkServiceImpl  implements SpecGroupLinkService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SpecGroupLinkDAO specGroupLinkDAO;

	@Override
	public Long insert(SpecGroupLinkDO specGroupLinkDO) throws ServiceException {
		try {
			return specGroupLinkDAO.insert(specGroupLinkDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(SpecGroupLinkDO specGroupLinkDO) throws ServiceException {
//		try {
//			return (Integer) specGroupLinkDAO.updateById(specGroupLinkDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(SpecGroupLinkDO specGroupLinkDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) specGroupLinkDAO.update(specGroupLinkDO);
			}else{
				return (Integer) specGroupLinkDAO.updateDynamic(specGroupLinkDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) specGroupLinkDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(SpecGroupLinkDO specGroupLinkDO) throws ServiceException {
//		try {
//			return (Integer) specGroupLinkDAO.updateDynamic(specGroupLinkDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public SpecGroupLinkDO selectById(Long id) throws ServiceException {
		try {
			return specGroupLinkDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(SpecGroupLinkDO specGroupLinkDO) throws ServiceException {
		try {
			return specGroupLinkDAO.selectCountDynamic(specGroupLinkDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<SpecGroupLinkDO> selectDynamic(SpecGroupLinkDO specGroupLinkDO) throws ServiceException {
		try {
			return specGroupLinkDAO.selectDynamic(specGroupLinkDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<SpecGroupLinkDO> selectDynamicPageQuery(SpecGroupLinkDO specGroupLinkDO) throws ServiceException {
		try {
			return specGroupLinkDAO.selectDynamicPageQuery(specGroupLinkDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	
	@Override
	public Page<SpecGroupLinkDO> queryPageListBySpecGroupLinkDO(SpecGroupLinkDO specGroupLinkDO) {
		if (specGroupLinkDO != null) {
			Long totalCount = this.selectCountDynamic(specGroupLinkDO);
			List<SpecGroupLinkDO> resultList = this.selectDynamicPageQuery(specGroupLinkDO);

			Page<SpecGroupLinkDO> page = new Page<SpecGroupLinkDO>();
			page.setPageNo(specGroupLinkDO.getStartPage());
			page.setPageSize(specGroupLinkDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<SpecGroupLinkDO>();
	}
	
	@Override
	public Page<SpecGroupLinkDO> queryPageListBySpecGroupLinkDOAndStartPageSize(SpecGroupLinkDO specGroupLinkDO,int startPage,int pageSize){
		if (specGroupLinkDO != null && startPage>0 && pageSize>0) {
			specGroupLinkDO.setStartPage(startPage);
			specGroupLinkDO.setPageSize(pageSize);
			return this.queryPageListBySpecGroupLinkDO(specGroupLinkDO);
		}
		return new Page<SpecGroupLinkDO>();
	}
	
	@Override
	public void insertBatch(List<SpecGroupLinkDO> list){
		if(CollectionUtils.isEmpty(list)){
			return;
		}
		try {
			specGroupLinkDAO.insertBatch(list);
		} catch (DAOException e) {
			logger.error("批量插入规格关联关系异常:{}", e);
		}
		
	}

	@Override
	public int updateBatch(List<SpecGroupLinkDO> list) throws ServiceException {
		if(CollectionUtils.isEmpty(list)){
			return -1;
		}
		try {
			return specGroupLinkDAO.updateBatch(list);
		} catch (Exception e) {
			logger.info("批量更新规格关联信息异常:{}", e);
		}
		return -1;
	}

}

package ng.bayue.base.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ng.bayue.base.domain.SpecGroupDO;
import ng.bayue.base.domain.SpecGroupLinkDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.base.persist.dao.SpecGroupDAO;
import ng.bayue.base.service.SpecGroupLinkService;
import ng.bayue.base.service.SpecGroupService;
import ng.bayue.util.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="specGroupService")
public class SpecGroupServiceImpl  implements SpecGroupService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SpecGroupDAO specGroupDAO;
	
	@Autowired
	private SpecGroupLinkService specGroupLinkService;

	@Override
	public Long insert(SpecGroupDO specGroupDO) throws ServiceException {
		try {
			return specGroupDAO.insert(specGroupDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(SpecGroupDO specGroupDO) throws ServiceException {
//		try {
//			return (Integer) specGroupDAO.updateById(specGroupDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(SpecGroupDO specGroupDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) specGroupDAO.update(specGroupDO);
			}else{
				return (Integer) specGroupDAO.updateDynamic(specGroupDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) specGroupDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(SpecGroupDO specGroupDO) throws ServiceException {
//		try {
//			return (Integer) specGroupDAO.updateDynamic(specGroupDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public SpecGroupDO selectById(Long id) throws ServiceException {
		try {
			return specGroupDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(SpecGroupDO specGroupDO) throws ServiceException {
		try {
			return specGroupDAO.selectCountDynamic(specGroupDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<SpecGroupDO> selectDynamic(SpecGroupDO specGroupDO) throws ServiceException {
		try {
			return specGroupDAO.selectDynamic(specGroupDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<SpecGroupDO> selectDynamicPageQuery(SpecGroupDO specGroupDO) throws ServiceException {
		try {
			return specGroupDAO.selectDynamicPageQuery(specGroupDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<SpecGroupDO> queryPageListBySpecGroupDO(SpecGroupDO specGroupDO) {
		if (specGroupDO != null) {
			Long totalCount = this.selectCountDynamic(specGroupDO);
			List<SpecGroupDO> resultList = this.selectDynamicPageQuery(specGroupDO);

			Page<SpecGroupDO> page = new Page<SpecGroupDO>();
			page.setPageNo(specGroupDO.getStartPage());
			page.setPageSize(specGroupDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<SpecGroupDO>();
	}

	public Page<SpecGroupDO> queryPageListBySpecGroupDOAndStartPageSize(SpecGroupDO specGroupDO,int startPage,int pageSize){
		if (specGroupDO != null && startPage>0 && pageSize>0) {
			specGroupDO.setStartPage(startPage);
			specGroupDO.setPageSize(pageSize);
			return this.queryPageListBySpecGroupDO(specGroupDO);
		}
		return new Page<SpecGroupDO>();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insertSpecGroupAndLink(SpecGroupDO specGroupDO, List<Long> specIds)
			throws ServiceException {
		if(null == specGroupDO || CollectionUtils.isEmpty(specIds)){
			return -1;
		}
		Long id = insert(specGroupDO);
		
		List<SpecGroupLinkDO> links = new ArrayList<SpecGroupLinkDO>();
		Date date = new Date();
		for(Long specId : specIds){
			SpecGroupLinkDO linkDO = new SpecGroupLinkDO();
			linkDO.setGroupId(id);
			linkDO.setSpecId(specId);
			linkDO.setSort(specId.intValue());
			linkDO.setCreateTime(date);
			linkDO.setModifyTime(date);
			links.add(linkDO);
		}
		
		specGroupLinkService.insertBatch(links);
		
//		Integer sort = specGroupDO.getSort();
//		String code = specGroupDO.getCode();
//		SpecGroupDO updateSort = new SpecGroupDO();
//		updateSort.setId(id);
//		if(null == sort){
//			updateSort.setSort(id.intValue());
//		}
//		if(StringUtils.isEmpty(code)){
//			updateSort.setCode(String.valueOf(id));
//		}
//		update(updateSort,false);
		return 1;
	}

	@Override
	public List<SpecGroupDO> selectByIds(List<Long> groupIds) {
		if(CollectionUtils.isEmpty(groupIds)){
			return null;
		}
		return specGroupDAO.selectByIds(groupIds);
	}

}

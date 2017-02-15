package ng.bayue.backend.service.impl;

import java.util.Date;
import java.util.List;

import ng.bayue.backend.domain.SysMenuDO;
import ng.bayue.backend.exception.ServiceException;
import ng.bayue.backend.persist.dao.SysMenuDAO;
import ng.bayue.backend.persist.exception.DAOException;
import ng.bayue.backend.service.SysMenuService;
import ng.bayue.common.Page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="sysMenuService")
public class SysMenuServiceImpl  implements SysMenuService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SysMenuDAO sysMenuDAO;

	@Override
	public SysMenuDO save(SysMenuDO sysMenuDO) throws ServiceException {
		try {
			sysMenuDO.setModifyTime(new Date());
//			if(null != sysMenuDO.getId()){//修改
//				sysMenuDAO.updateDynamic(sysMenuDO);
//			}else{//新增
//				sysMenuDO.setCreateTime(new Date());
				Long id = sysMenuDAO.insert(sysMenuDO);
				sysMenuDO.setId(id);
				
				return sysMenuDO;
//			}
			
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int update(SysMenuDO sysMenuDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) sysMenuDAO.update(sysMenuDO);
			}else{
				return (Integer) sysMenuDAO.updateDynamic(sysMenuDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) sysMenuDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(SysMenuDO sysMenuDO) throws ServiceException {
//		try {
//			return (Integer) sysMenuDAO.updateDynamic(sysMenuDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}
	
	
	public List<SysMenuDO> findSysMenuList(List<SysMenuDO> menuList){
		
		return null;
	}

	@Override
	public SysMenuDO selectById(Long id) throws ServiceException {
		try {
			return sysMenuDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(SysMenuDO sysMenuDO) throws ServiceException {
		try {
			return sysMenuDAO.selectCountDynamic(sysMenuDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<SysMenuDO> selectDynamic(SysMenuDO sysMenuDO) throws ServiceException {
		try {
			return sysMenuDAO.selectDynamic(sysMenuDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	
	@Override
	public List<SysMenuDO> selectDynamicForUrlIsNull(SysMenuDO sysMenuDO) throws ServiceException {
		try {
			return sysMenuDAO.selectDynamicForUrlIsNull(sysMenuDO);
		}catch(DAOException e){
			logger.error(e);
			throw new ServiceException(e);
		}
	}
	
	private List<SysMenuDO> selectDynamicPageQuery(SysMenuDO sysMenuDO){
		try {
			List<SysMenuDO> list = sysMenuDAO.selectDynamicPageQuery(sysMenuDO);
			return list;
		} catch (DAOException e) {
			logger.error("", e);
			throw new ServiceException(e);
		}
	}

	public Page<SysMenuDO> queryPageListBySysMenuDO(SysMenuDO sysMenuDO) {
		if (sysMenuDO != null) {
			Long totalCount = this.selectCountDynamic(sysMenuDO);
			List<SysMenuDO> resultList = this.selectDynamicPageQuery(sysMenuDO);

			Page<SysMenuDO> page = new Page<SysMenuDO>();
			page.setPageNo(sysMenuDO.getStartPage());
			page.setPageSize(sysMenuDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<SysMenuDO>();
	}

	public Page<SysMenuDO> queryPageListBySysMenuDOAndStartPageSize(SysMenuDO sysMenuDO,int startPage,int pageSize){
		if (sysMenuDO != null && startPage>0 && pageSize>0) {
			sysMenuDO.setStartPage(startPage);
			sysMenuDO.setPageSize(pageSize);
			return this.queryPageListBySysMenuDO(sysMenuDO);
		}
		return new Page<SysMenuDO>();
	}
	
	@Override
	public List<SysMenuDO> findListByParentIds(List<SysMenuDO> list){
		try {
			return this.sysMenuDAO.findListByParentIds(list);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	
	@Override
	public List<SysMenuDO> findListByIds(List<Long> list){
		try {
			return this.sysMenuDAO.findListByIds(list);
		}catch(DAOException e){
			logger.error(e);
			throw new ServiceException(e);
		}
	}
	
	
	@Override
	public List<SysMenuDO> findParentMenu(){
		try {
			return this.sysMenuDAO.findParentMenu();
		}catch(DAOException e){
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public List<SysMenuDO> selectByIds(List<Long> ids){
		if(null == ids || ids.isEmpty()) return null;
		return sysMenuDAO.selectByIds(ids);
	}

}

package ng.bayue.backend.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.backend.domain.SysRoleDO;
import ng.bayue.backend.exception.ServiceException;
import ng.bayue.backend.persist.dao.SysRoleDAO;
import ng.bayue.backend.persist.exception.DAOException;
import ng.bayue.backend.service.SysRoleService;
import ng.bayue.util.Page;

@Service(value="sysRoleService")
public class SysRoleServiceImpl  implements SysRoleService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SysRoleDAO sysRoleDAO;

	@Override
	public Long insert(SysRoleDO sysRoleDO) throws ServiceException {
		try {
			return sysRoleDAO.insert(sysRoleDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(SysRoleDO sysRoleDO) throws ServiceException {
//		try {
//			return (Integer) sysRoleDAO.updateById(sysRoleDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(SysRoleDO sysRoleDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) sysRoleDAO.update(sysRoleDO);
			}else{
				return (Integer) sysRoleDAO.updateDynamic(sysRoleDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) sysRoleDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(SysRoleDO sysRoleDO) throws ServiceException {
//		try {
//			return (Integer) sysRoleDAO.updateDynamic(sysRoleDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public SysRoleDO selectById(Long id) throws ServiceException {
		try {
			return sysRoleDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(SysRoleDO sysRoleDO) throws ServiceException {
		try {
			return sysRoleDAO.selectCountDynamic(sysRoleDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<SysRoleDO> selectDynamic(SysRoleDO sysRoleDO) throws ServiceException {
		try {
			return sysRoleDAO.selectDynamic(sysRoleDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<SysRoleDO> selectDynamicPageQuery(SysRoleDO sysRoleDO) throws ServiceException {
		try {
			return sysRoleDAO.selectDynamicPageQuery(sysRoleDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<SysRoleDO> queryPageListBySysRoleDO(SysRoleDO sysRoleDO) {
		if (sysRoleDO != null) {
			Long totalCount = this.selectCountDynamic(sysRoleDO);
			List<SysRoleDO> resultList = this.selectDynamicPageQuery(sysRoleDO);

			Page<SysRoleDO> page = new Page<SysRoleDO>();
			page.setPageNo(sysRoleDO.getStartPage());
			page.setPageSize(sysRoleDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<SysRoleDO>();
	}

	public Page<SysRoleDO> queryPageListBySysRoleDOAndStartPageSize(SysRoleDO sysRoleDO,int startPage,int pageSize){
		if (sysRoleDO != null && startPage>0 && pageSize>0) {
			sysRoleDO.setStartPage(startPage);
			sysRoleDO.setPageSize(pageSize);
			return this.queryPageListBySysRoleDO(sysRoleDO);
		}
		return new Page<SysRoleDO>();
	}

}

package ng.bayue.backend.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.backend.domain.SysUserRoleDO;
import ng.bayue.backend.exception.ServiceException;
import ng.bayue.backend.persist.dao.SysUserRoleDAO;
import ng.bayue.backend.persist.exception.DAOException;
import ng.bayue.backend.service.SysUserRoleService;
import ng.bayue.util.Page;

@Service(value="sysUserRoleService")
public class SysUserRoleServiceImpl  implements SysUserRoleService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SysUserRoleDAO sysUserRoleDAO;

	@Override
	public Long insert(SysUserRoleDO sysUserRoleDO) throws ServiceException {
		try {
			return sysUserRoleDAO.insert(sysUserRoleDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(SysUserRoleDO sysUserRoleDO) throws ServiceException {
//		try {
//			return (Integer) sysUserRoleDAO.updateById(sysUserRoleDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(SysUserRoleDO sysUserRoleDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) sysUserRoleDAO.update(sysUserRoleDO);
			}else{
				return (Integer) sysUserRoleDAO.updateDynamic(sysUserRoleDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) sysUserRoleDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(SysUserRoleDO sysUserRoleDO) throws ServiceException {
//		try {
//			return (Integer) sysUserRoleDAO.updateDynamic(sysUserRoleDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public SysUserRoleDO selectById(Long id) throws ServiceException {
		try {
			return sysUserRoleDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(SysUserRoleDO sysUserRoleDO) throws ServiceException {
		try {
			return sysUserRoleDAO.selectCountDynamic(sysUserRoleDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<SysUserRoleDO> selectDynamic(SysUserRoleDO sysUserRoleDO) throws ServiceException {
		try {
			return sysUserRoleDAO.selectDynamic(sysUserRoleDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<SysUserRoleDO> selectDynamicPageQuery(SysUserRoleDO sysUserRoleDO) throws ServiceException {
		try {
			return sysUserRoleDAO.selectDynamicPageQuery(sysUserRoleDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<SysUserRoleDO> queryPageListBySysUserRoleDO(SysUserRoleDO sysUserRoleDO) {
		if (sysUserRoleDO != null) {
			Long totalCount = this.selectCountDynamic(sysUserRoleDO);
			List<SysUserRoleDO> resultList = this.selectDynamicPageQuery(sysUserRoleDO);

			Page<SysUserRoleDO> page = new Page<SysUserRoleDO>();
			page.setPageNo(sysUserRoleDO.getStartPage());
			page.setPageSize(sysUserRoleDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<SysUserRoleDO>();
	}

	public Page<SysUserRoleDO> queryPageListBySysUserRoleDOAndStartPageSize(SysUserRoleDO sysUserRoleDO,int startPage,int pageSize){
		if (sysUserRoleDO != null && startPage>0 && pageSize>0) {
			sysUserRoleDO.setStartPage(startPage);
			sysUserRoleDO.setPageSize(pageSize);
			return this.queryPageListBySysUserRoleDO(sysUserRoleDO);
		}
		return new Page<SysUserRoleDO>();
	}

}

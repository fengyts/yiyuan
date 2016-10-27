package ng.bayue.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.backend.domain.SysMenuRoleDO;
import ng.bayue.backend.exception.ServiceException;
import ng.bayue.backend.persist.dao.SysMenuRoleDAO;
import ng.bayue.backend.persist.exception.DAOException;
import ng.bayue.backend.service.SysMenuRoleService;
import ng.bayue.util.Page;

@Service(value = "sysMenuRoleService")
public class SysMenuRoleServiceImpl implements SysMenuRoleService {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SysMenuRoleDAO sysMenuRoleDAO;

	@Override
	public Long insert(SysMenuRoleDO sysMenuRoleDO) throws ServiceException {
		try {
			return sysMenuRoleDAO.insert(sysMenuRoleDO);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public int update(SysMenuRoleDO sysMenuRoleDO, boolean isAllField) throws ServiceException {
		try {
			if (isAllField) {
				return (Integer) sysMenuRoleDAO.update(sysMenuRoleDO);
			} else {
				return (Integer) sysMenuRoleDAO.updateDynamic(sysMenuRoleDO);
			}
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) sysMenuRoleDAO.deleteById(id);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public SysMenuRoleDO selectById(Long id) throws ServiceException {
		try {
			return sysMenuRoleDAO.selectById(id);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(SysMenuRoleDO sysMenuRoleDO) throws ServiceException {
		try {
			return sysMenuRoleDAO.selectCountDynamic(sysMenuRoleDO);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SysMenuRoleDO> selectDynamic(SysMenuRoleDO sysMenuRoleDO) throws ServiceException {
		try {
			return sysMenuRoleDAO.selectDynamic(sysMenuRoleDO);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	private List<SysMenuRoleDO> selectDynamicPageQuery(SysMenuRoleDO sysMenuRoleDO) throws ServiceException {
		try {
			return sysMenuRoleDAO.selectDynamicPageQuery(sysMenuRoleDO);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	public Page<SysMenuRoleDO> queryPageListBySysMenuRoleDO(SysMenuRoleDO sysMenuRoleDO) {
		if (sysMenuRoleDO != null) {
			Long totalCount = this.selectCountDynamic(sysMenuRoleDO);
			List<SysMenuRoleDO> resultList = this.selectDynamicPageQuery(sysMenuRoleDO);

			Page<SysMenuRoleDO> page = new Page<SysMenuRoleDO>();
			page.setPageNo(sysMenuRoleDO.getStartPage());
			page.setPageSize(sysMenuRoleDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<SysMenuRoleDO>();
	}

	public Page<SysMenuRoleDO> queryPageListBySysMenuRoleDOAndStartPageSize(SysMenuRoleDO sysMenuRoleDO, int startPage,
			int pageSize) {
		if (sysMenuRoleDO != null && startPage > 0 && pageSize > 0) {
			sysMenuRoleDO.setStartPage(startPage);
			sysMenuRoleDO.setPageSize(pageSize);
			return this.queryPageListBySysMenuRoleDO(sysMenuRoleDO);
		}
		return new Page<SysMenuRoleDO>();
	}

	@Override
	public void insertBatch(Map<String,Object> map) throws ServiceException {
		try {
			sysMenuRoleDAO.insertBatch(map);
		} catch (DAOException e) {
			logger.error("批量插入异常", e);
		}
	}

}

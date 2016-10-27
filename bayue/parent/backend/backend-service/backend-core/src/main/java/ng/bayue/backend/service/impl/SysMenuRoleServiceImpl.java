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
	public int deleteByRoleId(Long roleId) throws ServiceException {
		try {
			return (Integer) sysMenuRoleDAO.deleteByRoleId(roleId);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SysMenuRoleDO> selectByRoleId(Long roleId) throws ServiceException {
		try {
			return sysMenuRoleDAO.selectByRoleId(roleId);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
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

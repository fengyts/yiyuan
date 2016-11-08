package ng.bayue.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ng.bayue.backend.domain.SysUserDO;
import ng.bayue.backend.domain.SysUserRoleDO;
import ng.bayue.backend.exception.ServiceException;
import ng.bayue.backend.persist.dao.SysUserDAO;
import ng.bayue.backend.persist.dao.SysUserRoleDAO;
import ng.bayue.backend.persist.exception.DAOException;
import ng.bayue.backend.service.SysUserService;
import ng.bayue.util.Page;
import ng.bayue.util.SecurityUtil;

@Service(value = "sysUserService")
public class SysUserServiceImpl implements SysUserService {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SysUserDAO sysUserDAO;

	@Autowired
	private SysUserRoleDAO sysUserRoleDAO;

	@Override
	public Long insert(SysUserDO sysUserDO) throws ServiceException {
		try {
			String salt = sysUserDO.getSalt();
			if(StringUtils.isEmpty(salt)){//生成随机盐
				salt = new String(SecurityUtil.encryptMD5(SecurityUtil.Salt.provideSalt()));
				sysUserDO.setSalt(salt);
			}
			String password = sysUserDO.getPassword();
			String passwdHash = SecurityUtil.hashToStr(password, salt, 2);
			sysUserDO.setPassword(passwdHash);
			return sysUserDAO.insert(sysUserDO);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public int update(SysUserDO sysUserDO, boolean isAllField) throws ServiceException {
		try {
			if (isAllField) {
				return (Integer) sysUserDAO.update(sysUserDO);
			} else {
				return (Integer) sysUserDAO.updateDynamic(sysUserDO);
			}
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) sysUserDAO.deleteById(id);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public SysUserDO selectById(Long id) throws ServiceException {
		try {
			return sysUserDAO.selectById(id);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(SysUserDO sysUserDO) throws ServiceException {
		try {
			return sysUserDAO.selectCountDynamic(sysUserDO);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SysUserDO> selectDynamic(SysUserDO sysUserDO) throws ServiceException {
		try {
			return sysUserDAO.selectDynamic(sysUserDO);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	private List<SysUserDO> selectDynamicPageQuery(SysUserDO sysUserDO) throws ServiceException {
		try {
			return sysUserDAO.selectDynamicPageQuery(sysUserDO);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	public Page<SysUserDO> queryPageListBySysUserDO(SysUserDO sysUserDO) {
		if (sysUserDO != null) {
			Long totalCount = this.selectCountDynamic(sysUserDO);
			List<SysUserDO> resultList = this.selectDynamicPageQuery(sysUserDO);

			Page<SysUserDO> page = new Page<SysUserDO>();
			page.setPageNo(sysUserDO.getStartPage());
			page.setPageSize(sysUserDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<SysUserDO>();
	}

	public Page<SysUserDO> queryPageListBySysUserDOAndStartPageSize(SysUserDO sysUserDO, int startPage, int pageSize) {
		if (sysUserDO != null && startPage > 0 && pageSize > 0) {
			sysUserDO.setStartPage(startPage);
			sysUserDO.setPageSize(pageSize);
			return this.queryPageListBySysUserDO(sysUserDO);
		}
		return new Page<SysUserDO>();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUserAndRelationRoles(SysUserDO sysUserDO, List<Long> roleIds) throws ServiceException {
		if (CollectionUtils.isEmpty(roleIds)) {
			insert(sysUserDO);
		} else {
			Long id = insert(sysUserDO);
			List<SysUserRoleDO> list = new ArrayList<SysUserRoleDO>();
			for (Long rId : roleIds) {
				SysUserRoleDO sur = new SysUserRoleDO();
				sur.setUserId(id);
				sur.setRoleId(rId);
				list.add(sur);
			}
			try {
				sysUserRoleDAO.insertBatch(list);
			} catch (DAOException e) {
				logger.error("", e);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUserAndRelationRoles(SysUserDO sysUser, List<Long> roleIds) throws ServiceException {
		if (CollectionUtils.isEmpty(roleIds)) {
			update(sysUser, false);
		} else {
			Long userId = sysUser.getId();
			try {
				sysUserRoleDAO.deleteById(userId);
			} catch (DAOException e) {
				logger.error("", e);
			}
			List<SysUserRoleDO> list = new ArrayList<SysUserRoleDO>();
			for (Long roleId : roleIds) {
				SysUserRoleDO sur = new SysUserRoleDO();
				sur.setUserId(userId);
				sur.setRoleId(roleId);
				list.add(sur);
			}
			try {
				sysUserRoleDAO.insertBatch(list);
				update(sysUser, false);
			} catch (DAOException e) {
				logger.error("", e);
			}
		}
	}

	@Override
	public SysUserDO findByLoginNameOrEmailOrMobile(String param) {
		if(StringUtils.isEmpty(param)){ return null;}
		SysUserDO sysUser = sysUserDAO.findByLoginNameOrEmailOrMobile(param);
		return sysUser;
	}
	
	

}

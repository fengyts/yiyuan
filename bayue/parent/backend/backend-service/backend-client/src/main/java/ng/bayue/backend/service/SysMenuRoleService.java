package ng.bayue.backend.service;

import java.util.List;
import java.util.Map;

import ng.bayue.backend.domain.SysMenuRoleDO;
import ng.bayue.backend.exception.ServiceException;
import ng.bayue.common.Page;
 /**
 * 角色菜单关系 Service
 * @author haisheng.long 2016-10-25 13:31:48
 */
public interface SysMenuRoleService {

	/**
	 * 插入  角色菜单关系
	 * @param sysMenuRoleDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Long insert(SysMenuRoleDO sysMenuRoleDO) throws ServiceException;

	/**
	 * 根据SysMenuRoleDO对象更新 角色菜单关系
	 * @param sysMenuRoleDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	int update(SysMenuRoleDO sysMenuRoleDO,boolean isAllField) throws ServiceException;

	/**
	 * 根据ID删除 角色菜单关系
	 * @param roleId
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	int deleteByRoleId(Long roleId) throws ServiceException;

	/**
	 * 根据ID查询 一个 角色菜单关系
	 * @param roleId
	 * @return SysMenuRoleDO
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	List<SysMenuRoleDO> selectByRoleId(Long roleId) throws ServiceException;
	
	
	void insertBatch(Map<String,Object> map) throws ServiceException;

}

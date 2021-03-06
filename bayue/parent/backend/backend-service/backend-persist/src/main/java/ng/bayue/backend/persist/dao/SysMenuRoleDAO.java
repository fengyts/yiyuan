package ng.bayue.backend.persist.dao;

import java.util.List;
import java.util.Map;

import ng.bayue.backend.domain.SysMenuRoleDO;
import ng.bayue.backend.persist.exception.DAOException;


 /**
 * 角色菜单关系 DAO
 * @author haisheng.Long 2016-10-25 13:31:48
 */
public interface SysMenuRoleDAO {

	/**
	 * 插入  角色菜单关系
	 * @param sysMenuRoleDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Long insert(SysMenuRoleDO sysMenuRoleDO) throws DAOException;

	/**
	 * 根据ID更新 角色菜单关系全部属性
	 * @param sysMenuRoleDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Integer update(SysMenuRoleDO sysMenuRoleDO) throws DAOException;

	/**
	 * 根据ID删除 角色菜单关系
	 * @param roleId
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Integer deleteByRoleId(Long roleId) throws DAOException;

	/**
	 * 动态更新 角色菜单关系部分属性，包括全部
	 * @param sysMenuRoleDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Integer updateDynamic(SysMenuRoleDO sysMenuRoleDO) throws DAOException;
	
	/**
	 * 根据ID查询 一个 角色菜单关系
	 * @param id
	 * @return SysMenuRoleDO
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	List<SysMenuRoleDO> selectByRoleId(Long roleId) throws DAOException;
	
	void insertBatch(Map<String,Object> map) throws DAOException;
	
}

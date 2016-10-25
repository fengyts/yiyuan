package ng.bayue.backend.persist.dao;

import java.util.List;

import ng.bayue.backend.domain.SysUserRoleDO;
import ng.bayue.backend.persist.exception.DAOException;


 /**
 * 系统用户角色关系 DAO
 * @author haisheng.Long 2016-10-25 13:31:48
 */
public interface SysUserRoleDAO {

	/**
	 * 插入  系统用户角色关系
	 * @param sysUserRoleDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Long insert(SysUserRoleDO sysUserRoleDO) throws DAOException;

	/**
	 * 根据ID更新 系统用户角色关系全部属性
	 * @param sysUserRoleDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Integer update(SysUserRoleDO sysUserRoleDO) throws DAOException;

	/**
	 * 根据ID删除 系统用户角色关系
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 系统用户角色关系部分属性，包括全部
	 * @param sysUserRoleDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Integer updateDynamic(SysUserRoleDO sysUserRoleDO) throws DAOException;

	/**
	 * 根据ID查询 一个 系统用户角色关系
	 * @param id
	 * @return SysUserRoleDO
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	SysUserRoleDO selectById(Long id) throws DAOException;

	/**
	 * 根据  系统用户角色关系 动态返回记录数
	 * @param sysUserRoleDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Long selectCountDynamic(SysUserRoleDO sysUserRoleDO) throws DAOException;

	/**
	 * 根据  系统用户角色关系 动态返回 系统用户角色关系 列表
	 * @param sysUserRoleDO
	 * @return List<SysUserRoleDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	List<SysUserRoleDO> selectDynamic(SysUserRoleDO sysUserRoleDO) throws DAOException;

	/**
	 * 根据  系统用户角色关系 动态返回 系统用户角色关系 Limit 列表
	 * @param sysUserRoleDO start,pageSize属性必须指定
	 * @return List<SysUserRoleDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	List<SysUserRoleDO> selectDynamicPageQuery(SysUserRoleDO sysUserRoleDO) throws DAOException;
}

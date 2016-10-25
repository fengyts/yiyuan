package ng.bayue.backend.persist.dao;

import java.util.List;

import ng.bayue.backend.domain.SysRoleDO;
import ng.bayue.backend.persist.exception.DAOException;


 /**
 * 系统用户角色 DAO
 * @author haisheng.Long 2016-10-25 13:31:48
 */
public interface SysRoleDAO {

	/**
	 * 插入  系统用户角色
	 * @param sysRoleDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Long insert(SysRoleDO sysRoleDO) throws DAOException;

	/**
	 * 根据ID更新 系统用户角色全部属性
	 * @param sysRoleDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Integer update(SysRoleDO sysRoleDO) throws DAOException;

	/**
	 * 根据ID删除 系统用户角色
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 系统用户角色部分属性，包括全部
	 * @param sysRoleDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Integer updateDynamic(SysRoleDO sysRoleDO) throws DAOException;

	/**
	 * 根据ID查询 一个 系统用户角色
	 * @param id
	 * @return SysRoleDO
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	SysRoleDO selectById(Long id) throws DAOException;

	/**
	 * 根据  系统用户角色 动态返回记录数
	 * @param sysRoleDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Long selectCountDynamic(SysRoleDO sysRoleDO) throws DAOException;

	/**
	 * 根据  系统用户角色 动态返回 系统用户角色 列表
	 * @param sysRoleDO
	 * @return List<SysRoleDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	List<SysRoleDO> selectDynamic(SysRoleDO sysRoleDO) throws DAOException;

	/**
	 * 根据  系统用户角色 动态返回 系统用户角色 Limit 列表
	 * @param sysRoleDO start,pageSize属性必须指定
	 * @return List<SysRoleDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	List<SysRoleDO> selectDynamicPageQuery(SysRoleDO sysRoleDO) throws DAOException;
}

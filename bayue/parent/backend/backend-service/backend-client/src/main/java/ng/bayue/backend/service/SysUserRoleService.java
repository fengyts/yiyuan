package ng.bayue.backend.service;

import java.util.List;

import ng.bayue.backend.domain.SysUserRoleDO;
import ng.bayue.backend.exception.ServiceException;
import ng.bayue.util.Page;
 /**
 * 系统用户角色关系 Service
 * @author haisheng.long 2016-10-25 13:31:48
 */
public interface SysUserRoleService {

	/**
	 * 插入  系统用户角色关系
	 * @param sysUserRoleDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Long insert(SysUserRoleDO sysUserRoleDO) throws ServiceException;

	/**
	 * 根据SysUserRoleDO对象更新 系统用户角色关系
	 * @param sysUserRoleDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	int update(SysUserRoleDO sysUserRoleDO,boolean isAllField) throws ServiceException;

	/**
	 * 根据ID删除 系统用户角色关系
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	int deleteById(Long id) throws ServiceException;

	/**
	 * 根据ID查询 一个 系统用户角色关系
	 * @param id
	 * @return SysUserRoleDO
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	SysUserRoleDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  系统用户角色关系 动态返回记录数
	 * @param sysUserRoleDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Long selectCountDynamic(SysUserRoleDO sysUserRoleDO) throws ServiceException;

	/**
	 * 动态返回 系统用户角色关系 列表
	 * @param sysUserRoleDO
	 * @return List<SysUserRoleDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	List<SysUserRoleDO> selectDynamic(SysUserRoleDO sysUserRoleDO) throws ServiceException;

	/**
	 * 动态返回 系统用户角色关系 分页列表
	 * @param sysUserRoleDO
	 * @return Page<SysUserRoleDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Page<SysUserRoleDO> queryPageListBySysUserRoleDO(SysUserRoleDO sysUserRoleDO);

	/**
	 * 动态返回 系统用户角色关系 分页列表
	 * @param sysUserRoleDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<SysUserRoleDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Page<SysUserRoleDO> queryPageListBySysUserRoleDOAndStartPageSize(SysUserRoleDO sysUserRoleDO,int startPage,int pageSize);
	
	void insertBatch(List<SysUserRoleDO> list) throws ServiceException;
	
	List<SysUserRoleDO> selectByUserIds(List<Long> userIds);

}

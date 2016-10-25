package ng.bayue.backend.service;

import java.util.List;

import ng.bayue.backend.domain.SysRoleDO;
import ng.bayue.backend.exception.ServiceException;
import ng.bayue.util.Page;
 /**
 * 系统用户角色 Service
 * @author haisheng.long 2016-10-25 13:31:48
 */
public interface SysRoleService {

	/**
	 * 插入  系统用户角色
	 * @param sysRoleDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Long insert(SysRoleDO sysRoleDO) throws ServiceException;

	/**
	 * 根据SysRoleDO对象更新 系统用户角色
	 * @param sysRoleDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	int update(SysRoleDO sysRoleDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 系统用户角色全部字段
//	 * @param sysRoleDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2016-10-25 13:31:48
//	 */
//	int updateById(SysRoleDO sysRoleDO) throws ServiceException;

	/**
	 * 根据ID删除 系统用户角色
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 系统用户角色部分字段
//	 * @param sysRoleDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2016-10-25 13:31:48
//	 */
//	int updateDynamic(SysRoleDO sysRoleDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 系统用户角色
	 * @param id
	 * @return SysRoleDO
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	SysRoleDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  系统用户角色 动态返回记录数
	 * @param sysRoleDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Long selectCountDynamic(SysRoleDO sysRoleDO) throws ServiceException;

	/**
	 * 动态返回 系统用户角色 列表
	 * @param sysRoleDO
	 * @return List<SysRoleDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	List<SysRoleDO> selectDynamic(SysRoleDO sysRoleDO) throws ServiceException;

	/**
	 * 动态返回 系统用户角色 分页列表
	 * @param sysRoleDO
	 * @return Page<SysRoleDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Page<SysRoleDO> queryPageListBySysRoleDO(SysRoleDO sysRoleDO);

	/**
	 * 动态返回 系统用户角色 分页列表
	 * @param sysRoleDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<SysRoleDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Page<SysRoleDO> queryPageListBySysRoleDOAndStartPageSize(SysRoleDO sysRoleDO,int startPage,int pageSize);

}

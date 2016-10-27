package ng.bayue.backend.service;

import java.util.List;
import java.util.Map;

import ng.bayue.backend.domain.SysMenuRoleDO;
import ng.bayue.backend.exception.ServiceException;
import ng.bayue.util.Page;
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
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	int deleteById(Long id) throws ServiceException;

	/**
	 * 根据ID查询 一个 角色菜单关系
	 * @param id
	 * @return SysMenuRoleDO
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	SysMenuRoleDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  角色菜单关系 动态返回记录数
	 * @param sysMenuRoleDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Long selectCountDynamic(SysMenuRoleDO sysMenuRoleDO) throws ServiceException;

	/**
	 * 动态返回 角色菜单关系 列表
	 * @param sysMenuRoleDO
	 * @return List<SysMenuRoleDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	List<SysMenuRoleDO> selectDynamic(SysMenuRoleDO sysMenuRoleDO) throws ServiceException;

	/**
	 * 动态返回 角色菜单关系 分页列表
	 * @param sysMenuRoleDO
	 * @return Page<SysMenuRoleDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Page<SysMenuRoleDO> queryPageListBySysMenuRoleDO(SysMenuRoleDO sysMenuRoleDO);

	/**
	 * 动态返回 角色菜单关系 分页列表
	 * @param sysMenuRoleDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<SysMenuRoleDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Page<SysMenuRoleDO> queryPageListBySysMenuRoleDOAndStartPageSize(SysMenuRoleDO sysMenuRoleDO,int startPage,int pageSize);
	
	void insertBatch(Map<String,Object> map) throws ServiceException;

}

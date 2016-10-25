package ng.bayue.backend.service;

import java.util.List;

import ng.bayue.backend.domain.SysUserDO;
import ng.bayue.backend.exception.ServiceException;
import ng.bayue.util.Page;
 /**
 * 后台系统用户 Service
 * @author haisheng.long 2016-10-25 13:31:48
 */
public interface SysUserService {

	/**
	 * 插入  后台系统用户
	 * @param sysUserDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Long insert(SysUserDO sysUserDO) throws ServiceException;

	/**
	 * 根据SysUserDO对象更新 后台系统用户
	 * @param sysUserDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	int update(SysUserDO sysUserDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 后台系统用户全部字段
//	 * @param sysUserDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2016-10-25 13:31:48
//	 */
//	int updateById(SysUserDO sysUserDO) throws ServiceException;

	/**
	 * 根据ID删除 后台系统用户
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 后台系统用户部分字段
//	 * @param sysUserDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2016-10-25 13:31:48
//	 */
//	int updateDynamic(SysUserDO sysUserDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 后台系统用户
	 * @param id
	 * @return SysUserDO
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	SysUserDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  后台系统用户 动态返回记录数
	 * @param sysUserDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Long selectCountDynamic(SysUserDO sysUserDO) throws ServiceException;

	/**
	 * 动态返回 后台系统用户 列表
	 * @param sysUserDO
	 * @return List<SysUserDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	List<SysUserDO> selectDynamic(SysUserDO sysUserDO) throws ServiceException;

	/**
	 * 动态返回 后台系统用户 分页列表
	 * @param sysUserDO
	 * @return Page<SysUserDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Page<SysUserDO> queryPageListBySysUserDO(SysUserDO sysUserDO);

	/**
	 * 动态返回 后台系统用户 分页列表
	 * @param sysUserDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<SysUserDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Page<SysUserDO> queryPageListBySysUserDOAndStartPageSize(SysUserDO sysUserDO,int startPage,int pageSize);

}

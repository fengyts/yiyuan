package ng.bayue.backend.service;

import java.util.List;

import ng.bayue.backend.domain.SysMenuDO;
import ng.bayue.backend.exception.ServiceException;
import ng.bayue.common.Page;

 /**
 * 菜单 Service
 * @author haisheng.long 2014-12-30 19:15:56
 */
public interface SysMenuService {

	/**
	 * 插入  菜单
	 * @param sysMenuDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	SysMenuDO save(SysMenuDO sysMenuDO) throws ServiceException;

	/**
	 * 根据SysMenuDO对象更新 菜单
	 * @param sysMenuDO
	 * @param isAllField 是否更新所有字段
	 * @return 更新行数
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	int update(SysMenuDO sysMenuDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 菜单全部字段
//	 * @param sysMenuDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2014-12-30 19:15:56
//	 */
//	int updateById(SysMenuDO sysMenuDO) throws ServiceException;

	/**
	 * 根据ID删除 菜单
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 菜单部分字段
//	 * @param sysMenuDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2014-12-30 19:15:56
//	 */
//	int updateDynamic(SysMenuDO sysMenuDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 菜单
	 * @param id
	 * @return SysMenuDO
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	SysMenuDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  菜单 动态返回记录数
	 * @param sysMenuDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	Long selectCountDynamic(SysMenuDO sysMenuDO) throws ServiceException;

	/**
	 * 动态返回 菜单 列表
	 * @param sysMenuDO
	 * @return List<SysMenuDO>
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	List<SysMenuDO> selectDynamic(SysMenuDO sysMenuDO) throws ServiceException;

	/**
	 * 动态返回 菜单 分页列表
	 * @param sysMenuDO
	 * @return Page<SysMenuDO>
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	Page<SysMenuDO> queryPageListBySysMenuDO(SysMenuDO sysMenuDO);

	/**
	 * 动态返回 菜单 分页列表
	 * @param sysMenuDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<SysMenuDO>
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	Page<SysMenuDO> queryPageListBySysMenuDOAndStartPageSize(SysMenuDO sysMenuDO,int startPage,int pageSize);

	/**
	 * 
	 * <pre>
	 * 根据parentid得到菜单集合
	 * </pre>
	 *
	 * @param list
	 * @return
	 */
	List<SysMenuDO> findListByParentIds(List<SysMenuDO> list);

	/**
	 * 
	 * <pre>
	 * 根据id得到菜单集合
	 * </pre>
	 *
	 * @param list
	 * @return
	 */
	List<SysMenuDO> findListByIds(List<Long> list);

	/**
	 * 
	 * <pre>
	 *  得到父菜单
	 * </pre>
	 *
	 * @return
	 */
	List<SysMenuDO> findParentMenu();

	List<SysMenuDO> selectByIds(List<Long> ids);

	List<SysMenuDO> selectDynamicForUrlIsNull(SysMenuDO sysMenuDO)
			throws ServiceException;

}

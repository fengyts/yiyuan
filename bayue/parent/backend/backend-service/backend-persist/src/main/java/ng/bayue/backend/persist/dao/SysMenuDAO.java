package ng.bayue.backend.persist.dao;

import java.util.List;

import ng.bayue.backend.domain.SysMenuDO;
import ng.bayue.backend.persist.exception.DAOException;


 /**
 * 菜单 DAO
 * @author haisheng.Long 2014-12-30 19:15:56
 */
public interface SysMenuDAO {

	/**
	 * 插入  菜单
	 * @param sysMenuDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	Long insert(SysMenuDO sysMenuDO) throws DAOException;

	/**
	 * 根据ID更新 菜单全部属性
	 * @param sysMenuDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	Integer update(SysMenuDO sysMenuDO) throws DAOException;

	/**
	 * 根据ID删除 菜单
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 菜单部分属性，包括全部
	 * @param sysMenuDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	Integer updateDynamic(SysMenuDO sysMenuDO) throws DAOException;

	/**
	 * 根据ID查询 一个 菜单
	 * @param id
	 * @return SysMenuDO
	 * @throws DAOException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	SysMenuDO selectById(Long id) throws DAOException;

	/**
	 * 根据  菜单 动态返回记录数
	 * @param sysMenuDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	Long selectCountDynamic(SysMenuDO sysMenuDO) throws DAOException;

	/**
	 * 根据  菜单 动态返回 菜单 列表
	 * @param sysMenuDO
	 * @return List<SysMenuDO>
	 * @throws DAOException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	List<SysMenuDO> selectDynamic(SysMenuDO sysMenuDO) throws DAOException;

	/**
	 * 根据  菜单 动态返回 菜单 Limit 列表
	 * @param sysMenuDO start,pageSize属性必须指定
	 * @return List<SysMenuDO>
	 * @throws DAOException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	List<SysMenuDO> selectDynamicPageQuery(SysMenuDO sysMenuDO) throws DAOException;

	/**
	 * 
	 * <pre>
	 *  根据parentid找到所有菜单
	 * </pre>
	 *
	 * @param sysMenuList
	 * @return
	 * @throws DAOException
	 */
	List<SysMenuDO> findListByParentIds(List<SysMenuDO> sysMenuList)
			throws DAOException;

	/**
	 * 
	 * <pre>
	 * 根据id找到菜单集合
	 * </pre>
	 *
	 * @param ids
	 * @return
	 * @throws DAOException
	 */
	List<SysMenuDO> findListByIds(List<Long> ids) throws DAOException;

	/**
	 * 
	 * <pre>
	 *  得到父菜单
	 * </pre>
	 *
	 * @return
	 */
	List<SysMenuDO> findParentMenu() throws DAOException;

	List<SysMenuDO> selectByIds(List<Long> ids);

	List<SysMenuDO> selectDynamicForUrlIsNull(SysMenuDO sysMenuDO)
			throws DAOException;
}

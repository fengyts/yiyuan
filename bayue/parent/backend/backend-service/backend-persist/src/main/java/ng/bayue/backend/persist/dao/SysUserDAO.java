package ng.bayue.backend.persist.dao;

import java.util.List;

import ng.bayue.backend.domain.SysUserDO;
import ng.bayue.backend.domain.dto.SysUserVO;
import ng.bayue.backend.persist.exception.DAOException;


 /**
 * 后台系统用户 DAO
 * @author haisheng.Long 2016-10-25 13:31:48
 */
public interface SysUserDAO {

	/**
	 * 插入  后台系统用户
	 * @param sysUserDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Long insert(SysUserDO sysUserDO) throws DAOException;

	/**
	 * 根据ID更新 后台系统用户全部属性
	 * @param sysUserDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Integer update(SysUserDO sysUserDO) throws DAOException;

	/**
	 * 根据ID删除 后台系统用户
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 后台系统用户部分属性，包括全部
	 * @param sysUserDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Integer updateDynamic(SysUserDO sysUserDO) throws DAOException;

	/**
	 * 根据ID查询 一个 后台系统用户
	 * @param id
	 * @return SysUserDO
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	SysUserDO selectById(Long id) throws DAOException;

	/**
	 * 根据  后台系统用户 动态返回记录数
	 * @param sysUserDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Long selectCountDynamic(SysUserDO sysUserDO) throws DAOException;

	/**
	 * 根据  后台系统用户 动态返回 后台系统用户 列表
	 * @param sysUserDO
	 * @return List<SysUserDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	List<SysUserDO> selectDynamic(SysUserDO sysUserDO) throws DAOException;

	/**
	 * 根据  后台系统用户 动态返回 后台系统用户 Limit 列表
	 * @param sysUserDO start,pageSize属性必须指定
	 * @return List<SysUserDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	List<SysUserDO> selectDynamicPageQuery(SysUserDO sysUserDO) throws DAOException;
	
	/**
	 * <pre>
	 * 结果集包含list的嵌套查询
	 * </pre>
	 *
	 * @param sysUserDO
	 * @return
	 */
	List<SysUserVO> nestedList(SysUserDO sysUserDO);
	
	SysUserDO findByLoginNameOrEmailOrMobile(String param);
}

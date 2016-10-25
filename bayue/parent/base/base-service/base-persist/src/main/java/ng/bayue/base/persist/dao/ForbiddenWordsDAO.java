package ng.bayue.base.persist.dao;

import java.util.List;

import ng.bayue.base.domain.ForbiddenWordsDO;
import ng.bayue.base.exception.DAOException;


 /**
 * 违禁词 DAO
 * @author haisheng.Long 2014-12-24 13:11:20
 */
public interface ForbiddenWordsDAO {

	/**
	 * 插入  违禁词
	 * @param forbiddenWordsDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	Long insert(ForbiddenWordsDO forbiddenWordsDO) throws DAOException;

	/**
	 * 根据ID更新 违禁词全部属性
	 * @param forbiddenWordsDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	Integer update(ForbiddenWordsDO forbiddenWordsDO) throws DAOException;

	/**
	 * 根据ID删除 违禁词
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 违禁词部分属性，包括全部
	 * @param forbiddenWordsDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	Integer updateDynamic(ForbiddenWordsDO forbiddenWordsDO) throws DAOException;

	/**
	 * 根据ID查询 一个 违禁词
	 * @param id
	 * @return ForbiddenWordsDO
	 * @throws DAOException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	ForbiddenWordsDO selectById(Long id) throws DAOException;

	/**
	 * 根据  违禁词 动态返回记录数
	 * @param forbiddenWordsDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	Long selectCountDynamic(ForbiddenWordsDO forbiddenWordsDO) throws DAOException;

	/**
	 * 根据  违禁词 动态返回 违禁词 列表
	 * @param forbiddenWordsDO
	 * @return List<ForbiddenWordsDO>
	 * @throws DAOException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	List<ForbiddenWordsDO> selectDynamic(ForbiddenWordsDO forbiddenWordsDO) throws DAOException;

	/**
	 * 根据  违禁词 动态返回 违禁词 Limit 列表
	 * @param forbiddenWordsDO start,pageSize属性必须指定
	 * @return List<ForbiddenWordsDO>
	 * @throws DAOException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	List<ForbiddenWordsDO> selectDynamicPageQuery(ForbiddenWordsDO forbiddenWordsDO) throws DAOException;

	List<ForbiddenWordsDO> selectListForbiddenWordsDO(List<Long> ids) throws DAOException;

	List<ForbiddenWordsDO> selectListOfByLikeForbiddenWordsPageQuery(ForbiddenWordsDO forbiddenWordsDO) throws DAOException;

	Long selectCountByLikeOfForbiddenWord(ForbiddenWordsDO forbiddenWordsDO) throws DAOException;
}

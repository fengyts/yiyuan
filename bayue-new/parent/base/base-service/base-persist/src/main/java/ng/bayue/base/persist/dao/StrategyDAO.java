package ng.bayue.base.persist.dao;

import java.util.List;
import ng.bayue.base.domain.StrategyDO;
import ng.bayue.base.exception.DAOException;


 /**
 * 攻略 DAO
 * @author haisheng.Long 2016-07-12 13:50:40
 */
public interface StrategyDAO {

	/**
	 * 插入  攻略
	 * @param strategyDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	Long insert(StrategyDO strategyDO) throws DAOException;

	/**
	 * 根据ID更新 攻略全部属性
	 * @param strategyDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	Integer update(StrategyDO strategyDO) throws DAOException;

	/**
	 * 根据ID删除 攻略
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 攻略部分属性，包括全部
	 * @param strategyDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	Integer updateDynamic(StrategyDO strategyDO) throws DAOException;

	/**
	 * 根据ID查询 一个 攻略
	 * @param id
	 * @return StrategyDO
	 * @throws DAOException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	StrategyDO selectById(Long id) throws DAOException;

	/**
	 * 根据  攻略 动态返回记录数
	 * @param strategyDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	Long selectCountDynamic(StrategyDO strategyDO) throws DAOException;

	/**
	 * 根据  攻略 动态返回 攻略 列表
	 * @param strategyDO
	 * @return List<StrategyDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	List<StrategyDO> selectDynamic(StrategyDO strategyDO) throws DAOException;

	/**
	 * 根据  攻略 动态返回 攻略 Limit 列表
	 * @param strategyDO start,pageSize属性必须指定
	 * @return List<StrategyDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	List<StrategyDO> selectDynamicPageQuery(StrategyDO strategyDO) throws DAOException;
}

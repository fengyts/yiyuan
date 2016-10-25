package ng.bayue.base.service;

import java.util.List;

import ng.bayue.base.domain.StrategyDO;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.util.Page;
 /**
 * 攻略 Service
 * @author haisheng.long 2016-07-12 13:50:40
 */
public interface StrategyService {

	/**
	 * 插入  攻略
	 * @param strategyDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	Long insert(StrategyDO strategyDO) throws ServiceException;

	/**
	 * 根据StrategyDO对象更新 攻略
	 * @param strategyDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	int update(StrategyDO strategyDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 攻略全部字段
//	 * @param strategyDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2016-07-12 13:50:40
//	 */
//	int updateById(StrategyDO strategyDO) throws ServiceException;

	/**
	 * 根据ID删除 攻略
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 攻略部分字段
//	 * @param strategyDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2016-07-12 13:50:40
//	 */
//	int updateDynamic(StrategyDO strategyDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 攻略
	 * @param id
	 * @return StrategyDO
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	StrategyDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  攻略 动态返回记录数
	 * @param strategyDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	Long selectCountDynamic(StrategyDO strategyDO) throws ServiceException;

	/**
	 * 动态返回 攻略 列表
	 * @param strategyDO
	 * @return List<StrategyDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	List<StrategyDO> selectDynamic(StrategyDO strategyDO) throws ServiceException;

	/**
	 * 动态返回 攻略 分页列表
	 * @param strategyDO
	 * @return Page<StrategyDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	Page<StrategyDO> queryPageListByStrategyDO(StrategyDO strategyDO);

	/**
	 * 动态返回 攻略 分页列表
	 * @param strategyDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<StrategyDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-12 13:50:40
	 */
	Page<StrategyDO> queryPageListByStrategyDOAndStartPageSize(StrategyDO strategyDO,int startPage,int pageSize);

}

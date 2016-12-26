package ng.bayue.promotion.persist.dao;

import java.util.List;

import ng.bayue.promotion.domain.TopicItemDO;
import ng.bayue.promotion.exception.DAOException;


 /**
 * 专题商品关联 DAO
 * @author haisheng.Long 2016-12-26 16:28:48
 */
public interface TopicItemDAO {

	/**
	 * 插入  专题商品关联
	 * @param topicItemDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Long insert(TopicItemDO topicItemDO) throws DAOException;

	/**
	 * 根据ID更新 专题商品关联全部属性
	 * @param topicItemDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Integer update(TopicItemDO topicItemDO) throws DAOException;

	/**
	 * 根据ID删除 专题商品关联
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 专题商品关联部分属性，包括全部
	 * @param topicItemDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Integer updateDynamic(TopicItemDO topicItemDO) throws DAOException;

	/**
	 * 根据ID查询 一个 专题商品关联
	 * @param id
	 * @return TopicItemDO
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	TopicItemDO selectById(Long id) throws DAOException;

	/**
	 * 根据  专题商品关联 动态返回记录数
	 * @param topicItemDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Long selectCountDynamic(TopicItemDO topicItemDO) throws DAOException;

	/**
	 * 根据  专题商品关联 动态返回 专题商品关联 列表
	 * @param topicItemDO
	 * @return List<TopicItemDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	List<TopicItemDO> selectDynamic(TopicItemDO topicItemDO) throws DAOException;

	/**
	 * 根据  专题商品关联 动态返回 专题商品关联 Limit 列表
	 * @param topicItemDO start,pageSize属性必须指定
	 * @return List<TopicItemDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	List<TopicItemDO> selectDynamicPageQuery(TopicItemDO topicItemDO) throws DAOException;
}

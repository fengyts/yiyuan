package ng.bayue.promotion.persist.dao;

import java.util.List;

import ng.bayue.promotion.domain.TopicDO;
import ng.bayue.promotion.dto.TopicDTO;
import ng.bayue.promotion.exception.DAOException;


 /**
 * 活动专题 DAO
 * @author haisheng.Long 2016-12-26 16:28:48
 */
public interface TopicDAO {

	/**
	 * 插入  活动专题
	 * @param topicDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Long insert(TopicDO topicDO) throws DAOException;

	/**
	 * 根据ID更新 活动专题全部属性
	 * @param topicDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Integer update(TopicDO topicDO) throws DAOException;

	/**
	 * 根据ID删除 活动专题
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 活动专题部分属性，包括全部
	 * @param topicDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Integer updateDynamic(TopicDO topicDO) throws DAOException;

	/**
	 * 根据ID查询 一个 活动专题
	 * @param id
	 * @return TopicDO
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	TopicDTO selectById(Long id) throws DAOException;

	/**
	 * 根据  活动专题 动态返回记录数
	 * @param topicDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Long selectCountDynamic(TopicDO topicDO) throws DAOException;

	/**
	 * 根据  活动专题 动态返回 活动专题 列表
	 * @param topicDO
	 * @return List<TopicDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	List<TopicDTO> selectDynamic(TopicDO topicDO) throws DAOException;

	/**
	 * 根据  活动专题 动态返回 活动专题 Limit 列表
	 * @param topicDO start,pageSize属性必须指定
	 * @return List<TopicDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	List<TopicDTO> selectDynamicPageQuery(TopicDO topicDO) throws DAOException;
}

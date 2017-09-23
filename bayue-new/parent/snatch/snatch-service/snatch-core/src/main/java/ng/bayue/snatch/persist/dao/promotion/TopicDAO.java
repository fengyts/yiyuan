package ng.bayue.snatch.persist.dao.promotion;

import java.util.List;

import ng.bayue.exception.CommonDAOException;
import ng.bayue.snatch.domain.promotion.TopicDO;
import ng.bayue.snatch.dto.promotion.TopicDTO;


 /**
 * 活动专题 DAO
 * @author haisheng.Long 2016-12-26 16:28:48
 */
public interface TopicDAO {

	/**
	 * 插入  活动专题
	 * @param topicDO
	 * @return 主键
	 * @throws CommonDAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Long insert(TopicDO topicDO) throws CommonDAOException;

	/**
	 * 根据ID更新 活动专题全部属性
	 * @param topicDO
	 * @return 更新行数
	 * @throws CommonDAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Integer update(TopicDO topicDO) throws CommonDAOException;

	/**
	 * 根据ID删除 活动专题
	 * @param id
	 * @return 删除行数
	 * @throws CommonDAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Integer deleteById(Long id) throws CommonDAOException;

	/**
	 * 动态更新 活动专题部分属性，包括全部
	 * @param topicDO
	 * @return 更新行数
	 * @throws CommonDAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Integer updateDynamic(TopicDO topicDO) throws CommonDAOException;

	/**
	 * 根据ID查询 一个 活动专题
	 * @param id
	 * @return TopicDO
	 * @throws CommonDAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	TopicDTO selectById(Long id) throws CommonDAOException;

	/**
	 * 根据  活动专题 动态返回记录数
	 * @param topicDO
	 * @return 记录条数
	 * @throws CommonDAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Long selectCountDynamic(TopicDO topicDO) throws CommonDAOException;

	/**
	 * 根据  活动专题 动态返回 活动专题 列表
	 * @param topicDO
	 * @return List<TopicDO>
	 * @throws CommonDAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	List<TopicDTO> selectDynamic(TopicDO topicDO) throws CommonDAOException;

	/**
	 * 根据  活动专题 动态返回 活动专题 Limit 列表
	 * @param topicDO start,pageSize属性必须指定
	 * @return List<TopicDO>
	 * @throws CommonDAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	List<TopicDTO> selectDynamicPageQuery(TopicDO topicDO) throws CommonDAOException;
}

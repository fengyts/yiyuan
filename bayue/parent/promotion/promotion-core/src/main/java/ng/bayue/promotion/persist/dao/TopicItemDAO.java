package ng.bayue.promotion.persist.dao;

import java.util.List;
import java.util.Map;

import ng.bayue.promotion.domain.TopicItemDO;
import ng.bayue.promotion.dto.TopicItemDTO;
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
	TopicItemDTO selectById(Long id) throws DAOException;

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
	List<TopicItemDTO> selectDynamic(TopicItemDO topicItemDO) throws DAOException;

	/**
	 * 根据  专题商品关联 动态返回 专题商品关联 Limit 列表
	 * @param topicItemDO start,pageSize属性必须指定
	 * @return List<TopicItemDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	List<TopicItemDTO> selectDynamicPageQuery(TopicItemDO topicItemDO) throws DAOException;
	
	int insertBatch(List<TopicItemDO> list) throws DAOException;
	
	/**
	 * <pre>
	 * 获取某专题下的一些商品
	 * </pre>
	 *
	 * @param param
	 * @return
	 * @throws DAOException
	 */
	List<TopicItemDO> existTopicItem(Map<String,Object> param) throws DAOException;
	
	/**
	 * <pre>
	 * 校验商品是否有效状态,用于商品下架和作废时校验用
	 * </pre>
	 *
	 * @param detailIds
	 * @return
	 * @throws DAOException
	 */
	List<TopicItemDO> validItemStatus(List<Long> detailIds) throws DAOException;
	
	int updateBatch(List<TopicItemDO> list) throws DAOException;
}

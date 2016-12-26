package ng.bayue.promotion.service;

import java.util.List;
import ng.bayue.util.Page;
import ng.bayue.promotion.exception.ServiceException;
import ng.bayue.promotion.domain.TopicItemDO;
 /**
 * 专题商品关联 Service
 * @author haisheng.long 2016-12-26 16:28:48
 */
public interface TopicItemService {

	/**
	 * 插入  专题商品关联
	 * @param topicItemDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Long insert(TopicItemDO topicItemDO) throws ServiceException;

	/**
	 * 根据TopicItemDO对象更新 专题商品关联
	 * @param topicItemDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	int update(TopicItemDO topicItemDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 专题商品关联全部字段
//	 * @param topicItemDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2016-12-26 16:28:48
//	 */
//	int updateById(TopicItemDO topicItemDO) throws ServiceException;

	/**
	 * 根据ID删除 专题商品关联
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 专题商品关联部分字段
//	 * @param topicItemDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2016-12-26 16:28:48
//	 */
//	int updateDynamic(TopicItemDO topicItemDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 专题商品关联
	 * @param id
	 * @return TopicItemDO
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	TopicItemDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  专题商品关联 动态返回记录数
	 * @param topicItemDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Long selectCountDynamic(TopicItemDO topicItemDO) throws ServiceException;

	/**
	 * 动态返回 专题商品关联 列表
	 * @param topicItemDO
	 * @return List<TopicItemDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	List<TopicItemDO> selectDynamic(TopicItemDO topicItemDO) throws ServiceException;

	/**
	 * 动态返回 专题商品关联 分页列表
	 * @param topicItemDO
	 * @return Page<TopicItemDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Page<TopicItemDO> queryPageListByTopicItemDO(TopicItemDO topicItemDO);

	/**
	 * 动态返回 专题商品关联 分页列表
	 * @param topicItemDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<TopicItemDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Page<TopicItemDO> queryPageListByTopicItemDOAndStartPageSize(TopicItemDO topicItemDO,int startPage,int pageSize);

}

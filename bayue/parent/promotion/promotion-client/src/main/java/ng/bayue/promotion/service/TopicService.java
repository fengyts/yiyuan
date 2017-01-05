package ng.bayue.promotion.service;

import java.util.List;
import ng.bayue.util.Page;
import ng.bayue.promotion.exception.ServiceException;
import ng.bayue.promotion.domain.TopicDO;
import ng.bayue.promotion.dto.TopicDTO;
 /**
 * 活动专题 Service
 * @author haisheng.long 2016-12-26 16:28:48
 */
public interface TopicService {

	/**
	 * 插入  活动专题
	 * @param topicDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Long insert(TopicDO topicDO) throws ServiceException;

	/**
	 * 根据TopicDO对象更新 活动专题
	 * @param topicDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	int update(TopicDO topicDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 活动专题全部字段
//	 * @param topicDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2016-12-26 16:28:48
//	 */
//	int updateById(TopicDO topicDO) throws ServiceException;

	/**
	 * 根据ID删除 活动专题
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 活动专题部分字段
//	 * @param topicDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2016-12-26 16:28:48
//	 */
//	int updateDynamic(TopicDO topicDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 活动专题
	 * @param id
	 * @return TopicDO
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	TopicDTO selectById(Long id) throws ServiceException;

	/**
	 * 根据  活动专题 动态返回记录数
	 * @param topicDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Long selectCountDynamic(TopicDO topicDO) throws ServiceException;

	/**
	 * 动态返回 活动专题 列表
	 * @param topicDO
	 * @return List<TopicDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	List<TopicDTO> selectDynamic(TopicDO topicDO) throws ServiceException;

	/**
	 * 动态返回 活动专题 分页列表
	 * @param topicDO
	 * @return Page<TopicDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Page<TopicDTO> queryPageListByTopicDO(TopicDO topicDO);

	/**
	 * 动态返回 活动专题 分页列表
	 * @param topicDTO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<TopicDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-26 16:28:48
	 */
	Page<TopicDTO> queryPageListByTopicDOAndStartPageSize(TopicDO topicDTO,int startPage,int pageSize);

}

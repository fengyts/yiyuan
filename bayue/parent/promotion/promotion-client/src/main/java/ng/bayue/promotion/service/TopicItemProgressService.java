package ng.bayue.promotion.service;

import ng.bayue.service.common.GeneralService;

import java.util.List;

import ng.bayue.promotion.domain.TopicItemProgressDO;
import ng.bayue.promotion.exception.ServiceException;

/**
 * 商品夺宝进度 Service
 * 
 * @author fengyts 2017-04-27 09:43:12
 */
public interface TopicItemProgressService extends GeneralService<TopicItemProgressDO, TopicItemProgressDO> {

	/**
	 * <pre>
	 * 根据topicItemId 动态更新
	 * </pre>
	 *
	 * @param topicItemId
	 * @return
	 * @throws ServiceException
	 */
	int updateByTopicItemId(TopicItemProgressDO itemProgressDO) throws ServiceException;

	/**
	 * <pre>
	 * 根据topicItemId列表批量查询
	 * </pre>
	 *
	 * @param topicItemIds
	 * @return
	 */
	List<TopicItemProgressDO> selectByTopicItemIds(List<Long> topicItemIds);

}

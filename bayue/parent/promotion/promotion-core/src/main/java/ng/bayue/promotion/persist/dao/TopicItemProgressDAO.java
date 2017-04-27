package ng.bayue.promotion.persist.dao;

import java.util.List;

import ng.bayue.exception.DAOException;
import ng.bayue.promotion.domain.TopicItemProgressDO;
import ng.bayue.service.common.GeneralDAO;

 /**
 * 商品夺宝进度 DAO
 *
 * @author fengyts 2017-04-27 09:43:12
 */
public interface TopicItemProgressDAO extends GeneralDAO<TopicItemProgressDO> {
	
	/**
	 * <pre>
	 * 根据topicItemId 动态更新
	 * </pre>
	 *
	 * @param topicItemId
	 * @return
	 * @throws DAOException
	 */
	int updateByTopicItemId(TopicItemProgressDO itemProgressDO) throws DAOException;
	
	/**
	 * <pre>
	 * 根据topicItemId列表批量查询
	 * </pre>
	 *
	 * @param topicItemIds
	 * @return
	 */
	List<TopicItemProgressDO> selectByIds(List<Long> topicItemIds);

}

package ng.bayue.snatch.persist.dao.promotion;

import java.util.List;

import ng.bayue.exception.CommonDAOException;
import ng.bayue.service.common.GeneralDAO;
import ng.bayue.snatch.domain.promotion.TopicItemProgressDO;
import ng.bayue.snatch.exception.DAOException;

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
	int updateByTopicItemId(TopicItemProgressDO itemProgressDO) throws CommonDAOException;
	
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

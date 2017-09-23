package ng.bayue.snatch.persist.mybatis.promotion;

import java.util.List;
import java.util.Map;

import ng.bayue.exception.CommonDAOException;
import ng.bayue.snatch.domain.promotion.TopicItemDO;
import ng.bayue.snatch.dto.promotion.TopicItemDTO;
import ng.bayue.snatch.persist.dao.promotion.TopicItemDAO;
import ng.bayue.snatch.persist.mybatis.MybatisBaseDAO;

import org.springframework.stereotype.Component;

@Component(value="topicItemDAO")
public class MybatisTopicItemDAO extends MybatisBaseDAO implements TopicItemDAO {
	
	private static final String NAMESPACE = "ng.bayue.promotion.domain.TopicItemMapper.MybatisTopicItemDAO_";
	
	public Long insert(TopicItemDO topicItemDO) throws CommonDAOException {
		int i = getSqlSession().insert(NAMESPACE + "insert", topicItemDO);
		if (i > 0) {
			return Long.valueOf(topicItemDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(TopicItemDO topicItemDO) throws CommonDAOException {
		return getSqlSession().update(NAMESPACE + "updateById", topicItemDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(NAMESPACE + "deleteById", id);
	}

	@Override
	public Integer updateDynamic(TopicItemDO topicItemDO) throws CommonDAOException {
		return getSqlSession().update(NAMESPACE + "update_dynamic", topicItemDO);
	}

	@Override
	public TopicItemDTO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(NAMESPACE + "selectById", id);
	}

	@Override
	public Long selectCountDynamic(TopicItemDO topicItemDO) throws CommonDAOException {
		return getSqlSession().selectOne(NAMESPACE + "select_dynamic_count", topicItemDO);
	}

	@Override
	public List<TopicItemDTO> selectDynamic(TopicItemDO topicItemDO) throws CommonDAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic", topicItemDO);
	}

	@Override
	public List<TopicItemDTO> selectDynamicPageQuery(TopicItemDO topicItemDO) throws CommonDAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic_page_query", topicItemDO);
	}
	
	@Override
	public int insertBatch(List<TopicItemDO> list) throws CommonDAOException {
		return getSqlSession().insert(NAMESPACE + "insert_batch",  list);
	}

	@Override
	public List<TopicItemDO> existTopicItem(Map<String,Object> param) throws CommonDAOException {
		return getSqlSession().selectList(NAMESPACE + "exist_topic_item", param);
	}

	@Override
	public List<TopicItemDO> validItemStatus(List<Long> detailIds) throws CommonDAOException {
		return getSqlSession().selectList(NAMESPACE + "validItemStatus", detailIds);
	}

	@Override
	public int updateBatch(List<TopicItemDO> list) throws CommonDAOException {
		return getSqlSession().update(NAMESPACE + "update_batch", list);
	}

}

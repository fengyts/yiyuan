package ng.bayue.promotion.persist.mybatis;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import ng.bayue.promotion.domain.TopicItemDO;
import ng.bayue.promotion.dto.TopicItemDTO;
import ng.bayue.promotion.exception.DAOException;
import ng.bayue.promotion.persist.dao.TopicItemDAO;

@Component(value="topicItemDAO")
public class MybatisTopicItemDAO extends MybatisBaseDAO implements TopicItemDAO {
	
	private static final String NAMESPACE = "ng.bayue.promotion.domain.TopicItemMapper.MybatisTopicItemDAO_";
	
	public Long insert(TopicItemDO topicItemDO) throws DAOException {
		int i = getSqlSession().insert(NAMESPACE + "insert", topicItemDO);
		if (i > 0) {
			return Long.valueOf(topicItemDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(TopicItemDO topicItemDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "updateById", topicItemDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete(NAMESPACE + "deleteById", id);
	}

	@Override
	public Integer updateDynamic(TopicItemDO topicItemDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "update_dynamic", topicItemDO);
	}

	@Override
	public TopicItemDTO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "selectById", id);
	}

	@Override
	public Long selectCountDynamic(TopicItemDO topicItemDO) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "select_dynamic_count", topicItemDO);
	}

	@Override
	public List<TopicItemDTO> selectDynamic(TopicItemDO topicItemDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic", topicItemDO);
	}

	@Override
	public List<TopicItemDTO> selectDynamicPageQuery(TopicItemDO topicItemDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic_page_query", topicItemDO);
	}
	
	@Override
	public int insertBatch(List<TopicItemDO> list) throws DAOException {
		return getSqlSession().insert(NAMESPACE + "insert_batch",  list);
	}

	@Override
	public List<TopicItemDO> existTopicItem(Map<String,Object> param) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "exist_topic_item", param);
	}

	@Override
	public List<TopicItemDO> validItemStatus(List<Long> detailIds) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "validItemStatus", detailIds);
	}

	@Override
	public int updateBatch(List<TopicItemDO> list) throws DAOException {
		return getSqlSession().update(NAMESPACE + "update_batch", list);
	}

}

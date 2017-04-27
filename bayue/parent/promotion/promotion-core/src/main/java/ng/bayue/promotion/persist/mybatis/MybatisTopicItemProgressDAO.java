package ng.bayue.promotion.persist.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;
import ng.bayue.promotion.domain.TopicItemProgressDO;
import ng.bayue.promotion.persist.dao.TopicItemProgressDAO;
import ng.bayue.exception.DAOException;

@Component(value="topicItemProgressDAO")
public class MybatisTopicItemProgressDAO extends MybatisBaseDAO implements TopicItemProgressDAO {
	
	private static final String NAMESPACE = "ng.bayue.promotion.domain.TopicItemProgressMapper.MybatisTopicItemProgressDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(TopicItemProgressDO topicItemProgressDO) throws DAOException {
		int i = getSqlSession().insert(getStatement("insert"), topicItemProgressDO);
		if (i > 0) {
			return Long.valueOf(topicItemProgressDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(TopicItemProgressDO topicItemProgressDO) throws DAOException {
		return getSqlSession().update(getStatement("updateById"), topicItemProgressDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(TopicItemProgressDO topicItemProgressDO) throws DAOException {
		return getSqlSession().update(getStatement("update_dynamic"), topicItemProgressDO);
	}

	@Override
	public TopicItemProgressDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(TopicItemProgressDO topicItemProgressDO) throws DAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), topicItemProgressDO);
	}

	@Override
	public List<TopicItemProgressDO> selectDynamic(TopicItemProgressDO topicItemProgressDO) throws DAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), topicItemProgressDO);
	}

	@Override
	public List<TopicItemProgressDO> selectDynamicPageQuery(TopicItemProgressDO topicItemProgressDO) throws DAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), topicItemProgressDO);
	}

	@Override
	public int updateByTopicItemId(TopicItemProgressDO itemProgressDO) throws DAOException {
		return getSqlSession().update(getStatement("update_by_topic_item_id"), itemProgressDO);
	}

	@Override
	public List<TopicItemProgressDO> selectByIds(List<Long> topicItemIds) {
		return getSqlSession().selectList(getStatement("select_byIds"), topicItemIds);
	}

}

package ng.bayue.snatch.persist.mybatis.promotion;

import java.util.List;

import ng.bayue.exception.CommonDAOException;
import ng.bayue.snatch.domain.promotion.TopicItemProgressDO;
import ng.bayue.snatch.persist.dao.promotion.TopicItemProgressDAO;
import ng.bayue.snatch.persist.mybatis.MybatisBaseDAO;

import org.springframework.stereotype.Component;

@Component(value="topicItemProgressDAO")
public class MybatisTopicItemProgressDAO extends MybatisBaseDAO implements TopicItemProgressDAO {
	
	private static final String NAMESPACE = "ng.bayue.promotion.domain.TopicItemProgressMapper.MybatisTopicItemProgressDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(TopicItemProgressDO topicItemProgressDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), topicItemProgressDO);
		if (i > 0) {
			return Long.valueOf(topicItemProgressDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(TopicItemProgressDO topicItemProgressDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), topicItemProgressDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(TopicItemProgressDO topicItemProgressDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), topicItemProgressDO);
	}

	@Override
	public TopicItemProgressDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(TopicItemProgressDO topicItemProgressDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), topicItemProgressDO);
	}

	@Override
	public List<TopicItemProgressDO> selectDynamic(TopicItemProgressDO topicItemProgressDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), topicItemProgressDO);
	}

	@Override
	public List<TopicItemProgressDO> selectDynamicPageQuery(TopicItemProgressDO topicItemProgressDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), topicItemProgressDO);
	}

	@Override
	public int updateByTopicItemId(TopicItemProgressDO itemProgressDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_by_topic_item_id"), itemProgressDO);
	}

	@Override
	public List<TopicItemProgressDO> selectByIds(List<Long> topicItemIds) {
		return getSqlSession().selectList(getStatement("select_byIds"), topicItemIds);
	}

}

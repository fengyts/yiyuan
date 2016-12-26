package ng.bayue.promotion.persist.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;

import ng.bayue.promotion.domain.TopicDO;
import ng.bayue.promotion.exception.DAOException;
import ng.bayue.promotion.persist.dao.TopicDAO;

@Component(value="topicDAO")
public class MybatisTopicDAO extends MybatisBaseDAO implements TopicDAO {
	
	private static final String NAMESPACE = "ng.bayue.promotion.domain.TopicMapper.MybatisTopicDAO_";
	
	public Long insert(TopicDO topicDO) throws DAOException {
		int i = getSqlSession().insert(NAMESPACE + "insert", topicDO);
		if (i > 0) {
			return Long.valueOf(topicDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(TopicDO topicDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "updateById", topicDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete(NAMESPACE + "deleteById", id);
	}

	@Override
	public Integer updateDynamic(TopicDO topicDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "update_dynamic", topicDO);
	}

	@Override
	public TopicDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "selectById", id);
	}

	@Override
	public Long selectCountDynamic(TopicDO topicDO) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "select_dynamic_count", topicDO);
	}

	@Override
	public List<TopicDO> selectDynamic(TopicDO topicDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic", topicDO);
	}

	@Override
	public List<TopicDO> selectDynamicPageQuery(TopicDO topicDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic_page_query", topicDO);
	}

}

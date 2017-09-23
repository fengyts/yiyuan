package ng.bayue.snatch.persist.mybatis.promotion;

import java.util.List;

import ng.bayue.exception.CommonDAOException;
import ng.bayue.snatch.domain.promotion.TopicDO;
import ng.bayue.snatch.dto.promotion.TopicDTO;
import ng.bayue.snatch.persist.dao.promotion.TopicDAO;
import ng.bayue.snatch.persist.mybatis.MybatisBaseDAO;

import org.springframework.stereotype.Component;

@Component(value="topicDAO")
public class MybatisTopicDAO extends MybatisBaseDAO implements TopicDAO {
	
	private static final String NAMESPACE = "ng.bayue.promotion.domain.TopicMapper.MybatisTopicDAO_";
	
	public Long insert(TopicDO topicDO) throws CommonDAOException {
		int i = getSqlSession().insert(NAMESPACE + "insert", topicDO);
		if (i > 0) {
			return Long.valueOf(topicDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(TopicDO topicDO) throws CommonDAOException {
		return getSqlSession().update(NAMESPACE + "updateById", topicDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(NAMESPACE + "deleteById", id);
	}

	@Override
	public Integer updateDynamic(TopicDO topicDO) throws CommonDAOException {
		return getSqlSession().update(NAMESPACE + "update_dynamic", topicDO);
	}

	@Override
	public TopicDTO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(NAMESPACE + "selectById", id);
	}

	@Override
	public Long selectCountDynamic(TopicDO topicDO) throws CommonDAOException {
		return getSqlSession().selectOne(NAMESPACE + "select_dynamic_count", topicDO);
	}

	@Override
	public List<TopicDTO> selectDynamic(TopicDO topicDO) throws CommonDAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic", topicDO);
	}

	@Override
	public List<TopicDTO> selectDynamicPageQuery(TopicDO topicDO) throws CommonDAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic_page_query", topicDO);
	}

}

package ng.bayue.base.persist.mybatis;
import java.util.ArrayList;
import java.util.List;

import ng.bayue.base.domain.ForbiddenWordsDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.persist.dao.ForbiddenWordsDAO;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

@Component(value="forbiddenWordsDAO")
public class MybatisForbiddenWordsDAO extends MybatisBaseDAO implements ForbiddenWordsDAO {
	public Long insert(ForbiddenWordsDO forbiddenWordsDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.base.domain.ForbiddenWordsMapper.MybatisForbiddenWordsDAO_insert", forbiddenWordsDO);
		if (i > 0) {
			return Long.valueOf(forbiddenWordsDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(ForbiddenWordsDO forbiddenWordsDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.ForbiddenWordsMapper.MybatisForbiddenWordsDAO_updateById", forbiddenWordsDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.base.domain.ForbiddenWordsMapper.MybatisForbiddenWordsDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(ForbiddenWordsDO forbiddenWordsDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.ForbiddenWordsMapper.MybatisForbiddenWordsDAO_update_dynamic", forbiddenWordsDO);
	}

	@Override
	public ForbiddenWordsDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.ForbiddenWordsMapper.MybatisForbiddenWordsDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(ForbiddenWordsDO forbiddenWordsDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.ForbiddenWordsMapper.MybatisForbiddenWordsDAO_select_dynamic_count", forbiddenWordsDO);
	}

	@Override
	public List<ForbiddenWordsDO> selectDynamic(ForbiddenWordsDO forbiddenWordsDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.ForbiddenWordsMapper.MybatisForbiddenWordsDAO_select_dynamic", forbiddenWordsDO);
	}

	@Override
	public List<ForbiddenWordsDO> selectDynamicPageQuery(ForbiddenWordsDO forbiddenWordsDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.ForbiddenWordsMapper.MybatisForbiddenWordsDAO_select_dynamic_page_query", forbiddenWordsDO);
	}

	@Override
	public List<ForbiddenWordsDO> selectListForbiddenWordsDO(List<Long> ids)  throws DAOException {
		if(CollectionUtils.isEmpty(ids)){
			return new ArrayList<ForbiddenWordsDO>();
		}
	    return getSqlSession().selectList("ng.bayue.base.domain.ForbiddenWordsMapper.MybatisForbiddenWordsDAO_select_ForbiddenWords_list_query", ids);
	}

	@Override
	public List<ForbiddenWordsDO> selectListOfByLikeForbiddenWordsPageQuery(ForbiddenWordsDO forbiddenWordsDO) throws DAOException {
	    return getSqlSession().selectList("ng.bayue.base.domain.ForbiddenWordsMapper.MybatisForbiddenWordsDAO_select_list_by_forbiddenWords_like_pagequery", forbiddenWordsDO);
	}

	@Override
	public Long selectCountByLikeOfForbiddenWord(ForbiddenWordsDO forbiddenWordsDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.ForbiddenWordsMapper.MybatisForbiddenWordsDAO_select_by_likeof_forbiddenWords_count", forbiddenWordsDO);
	}


}

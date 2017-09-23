package ng.bayue.base.persist.mybatis;
import java.util.List;

import ng.bayue.base.domain.DictionaryDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.persist.dao.DictionaryDAO;

import org.springframework.stereotype.Component;

@Component(value="dictionaryDAO")
public class MybatisDictionaryDAO extends MybatisBaseDAO implements DictionaryDAO {
	public Long insert(DictionaryDO dictionaryDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.base.domain.DictionaryMapper.MybatisDictionaryDAO_insert", dictionaryDO);
		if (i > 0) {
			return Long.valueOf(dictionaryDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(DictionaryDO dictionaryDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.DictionaryMapper.MybatisDictionaryDAO_updateById", dictionaryDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.base.domain.DictionaryMapper.MybatisDictionaryDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(DictionaryDO dictionaryDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.DictionaryMapper.MybatisDictionaryDAO_update_dynamic", dictionaryDO);
	}

	@Override
	public DictionaryDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.DictionaryMapper.MybatisDictionaryDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(DictionaryDO dictionaryDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.DictionaryMapper.MybatisDictionaryDAO_select_dynamic_count", dictionaryDO);
	}

	@Override
	public List<DictionaryDO> selectDynamic(DictionaryDO dictionaryDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.DictionaryMapper.MybatisDictionaryDAO_select_dynamic", dictionaryDO);
	}

	@Override
	public List<DictionaryDO> selectDynamicPageQuery(DictionaryDO dictionaryDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.DictionaryMapper.MybatisDictionaryDAO_select_dynamic_page_query", dictionaryDO);
	}

	@Override
	public List<DictionaryDO> listAllCode() {
		return getSqlSession().selectList("ng.bayue.base.domain.DictionaryMapper.MybatisDictionaryDAO_select_all_code");
	}

	@Override
	public List<DictionaryDO> selectByIds(List<Long> ids) {
		return getSqlSession().selectList("ng.bayue.base.domain.DictionaryMapper.MybatisDictionaryDAO_select_byIds", ids);
	}

}

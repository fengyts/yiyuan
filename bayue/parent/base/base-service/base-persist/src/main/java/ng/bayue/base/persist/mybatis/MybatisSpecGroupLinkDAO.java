package ng.bayue.base.persist.mybatis;
import java.util.List;

import ng.bayue.base.domain.SpecGroupLinkDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.persist.dao.SpecGroupLinkDAO;

import org.springframework.stereotype.Component;

@Component(value="specGroupLinkDAO")
public class MybatisSpecGroupLinkDAO extends MybatisBaseDAO implements SpecGroupLinkDAO {
	public Long insert(SpecGroupLinkDO specGroupLinkDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.base.domain.SpecGroupLinkMapper.MybatisSpecGroupLinkDAO_insert", specGroupLinkDO);
		if (i > 0) {
			return Long.valueOf(specGroupLinkDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(SpecGroupLinkDO specGroupLinkDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.SpecGroupLinkMapper.MybatisSpecGroupLinkDAO_updateById", specGroupLinkDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.base.domain.SpecGroupLinkMapper.MybatisSpecGroupLinkDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(SpecGroupLinkDO specGroupLinkDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.SpecGroupLinkMapper.MybatisSpecGroupLinkDAO_update_dynamic", specGroupLinkDO);
	}

	@Override
	public SpecGroupLinkDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.SpecGroupLinkMapper.MybatisSpecGroupLinkDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(SpecGroupLinkDO specGroupLinkDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.SpecGroupLinkMapper.MybatisSpecGroupLinkDAO_select_dynamic_count", specGroupLinkDO);
	}

	@Override
	public List<SpecGroupLinkDO> selectDynamic(SpecGroupLinkDO specGroupLinkDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.SpecGroupLinkMapper.MybatisSpecGroupLinkDAO_select_dynamic", specGroupLinkDO);
	}

	@Override
	public List<SpecGroupLinkDO> selectDynamicPageQuery(SpecGroupLinkDO specGroupLinkDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.SpecGroupLinkMapper.MybatisSpecGroupLinkDAO_select_dynamic_page_query", specGroupLinkDO);
	}
	
	@Override
	public void insertBatch(List<SpecGroupLinkDO> list) throws DAOException {
		getSqlSession().insert("ng.bayue.base.domain.SpecGroupLinkMapper.MybatisSpecGroupLinkDAO_insert_batch", list);
	}

}

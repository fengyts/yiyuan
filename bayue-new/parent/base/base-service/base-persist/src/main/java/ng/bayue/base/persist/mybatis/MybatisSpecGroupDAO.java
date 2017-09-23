package ng.bayue.base.persist.mybatis;
import java.util.List;

import ng.bayue.base.domain.SpecGroupDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.persist.dao.SpecGroupDAO;

import org.springframework.stereotype.Component;

@Component(value="specGroupDAO")
public class MybatisSpecGroupDAO extends MybatisBaseDAO implements SpecGroupDAO {
	public Long insert(SpecGroupDO specGroupDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.base.domain.SpecGroupMapper.MybatisSpecGroupDAO_insert", specGroupDO);
		if (i > 0) {
			return Long.valueOf(specGroupDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(SpecGroupDO specGroupDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.SpecGroupMapper.MybatisSpecGroupDAO_updateById", specGroupDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.base.domain.SpecGroupMapper.MybatisSpecGroupDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(SpecGroupDO specGroupDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.SpecGroupMapper.MybatisSpecGroupDAO_update_dynamic", specGroupDO);
	}

	@Override
	public SpecGroupDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.SpecGroupMapper.MybatisSpecGroupDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(SpecGroupDO specGroupDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.SpecGroupMapper.MybatisSpecGroupDAO_select_dynamic_count", specGroupDO);
	}

	@Override
	public List<SpecGroupDO> selectDynamic(SpecGroupDO specGroupDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.SpecGroupMapper.MybatisSpecGroupDAO_select_dynamic", specGroupDO);
	}

	@Override
	public List<SpecGroupDO> selectDynamicPageQuery(SpecGroupDO specGroupDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.SpecGroupMapper.MybatisSpecGroupDAO_select_dynamic_page_query", specGroupDO);
	}

	@Override
	public List<SpecGroupDO> selectByIds(List<Long> groupIds) {
		return getSqlSession().selectList("ng.bayue.base.domain.SpecGroupMapper.MybatisSpecGroupDAO_select_by_ids", groupIds);
	}

}

package ng.bayue.base.persist.mybatis;
import java.util.List;

import ng.bayue.base.domain.SpecDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.persist.dao.SpecDAO;

import org.springframework.stereotype.Component;

@Component(value="specDAO")
public class MybatisSpecDAO extends MybatisBaseDAO implements SpecDAO {
	public Long insert(SpecDO specDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.base.domain.SpecMapper.MybatisSpecDAO_insert", specDO);
		if (i > 0) {
			return Long.valueOf(specDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(SpecDO specDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.SpecMapper.MybatisSpecDAO_updateById", specDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.base.domain.SpecMapper.MybatisSpecDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(SpecDO specDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.SpecMapper.MybatisSpecDAO_update_dynamic", specDO);
	}

	@Override
	public SpecDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.SpecMapper.MybatisSpecDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(SpecDO specDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.SpecMapper.MybatisSpecDAO_select_dynamic_count", specDO);
	}

	@Override
	public List<SpecDO> selectDynamic(SpecDO specDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.SpecMapper.MybatisSpecDAO_select_dynamic", specDO);
	}

	@Override
	public List<SpecDO> selectDynamicPageQuery(SpecDO specDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.SpecMapper.MybatisSpecDAO_select_dynamic_page_query", specDO);
	}
	
	@Override
	public List<SpecDO> selectByIds(List<Long> ids){
		return getSqlSession().selectList("ng.bayue.base.domain.SpecMapper.MybatisSpecDAO_select_byIds", ids);
	}

}

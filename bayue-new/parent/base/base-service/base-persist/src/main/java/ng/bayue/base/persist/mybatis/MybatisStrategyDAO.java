package ng.bayue.base.persist.mybatis;
import java.util.List;

import ng.bayue.base.domain.StrategyDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.persist.dao.StrategyDAO;

import org.springframework.stereotype.Component;

@Component(value="strategyDAO")
public class MybatisStrategyDAO extends MybatisBaseDAO implements StrategyDAO {
	public Long insert(StrategyDO strategyDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.base.domain.StrategyMapper.MybatisStrategyDAO_insert", strategyDO);
		if (i > 0) {
			return Long.valueOf(strategyDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(StrategyDO strategyDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.StrategyMapper.MybatisStrategyDAO_updateById", strategyDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.base.domain.StrategyMapper.MybatisStrategyDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(StrategyDO strategyDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.StrategyMapper.MybatisStrategyDAO_update_dynamic", strategyDO);
	}

	@Override
	public StrategyDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.StrategyMapper.MybatisStrategyDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(StrategyDO strategyDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.StrategyMapper.MybatisStrategyDAO_select_dynamic_count", strategyDO);
	}

	@Override
	public List<StrategyDO> selectDynamic(StrategyDO strategyDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.StrategyMapper.MybatisStrategyDAO_select_dynamic", strategyDO);
	}

	@Override
	public List<StrategyDO> selectDynamicPageQuery(StrategyDO strategyDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.StrategyMapper.MybatisStrategyDAO_select_dynamic_page_query", strategyDO);
	}

}

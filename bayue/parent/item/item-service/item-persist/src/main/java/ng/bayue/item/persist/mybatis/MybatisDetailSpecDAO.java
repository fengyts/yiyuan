package ng.bayue.item.persist.mybatis;
import java.util.List;

import ng.bayue.item.domain.DetailSpecDO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.persist.dao.DetailSpecDAO;

import org.springframework.stereotype.Component;

@Component(value="detailSpecDAO")
public class MybatisDetailSpecDAO extends MybatisBaseDAO implements DetailSpecDAO {
	public Long insert(DetailSpecDO detailSpecDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.item.domain.DetailSpecMapper.MybatisDetailSpecDAO_insert", detailSpecDO);
		if (i > 0) {
			return Long.valueOf(detailSpecDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(DetailSpecDO detailSpecDO) throws DAOException {
		return getSqlSession().update("ng.bayue.item.domain.DetailSpecMapper.MybatisDetailSpecDAO_updateById", detailSpecDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.item.domain.DetailSpecMapper.MybatisDetailSpecDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(DetailSpecDO detailSpecDO) throws DAOException {
		return getSqlSession().update("ng.bayue.item.domain.DetailSpecMapper.MybatisDetailSpecDAO_update_dynamic", detailSpecDO);
	}

	@Override
	public DetailSpecDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.item.domain.DetailSpecMapper.MybatisDetailSpecDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(DetailSpecDO detailSpecDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.item.domain.DetailSpecMapper.MybatisDetailSpecDAO_select_dynamic_count", detailSpecDO);
	}

	@Override
	public List<DetailSpecDO> selectDynamic(DetailSpecDO detailSpecDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.item.domain.DetailSpecMapper.MybatisDetailSpecDAO_select_dynamic", detailSpecDO);
	}

	@Override
	public List<DetailSpecDO> selectDynamicPageQuery(DetailSpecDO detailSpecDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.item.domain.DetailSpecMapper.MybatisDetailSpecDAO_select_dynamic_page_query", detailSpecDO);
	}

}

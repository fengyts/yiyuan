package ng.bayue.base.persist.mybatis;
import java.util.List;

import ng.bayue.base.domain.DistrictInfoDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.persist.dao.DistrictInfoDAO;

import org.springframework.stereotype.Component;

@Component(value="districtInfoDAO")
public class MybatisDistrictInfoDAO extends MybatisBaseDAO implements DistrictInfoDAO {
	public Long insert(DistrictInfoDO districtInfoDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.base.domain.DistrictInfoMapper.MybatisDistrictInfoDAO_insert", districtInfoDO);
		if (i > 0) {
			return Long.valueOf(districtInfoDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(DistrictInfoDO districtInfoDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.DistrictInfoMapper.MybatisDistrictInfoDAO_updateById", districtInfoDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.base.domain.DistrictInfoMapper.MybatisDistrictInfoDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(DistrictInfoDO districtInfoDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.DistrictInfoMapper.MybatisDistrictInfoDAO_update_dynamic", districtInfoDO);
	}

	@Override
	public DistrictInfoDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.DistrictInfoMapper.MybatisDistrictInfoDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(DistrictInfoDO districtInfoDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.DistrictInfoMapper.MybatisDistrictInfoDAO_select_dynamic_count", districtInfoDO);
	}

	@Override
	public List<DistrictInfoDO> selectDynamic(DistrictInfoDO districtInfoDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.DistrictInfoMapper.MybatisDistrictInfoDAO_select_dynamic", districtInfoDO);
	}

	@Override
	public List<DistrictInfoDO> selectDynamicPageQuery(DistrictInfoDO districtInfoDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.DistrictInfoMapper.MybatisDistrictInfoDAO_select_dynamic_page_query", districtInfoDO);
	}

}

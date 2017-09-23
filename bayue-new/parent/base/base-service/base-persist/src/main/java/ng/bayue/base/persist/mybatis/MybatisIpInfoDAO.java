package ng.bayue.base.persist.mybatis;
import java.util.List;

import ng.bayue.base.domain.IpInfoDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.persist.dao.IpInfoDAO;

import org.springframework.stereotype.Component;

@Component(value="ipInfoDAO")
public class MybatisIpInfoDAO extends MybatisBaseDAO implements IpInfoDAO {
	public Long insert(IpInfoDO ipInfoDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.base.domain.IpInfoMapper.MybatisIpInfoDAO_insert", ipInfoDO);
		if (i > 0) {
			return Long.valueOf(ipInfoDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(IpInfoDO ipInfoDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.IpInfoMapper.MybatisIpInfoDAO_updateById", ipInfoDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.base.domain.IpInfoMapper.MybatisIpInfoDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(IpInfoDO ipInfoDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.IpInfoMapper.MybatisIpInfoDAO_update_dynamic", ipInfoDO);
	}

	@Override
	public IpInfoDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.IpInfoMapper.MybatisIpInfoDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(IpInfoDO ipInfoDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.IpInfoMapper.MybatisIpInfoDAO_select_dynamic_count", ipInfoDO);
	}

	@Override
	public List<IpInfoDO> selectDynamic(IpInfoDO ipInfoDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.IpInfoMapper.MybatisIpInfoDAO_select_dynamic", ipInfoDO);
	}

	@Override
	public List<IpInfoDO> selectDynamicPageQuery(IpInfoDO ipInfoDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.IpInfoMapper.MybatisIpInfoDAO_select_dynamic_page_query", ipInfoDO);
	}

	@Override
	public IpInfoDO selectIpInfoByIpInt(Long ip) {
		return getSqlSession().selectOne("ng.bayue.base.domain.IpInfoMapper.MybatisIpInfoDAO_select_by_ipint", ip);
	}

}

package ng.bayue.backend.persist.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;

import ng.bayue.backend.domain.SysUserDO;
import ng.bayue.backend.persist.common.MybatisBaseDAO;
import ng.bayue.backend.persist.dao.SysUserDAO;
import ng.bayue.backend.persist.exception.DAOException;

@Component(value="sysUserDAO")
public class MybatisSysUserDAO extends MybatisBaseDAO implements SysUserDAO {
	public Long insert(SysUserDO sysUserDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.backend.domain.SysUserMapper.MybatisSysUserDAO_insert", sysUserDO);
		if (i > 0) {
			return Long.valueOf(sysUserDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(SysUserDO sysUserDO) throws DAOException {
		return getSqlSession().update("ng.bayue.backend.domain.SysUserMapper.MybatisSysUserDAO_updateById", sysUserDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.backend.domain.SysUserMapper.MybatisSysUserDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(SysUserDO sysUserDO) throws DAOException {
		return getSqlSession().update("ng.bayue.backend.domain.SysUserMapper.MybatisSysUserDAO_update_dynamic", sysUserDO);
	}

	@Override
	public SysUserDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.backend.domain.SysUserMapper.MybatisSysUserDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(SysUserDO sysUserDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.backend.domain.SysUserMapper.MybatisSysUserDAO_select_dynamic_count", sysUserDO);
	}

	@Override
	public List<SysUserDO> selectDynamic(SysUserDO sysUserDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysUserMapper.MybatisSysUserDAO_select_dynamic", sysUserDO);
	}

	@Override
	public List<SysUserDO> selectDynamicPageQuery(SysUserDO sysUserDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysUserMapper.MybatisSysUserDAO_select_dynamic_page_query", sysUserDO);
	}

}
